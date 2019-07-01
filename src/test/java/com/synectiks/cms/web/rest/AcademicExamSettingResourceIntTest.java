package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.AcademicExamSetting;
import com.synectiks.cms.repository.AcademicExamSettingRepository;
import com.synectiks.cms.repository.search.AcademicExamSettingSearchRepository;
import com.synectiks.cms.service.AcademicExamSettingService;
import com.synectiks.cms.service.dto.AcademicExamSettingDTO;
import com.synectiks.cms.service.mapper.AcademicExamSettingMapper;
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

import com.synectiks.cms.domain.enumeration.SemesterEnum;
import com.synectiks.cms.domain.enumeration.GradeType;
/**
 * Test class for the AcademicExamSettingResource REST controller.
 *
 * @see AcademicExamSettingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class AcademicExamSettingResourceIntTest {

    private static final String DEFAULT_EXAM_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_EXAM_TYPE = "BBBBBBBBBB";

    private static final SemesterEnum DEFAULT_SEMESTER = SemesterEnum.SEMESTER1;
    private static final SemesterEnum UPDATED_SEMESTER = SemesterEnum.SEMESTER2;

    private static final String DEFAULT_SUBJECT = "AAAAAAAAAA";
    private static final String UPDATED_SUBJECT = "BBBBBBBBBB";

    private static final Date DEFAULT_EXAM_DATE = new Date();
    private static final Date UPDATED_EXAM_DATE = new Date();

    private static final String DEFAULT_DAY = "AAAAAAAAAA";
    private static final String UPDATED_DAY = "BBBBBBBBBB";

    private static final Integer DEFAULT_DURATION = 1;
    private static final Integer UPDATED_DURATION = 2;

    private static final String DEFAULT_START_TIME = "AAAAAAAAAA";
    private static final String UPDATED_START_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_END_TIME = "AAAAAAAAAA";
    private static final String UPDATED_END_TIME = "BBBBBBBBBB";

    private static final GradeType DEFAULT_GRADE_TYPE = GradeType.PERCENTAGE;
    private static final GradeType UPDATED_GRADE_TYPE = GradeType.GRADE;

    private static final Integer DEFAULT_TOTAL = 1;
    private static final Integer UPDATED_TOTAL = 2;

    private static final Integer DEFAULT_PASSING = 1;
    private static final Integer UPDATED_PASSING = 2;

    private static final String DEFAULT_ACTIONS = "AAAAAAAAAA";
    private static final String UPDATED_ACTIONS = "BBBBBBBBBB";

    private static final Date DEFAULT_START_DATE = new Date();
    private static final Date UPDATED_START_DATE = new Date();

    private static final Date DEFAULT_END_DATE = new Date();
    private static final Date UPDATED_END_DATE = new Date();

    @Autowired
    private AcademicExamSettingRepository academicExamSettingRepository;

    @Autowired
    private AcademicExamSettingMapper academicExamSettingMapper;

    @Autowired
    private AcademicExamSettingService academicExamSettingService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.AcademicExamSettingSearchRepositoryMockConfiguration
     */
    @Autowired
    private AcademicExamSettingSearchRepository mockAcademicExamSettingSearchRepository;

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

    private MockMvc restAcademicExamSettingMockMvc;

    private AcademicExamSetting academicExamSetting;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AcademicExamSettingResource academicExamSettingResource = new AcademicExamSettingResource(academicExamSettingService);
        this.restAcademicExamSettingMockMvc = MockMvcBuilders.standaloneSetup(academicExamSettingResource)
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
    public static AcademicExamSetting createEntity(EntityManager em) {
        AcademicExamSetting academicExamSetting = new AcademicExamSetting()
            .examType(DEFAULT_EXAM_TYPE)
            .semester(DEFAULT_SEMESTER)
            .subject(DEFAULT_SUBJECT)
            .examDate(DEFAULT_EXAM_DATE)
            .day(DEFAULT_DAY)
            .duration(DEFAULT_DURATION)
            .startTime(DEFAULT_START_TIME)
            .endTime(DEFAULT_END_TIME)
            .gradeType(DEFAULT_GRADE_TYPE)
            .total(DEFAULT_TOTAL)
            .passing(DEFAULT_PASSING)
            .actions(DEFAULT_ACTIONS)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE);
        return academicExamSetting;
    }

    @Before
    public void initTest() {
        academicExamSetting = createEntity(em);
    }

    @Test
    @Transactional
    public void createAcademicExamSetting() throws Exception {
        int databaseSizeBeforeCreate = academicExamSettingRepository.findAll().size();

        // Create the AcademicExamSetting
        AcademicExamSettingDTO academicExamSettingDTO = academicExamSettingMapper.toDto(academicExamSetting);
        restAcademicExamSettingMockMvc.perform(post("/api/academic-exam-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicExamSettingDTO)))
            .andExpect(status().isCreated());

        // Validate the AcademicExamSetting in the database
        List<AcademicExamSetting> academicExamSettingList = academicExamSettingRepository.findAll();
        assertThat(academicExamSettingList).hasSize(databaseSizeBeforeCreate + 1);
        AcademicExamSetting testAcademicExamSetting = academicExamSettingList.get(academicExamSettingList.size() - 1);
        assertThat(testAcademicExamSetting.getExamType()).isEqualTo(DEFAULT_EXAM_TYPE);
        assertThat(testAcademicExamSetting.getSemester()).isEqualTo(DEFAULT_SEMESTER);
        assertThat(testAcademicExamSetting.getSubject()).isEqualTo(DEFAULT_SUBJECT);
        assertThat(testAcademicExamSetting.getExamDate()).isEqualTo(DEFAULT_EXAM_DATE);
        assertThat(testAcademicExamSetting.getDay()).isEqualTo(DEFAULT_DAY);
        assertThat(testAcademicExamSetting.getDuration()).isEqualTo(DEFAULT_DURATION);
        assertThat(testAcademicExamSetting.getStartTime()).isEqualTo(DEFAULT_START_TIME);
        assertThat(testAcademicExamSetting.getEndTime()).isEqualTo(DEFAULT_END_TIME);
        assertThat(testAcademicExamSetting.getGradeType()).isEqualTo(DEFAULT_GRADE_TYPE);
        assertThat(testAcademicExamSetting.getTotal()).isEqualTo(DEFAULT_TOTAL);
        assertThat(testAcademicExamSetting.getPassing()).isEqualTo(DEFAULT_PASSING);
        assertThat(testAcademicExamSetting.getActions()).isEqualTo(DEFAULT_ACTIONS);
        assertThat(testAcademicExamSetting.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testAcademicExamSetting.getEndDate()).isEqualTo(DEFAULT_END_DATE);

        // Validate the AcademicExamSetting in Elasticsearch
        verify(mockAcademicExamSettingSearchRepository, times(1)).save(testAcademicExamSetting);
    }

    @Test
    @Transactional
    public void createAcademicExamSettingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = academicExamSettingRepository.findAll().size();

        // Create the AcademicExamSetting with an existing ID
        academicExamSetting.setId(1L);
        AcademicExamSettingDTO academicExamSettingDTO = academicExamSettingMapper.toDto(academicExamSetting);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAcademicExamSettingMockMvc.perform(post("/api/academic-exam-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicExamSettingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AcademicExamSetting in the database
        List<AcademicExamSetting> academicExamSettingList = academicExamSettingRepository.findAll();
        assertThat(academicExamSettingList).hasSize(databaseSizeBeforeCreate);

        // Validate the AcademicExamSetting in Elasticsearch
        verify(mockAcademicExamSettingSearchRepository, times(0)).save(academicExamSetting);
    }

    @Test
    @Transactional
    public void checkExamTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = academicExamSettingRepository.findAll().size();
        // set the field null
        academicExamSetting.setExamType(null);

        // Create the AcademicExamSetting, which fails.
        AcademicExamSettingDTO academicExamSettingDTO = academicExamSettingMapper.toDto(academicExamSetting);

        restAcademicExamSettingMockMvc.perform(post("/api/academic-exam-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicExamSettingDTO)))
            .andExpect(status().isBadRequest());

        List<AcademicExamSetting> academicExamSettingList = academicExamSettingRepository.findAll();
        assertThat(academicExamSettingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSemesterIsRequired() throws Exception {
        int databaseSizeBeforeTest = academicExamSettingRepository.findAll().size();
        // set the field null
        academicExamSetting.setSemester(null);

        // Create the AcademicExamSetting, which fails.
        AcademicExamSettingDTO academicExamSettingDTO = academicExamSettingMapper.toDto(academicExamSetting);

        restAcademicExamSettingMockMvc.perform(post("/api/academic-exam-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicExamSettingDTO)))
            .andExpect(status().isBadRequest());

        List<AcademicExamSetting> academicExamSettingList = academicExamSettingRepository.findAll();
        assertThat(academicExamSettingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExamDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = academicExamSettingRepository.findAll().size();
        // set the field null
        academicExamSetting.setExamDate(null);

        // Create the AcademicExamSetting, which fails.
        AcademicExamSettingDTO academicExamSettingDTO = academicExamSettingMapper.toDto(academicExamSetting);

        restAcademicExamSettingMockMvc.perform(post("/api/academic-exam-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicExamSettingDTO)))
            .andExpect(status().isBadRequest());

        List<AcademicExamSetting> academicExamSettingList = academicExamSettingRepository.findAll();
        assertThat(academicExamSettingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDayIsRequired() throws Exception {
        int databaseSizeBeforeTest = academicExamSettingRepository.findAll().size();
        // set the field null
        academicExamSetting.setDay(null);

        // Create the AcademicExamSetting, which fails.
        AcademicExamSettingDTO academicExamSettingDTO = academicExamSettingMapper.toDto(academicExamSetting);

        restAcademicExamSettingMockMvc.perform(post("/api/academic-exam-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicExamSettingDTO)))
            .andExpect(status().isBadRequest());

        List<AcademicExamSetting> academicExamSettingList = academicExamSettingRepository.findAll();
        assertThat(academicExamSettingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDurationIsRequired() throws Exception {
        int databaseSizeBeforeTest = academicExamSettingRepository.findAll().size();
        // set the field null
        academicExamSetting.setDuration(null);

        // Create the AcademicExamSetting, which fails.
        AcademicExamSettingDTO academicExamSettingDTO = academicExamSettingMapper.toDto(academicExamSetting);

        restAcademicExamSettingMockMvc.perform(post("/api/academic-exam-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicExamSettingDTO)))
            .andExpect(status().isBadRequest());

        List<AcademicExamSetting> academicExamSettingList = academicExamSettingRepository.findAll();
        assertThat(academicExamSettingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = academicExamSettingRepository.findAll().size();
        // set the field null
        academicExamSetting.setStartTime(null);

        // Create the AcademicExamSetting, which fails.
        AcademicExamSettingDTO academicExamSettingDTO = academicExamSettingMapper.toDto(academicExamSetting);

        restAcademicExamSettingMockMvc.perform(post("/api/academic-exam-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicExamSettingDTO)))
            .andExpect(status().isBadRequest());

        List<AcademicExamSetting> academicExamSettingList = academicExamSettingRepository.findAll();
        assertThat(academicExamSettingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = academicExamSettingRepository.findAll().size();
        // set the field null
        academicExamSetting.setEndTime(null);

        // Create the AcademicExamSetting, which fails.
        AcademicExamSettingDTO academicExamSettingDTO = academicExamSettingMapper.toDto(academicExamSetting);

        restAcademicExamSettingMockMvc.perform(post("/api/academic-exam-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicExamSettingDTO)))
            .andExpect(status().isBadRequest());

        List<AcademicExamSetting> academicExamSettingList = academicExamSettingRepository.findAll();
        assertThat(academicExamSettingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGradeTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = academicExamSettingRepository.findAll().size();
        // set the field null
        academicExamSetting.setGradeType(null);

        // Create the AcademicExamSetting, which fails.
        AcademicExamSettingDTO academicExamSettingDTO = academicExamSettingMapper.toDto(academicExamSetting);

        restAcademicExamSettingMockMvc.perform(post("/api/academic-exam-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicExamSettingDTO)))
            .andExpect(status().isBadRequest());

        List<AcademicExamSetting> academicExamSettingList = academicExamSettingRepository.findAll();
        assertThat(academicExamSettingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTotalIsRequired() throws Exception {
        int databaseSizeBeforeTest = academicExamSettingRepository.findAll().size();
        // set the field null
        academicExamSetting.setTotal(null);

        // Create the AcademicExamSetting, which fails.
        AcademicExamSettingDTO academicExamSettingDTO = academicExamSettingMapper.toDto(academicExamSetting);

        restAcademicExamSettingMockMvc.perform(post("/api/academic-exam-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicExamSettingDTO)))
            .andExpect(status().isBadRequest());

        List<AcademicExamSetting> academicExamSettingList = academicExamSettingRepository.findAll();
        assertThat(academicExamSettingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPassingIsRequired() throws Exception {
        int databaseSizeBeforeTest = academicExamSettingRepository.findAll().size();
        // set the field null
        academicExamSetting.setPassing(null);

        // Create the AcademicExamSetting, which fails.
        AcademicExamSettingDTO academicExamSettingDTO = academicExamSettingMapper.toDto(academicExamSetting);

        restAcademicExamSettingMockMvc.perform(post("/api/academic-exam-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicExamSettingDTO)))
            .andExpect(status().isBadRequest());

        List<AcademicExamSetting> academicExamSettingList = academicExamSettingRepository.findAll();
        assertThat(academicExamSettingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = academicExamSettingRepository.findAll().size();
        // set the field null
        academicExamSetting.setStartDate(null);

        // Create the AcademicExamSetting, which fails.
        AcademicExamSettingDTO academicExamSettingDTO = academicExamSettingMapper.toDto(academicExamSetting);

        restAcademicExamSettingMockMvc.perform(post("/api/academic-exam-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicExamSettingDTO)))
            .andExpect(status().isBadRequest());

        List<AcademicExamSetting> academicExamSettingList = academicExamSettingRepository.findAll();
        assertThat(academicExamSettingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = academicExamSettingRepository.findAll().size();
        // set the field null
        academicExamSetting.setEndDate(null);

        // Create the AcademicExamSetting, which fails.
        AcademicExamSettingDTO academicExamSettingDTO = academicExamSettingMapper.toDto(academicExamSetting);

        restAcademicExamSettingMockMvc.perform(post("/api/academic-exam-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicExamSettingDTO)))
            .andExpect(status().isBadRequest());

        List<AcademicExamSetting> academicExamSettingList = academicExamSettingRepository.findAll();
        assertThat(academicExamSettingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAcademicExamSettings() throws Exception {
        // Initialize the database
        academicExamSettingRepository.saveAndFlush(academicExamSetting);

        // Get all the academicExamSettingList
        restAcademicExamSettingMockMvc.perform(get("/api/academic-exam-settings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(academicExamSetting.getId().intValue())))
            .andExpect(jsonPath("$.[*].examType").value(hasItem(DEFAULT_EXAM_TYPE.toString())))
            .andExpect(jsonPath("$.[*].semester").value(hasItem(DEFAULT_SEMESTER.toString())))
            .andExpect(jsonPath("$.[*].subject").value(hasItem(DEFAULT_SUBJECT.toString())))
            .andExpect(jsonPath("$.[*].examDate").value(hasItem(DEFAULT_EXAM_DATE.toString())))
            .andExpect(jsonPath("$.[*].day").value(hasItem(DEFAULT_DAY.toString())))
            .andExpect(jsonPath("$.[*].duration").value(hasItem(DEFAULT_DURATION.toString())))
            .andExpect(jsonPath("$.[*].startTime").value(hasItem(DEFAULT_START_TIME.toString())))
            .andExpect(jsonPath("$.[*].endTime").value(hasItem(DEFAULT_END_TIME.toString())))
            .andExpect(jsonPath("$.[*].gradeType").value(hasItem(DEFAULT_GRADE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL)))
            .andExpect(jsonPath("$.[*].passing").value(hasItem(DEFAULT_PASSING)))
            .andExpect(jsonPath("$.[*].actions").value(hasItem(DEFAULT_ACTIONS.toString())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getAcademicExamSetting() throws Exception {
        // Initialize the database
        academicExamSettingRepository.saveAndFlush(academicExamSetting);

        // Get the academicExamSetting
        restAcademicExamSettingMockMvc.perform(get("/api/academic-exam-settings/{id}", academicExamSetting.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(academicExamSetting.getId().intValue()))
            .andExpect(jsonPath("$.examType").value(DEFAULT_EXAM_TYPE.toString()))
            .andExpect(jsonPath("$.semester").value(DEFAULT_SEMESTER.toString()))
            .andExpect(jsonPath("$.subject").value(DEFAULT_SUBJECT.toString()))
            .andExpect(jsonPath("$.examDate").value(DEFAULT_EXAM_DATE.toString()))
            .andExpect(jsonPath("$.day").value(DEFAULT_DAY.toString()))
            .andExpect(jsonPath("$.duration").value(DEFAULT_DURATION.toString()))
            .andExpect(jsonPath("$.startTime").value(DEFAULT_START_TIME.toString()))
            .andExpect(jsonPath("$.endTime").value(DEFAULT_END_TIME.toString()))
            .andExpect(jsonPath("$.gradeType").value(DEFAULT_GRADE_TYPE.toString()))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL))
            .andExpect(jsonPath("$.passing").value(DEFAULT_PASSING))
            .andExpect(jsonPath("$.actions").value(DEFAULT_ACTIONS.toString()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAcademicExamSetting() throws Exception {
        // Get the academicExamSetting
        restAcademicExamSettingMockMvc.perform(get("/api/academic-exam-settings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAcademicExamSetting() throws Exception {
        // Initialize the database
        academicExamSettingRepository.saveAndFlush(academicExamSetting);

        int databaseSizeBeforeUpdate = academicExamSettingRepository.findAll().size();

        // Update the academicExamSetting
        AcademicExamSetting updatedAcademicExamSetting = academicExamSettingRepository.findById(academicExamSetting.getId()).get();
        // Disconnect from session so that the updates on updatedAcademicExamSetting are not directly saved in db
        em.detach(updatedAcademicExamSetting);
        updatedAcademicExamSetting
            .examType(UPDATED_EXAM_TYPE)
            .semester(UPDATED_SEMESTER)
            .subject(UPDATED_SUBJECT)
            .examDate(UPDATED_EXAM_DATE)
            .day(UPDATED_DAY)
            .duration(UPDATED_DURATION)
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME)
            .gradeType(UPDATED_GRADE_TYPE)
            .total(UPDATED_TOTAL)
            .passing(UPDATED_PASSING)
            .actions(UPDATED_ACTIONS)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE);
        AcademicExamSettingDTO academicExamSettingDTO = academicExamSettingMapper.toDto(updatedAcademicExamSetting);

        restAcademicExamSettingMockMvc.perform(put("/api/academic-exam-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicExamSettingDTO)))
            .andExpect(status().isOk());

        // Validate the AcademicExamSetting in the database
        List<AcademicExamSetting> academicExamSettingList = academicExamSettingRepository.findAll();
        assertThat(academicExamSettingList).hasSize(databaseSizeBeforeUpdate);
        AcademicExamSetting testAcademicExamSetting = academicExamSettingList.get(academicExamSettingList.size() - 1);
        assertThat(testAcademicExamSetting.getExamType()).isEqualTo(UPDATED_EXAM_TYPE);
        assertThat(testAcademicExamSetting.getSemester()).isEqualTo(UPDATED_SEMESTER);
        assertThat(testAcademicExamSetting.getSubject()).isEqualTo(UPDATED_SUBJECT);
        assertThat(testAcademicExamSetting.getExamDate()).isEqualTo(UPDATED_EXAM_DATE);
        assertThat(testAcademicExamSetting.getDay()).isEqualTo(UPDATED_DAY);
        assertThat(testAcademicExamSetting.getDuration()).isEqualTo(UPDATED_DURATION);
        assertThat(testAcademicExamSetting.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testAcademicExamSetting.getEndTime()).isEqualTo(UPDATED_END_TIME);
        assertThat(testAcademicExamSetting.getGradeType()).isEqualTo(UPDATED_GRADE_TYPE);
        assertThat(testAcademicExamSetting.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testAcademicExamSetting.getPassing()).isEqualTo(UPDATED_PASSING);
        assertThat(testAcademicExamSetting.getActions()).isEqualTo(UPDATED_ACTIONS);
        assertThat(testAcademicExamSetting.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testAcademicExamSetting.getEndDate()).isEqualTo(UPDATED_END_DATE);

        // Validate the AcademicExamSetting in Elasticsearch
        verify(mockAcademicExamSettingSearchRepository, times(1)).save(testAcademicExamSetting);
    }

    @Test
    @Transactional
    public void updateNonExistingAcademicExamSetting() throws Exception {
        int databaseSizeBeforeUpdate = academicExamSettingRepository.findAll().size();

        // Create the AcademicExamSetting
        AcademicExamSettingDTO academicExamSettingDTO = academicExamSettingMapper.toDto(academicExamSetting);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAcademicExamSettingMockMvc.perform(put("/api/academic-exam-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicExamSettingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AcademicExamSetting in the database
        List<AcademicExamSetting> academicExamSettingList = academicExamSettingRepository.findAll();
        assertThat(academicExamSettingList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AcademicExamSetting in Elasticsearch
        verify(mockAcademicExamSettingSearchRepository, times(0)).save(academicExamSetting);
    }

    @Test
    @Transactional
    public void deleteAcademicExamSetting() throws Exception {
        // Initialize the database
        academicExamSettingRepository.saveAndFlush(academicExamSetting);

        int databaseSizeBeforeDelete = academicExamSettingRepository.findAll().size();

        // Delete the academicExamSetting
        restAcademicExamSettingMockMvc.perform(delete("/api/academic-exam-settings/{id}", academicExamSetting.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AcademicExamSetting> academicExamSettingList = academicExamSettingRepository.findAll();
        assertThat(academicExamSettingList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AcademicExamSetting in Elasticsearch
        verify(mockAcademicExamSettingSearchRepository, times(1)).deleteById(academicExamSetting.getId());
    }

    @Test
    @Transactional
    public void searchAcademicExamSetting() throws Exception {
        // Initialize the database
        academicExamSettingRepository.saveAndFlush(academicExamSetting);
        when(mockAcademicExamSettingSearchRepository.search(queryStringQuery("id:" + academicExamSetting.getId())))
            .thenReturn(Collections.singletonList(academicExamSetting));
        // Search the academicExamSetting
        restAcademicExamSettingMockMvc.perform(get("/api/_search/academic-exam-settings?query=id:" + academicExamSetting.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(academicExamSetting.getId().intValue())))
            .andExpect(jsonPath("$.[*].examType").value(hasItem(DEFAULT_EXAM_TYPE)))
            .andExpect(jsonPath("$.[*].semester").value(hasItem(DEFAULT_SEMESTER.toString())))
            .andExpect(jsonPath("$.[*].subject").value(hasItem(DEFAULT_SUBJECT)))
            .andExpect(jsonPath("$.[*].examDate").value(hasItem(DEFAULT_EXAM_DATE.toString())))
            .andExpect(jsonPath("$.[*].day").value(hasItem(DEFAULT_DAY)))
            .andExpect(jsonPath("$.[*].duration").value(hasItem(DEFAULT_DURATION)))
            .andExpect(jsonPath("$.[*].startTime").value(hasItem(DEFAULT_START_TIME)))
            .andExpect(jsonPath("$.[*].endTime").value(hasItem(DEFAULT_END_TIME)))
            .andExpect(jsonPath("$.[*].gradeType").value(hasItem(DEFAULT_GRADE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL)))
            .andExpect(jsonPath("$.[*].passing").value(hasItem(DEFAULT_PASSING)))
            .andExpect(jsonPath("$.[*].actions").value(hasItem(DEFAULT_ACTIONS)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AcademicExamSetting.class);
        AcademicExamSetting academicExamSetting1 = new AcademicExamSetting();
        academicExamSetting1.setId(1L);
        AcademicExamSetting academicExamSetting2 = new AcademicExamSetting();
        academicExamSetting2.setId(academicExamSetting1.getId());
        assertThat(academicExamSetting1).isEqualTo(academicExamSetting2);
        academicExamSetting2.setId(2L);
        assertThat(academicExamSetting1).isNotEqualTo(academicExamSetting2);
        academicExamSetting1.setId(null);
        assertThat(academicExamSetting1).isNotEqualTo(academicExamSetting2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AcademicExamSettingDTO.class);
        AcademicExamSettingDTO academicExamSettingDTO1 = new AcademicExamSettingDTO();
        academicExamSettingDTO1.setId(1L);
        AcademicExamSettingDTO academicExamSettingDTO2 = new AcademicExamSettingDTO();
        assertThat(academicExamSettingDTO1).isNotEqualTo(academicExamSettingDTO2);
        academicExamSettingDTO2.setId(academicExamSettingDTO1.getId());
        assertThat(academicExamSettingDTO1).isEqualTo(academicExamSettingDTO2);
        academicExamSettingDTO2.setId(2L);
        assertThat(academicExamSettingDTO1).isNotEqualTo(academicExamSettingDTO2);
        academicExamSettingDTO1.setId(null);
        assertThat(academicExamSettingDTO1).isNotEqualTo(academicExamSettingDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(academicExamSettingMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(academicExamSettingMapper.fromId(null)).isNull();
    }
}
