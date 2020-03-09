package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.Employee;
import com.synectiks.cms.domain.Branch;
import com.synectiks.cms.repository.EmployeeRepository;
import com.synectiks.cms.repository.search.EmployeeSearchRepository;
import com.synectiks.cms.service.EmployeeService;
import com.synectiks.cms.service.dto.EmployeeDTO;
import com.synectiks.cms.service.mapper.EmployeeMapper;
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
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the EmployeeResource REST controller.
 *
 * @see EmployeeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class EmployeeResourceIntTest {

    private static final String DEFAULT_EMPLOYEE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_JOINING_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_JOINING_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_JOB_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_JOB_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_RESIGNATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RESIGNATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_RESIGNATION_ACCEPTANCE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RESIGNATION_ACCEPTANCE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_AADHAR_NO = "AAAAAAAAAA";
    private static final String UPDATED_AADHAR_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PAN_NO = "AAAAAAAAAA";
    private static final String UPDATED_PAN_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PASSPORT_NO = "AAAAAAAAAA";
    private static final String UPDATED_PASSPORT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_CONTACT_NO = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_CONTACT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_CONTACT_NO = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_CONTACT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYEE_FATHER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_FATHER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYEE_MOTHER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_MOTHER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYEE_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PERSONAL_MAIL_ID = "AAAAAAAAAA";
    private static final String UPDATED_PERSONAL_MAIL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_OFFICIAL_MAIL_ID = "AAAAAAAAAA";
    private static final String UPDATED_OFFICIAL_MAIL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DISABILITY = "AAAAAAAAAA";
    private static final String UPDATED_DISABILITY = "BBBBBBBBBB";

    private static final String DEFAULT_DRIVING_LICENCE_NO = "AAAAAAAAAA";
    private static final String UPDATED_DRIVING_LICENCE_NO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DRIVING_LICENCE_VALIDITY = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DRIVING_LICENCE_VALIDITY = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_OF_EMPLOYMENT = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_OF_EMPLOYMENT = "BBBBBBBBBB";

    private static final Long DEFAULT_MANAGER_ID = 1L;
    private static final Long UPDATED_MANAGER_ID = 2L;

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_MARITAL_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_MARITAL_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_STAFF_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_STAFF_TYPE = "BBBBBBBBBB";

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeService employeeService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.EmployeeSearchRepositoryMockConfiguration
     */
    @Autowired
    private EmployeeSearchRepository mockEmployeeSearchRepository;

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

    private MockMvc restEmployeeMockMvc;

    private Employee employee;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EmployeeResource employeeResource = new EmployeeResource(employeeService);
        this.restEmployeeMockMvc = MockMvcBuilders.standaloneSetup(employeeResource)
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
    public static Employee createEntity(EntityManager em) {
        Employee employee = new Employee()
            .employeeName(DEFAULT_EMPLOYEE_NAME)
            .designation(DEFAULT_DESIGNATION)
            .joiningDate(DEFAULT_JOINING_DATE)
            .jobEndDate(DEFAULT_JOB_END_DATE)
            .resignationDate(DEFAULT_RESIGNATION_DATE)
            .resignationAcceptanceDate(DEFAULT_RESIGNATION_ACCEPTANCE_DATE)
            .aadharNo(DEFAULT_AADHAR_NO)
            .panNo(DEFAULT_PAN_NO)
            .passportNo(DEFAULT_PASSPORT_NO)
            .primaryContactNo(DEFAULT_PRIMARY_CONTACT_NO)
            .secondaryContactNo(DEFAULT_SECONDARY_CONTACT_NO)
            .employeeFatherName(DEFAULT_EMPLOYEE_FATHER_NAME)
            .employeeMotherName(DEFAULT_EMPLOYEE_MOTHER_NAME)
            .primaryAddress(DEFAULT_PRIMARY_ADDRESS)
            .secondaryAddress(DEFAULT_SECONDARY_ADDRESS)
            .employeeAddress(DEFAULT_EMPLOYEE_ADDRESS)
            .personalMailId(DEFAULT_PERSONAL_MAIL_ID)
            .officialMailId(DEFAULT_OFFICIAL_MAIL_ID)
            .disability(DEFAULT_DISABILITY)
            .drivingLicenceNo(DEFAULT_DRIVING_LICENCE_NO)
            .drivingLicenceValidity(DEFAULT_DRIVING_LICENCE_VALIDITY)
            .gender(DEFAULT_GENDER)
            .typeOfEmployment(DEFAULT_TYPE_OF_EMPLOYMENT)
            .managerId(DEFAULT_MANAGER_ID)
            .status(DEFAULT_STATUS)
            .maritalStatus(DEFAULT_MARITAL_STATUS)
            .staffType(DEFAULT_STAFF_TYPE);
        // Add required entity
        Branch branch = BranchResourceIntTest.createEntity(em);
        em.persist(branch);
        em.flush();
        employee.setBranch(branch);
        return employee;
    }

    @Before
    public void initTest() {
        employee = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmployee() throws Exception {
        int databaseSizeBeforeCreate = employeeRepository.findAll().size();

        // Create the Employee
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);
        restEmployeeMockMvc.perform(post("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isCreated());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeCreate + 1);
        Employee testEmployee = employeeList.get(employeeList.size() - 1);
        assertThat(testEmployee.getEmployeeName()).isEqualTo(DEFAULT_EMPLOYEE_NAME);
        assertThat(testEmployee.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
        assertThat(testEmployee.getJoiningDate()).isEqualTo(DEFAULT_JOINING_DATE);
        assertThat(testEmployee.getJobEndDate()).isEqualTo(DEFAULT_JOB_END_DATE);
        assertThat(testEmployee.getResignationDate()).isEqualTo(DEFAULT_RESIGNATION_DATE);
        assertThat(testEmployee.getResignationAcceptanceDate()).isEqualTo(DEFAULT_RESIGNATION_ACCEPTANCE_DATE);
        assertThat(testEmployee.getAadharNo()).isEqualTo(DEFAULT_AADHAR_NO);
        assertThat(testEmployee.getPanNo()).isEqualTo(DEFAULT_PAN_NO);
        assertThat(testEmployee.getPassportNo()).isEqualTo(DEFAULT_PASSPORT_NO);
        assertThat(testEmployee.getPrimaryContactNo()).isEqualTo(DEFAULT_PRIMARY_CONTACT_NO);
        assertThat(testEmployee.getSecondaryContactNo()).isEqualTo(DEFAULT_SECONDARY_CONTACT_NO);
        assertThat(testEmployee.getEmployeeFatherName()).isEqualTo(DEFAULT_EMPLOYEE_FATHER_NAME);
        assertThat(testEmployee.getEmployeeMotherName()).isEqualTo(DEFAULT_EMPLOYEE_MOTHER_NAME);
        assertThat(testEmployee.getPrimaryAddress()).isEqualTo(DEFAULT_PRIMARY_ADDRESS);
        assertThat(testEmployee.getSecondaryAddress()).isEqualTo(DEFAULT_SECONDARY_ADDRESS);
        assertThat(testEmployee.getEmployeeAddress()).isEqualTo(DEFAULT_EMPLOYEE_ADDRESS);
        assertThat(testEmployee.getPersonalMailId()).isEqualTo(DEFAULT_PERSONAL_MAIL_ID);
        assertThat(testEmployee.getOfficialMailId()).isEqualTo(DEFAULT_OFFICIAL_MAIL_ID);
        assertThat(testEmployee.getDisability()).isEqualTo(DEFAULT_DISABILITY);
        assertThat(testEmployee.getDrivingLicenceNo()).isEqualTo(DEFAULT_DRIVING_LICENCE_NO);
        assertThat(testEmployee.getDrivingLicenceValidity()).isEqualTo(DEFAULT_DRIVING_LICENCE_VALIDITY);
        assertThat(testEmployee.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testEmployee.getTypeOfEmployment()).isEqualTo(DEFAULT_TYPE_OF_EMPLOYMENT);
        assertThat(testEmployee.getManagerId()).isEqualTo(DEFAULT_MANAGER_ID);
        assertThat(testEmployee.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testEmployee.getMaritalStatus()).isEqualTo(DEFAULT_MARITAL_STATUS);
        assertThat(testEmployee.getStaffType()).isEqualTo(DEFAULT_STAFF_TYPE);

        // Validate the Employee in Elasticsearch
        verify(mockEmployeeSearchRepository, times(1)).save(testEmployee);
    }

    @Test
    @Transactional
    public void createEmployeeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = employeeRepository.findAll().size();

        // Create the Employee with an existing ID
        employee.setId(1L);
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmployeeMockMvc.perform(post("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeCreate);

        // Validate the Employee in Elasticsearch
        verify(mockEmployeeSearchRepository, times(0)).save(employee);
    }

    @Test
    @Transactional
    public void getAllEmployees() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList
        restEmployeeMockMvc.perform(get("/api/employees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(employee.getId().intValue())))
            .andExpect(jsonPath("$.[*].employeeName").value(hasItem(DEFAULT_EMPLOYEE_NAME.toString())))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION.toString())))
            .andExpect(jsonPath("$.[*].joiningDate").value(hasItem(DEFAULT_JOINING_DATE.toString())))
            .andExpect(jsonPath("$.[*].jobEndDate").value(hasItem(DEFAULT_JOB_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].resignationDate").value(hasItem(DEFAULT_RESIGNATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].resignationAcceptanceDate").value(hasItem(DEFAULT_RESIGNATION_ACCEPTANCE_DATE.toString())))
            .andExpect(jsonPath("$.[*].aadharNo").value(hasItem(DEFAULT_AADHAR_NO.toString())))
            .andExpect(jsonPath("$.[*].panNo").value(hasItem(DEFAULT_PAN_NO.toString())))
            .andExpect(jsonPath("$.[*].passportNo").value(hasItem(DEFAULT_PASSPORT_NO.toString())))
            .andExpect(jsonPath("$.[*].primaryContactNo").value(hasItem(DEFAULT_PRIMARY_CONTACT_NO.toString())))
            .andExpect(jsonPath("$.[*].secondaryContactNo").value(hasItem(DEFAULT_SECONDARY_CONTACT_NO.toString())))
            .andExpect(jsonPath("$.[*].employeeFatherName").value(hasItem(DEFAULT_EMPLOYEE_FATHER_NAME.toString())))
            .andExpect(jsonPath("$.[*].employeeMotherName").value(hasItem(DEFAULT_EMPLOYEE_MOTHER_NAME.toString())))
            .andExpect(jsonPath("$.[*].primaryAddress").value(hasItem(DEFAULT_PRIMARY_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].secondaryAddress").value(hasItem(DEFAULT_SECONDARY_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].employeeAddress").value(hasItem(DEFAULT_EMPLOYEE_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].personalMailId").value(hasItem(DEFAULT_PERSONAL_MAIL_ID.toString())))
            .andExpect(jsonPath("$.[*].officialMailId").value(hasItem(DEFAULT_OFFICIAL_MAIL_ID.toString())))
            .andExpect(jsonPath("$.[*].disability").value(hasItem(DEFAULT_DISABILITY.toString())))
            .andExpect(jsonPath("$.[*].drivingLicenceNo").value(hasItem(DEFAULT_DRIVING_LICENCE_NO.toString())))
            .andExpect(jsonPath("$.[*].drivingLicenceValidity").value(hasItem(DEFAULT_DRIVING_LICENCE_VALIDITY.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].typeOfEmployment").value(hasItem(DEFAULT_TYPE_OF_EMPLOYMENT.toString())))
            .andExpect(jsonPath("$.[*].managerId").value(hasItem(DEFAULT_MANAGER_ID.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].maritalStatus").value(hasItem(DEFAULT_MARITAL_STATUS.toString())))
            .andExpect(jsonPath("$.[*].staffType").value(hasItem(DEFAULT_STAFF_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getEmployee() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get the employee
        restEmployeeMockMvc.perform(get("/api/employees/{id}", employee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(employee.getId().intValue()))
            .andExpect(jsonPath("$.employeeName").value(DEFAULT_EMPLOYEE_NAME.toString()))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION.toString()))
            .andExpect(jsonPath("$.joiningDate").value(DEFAULT_JOINING_DATE.toString()))
            .andExpect(jsonPath("$.jobEndDate").value(DEFAULT_JOB_END_DATE.toString()))
            .andExpect(jsonPath("$.resignationDate").value(DEFAULT_RESIGNATION_DATE.toString()))
            .andExpect(jsonPath("$.resignationAcceptanceDate").value(DEFAULT_RESIGNATION_ACCEPTANCE_DATE.toString()))
            .andExpect(jsonPath("$.aadharNo").value(DEFAULT_AADHAR_NO.toString()))
            .andExpect(jsonPath("$.panNo").value(DEFAULT_PAN_NO.toString()))
            .andExpect(jsonPath("$.passportNo").value(DEFAULT_PASSPORT_NO.toString()))
            .andExpect(jsonPath("$.primaryContactNo").value(DEFAULT_PRIMARY_CONTACT_NO.toString()))
            .andExpect(jsonPath("$.secondaryContactNo").value(DEFAULT_SECONDARY_CONTACT_NO.toString()))
            .andExpect(jsonPath("$.employeeFatherName").value(DEFAULT_EMPLOYEE_FATHER_NAME.toString()))
            .andExpect(jsonPath("$.employeeMotherName").value(DEFAULT_EMPLOYEE_MOTHER_NAME.toString()))
            .andExpect(jsonPath("$.primaryAddress").value(DEFAULT_PRIMARY_ADDRESS.toString()))
            .andExpect(jsonPath("$.secondaryAddress").value(DEFAULT_SECONDARY_ADDRESS.toString()))
            .andExpect(jsonPath("$.employeeAddress").value(DEFAULT_EMPLOYEE_ADDRESS.toString()))
            .andExpect(jsonPath("$.personalMailId").value(DEFAULT_PERSONAL_MAIL_ID.toString()))
            .andExpect(jsonPath("$.officialMailId").value(DEFAULT_OFFICIAL_MAIL_ID.toString()))
            .andExpect(jsonPath("$.disability").value(DEFAULT_DISABILITY.toString()))
            .andExpect(jsonPath("$.drivingLicenceNo").value(DEFAULT_DRIVING_LICENCE_NO.toString()))
            .andExpect(jsonPath("$.drivingLicenceValidity").value(DEFAULT_DRIVING_LICENCE_VALIDITY.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.typeOfEmployment").value(DEFAULT_TYPE_OF_EMPLOYMENT.toString()))
            .andExpect(jsonPath("$.managerId").value(DEFAULT_MANAGER_ID.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.maritalStatus").value(DEFAULT_MARITAL_STATUS.toString()))
            .andExpect(jsonPath("$.staffType").value(DEFAULT_STAFF_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEmployee() throws Exception {
        // Get the employee
        restEmployeeMockMvc.perform(get("/api/employees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmployee() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        int databaseSizeBeforeUpdate = employeeRepository.findAll().size();

        // Update the employee
        Employee updatedEmployee = employeeRepository.findById(employee.getId()).get();
        // Disconnect from session so that the updates on updatedEmployee are not directly saved in db
        em.detach(updatedEmployee);
        updatedEmployee
            .employeeName(UPDATED_EMPLOYEE_NAME)
            .designation(UPDATED_DESIGNATION)
            .joiningDate(UPDATED_JOINING_DATE)
            .jobEndDate(UPDATED_JOB_END_DATE)
            .resignationDate(UPDATED_RESIGNATION_DATE)
            .resignationAcceptanceDate(UPDATED_RESIGNATION_ACCEPTANCE_DATE)
            .aadharNo(UPDATED_AADHAR_NO)
            .panNo(UPDATED_PAN_NO)
            .passportNo(UPDATED_PASSPORT_NO)
            .primaryContactNo(UPDATED_PRIMARY_CONTACT_NO)
            .secondaryContactNo(UPDATED_SECONDARY_CONTACT_NO)
            .employeeFatherName(UPDATED_EMPLOYEE_FATHER_NAME)
            .employeeMotherName(UPDATED_EMPLOYEE_MOTHER_NAME)
            .primaryAddress(UPDATED_PRIMARY_ADDRESS)
            .secondaryAddress(UPDATED_SECONDARY_ADDRESS)
            .employeeAddress(UPDATED_EMPLOYEE_ADDRESS)
            .personalMailId(UPDATED_PERSONAL_MAIL_ID)
            .officialMailId(UPDATED_OFFICIAL_MAIL_ID)
            .disability(UPDATED_DISABILITY)
            .drivingLicenceNo(UPDATED_DRIVING_LICENCE_NO)
            .drivingLicenceValidity(UPDATED_DRIVING_LICENCE_VALIDITY)
            .gender(UPDATED_GENDER)
            .typeOfEmployment(UPDATED_TYPE_OF_EMPLOYMENT)
            .managerId(UPDATED_MANAGER_ID)
            .status(UPDATED_STATUS)
            .maritalStatus(UPDATED_MARITAL_STATUS)
            .staffType(UPDATED_STAFF_TYPE);
        EmployeeDTO employeeDTO = employeeMapper.toDto(updatedEmployee);

        restEmployeeMockMvc.perform(put("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isOk());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeUpdate);
        Employee testEmployee = employeeList.get(employeeList.size() - 1);
        assertThat(testEmployee.getEmployeeName()).isEqualTo(UPDATED_EMPLOYEE_NAME);
        assertThat(testEmployee.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
        assertThat(testEmployee.getJoiningDate()).isEqualTo(UPDATED_JOINING_DATE);
        assertThat(testEmployee.getJobEndDate()).isEqualTo(UPDATED_JOB_END_DATE);
        assertThat(testEmployee.getResignationDate()).isEqualTo(UPDATED_RESIGNATION_DATE);
        assertThat(testEmployee.getResignationAcceptanceDate()).isEqualTo(UPDATED_RESIGNATION_ACCEPTANCE_DATE);
        assertThat(testEmployee.getAadharNo()).isEqualTo(UPDATED_AADHAR_NO);
        assertThat(testEmployee.getPanNo()).isEqualTo(UPDATED_PAN_NO);
        assertThat(testEmployee.getPassportNo()).isEqualTo(UPDATED_PASSPORT_NO);
        assertThat(testEmployee.getPrimaryContactNo()).isEqualTo(UPDATED_PRIMARY_CONTACT_NO);
        assertThat(testEmployee.getSecondaryContactNo()).isEqualTo(UPDATED_SECONDARY_CONTACT_NO);
        assertThat(testEmployee.getEmployeeFatherName()).isEqualTo(UPDATED_EMPLOYEE_FATHER_NAME);
        assertThat(testEmployee.getEmployeeMotherName()).isEqualTo(UPDATED_EMPLOYEE_MOTHER_NAME);
        assertThat(testEmployee.getPrimaryAddress()).isEqualTo(UPDATED_PRIMARY_ADDRESS);
        assertThat(testEmployee.getSecondaryAddress()).isEqualTo(UPDATED_SECONDARY_ADDRESS);
        assertThat(testEmployee.getEmployeeAddress()).isEqualTo(UPDATED_EMPLOYEE_ADDRESS);
        assertThat(testEmployee.getPersonalMailId()).isEqualTo(UPDATED_PERSONAL_MAIL_ID);
        assertThat(testEmployee.getOfficialMailId()).isEqualTo(UPDATED_OFFICIAL_MAIL_ID);
        assertThat(testEmployee.getDisability()).isEqualTo(UPDATED_DISABILITY);
        assertThat(testEmployee.getDrivingLicenceNo()).isEqualTo(UPDATED_DRIVING_LICENCE_NO);
        assertThat(testEmployee.getDrivingLicenceValidity()).isEqualTo(UPDATED_DRIVING_LICENCE_VALIDITY);
        assertThat(testEmployee.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testEmployee.getTypeOfEmployment()).isEqualTo(UPDATED_TYPE_OF_EMPLOYMENT);
        assertThat(testEmployee.getManagerId()).isEqualTo(UPDATED_MANAGER_ID);
        assertThat(testEmployee.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEmployee.getMaritalStatus()).isEqualTo(UPDATED_MARITAL_STATUS);
        assertThat(testEmployee.getStaffType()).isEqualTo(UPDATED_STAFF_TYPE);

        // Validate the Employee in Elasticsearch
        verify(mockEmployeeSearchRepository, times(1)).save(testEmployee);
    }

    @Test
    @Transactional
    public void updateNonExistingEmployee() throws Exception {
        int databaseSizeBeforeUpdate = employeeRepository.findAll().size();

        // Create the Employee
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmployeeMockMvc.perform(put("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Employee in Elasticsearch
        verify(mockEmployeeSearchRepository, times(0)).save(employee);
    }

    @Test
    @Transactional
    public void deleteEmployee() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        int databaseSizeBeforeDelete = employeeRepository.findAll().size();

        // Delete the employee
        restEmployeeMockMvc.perform(delete("/api/employees/{id}", employee.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Employee in Elasticsearch
        verify(mockEmployeeSearchRepository, times(1)).deleteById(employee.getId());
    }

    @Test
    @Transactional
    public void searchEmployee() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);
        when(mockEmployeeSearchRepository.search(queryStringQuery("id:" + employee.getId())))
            .thenReturn(Collections.singletonList(employee));
        // Search the employee
        restEmployeeMockMvc.perform(get("/api/_search/employees?query=id:" + employee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(employee.getId().intValue())))
            .andExpect(jsonPath("$.[*].employeeName").value(hasItem(DEFAULT_EMPLOYEE_NAME)))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION)))
            .andExpect(jsonPath("$.[*].joiningDate").value(hasItem(DEFAULT_JOINING_DATE.toString())))
            .andExpect(jsonPath("$.[*].jobEndDate").value(hasItem(DEFAULT_JOB_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].resignationDate").value(hasItem(DEFAULT_RESIGNATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].resignationAcceptanceDate").value(hasItem(DEFAULT_RESIGNATION_ACCEPTANCE_DATE.toString())))
            .andExpect(jsonPath("$.[*].aadharNo").value(hasItem(DEFAULT_AADHAR_NO)))
            .andExpect(jsonPath("$.[*].panNo").value(hasItem(DEFAULT_PAN_NO)))
            .andExpect(jsonPath("$.[*].passportNo").value(hasItem(DEFAULT_PASSPORT_NO)))
            .andExpect(jsonPath("$.[*].primaryContactNo").value(hasItem(DEFAULT_PRIMARY_CONTACT_NO)))
            .andExpect(jsonPath("$.[*].secondaryContactNo").value(hasItem(DEFAULT_SECONDARY_CONTACT_NO)))
            .andExpect(jsonPath("$.[*].employeeFatherName").value(hasItem(DEFAULT_EMPLOYEE_FATHER_NAME)))
            .andExpect(jsonPath("$.[*].employeeMotherName").value(hasItem(DEFAULT_EMPLOYEE_MOTHER_NAME)))
            .andExpect(jsonPath("$.[*].primaryAddress").value(hasItem(DEFAULT_PRIMARY_ADDRESS)))
            .andExpect(jsonPath("$.[*].secondaryAddress").value(hasItem(DEFAULT_SECONDARY_ADDRESS)))
            .andExpect(jsonPath("$.[*].employeeAddress").value(hasItem(DEFAULT_EMPLOYEE_ADDRESS)))
            .andExpect(jsonPath("$.[*].personalMailId").value(hasItem(DEFAULT_PERSONAL_MAIL_ID)))
            .andExpect(jsonPath("$.[*].officialMailId").value(hasItem(DEFAULT_OFFICIAL_MAIL_ID)))
            .andExpect(jsonPath("$.[*].disability").value(hasItem(DEFAULT_DISABILITY)))
            .andExpect(jsonPath("$.[*].drivingLicenceNo").value(hasItem(DEFAULT_DRIVING_LICENCE_NO)))
            .andExpect(jsonPath("$.[*].drivingLicenceValidity").value(hasItem(DEFAULT_DRIVING_LICENCE_VALIDITY.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER)))
            .andExpect(jsonPath("$.[*].typeOfEmployment").value(hasItem(DEFAULT_TYPE_OF_EMPLOYMENT)))
            .andExpect(jsonPath("$.[*].managerId").value(hasItem(DEFAULT_MANAGER_ID.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].maritalStatus").value(hasItem(DEFAULT_MARITAL_STATUS)))
            .andExpect(jsonPath("$.[*].staffType").value(hasItem(DEFAULT_STAFF_TYPE)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Employee.class);
        Employee employee1 = new Employee();
        employee1.setId(1L);
        Employee employee2 = new Employee();
        employee2.setId(employee1.getId());
        assertThat(employee1).isEqualTo(employee2);
        employee2.setId(2L);
        assertThat(employee1).isNotEqualTo(employee2);
        employee1.setId(null);
        assertThat(employee1).isNotEqualTo(employee2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmployeeDTO.class);
        EmployeeDTO employeeDTO1 = new EmployeeDTO();
        employeeDTO1.setId(1L);
        EmployeeDTO employeeDTO2 = new EmployeeDTO();
        assertThat(employeeDTO1).isNotEqualTo(employeeDTO2);
        employeeDTO2.setId(employeeDTO1.getId());
        assertThat(employeeDTO1).isEqualTo(employeeDTO2);
        employeeDTO2.setId(2L);
        assertThat(employeeDTO1).isNotEqualTo(employeeDTO2);
        employeeDTO1.setId(null);
        assertThat(employeeDTO1).isNotEqualTo(employeeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(employeeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(employeeMapper.fromId(null)).isNull();
    }
}
