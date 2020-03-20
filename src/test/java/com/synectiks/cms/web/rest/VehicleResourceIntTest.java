package com.synectiks.cms.web.rest;

import static com.synectiks.cms.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import org.springframework.validation.Validator;

import com.synectiks.cms.CmsApp;
import com.synectiks.cms.domain.Vehicle;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.repository.VehicleRepository;
import com.synectiks.cms.repository.search.VehicleSearchRepository;
import com.synectiks.cms.service.VehicleService;
import com.synectiks.cms.service.dto.VehicleDTO;
import com.synectiks.cms.service.mapper.VehicleMapper;
import com.synectiks.cms.web.rest.errors.ExceptionTranslator;
/**
 * Test class for the VehicleResource REST controller.
 *
 * @see VehicleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class VehicleResourceIntTest {

    private static final String DEFAULT_VEHICLE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_VEHICLE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_VEHICLE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_VEHICLE_TYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_CAPACITY = 1L;
    private static final Long UPDATED_CAPACITY = 2L;

    private static final String DEFAULT_OWNER_SHIP = "AAAAAAAAAA";
    private static final String UPDATED_OWNER_SHIP = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_OF_REGISTRATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_OF_REGISTRATION = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_YEAR_OF_MANUFACTURING = "AAAAAAAAAA";
    private static final String UPDATED_YEAR_OF_MANUFACTURING = "BBBBBBBBBB";

    private static final String DEFAULT_MANUFACTURING_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_MANUFACTURING_COMPANY = "BBBBBBBBBB";

    private static final String DEFAULT_MODEL = "AAAAAAAAAA";
    private static final String UPDATED_MODEL = "BBBBBBBBBB";

    private static final String DEFAULT_CHASIS_NO = "AAAAAAAAAA";
    private static final String UPDATED_CHASIS_NO = "BBBBBBBBBB";

    private static final String DEFAULT_RC_NO = "AAAAAAAAAA";
    private static final String UPDATED_RC_NO = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_NUMBER = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.DEACTIVE;

    private static final Long DEFAULT_COLLEGE_ID = 1L;
    private static final Long UPDATED_COLLEGE_ID = 2L;

    private static final Long DEFAULT_BRANCH_ID = 1L;
    private static final Long UPDATED_BRANCH_ID = 2L;

    private static final Long DEFAULT_EMPLOYEE_ID = 1L;
    private static final Long UPDATED_EMPLOYEE_ID = 2L;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleMapper vehicleMapper;

    @Autowired
    private VehicleService vehicleService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.VehicleSearchRepositoryMockConfiguration
     */
    @Autowired
    private VehicleSearchRepository mockVehicleSearchRepository;

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

    private MockMvc restVehicleMockMvc;

    private Vehicle vehicle;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VehicleResource vehicleResource = new VehicleResource(vehicleService);
        this.restVehicleMockMvc = MockMvcBuilders.standaloneSetup(vehicleResource)
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
    public static Vehicle createEntity(EntityManager em) {
        Vehicle vehicle = new Vehicle()
            .vehicleNumber(DEFAULT_VEHICLE_NUMBER)
            .vehicleType(DEFAULT_VEHICLE_TYPE)
            .capacity(DEFAULT_CAPACITY)
            .ownerShip(DEFAULT_OWNER_SHIP)
            .dateOfRegistration(DEFAULT_DATE_OF_REGISTRATION)
            .yearOfManufacturing(DEFAULT_YEAR_OF_MANUFACTURING)
            .manufacturingCompany(DEFAULT_MANUFACTURING_COMPANY)
            .model(DEFAULT_MODEL)
            .chasisNo(DEFAULT_CHASIS_NO)
            .rcNo(DEFAULT_RC_NO)
            .contactNumber(DEFAULT_CONTACT_NUMBER)
            .status(DEFAULT_STATUS)
            .collegeId(DEFAULT_COLLEGE_ID)
            .branchId(DEFAULT_BRANCH_ID)
            .employeeId(DEFAULT_EMPLOYEE_ID);
        return vehicle;
    }

    @Before
    public void initTest() {
        vehicle = createEntity(em);
    }

    @Test
    @Transactional
    public void createVehicle() throws Exception {
        int databaseSizeBeforeCreate = vehicleRepository.findAll().size();

        // Create the Vehicle
        VehicleDTO vehicleDTO = vehicleMapper.toDto(vehicle);
        restVehicleMockMvc.perform(post("/api/vehicles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vehicleDTO)))
            .andExpect(status().isCreated());

        // Validate the Vehicle in the database
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        assertThat(vehicleList).hasSize(databaseSizeBeforeCreate + 1);
        Vehicle testVehicle = vehicleList.get(vehicleList.size() - 1);
        assertThat(testVehicle.getVehicleNumber()).isEqualTo(DEFAULT_VEHICLE_NUMBER);
        assertThat(testVehicle.getVehicleType()).isEqualTo(DEFAULT_VEHICLE_TYPE);
        assertThat(testVehicle.getCapacity()).isEqualTo(DEFAULT_CAPACITY);
        assertThat(testVehicle.getOwnerShip()).isEqualTo(DEFAULT_OWNER_SHIP);
        assertThat(testVehicle.getDateOfRegistration()).isEqualTo(DEFAULT_DATE_OF_REGISTRATION);
        assertThat(testVehicle.getYearOfManufacturing()).isEqualTo(DEFAULT_YEAR_OF_MANUFACTURING);
        assertThat(testVehicle.getManufacturingCompany()).isEqualTo(DEFAULT_MANUFACTURING_COMPANY);
        assertThat(testVehicle.getModel()).isEqualTo(DEFAULT_MODEL);
        assertThat(testVehicle.getChasisNo()).isEqualTo(DEFAULT_CHASIS_NO);
        assertThat(testVehicle.getRcNo()).isEqualTo(DEFAULT_RC_NO);
        assertThat(testVehicle.getContactNumber()).isEqualTo(DEFAULT_CONTACT_NUMBER);
        assertThat(testVehicle.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testVehicle.getCollegeId()).isEqualTo(DEFAULT_COLLEGE_ID);
        assertThat(testVehicle.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
        assertThat(testVehicle.getEmployeeId()).isEqualTo(DEFAULT_EMPLOYEE_ID);

        // Validate the Vehicle in Elasticsearch
        verify(mockVehicleSearchRepository, times(1)).save(testVehicle);
    }

    @Test
    @Transactional
    public void createVehicleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vehicleRepository.findAll().size();

        // Create the Vehicle with an existing ID
        vehicle.setId(1L);
        VehicleDTO vehicleDTO = vehicleMapper.toDto(vehicle);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVehicleMockMvc.perform(post("/api/vehicles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vehicleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Vehicle in the database
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        assertThat(vehicleList).hasSize(databaseSizeBeforeCreate);

        // Validate the Vehicle in Elasticsearch
        verify(mockVehicleSearchRepository, times(0)).save(vehicle);
    }

    @Test
    @Transactional
    public void getAllVehicles() throws Exception {
        // Initialize the database
        vehicleRepository.saveAndFlush(vehicle);

        // Get all the vehicleList
        restVehicleMockMvc.perform(get("/api/vehicles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vehicle.getId().intValue())))
            .andExpect(jsonPath("$.[*].vehicleNumber").value(hasItem(DEFAULT_VEHICLE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].vehicleType").value(hasItem(DEFAULT_VEHICLE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].capacity").value(hasItem(DEFAULT_CAPACITY.intValue())))
            .andExpect(jsonPath("$.[*].ownerShip").value(hasItem(DEFAULT_OWNER_SHIP.toString())))
            .andExpect(jsonPath("$.[*].dateOfRegistration").value(hasItem(DEFAULT_DATE_OF_REGISTRATION.toString())))
            .andExpect(jsonPath("$.[*].yearOfManufacturing").value(hasItem(DEFAULT_YEAR_OF_MANUFACTURING.toString())))
            .andExpect(jsonPath("$.[*].manufacturingCompany").value(hasItem(DEFAULT_MANUFACTURING_COMPANY.toString())))
            .andExpect(jsonPath("$.[*].model").value(hasItem(DEFAULT_MODEL.toString())))
            .andExpect(jsonPath("$.[*].chasisNo").value(hasItem(DEFAULT_CHASIS_NO.toString())))
            .andExpect(jsonPath("$.[*].rcNo").value(hasItem(DEFAULT_RC_NO.toString())))
            .andExpect(jsonPath("$.[*].contactNumber").value(hasItem(DEFAULT_CONTACT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].collegeId").value(hasItem(DEFAULT_COLLEGE_ID.intValue())))
            .andExpect(jsonPath("$.[*].branchId").value(hasItem(DEFAULT_BRANCH_ID.intValue())))
            .andExpect(jsonPath("$.[*].employeeId").value(hasItem(DEFAULT_EMPLOYEE_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getVehicle() throws Exception {
        // Initialize the database
        vehicleRepository.saveAndFlush(vehicle);

        // Get the vehicle
        restVehicleMockMvc.perform(get("/api/vehicles/{id}", vehicle.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(vehicle.getId().intValue()))
            .andExpect(jsonPath("$.vehicleNumber").value(DEFAULT_VEHICLE_NUMBER.toString()))
            .andExpect(jsonPath("$.vehicleType").value(DEFAULT_VEHICLE_TYPE.toString()))
            .andExpect(jsonPath("$.capacity").value(DEFAULT_CAPACITY.intValue()))
            .andExpect(jsonPath("$.ownerShip").value(DEFAULT_OWNER_SHIP.toString()))
            .andExpect(jsonPath("$.dateOfRegistration").value(DEFAULT_DATE_OF_REGISTRATION.toString()))
            .andExpect(jsonPath("$.yearOfManufacturing").value(DEFAULT_YEAR_OF_MANUFACTURING.toString()))
            .andExpect(jsonPath("$.manufacturingCompany").value(DEFAULT_MANUFACTURING_COMPANY.toString()))
            .andExpect(jsonPath("$.model").value(DEFAULT_MODEL.toString()))
            .andExpect(jsonPath("$.chasisNo").value(DEFAULT_CHASIS_NO.toString()))
            .andExpect(jsonPath("$.rcNo").value(DEFAULT_RC_NO.toString()))
            .andExpect(jsonPath("$.contactNumber").value(DEFAULT_CONTACT_NUMBER.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.collegeId").value(DEFAULT_COLLEGE_ID.intValue()))
            .andExpect(jsonPath("$.branchId").value(DEFAULT_BRANCH_ID.intValue()))
            .andExpect(jsonPath("$.employeeId").value(DEFAULT_EMPLOYEE_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingVehicle() throws Exception {
        // Get the vehicle
        restVehicleMockMvc.perform(get("/api/vehicles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVehicle() throws Exception {
        // Initialize the database
        vehicleRepository.saveAndFlush(vehicle);

        int databaseSizeBeforeUpdate = vehicleRepository.findAll().size();

        // Update the vehicle
        Vehicle updatedVehicle = vehicleRepository.findById(vehicle.getId()).get();
        // Disconnect from session so that the updates on updatedVehicle are not directly saved in db
        em.detach(updatedVehicle);
        updatedVehicle
            .vehicleNumber(UPDATED_VEHICLE_NUMBER)
            .vehicleType(UPDATED_VEHICLE_TYPE)
            .capacity(UPDATED_CAPACITY)
            .ownerShip(UPDATED_OWNER_SHIP)
            .dateOfRegistration(UPDATED_DATE_OF_REGISTRATION)
            .yearOfManufacturing(UPDATED_YEAR_OF_MANUFACTURING)
            .manufacturingCompany(UPDATED_MANUFACTURING_COMPANY)
            .model(UPDATED_MODEL)
            .chasisNo(UPDATED_CHASIS_NO)
            .rcNo(UPDATED_RC_NO)
            .contactNumber(UPDATED_CONTACT_NUMBER)
            .status(UPDATED_STATUS)
            .collegeId(UPDATED_COLLEGE_ID)
            .branchId(UPDATED_BRANCH_ID)
            .employeeId(UPDATED_EMPLOYEE_ID);
        VehicleDTO vehicleDTO = vehicleMapper.toDto(updatedVehicle);

        restVehicleMockMvc.perform(put("/api/vehicles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vehicleDTO)))
            .andExpect(status().isOk());

        // Validate the Vehicle in the database
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        assertThat(vehicleList).hasSize(databaseSizeBeforeUpdate);
        Vehicle testVehicle = vehicleList.get(vehicleList.size() - 1);
        assertThat(testVehicle.getVehicleNumber()).isEqualTo(UPDATED_VEHICLE_NUMBER);
        assertThat(testVehicle.getVehicleType()).isEqualTo(UPDATED_VEHICLE_TYPE);
        assertThat(testVehicle.getCapacity()).isEqualTo(UPDATED_CAPACITY);
        assertThat(testVehicle.getOwnerShip()).isEqualTo(UPDATED_OWNER_SHIP);
        assertThat(testVehicle.getDateOfRegistration()).isEqualTo(UPDATED_DATE_OF_REGISTRATION);
        assertThat(testVehicle.getYearOfManufacturing()).isEqualTo(UPDATED_YEAR_OF_MANUFACTURING);
        assertThat(testVehicle.getManufacturingCompany()).isEqualTo(UPDATED_MANUFACTURING_COMPANY);
        assertThat(testVehicle.getModel()).isEqualTo(UPDATED_MODEL);
        assertThat(testVehicle.getChasisNo()).isEqualTo(UPDATED_CHASIS_NO);
        assertThat(testVehicle.getRcNo()).isEqualTo(UPDATED_RC_NO);
        assertThat(testVehicle.getContactNumber()).isEqualTo(UPDATED_CONTACT_NUMBER);
        assertThat(testVehicle.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testVehicle.getCollegeId()).isEqualTo(UPDATED_COLLEGE_ID);
        assertThat(testVehicle.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testVehicle.getEmployeeId()).isEqualTo(UPDATED_EMPLOYEE_ID);

        // Validate the Vehicle in Elasticsearch
        verify(mockVehicleSearchRepository, times(1)).save(testVehicle);
    }

    @Test
    @Transactional
    public void updateNonExistingVehicle() throws Exception {
        int databaseSizeBeforeUpdate = vehicleRepository.findAll().size();

        // Create the Vehicle
        VehicleDTO vehicleDTO = vehicleMapper.toDto(vehicle);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVehicleMockMvc.perform(put("/api/vehicles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vehicleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Vehicle in the database
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        assertThat(vehicleList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Vehicle in Elasticsearch
        verify(mockVehicleSearchRepository, times(0)).save(vehicle);
    }

    @Test
    @Transactional
    public void deleteVehicle() throws Exception {
        // Initialize the database
        vehicleRepository.saveAndFlush(vehicle);

        int databaseSizeBeforeDelete = vehicleRepository.findAll().size();

        // Delete the vehicle
        restVehicleMockMvc.perform(delete("/api/vehicles/{id}", vehicle.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        assertThat(vehicleList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Vehicle in Elasticsearch
        verify(mockVehicleSearchRepository, times(1)).deleteById(vehicle.getId());
    }

    @Test
    @Transactional
    public void searchVehicle() throws Exception {
        // Initialize the database
        vehicleRepository.saveAndFlush(vehicle);
//        when(mockVehicleSearchRepository.search(queryStringQuery("id:" + vehicle.getId())))
//            .thenReturn(Collections.singletonList(vehicle));
        // Search the vehicle
        restVehicleMockMvc.perform(get("/api/_search/vehicles?query=id:" + vehicle.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vehicle.getId().intValue())))
            .andExpect(jsonPath("$.[*].vehicleNumber").value(hasItem(DEFAULT_VEHICLE_NUMBER)))
            .andExpect(jsonPath("$.[*].vehicleType").value(hasItem(DEFAULT_VEHICLE_TYPE)))
            .andExpect(jsonPath("$.[*].capacity").value(hasItem(DEFAULT_CAPACITY.intValue())))
            .andExpect(jsonPath("$.[*].ownerShip").value(hasItem(DEFAULT_OWNER_SHIP)))
            .andExpect(jsonPath("$.[*].dateOfRegistration").value(hasItem(DEFAULT_DATE_OF_REGISTRATION.toString())))
            .andExpect(jsonPath("$.[*].yearOfManufacturing").value(hasItem(DEFAULT_YEAR_OF_MANUFACTURING)))
            .andExpect(jsonPath("$.[*].manufacturingCompany").value(hasItem(DEFAULT_MANUFACTURING_COMPANY)))
            .andExpect(jsonPath("$.[*].model").value(hasItem(DEFAULT_MODEL)))
            .andExpect(jsonPath("$.[*].chasisNo").value(hasItem(DEFAULT_CHASIS_NO)))
            .andExpect(jsonPath("$.[*].rcNo").value(hasItem(DEFAULT_RC_NO)))
            .andExpect(jsonPath("$.[*].contactNumber").value(hasItem(DEFAULT_CONTACT_NUMBER)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].collegeId").value(hasItem(DEFAULT_COLLEGE_ID.intValue())))
            .andExpect(jsonPath("$.[*].branchId").value(hasItem(DEFAULT_BRANCH_ID.intValue())))
            .andExpect(jsonPath("$.[*].employeeId").value(hasItem(DEFAULT_EMPLOYEE_ID.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Vehicle.class);
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setId(1L);
        Vehicle vehicle2 = new Vehicle();
        vehicle2.setId(vehicle1.getId());
        assertThat(vehicle1).isEqualTo(vehicle2);
        vehicle2.setId(2L);
        assertThat(vehicle1).isNotEqualTo(vehicle2);
        vehicle1.setId(null);
        assertThat(vehicle1).isNotEqualTo(vehicle2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VehicleDTO.class);
        VehicleDTO vehicleDTO1 = new VehicleDTO();
        vehicleDTO1.setId(1L);
        VehicleDTO vehicleDTO2 = new VehicleDTO();
        assertThat(vehicleDTO1).isNotEqualTo(vehicleDTO2);
        vehicleDTO2.setId(vehicleDTO1.getId());
        assertThat(vehicleDTO1).isEqualTo(vehicleDTO2);
        vehicleDTO2.setId(2L);
        assertThat(vehicleDTO1).isNotEqualTo(vehicleDTO2);
        vehicleDTO1.setId(null);
        assertThat(vehicleDTO1).isNotEqualTo(vehicleDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(vehicleMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(vehicleMapper.fromId(null)).isNull();
    }
}
