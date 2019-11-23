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

import com.synectiks.cms.CmsApp;
import com.synectiks.commons.entities.cms.FeeCategory;
import com.synectiks.commons.entities.cms.enumeration.Status;
import com.synectiks.cms.repository.FeeCategoryRepository;
import com.synectiks.cms.service.FeeCategoryService;
import com.synectiks.cms.service.dto.FeeCategoryDTO;
import com.synectiks.cms.service.mapper.FeeCategoryMapper;
import com.synectiks.cms.web.rest.errors.ExceptionTranslator;
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

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.DEACTIVE;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private FeeCategoryRepository feeCategoryRepository;


    @Autowired
    private FeeCategoryMapper feeCategoryMapper;
    

    @Autowired
    private FeeCategoryService feeCategoryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

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
            .setMessageConverters(jacksonMessageConverter).build();
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
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE);
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
        assertThat(testFeeCategory.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testFeeCategory.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testFeeCategory.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testFeeCategory.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testFeeCategory.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
        assertThat(testFeeCategory.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testFeeCategory.getEndDate()).isEqualTo(DEFAULT_END_DATE);
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
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = feeCategoryRepository.findAll().size();
        // set the field null
        feeCategory.setStatus(null);

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
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())));
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
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()));
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
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE);
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
        assertThat(testFeeCategory.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFeeCategory.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testFeeCategory.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testFeeCategory.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testFeeCategory.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
        assertThat(testFeeCategory.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testFeeCategory.getEndDate()).isEqualTo(UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingFeeCategory() throws Exception {
        int databaseSizeBeforeUpdate = feeCategoryRepository.findAll().size();

        // Create the FeeCategory
        FeeCategoryDTO feeCategoryDTO = feeCategoryMapper.toDto(feeCategory);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFeeCategoryMockMvc.perform(put("/api/fee-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feeCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FeeCategory in the database
        List<FeeCategory> feeCategoryList = feeCategoryRepository.findAll();
        assertThat(feeCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFeeCategory() throws Exception {
        // Initialize the database
        feeCategoryRepository.saveAndFlush(feeCategory);

        int databaseSizeBeforeDelete = feeCategoryRepository.findAll().size();

        // Get the feeCategory
        restFeeCategoryMockMvc.perform(delete("/api/fee-categories/{id}", feeCategory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FeeCategory> feeCategoryList = feeCategoryRepository.findAll();
        assertThat(feeCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchFeeCategory() throws Exception {
        // Initialize the database
        feeCategoryRepository.saveAndFlush(feeCategory);
        // Search the feeCategory
        restFeeCategoryMockMvc.perform(get("/api/_search/fee-categories?query=id:" + feeCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(feeCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].categoryName").value(hasItem(DEFAULT_CATEGORY_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())));
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
