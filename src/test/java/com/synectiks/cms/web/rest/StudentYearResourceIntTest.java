package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.StudentYear;
import com.synectiks.cms.repository.StudentYearRepository;
import com.synectiks.cms.repository.search.StudentYearSearchRepository;
import com.synectiks.cms.service.StudentYearService;
import com.synectiks.cms.service.dto.StudentYearDTO;
import com.synectiks.cms.service.mapper.StudentYearMapper;
import com.synectiks.cms.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;


import static com.synectiks.cms.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.synectiks.cms.domain.enumeration.SYear;
/**
 * Test class for the StudentYearResource REST controller.
 *
 * @see StudentYearResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class StudentYearResourceIntTest {

    private static final SYear DEFAULT_YEAR_DESC = SYear.I;
    private static final SYear UPDATED_YEAR_DESC = SYear.II;

    @Autowired
    private StudentYearRepository studentYearRepository;


    @Autowired
    private StudentYearMapper studentYearMapper;
    

    @Autowired
    private StudentYearService studentYearService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.StudentYearSearchRepositoryMockConfiguration
     */
    @Autowired
    private StudentYearSearchRepository mockStudentYearSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStudentYearMockMvc;

    private StudentYear studentYear;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StudentYearResource studentYearResource = new StudentYearResource(studentYearService);
        this.restStudentYearMockMvc = MockMvcBuilders.standaloneSetup(studentYearResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StudentYear createEntity(EntityManager em) {
        StudentYear studentYear = new StudentYear()
            .yearDesc(DEFAULT_YEAR_DESC);
        return studentYear;
    }

    @Before
    public void initTest() {
        studentYear = createEntity(em);
    }

    @Test
    @Transactional
    public void createStudentYear() throws Exception {
        int databaseSizeBeforeCreate = studentYearRepository.findAll().size();

        // Create the StudentYear
        StudentYearDTO studentYearDTO = studentYearMapper.toDto(studentYear);
        restStudentYearMockMvc.perform(post("/api/student-years")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentYearDTO)))
            .andExpect(status().isCreated());

        // Validate the StudentYear in the database
        List<StudentYear> studentYearList = studentYearRepository.findAll();
        assertThat(studentYearList).hasSize(databaseSizeBeforeCreate + 1);
        StudentYear testStudentYear = studentYearList.get(studentYearList.size() - 1);
        assertThat(testStudentYear.getYearDesc()).isEqualTo(DEFAULT_YEAR_DESC);

        // Validate the StudentYear in Elasticsearch
        verify(mockStudentYearSearchRepository, times(1)).save(testStudentYear);
    }

    @Test
    @Transactional
    public void createStudentYearWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = studentYearRepository.findAll().size();

        // Create the StudentYear with an existing ID
        studentYear.setId(1L);
        StudentYearDTO studentYearDTO = studentYearMapper.toDto(studentYear);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStudentYearMockMvc.perform(post("/api/student-years")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentYearDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StudentYear in the database
        List<StudentYear> studentYearList = studentYearRepository.findAll();
        assertThat(studentYearList).hasSize(databaseSizeBeforeCreate);

        // Validate the StudentYear in Elasticsearch
        verify(mockStudentYearSearchRepository, times(0)).save(studentYear);
    }

    @Test
    @Transactional
    public void checkYearDescIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentYearRepository.findAll().size();
        // set the field null
        studentYear.setYearDesc(null);

        // Create the StudentYear, which fails.
        StudentYearDTO studentYearDTO = studentYearMapper.toDto(studentYear);

        restStudentYearMockMvc.perform(post("/api/student-years")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentYearDTO)))
            .andExpect(status().isBadRequest());

        List<StudentYear> studentYearList = studentYearRepository.findAll();
        assertThat(studentYearList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStudentYears() throws Exception {
        // Initialize the database
        studentYearRepository.saveAndFlush(studentYear);

        // Get all the studentYearList
        restStudentYearMockMvc.perform(get("/api/student-years?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(studentYear.getId().intValue())))
            .andExpect(jsonPath("$.[*].yearDesc").value(hasItem(DEFAULT_YEAR_DESC.toString())));
    }
    

    @Test
    @Transactional
    public void getStudentYear() throws Exception {
        // Initialize the database
        studentYearRepository.saveAndFlush(studentYear);

        // Get the studentYear
        restStudentYearMockMvc.perform(get("/api/student-years/{id}", studentYear.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(studentYear.getId().intValue()))
            .andExpect(jsonPath("$.yearDesc").value(DEFAULT_YEAR_DESC.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingStudentYear() throws Exception {
        // Get the studentYear
        restStudentYearMockMvc.perform(get("/api/student-years/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStudentYear() throws Exception {
        // Initialize the database
        studentYearRepository.saveAndFlush(studentYear);

        int databaseSizeBeforeUpdate = studentYearRepository.findAll().size();

        // Update the studentYear
        StudentYear updatedStudentYear = studentYearRepository.findById(studentYear.getId()).get();
        // Disconnect from session so that the updates on updatedStudentYear are not directly saved in db
        em.detach(updatedStudentYear);
        updatedStudentYear
            .yearDesc(UPDATED_YEAR_DESC);
        StudentYearDTO studentYearDTO = studentYearMapper.toDto(updatedStudentYear);

        restStudentYearMockMvc.perform(put("/api/student-years")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentYearDTO)))
            .andExpect(status().isOk());

        // Validate the StudentYear in the database
        List<StudentYear> studentYearList = studentYearRepository.findAll();
        assertThat(studentYearList).hasSize(databaseSizeBeforeUpdate);
        StudentYear testStudentYear = studentYearList.get(studentYearList.size() - 1);
        assertThat(testStudentYear.getYearDesc()).isEqualTo(UPDATED_YEAR_DESC);

        // Validate the StudentYear in Elasticsearch
        verify(mockStudentYearSearchRepository, times(1)).save(testStudentYear);
    }

    @Test
    @Transactional
    public void updateNonExistingStudentYear() throws Exception {
        int databaseSizeBeforeUpdate = studentYearRepository.findAll().size();

        // Create the StudentYear
        StudentYearDTO studentYearDTO = studentYearMapper.toDto(studentYear);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restStudentYearMockMvc.perform(put("/api/student-years")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentYearDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StudentYear in the database
        List<StudentYear> studentYearList = studentYearRepository.findAll();
        assertThat(studentYearList).hasSize(databaseSizeBeforeUpdate);

        // Validate the StudentYear in Elasticsearch
        verify(mockStudentYearSearchRepository, times(0)).save(studentYear);
    }

    @Test
    @Transactional
    public void deleteStudentYear() throws Exception {
        // Initialize the database
        studentYearRepository.saveAndFlush(studentYear);

        int databaseSizeBeforeDelete = studentYearRepository.findAll().size();

        // Get the studentYear
        restStudentYearMockMvc.perform(delete("/api/student-years/{id}", studentYear.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<StudentYear> studentYearList = studentYearRepository.findAll();
        assertThat(studentYearList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the StudentYear in Elasticsearch
        verify(mockStudentYearSearchRepository, times(1)).deleteById(studentYear.getId());
    }

    @Test
    @Transactional
    public void searchStudentYear() throws Exception {
        // Initialize the database
        studentYearRepository.saveAndFlush(studentYear);
        when(mockStudentYearSearchRepository.search(queryStringQuery("id:" + studentYear.getId())))
            .thenReturn(Collections.singletonList(studentYear));
        // Search the studentYear
        restStudentYearMockMvc.perform(get("/api/_search/student-years?query=id:" + studentYear.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(studentYear.getId().intValue())))
            .andExpect(jsonPath("$.[*].yearDesc").value(hasItem(DEFAULT_YEAR_DESC.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StudentYear.class);
        StudentYear studentYear1 = new StudentYear();
        studentYear1.setId(1L);
        StudentYear studentYear2 = new StudentYear();
        studentYear2.setId(studentYear1.getId());
        assertThat(studentYear1).isEqualTo(studentYear2);
        studentYear2.setId(2L);
        assertThat(studentYear1).isNotEqualTo(studentYear2);
        studentYear1.setId(null);
        assertThat(studentYear1).isNotEqualTo(studentYear2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StudentYearDTO.class);
        StudentYearDTO studentYearDTO1 = new StudentYearDTO();
        studentYearDTO1.setId(1L);
        StudentYearDTO studentYearDTO2 = new StudentYearDTO();
        assertThat(studentYearDTO1).isNotEqualTo(studentYearDTO2);
        studentYearDTO2.setId(studentYearDTO1.getId());
        assertThat(studentYearDTO1).isEqualTo(studentYearDTO2);
        studentYearDTO2.setId(2L);
        assertThat(studentYearDTO1).isNotEqualTo(studentYearDTO2);
        studentYearDTO1.setId(null);
        assertThat(studentYearDTO1).isNotEqualTo(studentYearDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(studentYearMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(studentYearMapper.fromId(null)).isNull();
    }
}
