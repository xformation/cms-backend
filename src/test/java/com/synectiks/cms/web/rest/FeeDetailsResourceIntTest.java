package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.FeeDetails;
import com.synectiks.cms.repository.FeeDetailsRepository;
import com.synectiks.cms.repository.search.FeeDetailsSearchRepository;
import com.synectiks.cms.service.FeeDetailsService;
import com.synectiks.cms.service.dto.FeeDetailsDTO;
import com.synectiks.cms.service.mapper.FeeDetailsMapper;
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

import com.synectiks.cms.domain.enumeration.StudentTypeEnum;
import com.synectiks.cms.domain.enumeration.Gender;
/**
 * Test class for the FeeDetailsResource REST controller.
 *
 * @see FeeDetailsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class FeeDetailsResourceIntTest {

    private static final String DEFAULT_FEE_PARTICULARS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FEE_PARTICULARS_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FEE_PARTICULAR_DESC = "AAAAAAAAAA";
    private static final String UPDATED_FEE_PARTICULAR_DESC = "BBBBBBBBBB";

    private static final StudentTypeEnum DEFAULT_STUDENT_TYPE = StudentTypeEnum.REGULAR;
    private static final StudentTypeEnum UPDATED_STUDENT_TYPE = StudentTypeEnum.STAFF_CONCESSION;

    private static final Gender DEFAULT_GENDER = Gender.MALE;
    private static final Gender UPDATED_GENDER = Gender.FEMALE;

    private static final Long DEFAULT_AMOUNT = 1L;
    private static final Long UPDATED_AMOUNT = 2L;

    @Autowired
    private FeeDetailsRepository feeDetailsRepository;


    @Autowired
    private FeeDetailsMapper feeDetailsMapper;
    

    @Autowired
    private FeeDetailsService feeDetailsService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.FeeDetailsSearchRepositoryMockConfiguration
     */
    @Autowired
    private FeeDetailsSearchRepository mockFeeDetailsSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFeeDetailsMockMvc;

    private FeeDetails feeDetails;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FeeDetailsResource feeDetailsResource = new FeeDetailsResource(feeDetailsService);
        this.restFeeDetailsMockMvc = MockMvcBuilders.standaloneSetup(feeDetailsResource)
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
    public static FeeDetails createEntity(EntityManager em) {
        FeeDetails feeDetails = new FeeDetails()
            .feeParticularsName(DEFAULT_FEE_PARTICULARS_NAME)
            .feeParticularDesc(DEFAULT_FEE_PARTICULAR_DESC)
            .studentType(DEFAULT_STUDENT_TYPE)
            .gender(DEFAULT_GENDER)
            .amount(DEFAULT_AMOUNT);
        return feeDetails;
    }

    @Before
    public void initTest() {
        feeDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createFeeDetails() throws Exception {
        int databaseSizeBeforeCreate = feeDetailsRepository.findAll().size();

        // Create the FeeDetails
        FeeDetailsDTO feeDetailsDTO = feeDetailsMapper.toDto(feeDetails);
        restFeeDetailsMockMvc.perform(post("/api/fee-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feeDetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the FeeDetails in the database
        List<FeeDetails> feeDetailsList = feeDetailsRepository.findAll();
        assertThat(feeDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        FeeDetails testFeeDetails = feeDetailsList.get(feeDetailsList.size() - 1);
        assertThat(testFeeDetails.getFeeParticularsName()).isEqualTo(DEFAULT_FEE_PARTICULARS_NAME);
        assertThat(testFeeDetails.getFeeParticularDesc()).isEqualTo(DEFAULT_FEE_PARTICULAR_DESC);
        assertThat(testFeeDetails.getStudentType()).isEqualTo(DEFAULT_STUDENT_TYPE);
        assertThat(testFeeDetails.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testFeeDetails.getAmount()).isEqualTo(DEFAULT_AMOUNT);

        // Validate the FeeDetails in Elasticsearch
        verify(mockFeeDetailsSearchRepository, times(1)).save(testFeeDetails);
    }

    @Test
    @Transactional
    public void createFeeDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = feeDetailsRepository.findAll().size();

        // Create the FeeDetails with an existing ID
        feeDetails.setId(1L);
        FeeDetailsDTO feeDetailsDTO = feeDetailsMapper.toDto(feeDetails);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFeeDetailsMockMvc.perform(post("/api/fee-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feeDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FeeDetails in the database
        List<FeeDetails> feeDetailsList = feeDetailsRepository.findAll();
        assertThat(feeDetailsList).hasSize(databaseSizeBeforeCreate);

        // Validate the FeeDetails in Elasticsearch
        verify(mockFeeDetailsSearchRepository, times(0)).save(feeDetails);
    }

    @Test
    @Transactional
    public void checkFeeParticularsNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = feeDetailsRepository.findAll().size();
        // set the field null
        feeDetails.setFeeParticularsName(null);

        // Create the FeeDetails, which fails.
        FeeDetailsDTO feeDetailsDTO = feeDetailsMapper.toDto(feeDetails);

        restFeeDetailsMockMvc.perform(post("/api/fee-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feeDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<FeeDetails> feeDetailsList = feeDetailsRepository.findAll();
        assertThat(feeDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFeeParticularDescIsRequired() throws Exception {
        int databaseSizeBeforeTest = feeDetailsRepository.findAll().size();
        // set the field null
        feeDetails.setFeeParticularDesc(null);

        // Create the FeeDetails, which fails.
        FeeDetailsDTO feeDetailsDTO = feeDetailsMapper.toDto(feeDetails);

        restFeeDetailsMockMvc.perform(post("/api/fee-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feeDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<FeeDetails> feeDetailsList = feeDetailsRepository.findAll();
        assertThat(feeDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStudentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = feeDetailsRepository.findAll().size();
        // set the field null
        feeDetails.setStudentType(null);

        // Create the FeeDetails, which fails.
        FeeDetailsDTO feeDetailsDTO = feeDetailsMapper.toDto(feeDetails);

        restFeeDetailsMockMvc.perform(post("/api/fee-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feeDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<FeeDetails> feeDetailsList = feeDetailsRepository.findAll();
        assertThat(feeDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGenderIsRequired() throws Exception {
        int databaseSizeBeforeTest = feeDetailsRepository.findAll().size();
        // set the field null
        feeDetails.setGender(null);

        // Create the FeeDetails, which fails.
        FeeDetailsDTO feeDetailsDTO = feeDetailsMapper.toDto(feeDetails);

        restFeeDetailsMockMvc.perform(post("/api/fee-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feeDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<FeeDetails> feeDetailsList = feeDetailsRepository.findAll();
        assertThat(feeDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = feeDetailsRepository.findAll().size();
        // set the field null
        feeDetails.setAmount(null);

        // Create the FeeDetails, which fails.
        FeeDetailsDTO feeDetailsDTO = feeDetailsMapper.toDto(feeDetails);

        restFeeDetailsMockMvc.perform(post("/api/fee-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feeDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<FeeDetails> feeDetailsList = feeDetailsRepository.findAll();
        assertThat(feeDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFeeDetails() throws Exception {
        // Initialize the database
        feeDetailsRepository.saveAndFlush(feeDetails);

        // Get all the feeDetailsList
        restFeeDetailsMockMvc.perform(get("/api/fee-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(feeDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].feeParticularsName").value(hasItem(DEFAULT_FEE_PARTICULARS_NAME.toString())))
            .andExpect(jsonPath("$.[*].feeParticularDesc").value(hasItem(DEFAULT_FEE_PARTICULAR_DESC.toString())))
            .andExpect(jsonPath("$.[*].studentType").value(hasItem(DEFAULT_STUDENT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())));
    }
    

    @Test
    @Transactional
    public void getFeeDetails() throws Exception {
        // Initialize the database
        feeDetailsRepository.saveAndFlush(feeDetails);

        // Get the feeDetails
        restFeeDetailsMockMvc.perform(get("/api/fee-details/{id}", feeDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(feeDetails.getId().intValue()))
            .andExpect(jsonPath("$.feeParticularsName").value(DEFAULT_FEE_PARTICULARS_NAME.toString()))
            .andExpect(jsonPath("$.feeParticularDesc").value(DEFAULT_FEE_PARTICULAR_DESC.toString()))
            .andExpect(jsonPath("$.studentType").value(DEFAULT_STUDENT_TYPE.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingFeeDetails() throws Exception {
        // Get the feeDetails
        restFeeDetailsMockMvc.perform(get("/api/fee-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFeeDetails() throws Exception {
        // Initialize the database
        feeDetailsRepository.saveAndFlush(feeDetails);

        int databaseSizeBeforeUpdate = feeDetailsRepository.findAll().size();

        // Update the feeDetails
        FeeDetails updatedFeeDetails = feeDetailsRepository.findById(feeDetails.getId()).get();
        // Disconnect from session so that the updates on updatedFeeDetails are not directly saved in db
        em.detach(updatedFeeDetails);
        updatedFeeDetails
            .feeParticularsName(UPDATED_FEE_PARTICULARS_NAME)
            .feeParticularDesc(UPDATED_FEE_PARTICULAR_DESC)
            .studentType(UPDATED_STUDENT_TYPE)
            .gender(UPDATED_GENDER)
            .amount(UPDATED_AMOUNT);
        FeeDetailsDTO feeDetailsDTO = feeDetailsMapper.toDto(updatedFeeDetails);

        restFeeDetailsMockMvc.perform(put("/api/fee-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feeDetailsDTO)))
            .andExpect(status().isOk());

        // Validate the FeeDetails in the database
        List<FeeDetails> feeDetailsList = feeDetailsRepository.findAll();
        assertThat(feeDetailsList).hasSize(databaseSizeBeforeUpdate);
        FeeDetails testFeeDetails = feeDetailsList.get(feeDetailsList.size() - 1);
        assertThat(testFeeDetails.getFeeParticularsName()).isEqualTo(UPDATED_FEE_PARTICULARS_NAME);
        assertThat(testFeeDetails.getFeeParticularDesc()).isEqualTo(UPDATED_FEE_PARTICULAR_DESC);
        assertThat(testFeeDetails.getStudentType()).isEqualTo(UPDATED_STUDENT_TYPE);
        assertThat(testFeeDetails.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testFeeDetails.getAmount()).isEqualTo(UPDATED_AMOUNT);

        // Validate the FeeDetails in Elasticsearch
        verify(mockFeeDetailsSearchRepository, times(1)).save(testFeeDetails);
    }

    @Test
    @Transactional
    public void updateNonExistingFeeDetails() throws Exception {
        int databaseSizeBeforeUpdate = feeDetailsRepository.findAll().size();

        // Create the FeeDetails
        FeeDetailsDTO feeDetailsDTO = feeDetailsMapper.toDto(feeDetails);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFeeDetailsMockMvc.perform(put("/api/fee-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feeDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FeeDetails in the database
        List<FeeDetails> feeDetailsList = feeDetailsRepository.findAll();
        assertThat(feeDetailsList).hasSize(databaseSizeBeforeUpdate);

        // Validate the FeeDetails in Elasticsearch
        verify(mockFeeDetailsSearchRepository, times(0)).save(feeDetails);
    }

    @Test
    @Transactional
    public void deleteFeeDetails() throws Exception {
        // Initialize the database
        feeDetailsRepository.saveAndFlush(feeDetails);

        int databaseSizeBeforeDelete = feeDetailsRepository.findAll().size();

        // Get the feeDetails
        restFeeDetailsMockMvc.perform(delete("/api/fee-details/{id}", feeDetails.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FeeDetails> feeDetailsList = feeDetailsRepository.findAll();
        assertThat(feeDetailsList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the FeeDetails in Elasticsearch
        verify(mockFeeDetailsSearchRepository, times(1)).deleteById(feeDetails.getId());
    }

    @Test
    @Transactional
    public void searchFeeDetails() throws Exception {
        // Initialize the database
        feeDetailsRepository.saveAndFlush(feeDetails);
        when(mockFeeDetailsSearchRepository.search(queryStringQuery("id:" + feeDetails.getId())))
            .thenReturn(Collections.singletonList(feeDetails));
        // Search the feeDetails
        restFeeDetailsMockMvc.perform(get("/api/_search/fee-details?query=id:" + feeDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(feeDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].feeParticularsName").value(hasItem(DEFAULT_FEE_PARTICULARS_NAME.toString())))
            .andExpect(jsonPath("$.[*].feeParticularDesc").value(hasItem(DEFAULT_FEE_PARTICULAR_DESC.toString())))
            .andExpect(jsonPath("$.[*].studentType").value(hasItem(DEFAULT_STUDENT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FeeDetails.class);
        FeeDetails feeDetails1 = new FeeDetails();
        feeDetails1.setId(1L);
        FeeDetails feeDetails2 = new FeeDetails();
        feeDetails2.setId(feeDetails1.getId());
        assertThat(feeDetails1).isEqualTo(feeDetails2);
        feeDetails2.setId(2L);
        assertThat(feeDetails1).isNotEqualTo(feeDetails2);
        feeDetails1.setId(null);
        assertThat(feeDetails1).isNotEqualTo(feeDetails2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FeeDetailsDTO.class);
        FeeDetailsDTO feeDetailsDTO1 = new FeeDetailsDTO();
        feeDetailsDTO1.setId(1L);
        FeeDetailsDTO feeDetailsDTO2 = new FeeDetailsDTO();
        assertThat(feeDetailsDTO1).isNotEqualTo(feeDetailsDTO2);
        feeDetailsDTO2.setId(feeDetailsDTO1.getId());
        assertThat(feeDetailsDTO1).isEqualTo(feeDetailsDTO2);
        feeDetailsDTO2.setId(2L);
        assertThat(feeDetailsDTO1).isNotEqualTo(feeDetailsDTO2);
        feeDetailsDTO1.setId(null);
        assertThat(feeDetailsDTO1).isNotEqualTo(feeDetailsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(feeDetailsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(feeDetailsMapper.fromId(null)).isNull();
    }
}
