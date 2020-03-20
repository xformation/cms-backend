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
import com.synectiks.cms.domain.Batch;
import com.synectiks.cms.domain.enumeration.BatchEnum;
import com.synectiks.cms.repository.BatchRepository;
import com.synectiks.cms.repository.search.BatchSearchRepository;
import com.synectiks.cms.service.BatchService;
import com.synectiks.cms.service.dto.BatchDTO;
import com.synectiks.cms.service.mapper.BatchMapper;
import com.synectiks.cms.web.rest.errors.ExceptionTranslator;
/**
 * Test class for the BatchResource REST controller.
 *
 * @see BatchResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class BatchResourceIntTest {

    private static final BatchEnum DEFAULT_BATCH = BatchEnum.FIRSTYEAR;
    private static final BatchEnum UPDATED_BATCH = BatchEnum.SECONDYEAR;

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private BatchMapper batchMapper;

    @Autowired
    private BatchService batchService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.BatchSearchRepositoryMockConfiguration
     */
    @Autowired
    private BatchSearchRepository mockBatchSearchRepository;

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

    private MockMvc restBatchMockMvc;

    private Batch batch;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BatchResource batchResource = new BatchResource(batchService);
        this.restBatchMockMvc = MockMvcBuilders.standaloneSetup(batchResource)
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
    public static Batch createEntity(EntityManager em) {
        Batch batch = new Batch()
            .batch(DEFAULT_BATCH);
        return batch;
    }

    @Before
    public void initTest() {
        batch = createEntity(em);
    }

    @Test
    @Transactional
    public void createBatch() throws Exception {
        int databaseSizeBeforeCreate = batchRepository.findAll().size();

        // Create the Batch
        BatchDTO batchDTO = batchMapper.toDto(batch);
        restBatchMockMvc.perform(post("/api/batches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchDTO)))
            .andExpect(status().isCreated());

        // Validate the Batch in the database
        List<Batch> batchList = batchRepository.findAll();
        assertThat(batchList).hasSize(databaseSizeBeforeCreate + 1);
        Batch testBatch = batchList.get(batchList.size() - 1);
        assertThat(testBatch.getBatch()).isEqualTo(DEFAULT_BATCH);

        // Validate the Batch in Elasticsearch
        verify(mockBatchSearchRepository, times(1)).save(testBatch);
    }

    @Test
    @Transactional
    public void createBatchWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = batchRepository.findAll().size();

        // Create the Batch with an existing ID
        batch.setId(1L);
        BatchDTO batchDTO = batchMapper.toDto(batch);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBatchMockMvc.perform(post("/api/batches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Batch in the database
        List<Batch> batchList = batchRepository.findAll();
        assertThat(batchList).hasSize(databaseSizeBeforeCreate);

        // Validate the Batch in Elasticsearch
        verify(mockBatchSearchRepository, times(0)).save(batch);
    }

    @Test
    @Transactional
    public void checkBatchIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchRepository.findAll().size();
        // set the field null
        batch.setBatch(null);

        // Create the Batch, which fails.
        BatchDTO batchDTO = batchMapper.toDto(batch);

        restBatchMockMvc.perform(post("/api/batches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchDTO)))
            .andExpect(status().isBadRequest());

        List<Batch> batchList = batchRepository.findAll();
        assertThat(batchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBatches() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList
        restBatchMockMvc.perform(get("/api/batches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(batch.getId().intValue())))
            .andExpect(jsonPath("$.[*].batch").value(hasItem(DEFAULT_BATCH.toString())));
    }
    
    @Test
    @Transactional
    public void getBatch() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get the batch
        restBatchMockMvc.perform(get("/api/batches/{id}", batch.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(batch.getId().intValue()))
            .andExpect(jsonPath("$.batch").value(DEFAULT_BATCH.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBatch() throws Exception {
        // Get the batch
        restBatchMockMvc.perform(get("/api/batches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBatch() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        int databaseSizeBeforeUpdate = batchRepository.findAll().size();

        // Update the batch
        Batch updatedBatch = batchRepository.findById(batch.getId()).get();
        // Disconnect from session so that the updates on updatedBatch are not directly saved in db
        em.detach(updatedBatch);
        updatedBatch
            .batch(UPDATED_BATCH);
        BatchDTO batchDTO = batchMapper.toDto(updatedBatch);

        restBatchMockMvc.perform(put("/api/batches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchDTO)))
            .andExpect(status().isOk());

        // Validate the Batch in the database
        List<Batch> batchList = batchRepository.findAll();
        assertThat(batchList).hasSize(databaseSizeBeforeUpdate);
        Batch testBatch = batchList.get(batchList.size() - 1);
        assertThat(testBatch.getBatch()).isEqualTo(UPDATED_BATCH);

        // Validate the Batch in Elasticsearch
        verify(mockBatchSearchRepository, times(1)).save(testBatch);
    }

    @Test
    @Transactional
    public void updateNonExistingBatch() throws Exception {
        int databaseSizeBeforeUpdate = batchRepository.findAll().size();

        // Create the Batch
        BatchDTO batchDTO = batchMapper.toDto(batch);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBatchMockMvc.perform(put("/api/batches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Batch in the database
        List<Batch> batchList = batchRepository.findAll();
        assertThat(batchList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Batch in Elasticsearch
        verify(mockBatchSearchRepository, times(0)).save(batch);
    }

    @Test
    @Transactional
    public void deleteBatch() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        int databaseSizeBeforeDelete = batchRepository.findAll().size();

        // Delete the batch
        restBatchMockMvc.perform(delete("/api/batches/{id}", batch.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Batch> batchList = batchRepository.findAll();
        assertThat(batchList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Batch in Elasticsearch
        verify(mockBatchSearchRepository, times(1)).deleteById(batch.getId());
    }

    @Test
    @Transactional
    public void searchBatch() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);
//        when(mockBatchSearchRepository.search(queryStringQuery("id:" + batch.getId())))
//            .thenReturn(Collections.singletonList(batch));
        // Search the batch
        restBatchMockMvc.perform(get("/api/_search/batches?query=id:" + batch.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(batch.getId().intValue())))
            .andExpect(jsonPath("$.[*].batch").value(hasItem(DEFAULT_BATCH.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Batch.class);
        Batch batch1 = new Batch();
        batch1.setId(1L);
        Batch batch2 = new Batch();
        batch2.setId(batch1.getId());
        assertThat(batch1).isEqualTo(batch2);
        batch2.setId(2L);
        assertThat(batch1).isNotEqualTo(batch2);
        batch1.setId(null);
        assertThat(batch1).isNotEqualTo(batch2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BatchDTO.class);
        BatchDTO batchDTO1 = new BatchDTO();
        batchDTO1.setId(1L);
        BatchDTO batchDTO2 = new BatchDTO();
        assertThat(batchDTO1).isNotEqualTo(batchDTO2);
        batchDTO2.setId(batchDTO1.getId());
        assertThat(batchDTO1).isEqualTo(batchDTO2);
        batchDTO2.setId(2L);
        assertThat(batchDTO1).isNotEqualTo(batchDTO2);
        batchDTO1.setId(null);
        assertThat(batchDTO1).isNotEqualTo(batchDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(batchMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(batchMapper.fromId(null)).isNull();
    }
}
