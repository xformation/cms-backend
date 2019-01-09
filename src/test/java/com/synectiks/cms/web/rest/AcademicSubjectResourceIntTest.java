package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.AcademicSubject;
import com.synectiks.cms.repository.AcademicSubjectRepository;
import com.synectiks.cms.repository.search.AcademicSubjectSearchRepository;
import com.synectiks.cms.service.AcademicSubjectService;
import com.synectiks.cms.service.dto.AcademicSubjectDTO;
import com.synectiks.cms.service.mapper.AcademicSubjectMapper;
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

/**
 * Test class for the AcademicSubjectResource REST controller.
 *
 * @see AcademicSubjectResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class AcademicSubjectResourceIntTest {

    private static final String DEFAULT_SUBJECT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SUBJECT_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ELECTIVE_SUB = false;
    private static final Boolean UPDATED_ELECTIVE_SUB = true;

    @Autowired
    private AcademicSubjectRepository academicSubjectRepository;


    @Autowired
    private AcademicSubjectMapper academicSubjectMapper;
    

    @Autowired
    private AcademicSubjectService academicSubjectService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.AcademicSubjectSearchRepositoryMockConfiguration
     */
    @Autowired
    private AcademicSubjectSearchRepository mockAcademicSubjectSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAcademicSubjectMockMvc;

    private AcademicSubject academicSubject;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AcademicSubjectResource academicSubjectResource = new AcademicSubjectResource(academicSubjectService);
        this.restAcademicSubjectMockMvc = MockMvcBuilders.standaloneSetup(academicSubjectResource)
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
    public static AcademicSubject createEntity(EntityManager em) {
        AcademicSubject academicSubject = new AcademicSubject()
            .subjectName(DEFAULT_SUBJECT_NAME)
            .electiveSub(DEFAULT_ELECTIVE_SUB);
        return academicSubject;
    }

    @Before
    public void initTest() {
        academicSubject = createEntity(em);
    }

    @Test
    @Transactional
    public void createAcademicSubject() throws Exception {
        int databaseSizeBeforeCreate = academicSubjectRepository.findAll().size();

        // Create the AcademicSubject
        AcademicSubjectDTO academicSubjectDTO = academicSubjectMapper.toDto(academicSubject);
        restAcademicSubjectMockMvc.perform(post("/api/academic-subjects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicSubjectDTO)))
            .andExpect(status().isCreated());

        // Validate the AcademicSubject in the database
        List<AcademicSubject> academicSubjectList = academicSubjectRepository.findAll();
        assertThat(academicSubjectList).hasSize(databaseSizeBeforeCreate + 1);
        AcademicSubject testAcademicSubject = academicSubjectList.get(academicSubjectList.size() - 1);
        assertThat(testAcademicSubject.getSubjectName()).isEqualTo(DEFAULT_SUBJECT_NAME);
        assertThat(testAcademicSubject.isElectiveSub()).isEqualTo(DEFAULT_ELECTIVE_SUB);

        // Validate the AcademicSubject in Elasticsearch
        verify(mockAcademicSubjectSearchRepository, times(1)).save(testAcademicSubject);
    }

    @Test
    @Transactional
    public void createAcademicSubjectWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = academicSubjectRepository.findAll().size();

        // Create the AcademicSubject with an existing ID
        academicSubject.setId(1L);
        AcademicSubjectDTO academicSubjectDTO = academicSubjectMapper.toDto(academicSubject);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAcademicSubjectMockMvc.perform(post("/api/academic-subjects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicSubjectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AcademicSubject in the database
        List<AcademicSubject> academicSubjectList = academicSubjectRepository.findAll();
        assertThat(academicSubjectList).hasSize(databaseSizeBeforeCreate);

        // Validate the AcademicSubject in Elasticsearch
        verify(mockAcademicSubjectSearchRepository, times(0)).save(academicSubject);
    }

    @Test
    @Transactional
    public void checkSubjectNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = academicSubjectRepository.findAll().size();
        // set the field null
        academicSubject.setSubjectName(null);

        // Create the AcademicSubject, which fails.
        AcademicSubjectDTO academicSubjectDTO = academicSubjectMapper.toDto(academicSubject);

        restAcademicSubjectMockMvc.perform(post("/api/academic-subjects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicSubjectDTO)))
            .andExpect(status().isBadRequest());

        List<AcademicSubject> academicSubjectList = academicSubjectRepository.findAll();
        assertThat(academicSubjectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkElectiveSubIsRequired() throws Exception {
        int databaseSizeBeforeTest = academicSubjectRepository.findAll().size();
        // set the field null
        academicSubject.setElectiveSub(null);

        // Create the AcademicSubject, which fails.
        AcademicSubjectDTO academicSubjectDTO = academicSubjectMapper.toDto(academicSubject);

        restAcademicSubjectMockMvc.perform(post("/api/academic-subjects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicSubjectDTO)))
            .andExpect(status().isBadRequest());

        List<AcademicSubject> academicSubjectList = academicSubjectRepository.findAll();
        assertThat(academicSubjectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAcademicSubjects() throws Exception {
        // Initialize the database
        academicSubjectRepository.saveAndFlush(academicSubject);

        // Get all the academicSubjectList
        restAcademicSubjectMockMvc.perform(get("/api/academic-subjects?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(academicSubject.getId().intValue())))
            .andExpect(jsonPath("$.[*].subjectName").value(hasItem(DEFAULT_SUBJECT_NAME.toString())))
            .andExpect(jsonPath("$.[*].electiveSub").value(hasItem(DEFAULT_ELECTIVE_SUB.booleanValue())));
    }
    

    @Test
    @Transactional
    public void getAcademicSubject() throws Exception {
        // Initialize the database
        academicSubjectRepository.saveAndFlush(academicSubject);

        // Get the academicSubject
        restAcademicSubjectMockMvc.perform(get("/api/academic-subjects/{id}", academicSubject.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(academicSubject.getId().intValue()))
            .andExpect(jsonPath("$.subjectName").value(DEFAULT_SUBJECT_NAME.toString()))
            .andExpect(jsonPath("$.electiveSub").value(DEFAULT_ELECTIVE_SUB.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingAcademicSubject() throws Exception {
        // Get the academicSubject
        restAcademicSubjectMockMvc.perform(get("/api/academic-subjects/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAcademicSubject() throws Exception {
        // Initialize the database
        academicSubjectRepository.saveAndFlush(academicSubject);

        int databaseSizeBeforeUpdate = academicSubjectRepository.findAll().size();

        // Update the academicSubject
        AcademicSubject updatedAcademicSubject = academicSubjectRepository.findById(academicSubject.getId()).get();
        // Disconnect from session so that the updates on updatedAcademicSubject are not directly saved in db
        em.detach(updatedAcademicSubject);
        updatedAcademicSubject
            .subjectName(UPDATED_SUBJECT_NAME)
            .electiveSub(UPDATED_ELECTIVE_SUB);
        AcademicSubjectDTO academicSubjectDTO = academicSubjectMapper.toDto(updatedAcademicSubject);

        restAcademicSubjectMockMvc.perform(put("/api/academic-subjects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicSubjectDTO)))
            .andExpect(status().isOk());

        // Validate the AcademicSubject in the database
        List<AcademicSubject> academicSubjectList = academicSubjectRepository.findAll();
        assertThat(academicSubjectList).hasSize(databaseSizeBeforeUpdate);
        AcademicSubject testAcademicSubject = academicSubjectList.get(academicSubjectList.size() - 1);
        assertThat(testAcademicSubject.getSubjectName()).isEqualTo(UPDATED_SUBJECT_NAME);
        assertThat(testAcademicSubject.isElectiveSub()).isEqualTo(UPDATED_ELECTIVE_SUB);

        // Validate the AcademicSubject in Elasticsearch
        verify(mockAcademicSubjectSearchRepository, times(1)).save(testAcademicSubject);
    }

    @Test
    @Transactional
    public void updateNonExistingAcademicSubject() throws Exception {
        int databaseSizeBeforeUpdate = academicSubjectRepository.findAll().size();

        // Create the AcademicSubject
        AcademicSubjectDTO academicSubjectDTO = academicSubjectMapper.toDto(academicSubject);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restAcademicSubjectMockMvc.perform(put("/api/academic-subjects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicSubjectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AcademicSubject in the database
        List<AcademicSubject> academicSubjectList = academicSubjectRepository.findAll();
        assertThat(academicSubjectList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AcademicSubject in Elasticsearch
        verify(mockAcademicSubjectSearchRepository, times(0)).save(academicSubject);
    }

    @Test
    @Transactional
    public void deleteAcademicSubject() throws Exception {
        // Initialize the database
        academicSubjectRepository.saveAndFlush(academicSubject);

        int databaseSizeBeforeDelete = academicSubjectRepository.findAll().size();

        // Get the academicSubject
        restAcademicSubjectMockMvc.perform(delete("/api/academic-subjects/{id}", academicSubject.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AcademicSubject> academicSubjectList = academicSubjectRepository.findAll();
        assertThat(academicSubjectList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AcademicSubject in Elasticsearch
        verify(mockAcademicSubjectSearchRepository, times(1)).deleteById(academicSubject.getId());
    }

    @Test
    @Transactional
    public void searchAcademicSubject() throws Exception {
        // Initialize the database
        academicSubjectRepository.saveAndFlush(academicSubject);
        when(mockAcademicSubjectSearchRepository.search(queryStringQuery("id:" + academicSubject.getId())))
            .thenReturn(Collections.singletonList(academicSubject));
        // Search the academicSubject
        restAcademicSubjectMockMvc.perform(get("/api/_search/academic-subjects?query=id:" + academicSubject.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(academicSubject.getId().intValue())))
            .andExpect(jsonPath("$.[*].subjectName").value(hasItem(DEFAULT_SUBJECT_NAME.toString())))
            .andExpect(jsonPath("$.[*].electiveSub").value(hasItem(DEFAULT_ELECTIVE_SUB.booleanValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AcademicSubject.class);
        AcademicSubject academicSubject1 = new AcademicSubject();
        academicSubject1.setId(1L);
        AcademicSubject academicSubject2 = new AcademicSubject();
        academicSubject2.setId(academicSubject1.getId());
        assertThat(academicSubject1).isEqualTo(academicSubject2);
        academicSubject2.setId(2L);
        assertThat(academicSubject1).isNotEqualTo(academicSubject2);
        academicSubject1.setId(null);
        assertThat(academicSubject1).isNotEqualTo(academicSubject2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AcademicSubjectDTO.class);
        AcademicSubjectDTO academicSubjectDTO1 = new AcademicSubjectDTO();
        academicSubjectDTO1.setId(1L);
        AcademicSubjectDTO academicSubjectDTO2 = new AcademicSubjectDTO();
        assertThat(academicSubjectDTO1).isNotEqualTo(academicSubjectDTO2);
        academicSubjectDTO2.setId(academicSubjectDTO1.getId());
        assertThat(academicSubjectDTO1).isEqualTo(academicSubjectDTO2);
        academicSubjectDTO2.setId(2L);
        assertThat(academicSubjectDTO1).isNotEqualTo(academicSubjectDTO2);
        academicSubjectDTO1.setId(null);
        assertThat(academicSubjectDTO1).isNotEqualTo(academicSubjectDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(academicSubjectMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(academicSubjectMapper.fromId(null)).isNull();
    }
}
