package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.CollegeBranches;
import com.synectiks.cms.repository.CollegeBranchesRepository;
import com.synectiks.cms.repository.search.CollegeBranchesSearchRepository;
import com.synectiks.cms.service.CollegeBranchesService;
import com.synectiks.cms.service.dto.CollegeBranchesDTO;
import com.synectiks.cms.service.mapper.CollegeBranchesMapper;
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

/**
 * Test class for the CollegeBranchesResource REST controller.
 *
 * @see CollegeBranchesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class CollegeBranchesResourceIntTest {

    private static final String DEFAULT_BRANCH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_COLLEGE_HEAD = "AAAAAAAAAA";
    private static final String UPDATED_COLLEGE_HEAD = "BBBBBBBBBB";

    @Autowired
    private CollegeBranchesRepository collegeBranchesRepository;


    @Autowired
    private CollegeBranchesMapper collegeBranchesMapper;
    

    @Autowired
    private CollegeBranchesService collegeBranchesService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.CollegeBranchesSearchRepositoryMockConfiguration
     */
    @Autowired
    private CollegeBranchesSearchRepository mockCollegeBranchesSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCollegeBranchesMockMvc;

    private CollegeBranches collegeBranches;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CollegeBranchesResource collegeBranchesResource = new CollegeBranchesResource(collegeBranchesService);
        this.restCollegeBranchesMockMvc = MockMvcBuilders.standaloneSetup(collegeBranchesResource)
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
    public static CollegeBranches createEntity(EntityManager em) {
        CollegeBranches collegeBranches = new CollegeBranches()
            .branchName(DEFAULT_BRANCH_NAME)
            .description(DEFAULT_DESCRIPTION)
            .collegeHead(DEFAULT_COLLEGE_HEAD);
        return collegeBranches;
    }

    @Before
    public void initTest() {
        collegeBranches = createEntity(em);
    }

    @Test
    @Transactional
    public void createCollegeBranches() throws Exception {
        int databaseSizeBeforeCreate = collegeBranchesRepository.findAll().size();

        // Create the CollegeBranches
        CollegeBranchesDTO collegeBranchesDTO = collegeBranchesMapper.toDto(collegeBranches);
        restCollegeBranchesMockMvc.perform(post("/api/college-branches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(collegeBranchesDTO)))
            .andExpect(status().isCreated());

        // Validate the CollegeBranches in the database
        List<CollegeBranches> collegeBranchesList = collegeBranchesRepository.findAll();
        assertThat(collegeBranchesList).hasSize(databaseSizeBeforeCreate + 1);
        CollegeBranches testCollegeBranches = collegeBranchesList.get(collegeBranchesList.size() - 1);
        assertThat(testCollegeBranches.getBranchName()).isEqualTo(DEFAULT_BRANCH_NAME);
        assertThat(testCollegeBranches.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCollegeBranches.getCollegeHead()).isEqualTo(DEFAULT_COLLEGE_HEAD);

        // Validate the CollegeBranches in Elasticsearch
        verify(mockCollegeBranchesSearchRepository, times(1)).save(testCollegeBranches);
    }

    @Test
    @Transactional
    public void createCollegeBranchesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = collegeBranchesRepository.findAll().size();

        // Create the CollegeBranches with an existing ID
        collegeBranches.setId(1L);
        CollegeBranchesDTO collegeBranchesDTO = collegeBranchesMapper.toDto(collegeBranches);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCollegeBranchesMockMvc.perform(post("/api/college-branches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(collegeBranchesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CollegeBranches in the database
        List<CollegeBranches> collegeBranchesList = collegeBranchesRepository.findAll();
        assertThat(collegeBranchesList).hasSize(databaseSizeBeforeCreate);

        // Validate the CollegeBranches in Elasticsearch
        verify(mockCollegeBranchesSearchRepository, times(0)).save(collegeBranches);
    }

    @Test
    @Transactional
    public void checkBranchNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = collegeBranchesRepository.findAll().size();
        // set the field null
        collegeBranches.setBranchName(null);

        // Create the CollegeBranches, which fails.
        CollegeBranchesDTO collegeBranchesDTO = collegeBranchesMapper.toDto(collegeBranches);

        restCollegeBranchesMockMvc.perform(post("/api/college-branches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(collegeBranchesDTO)))
            .andExpect(status().isBadRequest());

        List<CollegeBranches> collegeBranchesList = collegeBranchesRepository.findAll();
        assertThat(collegeBranchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = collegeBranchesRepository.findAll().size();
        // set the field null
        collegeBranches.setDescription(null);

        // Create the CollegeBranches, which fails.
        CollegeBranchesDTO collegeBranchesDTO = collegeBranchesMapper.toDto(collegeBranches);

        restCollegeBranchesMockMvc.perform(post("/api/college-branches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(collegeBranchesDTO)))
            .andExpect(status().isBadRequest());

        List<CollegeBranches> collegeBranchesList = collegeBranchesRepository.findAll();
        assertThat(collegeBranchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCollegeHeadIsRequired() throws Exception {
        int databaseSizeBeforeTest = collegeBranchesRepository.findAll().size();
        // set the field null
        collegeBranches.setCollegeHead(null);

        // Create the CollegeBranches, which fails.
        CollegeBranchesDTO collegeBranchesDTO = collegeBranchesMapper.toDto(collegeBranches);

        restCollegeBranchesMockMvc.perform(post("/api/college-branches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(collegeBranchesDTO)))
            .andExpect(status().isBadRequest());

        List<CollegeBranches> collegeBranchesList = collegeBranchesRepository.findAll();
        assertThat(collegeBranchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCollegeBranches() throws Exception {
        // Initialize the database
        collegeBranchesRepository.saveAndFlush(collegeBranches);

        // Get all the collegeBranchesList
        restCollegeBranchesMockMvc.perform(get("/api/college-branches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(collegeBranches.getId().intValue())))
            .andExpect(jsonPath("$.[*].branchName").value(hasItem(DEFAULT_BRANCH_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].collegeHead").value(hasItem(DEFAULT_COLLEGE_HEAD.toString())));
    }
    

    @Test
    @Transactional
    public void getCollegeBranches() throws Exception {
        // Initialize the database
        collegeBranchesRepository.saveAndFlush(collegeBranches);

        // Get the collegeBranches
        restCollegeBranchesMockMvc.perform(get("/api/college-branches/{id}", collegeBranches.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(collegeBranches.getId().intValue()))
            .andExpect(jsonPath("$.branchName").value(DEFAULT_BRANCH_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.collegeHead").value(DEFAULT_COLLEGE_HEAD.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCollegeBranches() throws Exception {
        // Get the collegeBranches
        restCollegeBranchesMockMvc.perform(get("/api/college-branches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCollegeBranches() throws Exception {
        // Initialize the database
        collegeBranchesRepository.saveAndFlush(collegeBranches);

        int databaseSizeBeforeUpdate = collegeBranchesRepository.findAll().size();

        // Update the collegeBranches
        CollegeBranches updatedCollegeBranches = collegeBranchesRepository.findById(collegeBranches.getId()).get();
        // Disconnect from session so that the updates on updatedCollegeBranches are not directly saved in db
        em.detach(updatedCollegeBranches);
        updatedCollegeBranches
            .branchName(UPDATED_BRANCH_NAME)
            .description(UPDATED_DESCRIPTION)
            .collegeHead(UPDATED_COLLEGE_HEAD);
        CollegeBranchesDTO collegeBranchesDTO = collegeBranchesMapper.toDto(updatedCollegeBranches);

        restCollegeBranchesMockMvc.perform(put("/api/college-branches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(collegeBranchesDTO)))
            .andExpect(status().isOk());

        // Validate the CollegeBranches in the database
        List<CollegeBranches> collegeBranchesList = collegeBranchesRepository.findAll();
        assertThat(collegeBranchesList).hasSize(databaseSizeBeforeUpdate);
        CollegeBranches testCollegeBranches = collegeBranchesList.get(collegeBranchesList.size() - 1);
        assertThat(testCollegeBranches.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testCollegeBranches.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCollegeBranches.getCollegeHead()).isEqualTo(UPDATED_COLLEGE_HEAD);

        // Validate the CollegeBranches in Elasticsearch
        verify(mockCollegeBranchesSearchRepository, times(1)).save(testCollegeBranches);
    }

    @Test
    @Transactional
    public void updateNonExistingCollegeBranches() throws Exception {
        int databaseSizeBeforeUpdate = collegeBranchesRepository.findAll().size();

        // Create the CollegeBranches
        CollegeBranchesDTO collegeBranchesDTO = collegeBranchesMapper.toDto(collegeBranches);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCollegeBranchesMockMvc.perform(put("/api/college-branches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(collegeBranchesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CollegeBranches in the database
        List<CollegeBranches> collegeBranchesList = collegeBranchesRepository.findAll();
        assertThat(collegeBranchesList).hasSize(databaseSizeBeforeUpdate);

        // Validate the CollegeBranches in Elasticsearch
        verify(mockCollegeBranchesSearchRepository, times(0)).save(collegeBranches);
    }

    @Test
    @Transactional
    public void deleteCollegeBranches() throws Exception {
        // Initialize the database
        collegeBranchesRepository.saveAndFlush(collegeBranches);

        int databaseSizeBeforeDelete = collegeBranchesRepository.findAll().size();

        // Get the collegeBranches
        restCollegeBranchesMockMvc.perform(delete("/api/college-branches/{id}", collegeBranches.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CollegeBranches> collegeBranchesList = collegeBranchesRepository.findAll();
        assertThat(collegeBranchesList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the CollegeBranches in Elasticsearch
        verify(mockCollegeBranchesSearchRepository, times(1)).deleteById(collegeBranches.getId());
    }

    @Test
    @Transactional
    public void searchCollegeBranches() throws Exception {
        // Initialize the database
        collegeBranchesRepository.saveAndFlush(collegeBranches);
        when(mockCollegeBranchesSearchRepository.search(queryStringQuery("id:" + collegeBranches.getId())))
            .thenReturn(Collections.singletonList(collegeBranches));
        // Search the collegeBranches
        restCollegeBranchesMockMvc.perform(get("/api/_search/college-branches?query=id:" + collegeBranches.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(collegeBranches.getId().intValue())))
            .andExpect(jsonPath("$.[*].branchName").value(hasItem(DEFAULT_BRANCH_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].collegeHead").value(hasItem(DEFAULT_COLLEGE_HEAD.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CollegeBranches.class);
        CollegeBranches collegeBranches1 = new CollegeBranches();
        collegeBranches1.setId(1L);
        CollegeBranches collegeBranches2 = new CollegeBranches();
        collegeBranches2.setId(collegeBranches1.getId());
        assertThat(collegeBranches1).isEqualTo(collegeBranches2);
        collegeBranches2.setId(2L);
        assertThat(collegeBranches1).isNotEqualTo(collegeBranches2);
        collegeBranches1.setId(null);
        assertThat(collegeBranches1).isNotEqualTo(collegeBranches2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CollegeBranchesDTO.class);
        CollegeBranchesDTO collegeBranchesDTO1 = new CollegeBranchesDTO();
        collegeBranchesDTO1.setId(1L);
        CollegeBranchesDTO collegeBranchesDTO2 = new CollegeBranchesDTO();
        assertThat(collegeBranchesDTO1).isNotEqualTo(collegeBranchesDTO2);
        collegeBranchesDTO2.setId(collegeBranchesDTO1.getId());
        assertThat(collegeBranchesDTO1).isEqualTo(collegeBranchesDTO2);
        collegeBranchesDTO2.setId(2L);
        assertThat(collegeBranchesDTO1).isNotEqualTo(collegeBranchesDTO2);
        collegeBranchesDTO1.setId(null);
        assertThat(collegeBranchesDTO1).isNotEqualTo(collegeBranchesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(collegeBranchesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(collegeBranchesMapper.fromId(null)).isNull();
    }
}
