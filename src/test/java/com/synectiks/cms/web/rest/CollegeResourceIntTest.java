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

import com.synectiks.cms.CmsApp;
import com.synectiks.cms.entities.College;
import com.synectiks.cms.repositories.CollegeRepository;
import com.synectiks.cms.service.CollegeService;
import com.synectiks.cms.service.dto.CollegeDTO;
import com.synectiks.cms.service.mapper.CollegeMapper;
import com.synectiks.cms.web.rest.errors.ExceptionTranslator;

/**
 * Test class for the CollegeResource REST controller.
 *
 * @see CollegeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class CollegeResourceIntTest {

    private static final String DEFAULT_SHORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LOGO_PATH = "AAAAAAAAAA";
    private static final String UPDATED_LOGO_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_BACKGROUND_IMAGE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_BACKGROUND_IMAGE_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_INSTRUCTION_INFORMATION = "AAAAAAAAAA";
    private static final String UPDATED_INSTRUCTION_INFORMATION = "BBBBBBBBBB";

    private static final String DEFAULT_LOGO_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LOGO_FILE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BACKGROUND_IMAGE_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BACKGROUND_IMAGE_FILE_NAME = "BBBBBBBBBB";

    @Autowired
    private CollegeRepository collegeRepository;


    @Autowired
    private CollegeMapper collegeMapper;
    

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCollegeMockMvc;

    private College college;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CollegeResource collegeResource = new CollegeResource(collegeService);
        this.restCollegeMockMvc = MockMvcBuilders.standaloneSetup(collegeResource)
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
    public static College createEntity(EntityManager em) {
        College college = new College()
            .shortName(DEFAULT_SHORT_NAME)
            .logoPath(DEFAULT_LOGO_PATH)
            .backgroundImagePath(DEFAULT_BACKGROUND_IMAGE_PATH)
            .instructionInformation(DEFAULT_INSTRUCTION_INFORMATION)
            .logoFileName(DEFAULT_LOGO_FILE_NAME)
            .backgroundImageFileName(DEFAULT_BACKGROUND_IMAGE_FILE_NAME);
        return college;
    }

    @Before
    public void initTest() {
        college = createEntity(em);
    }

    @Test
    @Transactional
    public void createCollege() throws Exception {
        int databaseSizeBeforeCreate = collegeRepository.findAll().size();

        // Create the College
        CollegeDTO collegeDTO = collegeMapper.toDto(college);
        restCollegeMockMvc.perform(post("/api/colleges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(collegeDTO)))
            .andExpect(status().isCreated());

        // Validate the College in the database
        List<College> collegeList = collegeRepository.findAll();
        assertThat(collegeList).hasSize(databaseSizeBeforeCreate + 1);
        College testCollege = collegeList.get(collegeList.size() - 1);
        assertThat(testCollege.getShortName()).isEqualTo(DEFAULT_SHORT_NAME);
        assertThat(testCollege.getLogoPath()).isEqualTo(DEFAULT_LOGO_PATH);
        assertThat(testCollege.getBackgroundImagePath()).isEqualTo(DEFAULT_BACKGROUND_IMAGE_PATH);
        assertThat(testCollege.getInstructionInformation()).isEqualTo(DEFAULT_INSTRUCTION_INFORMATION);
        assertThat(testCollege.getLogoFileName()).isEqualTo(DEFAULT_LOGO_FILE_NAME);
        assertThat(testCollege.getBackgroundImageFileName()).isEqualTo(DEFAULT_BACKGROUND_IMAGE_FILE_NAME);

    }

    @Test
    @Transactional
    public void createCollegeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = collegeRepository.findAll().size();

        // Create the College with an existing ID
        college.setId(1L);
        CollegeDTO collegeDTO = collegeMapper.toDto(college);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCollegeMockMvc.perform(post("/api/colleges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(collegeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the College in the database
        List<College> collegeList = collegeRepository.findAll();
        assertThat(collegeList).hasSize(databaseSizeBeforeCreate);

    }

    @Test
    @Transactional
    public void checkShortNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = collegeRepository.findAll().size();
        // set the field null
        college.setShortName(null);

        // Create the College, which fails.
        CollegeDTO collegeDTO = collegeMapper.toDto(college);

        restCollegeMockMvc.perform(post("/api/colleges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(collegeDTO)))
            .andExpect(status().isBadRequest());

        List<College> collegeList = collegeRepository.findAll();
        assertThat(collegeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllColleges() throws Exception {
        // Initialize the database
        collegeRepository.saveAndFlush(college);

        // Get all the collegeList
        restCollegeMockMvc.perform(get("/api/colleges?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(college.getId().intValue())))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME.toString())))
            .andExpect(jsonPath("$.[*].logoPath").value(hasItem(DEFAULT_LOGO_PATH.toString())))
            .andExpect(jsonPath("$.[*].backgroundImagePath").value(hasItem(DEFAULT_BACKGROUND_IMAGE_PATH.toString())))
            .andExpect(jsonPath("$.[*].instructionInformation").value(hasItem(DEFAULT_INSTRUCTION_INFORMATION.toString())))
            .andExpect(jsonPath("$.[*].logoFileName").value(hasItem(DEFAULT_LOGO_FILE_NAME.toString())))
            .andExpect(jsonPath("$.[*].backgroundImageFileName").value(hasItem(DEFAULT_BACKGROUND_IMAGE_FILE_NAME.toString())));
    }
    

    @Test
    @Transactional
    public void getCollege() throws Exception {
        // Initialize the database
        collegeRepository.saveAndFlush(college);

        // Get the college
        restCollegeMockMvc.perform(get("/api/colleges/{id}", college.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(college.getId().intValue()))
            .andExpect(jsonPath("$.shortName").value(DEFAULT_SHORT_NAME.toString()))
            .andExpect(jsonPath("$.logoPath").value(DEFAULT_LOGO_PATH.toString()))
            .andExpect(jsonPath("$.backgroundImagePath").value(DEFAULT_BACKGROUND_IMAGE_PATH.toString()))
            .andExpect(jsonPath("$.instructionInformation").value(DEFAULT_INSTRUCTION_INFORMATION.toString()))
            .andExpect(jsonPath("$.logoFileName").value(DEFAULT_LOGO_FILE_NAME.toString()))
            .andExpect(jsonPath("$.backgroundImageFileName").value(DEFAULT_BACKGROUND_IMAGE_FILE_NAME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCollege() throws Exception {
        // Get the college
        restCollegeMockMvc.perform(get("/api/colleges/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCollege() throws Exception {
        // Initialize the database
        collegeRepository.saveAndFlush(college);

        int databaseSizeBeforeUpdate = collegeRepository.findAll().size();

        // Update the college
        College updatedCollege = collegeRepository.findById(college.getId()).get();
        // Disconnect from session so that the updates on updatedCollege are not directly saved in db
        em.detach(updatedCollege);
        updatedCollege
            .shortName(UPDATED_SHORT_NAME)
            .logoPath(UPDATED_LOGO_PATH)
            .backgroundImagePath(UPDATED_BACKGROUND_IMAGE_PATH)
            .instructionInformation(UPDATED_INSTRUCTION_INFORMATION)
            .logoFileName(UPDATED_LOGO_FILE_NAME)
            .backgroundImageFileName(UPDATED_BACKGROUND_IMAGE_FILE_NAME);
        CollegeDTO collegeDTO = collegeMapper.toDto(updatedCollege);

        restCollegeMockMvc.perform(put("/api/colleges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(collegeDTO)))
            .andExpect(status().isOk());

        // Validate the College in the database
        List<College> collegeList = collegeRepository.findAll();
        assertThat(collegeList).hasSize(databaseSizeBeforeUpdate);
        College testCollege = collegeList.get(collegeList.size() - 1);
        assertThat(testCollege.getShortName()).isEqualTo(UPDATED_SHORT_NAME);
        assertThat(testCollege.getLogoPath()).isEqualTo(UPDATED_LOGO_PATH);
        assertThat(testCollege.getBackgroundImagePath()).isEqualTo(UPDATED_BACKGROUND_IMAGE_PATH);
        assertThat(testCollege.getInstructionInformation()).isEqualTo(UPDATED_INSTRUCTION_INFORMATION);
        assertThat(testCollege.getLogoFileName()).isEqualTo(UPDATED_LOGO_FILE_NAME);
        assertThat(testCollege.getBackgroundImageFileName()).isEqualTo(UPDATED_BACKGROUND_IMAGE_FILE_NAME);

    }

    @Test
    @Transactional
    public void updateNonExistingCollege() throws Exception {
        int databaseSizeBeforeUpdate = collegeRepository.findAll().size();

        // Create the College
        CollegeDTO collegeDTO = collegeMapper.toDto(college);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCollegeMockMvc.perform(put("/api/colleges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(collegeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the College in the database
        List<College> collegeList = collegeRepository.findAll();
        assertThat(collegeList).hasSize(databaseSizeBeforeUpdate);

    }

    @Test
    @Transactional
    public void deleteCollege() throws Exception {
        // Initialize the database
        collegeRepository.saveAndFlush(college);

        int databaseSizeBeforeDelete = collegeRepository.findAll().size();

        // Get the college
        restCollegeMockMvc.perform(delete("/api/colleges/{id}", college.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<College> collegeList = collegeRepository.findAll();
        assertThat(collegeList).hasSize(databaseSizeBeforeDelete - 1);

    }

    @Test
    @Transactional
    public void searchCollege() throws Exception {
        // Initialize the database
        collegeRepository.saveAndFlush(college);
        // Search the college
        restCollegeMockMvc.perform(get("/api/_search/colleges?query=id:" + college.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(college.getId().intValue())))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME.toString())))
            .andExpect(jsonPath("$.[*].logoPath").value(hasItem(DEFAULT_LOGO_PATH.toString())))
            .andExpect(jsonPath("$.[*].backgroundImagePath").value(hasItem(DEFAULT_BACKGROUND_IMAGE_PATH.toString())))
            .andExpect(jsonPath("$.[*].instructionInformation").value(hasItem(DEFAULT_INSTRUCTION_INFORMATION.toString())))
            .andExpect(jsonPath("$.[*].logoFileName").value(hasItem(DEFAULT_LOGO_FILE_NAME.toString())))
            .andExpect(jsonPath("$.[*].backgroundImageFileName").value(hasItem(DEFAULT_BACKGROUND_IMAGE_FILE_NAME.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(College.class);
        College college1 = new College();
        college1.setId(1L);
        College college2 = new College();
        college2.setId(college1.getId());
        assertThat(college1).isEqualTo(college2);
        college2.setId(2L);
        assertThat(college1).isNotEqualTo(college2);
        college1.setId(null);
        assertThat(college1).isNotEqualTo(college2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CollegeDTO.class);
        CollegeDTO collegeDTO1 = new CollegeDTO();
        collegeDTO1.setId(1L);
        CollegeDTO collegeDTO2 = new CollegeDTO();
        assertThat(collegeDTO1).isNotEqualTo(collegeDTO2);
        collegeDTO2.setId(collegeDTO1.getId());
        assertThat(collegeDTO1).isEqualTo(collegeDTO2);
        collegeDTO2.setId(2L);
        assertThat(collegeDTO1).isNotEqualTo(collegeDTO2);
        collegeDTO1.setId(null);
        assertThat(collegeDTO1).isNotEqualTo(collegeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(collegeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(collegeMapper.fromId(null)).isNull();
    }
}
