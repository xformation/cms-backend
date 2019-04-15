package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.AdmissionEnquiry;
import com.synectiks.cms.repository.AdmissionEnquiryRepository;
import com.synectiks.cms.repository.search.AdmissionEnquirySearchRepository;
import com.synectiks.cms.service.AdmissionEnquiryService;
import com.synectiks.cms.service.dto.AdmissionEnquiryDTO;
import com.synectiks.cms.service.mapper.AdmissionEnquiryMapper;
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

import com.synectiks.cms.domain.enumeration.CourseEnum;
import com.synectiks.cms.domain.enumeration.ModeOfEnquiry;
import com.synectiks.cms.domain.enumeration.EnquiryStatus;
/**
 * Test class for the AdmissionEnquiryResource REST controller.
 *
 * @see AdmissionEnquiryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class AdmissionEnquiryResourceIntTest {

    private static final String DEFAULT_STUDENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ALTERNATE_MOBILE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ALTERNATE_MOBILE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final CourseEnum DEFAULT_COURSE_APPLYING_FOR = CourseEnum.BTECH;
    private static final CourseEnum UPDATED_COURSE_APPLYING_FOR = CourseEnum.MTECH;

    private static final ModeOfEnquiry DEFAULT_MODE_OF_ENQUIRY = ModeOfEnquiry.INPERSON;
    private static final ModeOfEnquiry UPDATED_MODE_OF_ENQUIRY = ModeOfEnquiry.TELEPHONE;

    private static final EnquiryStatus DEFAULT_STATUS = EnquiryStatus.RECEIVED;
    private static final EnquiryStatus UPDATED_STATUS = EnquiryStatus.FOLLOWUP;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Date DEFAULT_ENQUIRY_DATE = new Date();
    private static final Date UPDATED_ENQUIRY_DATE = new Date();

    private static final Date DEFAULT_UPDATED_ON = new Date();
    private static final Date UPDATED_UPDATED_ON = new Date();
    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    @Autowired
    private AdmissionEnquiryRepository admissionEnquiryRepository;


    @Autowired
    private AdmissionEnquiryMapper admissionEnquiryMapper;
    

    @Autowired
    private AdmissionEnquiryService admissionEnquiryService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.AdmissionEnquirySearchRepositoryMockConfiguration
     */
    @Autowired
    private AdmissionEnquirySearchRepository mockAdmissionEnquirySearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAdmissionEnquiryMockMvc;

    private AdmissionEnquiry admissionEnquiry;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdmissionEnquiryResource admissionEnquiryResource = new AdmissionEnquiryResource(admissionEnquiryService);
        this.restAdmissionEnquiryMockMvc = MockMvcBuilders.standaloneSetup(admissionEnquiryResource)
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
    public static AdmissionEnquiry createEntity(EntityManager em) {
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry()
            .studentName(DEFAULT_STUDENT_NAME)
            .mobileNumber(DEFAULT_MOBILE_NUMBER)
            .alternateMobileNumber(DEFAULT_ALTERNATE_MOBILE_NUMBER)
            .email(DEFAULT_EMAIL)
            .courseApplyingFor(DEFAULT_COURSE_APPLYING_FOR)
            .modeOfEnquiry(DEFAULT_MODE_OF_ENQUIRY)
            .status(DEFAULT_STATUS)
            .description(DEFAULT_DESCRIPTION)
            .enquiryDate(DEFAULT_ENQUIRY_DATE)
            .updatedOn(DEFAULT_UPDATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY);
        return admissionEnquiry;
    }

    @Before
    public void initTest() {
        admissionEnquiry = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdmissionEnquiry() throws Exception {
        int databaseSizeBeforeCreate = admissionEnquiryRepository.findAll().size();

        // Create the AdmissionEnquiry
        AdmissionEnquiryDTO admissionEnquiryDTO = admissionEnquiryMapper.toDto(admissionEnquiry);
        restAdmissionEnquiryMockMvc.perform(post("/api/admission-enquiries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admissionEnquiryDTO)))
            .andExpect(status().isCreated());

        // Validate the AdmissionEnquiry in the database
        List<AdmissionEnquiry> admissionEnquiryList = admissionEnquiryRepository.findAll();
        assertThat(admissionEnquiryList).hasSize(databaseSizeBeforeCreate + 1);
        AdmissionEnquiry testAdmissionEnquiry = admissionEnquiryList.get(admissionEnquiryList.size() - 1);
        assertThat(testAdmissionEnquiry.getStudentName()).isEqualTo(DEFAULT_STUDENT_NAME);
        assertThat(testAdmissionEnquiry.getMobileNumber()).isEqualTo(DEFAULT_MOBILE_NUMBER);
        assertThat(testAdmissionEnquiry.getAlternateMobileNumber()).isEqualTo(DEFAULT_ALTERNATE_MOBILE_NUMBER);
        assertThat(testAdmissionEnquiry.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testAdmissionEnquiry.getCourseApplyingFor()).isEqualTo(DEFAULT_COURSE_APPLYING_FOR);
        assertThat(testAdmissionEnquiry.getModeOfEnquiry()).isEqualTo(DEFAULT_MODE_OF_ENQUIRY);
        assertThat(testAdmissionEnquiry.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testAdmissionEnquiry.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAdmissionEnquiry.getEnquiryDate()).isEqualTo(DEFAULT_ENQUIRY_DATE);
        assertThat(testAdmissionEnquiry.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
        assertThat(testAdmissionEnquiry.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);

        // Validate the AdmissionEnquiry in Elasticsearch
        verify(mockAdmissionEnquirySearchRepository, times(1)).save(testAdmissionEnquiry);
    }

    @Test
    @Transactional
    public void createAdmissionEnquiryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = admissionEnquiryRepository.findAll().size();

        // Create the AdmissionEnquiry with an existing ID
        admissionEnquiry.setId(1L);
        AdmissionEnquiryDTO admissionEnquiryDTO = admissionEnquiryMapper.toDto(admissionEnquiry);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmissionEnquiryMockMvc.perform(post("/api/admission-enquiries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admissionEnquiryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdmissionEnquiry in the database
        List<AdmissionEnquiry> admissionEnquiryList = admissionEnquiryRepository.findAll();
        assertThat(admissionEnquiryList).hasSize(databaseSizeBeforeCreate);

        // Validate the AdmissionEnquiry in Elasticsearch
        verify(mockAdmissionEnquirySearchRepository, times(0)).save(admissionEnquiry);
    }

    @Test
    @Transactional
    public void checkStudentNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = admissionEnquiryRepository.findAll().size();
        // set the field null
        admissionEnquiry.setStudentName(null);

        // Create the AdmissionEnquiry, which fails.
        AdmissionEnquiryDTO admissionEnquiryDTO = admissionEnquiryMapper.toDto(admissionEnquiry);

        restAdmissionEnquiryMockMvc.perform(post("/api/admission-enquiries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admissionEnquiryDTO)))
            .andExpect(status().isBadRequest());

        List<AdmissionEnquiry> admissionEnquiryList = admissionEnquiryRepository.findAll();
        assertThat(admissionEnquiryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMobileNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = admissionEnquiryRepository.findAll().size();
        // set the field null
        admissionEnquiry.setMobileNumber(null);

        // Create the AdmissionEnquiry, which fails.
        AdmissionEnquiryDTO admissionEnquiryDTO = admissionEnquiryMapper.toDto(admissionEnquiry);

        restAdmissionEnquiryMockMvc.perform(post("/api/admission-enquiries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admissionEnquiryDTO)))
            .andExpect(status().isBadRequest());

        List<AdmissionEnquiry> admissionEnquiryList = admissionEnquiryRepository.findAll();
        assertThat(admissionEnquiryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCourseApplyingForIsRequired() throws Exception {
        int databaseSizeBeforeTest = admissionEnquiryRepository.findAll().size();
        // set the field null
        admissionEnquiry.setCourseApplyingFor(null);

        // Create the AdmissionEnquiry, which fails.
        AdmissionEnquiryDTO admissionEnquiryDTO = admissionEnquiryMapper.toDto(admissionEnquiry);

        restAdmissionEnquiryMockMvc.perform(post("/api/admission-enquiries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admissionEnquiryDTO)))
            .andExpect(status().isBadRequest());

        List<AdmissionEnquiry> admissionEnquiryList = admissionEnquiryRepository.findAll();
        assertThat(admissionEnquiryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkModeOfEnquiryIsRequired() throws Exception {
        int databaseSizeBeforeTest = admissionEnquiryRepository.findAll().size();
        // set the field null
        admissionEnquiry.setModeOfEnquiry(null);

        // Create the AdmissionEnquiry, which fails.
        AdmissionEnquiryDTO admissionEnquiryDTO = admissionEnquiryMapper.toDto(admissionEnquiry);

        restAdmissionEnquiryMockMvc.perform(post("/api/admission-enquiries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admissionEnquiryDTO)))
            .andExpect(status().isBadRequest());

        List<AdmissionEnquiry> admissionEnquiryList = admissionEnquiryRepository.findAll();
        assertThat(admissionEnquiryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = admissionEnquiryRepository.findAll().size();
        // set the field null
        admissionEnquiry.setStatus(null);

        // Create the AdmissionEnquiry, which fails.
        AdmissionEnquiryDTO admissionEnquiryDTO = admissionEnquiryMapper.toDto(admissionEnquiry);

        restAdmissionEnquiryMockMvc.perform(post("/api/admission-enquiries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admissionEnquiryDTO)))
            .andExpect(status().isBadRequest());

        List<AdmissionEnquiry> admissionEnquiryList = admissionEnquiryRepository.findAll();
        assertThat(admissionEnquiryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = admissionEnquiryRepository.findAll().size();
        // set the field null
        admissionEnquiry.setDescription(null);

        // Create the AdmissionEnquiry, which fails.
        AdmissionEnquiryDTO admissionEnquiryDTO = admissionEnquiryMapper.toDto(admissionEnquiry);

        restAdmissionEnquiryMockMvc.perform(post("/api/admission-enquiries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admissionEnquiryDTO)))
            .andExpect(status().isBadRequest());

        List<AdmissionEnquiry> admissionEnquiryList = admissionEnquiryRepository.findAll();
        assertThat(admissionEnquiryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnquiryDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = admissionEnquiryRepository.findAll().size();
        // set the field null
        admissionEnquiry.setEnquiryDate(null);

        // Create the AdmissionEnquiry, which fails.
        AdmissionEnquiryDTO admissionEnquiryDTO = admissionEnquiryMapper.toDto(admissionEnquiry);

        restAdmissionEnquiryMockMvc.perform(post("/api/admission-enquiries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admissionEnquiryDTO)))
            .andExpect(status().isBadRequest());

        List<AdmissionEnquiry> admissionEnquiryList = admissionEnquiryRepository.findAll();
        assertThat(admissionEnquiryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAdmissionEnquiries() throws Exception {
        // Initialize the database
        admissionEnquiryRepository.saveAndFlush(admissionEnquiry);

        // Get all the admissionEnquiryList
        restAdmissionEnquiryMockMvc.perform(get("/api/admission-enquiries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(admissionEnquiry.getId().intValue())))
            .andExpect(jsonPath("$.[*].studentName").value(hasItem(DEFAULT_STUDENT_NAME.toString())))
            .andExpect(jsonPath("$.[*].mobileNumber").value(hasItem(DEFAULT_MOBILE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].alternateMobileNumber").value(hasItem(DEFAULT_ALTERNATE_MOBILE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].courseApplyingFor").value(hasItem(DEFAULT_COURSE_APPLYING_FOR.toString())))
            .andExpect(jsonPath("$.[*].modeOfEnquiry").value(hasItem(DEFAULT_MODE_OF_ENQUIRY.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].enquiryDate").value(hasItem(DEFAULT_ENQUIRY_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())));
    }
    

    @Test
    @Transactional
    public void getAdmissionEnquiry() throws Exception {
        // Initialize the database
        admissionEnquiryRepository.saveAndFlush(admissionEnquiry);

        // Get the admissionEnquiry
        restAdmissionEnquiryMockMvc.perform(get("/api/admission-enquiries/{id}", admissionEnquiry.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(admissionEnquiry.getId().intValue()))
            .andExpect(jsonPath("$.studentName").value(DEFAULT_STUDENT_NAME.toString()))
            .andExpect(jsonPath("$.mobileNumber").value(DEFAULT_MOBILE_NUMBER.toString()))
            .andExpect(jsonPath("$.alternateMobileNumber").value(DEFAULT_ALTERNATE_MOBILE_NUMBER.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.courseApplyingFor").value(DEFAULT_COURSE_APPLYING_FOR.toString()))
            .andExpect(jsonPath("$.modeOfEnquiry").value(DEFAULT_MODE_OF_ENQUIRY.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.enquiryDate").value(DEFAULT_ENQUIRY_DATE.toString()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingAdmissionEnquiry() throws Exception {
        // Get the admissionEnquiry
        restAdmissionEnquiryMockMvc.perform(get("/api/admission-enquiries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdmissionEnquiry() throws Exception {
        // Initialize the database
        admissionEnquiryRepository.saveAndFlush(admissionEnquiry);

        int databaseSizeBeforeUpdate = admissionEnquiryRepository.findAll().size();

        // Update the admissionEnquiry
        AdmissionEnquiry updatedAdmissionEnquiry = admissionEnquiryRepository.findById(admissionEnquiry.getId()).get();
        // Disconnect from session so that the updates on updatedAdmissionEnquiry are not directly saved in db
        em.detach(updatedAdmissionEnquiry);
        updatedAdmissionEnquiry
            .studentName(UPDATED_STUDENT_NAME)
            .mobileNumber(UPDATED_MOBILE_NUMBER)
            .alternateMobileNumber(UPDATED_ALTERNATE_MOBILE_NUMBER)
            .email(UPDATED_EMAIL)
            .courseApplyingFor(UPDATED_COURSE_APPLYING_FOR)
            .modeOfEnquiry(UPDATED_MODE_OF_ENQUIRY)
            .status(UPDATED_STATUS)
            .description(UPDATED_DESCRIPTION)
            .enquiryDate(UPDATED_ENQUIRY_DATE)
            .updatedOn(UPDATED_UPDATED_ON)
            .updatedBy(UPDATED_UPDATED_BY);
        AdmissionEnquiryDTO admissionEnquiryDTO = admissionEnquiryMapper.toDto(updatedAdmissionEnquiry);

        restAdmissionEnquiryMockMvc.perform(put("/api/admission-enquiries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admissionEnquiryDTO)))
            .andExpect(status().isOk());

        // Validate the AdmissionEnquiry in the database
        List<AdmissionEnquiry> admissionEnquiryList = admissionEnquiryRepository.findAll();
        assertThat(admissionEnquiryList).hasSize(databaseSizeBeforeUpdate);
        AdmissionEnquiry testAdmissionEnquiry = admissionEnquiryList.get(admissionEnquiryList.size() - 1);
        assertThat(testAdmissionEnquiry.getStudentName()).isEqualTo(UPDATED_STUDENT_NAME);
        assertThat(testAdmissionEnquiry.getMobileNumber()).isEqualTo(UPDATED_MOBILE_NUMBER);
        assertThat(testAdmissionEnquiry.getAlternateMobileNumber()).isEqualTo(UPDATED_ALTERNATE_MOBILE_NUMBER);
        assertThat(testAdmissionEnquiry.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testAdmissionEnquiry.getCourseApplyingFor()).isEqualTo(UPDATED_COURSE_APPLYING_FOR);
        assertThat(testAdmissionEnquiry.getModeOfEnquiry()).isEqualTo(UPDATED_MODE_OF_ENQUIRY);
        assertThat(testAdmissionEnquiry.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAdmissionEnquiry.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAdmissionEnquiry.getEnquiryDate()).isEqualTo(UPDATED_ENQUIRY_DATE);
        assertThat(testAdmissionEnquiry.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
        assertThat(testAdmissionEnquiry.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);

        // Validate the AdmissionEnquiry in Elasticsearch
        verify(mockAdmissionEnquirySearchRepository, times(1)).save(testAdmissionEnquiry);
    }

    @Test
    @Transactional
    public void updateNonExistingAdmissionEnquiry() throws Exception {
        int databaseSizeBeforeUpdate = admissionEnquiryRepository.findAll().size();

        // Create the AdmissionEnquiry
        AdmissionEnquiryDTO admissionEnquiryDTO = admissionEnquiryMapper.toDto(admissionEnquiry);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAdmissionEnquiryMockMvc.perform(put("/api/admission-enquiries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admissionEnquiryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdmissionEnquiry in the database
        List<AdmissionEnquiry> admissionEnquiryList = admissionEnquiryRepository.findAll();
        assertThat(admissionEnquiryList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AdmissionEnquiry in Elasticsearch
        verify(mockAdmissionEnquirySearchRepository, times(0)).save(admissionEnquiry);
    }

    @Test
    @Transactional
    public void deleteAdmissionEnquiry() throws Exception {
        // Initialize the database
        admissionEnquiryRepository.saveAndFlush(admissionEnquiry);

        int databaseSizeBeforeDelete = admissionEnquiryRepository.findAll().size();

        // Get the admissionEnquiry
        restAdmissionEnquiryMockMvc.perform(delete("/api/admission-enquiries/{id}", admissionEnquiry.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AdmissionEnquiry> admissionEnquiryList = admissionEnquiryRepository.findAll();
        assertThat(admissionEnquiryList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AdmissionEnquiry in Elasticsearch
        verify(mockAdmissionEnquirySearchRepository, times(1)).deleteById(admissionEnquiry.getId());
    }

    @Test
    @Transactional
    public void searchAdmissionEnquiry() throws Exception {
        // Initialize the database
        admissionEnquiryRepository.saveAndFlush(admissionEnquiry);
        when(mockAdmissionEnquirySearchRepository.search(queryStringQuery("id:" + admissionEnquiry.getId())))
            .thenReturn(Collections.singletonList(admissionEnquiry));
        // Search the admissionEnquiry
        restAdmissionEnquiryMockMvc.perform(get("/api/_search/admission-enquiries?query=id:" + admissionEnquiry.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(admissionEnquiry.getId().intValue())))
            .andExpect(jsonPath("$.[*].studentName").value(hasItem(DEFAULT_STUDENT_NAME.toString())))
            .andExpect(jsonPath("$.[*].mobileNumber").value(hasItem(DEFAULT_MOBILE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].alternateMobileNumber").value(hasItem(DEFAULT_ALTERNATE_MOBILE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].courseApplyingFor").value(hasItem(DEFAULT_COURSE_APPLYING_FOR.toString())))
            .andExpect(jsonPath("$.[*].modeOfEnquiry").value(hasItem(DEFAULT_MODE_OF_ENQUIRY.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].enquiryDate").value(hasItem(DEFAULT_ENQUIRY_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmissionEnquiry.class);
        AdmissionEnquiry admissionEnquiry1 = new AdmissionEnquiry();
        admissionEnquiry1.setId(1L);
        AdmissionEnquiry admissionEnquiry2 = new AdmissionEnquiry();
        admissionEnquiry2.setId(admissionEnquiry1.getId());
        assertThat(admissionEnquiry1).isEqualTo(admissionEnquiry2);
        admissionEnquiry2.setId(2L);
        assertThat(admissionEnquiry1).isNotEqualTo(admissionEnquiry2);
        admissionEnquiry1.setId(null);
        assertThat(admissionEnquiry1).isNotEqualTo(admissionEnquiry2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmissionEnquiryDTO.class);
        AdmissionEnquiryDTO admissionEnquiryDTO1 = new AdmissionEnquiryDTO();
        admissionEnquiryDTO1.setId(1L);
        AdmissionEnquiryDTO admissionEnquiryDTO2 = new AdmissionEnquiryDTO();
        assertThat(admissionEnquiryDTO1).isNotEqualTo(admissionEnquiryDTO2);
        admissionEnquiryDTO2.setId(admissionEnquiryDTO1.getId());
        assertThat(admissionEnquiryDTO1).isEqualTo(admissionEnquiryDTO2);
        admissionEnquiryDTO2.setId(2L);
        assertThat(admissionEnquiryDTO1).isNotEqualTo(admissionEnquiryDTO2);
        admissionEnquiryDTO1.setId(null);
        assertThat(admissionEnquiryDTO1).isNotEqualTo(admissionEnquiryDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(admissionEnquiryMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(admissionEnquiryMapper.fromId(null)).isNull();
    }
}
