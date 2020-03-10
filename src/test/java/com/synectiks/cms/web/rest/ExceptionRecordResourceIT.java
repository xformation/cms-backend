package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;
import com.synectiks.cms.domain.ExceptionRecord;
import com.synectiks.cms.repository.ExceptionRecordRepository;
import com.synectiks.cms.repository.search.ExceptionRecordSearchRepository;
import com.synectiks.cms.service.ExceptionRecordService;
import com.synectiks.cms.service.dto.ExceptionRecordDTO;
import com.synectiks.cms.service.mapper.ExceptionRecordMapper;
import com.synectiks.cms.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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

/**
 * Integration tests for the {@Link ExceptionRecordResource} REST controller.
 */
@SpringBootTest(classes = CmsApp.class)
public class ExceptionRecordResourceIT {

    private static final String DEFAULT_EXCEPTION_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_EXCEPTION_SOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_EXCEPTION_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_EXCEPTION_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_EXCEPTION_RECORD = "AAAAAAAAAA";
    private static final String UPDATED_EXCEPTION_RECORD = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_EXCEPTION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EXCEPTION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_USER = "AAAAAAAAAA";
    private static final String UPDATED_USER = "BBBBBBBBBB";

    @Autowired
    private ExceptionRecordRepository exceptionRecordRepository;

    @Autowired
    private ExceptionRecordMapper exceptionRecordMapper;

    @Autowired
    private ExceptionRecordService exceptionRecordService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.ExceptionRecordSearchRepositoryMockConfiguration
     */
    @Autowired
    private ExceptionRecordSearchRepository mockExceptionRecordSearchRepository;

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

    private MockMvc restExceptionRecordMockMvc;

    private ExceptionRecord exceptionRecord;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ExceptionRecordResource exceptionRecordResource = new ExceptionRecordResource(exceptionRecordService);
        this.restExceptionRecordMockMvc = MockMvcBuilders.standaloneSetup(exceptionRecordResource)
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
    public static ExceptionRecord createEntity(EntityManager em) {
        ExceptionRecord exceptionRecord = new ExceptionRecord()
            .exceptionSource(DEFAULT_EXCEPTION_SOURCE)
            .exceptionType(DEFAULT_EXCEPTION_TYPE)
            .exceptionRecord(DEFAULT_EXCEPTION_RECORD)
            .exceptionDate(DEFAULT_EXCEPTION_DATE)
            .user(DEFAULT_USER);
        return exceptionRecord;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExceptionRecord createUpdatedEntity(EntityManager em) {
        ExceptionRecord exceptionRecord = new ExceptionRecord()
            .exceptionSource(UPDATED_EXCEPTION_SOURCE)
            .exceptionType(UPDATED_EXCEPTION_TYPE)
            .exceptionRecord(UPDATED_EXCEPTION_RECORD)
            .exceptionDate(UPDATED_EXCEPTION_DATE)
            .user(UPDATED_USER);
        return exceptionRecord;
    }

    @BeforeEach
    public void initTest() {
        exceptionRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createExceptionRecord() throws Exception {
        int databaseSizeBeforeCreate = exceptionRecordRepository.findAll().size();

        // Create the ExceptionRecord
        ExceptionRecordDTO exceptionRecordDTO = exceptionRecordMapper.toDto(exceptionRecord);
        restExceptionRecordMockMvc.perform(post("/api/exception-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exceptionRecordDTO)))
            .andExpect(status().isCreated());

        // Validate the ExceptionRecord in the database
        List<ExceptionRecord> exceptionRecordList = exceptionRecordRepository.findAll();
        assertThat(exceptionRecordList).hasSize(databaseSizeBeforeCreate + 1);
        ExceptionRecord testExceptionRecord = exceptionRecordList.get(exceptionRecordList.size() - 1);
        assertThat(testExceptionRecord.getExceptionSource()).isEqualTo(DEFAULT_EXCEPTION_SOURCE);
        assertThat(testExceptionRecord.getExceptionType()).isEqualTo(DEFAULT_EXCEPTION_TYPE);
        assertThat(testExceptionRecord.getExceptionRecord()).isEqualTo(DEFAULT_EXCEPTION_RECORD);
        assertThat(testExceptionRecord.getExceptionDate()).isEqualTo(DEFAULT_EXCEPTION_DATE);
        assertThat(testExceptionRecord.getUser()).isEqualTo(DEFAULT_USER);

        // Validate the ExceptionRecord in Elasticsearch
        verify(mockExceptionRecordSearchRepository, times(1)).save(testExceptionRecord);
    }

    @Test
    @Transactional
    public void createExceptionRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = exceptionRecordRepository.findAll().size();

        // Create the ExceptionRecord with an existing ID
        exceptionRecord.setId(1L);
        ExceptionRecordDTO exceptionRecordDTO = exceptionRecordMapper.toDto(exceptionRecord);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExceptionRecordMockMvc.perform(post("/api/exception-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exceptionRecordDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ExceptionRecord in the database
        List<ExceptionRecord> exceptionRecordList = exceptionRecordRepository.findAll();
        assertThat(exceptionRecordList).hasSize(databaseSizeBeforeCreate);

        // Validate the ExceptionRecord in Elasticsearch
        verify(mockExceptionRecordSearchRepository, times(0)).save(exceptionRecord);
    }


    @Test
    @Transactional
    public void getAllExceptionRecords() throws Exception {
        // Initialize the database
        exceptionRecordRepository.saveAndFlush(exceptionRecord);

        // Get all the exceptionRecordList
        restExceptionRecordMockMvc.perform(get("/api/exception-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(exceptionRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].exceptionSource").value(hasItem(DEFAULT_EXCEPTION_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].exceptionType").value(hasItem(DEFAULT_EXCEPTION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].exceptionRecord").value(hasItem(DEFAULT_EXCEPTION_RECORD.toString())))
            .andExpect(jsonPath("$.[*].exceptionDate").value(hasItem(DEFAULT_EXCEPTION_DATE.toString())))
            .andExpect(jsonPath("$.[*].user").value(hasItem(DEFAULT_USER.toString())));
    }

    @Test
    @Transactional
    public void getExceptionRecord() throws Exception {
        // Initialize the database
        exceptionRecordRepository.saveAndFlush(exceptionRecord);

        // Get the exceptionRecord
        restExceptionRecordMockMvc.perform(get("/api/exception-records/{id}", exceptionRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(exceptionRecord.getId().intValue()))
            .andExpect(jsonPath("$.exceptionSource").value(DEFAULT_EXCEPTION_SOURCE.toString()))
            .andExpect(jsonPath("$.exceptionType").value(DEFAULT_EXCEPTION_TYPE.toString()))
            .andExpect(jsonPath("$.exceptionRecord").value(DEFAULT_EXCEPTION_RECORD.toString()))
            .andExpect(jsonPath("$.exceptionDate").value(DEFAULT_EXCEPTION_DATE.toString()))
            .andExpect(jsonPath("$.user").value(DEFAULT_USER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingExceptionRecord() throws Exception {
        // Get the exceptionRecord
        restExceptionRecordMockMvc.perform(get("/api/exception-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExceptionRecord() throws Exception {
        // Initialize the database
        exceptionRecordRepository.saveAndFlush(exceptionRecord);

        int databaseSizeBeforeUpdate = exceptionRecordRepository.findAll().size();

        // Update the exceptionRecord
        ExceptionRecord updatedExceptionRecord = exceptionRecordRepository.findById(exceptionRecord.getId()).get();
        // Disconnect from session so that the updates on updatedExceptionRecord are not directly saved in db
        em.detach(updatedExceptionRecord);
        updatedExceptionRecord
            .exceptionSource(UPDATED_EXCEPTION_SOURCE)
            .exceptionType(UPDATED_EXCEPTION_TYPE)
            .exceptionRecord(UPDATED_EXCEPTION_RECORD)
            .exceptionDate(UPDATED_EXCEPTION_DATE)
            .user(UPDATED_USER);
        ExceptionRecordDTO exceptionRecordDTO = exceptionRecordMapper.toDto(updatedExceptionRecord);

        restExceptionRecordMockMvc.perform(put("/api/exception-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exceptionRecordDTO)))
            .andExpect(status().isOk());

        // Validate the ExceptionRecord in the database
        List<ExceptionRecord> exceptionRecordList = exceptionRecordRepository.findAll();
        assertThat(exceptionRecordList).hasSize(databaseSizeBeforeUpdate);
        ExceptionRecord testExceptionRecord = exceptionRecordList.get(exceptionRecordList.size() - 1);
        assertThat(testExceptionRecord.getExceptionSource()).isEqualTo(UPDATED_EXCEPTION_SOURCE);
        assertThat(testExceptionRecord.getExceptionType()).isEqualTo(UPDATED_EXCEPTION_TYPE);
        assertThat(testExceptionRecord.getExceptionRecord()).isEqualTo(UPDATED_EXCEPTION_RECORD);
        assertThat(testExceptionRecord.getExceptionDate()).isEqualTo(UPDATED_EXCEPTION_DATE);
        assertThat(testExceptionRecord.getUser()).isEqualTo(UPDATED_USER);

        // Validate the ExceptionRecord in Elasticsearch
        verify(mockExceptionRecordSearchRepository, times(1)).save(testExceptionRecord);
    }

    @Test
    @Transactional
    public void updateNonExistingExceptionRecord() throws Exception {
        int databaseSizeBeforeUpdate = exceptionRecordRepository.findAll().size();

        // Create the ExceptionRecord
        ExceptionRecordDTO exceptionRecordDTO = exceptionRecordMapper.toDto(exceptionRecord);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExceptionRecordMockMvc.perform(put("/api/exception-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exceptionRecordDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ExceptionRecord in the database
        List<ExceptionRecord> exceptionRecordList = exceptionRecordRepository.findAll();
        assertThat(exceptionRecordList).hasSize(databaseSizeBeforeUpdate);

        // Validate the ExceptionRecord in Elasticsearch
        verify(mockExceptionRecordSearchRepository, times(0)).save(exceptionRecord);
    }

    @Test
    @Transactional
    public void deleteExceptionRecord() throws Exception {
        // Initialize the database
        exceptionRecordRepository.saveAndFlush(exceptionRecord);

        int databaseSizeBeforeDelete = exceptionRecordRepository.findAll().size();

        // Delete the exceptionRecord
        restExceptionRecordMockMvc.perform(delete("/api/exception-records/{id}", exceptionRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ExceptionRecord> exceptionRecordList = exceptionRecordRepository.findAll();
        assertThat(exceptionRecordList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the ExceptionRecord in Elasticsearch
        verify(mockExceptionRecordSearchRepository, times(1)).deleteById(exceptionRecord.getId());
    }

    @Test
    @Transactional
    public void searchExceptionRecord() throws Exception {
        // Initialize the database
        exceptionRecordRepository.saveAndFlush(exceptionRecord);
//        when(mockExceptionRecordSearchRepository.search(queryStringQuery("id:" + exceptionRecord.getId())))
//            .thenReturn(Collections.singletonList(exceptionRecord));
        // Search the exceptionRecord
        restExceptionRecordMockMvc.perform(get("/api/_search/exception-records?query=id:" + exceptionRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(exceptionRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].exceptionSource").value(hasItem(DEFAULT_EXCEPTION_SOURCE)))
            .andExpect(jsonPath("$.[*].exceptionType").value(hasItem(DEFAULT_EXCEPTION_TYPE)))
            .andExpect(jsonPath("$.[*].exceptionRecord").value(hasItem(DEFAULT_EXCEPTION_RECORD)))
            .andExpect(jsonPath("$.[*].exceptionDate").value(hasItem(DEFAULT_EXCEPTION_DATE.toString())))
            .andExpect(jsonPath("$.[*].user").value(hasItem(DEFAULT_USER)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExceptionRecord.class);
        ExceptionRecord exceptionRecord1 = new ExceptionRecord();
        exceptionRecord1.setId(1L);
        ExceptionRecord exceptionRecord2 = new ExceptionRecord();
        exceptionRecord2.setId(exceptionRecord1.getId());
        assertThat(exceptionRecord1).isEqualTo(exceptionRecord2);
        exceptionRecord2.setId(2L);
        assertThat(exceptionRecord1).isNotEqualTo(exceptionRecord2);
        exceptionRecord1.setId(null);
        assertThat(exceptionRecord1).isNotEqualTo(exceptionRecord2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExceptionRecordDTO.class);
        ExceptionRecordDTO exceptionRecordDTO1 = new ExceptionRecordDTO();
        exceptionRecordDTO1.setId(1L);
        ExceptionRecordDTO exceptionRecordDTO2 = new ExceptionRecordDTO();
        assertThat(exceptionRecordDTO1).isNotEqualTo(exceptionRecordDTO2);
        exceptionRecordDTO2.setId(exceptionRecordDTO1.getId());
        assertThat(exceptionRecordDTO1).isEqualTo(exceptionRecordDTO2);
        exceptionRecordDTO2.setId(2L);
        assertThat(exceptionRecordDTO1).isNotEqualTo(exceptionRecordDTO2);
        exceptionRecordDTO1.setId(null);
        assertThat(exceptionRecordDTO1).isNotEqualTo(exceptionRecordDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(exceptionRecordMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(exceptionRecordMapper.fromId(null)).isNull();
    }
}
