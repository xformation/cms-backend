package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.Periods;
import com.synectiks.cms.repository.PeriodsRepository;
import com.synectiks.cms.repository.search.PeriodsSearchRepository;
import com.synectiks.cms.service.PeriodsService;
import com.synectiks.cms.service.dto.PeriodsDTO;
import com.synectiks.cms.service.mapper.PeriodsMapper;
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

import com.synectiks.cms.domain.enumeration.ClassPeriods;
/**
 * Test class for the PeriodsResource REST controller.
 *
 * @see PeriodsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class PeriodsResourceIntTest {

    private static final ClassPeriods DEFAULT_PERIODS = ClassPeriods.ONE;
    private static final ClassPeriods UPDATED_PERIODS = ClassPeriods.TWO;

    @Autowired
    private PeriodsRepository periodsRepository;

    @Autowired
    private PeriodsMapper periodsMapper;

    @Autowired
    private PeriodsService periodsService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.PeriodsSearchRepositoryMockConfiguration
     */
    @Autowired
    private PeriodsSearchRepository mockPeriodsSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPeriodsMockMvc;

    private Periods periods;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PeriodsResource periodsResource = new PeriodsResource(periodsService);
        this.restPeriodsMockMvc = MockMvcBuilders.standaloneSetup(periodsResource)
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
    public static Periods createEntity(EntityManager em) {
        Periods periods = new Periods()
            .periods(DEFAULT_PERIODS);
        return periods;
    }

    @Before
    public void initTest() {
        periods = createEntity(em);
    }

    @Test
    @Transactional
    public void createPeriods() throws Exception {
        int databaseSizeBeforeCreate = periodsRepository.findAll().size();

        // Create the Periods
        PeriodsDTO periodsDTO = periodsMapper.toDto(periods);
        restPeriodsMockMvc.perform(post("/api/periods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(periodsDTO)))
            .andExpect(status().isCreated());

        // Validate the Periods in the database
        List<Periods> periodsList = periodsRepository.findAll();
        assertThat(periodsList).hasSize(databaseSizeBeforeCreate + 1);
        Periods testPeriods = periodsList.get(periodsList.size() - 1);
        assertThat(testPeriods.getPeriods()).isEqualTo(DEFAULT_PERIODS);

        // Validate the Periods in Elasticsearch
        verify(mockPeriodsSearchRepository, times(1)).save(testPeriods);
    }

    @Test
    @Transactional
    public void createPeriodsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = periodsRepository.findAll().size();

        // Create the Periods with an existing ID
        periods.setId(1L);
        PeriodsDTO periodsDTO = periodsMapper.toDto(periods);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPeriodsMockMvc.perform(post("/api/periods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(periodsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Periods in the database
        List<Periods> periodsList = periodsRepository.findAll();
        assertThat(periodsList).hasSize(databaseSizeBeforeCreate);

        // Validate the Periods in Elasticsearch
        verify(mockPeriodsSearchRepository, times(0)).save(periods);
    }

    @Test
    @Transactional
    public void checkPeriodsIsRequired() throws Exception {
        int databaseSizeBeforeTest = periodsRepository.findAll().size();
        // set the field null
        periods.setPeriods(null);

        // Create the Periods, which fails.
        PeriodsDTO periodsDTO = periodsMapper.toDto(periods);

        restPeriodsMockMvc.perform(post("/api/periods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(periodsDTO)))
            .andExpect(status().isBadRequest());

        List<Periods> periodsList = periodsRepository.findAll();
        assertThat(periodsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPeriods() throws Exception {
        // Initialize the database
        periodsRepository.saveAndFlush(periods);

        // Get all the periodsList
        restPeriodsMockMvc.perform(get("/api/periods?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(periods.getId().intValue())))
            .andExpect(jsonPath("$.[*].periods").value(hasItem(DEFAULT_PERIODS.toString())));
    }
    
    @Test
    @Transactional
    public void getPeriods() throws Exception {
        // Initialize the database
        periodsRepository.saveAndFlush(periods);

        // Get the periods
        restPeriodsMockMvc.perform(get("/api/periods/{id}", periods.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(periods.getId().intValue()))
            .andExpect(jsonPath("$.periods").value(DEFAULT_PERIODS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPeriods() throws Exception {
        // Get the periods
        restPeriodsMockMvc.perform(get("/api/periods/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePeriods() throws Exception {
        // Initialize the database
        periodsRepository.saveAndFlush(periods);

        int databaseSizeBeforeUpdate = periodsRepository.findAll().size();

        // Update the periods
        Periods updatedPeriods = periodsRepository.findById(periods.getId()).get();
        // Disconnect from session so that the updates on updatedPeriods are not directly saved in db
        em.detach(updatedPeriods);
        updatedPeriods
            .periods(UPDATED_PERIODS);
        PeriodsDTO periodsDTO = periodsMapper.toDto(updatedPeriods);

        restPeriodsMockMvc.perform(put("/api/periods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(periodsDTO)))
            .andExpect(status().isOk());

        // Validate the Periods in the database
        List<Periods> periodsList = periodsRepository.findAll();
        assertThat(periodsList).hasSize(databaseSizeBeforeUpdate);
        Periods testPeriods = periodsList.get(periodsList.size() - 1);
        assertThat(testPeriods.getPeriods()).isEqualTo(UPDATED_PERIODS);

        // Validate the Periods in Elasticsearch
        verify(mockPeriodsSearchRepository, times(1)).save(testPeriods);
    }

    @Test
    @Transactional
    public void updateNonExistingPeriods() throws Exception {
        int databaseSizeBeforeUpdate = periodsRepository.findAll().size();

        // Create the Periods
        PeriodsDTO periodsDTO = periodsMapper.toDto(periods);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPeriodsMockMvc.perform(put("/api/periods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(periodsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Periods in the database
        List<Periods> periodsList = periodsRepository.findAll();
        assertThat(periodsList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Periods in Elasticsearch
        verify(mockPeriodsSearchRepository, times(0)).save(periods);
    }

    @Test
    @Transactional
    public void deletePeriods() throws Exception {
        // Initialize the database
        periodsRepository.saveAndFlush(periods);

        int databaseSizeBeforeDelete = periodsRepository.findAll().size();

        // Get the periods
        restPeriodsMockMvc.perform(delete("/api/periods/{id}", periods.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Periods> periodsList = periodsRepository.findAll();
        assertThat(periodsList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Periods in Elasticsearch
        verify(mockPeriodsSearchRepository, times(1)).deleteById(periods.getId());
    }

    @Test
    @Transactional
    public void searchPeriods() throws Exception {
        // Initialize the database
        periodsRepository.saveAndFlush(periods);
        when(mockPeriodsSearchRepository.search(queryStringQuery("id:" + periods.getId())))
            .thenReturn(Collections.singletonList(periods));
        // Search the periods
        restPeriodsMockMvc.perform(get("/api/_search/periods?query=id:" + periods.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(periods.getId().intValue())))
            .andExpect(jsonPath("$.[*].periods").value(hasItem(DEFAULT_PERIODS.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Periods.class);
        Periods periods1 = new Periods();
        periods1.setId(1L);
        Periods periods2 = new Periods();
        periods2.setId(periods1.getId());
        assertThat(periods1).isEqualTo(periods2);
        periods2.setId(2L);
        assertThat(periods1).isNotEqualTo(periods2);
        periods1.setId(null);
        assertThat(periods1).isNotEqualTo(periods2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PeriodsDTO.class);
        PeriodsDTO periodsDTO1 = new PeriodsDTO();
        periodsDTO1.setId(1L);
        PeriodsDTO periodsDTO2 = new PeriodsDTO();
        assertThat(periodsDTO1).isNotEqualTo(periodsDTO2);
        periodsDTO2.setId(periodsDTO1.getId());
        assertThat(periodsDTO1).isEqualTo(periodsDTO2);
        periodsDTO2.setId(2L);
        assertThat(periodsDTO1).isNotEqualTo(periodsDTO2);
        periodsDTO1.setId(null);
        assertThat(periodsDTO1).isNotEqualTo(periodsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(periodsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(periodsMapper.fromId(null)).isNull();
    }
}
