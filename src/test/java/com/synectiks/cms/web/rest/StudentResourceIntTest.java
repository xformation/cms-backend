package com.synectiks.cms.web.rest;

import static com.synectiks.cms.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import javax.persistence.EntityManager;

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

import com.synectiks.cms.CmsApp;
import com.synectiks.cms.domain.Batch;
import com.synectiks.cms.domain.Branch;
import com.synectiks.cms.domain.Department;
import com.synectiks.cms.domain.Section;
import com.synectiks.cms.domain.Student;
import com.synectiks.cms.domain.enumeration.Bloodgroup;
import com.synectiks.cms.domain.enumeration.Caste;
import com.synectiks.cms.domain.enumeration.Gender;
import com.synectiks.cms.domain.enumeration.RelationWithStudentEnum;
import com.synectiks.cms.domain.enumeration.Religion;
import com.synectiks.cms.domain.enumeration.StudentTypeEnum;
import com.synectiks.cms.repository.StudentRepository;
import com.synectiks.cms.service.StudentService;
import com.synectiks.cms.service.dto.StudentDTO;
import com.synectiks.cms.service.mapper.StudentMapper;
import com.synectiks.cms.web.rest.errors.ExceptionTranslator;
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

    private static final String DEFAULT_AADHAR_NO = "1L";
    private static final String UPDATED_AADHAR_NO = "2L";

    private static final LocalDate DEFAULT_DATE_OF_BIRTH =  LocalDate.ofEpochDay(0L);
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

    private static final String DEFAULT_STUDENT_CONTACT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_CONTACT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ALTERNATE_CONTACT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ALTERNATE_CONTACT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_STUDENT_EMAIL_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_EMAIL_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_ALTERNATE_EMAIL_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ALTERNATE_EMAIL_ADDRESS = "BBBBBBBBBB";

    private static final RelationWithStudentEnum DEFAULT_RELATION_WITH_STUDENT = RelationWithStudentEnum.FATHER;
    private static final RelationWithStudentEnum UPDATED_RELATION_WITH_STUDENT = RelationWithStudentEnum.MOTHER;

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

    private static final Long DEFAULT_ADMISSION_NO = 1L;
    private static final Long UPDATED_ADMISSION_NO = 2L;

    private static final String DEFAULT_ROLL_NO = "AAAAAAAAAA";
    private static final String UPDATED_ROLL_NO = "BBBBBBBBBB";

    private static final StudentTypeEnum DEFAULT_STUDENT_TYPE = StudentTypeEnum.REGULAR;
    private static final StudentTypeEnum UPDATED_STUDENT_TYPE = StudentTypeEnum.STAFF_CONCESSION;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentService studentService;
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
        Student student = new Student()
            .studentName(DEFAULT_STUDENT_NAME)
            .studentMiddleName(DEFAULT_STUDENT_MIDDLE_NAME)
            .studentLastName(DEFAULT_STUDENT_LAST_NAME)
            .fatherName(DEFAULT_FATHER_NAME)
            .fatherMiddleName(DEFAULT_FATHER_MIDDLE_NAME)
            .fatherLastName(DEFAULT_FATHER_LAST_NAME)
            .motherName(DEFAULT_MOTHER_NAME)
            .motherMiddleName(DEFAULT_MOTHER_MIDDLE_NAME)
            .motherLastName(DEFAULT_MOTHER_LAST_NAME)
            .studentAadharNo(DEFAULT_AADHAR_NO)
            .dateOfBirth(DEFAULT_DATE_OF_BIRTH)
            .placeOfBirth(DEFAULT_PLACE_OF_BIRTH)
            .religion(DEFAULT_RELIGION)
            .caste(DEFAULT_CASTE)
            .subCaste(DEFAULT_SUB_CASTE)
            .age(DEFAULT_AGE)
            .sex(DEFAULT_SEX)
            .bloodGroup(DEFAULT_BLOOD_GROUP)
            .state(DEFAULT_STATE)
            .country(DEFAULT_COUNTRY)
            .relationWithStudent(DEFAULT_RELATION_WITH_STUDENT)
            .emergencyContactName(DEFAULT_EMERGENCY_CONTACT_NAME)
            .emergencyContactMiddleName(DEFAULT_EMERGENCY_CONTACT_MIDDLE_NAME)
            .emergencyContactLastName(DEFAULT_EMERGENCY_CONTACT_LAST_NAME)
            .rollNo(DEFAULT_ROLL_NO)
            .studentType(DEFAULT_STUDENT_TYPE);
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
        assertThat(testStudent.getDateOfBirth()).isEqualTo(DEFAULT_DATE_OF_BIRTH);
        assertThat(testStudent.getPlaceOfBirth()).isEqualTo(DEFAULT_PLACE_OF_BIRTH);
        assertThat(testStudent.getReligion()).isEqualTo(DEFAULT_RELIGION);
        assertThat(testStudent.getCaste()).isEqualTo(DEFAULT_CASTE);
        assertThat(testStudent.getSubCaste()).isEqualTo(DEFAULT_SUB_CASTE);
        assertThat(testStudent.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testStudent.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testStudent.getBloodGroup()).isEqualTo(DEFAULT_BLOOD_GROUP);
        assertThat(testStudent.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testStudent.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testStudent.getRelationWithStudent()).isEqualTo(DEFAULT_RELATION_WITH_STUDENT);
        assertThat(testStudent.getEmergencyContactName()).isEqualTo(DEFAULT_EMERGENCY_CONTACT_NAME);
        assertThat(testStudent.getEmergencyContactMiddleName()).isEqualTo(DEFAULT_EMERGENCY_CONTACT_MIDDLE_NAME);
        assertThat(testStudent.getEmergencyContactLastName()).isEqualTo(DEFAULT_EMERGENCY_CONTACT_LAST_NAME);
        assertThat(testStudent.getAdmissionNo()).isEqualTo(DEFAULT_ADMISSION_NO);
        assertThat(testStudent.getRollNo()).isEqualTo(DEFAULT_ROLL_NO);
        assertThat(testStudent.getStudentType()).isEqualTo(DEFAULT_STUDENT_TYPE);    }

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
    public void checkCityIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setCity(null);

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
    public void checkEmergencyContactNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setEmergencyContactName(null);

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
    public void checkEmergencyContactMiddleNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setEmergencyContactMiddleName(null);

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
    public void checkEmergencyContactLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setEmergencyContactLastName(null);

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
            .andExpect(jsonPath("$.[*].studentContactNumber").value(hasItem(DEFAULT_STUDENT_CONTACT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].alternateContactNumber").value(hasItem(DEFAULT_ALTERNATE_CONTACT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].studentEmailAddress").value(hasItem(DEFAULT_STUDENT_EMAIL_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].alternateEmailAddress").value(hasItem(DEFAULT_ALTERNATE_EMAIL_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].relationWithStudent").value(hasItem(DEFAULT_RELATION_WITH_STUDENT.toString())))
            .andExpect(jsonPath("$.[*].emergencyContactName").value(hasItem(DEFAULT_EMERGENCY_CONTACT_NAME.toString())))
            .andExpect(jsonPath("$.[*].emergencyContactMiddleName").value(hasItem(DEFAULT_EMERGENCY_CONTACT_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].emergencyContactLastName").value(hasItem(DEFAULT_EMERGENCY_CONTACT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].emergencyContactNo").value(hasItem(DEFAULT_EMERGENCY_CONTACT_NO.toString())))
            .andExpect(jsonPath("$.[*].emergencyContactEmailAddress").value(hasItem(DEFAULT_EMERGENCY_CONTACT_EMAIL_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].uploadPhoto").value(hasItem(DEFAULT_UPLOAD_PHOTO.toString())))
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
            .andExpect(jsonPath("$.studentContactNumber").value(DEFAULT_STUDENT_CONTACT_NUMBER.toString()))
            .andExpect(jsonPath("$.alternateContactNumber").value(DEFAULT_ALTERNATE_CONTACT_NUMBER.toString()))
            .andExpect(jsonPath("$.studentEmailAddress").value(DEFAULT_STUDENT_EMAIL_ADDRESS.toString()))
            .andExpect(jsonPath("$.alternateEmailAddress").value(DEFAULT_ALTERNATE_EMAIL_ADDRESS.toString()))
            .andExpect(jsonPath("$.relationWithStudent").value(DEFAULT_RELATION_WITH_STUDENT.toString()))
            .andExpect(jsonPath("$.emergencyContactName").value(DEFAULT_EMERGENCY_CONTACT_NAME.toString()))
            .andExpect(jsonPath("$.emergencyContactMiddleName").value(DEFAULT_EMERGENCY_CONTACT_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.emergencyContactLastName").value(DEFAULT_EMERGENCY_CONTACT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.emergencyContactNo").value(DEFAULT_EMERGENCY_CONTACT_NO.toString()))
            .andExpect(jsonPath("$.emergencyContactEmailAddress").value(DEFAULT_EMERGENCY_CONTACT_EMAIL_ADDRESS.toString()))
            .andExpect(jsonPath("$.uploadPhoto").value(DEFAULT_UPLOAD_PHOTO.toString()))
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
        updatedStudent
            .studentName(UPDATED_STUDENT_NAME)
            .studentMiddleName(UPDATED_STUDENT_MIDDLE_NAME)
            .studentLastName(UPDATED_STUDENT_LAST_NAME)
            .fatherName(UPDATED_FATHER_NAME)
            .fatherMiddleName(UPDATED_FATHER_MIDDLE_NAME)
            .fatherLastName(UPDATED_FATHER_LAST_NAME)
            .motherName(UPDATED_MOTHER_NAME)
            .motherMiddleName(UPDATED_MOTHER_MIDDLE_NAME)
            .motherLastName(UPDATED_MOTHER_LAST_NAME)
            .studentAadharNo(UPDATED_AADHAR_NO)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .placeOfBirth(UPDATED_PLACE_OF_BIRTH)
            .religion(UPDATED_RELIGION)
            .caste(UPDATED_CASTE)
            .subCaste(UPDATED_SUB_CASTE)
            .age(UPDATED_AGE)
            .sex(UPDATED_SEX)
            .bloodGroup(UPDATED_BLOOD_GROUP)
            .state(UPDATED_STATE)
            .country(UPDATED_COUNTRY)
            .relationWithStudent(UPDATED_RELATION_WITH_STUDENT)
            .emergencyContactName(UPDATED_EMERGENCY_CONTACT_NAME)
            .emergencyContactMiddleName(UPDATED_EMERGENCY_CONTACT_MIDDLE_NAME)
            .emergencyContactLastName(UPDATED_EMERGENCY_CONTACT_LAST_NAME)
            .rollNo(UPDATED_ROLL_NO)
            .studentType(UPDATED_STUDENT_TYPE);
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
        assertThat(testStudent.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
        assertThat(testStudent.getPlaceOfBirth()).isEqualTo(UPDATED_PLACE_OF_BIRTH);
        assertThat(testStudent.getReligion()).isEqualTo(UPDATED_RELIGION);
        assertThat(testStudent.getCaste()).isEqualTo(UPDATED_CASTE);
        assertThat(testStudent.getSubCaste()).isEqualTo(UPDATED_SUB_CASTE);
        assertThat(testStudent.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testStudent.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testStudent.getBloodGroup()).isEqualTo(UPDATED_BLOOD_GROUP);
        assertThat(testStudent.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testStudent.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testStudent.getRelationWithStudent()).isEqualTo(UPDATED_RELATION_WITH_STUDENT);
        assertThat(testStudent.getEmergencyContactName()).isEqualTo(UPDATED_EMERGENCY_CONTACT_NAME);
        assertThat(testStudent.getEmergencyContactMiddleName()).isEqualTo(UPDATED_EMERGENCY_CONTACT_MIDDLE_NAME);
        assertThat(testStudent.getEmergencyContactLastName()).isEqualTo(UPDATED_EMERGENCY_CONTACT_LAST_NAME);
        assertThat(testStudent.getAdmissionNo()).isEqualTo(UPDATED_ADMISSION_NO);
        assertThat(testStudent.getRollNo()).isEqualTo(UPDATED_ROLL_NO);
        assertThat(testStudent.getStudentType()).isEqualTo(UPDATED_STUDENT_TYPE);
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
    }

    @Test
    @Transactional
    public void deleteStudent() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        int databaseSizeBeforeDelete = studentRepository.findAll().size();

        // Delete the student
        restStudentMockMvc.perform(delete("/api/students/{id}", student.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeDelete - 1);    }

    @Test
    @Transactional
    public void searchStudent() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);
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
            .andExpect(jsonPath("$.[*].studentContactNumber").value(hasItem(DEFAULT_STUDENT_CONTACT_NUMBER)))
            .andExpect(jsonPath("$.[*].alternateContactNumber").value(hasItem(DEFAULT_ALTERNATE_CONTACT_NUMBER)))
            .andExpect(jsonPath("$.[*].studentEmailAddress").value(hasItem(DEFAULT_STUDENT_EMAIL_ADDRESS)))
            .andExpect(jsonPath("$.[*].alternateEmailAddress").value(hasItem(DEFAULT_ALTERNATE_EMAIL_ADDRESS)))
            .andExpect(jsonPath("$.[*].relationWithStudent").value(hasItem(DEFAULT_RELATION_WITH_STUDENT.toString())))
            .andExpect(jsonPath("$.[*].emergencyContactName").value(hasItem(DEFAULT_EMERGENCY_CONTACT_NAME)))
            .andExpect(jsonPath("$.[*].emergencyContactMiddleName").value(hasItem(DEFAULT_EMERGENCY_CONTACT_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].emergencyContactLastName").value(hasItem(DEFAULT_EMERGENCY_CONTACT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].emergencyContactNo").value(hasItem(DEFAULT_EMERGENCY_CONTACT_NO)))
            .andExpect(jsonPath("$.[*].emergencyContactEmailAddress").value(hasItem(DEFAULT_EMERGENCY_CONTACT_EMAIL_ADDRESS)))
            .andExpect(jsonPath("$.[*].uploadPhoto").value(hasItem(DEFAULT_UPLOAD_PHOTO)))
            .andExpect(jsonPath("$.[*].admissionNo").value(hasItem(DEFAULT_ADMISSION_NO.intValue())))
            .andExpect(jsonPath("$.[*].rollNo").value(hasItem(DEFAULT_ROLL_NO)))
            .andExpect(jsonPath("$.[*].studentType").value(hasItem(DEFAULT_STUDENT_TYPE.toString())));
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
