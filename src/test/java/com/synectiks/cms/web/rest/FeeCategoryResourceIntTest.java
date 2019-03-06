package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.FeeCategory;
import com.synectiks.cms.repository.FeeCategoryRepository;
import com.synectiks.cms.repository.search.FeeCategorySearchRepository;
import com.synectiks.cms.service.FeeCategoryService;
import com.synectiks.cms.service.dto.FeeCategoryDTO;
import com.synectiks.cms.service.mapper.FeeCategoryMapper;
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
 * Test class for the FeeCategoryResource REST controller.
 *
 * @see FeeCategoryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class FeeCategoryResourceIntTest {

    private static final String DEFAULT_CATEGORY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private FeeCategoryRepository feeCategoryRepository;

    @Autowired
    private FeeCategoryMapper feeCategoryMapper;

    @Autowired
    private FeeCategoryService feeCategoryService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.FeeCategorySearchRepositoryMockConfiguration
     */
    @Autowired
    private FeeCategorySearchRepository mockFeeCategorySearchRepository;

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

    private MockMvc restFeeCategoryMockMvc;

    private FeeCategory feeCategory;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FeeCategoryResource feeCategoryResource = new FeeCategoryResource(feeCategoryService);
        this.restFeeCategoryMockMvc = MockMvcBuilders.standaloneSetup(feeCategoryResource)
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
    public static FeeCategory createEntity(EntityManager em) {
        FeeCategory feeCategory = new FeeCategory()
            .categoryName(DEFAULT_CATEGORY_NAME)
            .description(DEFAULT_DESCRIPTION);
        return feeCategory;
    }

    @Before
    public void initTest() {
        feeCategory = createEntity(em);
    }

    @Test
    @Transactional
    public void createFeeCategory() throws Exception {
        int databaseSizeBeforeCreate = feeCategoryRepository.findAll().size();

        // Create the FeeCategory
        FeeCategoryDTO feeCategoryDTO = feeCategoryMapper.toDto(feeCategory);
        restFeeCategoryMockMvc.perform(post("/api/fee-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feeCategoryDTO)))
            .andExpect(status().isCreated());

        // Validate the FeeCategory in the database
        List<FeeCategory> feeCategoryList = feeCategoryRepository.findAll();
        assertThat(feeCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        FeeCategory testFeeCategory = feeCategoryList.get(feeCategoryList.size() - 1);
        assertThat(testFeeCategory.getCategoryName()).isEqualTo(DEFAULT_CATEGORY_NAME);
        assertThat(testFeeCategory.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);

        // Validate the FeeCategory in Elasticsearch
        verify(mockFeeCategorySearchRepository, times(1)).save(testFeeCategory);
    }

    @Test
    @Transactional
    public void createFeeCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = feeCategoryRepository.findAll().size();

        // Create the FeeCategory with an existing ID
        feeCategory.setId(1L);
        FeeCategoryDTO feeCategoryDTO = feeCategoryMapper.toDto(feeCategory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFeeCategoryMockMvc.perform(post("/api/fee-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feeCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FeeCategory in the database
        List<FeeCategory> feeCategoryList = feeCategoryRepository.findAll();
        assertThat(feeCategoryList).hasSize(databaseSizeBeforeCreate);

        // Validate the FeeCategory in Elasticsearch
        verify(mockFeeCategorySearchRepository, times(0)).save(feeCategory);
    }

    @Test
    @Transactional
    public void checkCategoryNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = feeCategoryRepository.findAll().size();
        // set the field null
        feeCategory.setCategoryName(null);

        // Create the FeeCategory, which fails.
        FeeCategoryDTO feeCategoryDTO = feeCategoryMapper.toDto(feeCategory);

        restFeeCategoryMockMvc.perform(post("/api/fee-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feeCategoryDTO)))
            .andExpect(status().isBadRequest());

        List<FeeCategory> feeCategoryList = feeCategoryRepository.findAll();
        assertThat(feeCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = feeCategoryRepository.findAll().size();
        // set the field null
        feeCategory.setDescription(null);

        // Create the FeeCategory, which fails.
        FeeCategoryDTO feeCategoryDTO = feeCategoryMapper.toDto(feeCategory);

        restFeeCategoryMockMvc.perform(post("/api/fee-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feeCategoryDTO)))
            .andExpect(status().isBadRequest());

        List<FeeCategory> feeCategoryList = feeCategoryRepository.findAll();
        assertThat(feeCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFeeCategories() throws Exception {
        // Initialize the database
        feeCategoryRepository.saveAndFlush(feeCategory);

        // Get all the feeCategoryList
        restFeeCategoryMockMvc.perform(get("/api/fee-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(feeCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].categoryName").value(hasItem(DEFAULT_CATEGORY_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getFeeCategory() throws Exception {
        // Initialize the database
        feeCategoryRepository.saveAndFlush(feeCategory);

        // Get the feeCategory
        restFeeCategoryMockMvc.perform(get("/api/fee-categories/{id}", feeCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(feeCategory.getId().intValue()))
            .andExpect(jsonPath("$.categoryName").value(DEFAULT_CATEGORY_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFeeCategory() throws Exception {
        // Get the feeCategory
        restFeeCategoryMockMvc.perform(get("/api/fee-categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFeeCategory() throws Exception {
        // Initialize the database
        feeCategoryRepository.saveAndFlush(feeCategory);

        int databaseSizeBeforeUpdate = feeCategoryRepository.findAll().size();

        // Update the feeCategory
        FeeCategory updatedFeeCategory = feeCategoryRepository.findById(feeCategory.getId()).get();
        // Disconnect from session so that the updates on updatedFeeCategory are not directly saved in db
        em.detach(updatedFeeCategory);
        updatedFeeCategory
            .categoryName(UPDATED_CATEGORY_NAME)
            .description(UPDATED_DESCRIPTION);
        FeeCategoryDTO feeCategoryDTO = feeCategoryMapper.toDto(updatedFeeCategory);

        restFeeCategoryMockMvc.perform(put("/api/fee-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feeCategoryDTO)))
            .andExpect(status().isOk());

        // Validate the FeeCategory in the database
        List<FeeCategory> feeCategoryList = feeCategoryRepository.findAll();
        assertThat(feeCategoryList).hasSize(databaseSizeBeforeUpdate);
        FeeCategory testFeeCategory = feeCategoryList.get(feeCategoryList.size() - 1);
        assertThat(testFeeCategory.getCategoryName()).isEqualTo(UPDATED_CATEGORY_NAME);
        assertThat(testFeeCategory.getDescription()).isEqualTo(UPDATED_DESCRIPTION);

        // Validate the FeeCategory in Elasticsearch
        verify(mockFeeCategorySearchRepository, times(1)).save(testFeeCategory);
    }

    @Test
    @Transactional
    public void updateNonExistingFeeCategory() throws Exception {
        int databaseSizeBeforeUpdate = feeCategoryRepository.findAll().size();

        // Create the FeeCategory
        FeeCategoryDTO feeCategoryDTO = feeCategoryMapper.toDto(feeCategory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFeeCategoryMockMvc.perform(put("/api/fee-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feeCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FeeCategory in the database
        List<FeeCategory> feeCategoryList = feeCategoryRepository.findAll();
        assertThat(feeCategoryList).hasSize(databaseSizeBeforeUpdate);

        // Validate the FeeCategory in Elasticsearch
        verify(mockFeeCategorySearchRepository, times(0)).save(feeCategory);
    }

    @Test
    @Transactional
    public void deleteFeeCategory() throws Exception {
        // Initialize the database
        feeCategoryRepository.saveAndFlush(feeCategory);

        int databaseSizeBeforeDelete = feeCategoryRepository.findAll().size();

        // Delete the feeCategory
        restFeeCategoryMockMvc.perform(delete("/api/fee-categories/{id}", feeCategory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FeeCategory> feeCategoryList = feeCategoryRepository.findAll();
        assertThat(feeCategoryList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the FeeCategory in Elasticsearch
        verify(mockFeeCategorySearchRepository, times(1)).deleteById(feeCategory.getId());
    }

    @Test
    @Transactional
    public void searchFeeCategory() throws Exception {
        // Initialize the database
        feeCategoryRepository.saveAndFlush(feeCategory);
        when(mockFeeCategorySearchRepository.search(queryStringQuery("id:" + feeCategory.getId())))
            .thenReturn(Collections.singletonList(feeCategory));
        // Search the feeCategory
        restFeeCategoryMockMvc.perform(get("/api/_search/fee-categories?query=id:" + feeCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(feeCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].categoryName").value(hasItem(DEFAULT_CATEGORY_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FeeCategory.class);
        FeeCategory feeCategory1 = new FeeCategory();
        feeCategory1.setId(1L);
        FeeCategory feeCategory2 = new FeeCategory();
        feeCategory2.setId(feeCategory1.getId());
        assertThat(feeCategory1).isEqualTo(feeCategory2);
        feeCategory2.setId(2L);
        assertThat(feeCategory1).isNotEqualTo(feeCategory2);
        feeCategory1.setId(null);
        assertThat(feeCategory1).isNotEqualTo(feeCategory2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FeeCategoryDTO.class);
        FeeCategoryDTO feeCategoryDTO1 = new FeeCategoryDTO();
        feeCategoryDTO1.setId(1L);
        FeeCategoryDTO feeCategoryDTO2 = new FeeCategoryDTO();
        assertThat(feeCategoryDTO1).isNotEqualTo(feeCategoryDTO2);
        feeCategoryDTO2.setId(feeCategoryDTO1.getId());
        assertThat(feeCategoryDTO1).isEqualTo(feeCategoryDTO2);
        feeCategoryDTO2.setId(2L);
        assertThat(feeCategoryDTO1).isNotEqualTo(feeCategoryDTO2);
        feeCategoryDTO1.setId(null);
        assertThat(feeCategoryDTO1).isNotEqualTo(feeCategoryDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(feeCategoryMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(feeCategoryMapper.fromId(null)).isNull();
    }
}
