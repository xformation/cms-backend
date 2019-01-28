package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.Student;
import com.synectiks.cms.domain.Department;
import com.synectiks.cms.domain.Batch;
import com.synectiks.cms.domain.Section;
import com.synectiks.cms.domain.Branch;
import com.synectiks.cms.repository.StudentRepository;
import com.synectiks.cms.repository.search.StudentSearchRepository;
import com.synectiks.cms.service.StudentService;
import com.synectiks.cms.service.dto.StudentDTO;
import com.synectiks.cms.service.mapper.StudentMapper;
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

import com.synectiks.cms.domain.enumeration.Religion;
import com.synectiks.cms.domain.enumeration.Caste;
import com.synectiks.cms.domain.enumeration.Gender;
import com.synectiks.cms.domain.enumeration.Bloodgroup;
import com.synectiks.cms.domain.enumeration.RelationWithStudentEnum;
/**
 * Test class for the StudentResource REST controller.
 *
 * @see StudentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class StudentResourceIntTest {

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

    private static final Long DEFAULT_AADHAR_NO = 1L;
    private static final Long UPDATED_AADHAR_NO = 2L;

    private static final Date DEFAULT_DATE_OF_BIRTH = new Date();
    private static final Date UPDATED_DATE_OF_BIRTH = new Date();

    private static final String DEFAULT_PLACE_OF_BIRTH = "AAAAAAAAAA";
    private static final String UPDATED_PLACE_OF_BIRTH = "BBBBBBBBBB";

    private static final Religion DEFAULT_RELIGION = Religion.HINDU;
    private static final Religion UPDATED_RELIGION = Religion.MUSLIM;

    private static final Caste DEFAULT_CASTE = Caste.OC;
    private static final Caste UPDATED_CASTE = Caste.BC;

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

    private static final Long DEFAULT_STUDENT_CONTACT_NUMBER = 1L;
    private static final Long UPDATED_STUDENT_CONTACT_NUMBER = 2L;

    private static final Long DEFAULT_ALTERNATE_CONTACT_NUMBER = 1L;
    private static final Long UPDATED_ALTERNATE_CONTACT_NUMBER = 2L;

    private static final String DEFAULT_STUDENT_EMAIL_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_EMAIL_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_ALTERNATE_EMAIL_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ALTERNATE_EMAIL_ADDRESS = "BBBBBBBBBB";

    private static final RelationWithStudentEnum DEFAULT_RELATION_WITH_STUDENT = RelationWithStudentEnum.FATHER;
    private static final RelationWithStudentEnum UPDATED_RELATION_WITH_STUDENT = RelationWithStudentEnum.MOTHER;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_CONTACT_NO = 1L;
    private static final Long UPDATED_CONTACT_NO = 2L;

    private static final String DEFAULT_EMAIL_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_ADDRESS = "BBBBBBBBBB";

    private static final Long DEFAULT_UPLOAD_PHOTO = 1L;
    private static final Long UPDATED_UPLOAD_PHOTO = 2L;

    private static final Long DEFAULT_ADMISSION_NO = 1L;
    private static final Long UPDATED_ADMISSION_NO = 2L;

    private static final String DEFAULT_ROLL_NO = "AAAAAAAAAA";
    private static final String UPDATED_ROLL_NO = "BBBBBBBBBB";

    private static final String DEFAULT_STUDENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_TYPE = "BBBBBBBBBB";

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentService studentService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.StudentSearchRepositoryMockConfiguration
     */
    @Autowired
    private StudentSearchRepository mockStudentSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStudentMockMvc;

    private Student student;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StudentResource studentResource = new StudentResource(studentService);
        this.restStudentMockMvc = MockMvcBuilders.standaloneSetup(studentResource)
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
    public static Student createEntity(EntityManager em) {
        Student student = new Student();
        student.studentName(DEFAULT_STUDENT_NAME);
        student.studentMiddleName(DEFAULT_STUDENT_MIDDLE_NAME);
        student.studentLastName(DEFAULT_STUDENT_LAST_NAME);
        student.fatherName(DEFAULT_FATHER_NAME);
        student.fatherMiddleName(DEFAULT_FATHER_MIDDLE_NAME);
        student.fatherLastName(DEFAULT_FATHER_LAST_NAME);
        student.motherName(DEFAULT_MOTHER_NAME);
        student.motherMiddleName(DEFAULT_MOTHER_MIDDLE_NAME);
        student.motherLastName(DEFAULT_MOTHER_LAST_NAME);
        student.aadharNo(DEFAULT_AADHAR_NO);
        student.setDateOfBirth(DEFAULT_DATE_OF_BIRTH);
        student.placeOfBirth(DEFAULT_PLACE_OF_BIRTH);
        student.religion(DEFAULT_RELIGION);
        student.caste(DEFAULT_CASTE);
        student.subCaste(DEFAULT_SUB_CASTE);
        student.age(DEFAULT_AGE);
        student.sex(DEFAULT_SEX);
        student.bloodGroup(DEFAULT_BLOOD_GROUP);
        student.addressLineOne(DEFAULT_ADDRESS_LINE_ONE);
        student.addressLineTwo(DEFAULT_ADDRESS_LINE_TWO);
        student.addressLineThree(DEFAULT_ADDRESS_LINE_THREE);
        student.town(DEFAULT_TOWN);
        student.state(DEFAULT_STATE);
        student.country(DEFAULT_COUNTRY);
        student.pincode(DEFAULT_PINCODE);
        student.studentContactNumber(DEFAULT_STUDENT_CONTACT_NUMBER);
        student.alternateContactNumber(DEFAULT_ALTERNATE_CONTACT_NUMBER);
        student.studentEmailAddress(DEFAULT_STUDENT_EMAIL_ADDRESS);
        student.alternateEmailAddress(DEFAULT_ALTERNATE_EMAIL_ADDRESS);
        student.relationWithStudent(DEFAULT_RELATION_WITH_STUDENT);
        student.name(DEFAULT_NAME);
        student.middleName(DEFAULT_MIDDLE_NAME);
        student.lastName(DEFAULT_LAST_NAME);
        student.contactNo(DEFAULT_CONTACT_NO);
        student.emailAddress(DEFAULT_EMAIL_ADDRESS);
        student.uploadPhoto(DEFAULT_UPLOAD_PHOTO);
        student.admissionNo(DEFAULT_ADMISSION_NO);
        student.rollNo(DEFAULT_ROLL_NO);
        student.studentType(DEFAULT_STUDENT_TYPE);
        // Add required entity
        Department department = DepartmentResourceIntTest.createEntity(em);
        em.persist(department);
        em.flush();
        student.setDepartment(department);
        // Add required entity
        Batch batch = BatchResourceIntTest.createEntity(em);
        em.persist(batch);
        em.flush();
        student.setBatch(batch);
        // Add required entity
        Section section = SectionResourceIntTest.createEntity(em);
        em.persist(section);
        em.flush();
        student.setSection(section);
        // Add required entity
        Branch branch = BranchResourceIntTest.createEntity(em);
        em.persist(branch);
        em.flush();
        student.setBranch(branch);
        return student;
    }

    @Before
    public void initTest() {
        student = createEntity(em);
    }

    @Test
    @Transactional
    public void createStudent() throws Exception {
        int databaseSizeBeforeCreate = studentRepository.findAll().size();

        // Create the Student
        StudentDTO studentDTO = studentMapper.toDto(student);
        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isCreated());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeCreate + 1);
        Student testStudent = studentList.get(studentList.size() - 1);
        assertThat(testStudent.getStudentName()).isEqualTo(DEFAULT_STUDENT_NAME);
        assertThat(testStudent.getStudentMiddleName()).isEqualTo(DEFAULT_STUDENT_MIDDLE_NAME);
        assertThat(testStudent.getStudentLastName()).isEqualTo(DEFAULT_STUDENT_LAST_NAME);
        assertThat(testStudent.getFatherName()).isEqualTo(DEFAULT_FATHER_NAME);
        assertThat(testStudent.getFatherMiddleName()).isEqualTo(DEFAULT_FATHER_MIDDLE_NAME);
        assertThat(testStudent.getFatherLastName()).isEqualTo(DEFAULT_FATHER_LAST_NAME);
        assertThat(testStudent.getMotherName()).isEqualTo(DEFAULT_MOTHER_NAME);
        assertThat(testStudent.getMotherMiddleName()).isEqualTo(DEFAULT_MOTHER_MIDDLE_NAME);
        assertThat(testStudent.getMotherLastName()).isEqualTo(DEFAULT_MOTHER_LAST_NAME);
        assertThat(testStudent.getAadharNo()).isEqualTo(DEFAULT_AADHAR_NO);
        assertThat(testStudent.getDateOfBirth()).isEqualTo(DEFAULT_DATE_OF_BIRTH);
        assertThat(testStudent.getPlaceOfBirth()).isEqualTo(DEFAULT_PLACE_OF_BIRTH);
        assertThat(testStudent.getReligion()).isEqualTo(DEFAULT_RELIGION);
        assertThat(testStudent.getCaste()).isEqualTo(DEFAULT_CASTE);
        assertThat(testStudent.getSubCaste()).isEqualTo(DEFAULT_SUB_CASTE);
        assertThat(testStudent.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testStudent.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testStudent.getBloodGroup()).isEqualTo(DEFAULT_BLOOD_GROUP);
        assertThat(testStudent.getAddressLineOne()).isEqualTo(DEFAULT_ADDRESS_LINE_ONE);
        assertThat(testStudent.getAddressLineTwo()).isEqualTo(DEFAULT_ADDRESS_LINE_TWO);
        assertThat(testStudent.getAddressLineThree()).isEqualTo(DEFAULT_ADDRESS_LINE_THREE);
        assertThat(testStudent.getTown()).isEqualTo(DEFAULT_TOWN);
        assertThat(testStudent.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testStudent.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testStudent.getPincode()).isEqualTo(DEFAULT_PINCODE);
        assertThat(testStudent.getStudentContactNumber()).isEqualTo(DEFAULT_STUDENT_CONTACT_NUMBER);
        assertThat(testStudent.getAlternateContactNumber()).isEqualTo(DEFAULT_ALTERNATE_CONTACT_NUMBER);
        assertThat(testStudent.getStudentEmailAddress()).isEqualTo(DEFAULT_STUDENT_EMAIL_ADDRESS);
        assertThat(testStudent.getAlternateEmailAddress()).isEqualTo(DEFAULT_ALTERNATE_EMAIL_ADDRESS);
        assertThat(testStudent.getRelationWithStudent()).isEqualTo(DEFAULT_RELATION_WITH_STUDENT);
        assertThat(testStudent.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testStudent.getMiddleName()).isEqualTo(DEFAULT_MIDDLE_NAME);
        assertThat(testStudent.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testStudent.getContactNo()).isEqualTo(DEFAULT_CONTACT_NO);
        assertThat(testStudent.getEmailAddress()).isEqualTo(DEFAULT_EMAIL_ADDRESS);
        assertThat(testStudent.getUploadPhoto()).isEqualTo(DEFAULT_UPLOAD_PHOTO);
        assertThat(testStudent.getAdmissionNo()).isEqualTo(DEFAULT_ADMISSION_NO);
        assertThat(testStudent.getRollNo()).isEqualTo(DEFAULT_ROLL_NO);
        assertThat(testStudent.getStudentType()).isEqualTo(DEFAULT_STUDENT_TYPE);

        // Validate the Student in Elasticsearch
        verify(mockStudentSearchRepository, times(1)).save(testStudent);
    }

    @Test
    @Transactional
    public void createStudentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = studentRepository.findAll().size();

        // Create the Student with an existing ID
        student.setId(1L);
        StudentDTO studentDTO = studentMapper.toDto(student);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeCreate);

        // Validate the Student in Elasticsearch
        verify(mockStudentSearchRepository, times(0)).save(student);
    }

    @Test
    @Transactional
    public void checkStudentNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setStudentName(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStudentMiddleNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setStudentMiddleName(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStudentLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setStudentLastName(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFatherNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setFatherName(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFatherMiddleNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setFatherMiddleName(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFatherLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setFatherLastName(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMotherNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setMotherName(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMotherMiddleNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setMotherMiddleName(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMotherLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setMotherLastName(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAadharNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setAadharNo(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateOfBirthIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setDateOfBirth(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPlaceOfBirthIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setPlaceOfBirth(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReligionIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setReligion(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCasteIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setCaste(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSubCasteIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setSubCaste(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAgeIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setAge(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSexIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setSex(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBloodGroupIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setBloodGroup(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAddressLineOneIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setAddressLineOne(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAddressLineTwoIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setAddressLineTwo(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAddressLineThreeIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setAddressLineThree(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTownIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setTown(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStateIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setState(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCountryIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setCountry(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPincodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setPincode(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStudentContactNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setStudentContactNumber(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAlternateContactNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setAlternateContactNumber(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStudentEmailAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setStudentEmailAddress(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAlternateEmailAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setAlternateEmailAddress(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRelationWithStudentIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setRelationWithStudent(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setName(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMiddleNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setMiddleName(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setLastName(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContactNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setContactNo(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setEmailAddress(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUploadPhotoIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setUploadPhoto(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAdmissionNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setAdmissionNo(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRollNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setRollNo(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStudentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setStudentType(null);

        // Create the Student, which fails.
        StudentDTO studentDTO = studentMapper.toDto(student);

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStudents() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList
        restStudentMockMvc.perform(get("/api/students?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(student.getId().intValue())))
            .andExpect(jsonPath("$.[*].studentName").value(hasItem(DEFAULT_STUDENT_NAME.toString())))
            .andExpect(jsonPath("$.[*].studentMiddleName").value(hasItem(DEFAULT_STUDENT_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].studentLastName").value(hasItem(DEFAULT_STUDENT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].fatherName").value(hasItem(DEFAULT_FATHER_NAME.toString())))
            .andExpect(jsonPath("$.[*].fatherMiddleName").value(hasItem(DEFAULT_FATHER_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].fatherLastName").value(hasItem(DEFAULT_FATHER_LAST_NAME.toString())))
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
            .andExpect(jsonPath("$.[*].studentContactNumber").value(hasItem(DEFAULT_STUDENT_CONTACT_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].alternateContactNumber").value(hasItem(DEFAULT_ALTERNATE_CONTACT_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].studentEmailAddress").value(hasItem(DEFAULT_STUDENT_EMAIL_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].alternateEmailAddress").value(hasItem(DEFAULT_ALTERNATE_EMAIL_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].relationWithStudent").value(hasItem(DEFAULT_RELATION_WITH_STUDENT.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].contactNo").value(hasItem(DEFAULT_CONTACT_NO.intValue())))
            .andExpect(jsonPath("$.[*].emailAddress").value(hasItem(DEFAULT_EMAIL_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].uploadPhoto").value(hasItem(DEFAULT_UPLOAD_PHOTO.intValue())))
            .andExpect(jsonPath("$.[*].admissionNo").value(hasItem(DEFAULT_ADMISSION_NO.intValue())))
            .andExpect(jsonPath("$.[*].rollNo").value(hasItem(DEFAULT_ROLL_NO.toString())))
            .andExpect(jsonPath("$.[*].studentType").value(hasItem(DEFAULT_STUDENT_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getStudent() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get the student
        restStudentMockMvc.perform(get("/api/students/{id}", student.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(student.getId().intValue()))
            .andExpect(jsonPath("$.studentName").value(DEFAULT_STUDENT_NAME.toString()))
            .andExpect(jsonPath("$.studentMiddleName").value(DEFAULT_STUDENT_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.studentLastName").value(DEFAULT_STUDENT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.fatherName").value(DEFAULT_FATHER_NAME.toString()))
            .andExpect(jsonPath("$.fatherMiddleName").value(DEFAULT_FATHER_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.fatherLastName").value(DEFAULT_FATHER_LAST_NAME.toString()))
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
            .andExpect(jsonPath("$.studentContactNumber").value(DEFAULT_STUDENT_CONTACT_NUMBER.intValue()))
            .andExpect(jsonPath("$.alternateContactNumber").value(DEFAULT_ALTERNATE_CONTACT_NUMBER.intValue()))
            .andExpect(jsonPath("$.studentEmailAddress").value(DEFAULT_STUDENT_EMAIL_ADDRESS.toString()))
            .andExpect(jsonPath("$.alternateEmailAddress").value(DEFAULT_ALTERNATE_EMAIL_ADDRESS.toString()))
            .andExpect(jsonPath("$.relationWithStudent").value(DEFAULT_RELATION_WITH_STUDENT.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.middleName").value(DEFAULT_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.contactNo").value(DEFAULT_CONTACT_NO.intValue()))
            .andExpect(jsonPath("$.emailAddress").value(DEFAULT_EMAIL_ADDRESS.toString()))
            .andExpect(jsonPath("$.uploadPhoto").value(DEFAULT_UPLOAD_PHOTO.intValue()))
            .andExpect(jsonPath("$.admissionNo").value(DEFAULT_ADMISSION_NO.intValue()))
            .andExpect(jsonPath("$.rollNo").value(DEFAULT_ROLL_NO.toString()))
            .andExpect(jsonPath("$.studentType").value(DEFAULT_STUDENT_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingStudent() throws Exception {
        // Get the student
        restStudentMockMvc.perform(get("/api/students/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStudent() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        int databaseSizeBeforeUpdate = studentRepository.findAll().size();

        // Update the student
        Student updatedStudent = studentRepository.findById(student.getId()).get();
        // Disconnect from session so that the updates on updatedStudent are not directly saved in db
        em.detach(updatedStudent);
        //updatedStudent
        updatedStudent.studentName(UPDATED_STUDENT_NAME);
        updatedStudent.studentMiddleName(UPDATED_STUDENT_MIDDLE_NAME);
        updatedStudent.studentLastName(UPDATED_STUDENT_LAST_NAME);
        updatedStudent.fatherName(UPDATED_FATHER_NAME);
        updatedStudent.fatherMiddleName(UPDATED_FATHER_MIDDLE_NAME);
        updatedStudent.fatherLastName(UPDATED_FATHER_LAST_NAME);
        updatedStudent.motherName(UPDATED_MOTHER_NAME);
        updatedStudent.motherMiddleName(UPDATED_MOTHER_MIDDLE_NAME);
        updatedStudent.motherLastName(UPDATED_MOTHER_LAST_NAME);
        updatedStudent.aadharNo(UPDATED_AADHAR_NO);
        updatedStudent.setDateOfBirth(UPDATED_DATE_OF_BIRTH);
        updatedStudent.placeOfBirth(UPDATED_PLACE_OF_BIRTH);
        updatedStudent.religion(UPDATED_RELIGION);
        updatedStudent.caste(UPDATED_CASTE);
        updatedStudent.subCaste(UPDATED_SUB_CASTE);
        updatedStudent.age(UPDATED_AGE);
        updatedStudent.sex(UPDATED_SEX);
        updatedStudent.bloodGroup(UPDATED_BLOOD_GROUP);
        updatedStudent.addressLineOne(UPDATED_ADDRESS_LINE_ONE);
        updatedStudent.addressLineTwo(UPDATED_ADDRESS_LINE_TWO);
        updatedStudent.addressLineThree(UPDATED_ADDRESS_LINE_THREE);
        updatedStudent.town(UPDATED_TOWN);
        updatedStudent.state(UPDATED_STATE);
        updatedStudent.country(UPDATED_COUNTRY);
        updatedStudent.pincode(UPDATED_PINCODE);
        updatedStudent.studentContactNumber(UPDATED_STUDENT_CONTACT_NUMBER);
        updatedStudent.alternateContactNumber(UPDATED_ALTERNATE_CONTACT_NUMBER);
        updatedStudent.studentEmailAddress(UPDATED_STUDENT_EMAIL_ADDRESS);
        updatedStudent.alternateEmailAddress(UPDATED_ALTERNATE_EMAIL_ADDRESS);
        updatedStudent.relationWithStudent(UPDATED_RELATION_WITH_STUDENT);
        updatedStudent.name(UPDATED_NAME);
        updatedStudent.middleName(UPDATED_MIDDLE_NAME);
        updatedStudent.lastName(UPDATED_LAST_NAME);
        updatedStudent.contactNo(UPDATED_CONTACT_NO);
        updatedStudent.emailAddress(UPDATED_EMAIL_ADDRESS);
        updatedStudent.uploadPhoto(UPDATED_UPLOAD_PHOTO);
        updatedStudent.admissionNo(UPDATED_ADMISSION_NO);
        updatedStudent.rollNo(UPDATED_ROLL_NO);
        updatedStudent.studentType(UPDATED_STUDENT_TYPE);
        StudentDTO studentDTO = studentMapper.toDto(updatedStudent);

        restStudentMockMvc.perform(put("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isOk());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
        Student testStudent = studentList.get(studentList.size() - 1);
        assertThat(testStudent.getStudentName()).isEqualTo(UPDATED_STUDENT_NAME);
        assertThat(testStudent.getStudentMiddleName()).isEqualTo(UPDATED_STUDENT_MIDDLE_NAME);
        assertThat(testStudent.getStudentLastName()).isEqualTo(UPDATED_STUDENT_LAST_NAME);
        assertThat(testStudent.getFatherName()).isEqualTo(UPDATED_FATHER_NAME);
        assertThat(testStudent.getFatherMiddleName()).isEqualTo(UPDATED_FATHER_MIDDLE_NAME);
        assertThat(testStudent.getFatherLastName()).isEqualTo(UPDATED_FATHER_LAST_NAME);
        assertThat(testStudent.getMotherName()).isEqualTo(UPDATED_MOTHER_NAME);
        assertThat(testStudent.getMotherMiddleName()).isEqualTo(UPDATED_MOTHER_MIDDLE_NAME);
        assertThat(testStudent.getMotherLastName()).isEqualTo(UPDATED_MOTHER_LAST_NAME);
        assertThat(testStudent.getAadharNo()).isEqualTo(UPDATED_AADHAR_NO);
        assertThat(testStudent.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
        assertThat(testStudent.getPlaceOfBirth()).isEqualTo(UPDATED_PLACE_OF_BIRTH);
        assertThat(testStudent.getReligion()).isEqualTo(UPDATED_RELIGION);
        assertThat(testStudent.getCaste()).isEqualTo(UPDATED_CASTE);
        assertThat(testStudent.getSubCaste()).isEqualTo(UPDATED_SUB_CASTE);
        assertThat(testStudent.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testStudent.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testStudent.getBloodGroup()).isEqualTo(UPDATED_BLOOD_GROUP);
        assertThat(testStudent.getAddressLineOne()).isEqualTo(UPDATED_ADDRESS_LINE_ONE);
        assertThat(testStudent.getAddressLineTwo()).isEqualTo(UPDATED_ADDRESS_LINE_TWO);
        assertThat(testStudent.getAddressLineThree()).isEqualTo(UPDATED_ADDRESS_LINE_THREE);
        assertThat(testStudent.getTown()).isEqualTo(UPDATED_TOWN);
        assertThat(testStudent.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testStudent.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testStudent.getPincode()).isEqualTo(UPDATED_PINCODE);
        assertThat(testStudent.getStudentContactNumber()).isEqualTo(UPDATED_STUDENT_CONTACT_NUMBER);
        assertThat(testStudent.getAlternateContactNumber()).isEqualTo(UPDATED_ALTERNATE_CONTACT_NUMBER);
        assertThat(testStudent.getStudentEmailAddress()).isEqualTo(UPDATED_STUDENT_EMAIL_ADDRESS);
        assertThat(testStudent.getAlternateEmailAddress()).isEqualTo(UPDATED_ALTERNATE_EMAIL_ADDRESS);
        assertThat(testStudent.getRelationWithStudent()).isEqualTo(UPDATED_RELATION_WITH_STUDENT);
        assertThat(testStudent.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testStudent.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testStudent.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testStudent.getContactNo()).isEqualTo(UPDATED_CONTACT_NO);
        assertThat(testStudent.getEmailAddress()).isEqualTo(UPDATED_EMAIL_ADDRESS);
        assertThat(testStudent.getUploadPhoto()).isEqualTo(UPDATED_UPLOAD_PHOTO);
        assertThat(testStudent.getAdmissionNo()).isEqualTo(UPDATED_ADMISSION_NO);
        assertThat(testStudent.getRollNo()).isEqualTo(UPDATED_ROLL_NO);
        assertThat(testStudent.getStudentType()).isEqualTo(UPDATED_STUDENT_TYPE);

        // Validate the Student in Elasticsearch
        verify(mockStudentSearchRepository, times(1)).save(testStudent);
    }

    @Test
    @Transactional
    public void updateNonExistingStudent() throws Exception {
        int databaseSizeBeforeUpdate = studentRepository.findAll().size();

        // Create the Student
        StudentDTO studentDTO = studentMapper.toDto(student);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStudentMockMvc.perform(put("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Student in Elasticsearch
        verify(mockStudentSearchRepository, times(0)).save(student);
    }

    @Test
    @Transactional
    public void deleteStudent() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        int databaseSizeBeforeDelete = studentRepository.findAll().size();

        // Get the student
        restStudentMockMvc.perform(delete("/api/students/{id}", student.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Student in Elasticsearch
        verify(mockStudentSearchRepository, times(1)).deleteById(student.getId());
    }

    @Test
    @Transactional
    public void searchStudent() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);
        when(mockStudentSearchRepository.search(queryStringQuery("id:" + student.getId())))
            .thenReturn(Collections.singletonList(student));
        // Search the student
        restStudentMockMvc.perform(get("/api/_search/students?query=id:" + student.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(student.getId().intValue())))
            .andExpect(jsonPath("$.[*].studentName").value(hasItem(DEFAULT_STUDENT_NAME)))
            .andExpect(jsonPath("$.[*].studentMiddleName").value(hasItem(DEFAULT_STUDENT_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].studentLastName").value(hasItem(DEFAULT_STUDENT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].fatherName").value(hasItem(DEFAULT_FATHER_NAME)))
            .andExpect(jsonPath("$.[*].fatherMiddleName").value(hasItem(DEFAULT_FATHER_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].fatherLastName").value(hasItem(DEFAULT_FATHER_LAST_NAME)))
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
            .andExpect(jsonPath("$.[*].studentContactNumber").value(hasItem(DEFAULT_STUDENT_CONTACT_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].alternateContactNumber").value(hasItem(DEFAULT_ALTERNATE_CONTACT_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].studentEmailAddress").value(hasItem(DEFAULT_STUDENT_EMAIL_ADDRESS)))
            .andExpect(jsonPath("$.[*].alternateEmailAddress").value(hasItem(DEFAULT_ALTERNATE_EMAIL_ADDRESS)))
            .andExpect(jsonPath("$.[*].relationWithStudent").value(hasItem(DEFAULT_RELATION_WITH_STUDENT.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].contactNo").value(hasItem(DEFAULT_CONTACT_NO.intValue())))
            .andExpect(jsonPath("$.[*].emailAddress").value(hasItem(DEFAULT_EMAIL_ADDRESS)))
            .andExpect(jsonPath("$.[*].uploadPhoto").value(hasItem(DEFAULT_UPLOAD_PHOTO.intValue())))
            .andExpect(jsonPath("$.[*].admissionNo").value(hasItem(DEFAULT_ADMISSION_NO.intValue())))
            .andExpect(jsonPath("$.[*].rollNo").value(hasItem(DEFAULT_ROLL_NO)))
            .andExpect(jsonPath("$.[*].studentType").value(hasItem(DEFAULT_STUDENT_TYPE)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Student.class);
        Student student1 = new Student();
        student1.setId(1L);
        Student student2 = new Student();
        student2.setId(student1.getId());
        assertThat(student1).isEqualTo(student2);
        student2.setId(2L);
        assertThat(student1).isNotEqualTo(student2);
        student1.setId(null);
        assertThat(student1).isNotEqualTo(student2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StudentDTO.class);
        StudentDTO studentDTO1 = new StudentDTO();
        studentDTO1.setId(1L);
        StudentDTO studentDTO2 = new StudentDTO();
        assertThat(studentDTO1).isNotEqualTo(studentDTO2);
        studentDTO2.setId(studentDTO1.getId());
        assertThat(studentDTO1).isEqualTo(studentDTO2);
        studentDTO2.setId(2L);
        assertThat(studentDTO1).isNotEqualTo(studentDTO2);
        studentDTO1.setId(null);
        assertThat(studentDTO1).isNotEqualTo(studentDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(studentMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(studentMapper.fromId(null)).isNull();
    }
}
