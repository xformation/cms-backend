package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.AdmissionApplication;
import com.synectiks.cms.repository.AdmissionApplicationRepository;
//import com.synectiks.cms.repository.search.AdmissionApplicationSearchRepository;
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
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;


import static com.synectiks.cms.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
//import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.synectiks.cms.domain.enumeration.AdmissionStatusEnum;
import com.synectiks.cms.domain.enumeration.Gender;
import com.synectiks.cms.domain.enumeration.CourseEnum;
/**
 * Test class for the AdmissionApplicationResource REST controller.
 *
 * @see AdmissionApplicationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class AdmissionApplicationResourceIntTest {

    private static final AdmissionStatusEnum DEFAULT_ADMISSION_STATUS = AdmissionStatusEnum.INPROCESS;
    private static final AdmissionStatusEnum UPDATED_ADMISSION_STATUS = AdmissionStatusEnum.DECLINED;

    private static final String DEFAULT_STUDENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STUDENT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STUDENT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FATHER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FATHER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FATHER_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FATHER_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FATHER_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FATHER_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ALTERNATE_MOBILE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ALTERNATE_MOBILE_NUMBER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_OF_BIRTH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_OF_BIRTH = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final Gender DEFAULT_SEX = Gender.MALE;
    private static final Gender UPDATED_SEX = Gender.FEMALE;

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final Integer DEFAULT_APPLICATION_ID = 1;
    private static final Integer UPDATED_APPLICATION_ID = 2;

    private static final String DEFAULT_UPLOAD_PHOTO = "AAAAAAAAAA";
    private static final String UPDATED_UPLOAD_PHOTO = "BBBBBBBBBB";

    private static final CourseEnum DEFAULT_COURSE = CourseEnum.BTECH;
    private static final CourseEnum UPDATED_COURSE = CourseEnum.MTECH;

    private static final LocalDate DEFAULT_ADMISSION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ADMISSION_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private AdmissionApplicationRepository admissionApplicationRepository;

    @Autowired
    private AdmissionApplicationMapper admissionApplicationMapper;

    @Autowired
    private AdmissionApplicationService admissionApplicationService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

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
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
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
            .studentName(DEFAULT_STUDENT_NAME)
            .studentMiddleName(DEFAULT_STUDENT_MIDDLE_NAME)
            .studentLastName(DEFAULT_STUDENT_LAST_NAME)
            .fatherName(DEFAULT_FATHER_NAME)
            .fatherMiddleName(DEFAULT_FATHER_MIDDLE_NAME)
            .fatherLastName(DEFAULT_FATHER_LAST_NAME)
            .motherName(DEFAULT_MOTHER_NAME)
            .motherMiddleName(DEFAULT_MOTHER_MIDDLE_NAME)
            .motherLastName(DEFAULT_MOTHER_LAST_NAME)
            .contactNumber(DEFAULT_CONTACT_NUMBER)
            .alternateMobileNumber(DEFAULT_ALTERNATE_MOBILE_NUMBER)
            .dateOfBirth(DEFAULT_DATE_OF_BIRTH)
            .email(DEFAULT_EMAIL)
            .sex(DEFAULT_SEX)
            .comments(DEFAULT_COMMENTS)
            .applicationId(DEFAULT_APPLICATION_ID)
            .uploadPhoto(DEFAULT_UPLOAD_PHOTO)
            .course(DEFAULT_COURSE)
            .admissionDate(DEFAULT_ADMISSION_DATE);
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
        assertThat(testAdmissionApplication.getStudentName()).isEqualTo(DEFAULT_STUDENT_NAME);
        assertThat(testAdmissionApplication.getStudentMiddleName()).isEqualTo(DEFAULT_STUDENT_MIDDLE_NAME);
        assertThat(testAdmissionApplication.getStudentLastName()).isEqualTo(DEFAULT_STUDENT_LAST_NAME);
        assertThat(testAdmissionApplication.getFatherName()).isEqualTo(DEFAULT_FATHER_NAME);
        assertThat(testAdmissionApplication.getFatherMiddleName()).isEqualTo(DEFAULT_FATHER_MIDDLE_NAME);
        assertThat(testAdmissionApplication.getFatherLastName()).isEqualTo(DEFAULT_FATHER_LAST_NAME);
        assertThat(testAdmissionApplication.getMotherName()).isEqualTo(DEFAULT_MOTHER_NAME);
        assertThat(testAdmissionApplication.getMotherMiddleName()).isEqualTo(DEFAULT_MOTHER_MIDDLE_NAME);
        assertThat(testAdmissionApplication.getMotherLastName()).isEqualTo(DEFAULT_MOTHER_LAST_NAME);
        assertThat(testAdmissionApplication.getContactNumber()).isEqualTo(DEFAULT_CONTACT_NUMBER);
        assertThat(testAdmissionApplication.getAlternateMobileNumber()).isEqualTo(DEFAULT_ALTERNATE_MOBILE_NUMBER);
        assertThat(testAdmissionApplication.getDateOfBirth()).isEqualTo(DEFAULT_DATE_OF_BIRTH);
        assertThat(testAdmissionApplication.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testAdmissionApplication.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testAdmissionApplication.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testAdmissionApplication.getApplicationId()).isEqualTo(DEFAULT_APPLICATION_ID);
        assertThat(testAdmissionApplication.getUploadPhoto()).isEqualTo(DEFAULT_UPLOAD_PHOTO);
        assertThat(testAdmissionApplication.getCourse()).isEqualTo(DEFAULT_COURSE);
        assertThat(testAdmissionApplication.getAdmissionDate()).isEqualTo(DEFAULT_ADMISSION_DATE);
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
            .andExpect(jsonPath("$.[*].studentName").value(hasItem(DEFAULT_STUDENT_NAME.toString())))
            .andExpect(jsonPath("$.[*].studentMiddleName").value(hasItem(DEFAULT_STUDENT_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].studentLastName").value(hasItem(DEFAULT_STUDENT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].fatherName").value(hasItem(DEFAULT_FATHER_NAME.toString())))
            .andExpect(jsonPath("$.[*].fatherMiddleName").value(hasItem(DEFAULT_FATHER_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].fatherLastName").value(hasItem(DEFAULT_FATHER_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].motherName").value(hasItem(DEFAULT_MOTHER_NAME.toString())))
            .andExpect(jsonPath("$.[*].motherMiddleName").value(hasItem(DEFAULT_MOTHER_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].motherLastName").value(hasItem(DEFAULT_MOTHER_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].contactNumber").value(hasItem(DEFAULT_CONTACT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].alternateMobileNumber").value(hasItem(DEFAULT_ALTERNATE_MOBILE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.toString())))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS.toString())))
            .andExpect(jsonPath("$.[*].applicationId").value(hasItem(DEFAULT_APPLICATION_ID)))
            .andExpect(jsonPath("$.[*].uploadPhoto").value(hasItem(DEFAULT_UPLOAD_PHOTO.toString())))
            .andExpect(jsonPath("$.[*].course").value(hasItem(DEFAULT_COURSE.toString())))
            .andExpect(jsonPath("$.[*].admissionDate").value(hasItem(DEFAULT_ADMISSION_DATE.toString())));
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
            .andExpect(jsonPath("$.studentName").value(DEFAULT_STUDENT_NAME.toString()))
            .andExpect(jsonPath("$.studentMiddleName").value(DEFAULT_STUDENT_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.studentLastName").value(DEFAULT_STUDENT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.fatherName").value(DEFAULT_FATHER_NAME.toString()))
            .andExpect(jsonPath("$.fatherMiddleName").value(DEFAULT_FATHER_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.fatherLastName").value(DEFAULT_FATHER_LAST_NAME.toString()))
            .andExpect(jsonPath("$.motherName").value(DEFAULT_MOTHER_NAME.toString()))
            .andExpect(jsonPath("$.motherMiddleName").value(DEFAULT_MOTHER_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.motherLastName").value(DEFAULT_MOTHER_LAST_NAME.toString()))
            .andExpect(jsonPath("$.contactNumber").value(DEFAULT_CONTACT_NUMBER.toString()))
            .andExpect(jsonPath("$.alternateMobileNumber").value(DEFAULT_ALTERNATE_MOBILE_NUMBER.toString()))
            .andExpect(jsonPath("$.dateOfBirth").value(DEFAULT_DATE_OF_BIRTH.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX.toString()))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS.toString()))
            .andExpect(jsonPath("$.applicationId").value(DEFAULT_APPLICATION_ID))
            .andExpect(jsonPath("$.uploadPhoto").value(DEFAULT_UPLOAD_PHOTO.toString()))
            .andExpect(jsonPath("$.course").value(DEFAULT_COURSE.toString()))
            .andExpect(jsonPath("$.admissionDate").value(DEFAULT_ADMISSION_DATE.toString()));
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
            .studentName(UPDATED_STUDENT_NAME)
            .studentMiddleName(UPDATED_STUDENT_MIDDLE_NAME)
            .studentLastName(UPDATED_STUDENT_LAST_NAME)
            .fatherName(UPDATED_FATHER_NAME)
            .fatherMiddleName(UPDATED_FATHER_MIDDLE_NAME)
            .fatherLastName(UPDATED_FATHER_LAST_NAME)
            .motherName(UPDATED_MOTHER_NAME)
            .motherMiddleName(UPDATED_MOTHER_MIDDLE_NAME)
            .motherLastName(UPDATED_MOTHER_LAST_NAME)
            .contactNumber(UPDATED_CONTACT_NUMBER)
            .alternateMobileNumber(UPDATED_ALTERNATE_MOBILE_NUMBER)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .email(UPDATED_EMAIL)
            .sex(UPDATED_SEX)
            .comments(UPDATED_COMMENTS)
            .applicationId(UPDATED_APPLICATION_ID)
            .uploadPhoto(UPDATED_UPLOAD_PHOTO)
            .course(UPDATED_COURSE)
            .admissionDate(UPDATED_ADMISSION_DATE);
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
        assertThat(testAdmissionApplication.getStudentName()).isEqualTo(UPDATED_STUDENT_NAME);
        assertThat(testAdmissionApplication.getStudentMiddleName()).isEqualTo(UPDATED_STUDENT_MIDDLE_NAME);
        assertThat(testAdmissionApplication.getStudentLastName()).isEqualTo(UPDATED_STUDENT_LAST_NAME);
        assertThat(testAdmissionApplication.getFatherName()).isEqualTo(UPDATED_FATHER_NAME);
        assertThat(testAdmissionApplication.getFatherMiddleName()).isEqualTo(UPDATED_FATHER_MIDDLE_NAME);
        assertThat(testAdmissionApplication.getFatherLastName()).isEqualTo(UPDATED_FATHER_LAST_NAME);
        assertThat(testAdmissionApplication.getMotherName()).isEqualTo(UPDATED_MOTHER_NAME);
        assertThat(testAdmissionApplication.getMotherMiddleName()).isEqualTo(UPDATED_MOTHER_MIDDLE_NAME);
        assertThat(testAdmissionApplication.getMotherLastName()).isEqualTo(UPDATED_MOTHER_LAST_NAME);
        assertThat(testAdmissionApplication.getContactNumber()).isEqualTo(UPDATED_CONTACT_NUMBER);
        assertThat(testAdmissionApplication.getAlternateMobileNumber()).isEqualTo(UPDATED_ALTERNATE_MOBILE_NUMBER);
        assertThat(testAdmissionApplication.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
        assertThat(testAdmissionApplication.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testAdmissionApplication.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testAdmissionApplication.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testAdmissionApplication.getApplicationId()).isEqualTo(UPDATED_APPLICATION_ID);
        assertThat(testAdmissionApplication.getUploadPhoto()).isEqualTo(UPDATED_UPLOAD_PHOTO);
        assertThat(testAdmissionApplication.getCourse()).isEqualTo(UPDATED_COURSE);
        assertThat(testAdmissionApplication.getAdmissionDate()).isEqualTo(UPDATED_ADMISSION_DATE);
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
    }

    @Test
    @Transactional
    public void deleteAdmissionApplication() throws Exception {
        // Initialize the database
        admissionApplicationRepository.saveAndFlush(admissionApplication);

        int databaseSizeBeforeDelete = admissionApplicationRepository.findAll().size();

        // Delete the admissionApplication
        restAdmissionApplicationMockMvc.perform(delete("/api/admission-applications/{id}", admissionApplication.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AdmissionApplication> admissionApplicationList = admissionApplicationRepository.findAll();
        assertThat(admissionApplicationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchAdmissionApplication() throws Exception {
        // Initialize the database
        admissionApplicationRepository.saveAndFlush(admissionApplication);
        // Search the admissionApplication
        restAdmissionApplicationMockMvc.perform(get("/api/_search/admission-applications?query=id:" + admissionApplication.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(admissionApplication.getId().intValue())))
            .andExpect(jsonPath("$.[*].admissionStatus").value(hasItem(DEFAULT_ADMISSION_STATUS.toString())))
            .andExpect(jsonPath("$.[*].studentName").value(hasItem(DEFAULT_STUDENT_NAME)))
            .andExpect(jsonPath("$.[*].studentMiddleName").value(hasItem(DEFAULT_STUDENT_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].studentLastName").value(hasItem(DEFAULT_STUDENT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].fatherName").value(hasItem(DEFAULT_FATHER_NAME)))
            .andExpect(jsonPath("$.[*].fatherMiddleName").value(hasItem(DEFAULT_FATHER_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].fatherLastName").value(hasItem(DEFAULT_FATHER_LAST_NAME)))
            .andExpect(jsonPath("$.[*].motherName").value(hasItem(DEFAULT_MOTHER_NAME)))
            .andExpect(jsonPath("$.[*].motherMiddleName").value(hasItem(DEFAULT_MOTHER_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].motherLastName").value(hasItem(DEFAULT_MOTHER_LAST_NAME)))
            .andExpect(jsonPath("$.[*].contactNumber").value(hasItem(DEFAULT_CONTACT_NUMBER)))
            .andExpect(jsonPath("$.[*].alternateMobileNumber").value(hasItem(DEFAULT_ALTERNATE_MOBILE_NUMBER)))
            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.toString())))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS)))
            .andExpect(jsonPath("$.[*].applicationId").value(hasItem(DEFAULT_APPLICATION_ID)))
            .andExpect(jsonPath("$.[*].uploadPhoto").value(hasItem(DEFAULT_UPLOAD_PHOTO)))
            .andExpect(jsonPath("$.[*].course").value(hasItem(DEFAULT_COURSE.toString())))
            .andExpect(jsonPath("$.[*].admissionDate").value(hasItem(DEFAULT_ADMISSION_DATE.toString())));
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
