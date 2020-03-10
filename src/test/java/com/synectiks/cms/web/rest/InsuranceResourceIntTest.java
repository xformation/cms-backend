package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.Insurance;
import com.synectiks.cms.repository.InsuranceRepository;
import com.synectiks.cms.repository.search.InsuranceSearchRepository;
import com.synectiks.cms.service.InsuranceService;
import com.synectiks.cms.service.dto.InsuranceDTO;
import com.synectiks.cms.service.mapper.InsuranceMapper;
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

import com.synectiks.cms.domain.enumeration.TypeOfInsurance;
/**
 * Test class for the InsuranceResource REST controller.
 *
 * @see InsuranceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class InsuranceResourceIntTest {

    private static final String DEFAULT_INSURANCE_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_INSURANCE_COMPANY = "BBBBBBBBBB";

    private static final TypeOfInsurance DEFAULT_TYPE_OF_INSURANCE = TypeOfInsurance.LIABILITY;
    private static final TypeOfInsurance UPDATED_TYPE_OF_INSURANCE = TypeOfInsurance.COLLISION;

    private static final LocalDate DEFAULT_DATE_OF_INSURANCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_OF_INSURANCE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_VALID_TILL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_VALID_TILL = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private InsuranceMapper insuranceMapper;

    @Autowired
    private InsuranceService insuranceService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.InsuranceSearchRepositoryMockConfiguration
     */
    @Autowired
    private InsuranceSearchRepository mockInsuranceSearchRepository;

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

    private MockMvc restInsuranceMockMvc;

    private Insurance insurance;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InsuranceResource insuranceResource = new InsuranceResource(insuranceService);
        this.restInsuranceMockMvc = MockMvcBuilders.standaloneSetup(insuranceResource)
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
    public static Insurance createEntity(EntityManager em) {
        Insurance insurance = new Insurance()
            .insuranceCompany(DEFAULT_INSURANCE_COMPANY)
            .typeOfInsurance(DEFAULT_TYPE_OF_INSURANCE)
            .dateOfInsurance(DEFAULT_DATE_OF_INSURANCE)
            .validTill(DEFAULT_VALID_TILL);
        return insurance;
    }

    @Before
    public void initTest() {
        insurance = createEntity(em);
    }

    @Test
    @Transactional
    public void createInsurance() throws Exception {
        int databaseSizeBeforeCreate = insuranceRepository.findAll().size();

        // Create the Insurance
        InsuranceDTO insuranceDTO = insuranceMapper.toDto(insurance);
        restInsuranceMockMvc.perform(post("/api/insurances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(insuranceDTO)))
            .andExpect(status().isCreated());

        // Validate the Insurance in the database
        List<Insurance> insuranceList = insuranceRepository.findAll();
        assertThat(insuranceList).hasSize(databaseSizeBeforeCreate + 1);
        Insurance testInsurance = insuranceList.get(insuranceList.size() - 1);
        assertThat(testInsurance.getInsuranceCompany()).isEqualTo(DEFAULT_INSURANCE_COMPANY);
        assertThat(testInsurance.getTypeOfInsurance()).isEqualTo(DEFAULT_TYPE_OF_INSURANCE);
        assertThat(testInsurance.getDateOfInsurance()).isEqualTo(DEFAULT_DATE_OF_INSURANCE);
        assertThat(testInsurance.getValidTill()).isEqualTo(DEFAULT_VALID_TILL);

        // Validate the Insurance in Elasticsearch
        verify(mockInsuranceSearchRepository, times(1)).save(testInsurance);
    }

    @Test
    @Transactional
    public void createInsuranceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = insuranceRepository.findAll().size();

        // Create the Insurance with an existing ID
        insurance.setId(1L);
        InsuranceDTO insuranceDTO = insuranceMapper.toDto(insurance);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInsuranceMockMvc.perform(post("/api/insurances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(insuranceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Insurance in the database
        List<Insurance> insuranceList = insuranceRepository.findAll();
        assertThat(insuranceList).hasSize(databaseSizeBeforeCreate);

        // Validate the Insurance in Elasticsearch
        verify(mockInsuranceSearchRepository, times(0)).save(insurance);
    }

    @Test
    @Transactional
    public void checkInsuranceCompanyIsRequired() throws Exception {
        int databaseSizeBeforeTest = insuranceRepository.findAll().size();
        // set the field null
        insurance.setInsuranceCompany(null);

        // Create the Insurance, which fails.
        InsuranceDTO insuranceDTO = insuranceMapper.toDto(insurance);

        restInsuranceMockMvc.perform(post("/api/insurances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(insuranceDTO)))
            .andExpect(status().isBadRequest());

        List<Insurance> insuranceList = insuranceRepository.findAll();
        assertThat(insuranceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeOfInsuranceIsRequired() throws Exception {
        int databaseSizeBeforeTest = insuranceRepository.findAll().size();
        // set the field null
        insurance.setTypeOfInsurance(null);

        // Create the Insurance, which fails.
        InsuranceDTO insuranceDTO = insuranceMapper.toDto(insurance);

        restInsuranceMockMvc.perform(post("/api/insurances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(insuranceDTO)))
            .andExpect(status().isBadRequest());

        List<Insurance> insuranceList = insuranceRepository.findAll();
        assertThat(insuranceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateOfInsuranceIsRequired() throws Exception {
        int databaseSizeBeforeTest = insuranceRepository.findAll().size();
        // set the field null
        insurance.setDateOfInsurance(null);

        // Create the Insurance, which fails.
        InsuranceDTO insuranceDTO = insuranceMapper.toDto(insurance);

        restInsuranceMockMvc.perform(post("/api/insurances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(insuranceDTO)))
            .andExpect(status().isBadRequest());

        List<Insurance> insuranceList = insuranceRepository.findAll();
        assertThat(insuranceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValidTillIsRequired() throws Exception {
        int databaseSizeBeforeTest = insuranceRepository.findAll().size();
        // set the field null
        insurance.setValidTill(null);

        // Create the Insurance, which fails.
        InsuranceDTO insuranceDTO = insuranceMapper.toDto(insurance);

        restInsuranceMockMvc.perform(post("/api/insurances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(insuranceDTO)))
            .andExpect(status().isBadRequest());

        List<Insurance> insuranceList = insuranceRepository.findAll();
        assertThat(insuranceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInsurances() throws Exception {
        // Initialize the database
        insuranceRepository.saveAndFlush(insurance);

        // Get all the insuranceList
        restInsuranceMockMvc.perform(get("/api/insurances?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(insurance.getId().intValue())))
            .andExpect(jsonPath("$.[*].insuranceCompany").value(hasItem(DEFAULT_INSURANCE_COMPANY.toString())))
            .andExpect(jsonPath("$.[*].typeOfInsurance").value(hasItem(DEFAULT_TYPE_OF_INSURANCE.toString())))
            .andExpect(jsonPath("$.[*].dateOfInsurance").value(hasItem(DEFAULT_DATE_OF_INSURANCE.toString())))
            .andExpect(jsonPath("$.[*].validTill").value(hasItem(DEFAULT_VALID_TILL.toString())));
    }

    @Test
    @Transactional
    public void getInsurance() throws Exception {
        // Initialize the database
        insuranceRepository.saveAndFlush(insurance);

        // Get the insurance
        restInsuranceMockMvc.perform(get("/api/insurances/{id}", insurance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(insurance.getId().intValue()))
            .andExpect(jsonPath("$.insuranceCompany").value(DEFAULT_INSURANCE_COMPANY.toString()))
            .andExpect(jsonPath("$.typeOfInsurance").value(DEFAULT_TYPE_OF_INSURANCE.toString()))
            .andExpect(jsonPath("$.dateOfInsurance").value(DEFAULT_DATE_OF_INSURANCE.toString()))
            .andExpect(jsonPath("$.validTill").value(DEFAULT_VALID_TILL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingInsurance() throws Exception {
        // Get the insurance
        restInsuranceMockMvc.perform(get("/api/insurances/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInsurance() throws Exception {
        // Initialize the database
        insuranceRepository.saveAndFlush(insurance);

        int databaseSizeBeforeUpdate = insuranceRepository.findAll().size();

        // Update the insurance
        Insurance updatedInsurance = insuranceRepository.findById(insurance.getId()).get();
        // Disconnect from session so that the updates on updatedInsurance are not directly saved in db
        em.detach(updatedInsurance);
        updatedInsurance
            .insuranceCompany(UPDATED_INSURANCE_COMPANY)
            .typeOfInsurance(UPDATED_TYPE_OF_INSURANCE)
            .dateOfInsurance(UPDATED_DATE_OF_INSURANCE)
            .validTill(UPDATED_VALID_TILL);
        InsuranceDTO insuranceDTO = insuranceMapper.toDto(updatedInsurance);

        restInsuranceMockMvc.perform(put("/api/insurances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(insuranceDTO)))
            .andExpect(status().isOk());

        // Validate the Insurance in the database
        List<Insurance> insuranceList = insuranceRepository.findAll();
        assertThat(insuranceList).hasSize(databaseSizeBeforeUpdate);
        Insurance testInsurance = insuranceList.get(insuranceList.size() - 1);
        assertThat(testInsurance.getInsuranceCompany()).isEqualTo(UPDATED_INSURANCE_COMPANY);
        assertThat(testInsurance.getTypeOfInsurance()).isEqualTo(UPDATED_TYPE_OF_INSURANCE);
        assertThat(testInsurance.getDateOfInsurance()).isEqualTo(UPDATED_DATE_OF_INSURANCE);
        assertThat(testInsurance.getValidTill()).isEqualTo(UPDATED_VALID_TILL);

        // Validate the Insurance in Elasticsearch
        verify(mockInsuranceSearchRepository, times(1)).save(testInsurance);
    }

    @Test
    @Transactional
    public void updateNonExistingInsurance() throws Exception {
        int databaseSizeBeforeUpdate = insuranceRepository.findAll().size();

        // Create the Insurance
        InsuranceDTO insuranceDTO = insuranceMapper.toDto(insurance);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInsuranceMockMvc.perform(put("/api/insurances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(insuranceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Insurance in the database
        List<Insurance> insuranceList = insuranceRepository.findAll();
        assertThat(insuranceList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Insurance in Elasticsearch
        verify(mockInsuranceSearchRepository, times(0)).save(insurance);
    }

    @Test
    @Transactional
    public void deleteInsurance() throws Exception {
        // Initialize the database
        insuranceRepository.saveAndFlush(insurance);

        int databaseSizeBeforeDelete = insuranceRepository.findAll().size();

        // Delete the insurance
        restInsuranceMockMvc.perform(delete("/api/insurances/{id}", insurance.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Insurance> insuranceList = insuranceRepository.findAll();
        assertThat(insuranceList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Insurance in Elasticsearch
        verify(mockInsuranceSearchRepository, times(1)).deleteById(insurance.getId());
    }

    @Test
    @Transactional
    public void searchInsurance() throws Exception {
        // Initialize the database
        insuranceRepository.saveAndFlush(insurance);
//        when(mockInsuranceSearchRepository.search(queryStringQuery("id:" + insurance.getId())))
//            .thenReturn(Collections.singletonList(insurance));
        // Search the insurance
        restInsuranceMockMvc.perform(get("/api/_search/insurances?query=id:" + insurance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(insurance.getId().intValue())))
            .andExpect(jsonPath("$.[*].insuranceCompany").value(hasItem(DEFAULT_INSURANCE_COMPANY)))
            .andExpect(jsonPath("$.[*].typeOfInsurance").value(hasItem(DEFAULT_TYPE_OF_INSURANCE.toString())))
            .andExpect(jsonPath("$.[*].dateOfInsurance").value(hasItem(DEFAULT_DATE_OF_INSURANCE.toString())))
            .andExpect(jsonPath("$.[*].validTill").value(hasItem(DEFAULT_VALID_TILL.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Insurance.class);
        Insurance insurance1 = new Insurance();
        insurance1.setId(1L);
        Insurance insurance2 = new Insurance();
        insurance2.setId(insurance1.getId());
        assertThat(insurance1).isEqualTo(insurance2);
        insurance2.setId(2L);
        assertThat(insurance1).isNotEqualTo(insurance2);
        insurance1.setId(null);
        assertThat(insurance1).isNotEqualTo(insurance2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsuranceDTO.class);
        InsuranceDTO insuranceDTO1 = new InsuranceDTO();
        insuranceDTO1.setId(1L);
        InsuranceDTO insuranceDTO2 = new InsuranceDTO();
        assertThat(insuranceDTO1).isNotEqualTo(insuranceDTO2);
        insuranceDTO2.setId(insuranceDTO1.getId());
        assertThat(insuranceDTO1).isEqualTo(insuranceDTO2);
        insuranceDTO2.setId(2L);
        assertThat(insuranceDTO1).isNotEqualTo(insuranceDTO2);
        insuranceDTO1.setId(null);
        assertThat(insuranceDTO1).isNotEqualTo(insuranceDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(insuranceMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(insuranceMapper.fromId(null)).isNull();
    }
}
