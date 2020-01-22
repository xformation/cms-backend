package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.Teacher;
import com.synectiks.cms.repository.TeacherRepository;
import com.synectiks.cms.repository.search.TeacherSearchRepository;
import com.synectiks.cms.service.TeacherService;
import com.synectiks.cms.service.dto.TeacherDTO;
import com.synectiks.cms.service.mapper.TeacherMapper;
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
import java.util.Date;
import java.util.List;


import static com.synectiks.cms.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.synectiks.cms.domain.enumeration.Religion;
import com.synectiks.cms.domain.enumeration.Caste;
import com.synectiks.cms.domain.enumeration.Gender;
import com.synectiks.cms.domain.enumeration.Bloodgroup;
import com.synectiks.cms.domain.enumeration.RelationWithStudentEnum;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.StaffType;
/**
 * Test class for the TeacherResource REST controller.
 *
 * @see TeacherResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class TeacherResourceIntTest {

    private static final String DEFAULT_TEACHER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TEACHER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TEACHER_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TEACHER_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TEACHER_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TEACHER_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FATHER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FATHER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FATHER_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FATHER_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FATHER_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FATHER_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SPOUSE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SPOUSE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SPOUSE_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SPOUSE_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SPOUSE_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SPOUSE_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_LAST_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_AADHAR_NO = 1L;
    private static final Long UPDATED_AADHAR_NO = 2L;

    private static final LocalDate DEFAULT_DATE_OF_BIRTH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_OF_BIRTH = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PLACE_OF_BIRTH = "AAAAAAAAAA";
    private static final String UPDATED_PLACE_OF_BIRTH = "BBBBBBBBBB";

    private static final Religion DEFAULT_RELIGION = Religion.HINDU;
    private static final Religion UPDATED_RELIGION = Religion.MUSLIM;

    private static final Caste DEFAULT_CASTE = Caste.OBC;
    private static final Caste UPDATED_CASTE = Caste.OBC;

    private static final String DEFAULT_SUB_CASTE = "AAAAAAAAAA";
    private static final String UPDATED_SUB_CASTE = "BBBBBBBBBB";

    private static final Integer DEFAULT_AGE = 1;
    private static final Integer UPDATED_AGE = 2;

    private static final Gender DEFAULT_SEX = Gender.MALE;
    private static final Gender UPDATED_SEX = Gender.FEMALE;

    private static final Bloodgroup DEFAULT_BLOOD_GROUP = Bloodgroup.ABPOSITIVE;
    private static final Bloodgroup UPDATED_BLOOD_GROUP = Bloodgroup.ABNEGATIVE;

    private static final String DEFAULT_ADDRESS_LINE_ONE = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_LINE_ONE = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_LINE_TWO = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_LINE_TWO = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_LINE_THREE = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_LINE_THREE = "BBBBBBBBBB";

    private static final String DEFAULT_TOWN = "AAAAAAAAAA";
    private static final String UPDATED_TOWN = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final Long DEFAULT_PINCODE = 1L;
    private static final Long UPDATED_PINCODE = 2L;

    private static final String DEFAULT_TEACHER_CONTACT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_TEACHER_CONTACT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ALTERNATE_CONTACT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ALTERNATE_CONTACT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_TEACHER_EMAIL_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_TEACHER_EMAIL_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_ALTERNATE_EMAIL_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ALTERNATE_EMAIL_ADDRESS = "BBBBBBBBBB";

    private static final RelationWithStudentEnum DEFAULT_RELATION_WITH_STAFF = RelationWithStudentEnum.FATHER;
    private static final RelationWithStudentEnum UPDATED_RELATION_WITH_STAFF = RelationWithStudentEnum.MOTHER;

    private static final String DEFAULT_EMERGENCY_CONTACT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMERGENCY_CONTACT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMERGENCY_CONTACT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMERGENCY_CONTACT_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMERGENCY_CONTACT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMERGENCY_CONTACT_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMERGENCY_CONTACT_NO = "AAAAAAAAAA";
    private static final String UPDATED_EMERGENCY_CONTACT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_EMERGENCY_CONTACT_EMAIL_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_EMERGENCY_CONTACT_EMAIL_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_UPLOAD_PHOTO = "AAAAAAAAAA";
    private static final String UPDATED_UPLOAD_PHOTO = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.DEACTIVE;

    private static final Long DEFAULT_EMPLOYEE_ID = 1L;
    private static final Long UPDATED_EMPLOYEE_ID = 2L;

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    private static final StaffType DEFAULT_STAFF_TYPE = StaffType.TEACHING;
    private static final StaffType UPDATED_STAFF_TYPE = StaffType.NONTEACHING;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private TeacherService teacherService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.TeacherSearchRepositoryMockConfiguration
     */
    @Autowired
    private TeacherSearchRepository mockTeacherSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;


    private MockMvc restTeacherMockMvc;

    private Teacher teacher;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TeacherResource teacherResource = new TeacherResource(teacherService);
        this.restTeacherMockMvc = MockMvcBuilders.standaloneSetup(teacherResource)
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
    public static Teacher createEntity(EntityManager em) {
        Teacher teacher = new Teacher()
            .teacherName(DEFAULT_TEACHER_NAME)
            .teacherMiddleName(DEFAULT_TEACHER_MIDDLE_NAME)
            .teacherLastName(DEFAULT_TEACHER_LAST_NAME)
            .fatherName(DEFAULT_FATHER_NAME)
            .fatherMiddleName(DEFAULT_FATHER_MIDDLE_NAME)
            .fatherLastName(DEFAULT_FATHER_LAST_NAME)
            .spouseName(DEFAULT_SPOUSE_NAME)
            .spouseMiddleName(DEFAULT_SPOUSE_MIDDLE_NAME)
            .spouseLastName(DEFAULT_SPOUSE_LAST_NAME)
            .motherName(DEFAULT_MOTHER_NAME)
            .motherMiddleName(DEFAULT_MOTHER_MIDDLE_NAME)
            .motherLastName(DEFAULT_MOTHER_LAST_NAME)
            .aadharNo(String.valueOf(DEFAULT_AADHAR_NO))
            .dateOfBirth(DEFAULT_DATE_OF_BIRTH)
            .placeOfBirth(DEFAULT_PLACE_OF_BIRTH)
            .religion(DEFAULT_RELIGION.toString())
            .caste(DEFAULT_CASTE.toString())
            .subCaste(DEFAULT_SUB_CASTE)
            .age(DEFAULT_AGE)
            .sex(DEFAULT_SEX.toString())
            .bloodGroup(DEFAULT_BLOOD_GROUP.toString())
            .address(DEFAULT_ADDRESS_LINE_ONE)
//            .addressLineTwo(DEFAULT_ADDRESS_LINE_TWO)
//            .addressLineThree(DEFAULT_ADDRESS_LINE_THREE)
            .town(DEFAULT_TOWN)
            .state(DEFAULT_STATE)
            .country(DEFAULT_COUNTRY)
            .pinCode(String.valueOf(DEFAULT_PINCODE))
            .teacherContactNumber(DEFAULT_TEACHER_CONTACT_NUMBER)
            .alternateContactNumber(DEFAULT_ALTERNATE_CONTACT_NUMBER)
            .teacherEmailAddress(DEFAULT_TEACHER_EMAIL_ADDRESS)
            .alternateEmailAddress(DEFAULT_ALTERNATE_EMAIL_ADDRESS)
            .relationWithStaff(DEFAULT_RELATION_WITH_STAFF.toString())
            .emergencyContactName(DEFAULT_EMERGENCY_CONTACT_NAME)
            .emergencyContactMiddleName(DEFAULT_EMERGENCY_CONTACT_MIDDLE_NAME)
            .emergencyContactLastName(DEFAULT_EMERGENCY_CONTACT_LAST_NAME)
            .emergencyContactNo(DEFAULT_EMERGENCY_CONTACT_NO)
            .emergencyContactEmailAddress(DEFAULT_EMERGENCY_CONTACT_EMAIL_ADDRESS)
            .uploadPhoto(DEFAULT_UPLOAD_PHOTO)
            .status(DEFAULT_STATUS.toString())
            .employeeId(DEFAULT_EMPLOYEE_ID)
            .designation(DEFAULT_DESIGNATION)
            .staffType(DEFAULT_STAFF_TYPE.toString());
        return teacher;
    }

    @Before
    public void initTest() {
        teacher = createEntity(em);
    }

    @Test
    @Transactional
    public void createTeacher() throws Exception {
        int databaseSizeBeforeCreate = teacherRepository.findAll().size();

        // Create the Teacher
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);
        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isCreated());

        // Validate the Teacher in the database
        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeCreate + 1);
        Teacher testTeacher = teacherList.get(teacherList.size() - 1);
        assertThat(testTeacher.getTeacherName()).isEqualTo(DEFAULT_TEACHER_NAME);
        assertThat(testTeacher.getTeacherMiddleName()).isEqualTo(DEFAULT_TEACHER_MIDDLE_NAME);
        assertThat(testTeacher.getTeacherLastName()).isEqualTo(DEFAULT_TEACHER_LAST_NAME);
        assertThat(testTeacher.getFatherName()).isEqualTo(DEFAULT_FATHER_NAME);
        assertThat(testTeacher.getFatherMiddleName()).isEqualTo(DEFAULT_FATHER_MIDDLE_NAME);
        assertThat(testTeacher.getFatherLastName()).isEqualTo(DEFAULT_FATHER_LAST_NAME);
        assertThat(testTeacher.getSpouseName()).isEqualTo(DEFAULT_SPOUSE_NAME);
        assertThat(testTeacher.getSpouseMiddleName()).isEqualTo(DEFAULT_SPOUSE_MIDDLE_NAME);
        assertThat(testTeacher.getSpouseLastName()).isEqualTo(DEFAULT_SPOUSE_LAST_NAME);
        assertThat(testTeacher.getMotherName()).isEqualTo(DEFAULT_MOTHER_NAME);
        assertThat(testTeacher.getMotherMiddleName()).isEqualTo(DEFAULT_MOTHER_MIDDLE_NAME);
        assertThat(testTeacher.getMotherLastName()).isEqualTo(DEFAULT_MOTHER_LAST_NAME);
        assertThat(testTeacher.getAadharNo()).isEqualTo(DEFAULT_AADHAR_NO);
        assertThat(testTeacher.getDateOfBirth()).isEqualTo(DEFAULT_DATE_OF_BIRTH);
        assertThat(testTeacher.getPlaceOfBirth()).isEqualTo(DEFAULT_PLACE_OF_BIRTH);
        assertThat(testTeacher.getReligion()).isEqualTo(DEFAULT_RELIGION);
        assertThat(testTeacher.getCaste()).isEqualTo(DEFAULT_CASTE);
        assertThat(testTeacher.getSubCaste()).isEqualTo(DEFAULT_SUB_CASTE);
        assertThat(testTeacher.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testTeacher.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testTeacher.getBloodGroup()).isEqualTo(DEFAULT_BLOOD_GROUP);
        assertThat(testTeacher.getAddress()).isEqualTo(DEFAULT_ADDRESS_LINE_ONE);
//        assertThat(testTeacher.getAddressLineTwo()).isEqualTo(DEFAULT_ADDRESS_LINE_TWO);
//        assertThat(testTeacher.getAddressLineThree()).isEqualTo(DEFAULT_ADDRESS_LINE_THREE);
        assertThat(testTeacher.getTown()).isEqualTo(DEFAULT_TOWN);
        assertThat(testTeacher.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testTeacher.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testTeacher.getPinCode()).isEqualTo(DEFAULT_PINCODE);
        assertThat(testTeacher.getTeacherContactNumber()).isEqualTo(DEFAULT_TEACHER_CONTACT_NUMBER);
        assertThat(testTeacher.getAlternateContactNumber()).isEqualTo(DEFAULT_ALTERNATE_CONTACT_NUMBER);
        assertThat(testTeacher.getTeacherEmailAddress()).isEqualTo(DEFAULT_TEACHER_EMAIL_ADDRESS);
        assertThat(testTeacher.getAlternateEmailAddress()).isEqualTo(DEFAULT_ALTERNATE_EMAIL_ADDRESS);
        assertThat(testTeacher.getRelationWithStaff()).isEqualTo(DEFAULT_RELATION_WITH_STAFF);
        assertThat(testTeacher.getEmergencyContactName()).isEqualTo(DEFAULT_EMERGENCY_CONTACT_NAME);
        assertThat(testTeacher.getEmergencyContactMiddleName()).isEqualTo(DEFAULT_EMERGENCY_CONTACT_MIDDLE_NAME);
        assertThat(testTeacher.getEmergencyContactLastName()).isEqualTo(DEFAULT_EMERGENCY_CONTACT_LAST_NAME);
        assertThat(testTeacher.getEmergencyContactNo()).isEqualTo(DEFAULT_EMERGENCY_CONTACT_NO);
        assertThat(testTeacher.getEmergencyContactEmailAddress()).isEqualTo(DEFAULT_EMERGENCY_CONTACT_EMAIL_ADDRESS);
        assertThat(testTeacher.getUploadPhoto()).isEqualTo(DEFAULT_UPLOAD_PHOTO);
        assertThat(testTeacher.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTeacher.getEmployeeId()).isEqualTo(DEFAULT_EMPLOYEE_ID);
        assertThat(testTeacher.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
        assertThat(testTeacher.getStaffType()).isEqualTo(DEFAULT_STAFF_TYPE);

        // Validate the Teacher in Elasticsearch
        verify(mockTeacherSearchRepository, times(1)).save(testTeacher);
    }

    @Test
    @Transactional
    public void createTeacherWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = teacherRepository.findAll().size();

        // Create the Teacher with an existing ID
        teacher.setId(1L);
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Teacher in the database
        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeCreate);

        // Validate the Teacher in Elasticsearch
        verify(mockTeacherSearchRepository, times(0)).save(teacher);
    }

    @Test
    @Transactional
    public void checkTeacherNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setTeacherName(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTeacherMiddleNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setTeacherMiddleName(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTeacherLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setTeacherLastName(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFatherNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setFatherName(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFatherMiddleNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setFatherMiddleName(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFatherLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setFatherLastName(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMotherNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setMotherName(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMotherMiddleNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setMotherMiddleName(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMotherLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setMotherLastName(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAadharNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setAadharNo(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateOfBirthIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setDateOfBirth(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPlaceOfBirthIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setPlaceOfBirth(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReligionIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setReligion(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCasteIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setCaste(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSubCasteIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setSubCaste(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAgeIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setAge(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSexIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setSex(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBloodGroupIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setBloodGroup(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

//    @Test
//    @Transactional
//    public void checkAddressLineOneIsRequired() throws Exception {
//        int databaseSizeBeforeTest = teacherRepository.findAll().size();
//        // set the field null
//        teacher.setAddressLineOne(null);
//
//        // Create the Teacher, which fails.
//        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);
//
//        restTeacherMockMvc.perform(post("/api/teachers")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
//            .andExpect(status().isBadRequest());
//
//        List<Teacher> teacherList = teacherRepository.findAll();
//        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkAddressLineThreeIsRequired() throws Exception {
//        int databaseSizeBeforeTest = teacherRepository.findAll().size();
//        // set the field null
//        teacher.setAddressLineThree(null);
//
//        // Create the Teacher, which fails.
//        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);
//
//        restTeacherMockMvc.perform(post("/api/teachers")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
//            .andExpect(status().isBadRequest());
//
//        List<Teacher> teacherList = teacherRepository.findAll();
//        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
//    }
//
    @Test
    @Transactional
    public void checkTownIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setTown(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStateIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setState(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCountryIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setCountry(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

//    @Test
//    @Transactional
//    public void checkPincodeIsRequired() throws Exception {
//        int databaseSizeBeforeTest = teacherRepository.findAll().size();
//        // set the field null
//        teacher.setPincode(null);
//
//        // Create the Teacher, which fails.
//        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);
//
//        restTeacherMockMvc.perform(post("/api/teachers")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
//            .andExpect(status().isBadRequest());
//
//        List<Teacher> teacherList = teacherRepository.findAll();
//        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
//    }

    @Test
    @Transactional
    public void checkTeacherContactNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setTeacherContactNumber(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTeacherEmailAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setTeacherEmailAddress(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRelationWithStaffIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setRelationWithStaff(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmergencyContactNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setEmergencyContactName(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmergencyContactLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setEmergencyContactLastName(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmergencyContactNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setEmergencyContactNo(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmergencyContactEmailAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setEmergencyContactEmailAddress(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUploadPhotoIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setUploadPhoto(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setStatus(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDesignationIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setDesignation(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStaffTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = teacherRepository.findAll().size();
        // set the field null
        teacher.setStaffType(null);

        // Create the Teacher, which fails.
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        restTeacherMockMvc.perform(post("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTeachers() throws Exception {
        // Initialize the database
        teacherRepository.saveAndFlush(teacher);

        // Get all the teacherList
        restTeacherMockMvc.perform(get("/api/teachers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(teacher.getId().intValue())))
            .andExpect(jsonPath("$.[*].teacherName").value(hasItem(DEFAULT_TEACHER_NAME.toString())))
            .andExpect(jsonPath("$.[*].teacherMiddleName").value(hasItem(DEFAULT_TEACHER_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].teacherLastName").value(hasItem(DEFAULT_TEACHER_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].fatherName").value(hasItem(DEFAULT_FATHER_NAME.toString())))
            .andExpect(jsonPath("$.[*].fatherMiddleName").value(hasItem(DEFAULT_FATHER_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].fatherLastName").value(hasItem(DEFAULT_FATHER_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].spouseName").value(hasItem(DEFAULT_SPOUSE_NAME.toString())))
            .andExpect(jsonPath("$.[*].spouseMiddleName").value(hasItem(DEFAULT_SPOUSE_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].spouseLastName").value(hasItem(DEFAULT_SPOUSE_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].motherName").value(hasItem(DEFAULT_MOTHER_NAME.toString())))
            .andExpect(jsonPath("$.[*].motherMiddleName").value(hasItem(DEFAULT_MOTHER_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].motherLastName").value(hasItem(DEFAULT_MOTHER_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].aadharNo").value(hasItem(DEFAULT_AADHAR_NO.intValue())))
            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH.toString())))
            .andExpect(jsonPath("$.[*].placeOfBirth").value(hasItem(DEFAULT_PLACE_OF_BIRTH.toString())))
            .andExpect(jsonPath("$.[*].religion").value(hasItem(DEFAULT_RELIGION.toString())))
            .andExpect(jsonPath("$.[*].caste").value(hasItem(DEFAULT_CASTE.toString())))
            .andExpect(jsonPath("$.[*].subCaste").value(hasItem(DEFAULT_SUB_CASTE.toString())))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.toString())))
            .andExpect(jsonPath("$.[*].bloodGroup").value(hasItem(DEFAULT_BLOOD_GROUP.toString())))
            .andExpect(jsonPath("$.[*].addressLineOne").value(hasItem(DEFAULT_ADDRESS_LINE_ONE.toString())))
            .andExpect(jsonPath("$.[*].addressLineTwo").value(hasItem(DEFAULT_ADDRESS_LINE_TWO.toString())))
            .andExpect(jsonPath("$.[*].addressLineThree").value(hasItem(DEFAULT_ADDRESS_LINE_THREE.toString())))
            .andExpect(jsonPath("$.[*].town").value(hasItem(DEFAULT_TOWN.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].pincode").value(hasItem(DEFAULT_PINCODE.intValue())))
            .andExpect(jsonPath("$.[*].teacherContactNumber").value(hasItem(DEFAULT_TEACHER_CONTACT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].alternateContactNumber").value(hasItem(DEFAULT_ALTERNATE_CONTACT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].teacherEmailAddress").value(hasItem(DEFAULT_TEACHER_EMAIL_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].alternateEmailAddress").value(hasItem(DEFAULT_ALTERNATE_EMAIL_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].relationWithStaff").value(hasItem(DEFAULT_RELATION_WITH_STAFF.toString())))
            .andExpect(jsonPath("$.[*].emergencyContactName").value(hasItem(DEFAULT_EMERGENCY_CONTACT_NAME.toString())))
            .andExpect(jsonPath("$.[*].emergencyContactMiddleName").value(hasItem(DEFAULT_EMERGENCY_CONTACT_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].emergencyContactLastName").value(hasItem(DEFAULT_EMERGENCY_CONTACT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].emergencyContactNo").value(hasItem(DEFAULT_EMERGENCY_CONTACT_NO.toString())))
            .andExpect(jsonPath("$.[*].emergencyContactEmailAddress").value(hasItem(DEFAULT_EMERGENCY_CONTACT_EMAIL_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].uploadPhoto").value(hasItem(DEFAULT_UPLOAD_PHOTO.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].employeeId").value(hasItem(DEFAULT_EMPLOYEE_ID.intValue())))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION.toString())))
            .andExpect(jsonPath("$.[*].staffType").value(hasItem(DEFAULT_STAFF_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getTeacher() throws Exception {
        // Initialize the database
        teacherRepository.saveAndFlush(teacher);

        // Get the teacher
        restTeacherMockMvc.perform(get("/api/teachers/{id}", teacher.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(teacher.getId().intValue()))
            .andExpect(jsonPath("$.teacherName").value(DEFAULT_TEACHER_NAME.toString()))
            .andExpect(jsonPath("$.teacherMiddleName").value(DEFAULT_TEACHER_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.teacherLastName").value(DEFAULT_TEACHER_LAST_NAME.toString()))
            .andExpect(jsonPath("$.fatherName").value(DEFAULT_FATHER_NAME.toString()))
            .andExpect(jsonPath("$.fatherMiddleName").value(DEFAULT_FATHER_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.fatherLastName").value(DEFAULT_FATHER_LAST_NAME.toString()))
            .andExpect(jsonPath("$.spouseName").value(DEFAULT_SPOUSE_NAME.toString()))
            .andExpect(jsonPath("$.spouseMiddleName").value(DEFAULT_SPOUSE_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.spouseLastName").value(DEFAULT_SPOUSE_LAST_NAME.toString()))
            .andExpect(jsonPath("$.motherName").value(DEFAULT_MOTHER_NAME.toString()))
            .andExpect(jsonPath("$.motherMiddleName").value(DEFAULT_MOTHER_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.motherLastName").value(DEFAULT_MOTHER_LAST_NAME.toString()))
            .andExpect(jsonPath("$.aadharNo").value(DEFAULT_AADHAR_NO.intValue()))
            .andExpect(jsonPath("$.dateOfBirth").value(DEFAULT_DATE_OF_BIRTH.toString()))
            .andExpect(jsonPath("$.placeOfBirth").value(DEFAULT_PLACE_OF_BIRTH.toString()))
            .andExpect(jsonPath("$.religion").value(DEFAULT_RELIGION.toString()))
            .andExpect(jsonPath("$.caste").value(DEFAULT_CASTE.toString()))
            .andExpect(jsonPath("$.subCaste").value(DEFAULT_SUB_CASTE.toString()))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX.toString()))
            .andExpect(jsonPath("$.bloodGroup").value(DEFAULT_BLOOD_GROUP.toString()))
            .andExpect(jsonPath("$.addressLineOne").value(DEFAULT_ADDRESS_LINE_ONE.toString()))
            .andExpect(jsonPath("$.addressLineTwo").value(DEFAULT_ADDRESS_LINE_TWO.toString()))
            .andExpect(jsonPath("$.addressLineThree").value(DEFAULT_ADDRESS_LINE_THREE.toString()))
            .andExpect(jsonPath("$.town").value(DEFAULT_TOWN.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY.toString()))
            .andExpect(jsonPath("$.pincode").value(DEFAULT_PINCODE.intValue()))
            .andExpect(jsonPath("$.teacherContactNumber").value(DEFAULT_TEACHER_CONTACT_NUMBER.toString()))
            .andExpect(jsonPath("$.alternateContactNumber").value(DEFAULT_ALTERNATE_CONTACT_NUMBER.toString()))
            .andExpect(jsonPath("$.teacherEmailAddress").value(DEFAULT_TEACHER_EMAIL_ADDRESS.toString()))
            .andExpect(jsonPath("$.alternateEmailAddress").value(DEFAULT_ALTERNATE_EMAIL_ADDRESS.toString()))
            .andExpect(jsonPath("$.relationWithStaff").value(DEFAULT_RELATION_WITH_STAFF.toString()))
            .andExpect(jsonPath("$.emergencyContactName").value(DEFAULT_EMERGENCY_CONTACT_NAME.toString()))
            .andExpect(jsonPath("$.emergencyContactMiddleName").value(DEFAULT_EMERGENCY_CONTACT_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.emergencyContactLastName").value(DEFAULT_EMERGENCY_CONTACT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.emergencyContactNo").value(DEFAULT_EMERGENCY_CONTACT_NO.toString()))
            .andExpect(jsonPath("$.emergencyContactEmailAddress").value(DEFAULT_EMERGENCY_CONTACT_EMAIL_ADDRESS.toString()))
            .andExpect(jsonPath("$.uploadPhoto").value(DEFAULT_UPLOAD_PHOTO.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.employeeId").value(DEFAULT_EMPLOYEE_ID.intValue()))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION.toString()))
            .andExpect(jsonPath("$.staffType").value(DEFAULT_STAFF_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTeacher() throws Exception {
        // Get the teacher
        restTeacherMockMvc.perform(get("/api/teachers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTeacher() throws Exception {
        // Initialize the database
        teacherRepository.saveAndFlush(teacher);

        int databaseSizeBeforeUpdate = teacherRepository.findAll().size();

        // Update the teacher
        Teacher updatedTeacher = teacherRepository.findById(teacher.getId()).get();
        // Disconnect from session so that the updates on updatedTeacher are not directly saved in db
        em.detach(updatedTeacher);
        updatedTeacher
            .teacherName(UPDATED_TEACHER_NAME)
            .teacherMiddleName(UPDATED_TEACHER_MIDDLE_NAME)
            .teacherLastName(UPDATED_TEACHER_LAST_NAME)
            .fatherName(UPDATED_FATHER_NAME)
            .fatherMiddleName(UPDATED_FATHER_MIDDLE_NAME)
            .fatherLastName(UPDATED_FATHER_LAST_NAME)
            .spouseName(UPDATED_SPOUSE_NAME)
            .spouseMiddleName(UPDATED_SPOUSE_MIDDLE_NAME)
            .spouseLastName(UPDATED_SPOUSE_LAST_NAME)
            .motherName(UPDATED_MOTHER_NAME)
            .motherMiddleName(UPDATED_MOTHER_MIDDLE_NAME)
            .motherLastName(UPDATED_MOTHER_LAST_NAME)
//            .aadharNo(UPDATED_AADHAR_NO)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .placeOfBirth(UPDATED_PLACE_OF_BIRTH)
//            .religion(UPDATED_RELIGION)
//            .caste(UPDATED_CASTE)
            .subCaste(UPDATED_SUB_CASTE)
            .age(UPDATED_AGE)
//            .sex(UPDATED_SEX)
//            .bloodGroup(UPDATED_BLOOD_GROUP)
            .address(UPDATED_ADDRESS_LINE_ONE)
//            .addressLineTwo(UPDATED_ADDRESS_LINE_TWO)
//            .addressLineThree(UPDATED_ADDRESS_LINE_THREE)
            .town(UPDATED_TOWN)
            .state(UPDATED_STATE)
            .country(UPDATED_COUNTRY)
//            .pincode(UPDATED_PINCODE)
            .teacherContactNumber(UPDATED_TEACHER_CONTACT_NUMBER)
            .alternateContactNumber(UPDATED_ALTERNATE_CONTACT_NUMBER)
            .teacherEmailAddress(UPDATED_TEACHER_EMAIL_ADDRESS)
            .alternateEmailAddress(UPDATED_ALTERNATE_EMAIL_ADDRESS)
//            .relationWithStaff(UPDATED_RELATION_WITH_STAFF)
            .emergencyContactName(UPDATED_EMERGENCY_CONTACT_NAME)
            .emergencyContactMiddleName(UPDATED_EMERGENCY_CONTACT_MIDDLE_NAME)
            .emergencyContactLastName(UPDATED_EMERGENCY_CONTACT_LAST_NAME)
            .emergencyContactNo(UPDATED_EMERGENCY_CONTACT_NO)
            .emergencyContactEmailAddress(UPDATED_EMERGENCY_CONTACT_EMAIL_ADDRESS)
            .uploadPhoto(UPDATED_UPLOAD_PHOTO)
//            .status(UPDATED_STATUS)
            .employeeId(UPDATED_EMPLOYEE_ID)
            .designation(UPDATED_DESIGNATION);
//            .staffType(UPDATED_STAFF_TYPE);
        TeacherDTO teacherDTO = teacherMapper.toDto(updatedTeacher);

        restTeacherMockMvc.perform(put("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isOk());

        // Validate the Teacher in the database
        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeUpdate);
        Teacher testTeacher = teacherList.get(teacherList.size() - 1);
        assertThat(testTeacher.getTeacherName()).isEqualTo(UPDATED_TEACHER_NAME);
        assertThat(testTeacher.getTeacherMiddleName()).isEqualTo(UPDATED_TEACHER_MIDDLE_NAME);
        assertThat(testTeacher.getTeacherLastName()).isEqualTo(UPDATED_TEACHER_LAST_NAME);
        assertThat(testTeacher.getFatherName()).isEqualTo(UPDATED_FATHER_NAME);
        assertThat(testTeacher.getFatherMiddleName()).isEqualTo(UPDATED_FATHER_MIDDLE_NAME);
        assertThat(testTeacher.getFatherLastName()).isEqualTo(UPDATED_FATHER_LAST_NAME);
        assertThat(testTeacher.getSpouseName()).isEqualTo(UPDATED_SPOUSE_NAME);
        assertThat(testTeacher.getSpouseMiddleName()).isEqualTo(UPDATED_SPOUSE_MIDDLE_NAME);
        assertThat(testTeacher.getSpouseLastName()).isEqualTo(UPDATED_SPOUSE_LAST_NAME);
        assertThat(testTeacher.getMotherName()).isEqualTo(UPDATED_MOTHER_NAME);
        assertThat(testTeacher.getMotherMiddleName()).isEqualTo(UPDATED_MOTHER_MIDDLE_NAME);
        assertThat(testTeacher.getMotherLastName()).isEqualTo(UPDATED_MOTHER_LAST_NAME);
        assertThat(testTeacher.getAadharNo()).isEqualTo(UPDATED_AADHAR_NO);
        assertThat(testTeacher.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
        assertThat(testTeacher.getPlaceOfBirth()).isEqualTo(UPDATED_PLACE_OF_BIRTH);
        assertThat(testTeacher.getReligion()).isEqualTo(UPDATED_RELIGION);
        assertThat(testTeacher.getCaste()).isEqualTo(UPDATED_CASTE);
        assertThat(testTeacher.getSubCaste()).isEqualTo(UPDATED_SUB_CASTE);
        assertThat(testTeacher.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testTeacher.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testTeacher.getBloodGroup()).isEqualTo(UPDATED_BLOOD_GROUP);
//        assertThat(testTeacher.getAddressLineOne()).isEqualTo(UPDATED_ADDRESS_LINE_ONE);
//        assertThat(testTeacher.getAddressLineTwo()).isEqualTo(UPDATED_ADDRESS_LINE_TWO);
//        assertThat(testTeacher.getAddressLineThree()).isEqualTo(UPDATED_ADDRESS_LINE_THREE);
        assertThat(testTeacher.getTown()).isEqualTo(UPDATED_TOWN);
        assertThat(testTeacher.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testTeacher.getCountry()).isEqualTo(UPDATED_COUNTRY);
//        assertThat(testTeacher.getPincode()).isEqualTo(UPDATED_PINCODE);
        assertThat(testTeacher.getTeacherContactNumber()).isEqualTo(UPDATED_TEACHER_CONTACT_NUMBER);
        assertThat(testTeacher.getAlternateContactNumber()).isEqualTo(UPDATED_ALTERNATE_CONTACT_NUMBER);
        assertThat(testTeacher.getTeacherEmailAddress()).isEqualTo(UPDATED_TEACHER_EMAIL_ADDRESS);
        assertThat(testTeacher.getAlternateEmailAddress()).isEqualTo(UPDATED_ALTERNATE_EMAIL_ADDRESS);
        assertThat(testTeacher.getRelationWithStaff()).isEqualTo(UPDATED_RELATION_WITH_STAFF);
        assertThat(testTeacher.getEmergencyContactName()).isEqualTo(UPDATED_EMERGENCY_CONTACT_NAME);
        assertThat(testTeacher.getEmergencyContactMiddleName()).isEqualTo(UPDATED_EMERGENCY_CONTACT_MIDDLE_NAME);
        assertThat(testTeacher.getEmergencyContactLastName()).isEqualTo(UPDATED_EMERGENCY_CONTACT_LAST_NAME);
        assertThat(testTeacher.getEmergencyContactNo()).isEqualTo(UPDATED_EMERGENCY_CONTACT_NO);
        assertThat(testTeacher.getEmergencyContactEmailAddress()).isEqualTo(UPDATED_EMERGENCY_CONTACT_EMAIL_ADDRESS);
        assertThat(testTeacher.getUploadPhoto()).isEqualTo(UPDATED_UPLOAD_PHOTO);
        assertThat(testTeacher.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTeacher.getEmployeeId()).isEqualTo(UPDATED_EMPLOYEE_ID);
        assertThat(testTeacher.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
        assertThat(testTeacher.getStaffType()).isEqualTo(UPDATED_STAFF_TYPE);

        // Validate the Teacher in Elasticsearch
        verify(mockTeacherSearchRepository, times(1)).save(testTeacher);
    }

    @Test
    @Transactional
    public void updateNonExistingTeacher() throws Exception {
        int databaseSizeBeforeUpdate = teacherRepository.findAll().size();

        // Create the Teacher
        TeacherDTO teacherDTO = teacherMapper.toDto(teacher);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTeacherMockMvc.perform(put("/api/teachers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teacherDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Teacher in the database
        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Teacher in Elasticsearch
        verify(mockTeacherSearchRepository, times(0)).save(teacher);
    }

    @Test
    @Transactional
    public void deleteTeacher() throws Exception {
        // Initialize the database
        teacherRepository.saveAndFlush(teacher);

        int databaseSizeBeforeDelete = teacherRepository.findAll().size();

        // Delete the teacher
        restTeacherMockMvc.perform(delete("/api/teachers/{id}", teacher.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Teacher in Elasticsearch
        verify(mockTeacherSearchRepository, times(1)).deleteById(teacher.getId());
    }

    @Test
    @Transactional
    public void searchTeacher() throws Exception {
        // Initialize the database
        teacherRepository.saveAndFlush(teacher);
        when(mockTeacherSearchRepository.search(queryStringQuery("id:" + teacher.getId())))
            .thenReturn(Collections.singletonList(teacher));
        // Search the teacher
        restTeacherMockMvc.perform(get("/api/_search/teachers?query=id:" + teacher.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(teacher.getId().intValue())))
            .andExpect(jsonPath("$.[*].teacherName").value(hasItem(DEFAULT_TEACHER_NAME)))
            .andExpect(jsonPath("$.[*].teacherMiddleName").value(hasItem(DEFAULT_TEACHER_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].teacherLastName").value(hasItem(DEFAULT_TEACHER_LAST_NAME)))
            .andExpect(jsonPath("$.[*].fatherName").value(hasItem(DEFAULT_FATHER_NAME)))
            .andExpect(jsonPath("$.[*].fatherMiddleName").value(hasItem(DEFAULT_FATHER_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].fatherLastName").value(hasItem(DEFAULT_FATHER_LAST_NAME)))
            .andExpect(jsonPath("$.[*].spouseName").value(hasItem(DEFAULT_SPOUSE_NAME)))
            .andExpect(jsonPath("$.[*].spouseMiddleName").value(hasItem(DEFAULT_SPOUSE_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].spouseLastName").value(hasItem(DEFAULT_SPOUSE_LAST_NAME)))
            .andExpect(jsonPath("$.[*].motherName").value(hasItem(DEFAULT_MOTHER_NAME)))
            .andExpect(jsonPath("$.[*].motherMiddleName").value(hasItem(DEFAULT_MOTHER_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].motherLastName").value(hasItem(DEFAULT_MOTHER_LAST_NAME)))
            .andExpect(jsonPath("$.[*].aadharNo").value(hasItem(DEFAULT_AADHAR_NO.intValue())))
            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH.toString())))
            .andExpect(jsonPath("$.[*].placeOfBirth").value(hasItem(DEFAULT_PLACE_OF_BIRTH)))
            .andExpect(jsonPath("$.[*].religion").value(hasItem(DEFAULT_RELIGION.toString())))
            .andExpect(jsonPath("$.[*].caste").value(hasItem(DEFAULT_CASTE.toString())))
            .andExpect(jsonPath("$.[*].subCaste").value(hasItem(DEFAULT_SUB_CASTE)))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.toString())))
            .andExpect(jsonPath("$.[*].bloodGroup").value(hasItem(DEFAULT_BLOOD_GROUP.toString())))
            .andExpect(jsonPath("$.[*].addressLineOne").value(hasItem(DEFAULT_ADDRESS_LINE_ONE)))
            .andExpect(jsonPath("$.[*].addressLineTwo").value(hasItem(DEFAULT_ADDRESS_LINE_TWO)))
            .andExpect(jsonPath("$.[*].addressLineThree").value(hasItem(DEFAULT_ADDRESS_LINE_THREE)))
            .andExpect(jsonPath("$.[*].town").value(hasItem(DEFAULT_TOWN)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].pincode").value(hasItem(DEFAULT_PINCODE.intValue())))
            .andExpect(jsonPath("$.[*].teacherContactNumber").value(hasItem(DEFAULT_TEACHER_CONTACT_NUMBER)))
            .andExpect(jsonPath("$.[*].alternateContactNumber").value(hasItem(DEFAULT_ALTERNATE_CONTACT_NUMBER)))
            .andExpect(jsonPath("$.[*].teacherEmailAddress").value(hasItem(DEFAULT_TEACHER_EMAIL_ADDRESS)))
            .andExpect(jsonPath("$.[*].alternateEmailAddress").value(hasItem(DEFAULT_ALTERNATE_EMAIL_ADDRESS)))
            .andExpect(jsonPath("$.[*].relationWithStaff").value(hasItem(DEFAULT_RELATION_WITH_STAFF.toString())))
            .andExpect(jsonPath("$.[*].emergencyContactName").value(hasItem(DEFAULT_EMERGENCY_CONTACT_NAME)))
            .andExpect(jsonPath("$.[*].emergencyContactMiddleName").value(hasItem(DEFAULT_EMERGENCY_CONTACT_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].emergencyContactLastName").value(hasItem(DEFAULT_EMERGENCY_CONTACT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].emergencyContactNo").value(hasItem(DEFAULT_EMERGENCY_CONTACT_NO)))
            .andExpect(jsonPath("$.[*].emergencyContactEmailAddress").value(hasItem(DEFAULT_EMERGENCY_CONTACT_EMAIL_ADDRESS)))
            .andExpect(jsonPath("$.[*].uploadPhoto").value(hasItem(DEFAULT_UPLOAD_PHOTO)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].employeeId").value(hasItem(DEFAULT_EMPLOYEE_ID.intValue())))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION)))
            .andExpect(jsonPath("$.[*].staffType").value(hasItem(DEFAULT_STAFF_TYPE.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Teacher.class);
        Teacher teacher1 = new Teacher();
        teacher1.setId(1L);
        Teacher teacher2 = new Teacher();
        teacher2.setId(teacher1.getId());
        assertThat(teacher1).isEqualTo(teacher2);
        teacher2.setId(2L);
        assertThat(teacher1).isNotEqualTo(teacher2);
        teacher1.setId(null);
        assertThat(teacher1).isNotEqualTo(teacher2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TeacherDTO.class);
        TeacherDTO teacherDTO1 = new TeacherDTO();
        teacherDTO1.setId(1L);
        TeacherDTO teacherDTO2 = new TeacherDTO();
        assertThat(teacherDTO1).isNotEqualTo(teacherDTO2);
        teacherDTO2.setId(teacherDTO1.getId());
        assertThat(teacherDTO1).isEqualTo(teacherDTO2);
        teacherDTO2.setId(2L);
        assertThat(teacherDTO1).isNotEqualTo(teacherDTO2);
        teacherDTO1.setId(null);
        assertThat(teacherDTO1).isNotEqualTo(teacherDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(teacherMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(teacherMapper.fromId(null)).isNull();
    }
}
