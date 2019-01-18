package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.StudentSubject;
import com.synectiks.cms.repository.StudentSubjectRepository;
import com.synectiks.cms.repository.search.StudentSubjectSearchRepository;
import com.synectiks.cms.service.StudentSubjectService;
import com.synectiks.cms.service.dto.StudentSubjectDTO;
import com.synectiks.cms.service.mapper.StudentSubjectMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;


import static com.synectiks.cms.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the StudentSubjectResource REST controller.
 *
 * @see StudentSubjectResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class StudentSubjectResourceIntTest {

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final Date DEFAULT_LASTUPDATED_DATE = new Date();
    private static final Date UPDATED_LASTUPDATED_DATE = new Date();

    @Autowired
    private StudentSubjectRepository studentSubjectRepository;


    @Autowired
    private StudentSubjectMapper studentSubjectMapper;


    @Autowired
    private StudentSubjectService studentSubjectService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.StudentSubjectSearchRepositoryMockConfiguration
     */
    @Autowired
    private StudentSubjectSearchRepository mockStudentSubjectSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStudentSubjectMockMvc;

    private StudentSubject studentSubject;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StudentSubjectResource studentSubjectResource = new StudentSubjectResource(studentSubjectService);
        this.restStudentSubjectMockMvc = MockMvcBuilders.standaloneSetup(studentSubjectResource)
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
    public static StudentSubject createEntity(EntityManager em) {
        StudentSubject studentSubject = new StudentSubject();
        studentSubject.comments(DEFAULT_COMMENTS);
        studentSubject.setLastupdatedDate(DEFAULT_LASTUPDATED_DATE);
        return studentSubject;
    }

    @Before
    public void initTest() {
        studentSubject = createEntity(em);
    }

    @Test
    @Transactional
    public void createStudentSubject() throws Exception {
        int databaseSizeBeforeCreate = studentSubjectRepository.findAll().size();

        // Create the StudentSubject
        StudentSubjectDTO studentSubjectDTO = studentSubjectMapper.toDto(studentSubject);
        restStudentSubjectMockMvc.perform(post("/api/student-subjects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentSubjectDTO)))
            .andExpect(status().isCreated());

        // Validate the StudentSubject in the database
        List<StudentSubject> studentSubjectList = studentSubjectRepository.findAll();
        assertThat(studentSubjectList).hasSize(databaseSizeBeforeCreate + 1);
        StudentSubject testStudentSubject = studentSubjectList.get(studentSubjectList.size() - 1);
        assertThat(testStudentSubject.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testStudentSubject.getLastupdatedDate()).isEqualTo(DEFAULT_LASTUPDATED_DATE);

        // Validate the StudentSubject in Elasticsearch
        verify(mockStudentSubjectSearchRepository, times(1)).save(testStudentSubject);
    }

    @Test
    @Transactional
    public void createStudentSubjectWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = studentSubjectRepository.findAll().size();

        // Create the StudentSubject with an existing ID
        studentSubject.setId(1L);
        StudentSubjectDTO studentSubjectDTO = studentSubjectMapper.toDto(studentSubject);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStudentSubjectMockMvc.perform(post("/api/student-subjects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentSubjectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StudentSubject in the database
        List<StudentSubject> studentSubjectList = studentSubjectRepository.findAll();
        assertThat(studentSubjectList).hasSize(databaseSizeBeforeCreate);

        // Validate the StudentSubject in Elasticsearch
        verify(mockStudentSubjectSearchRepository, times(0)).save(studentSubject);
    }

    @Test
    @Transactional
    public void checkLastupdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentSubjectRepository.findAll().size();
        // set the field null
        studentSubject.setLastupdatedDate(null);

        // Create the StudentSubject, which fails.
        StudentSubjectDTO studentSubjectDTO = studentSubjectMapper.toDto(studentSubject);

        restStudentSubjectMockMvc.perform(post("/api/student-subjects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentSubjectDTO)))
            .andExpect(status().isBadRequest());

        List<StudentSubject> studentSubjectList = studentSubjectRepository.findAll();
        assertThat(studentSubjectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStudentSubjects() throws Exception {
        // Initialize the database
        studentSubjectRepository.saveAndFlush(studentSubject);

        // Get all the studentSubjectList
        restStudentSubjectMockMvc.perform(get("/api/student-subjects?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(studentSubject.getId().intValue())))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS.toString())))
            .andExpect(jsonPath("$.[*].lastupdatedDate").value(hasItem(DEFAULT_LASTUPDATED_DATE.toString())));
    }


    @Test
    @Transactional
    public void getStudentSubject() throws Exception {
        // Initialize the database
        studentSubjectRepository.saveAndFlush(studentSubject);

        // Get the studentSubject
        restStudentSubjectMockMvc.perform(get("/api/student-subjects/{id}", studentSubject.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(studentSubject.getId().intValue()))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS.toString()))
            .andExpect(jsonPath("$.lastupdatedDate").value(DEFAULT_LASTUPDATED_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingStudentSubject() throws Exception {
        // Get the studentSubject
        restStudentSubjectMockMvc.perform(get("/api/student-subjects/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStudentSubject() throws Exception {
        // Initialize the database
        studentSubjectRepository.saveAndFlush(studentSubject);

        int databaseSizeBeforeUpdate = studentSubjectRepository.findAll().size();

        // Update the studentSubject
        StudentSubject updatedStudentSubject = studentSubjectRepository.findById(studentSubject.getId()).get();
        // Disconnect from session so that the updates on updatedStudentSubject are not directly saved in db
        em.detach(updatedStudentSubject);
        //updatedStudentSubject
        updatedStudentSubject.comments(UPDATED_COMMENTS);
        updatedStudentSubject.setLastupdatedDate(UPDATED_LASTUPDATED_DATE);
        StudentSubjectDTO studentSubjectDTO = studentSubjectMapper.toDto(updatedStudentSubject);

        restStudentSubjectMockMvc.perform(put("/api/student-subjects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentSubjectDTO)))
            .andExpect(status().isOk());

        // Validate the StudentSubject in the database
        List<StudentSubject> studentSubjectList = studentSubjectRepository.findAll();
        assertThat(studentSubjectList).hasSize(databaseSizeBeforeUpdate);
        StudentSubject testStudentSubject = studentSubjectList.get(studentSubjectList.size() - 1);
        assertThat(testStudentSubject.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testStudentSubject.getLastupdatedDate()).isEqualTo(UPDATED_LASTUPDATED_DATE);

        // Validate the StudentSubject in Elasticsearch
        verify(mockStudentSubjectSearchRepository, times(1)).save(testStudentSubject);
    }

    @Test
    @Transactional
    public void updateNonExistingStudentSubject() throws Exception {
        int databaseSizeBeforeUpdate = studentSubjectRepository.findAll().size();

        // Create the StudentSubject
        StudentSubjectDTO studentSubjectDTO = studentSubjectMapper.toDto(studentSubject);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restStudentSubjectMockMvc.perform(put("/api/student-subjects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentSubjectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StudentSubject in the database
        List<StudentSubject> studentSubjectList = studentSubjectRepository.findAll();
        assertThat(studentSubjectList).hasSize(databaseSizeBeforeUpdate);

        // Validate the StudentSubject in Elasticsearch
        verify(mockStudentSubjectSearchRepository, times(0)).save(studentSubject);
    }

    @Test
    @Transactional
    public void deleteStudentSubject() throws Exception {
        // Initialize the database
        studentSubjectRepository.saveAndFlush(studentSubject);

        int databaseSizeBeforeDelete = studentSubjectRepository.findAll().size();

        // Get the studentSubject
        restStudentSubjectMockMvc.perform(delete("/api/student-subjects/{id}", studentSubject.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<StudentSubject> studentSubjectList = studentSubjectRepository.findAll();
        assertThat(studentSubjectList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the StudentSubject in Elasticsearch
        verify(mockStudentSubjectSearchRepository, times(1)).deleteById(studentSubject.getId());
    }

    @Test
    @Transactional
    public void searchStudentSubject() throws Exception {
        // Initialize the database
        studentSubjectRepository.saveAndFlush(studentSubject);
        when(mockStudentSubjectSearchRepository.search(queryStringQuery("id:" + studentSubject.getId())))
            .thenReturn(Collections.singletonList(studentSubject));
        // Search the studentSubject
        restStudentSubjectMockMvc.perform(get("/api/_search/student-subjects?query=id:" + studentSubject.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(studentSubject.getId().intValue())))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS.toString())))
            .andExpect(jsonPath("$.[*].lastupdatedDate").value(hasItem(DEFAULT_LASTUPDATED_DATE.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StudentSubject.class);
        StudentSubject studentSubject1 = new StudentSubject();
        studentSubject1.setId(1L);
        StudentSubject studentSubject2 = new StudentSubject();
        studentSubject2.setId(studentSubject1.getId());
        assertThat(studentSubject1).isEqualTo(studentSubject2);
        studentSubject2.setId(2L);
        assertThat(studentSubject1).isNotEqualTo(studentSubject2);
        studentSubject1.setId(null);
        assertThat(studentSubject1).isNotEqualTo(studentSubject2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StudentSubjectDTO.class);
        StudentSubjectDTO studentSubjectDTO1 = new StudentSubjectDTO();
        studentSubjectDTO1.setId(1L);
        StudentSubjectDTO studentSubjectDTO2 = new StudentSubjectDTO();
        assertThat(studentSubjectDTO1).isNotEqualTo(studentSubjectDTO2);
        studentSubjectDTO2.setId(studentSubjectDTO1.getId());
        assertThat(studentSubjectDTO1).isEqualTo(studentSubjectDTO2);
        studentSubjectDTO2.setId(2L);
        assertThat(studentSubjectDTO1).isNotEqualTo(studentSubjectDTO2);
        studentSubjectDTO1.setId(null);
        assertThat(studentSubjectDTO1).isNotEqualTo(studentSubjectDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(studentSubjectMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(studentSubjectMapper.fromId(null)).isNull();
    }
}
