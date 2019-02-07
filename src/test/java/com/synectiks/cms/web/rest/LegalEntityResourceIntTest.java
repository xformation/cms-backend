package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.LegalEntity;
import com.synectiks.cms.repository.LegalEntityRepository;
import com.synectiks.cms.repository.search.LegalEntitySearchRepository;
import com.synectiks.cms.service.LegalEntityService;
import com.synectiks.cms.service.dto.LegalEntityDTO;
import com.synectiks.cms.service.mapper.LegalEntityMapper;
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

import com.synectiks.cms.domain.enumeration.TypeOfCollege;
/**
 * Test class for the LegalEntityResource REST controller.
 *
 * @see LegalEntityResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class LegalEntityResourceIntTest {

    private static final Long DEFAULT_LOGO = 1L;
    private static final Long UPDATED_LOGO = 2L;

    private static final String DEFAULT_LEGAL_NAME_OF_THE_COLLEGE = "AAAAAAAAAA";
    private static final String UPDATED_LEGAL_NAME_OF_THE_COLLEGE = "BBBBBBBBBB";

    private static final TypeOfCollege DEFAULT_TYPE_OF_COLLEGE = TypeOfCollege.PRIVATE;
    private static final TypeOfCollege UPDATED_TYPE_OF_COLLEGE = TypeOfCollege.PUBLIC;

    private static final Date DEFAULT_DATE_OF_INCORPORATION = new Date();
    private static final Date UPDATED_DATE_OF_INCORPORATION = new Date();

    private static final String DEFAULT_REGISTERED_OFFICE_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_REGISTERED_OFFICE_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_COLLEGE_IDENTIFICATION_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_COLLEGE_IDENTIFICATION_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PAN = "AAAAAAAAAA";
    private static final String UPDATED_PAN = "BBBBBBBBBB";

    private static final String DEFAULT_TAN = "AAAAAAAAAA";
    private static final String UPDATED_TAN = "BBBBBBBBBB";

    private static final String DEFAULT_TAN_CIRCLE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_TAN_CIRCLE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CIT_TDS_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_CIT_TDS_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_FORM_SIGNATORY = "AAAAAAAAAA";
    private static final String UPDATED_FORM_SIGNATORY = "BBBBBBBBBB";

    private static final String DEFAULT_PF_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PF_NUMBER = "BBBBBBBBBB";

    private static final Date DEFAULT_PF_REGISTRATION_DATE = new Date();
    private static final Date UPDATED_PF_REGISTRATION_DATE = new Date();

    private static final String DEFAULT_PF_SIGNATORY = "AAAAAAAAAA";
    private static final String UPDATED_PF_SIGNATORY = "BBBBBBBBBB";

    private static final String DEFAULT_PF_SIGNATORY_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_PF_SIGNATORY_DESIGNATION = "BBBBBBBBBB";

    private static final String DEFAULT_PF_SIGNATORY_FATHER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PF_SIGNATORY_FATHER_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_ESI_NUMBER = 1L;
    private static final Long UPDATED_ESI_NUMBER = 2L;

    private static final Date DEFAULT_ESI_REGISTRATION_DATE = new Date();
    private static final Date UPDATED_ESI_REGISTRATION_DATE = new Date();

    private static final String DEFAULT_ESI_SIGNATORY = "AAAAAAAAAA";
    private static final String UPDATED_ESI_SIGNATORY = "BBBBBBBBBB";

    private static final String DEFAULT_ESI_SIGNATORY_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_ESI_SIGNATORY_DESIGNATION = "BBBBBBBBBB";

    private static final String DEFAULT_ESI_SIGNATORY_FATHER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ESI_SIGNATORY_FATHER_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_PT_NUMBER = 1L;
    private static final Long UPDATED_PT_NUMBER = 2L;

    private static final Date DEFAULT_PT_REGISTRATION_DATE = new Date();
    private static final Date UPDATED_PT_REGISTRATION_DATE = new Date();

    private static final String DEFAULT_PT_SIGNATORY = "AAAAAAAAAA";
    private static final String UPDATED_PT_SIGNATORY = "BBBBBBBBBB";

    @Autowired
    private LegalEntityRepository legalEntityRepository;

    @Autowired
    private LegalEntityMapper legalEntityMapper;

    @Autowired
    private LegalEntityService legalEntityService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.LegalEntitySearchRepositoryMockConfiguration
     */
    @Autowired
    private LegalEntitySearchRepository mockLegalEntitySearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLegalEntityMockMvc;

    private LegalEntity legalEntity;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LegalEntityResource legalEntityResource = new LegalEntityResource(legalEntityService);
        this.restLegalEntityMockMvc = MockMvcBuilders.standaloneSetup(legalEntityResource)
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
    public static LegalEntity createEntity(EntityManager em) {
        LegalEntity legalEntity = new LegalEntity();
        legalEntity.logo(DEFAULT_LOGO);
        legalEntity.legalNameOfTheCollege(DEFAULT_LEGAL_NAME_OF_THE_COLLEGE);
        legalEntity.typeOfCollege(DEFAULT_TYPE_OF_COLLEGE);
        legalEntity.dateOfIncorporation(DEFAULT_DATE_OF_INCORPORATION);
        legalEntity.registeredOfficeAddress(DEFAULT_REGISTERED_OFFICE_ADDRESS);
        legalEntity.collegeIdentificationNumber(DEFAULT_COLLEGE_IDENTIFICATION_NUMBER);
        legalEntity.pan(DEFAULT_PAN);
        legalEntity.tan(DEFAULT_TAN);
        legalEntity.tanCircleNumber(DEFAULT_TAN_CIRCLE_NUMBER);
        legalEntity.citTdsLocation(DEFAULT_CIT_TDS_LOCATION);
        legalEntity.formSignatory(DEFAULT_FORM_SIGNATORY);
        legalEntity.pfNumber(DEFAULT_PF_NUMBER);
        legalEntity.pfRegistrationDate(DEFAULT_PF_REGISTRATION_DATE);
        legalEntity.pfSignatory(DEFAULT_PF_SIGNATORY);
        legalEntity.pfSignatoryDesignation(DEFAULT_PF_SIGNATORY_DESIGNATION);
        legalEntity.pfSignatoryFatherName(DEFAULT_PF_SIGNATORY_FATHER_NAME);
        legalEntity.esiNumber(DEFAULT_ESI_NUMBER);
        legalEntity.esiRegistrationDate(DEFAULT_ESI_REGISTRATION_DATE);
        legalEntity.esiSignatory(DEFAULT_ESI_SIGNATORY);
        legalEntity.esiSignatoryDesignation(DEFAULT_ESI_SIGNATORY_DESIGNATION);
        legalEntity.esiSignatoryFatherName(DEFAULT_ESI_SIGNATORY_FATHER_NAME);
        legalEntity.ptNumber(DEFAULT_PT_NUMBER);
        legalEntity.ptRegistrationDate(DEFAULT_PT_REGISTRATION_DATE);
        legalEntity.ptSignatory(DEFAULT_PT_SIGNATORY);
        return legalEntity;
    }

    @Before
    public void initTest() {
        legalEntity = createEntity(em);
    }

    @Test
    @Transactional
    public void createLegalEntity() throws Exception {
        int databaseSizeBeforeCreate = legalEntityRepository.findAll().size();

        // Create the LegalEntity
        LegalEntityDTO legalEntityDTO = legalEntityMapper.toDto(legalEntity);
        restLegalEntityMockMvc.perform(post("/api/legal-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(legalEntityDTO)))
            .andExpect(status().isCreated());

        // Validate the LegalEntity in the database
        List<LegalEntity> legalEntityList = legalEntityRepository.findAll();
        assertThat(legalEntityList).hasSize(databaseSizeBeforeCreate + 1);
        LegalEntity testLegalEntity = legalEntityList.get(legalEntityList.size() - 1);
        assertThat(testLegalEntity.getLogo()).isEqualTo(DEFAULT_LOGO);
        assertThat(testLegalEntity.getLegalNameOfTheCollege()).isEqualTo(DEFAULT_LEGAL_NAME_OF_THE_COLLEGE);
        assertThat(testLegalEntity.getTypeOfCollege()).isEqualTo(DEFAULT_TYPE_OF_COLLEGE);
        assertThat(testLegalEntity.getDateOfIncorporation()).isEqualTo(DEFAULT_DATE_OF_INCORPORATION);
        assertThat(testLegalEntity.getRegisteredOfficeAddress()).isEqualTo(DEFAULT_REGISTERED_OFFICE_ADDRESS);
        assertThat(testLegalEntity.getCollegeIdentificationNumber()).isEqualTo(DEFAULT_COLLEGE_IDENTIFICATION_NUMBER);
        assertThat(testLegalEntity.getPan()).isEqualTo(DEFAULT_PAN);
        assertThat(testLegalEntity.getTan()).isEqualTo(DEFAULT_TAN);
        assertThat(testLegalEntity.getTanCircleNumber()).isEqualTo(DEFAULT_TAN_CIRCLE_NUMBER);
        assertThat(testLegalEntity.getCitTdsLocation()).isEqualTo(DEFAULT_CIT_TDS_LOCATION);
        assertThat(testLegalEntity.getFormSignatory()).isEqualTo(DEFAULT_FORM_SIGNATORY);
        assertThat(testLegalEntity.getPfNumber()).isEqualTo(DEFAULT_PF_NUMBER);
        assertThat(testLegalEntity.getPfRegistrationDate()).isEqualTo(DEFAULT_PF_REGISTRATION_DATE);
        assertThat(testLegalEntity.getPfSignatory()).isEqualTo(DEFAULT_PF_SIGNATORY);
        assertThat(testLegalEntity.getPfSignatoryDesignation()).isEqualTo(DEFAULT_PF_SIGNATORY_DESIGNATION);
        assertThat(testLegalEntity.getPfSignatoryFatherName()).isEqualTo(DEFAULT_PF_SIGNATORY_FATHER_NAME);
        assertThat(testLegalEntity.getEsiNumber()).isEqualTo(DEFAULT_ESI_NUMBER);
        assertThat(testLegalEntity.getEsiRegistrationDate()).isEqualTo(DEFAULT_ESI_REGISTRATION_DATE);
        assertThat(testLegalEntity.getEsiSignatory()).isEqualTo(DEFAULT_ESI_SIGNATORY);
        assertThat(testLegalEntity.getEsiSignatoryDesignation()).isEqualTo(DEFAULT_ESI_SIGNATORY_DESIGNATION);
        assertThat(testLegalEntity.getEsiSignatoryFatherName()).isEqualTo(DEFAULT_ESI_SIGNATORY_FATHER_NAME);
        assertThat(testLegalEntity.getPtNumber()).isEqualTo(DEFAULT_PT_NUMBER);
        assertThat(testLegalEntity.getPtRegistrationDate()).isEqualTo(DEFAULT_PT_REGISTRATION_DATE);
        assertThat(testLegalEntity.getPtSignatory()).isEqualTo(DEFAULT_PT_SIGNATORY);

        // Validate the LegalEntity in Elasticsearch
        verify(mockLegalEntitySearchRepository, times(1)).save(testLegalEntity);
    }

    @Test
    @Transactional
    public void createLegalEntityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = legalEntityRepository.findAll().size();

        // Create the LegalEntity with an existing ID
        legalEntity.setId(1L);
        LegalEntityDTO legalEntityDTO = legalEntityMapper.toDto(legalEntity);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLegalEntityMockMvc.perform(post("/api/legal-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(legalEntityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LegalEntity in the database
        List<LegalEntity> legalEntityList = legalEntityRepository.findAll();
        assertThat(legalEntityList).hasSize(databaseSizeBeforeCreate);

        // Validate the LegalEntity in Elasticsearch
        verify(mockLegalEntitySearchRepository, times(0)).save(legalEntity);
    }

    @Test
    @Transactional
    public void checkLogoIsRequired() throws Exception {
        int databaseSizeBeforeTest = legalEntityRepository.findAll().size();
        // set the field null
        legalEntity.setLogo(null);

        // Create the LegalEntity, which fails.
        LegalEntityDTO legalEntityDTO = legalEntityMapper.toDto(legalEntity);

        restLegalEntityMockMvc.perform(post("/api/legal-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(legalEntityDTO)))
            .andExpect(status().isBadRequest());

        List<LegalEntity> legalEntityList = legalEntityRepository.findAll();
        assertThat(legalEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLegalNameOfTheCollegeIsRequired() throws Exception {
        int databaseSizeBeforeTest = legalEntityRepository.findAll().size();
        // set the field null
        legalEntity.setLegalNameOfTheCollege(null);

        // Create the LegalEntity, which fails.
        LegalEntityDTO legalEntityDTO = legalEntityMapper.toDto(legalEntity);

        restLegalEntityMockMvc.perform(post("/api/legal-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(legalEntityDTO)))
            .andExpect(status().isBadRequest());

        List<LegalEntity> legalEntityList = legalEntityRepository.findAll();
        assertThat(legalEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeOfCollegeIsRequired() throws Exception {
        int databaseSizeBeforeTest = legalEntityRepository.findAll().size();
        // set the field null
        legalEntity.setTypeOfCollege(null);

        // Create the LegalEntity, which fails.
        LegalEntityDTO legalEntityDTO = legalEntityMapper.toDto(legalEntity);

        restLegalEntityMockMvc.perform(post("/api/legal-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(legalEntityDTO)))
            .andExpect(status().isBadRequest());

        List<LegalEntity> legalEntityList = legalEntityRepository.findAll();
        assertThat(legalEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateOfIncorporationIsRequired() throws Exception {
        int databaseSizeBeforeTest = legalEntityRepository.findAll().size();
        // set the field null
        legalEntity.setDateOfIncorporation(null);

        // Create the LegalEntity, which fails.
        LegalEntityDTO legalEntityDTO = legalEntityMapper.toDto(legalEntity);

        restLegalEntityMockMvc.perform(post("/api/legal-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(legalEntityDTO)))
            .andExpect(status().isBadRequest());

        List<LegalEntity> legalEntityList = legalEntityRepository.findAll();
        assertThat(legalEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRegisteredOfficeAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = legalEntityRepository.findAll().size();
        // set the field null
        legalEntity.setRegisteredOfficeAddress(null);

        // Create the LegalEntity, which fails.
        LegalEntityDTO legalEntityDTO = legalEntityMapper.toDto(legalEntity);

        restLegalEntityMockMvc.perform(post("/api/legal-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(legalEntityDTO)))
            .andExpect(status().isBadRequest());

        List<LegalEntity> legalEntityList = legalEntityRepository.findAll();
        assertThat(legalEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCollegeIdentificationNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = legalEntityRepository.findAll().size();
        // set the field null
        legalEntity.setCollegeIdentificationNumber(null);

        // Create the LegalEntity, which fails.
        LegalEntityDTO legalEntityDTO = legalEntityMapper.toDto(legalEntity);

        restLegalEntityMockMvc.perform(post("/api/legal-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(legalEntityDTO)))
            .andExpect(status().isBadRequest());

        List<LegalEntity> legalEntityList = legalEntityRepository.findAll();
        assertThat(legalEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPanIsRequired() throws Exception {
        int databaseSizeBeforeTest = legalEntityRepository.findAll().size();
        // set the field null
        legalEntity.setPan(null);

        // Create the LegalEntity, which fails.
        LegalEntityDTO legalEntityDTO = legalEntityMapper.toDto(legalEntity);

        restLegalEntityMockMvc.perform(post("/api/legal-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(legalEntityDTO)))
            .andExpect(status().isBadRequest());

        List<LegalEntity> legalEntityList = legalEntityRepository.findAll();
        assertThat(legalEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTanIsRequired() throws Exception {
        int databaseSizeBeforeTest = legalEntityRepository.findAll().size();
        // set the field null
        legalEntity.setTan(null);

        // Create the LegalEntity, which fails.
        LegalEntityDTO legalEntityDTO = legalEntityMapper.toDto(legalEntity);

        restLegalEntityMockMvc.perform(post("/api/legal-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(legalEntityDTO)))
            .andExpect(status().isBadRequest());

        List<LegalEntity> legalEntityList = legalEntityRepository.findAll();
        assertThat(legalEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTanCircleNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = legalEntityRepository.findAll().size();
        // set the field null
        legalEntity.setTanCircleNumber(null);

        // Create the LegalEntity, which fails.
        LegalEntityDTO legalEntityDTO = legalEntityMapper.toDto(legalEntity);

        restLegalEntityMockMvc.perform(post("/api/legal-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(legalEntityDTO)))
            .andExpect(status().isBadRequest());

        List<LegalEntity> legalEntityList = legalEntityRepository.findAll();
        assertThat(legalEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCitTdsLocationIsRequired() throws Exception {
        int databaseSizeBeforeTest = legalEntityRepository.findAll().size();
        // set the field null
        legalEntity.setCitTdsLocation(null);

        // Create the LegalEntity, which fails.
        LegalEntityDTO legalEntityDTO = legalEntityMapper.toDto(legalEntity);

        restLegalEntityMockMvc.perform(post("/api/legal-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(legalEntityDTO)))
            .andExpect(status().isBadRequest());

        List<LegalEntity> legalEntityList = legalEntityRepository.findAll();
        assertThat(legalEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFormSignatoryIsRequired() throws Exception {
        int databaseSizeBeforeTest = legalEntityRepository.findAll().size();
        // set the field null
        legalEntity.setFormSignatory(null);

        // Create the LegalEntity, which fails.
        LegalEntityDTO legalEntityDTO = legalEntityMapper.toDto(legalEntity);

        restLegalEntityMockMvc.perform(post("/api/legal-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(legalEntityDTO)))
            .andExpect(status().isBadRequest());

        List<LegalEntity> legalEntityList = legalEntityRepository.findAll();
        assertThat(legalEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPfNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = legalEntityRepository.findAll().size();
        // set the field null
        legalEntity.setPfNumber(null);

        // Create the LegalEntity, which fails.
        LegalEntityDTO legalEntityDTO = legalEntityMapper.toDto(legalEntity);

        restLegalEntityMockMvc.perform(post("/api/legal-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(legalEntityDTO)))
            .andExpect(status().isBadRequest());

        List<LegalEntity> legalEntityList = legalEntityRepository.findAll();
        assertThat(legalEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPfRegistrationDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = legalEntityRepository.findAll().size();
        // set the field null
        legalEntity.setPfRegistrationDate(null);

        // Create the LegalEntity, which fails.
        LegalEntityDTO legalEntityDTO = legalEntityMapper.toDto(legalEntity);

        restLegalEntityMockMvc.perform(post("/api/legal-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(legalEntityDTO)))
            .andExpect(status().isBadRequest());

        List<LegalEntity> legalEntityList = legalEntityRepository.findAll();
        assertThat(legalEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPfSignatoryIsRequired() throws Exception {
        int databaseSizeBeforeTest = legalEntityRepository.findAll().size();
        // set the field null
        legalEntity.setPfSignatory(null);

        // Create the LegalEntity, which fails.
        LegalEntityDTO legalEntityDTO = legalEntityMapper.toDto(legalEntity);

        restLegalEntityMockMvc.perform(post("/api/legal-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(legalEntityDTO)))
            .andExpect(status().isBadRequest());

        List<LegalEntity> legalEntityList = legalEntityRepository.findAll();
        assertThat(legalEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPfSignatoryDesignationIsRequired() throws Exception {
        int databaseSizeBeforeTest = legalEntityRepository.findAll().size();
        // set the field null
        legalEntity.setPfSignatoryDesignation(null);

        // Create the LegalEntity, which fails.
        LegalEntityDTO legalEntityDTO = legalEntityMapper.toDto(legalEntity);

        restLegalEntityMockMvc.perform(post("/api/legal-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(legalEntityDTO)))
            .andExpect(status().isBadRequest());

        List<LegalEntity> legalEntityList = legalEntityRepository.findAll();
        assertThat(legalEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPfSignatoryFatherNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = legalEntityRepository.findAll().size();
        // set the field null
        legalEntity.setPfSignatoryFatherName(null);

        // Create the LegalEntity, which fails.
        LegalEntityDTO legalEntityDTO = legalEntityMapper.toDto(legalEntity);

        restLegalEntityMockMvc.perform(post("/api/legal-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(legalEntityDTO)))
            .andExpect(status().isBadRequest());

        List<LegalEntity> legalEntityList = legalEntityRepository.findAll();
        assertThat(legalEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEsiNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = legalEntityRepository.findAll().size();
        // set the field null
        legalEntity.setEsiNumber(null);

        // Create the LegalEntity, which fails.
        LegalEntityDTO legalEntityDTO = legalEntityMapper.toDto(legalEntity);

        restLegalEntityMockMvc.perform(post("/api/legal-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(legalEntityDTO)))
            .andExpect(status().isBadRequest());

        List<LegalEntity> legalEntityList = legalEntityRepository.findAll();
        assertThat(legalEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEsiRegistrationDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = legalEntityRepository.findAll().size();
        // set the field null
        legalEntity.setEsiRegistrationDate(null);

        // Create the LegalEntity, which fails.
        LegalEntityDTO legalEntityDTO = legalEntityMapper.toDto(legalEntity);

        restLegalEntityMockMvc.perform(post("/api/legal-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(legalEntityDTO)))
            .andExpect(status().isBadRequest());

        List<LegalEntity> legalEntityList = legalEntityRepository.findAll();
        assertThat(legalEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEsiSignatoryIsRequired() throws Exception {
        int databaseSizeBeforeTest = legalEntityRepository.findAll().size();
        // set the field null
        legalEntity.setEsiSignatory(null);

        // Create the LegalEntity, which fails.
        LegalEntityDTO legalEntityDTO = legalEntityMapper.toDto(legalEntity);

        restLegalEntityMockMvc.perform(post("/api/legal-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(legalEntityDTO)))
            .andExpect(status().isBadRequest());

        List<LegalEntity> legalEntityList = legalEntityRepository.findAll();
        assertThat(legalEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEsiSignatoryDesignationIsRequired() throws Exception {
        int databaseSizeBeforeTest = legalEntityRepository.findAll().size();
        // set the field null
        legalEntity.setEsiSignatoryDesignation(null);

        // Create the LegalEntity, which fails.
        LegalEntityDTO legalEntityDTO = legalEntityMapper.toDto(legalEntity);

        restLegalEntityMockMvc.perform(post("/api/legal-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(legalEntityDTO)))
            .andExpect(status().isBadRequest());

        List<LegalEntity> legalEntityList = legalEntityRepository.findAll();
        assertThat(legalEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEsiSignatoryFatherNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = legalEntityRepository.findAll().size();
        // set the field null
        legalEntity.setEsiSignatoryFatherName(null);

        // Create the LegalEntity, which fails.
        LegalEntityDTO legalEntityDTO = legalEntityMapper.toDto(legalEntity);

        restLegalEntityMockMvc.perform(post("/api/legal-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(legalEntityDTO)))
            .andExpect(status().isBadRequest());

        List<LegalEntity> legalEntityList = legalEntityRepository.findAll();
        assertThat(legalEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPtNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = legalEntityRepository.findAll().size();
        // set the field null
        legalEntity.setPtNumber(null);

        // Create the LegalEntity, which fails.
        LegalEntityDTO legalEntityDTO = legalEntityMapper.toDto(legalEntity);

        restLegalEntityMockMvc.perform(post("/api/legal-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(legalEntityDTO)))
            .andExpect(status().isBadRequest());

        List<LegalEntity> legalEntityList = legalEntityRepository.findAll();
        assertThat(legalEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPtRegistrationDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = legalEntityRepository.findAll().size();
        // set the field null
        legalEntity.setPtRegistrationDate(null);

        // Create the LegalEntity, which fails.
        LegalEntityDTO legalEntityDTO = legalEntityMapper.toDto(legalEntity);

        restLegalEntityMockMvc.perform(post("/api/legal-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(legalEntityDTO)))
            .andExpect(status().isBadRequest());

        List<LegalEntity> legalEntityList = legalEntityRepository.findAll();
        assertThat(legalEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPtSignatoryIsRequired() throws Exception {
        int databaseSizeBeforeTest = legalEntityRepository.findAll().size();
        // set the field null
        legalEntity.setPtSignatory(null);

        // Create the LegalEntity, which fails.
        LegalEntityDTO legalEntityDTO = legalEntityMapper.toDto(legalEntity);

        restLegalEntityMockMvc.perform(post("/api/legal-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(legalEntityDTO)))
            .andExpect(status().isBadRequest());

        List<LegalEntity> legalEntityList = legalEntityRepository.findAll();
        assertThat(legalEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLegalEntities() throws Exception {
        // Initialize the database
        legalEntityRepository.saveAndFlush(legalEntity);

        // Get all the legalEntityList
        restLegalEntityMockMvc.perform(get("/api/legal-entities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(legalEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].logo").value(hasItem(DEFAULT_LOGO.intValue())))
            .andExpect(jsonPath("$.[*].legalNameOfTheCollege").value(hasItem(DEFAULT_LEGAL_NAME_OF_THE_COLLEGE.toString())))
            .andExpect(jsonPath("$.[*].typeOfCollege").value(hasItem(DEFAULT_TYPE_OF_COLLEGE.toString())))
            .andExpect(jsonPath("$.[*].dateOfIncorporation").value(hasItem(DEFAULT_DATE_OF_INCORPORATION.toString())))
            .andExpect(jsonPath("$.[*].registeredOfficeAddress").value(hasItem(DEFAULT_REGISTERED_OFFICE_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].collegeIdentificationNumber").value(hasItem(DEFAULT_COLLEGE_IDENTIFICATION_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].pan").value(hasItem(DEFAULT_PAN.toString())))
            .andExpect(jsonPath("$.[*].tan").value(hasItem(DEFAULT_TAN.toString())))
            .andExpect(jsonPath("$.[*].tanCircleNumber").value(hasItem(DEFAULT_TAN_CIRCLE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].citTdsLocation").value(hasItem(DEFAULT_CIT_TDS_LOCATION.toString())))
            .andExpect(jsonPath("$.[*].formSignatory").value(hasItem(DEFAULT_FORM_SIGNATORY.toString())))
            .andExpect(jsonPath("$.[*].pfNumber").value(hasItem(DEFAULT_PF_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].pfRegistrationDate").value(hasItem(DEFAULT_PF_REGISTRATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].pfSignatory").value(hasItem(DEFAULT_PF_SIGNATORY.toString())))
            .andExpect(jsonPath("$.[*].pfSignatoryDesignation").value(hasItem(DEFAULT_PF_SIGNATORY_DESIGNATION.toString())))
            .andExpect(jsonPath("$.[*].pfSignatoryFatherName").value(hasItem(DEFAULT_PF_SIGNATORY_FATHER_NAME.toString())))
            .andExpect(jsonPath("$.[*].esiNumber").value(hasItem(DEFAULT_ESI_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].esiRegistrationDate").value(hasItem(DEFAULT_ESI_REGISTRATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].esiSignatory").value(hasItem(DEFAULT_ESI_SIGNATORY.toString())))
            .andExpect(jsonPath("$.[*].esiSignatoryDesignation").value(hasItem(DEFAULT_ESI_SIGNATORY_DESIGNATION.toString())))
            .andExpect(jsonPath("$.[*].esiSignatoryFatherName").value(hasItem(DEFAULT_ESI_SIGNATORY_FATHER_NAME.toString())))
            .andExpect(jsonPath("$.[*].ptNumber").value(hasItem(DEFAULT_PT_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].ptRegistrationDate").value(hasItem(DEFAULT_PT_REGISTRATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].ptSignatory").value(hasItem(DEFAULT_PT_SIGNATORY.toString())));
    }
    
    @Test
    @Transactional
    public void getLegalEntity() throws Exception {
        // Initialize the database
        legalEntityRepository.saveAndFlush(legalEntity);

        // Get the legalEntity
        restLegalEntityMockMvc.perform(get("/api/legal-entities/{id}", legalEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(legalEntity.getId().intValue()))
            .andExpect(jsonPath("$.logo").value(DEFAULT_LOGO.intValue()))
            .andExpect(jsonPath("$.legalNameOfTheCollege").value(DEFAULT_LEGAL_NAME_OF_THE_COLLEGE.toString()))
            .andExpect(jsonPath("$.typeOfCollege").value(DEFAULT_TYPE_OF_COLLEGE.toString()))
            .andExpect(jsonPath("$.dateOfIncorporation").value(DEFAULT_DATE_OF_INCORPORATION.toString()))
            .andExpect(jsonPath("$.registeredOfficeAddress").value(DEFAULT_REGISTERED_OFFICE_ADDRESS.toString()))
            .andExpect(jsonPath("$.collegeIdentificationNumber").value(DEFAULT_COLLEGE_IDENTIFICATION_NUMBER.toString()))
            .andExpect(jsonPath("$.pan").value(DEFAULT_PAN.toString()))
            .andExpect(jsonPath("$.tan").value(DEFAULT_TAN.toString()))
            .andExpect(jsonPath("$.tanCircleNumber").value(DEFAULT_TAN_CIRCLE_NUMBER.toString()))
            .andExpect(jsonPath("$.citTdsLocation").value(DEFAULT_CIT_TDS_LOCATION.toString()))
            .andExpect(jsonPath("$.formSignatory").value(DEFAULT_FORM_SIGNATORY.toString()))
            .andExpect(jsonPath("$.pfNumber").value(DEFAULT_PF_NUMBER.toString()))
            .andExpect(jsonPath("$.pfRegistrationDate").value(DEFAULT_PF_REGISTRATION_DATE.toString()))
            .andExpect(jsonPath("$.pfSignatory").value(DEFAULT_PF_SIGNATORY.toString()))
            .andExpect(jsonPath("$.pfSignatoryDesignation").value(DEFAULT_PF_SIGNATORY_DESIGNATION.toString()))
            .andExpect(jsonPath("$.pfSignatoryFatherName").value(DEFAULT_PF_SIGNATORY_FATHER_NAME.toString()))
            .andExpect(jsonPath("$.esiNumber").value(DEFAULT_ESI_NUMBER.intValue()))
            .andExpect(jsonPath("$.esiRegistrationDate").value(DEFAULT_ESI_REGISTRATION_DATE.toString()))
            .andExpect(jsonPath("$.esiSignatory").value(DEFAULT_ESI_SIGNATORY.toString()))
            .andExpect(jsonPath("$.esiSignatoryDesignation").value(DEFAULT_ESI_SIGNATORY_DESIGNATION.toString()))
            .andExpect(jsonPath("$.esiSignatoryFatherName").value(DEFAULT_ESI_SIGNATORY_FATHER_NAME.toString()))
            .andExpect(jsonPath("$.ptNumber").value(DEFAULT_PT_NUMBER.intValue()))
            .andExpect(jsonPath("$.ptRegistrationDate").value(DEFAULT_PT_REGISTRATION_DATE.toString()))
            .andExpect(jsonPath("$.ptSignatory").value(DEFAULT_PT_SIGNATORY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLegalEntity() throws Exception {
        // Get the legalEntity
        restLegalEntityMockMvc.perform(get("/api/legal-entities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLegalEntity() throws Exception {
        // Initialize the database
        legalEntityRepository.saveAndFlush(legalEntity);

        int databaseSizeBeforeUpdate = legalEntityRepository.findAll().size();

        // Update the legalEntity
        LegalEntity updatedLegalEntity = legalEntityRepository.findById(legalEntity.getId()).get();
        // Disconnect from session so that the updates on updatedLegalEntity are not directly saved in db
        em.detach(updatedLegalEntity);
        //updatedLegalEntity
        updatedLegalEntity.logo(UPDATED_LOGO);
        updatedLegalEntity.legalNameOfTheCollege(UPDATED_LEGAL_NAME_OF_THE_COLLEGE);
        updatedLegalEntity.typeOfCollege(UPDATED_TYPE_OF_COLLEGE);
        updatedLegalEntity.setDateOfIncorporation(UPDATED_DATE_OF_INCORPORATION);
        updatedLegalEntity.registeredOfficeAddress(UPDATED_REGISTERED_OFFICE_ADDRESS);
        updatedLegalEntity.collegeIdentificationNumber(UPDATED_COLLEGE_IDENTIFICATION_NUMBER);
        updatedLegalEntity.pan(UPDATED_PAN);
        updatedLegalEntity.tan(UPDATED_TAN);
        updatedLegalEntity.tanCircleNumber(UPDATED_TAN_CIRCLE_NUMBER);
        updatedLegalEntity.citTdsLocation(UPDATED_CIT_TDS_LOCATION);
        updatedLegalEntity.formSignatory(UPDATED_FORM_SIGNATORY);
        updatedLegalEntity.pfNumber(UPDATED_PF_NUMBER);
        updatedLegalEntity.setPfRegistrationDate(UPDATED_PF_REGISTRATION_DATE);
        updatedLegalEntity.pfSignatory(UPDATED_PF_SIGNATORY);
        updatedLegalEntity.pfSignatoryDesignation(UPDATED_PF_SIGNATORY_DESIGNATION);
        updatedLegalEntity.pfSignatoryFatherName(UPDATED_PF_SIGNATORY_FATHER_NAME);
        updatedLegalEntity.esiNumber(UPDATED_ESI_NUMBER);
        updatedLegalEntity.setEsiRegistrationDate(UPDATED_ESI_REGISTRATION_DATE);
        updatedLegalEntity.esiSignatory(UPDATED_ESI_SIGNATORY);
        updatedLegalEntity.esiSignatoryDesignation(UPDATED_ESI_SIGNATORY_DESIGNATION);
        updatedLegalEntity.esiSignatoryFatherName(UPDATED_ESI_SIGNATORY_FATHER_NAME);
        updatedLegalEntity.ptNumber(UPDATED_PT_NUMBER);
        updatedLegalEntity.setPtRegistrationDate(UPDATED_PT_REGISTRATION_DATE);
        updatedLegalEntity.ptSignatory(UPDATED_PT_SIGNATORY);
        LegalEntityDTO legalEntityDTO = legalEntityMapper.toDto(updatedLegalEntity);

        restLegalEntityMockMvc.perform(put("/api/legal-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(legalEntityDTO)))
            .andExpect(status().isOk());

        // Validate the LegalEntity in the database
        List<LegalEntity> legalEntityList = legalEntityRepository.findAll();
        assertThat(legalEntityList).hasSize(databaseSizeBeforeUpdate);
        LegalEntity testLegalEntity = legalEntityList.get(legalEntityList.size() - 1);
        assertThat(testLegalEntity.getLogo()).isEqualTo(UPDATED_LOGO);
        assertThat(testLegalEntity.getLegalNameOfTheCollege()).isEqualTo(UPDATED_LEGAL_NAME_OF_THE_COLLEGE);
        assertThat(testLegalEntity.getTypeOfCollege()).isEqualTo(UPDATED_TYPE_OF_COLLEGE);
        assertThat(testLegalEntity.getDateOfIncorporation()).isEqualTo(UPDATED_DATE_OF_INCORPORATION);
        assertThat(testLegalEntity.getRegisteredOfficeAddress()).isEqualTo(UPDATED_REGISTERED_OFFICE_ADDRESS);
        assertThat(testLegalEntity.getCollegeIdentificationNumber()).isEqualTo(UPDATED_COLLEGE_IDENTIFICATION_NUMBER);
        assertThat(testLegalEntity.getPan()).isEqualTo(UPDATED_PAN);
        assertThat(testLegalEntity.getTan()).isEqualTo(UPDATED_TAN);
        assertThat(testLegalEntity.getTanCircleNumber()).isEqualTo(UPDATED_TAN_CIRCLE_NUMBER);
        assertThat(testLegalEntity.getCitTdsLocation()).isEqualTo(UPDATED_CIT_TDS_LOCATION);
        assertThat(testLegalEntity.getFormSignatory()).isEqualTo(UPDATED_FORM_SIGNATORY);
        assertThat(testLegalEntity.getPfNumber()).isEqualTo(UPDATED_PF_NUMBER);
        assertThat(testLegalEntity.getPfRegistrationDate()).isEqualTo(UPDATED_PF_REGISTRATION_DATE);
        assertThat(testLegalEntity.getPfSignatory()).isEqualTo(UPDATED_PF_SIGNATORY);
        assertThat(testLegalEntity.getPfSignatoryDesignation()).isEqualTo(UPDATED_PF_SIGNATORY_DESIGNATION);
        assertThat(testLegalEntity.getPfSignatoryFatherName()).isEqualTo(UPDATED_PF_SIGNATORY_FATHER_NAME);
        assertThat(testLegalEntity.getEsiNumber()).isEqualTo(UPDATED_ESI_NUMBER);
        assertThat(testLegalEntity.getEsiRegistrationDate()).isEqualTo(UPDATED_ESI_REGISTRATION_DATE);
        assertThat(testLegalEntity.getEsiSignatory()).isEqualTo(UPDATED_ESI_SIGNATORY);
        assertThat(testLegalEntity.getEsiSignatoryDesignation()).isEqualTo(UPDATED_ESI_SIGNATORY_DESIGNATION);
        assertThat(testLegalEntity.getEsiSignatoryFatherName()).isEqualTo(UPDATED_ESI_SIGNATORY_FATHER_NAME);
        assertThat(testLegalEntity.getPtNumber()).isEqualTo(UPDATED_PT_NUMBER);
        assertThat(testLegalEntity.getPtRegistrationDate()).isEqualTo(UPDATED_PT_REGISTRATION_DATE);
        assertThat(testLegalEntity.getPtSignatory()).isEqualTo(UPDATED_PT_SIGNATORY);

        // Validate the LegalEntity in Elasticsearch
        verify(mockLegalEntitySearchRepository, times(1)).save(testLegalEntity);
    }

    @Test
    @Transactional
    public void updateNonExistingLegalEntity() throws Exception {
        int databaseSizeBeforeUpdate = legalEntityRepository.findAll().size();

        // Create the LegalEntity
        LegalEntityDTO legalEntityDTO = legalEntityMapper.toDto(legalEntity);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLegalEntityMockMvc.perform(put("/api/legal-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(legalEntityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LegalEntity in the database
        List<LegalEntity> legalEntityList = legalEntityRepository.findAll();
        assertThat(legalEntityList).hasSize(databaseSizeBeforeUpdate);

        // Validate the LegalEntity in Elasticsearch
        verify(mockLegalEntitySearchRepository, times(0)).save(legalEntity);
    }

    @Test
    @Transactional
    public void deleteLegalEntity() throws Exception {
        // Initialize the database
        legalEntityRepository.saveAndFlush(legalEntity);

        int databaseSizeBeforeDelete = legalEntityRepository.findAll().size();

        // Get the legalEntity
        restLegalEntityMockMvc.perform(delete("/api/legal-entities/{id}", legalEntity.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LegalEntity> legalEntityList = legalEntityRepository.findAll();
        assertThat(legalEntityList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the LegalEntity in Elasticsearch
        verify(mockLegalEntitySearchRepository, times(1)).deleteById(legalEntity.getId());
    }

    @Test
    @Transactional
    public void searchLegalEntity() throws Exception {
        // Initialize the database
        legalEntityRepository.saveAndFlush(legalEntity);
        when(mockLegalEntitySearchRepository.search(queryStringQuery("id:" + legalEntity.getId())))
            .thenReturn(Collections.singletonList(legalEntity));
        // Search the legalEntity
        restLegalEntityMockMvc.perform(get("/api/_search/legal-entities?query=id:" + legalEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(legalEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].logo").value(hasItem(DEFAULT_LOGO.intValue())))
            .andExpect(jsonPath("$.[*].legalNameOfTheCollege").value(hasItem(DEFAULT_LEGAL_NAME_OF_THE_COLLEGE)))
            .andExpect(jsonPath("$.[*].typeOfCollege").value(hasItem(DEFAULT_TYPE_OF_COLLEGE.toString())))
            .andExpect(jsonPath("$.[*].dateOfIncorporation").value(hasItem(DEFAULT_DATE_OF_INCORPORATION.toString())))
            .andExpect(jsonPath("$.[*].registeredOfficeAddress").value(hasItem(DEFAULT_REGISTERED_OFFICE_ADDRESS)))
            .andExpect(jsonPath("$.[*].collegeIdentificationNumber").value(hasItem(DEFAULT_COLLEGE_IDENTIFICATION_NUMBER)))
            .andExpect(jsonPath("$.[*].pan").value(hasItem(DEFAULT_PAN)))
            .andExpect(jsonPath("$.[*].tan").value(hasItem(DEFAULT_TAN)))
            .andExpect(jsonPath("$.[*].tanCircleNumber").value(hasItem(DEFAULT_TAN_CIRCLE_NUMBER)))
            .andExpect(jsonPath("$.[*].citTdsLocation").value(hasItem(DEFAULT_CIT_TDS_LOCATION)))
            .andExpect(jsonPath("$.[*].formSignatory").value(hasItem(DEFAULT_FORM_SIGNATORY)))
            .andExpect(jsonPath("$.[*].pfNumber").value(hasItem(DEFAULT_PF_NUMBER)))
            .andExpect(jsonPath("$.[*].pfRegistrationDate").value(hasItem(DEFAULT_PF_REGISTRATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].pfSignatory").value(hasItem(DEFAULT_PF_SIGNATORY)))
            .andExpect(jsonPath("$.[*].pfSignatoryDesignation").value(hasItem(DEFAULT_PF_SIGNATORY_DESIGNATION)))
            .andExpect(jsonPath("$.[*].pfSignatoryFatherName").value(hasItem(DEFAULT_PF_SIGNATORY_FATHER_NAME)))
            .andExpect(jsonPath("$.[*].esiNumber").value(hasItem(DEFAULT_ESI_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].esiRegistrationDate").value(hasItem(DEFAULT_ESI_REGISTRATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].esiSignatory").value(hasItem(DEFAULT_ESI_SIGNATORY)))
            .andExpect(jsonPath("$.[*].esiSignatoryDesignation").value(hasItem(DEFAULT_ESI_SIGNATORY_DESIGNATION)))
            .andExpect(jsonPath("$.[*].esiSignatoryFatherName").value(hasItem(DEFAULT_ESI_SIGNATORY_FATHER_NAME)))
            .andExpect(jsonPath("$.[*].ptNumber").value(hasItem(DEFAULT_PT_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].ptRegistrationDate").value(hasItem(DEFAULT_PT_REGISTRATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].ptSignatory").value(hasItem(DEFAULT_PT_SIGNATORY)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LegalEntity.class);
        LegalEntity legalEntity1 = new LegalEntity();
        legalEntity1.setId(1L);
        LegalEntity legalEntity2 = new LegalEntity();
        legalEntity2.setId(legalEntity1.getId());
        assertThat(legalEntity1).isEqualTo(legalEntity2);
        legalEntity2.setId(2L);
        assertThat(legalEntity1).isNotEqualTo(legalEntity2);
        legalEntity1.setId(null);
        assertThat(legalEntity1).isNotEqualTo(legalEntity2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LegalEntityDTO.class);
        LegalEntityDTO legalEntityDTO1 = new LegalEntityDTO();
        legalEntityDTO1.setId(1L);
        LegalEntityDTO legalEntityDTO2 = new LegalEntityDTO();
        assertThat(legalEntityDTO1).isNotEqualTo(legalEntityDTO2);
        legalEntityDTO2.setId(legalEntityDTO1.getId());
        assertThat(legalEntityDTO1).isEqualTo(legalEntityDTO2);
        legalEntityDTO2.setId(2L);
        assertThat(legalEntityDTO1).isNotEqualTo(legalEntityDTO2);
        legalEntityDTO1.setId(null);
        assertThat(legalEntityDTO1).isNotEqualTo(legalEntityDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(legalEntityMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(legalEntityMapper.fromId(null)).isNull();
    }
}
