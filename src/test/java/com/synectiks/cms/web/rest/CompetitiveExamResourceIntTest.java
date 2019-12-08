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
import com.synectiks.cms.entities.CompetitiveExam;
import com.synectiks.cms.repositories.CompetitiveExamRepository;
import com.synectiks.cms.service.CompetitiveExamService;
import com.synectiks.cms.service.dto.CompetitiveExamDTO;
import com.synectiks.cms.service.mapper.CompetitiveExamMapper;
import com.synectiks.cms.web.rest.errors.ExceptionTranslator;

/**
 * Test class for the CompetitiveExamResource REST controller.
 *
 * @see CompetitiveExamResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class CompetitiveExamResourceIntTest {

    private static final String DEFAULT_TEST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TEST_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_TEST_SCORE = 1;
    private static final Integer UPDATED_TEST_SCORE = 2;

    private static final Long DEFAULT_ENROLLMENT_NO = 1L;
    private static final Long UPDATED_ENROLLMENT_NO = 2L;

    private static final Long DEFAULT_RANK = 1L;
    private static final Long UPDATED_RANK = 2L;

    @Autowired
    private CompetitiveExamRepository competitiveExamRepository;

    @Autowired
    private CompetitiveExamMapper competitiveExamMapper;

    @Autowired
    private CompetitiveExamService competitiveExamService;

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

    private MockMvc restCompetitiveExamMockMvc;

    private CompetitiveExam competitiveExam;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CompetitiveExamResource competitiveExamResource = new CompetitiveExamResource(competitiveExamService);
        this.restCompetitiveExamMockMvc = MockMvcBuilders.standaloneSetup(competitiveExamResource)
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
    public static CompetitiveExam createEntity(EntityManager em) {
        CompetitiveExam competitiveExam = new CompetitiveExam()
            .testName(DEFAULT_TEST_NAME)
            .testScore(DEFAULT_TEST_SCORE)
            .enrollmentNo(DEFAULT_ENROLLMENT_NO)
            .rank(DEFAULT_RANK);
        return competitiveExam;
    }

    @Before
    public void initTest() {
        competitiveExam = createEntity(em);
    }

    @Test
    @Transactional
    public void createCompetitiveExam() throws Exception {
        int databaseSizeBeforeCreate = competitiveExamRepository.findAll().size();

        // Create the CompetitiveExam
        CompetitiveExamDTO competitiveExamDTO = competitiveExamMapper.toDto(competitiveExam);
        restCompetitiveExamMockMvc.perform(post("/api/competitive-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(competitiveExamDTO)))
            .andExpect(status().isCreated());

        // Validate the CompetitiveExam in the database
        List<CompetitiveExam> competitiveExamList = competitiveExamRepository.findAll();
        assertThat(competitiveExamList).hasSize(databaseSizeBeforeCreate + 1);
        CompetitiveExam testCompetitiveExam = competitiveExamList.get(competitiveExamList.size() - 1);
        assertThat(testCompetitiveExam.getTestName()).isEqualTo(DEFAULT_TEST_NAME);
        assertThat(testCompetitiveExam.getTestScore()).isEqualTo(DEFAULT_TEST_SCORE);
        assertThat(testCompetitiveExam.getEnrollmentNo()).isEqualTo(DEFAULT_ENROLLMENT_NO);
        assertThat(testCompetitiveExam.getRank()).isEqualTo(DEFAULT_RANK);

    }

    @Test
    @Transactional
    public void createCompetitiveExamWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = competitiveExamRepository.findAll().size();

        // Create the CompetitiveExam with an existing ID
        competitiveExam.setId(1L);
        CompetitiveExamDTO competitiveExamDTO = competitiveExamMapper.toDto(competitiveExam);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompetitiveExamMockMvc.perform(post("/api/competitive-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(competitiveExamDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CompetitiveExam in the database
        List<CompetitiveExam> competitiveExamList = competitiveExamRepository.findAll();
        assertThat(competitiveExamList).hasSize(databaseSizeBeforeCreate);

    }

    @Test
    @Transactional
    public void checkTestNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = competitiveExamRepository.findAll().size();
        // set the field null
        competitiveExam.setTestName(null);

        // Create the CompetitiveExam, which fails.
        CompetitiveExamDTO competitiveExamDTO = competitiveExamMapper.toDto(competitiveExam);

        restCompetitiveExamMockMvc.perform(post("/api/competitive-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(competitiveExamDTO)))
            .andExpect(status().isBadRequest());

        List<CompetitiveExam> competitiveExamList = competitiveExamRepository.findAll();
        assertThat(competitiveExamList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTestScoreIsRequired() throws Exception {
        int databaseSizeBeforeTest = competitiveExamRepository.findAll().size();
        // set the field null
        competitiveExam.setTestScore(null);

        // Create the CompetitiveExam, which fails.
        CompetitiveExamDTO competitiveExamDTO = competitiveExamMapper.toDto(competitiveExam);

        restCompetitiveExamMockMvc.perform(post("/api/competitive-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(competitiveExamDTO)))
            .andExpect(status().isBadRequest());

        List<CompetitiveExam> competitiveExamList = competitiveExamRepository.findAll();
        assertThat(competitiveExamList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnrollmentNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = competitiveExamRepository.findAll().size();
        // set the field null
        competitiveExam.setEnrollmentNo(null);

        // Create the CompetitiveExam, which fails.
        CompetitiveExamDTO competitiveExamDTO = competitiveExamMapper.toDto(competitiveExam);

        restCompetitiveExamMockMvc.perform(post("/api/competitive-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(competitiveExamDTO)))
            .andExpect(status().isBadRequest());

        List<CompetitiveExam> competitiveExamList = competitiveExamRepository.findAll();
        assertThat(competitiveExamList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRankIsRequired() throws Exception {
        int databaseSizeBeforeTest = competitiveExamRepository.findAll().size();
        // set the field null
        competitiveExam.setRank(null);

        // Create the CompetitiveExam, which fails.
        CompetitiveExamDTO competitiveExamDTO = competitiveExamMapper.toDto(competitiveExam);

        restCompetitiveExamMockMvc.perform(post("/api/competitive-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(competitiveExamDTO)))
            .andExpect(status().isBadRequest());

        List<CompetitiveExam> competitiveExamList = competitiveExamRepository.findAll();
        assertThat(competitiveExamList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCompetitiveExams() throws Exception {
        // Initialize the database
        competitiveExamRepository.saveAndFlush(competitiveExam);

        // Get all the competitiveExamList
        restCompetitiveExamMockMvc.perform(get("/api/competitive-exams?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(competitiveExam.getId().intValue())))
            .andExpect(jsonPath("$.[*].testName").value(hasItem(DEFAULT_TEST_NAME.toString())))
            .andExpect(jsonPath("$.[*].testScore").value(hasItem(DEFAULT_TEST_SCORE)))
            .andExpect(jsonPath("$.[*].enrollmentNo").value(hasItem(DEFAULT_ENROLLMENT_NO.intValue())))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK.intValue())));
    }
    
    @Test
    @Transactional
    public void getCompetitiveExam() throws Exception {
        // Initialize the database
        competitiveExamRepository.saveAndFlush(competitiveExam);

        // Get the competitiveExam
        restCompetitiveExamMockMvc.perform(get("/api/competitive-exams/{id}", competitiveExam.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(competitiveExam.getId().intValue()))
            .andExpect(jsonPath("$.testName").value(DEFAULT_TEST_NAME.toString()))
            .andExpect(jsonPath("$.testScore").value(DEFAULT_TEST_SCORE))
            .andExpect(jsonPath("$.enrollmentNo").value(DEFAULT_ENROLLMENT_NO.intValue()))
            .andExpect(jsonPath("$.rank").value(DEFAULT_RANK.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCompetitiveExam() throws Exception {
        // Get the competitiveExam
        restCompetitiveExamMockMvc.perform(get("/api/competitive-exams/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCompetitiveExam() throws Exception {
        // Initialize the database
        competitiveExamRepository.saveAndFlush(competitiveExam);

        int databaseSizeBeforeUpdate = competitiveExamRepository.findAll().size();

        // Update the competitiveExam
        CompetitiveExam updatedCompetitiveExam = competitiveExamRepository.findById(competitiveExam.getId()).get();
        // Disconnect from session so that the updates on updatedCompetitiveExam are not directly saved in db
        em.detach(updatedCompetitiveExam);
        updatedCompetitiveExam
            .testName(UPDATED_TEST_NAME)
            .testScore(UPDATED_TEST_SCORE)
            .enrollmentNo(UPDATED_ENROLLMENT_NO)
            .rank(UPDATED_RANK);
        CompetitiveExamDTO competitiveExamDTO = competitiveExamMapper.toDto(updatedCompetitiveExam);

        restCompetitiveExamMockMvc.perform(put("/api/competitive-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(competitiveExamDTO)))
            .andExpect(status().isOk());

        // Validate the CompetitiveExam in the database
        List<CompetitiveExam> competitiveExamList = competitiveExamRepository.findAll();
        assertThat(competitiveExamList).hasSize(databaseSizeBeforeUpdate);
        CompetitiveExam testCompetitiveExam = competitiveExamList.get(competitiveExamList.size() - 1);
        assertThat(testCompetitiveExam.getTestName()).isEqualTo(UPDATED_TEST_NAME);
        assertThat(testCompetitiveExam.getTestScore()).isEqualTo(UPDATED_TEST_SCORE);
        assertThat(testCompetitiveExam.getEnrollmentNo()).isEqualTo(UPDATED_ENROLLMENT_NO);
        assertThat(testCompetitiveExam.getRank()).isEqualTo(UPDATED_RANK);

    }

    @Test
    @Transactional
    public void updateNonExistingCompetitiveExam() throws Exception {
        int databaseSizeBeforeUpdate = competitiveExamRepository.findAll().size();

        // Create the CompetitiveExam
        CompetitiveExamDTO competitiveExamDTO = competitiveExamMapper.toDto(competitiveExam);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCompetitiveExamMockMvc.perform(put("/api/competitive-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(competitiveExamDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CompetitiveExam in the database
        List<CompetitiveExam> competitiveExamList = competitiveExamRepository.findAll();
        assertThat(competitiveExamList).hasSize(databaseSizeBeforeUpdate);

    }

    @Test
    @Transactional
    public void deleteCompetitiveExam() throws Exception {
        // Initialize the database
        competitiveExamRepository.saveAndFlush(competitiveExam);

        int databaseSizeBeforeDelete = competitiveExamRepository.findAll().size();

        // Delete the competitiveExam
        restCompetitiveExamMockMvc.perform(delete("/api/competitive-exams/{id}", competitiveExam.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CompetitiveExam> competitiveExamList = competitiveExamRepository.findAll();
        assertThat(competitiveExamList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchCompetitiveExam() throws Exception {
        // Initialize the database
        competitiveExamRepository.saveAndFlush(competitiveExam);
        // Search the competitiveExam
        restCompetitiveExamMockMvc.perform(get("/api/_search/competitive-exams?query=id:" + competitiveExam.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(competitiveExam.getId().intValue())))
            .andExpect(jsonPath("$.[*].testName").value(hasItem(DEFAULT_TEST_NAME)))
            .andExpect(jsonPath("$.[*].testScore").value(hasItem(DEFAULT_TEST_SCORE)))
            .andExpect(jsonPath("$.[*].enrollmentNo").value(hasItem(DEFAULT_ENROLLMENT_NO.intValue())))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CompetitiveExam.class);
        CompetitiveExam competitiveExam1 = new CompetitiveExam();
        competitiveExam1.setId(1L);
        CompetitiveExam competitiveExam2 = new CompetitiveExam();
        competitiveExam2.setId(competitiveExam1.getId());
        assertThat(competitiveExam1).isEqualTo(competitiveExam2);
        competitiveExam2.setId(2L);
        assertThat(competitiveExam1).isNotEqualTo(competitiveExam2);
        competitiveExam1.setId(null);
        assertThat(competitiveExam1).isNotEqualTo(competitiveExam2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CompetitiveExamDTO.class);
        CompetitiveExamDTO competitiveExamDTO1 = new CompetitiveExamDTO();
        competitiveExamDTO1.setId(1L);
        CompetitiveExamDTO competitiveExamDTO2 = new CompetitiveExamDTO();
        assertThat(competitiveExamDTO1).isNotEqualTo(competitiveExamDTO2);
        competitiveExamDTO2.setId(competitiveExamDTO1.getId());
        assertThat(competitiveExamDTO1).isEqualTo(competitiveExamDTO2);
        competitiveExamDTO2.setId(2L);
        assertThat(competitiveExamDTO1).isNotEqualTo(competitiveExamDTO2);
        competitiveExamDTO1.setId(null);
        assertThat(competitiveExamDTO1).isNotEqualTo(competitiveExamDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(competitiveExamMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(competitiveExamMapper.fromId(null)).isNull();
    }
}
