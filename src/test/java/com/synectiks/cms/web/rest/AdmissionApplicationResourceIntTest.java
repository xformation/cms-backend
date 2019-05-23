package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.AdmissionApplication;
import com.synectiks.cms.repository.AdmissionApplicationRepository;
import com.synectiks.cms.repository.search.AdmissionApplicationSearchRepository;
import com.synectiks.cms.service.AdmissionApplicationService;
import com.synectiks.cms.service.dto.AdmissionApplicationDTO;
import com.synectiks.cms.service.mapper.AdmissionApplicationMapper;
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

import com.synectiks.cms.domain.enumeration.AdmissionStatusEnum;
import com.synectiks.cms.domain.enumeration.CourseEnum;
/**
 * Test class for the AdmissionApplicationResource REST controller.
 *
 * @see AdmissionApplicationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class AdmissionApplicationResourceIntTest {

    private static final AdmissionStatusEnum DEFAULT_ADMISSION_STATUS = AdmissionStatusEnum.RECEIVED;
    private static final AdmissionStatusEnum UPDATED_ADMISSION_STATUS = AdmissionStatusEnum.INPROCESS;

    private static final CourseEnum DEFAULT_COURSE = CourseEnum.BTECH;
    private static final CourseEnum UPDATED_COURSE = CourseEnum.MTECH;

    private static final Date DEFAULT_ADMISSION_DATE = new Date();
    private static final Date UPDATED_ADMISSION_DATE = new Date();

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    @Autowired
    private AdmissionApplicationRepository admissionApplicationRepository;

    @Autowired
    private AdmissionApplicationMapper admissionApplicationMapper;

    @Autowired
    private AdmissionApplicationService admissionApplicationService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.AdmissionApplicationSearchRepositoryMockConfiguration
     */
    @Autowired
    private AdmissionApplicationSearchRepository mockAdmissionApplicationSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAdmissionApplicationMockMvc;

    private AdmissionApplication admissionApplication;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdmissionApplicationResource admissionApplicationResource = new AdmissionApplicationResource(admissionApplicationService);
        this.restAdmissionApplicationMockMvc = MockMvcBuilders.standaloneSetup(admissionApplicationResource)
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
    public static AdmissionApplication createEntity(EntityManager em) {
        AdmissionApplication admissionApplication = new AdmissionApplication()
            .admissionStatus(DEFAULT_ADMISSION_STATUS)
            .course(DEFAULT_COURSE)
            .admissionDate(DEFAULT_ADMISSION_DATE)
            .comments(DEFAULT_COMMENTS);
        return admissionApplication;
    }

    @Before
    public void initTest() {
        admissionApplication = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdmissionApplication() throws Exception {
        int databaseSizeBeforeCreate = admissionApplicationRepository.findAll().size();

        // Create the AdmissionApplication
        AdmissionApplicationDTO admissionApplicationDTO = admissionApplicationMapper.toDto(admissionApplication);
        restAdmissionApplicationMockMvc.perform(post("/api/admission-applications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admissionApplicationDTO)))
            .andExpect(status().isCreated());

        // Validate the AdmissionApplication in the database
        List<AdmissionApplication> admissionApplicationList = admissionApplicationRepository.findAll();
        assertThat(admissionApplicationList).hasSize(databaseSizeBeforeCreate + 1);
        AdmissionApplication testAdmissionApplication = admissionApplicationList.get(admissionApplicationList.size() - 1);
        assertThat(testAdmissionApplication.getAdmissionStatus()).isEqualTo(DEFAULT_ADMISSION_STATUS);
        assertThat(testAdmissionApplication.getCourse()).isEqualTo(DEFAULT_COURSE);
        assertThat(testAdmissionApplication.getAdmissionDate()).isEqualTo(DEFAULT_ADMISSION_DATE);
        assertThat(testAdmissionApplication.getComments()).isEqualTo(DEFAULT_COMMENTS);

        // Validate the AdmissionApplication in Elasticsearch
        verify(mockAdmissionApplicationSearchRepository, times(1)).save(testAdmissionApplication);
    }

    @Test
    @Transactional
    public void createAdmissionApplicationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = admissionApplicationRepository.findAll().size();

        // Create the AdmissionApplication with an existing ID
        admissionApplication.setId(1L);
        AdmissionApplicationDTO admissionApplicationDTO = admissionApplicationMapper.toDto(admissionApplication);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmissionApplicationMockMvc.perform(post("/api/admission-applications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admissionApplicationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdmissionApplication in the database
        List<AdmissionApplication> admissionApplicationList = admissionApplicationRepository.findAll();
        assertThat(admissionApplicationList).hasSize(databaseSizeBeforeCreate);

        // Validate the AdmissionApplication in Elasticsearch
        verify(mockAdmissionApplicationSearchRepository, times(0)).save(admissionApplication);
    }

    @Test
    @Transactional
    public void checkAdmissionStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = admissionApplicationRepository.findAll().size();
        // set the field null
        admissionApplication.setAdmissionStatus(null);

        // Create the AdmissionApplication, which fails.
        AdmissionApplicationDTO admissionApplicationDTO = admissionApplicationMapper.toDto(admissionApplication);

        restAdmissionApplicationMockMvc.perform(post("/api/admission-applications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admissionApplicationDTO)))
            .andExpect(status().isBadRequest());

        List<AdmissionApplication> admissionApplicationList = admissionApplicationRepository.findAll();
        assertThat(admissionApplicationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCourseIsRequired() throws Exception {
        int databaseSizeBeforeTest = admissionApplicationRepository.findAll().size();
        // set the field null
        admissionApplication.setCourse(null);

        // Create the AdmissionApplication, which fails.
        AdmissionApplicationDTO admissionApplicationDTO = admissionApplicationMapper.toDto(admissionApplication);

        restAdmissionApplicationMockMvc.perform(post("/api/admission-applications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admissionApplicationDTO)))
            .andExpect(status().isBadRequest());

        List<AdmissionApplication> admissionApplicationList = admissionApplicationRepository.findAll();
        assertThat(admissionApplicationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAdmissionDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = admissionApplicationRepository.findAll().size();
        // set the field null
        admissionApplication.setAdmissionDate(null);

        // Create the AdmissionApplication, which fails.
        AdmissionApplicationDTO admissionApplicationDTO = admissionApplicationMapper.toDto(admissionApplication);

        restAdmissionApplicationMockMvc.perform(post("/api/admission-applications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admissionApplicationDTO)))
            .andExpect(status().isBadRequest());

        List<AdmissionApplication> admissionApplicationList = admissionApplicationRepository.findAll();
        assertThat(admissionApplicationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCommentsIsRequired() throws Exception {
        int databaseSizeBeforeTest = admissionApplicationRepository.findAll().size();
        // set the field null
        admissionApplication.setComments(null);

        // Create the AdmissionApplication, which fails.
        AdmissionApplicationDTO admissionApplicationDTO = admissionApplicationMapper.toDto(admissionApplication);

        restAdmissionApplicationMockMvc.perform(post("/api/admission-applications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admissionApplicationDTO)))
            .andExpect(status().isBadRequest());

        List<AdmissionApplication> admissionApplicationList = admissionApplicationRepository.findAll();
        assertThat(admissionApplicationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAdmissionApplications() throws Exception {
        // Initialize the database
        admissionApplicationRepository.saveAndFlush(admissionApplication);

        // Get all the admissionApplicationList
        restAdmissionApplicationMockMvc.perform(get("/api/admission-applications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(admissionApplication.getId().intValue())))
            .andExpect(jsonPath("$.[*].admissionStatus").value(hasItem(DEFAULT_ADMISSION_STATUS.toString())))
            .andExpect(jsonPath("$.[*].course").value(hasItem(DEFAULT_COURSE.toString())))
            .andExpect(jsonPath("$.[*].admissionDate").value(hasItem(DEFAULT_ADMISSION_DATE.toString())))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS.toString())));
    }
    
    @Test
    @Transactional
    public void getAdmissionApplication() throws Exception {
        // Initialize the database
        admissionApplicationRepository.saveAndFlush(admissionApplication);

        // Get the admissionApplication
        restAdmissionApplicationMockMvc.perform(get("/api/admission-applications/{id}", admissionApplication.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(admissionApplication.getId().intValue()))
            .andExpect(jsonPath("$.admissionStatus").value(DEFAULT_ADMISSION_STATUS.toString()))
            .andExpect(jsonPath("$.course").value(DEFAULT_COURSE.toString()))
            .andExpect(jsonPath("$.admissionDate").value(DEFAULT_ADMISSION_DATE.toString()))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAdmissionApplication() throws Exception {
        // Get the admissionApplication
        restAdmissionApplicationMockMvc.perform(get("/api/admission-applications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdmissionApplication() throws Exception {
        // Initialize the database
        admissionApplicationRepository.saveAndFlush(admissionApplication);

        int databaseSizeBeforeUpdate = admissionApplicationRepository.findAll().size();

        // Update the admissionApplication
        AdmissionApplication updatedAdmissionApplication = admissionApplicationRepository.findById(admissionApplication.getId()).get();
        // Disconnect from session so that the updates on updatedAdmissionApplication are not directly saved in db
        em.detach(updatedAdmissionApplication);
        updatedAdmissionApplication
            .admissionStatus(UPDATED_ADMISSION_STATUS)
            .course(UPDATED_COURSE)
            .admissionDate(UPDATED_ADMISSION_DATE)
            .comments(UPDATED_COMMENTS);
        AdmissionApplicationDTO admissionApplicationDTO = admissionApplicationMapper.toDto(updatedAdmissionApplication);

        restAdmissionApplicationMockMvc.perform(put("/api/admission-applications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admissionApplicationDTO)))
            .andExpect(status().isOk());

        // Validate the AdmissionApplication in the database
        List<AdmissionApplication> admissionApplicationList = admissionApplicationRepository.findAll();
        assertThat(admissionApplicationList).hasSize(databaseSizeBeforeUpdate);
        AdmissionApplication testAdmissionApplication = admissionApplicationList.get(admissionApplicationList.size() - 1);
        assertThat(testAdmissionApplication.getAdmissionStatus()).isEqualTo(UPDATED_ADMISSION_STATUS);
        assertThat(testAdmissionApplication.getCourse()).isEqualTo(UPDATED_COURSE);
        assertThat(testAdmissionApplication.getAdmissionDate()).isEqualTo(UPDATED_ADMISSION_DATE);
        assertThat(testAdmissionApplication.getComments()).isEqualTo(UPDATED_COMMENTS);

        // Validate the AdmissionApplication in Elasticsearch
        verify(mockAdmissionApplicationSearchRepository, times(1)).save(testAdmissionApplication);
    }

    @Test
    @Transactional
    public void updateNonExistingAdmissionApplication() throws Exception {
        int databaseSizeBeforeUpdate = admissionApplicationRepository.findAll().size();

        // Create the AdmissionApplication
        AdmissionApplicationDTO admissionApplicationDTO = admissionApplicationMapper.toDto(admissionApplication);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmissionApplicationMockMvc.perform(put("/api/admission-applications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admissionApplicationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdmissionApplication in the database
        List<AdmissionApplication> admissionApplicationList = admissionApplicationRepository.findAll();
        assertThat(admissionApplicationList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AdmissionApplication in Elasticsearch
        verify(mockAdmissionApplicationSearchRepository, times(0)).save(admissionApplication);
    }

    @Test
    @Transactional
    public void deleteAdmissionApplication() throws Exception {
        // Initialize the database
        admissionApplicationRepository.saveAndFlush(admissionApplication);

        int databaseSizeBeforeDelete = admissionApplicationRepository.findAll().size();

        // Get the admissionApplication
        restAdmissionApplicationMockMvc.perform(delete("/api/admission-applications/{id}", admissionApplication.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AdmissionApplication> admissionApplicationList = admissionApplicationRepository.findAll();
        assertThat(admissionApplicationList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AdmissionApplication in Elasticsearch
        verify(mockAdmissionApplicationSearchRepository, times(1)).deleteById(admissionApplication.getId());
    }

    @Test
    @Transactional
    public void searchAdmissionApplication() throws Exception {
        // Initialize the database
        admissionApplicationRepository.saveAndFlush(admissionApplication);
        when(mockAdmissionApplicationSearchRepository.search(queryStringQuery("id:" + admissionApplication.getId())))
            .thenReturn(Collections.singletonList(admissionApplication));
        // Search the admissionApplication
        restAdmissionApplicationMockMvc.perform(get("/api/_search/admission-applications?query=id:" + admissionApplication.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(admissionApplication.getId().intValue())))
            .andExpect(jsonPath("$.[*].admissionStatus").value(hasItem(DEFAULT_ADMISSION_STATUS.toString())))
            .andExpect(jsonPath("$.[*].course").value(hasItem(DEFAULT_COURSE.toString())))
            .andExpect(jsonPath("$.[*].admissionDate").value(hasItem(DEFAULT_ADMISSION_DATE.toString())))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmissionApplication.class);
        AdmissionApplication admissionApplication1 = new AdmissionApplication();
        admissionApplication1.setId(1L);
        AdmissionApplication admissionApplication2 = new AdmissionApplication();
        admissionApplication2.setId(admissionApplication1.getId());
        assertThat(admissionApplication1).isEqualTo(admissionApplication2);
        admissionApplication2.setId(2L);
        assertThat(admissionApplication1).isNotEqualTo(admissionApplication2);
        admissionApplication1.setId(null);
        assertThat(admissionApplication1).isNotEqualTo(admissionApplication2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmissionApplicationDTO.class);
        AdmissionApplicationDTO admissionApplicationDTO1 = new AdmissionApplicationDTO();
        admissionApplicationDTO1.setId(1L);
        AdmissionApplicationDTO admissionApplicationDTO2 = new AdmissionApplicationDTO();
        assertThat(admissionApplicationDTO1).isNotEqualTo(admissionApplicationDTO2);
        admissionApplicationDTO2.setId(admissionApplicationDTO1.getId());
        assertThat(admissionApplicationDTO1).isEqualTo(admissionApplicationDTO2);
        admissionApplicationDTO2.setId(2L);
        assertThat(admissionApplicationDTO1).isNotEqualTo(admissionApplicationDTO2);
        admissionApplicationDTO1.setId(null);
        assertThat(admissionApplicationDTO1).isNotEqualTo(admissionApplicationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(admissionApplicationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(admissionApplicationMapper.fromId(null)).isNull();
    }
}
