package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.AcademicYear;
import com.synectiks.cms.repository.AcademicYearRepository;
import com.synectiks.cms.repository.search.AcademicYearSearchRepository;
import com.synectiks.cms.service.AcademicYearService;
import com.synectiks.cms.service.dto.AcademicYearDTO;
import com.synectiks.cms.service.mapper.AcademicYearMapper;
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

/**
 * Test class for the AcademicYearResource REST controller.
 *
 * @see AcademicYearResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class AcademicYearResourceIntTest {

    private static final String DEFAULT_YEAR = "AAAAAAAAAA";
    private static final String UPDATED_YEAR = "BBBBBBBBBB";

    private static final Date DEFAULT_START_DATE = new Date();
    private static final Date UPDATED_START_DATE = new Date();

    private static final Date DEFAULT_END_DATE = new Date();
    private static final Date UPDATED_END_DATE = new Date();

    @Autowired
    private AcademicYearRepository academicYearRepository;

    @Autowired
    private AcademicYearMapper academicYearMapper;

    @Autowired
    private AcademicYearService academicYearService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.AcademicYearSearchRepositoryMockConfiguration
     */
    @Autowired
    private AcademicYearSearchRepository mockAcademicYearSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAcademicYearMockMvc;

    private AcademicYear academicYear;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AcademicYearResource academicYearResource = new AcademicYearResource(academicYearService);
        this.restAcademicYearMockMvc = MockMvcBuilders.standaloneSetup(academicYearResource)
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
    public static AcademicYear createEntity(EntityManager em) {
        AcademicYear academicYear = new AcademicYear()
            .year(DEFAULT_YEAR)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE);
        return academicYear;
    }

    @Before
    public void initTest() {
        academicYear = createEntity(em);
    }

    @Test
    @Transactional
    public void createAcademicYear() throws Exception {
        int databaseSizeBeforeCreate = academicYearRepository.findAll().size();

        // Create the AcademicYear
        AcademicYearDTO academicYearDTO = academicYearMapper.toDto(academicYear);
        restAcademicYearMockMvc.perform(post("/api/academic-years")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicYearDTO)))
            .andExpect(status().isCreated());

        // Validate the AcademicYear in the database
        List<AcademicYear> academicYearList = academicYearRepository.findAll();
        assertThat(academicYearList).hasSize(databaseSizeBeforeCreate + 1);
        AcademicYear testAcademicYear = academicYearList.get(academicYearList.size() - 1);
        assertThat(testAcademicYear.getYear()).isEqualTo(DEFAULT_YEAR);
        assertThat(testAcademicYear.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testAcademicYear.getEndDate()).isEqualTo(DEFAULT_END_DATE);

        // Validate the AcademicYear in Elasticsearch
        verify(mockAcademicYearSearchRepository, times(1)).save(testAcademicYear);
    }

    @Test
    @Transactional
    public void createAcademicYearWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = academicYearRepository.findAll().size();

        // Create the AcademicYear with an existing ID
        academicYear.setId(1L);
        AcademicYearDTO academicYearDTO = academicYearMapper.toDto(academicYear);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAcademicYearMockMvc.perform(post("/api/academic-years")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicYearDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AcademicYear in the database
        List<AcademicYear> academicYearList = academicYearRepository.findAll();
        assertThat(academicYearList).hasSize(databaseSizeBeforeCreate);

        // Validate the AcademicYear in Elasticsearch
        verify(mockAcademicYearSearchRepository, times(0)).save(academicYear);
    }

    @Test
    @Transactional
    public void checkYearIsRequired() throws Exception {
        int databaseSizeBeforeTest = academicYearRepository.findAll().size();
        // set the field null
        academicYear.setYear(null);

        // Create the AcademicYear, which fails.
        AcademicYearDTO academicYearDTO = academicYearMapper.toDto(academicYear);

        restAcademicYearMockMvc.perform(post("/api/academic-years")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicYearDTO)))
            .andExpect(status().isBadRequest());

        List<AcademicYear> academicYearList = academicYearRepository.findAll();
        assertThat(academicYearList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = academicYearRepository.findAll().size();
        // set the field null
        academicYear.setStartDate(null);

        // Create the AcademicYear, which fails.
        AcademicYearDTO academicYearDTO = academicYearMapper.toDto(academicYear);

        restAcademicYearMockMvc.perform(post("/api/academic-years")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicYearDTO)))
            .andExpect(status().isBadRequest());

        List<AcademicYear> academicYearList = academicYearRepository.findAll();
        assertThat(academicYearList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = academicYearRepository.findAll().size();
        // set the field null
        academicYear.setEndDate(null);

        // Create the AcademicYear, which fails.
        AcademicYearDTO academicYearDTO = academicYearMapper.toDto(academicYear);

        restAcademicYearMockMvc.perform(post("/api/academic-years")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicYearDTO)))
            .andExpect(status().isBadRequest());

        List<AcademicYear> academicYearList = academicYearRepository.findAll();
        assertThat(academicYearList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAcademicYears() throws Exception {
        // Initialize the database
        academicYearRepository.saveAndFlush(academicYear);

        // Get all the academicYearList
        restAcademicYearMockMvc.perform(get("/api/academic-years?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(academicYear.getId().intValue())))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR.toString())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getAcademicYear() throws Exception {
        // Initialize the database
        academicYearRepository.saveAndFlush(academicYear);

        // Get the academicYear
        restAcademicYearMockMvc.perform(get("/api/academic-years/{id}", academicYear.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(academicYear.getId().intValue()))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR.toString()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAcademicYear() throws Exception {
        // Get the academicYear
        restAcademicYearMockMvc.perform(get("/api/academic-years/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAcademicYear() throws Exception {
        // Initialize the database
        academicYearRepository.saveAndFlush(academicYear);

        int databaseSizeBeforeUpdate = academicYearRepository.findAll().size();

        // Update the academicYear
        AcademicYear updatedAcademicYear = academicYearRepository.findById(academicYear.getId()).get();
        // Disconnect from session so that the updates on updatedAcademicYear are not directly saved in db
        em.detach(updatedAcademicYear);
        updatedAcademicYear
            .year(UPDATED_YEAR)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE);
        AcademicYearDTO academicYearDTO = academicYearMapper.toDto(updatedAcademicYear);

        restAcademicYearMockMvc.perform(put("/api/academic-years")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicYearDTO)))
            .andExpect(status().isOk());

        // Validate the AcademicYear in the database
        List<AcademicYear> academicYearList = academicYearRepository.findAll();
        assertThat(academicYearList).hasSize(databaseSizeBeforeUpdate);
        AcademicYear testAcademicYear = academicYearList.get(academicYearList.size() - 1);
        assertThat(testAcademicYear.getYear()).isEqualTo(UPDATED_YEAR);
        assertThat(testAcademicYear.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testAcademicYear.getEndDate()).isEqualTo(UPDATED_END_DATE);

        // Validate the AcademicYear in Elasticsearch
        verify(mockAcademicYearSearchRepository, times(1)).save(testAcademicYear);
    }

    @Test
    @Transactional
    public void updateNonExistingAcademicYear() throws Exception {
        int databaseSizeBeforeUpdate = academicYearRepository.findAll().size();

        // Create the AcademicYear
        AcademicYearDTO academicYearDTO = academicYearMapper.toDto(academicYear);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAcademicYearMockMvc.perform(put("/api/academic-years")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicYearDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AcademicYear in the database
        List<AcademicYear> academicYearList = academicYearRepository.findAll();
        assertThat(academicYearList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AcademicYear in Elasticsearch
        verify(mockAcademicYearSearchRepository, times(0)).save(academicYear);
    }

    @Test
    @Transactional
    public void deleteAcademicYear() throws Exception {
        // Initialize the database
        academicYearRepository.saveAndFlush(academicYear);

        int databaseSizeBeforeDelete = academicYearRepository.findAll().size();

        // Get the academicYear
        restAcademicYearMockMvc.perform(delete("/api/academic-years/{id}", academicYear.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AcademicYear> academicYearList = academicYearRepository.findAll();
        assertThat(academicYearList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AcademicYear in Elasticsearch
        verify(mockAcademicYearSearchRepository, times(1)).deleteById(academicYear.getId());
    }

    @Test
    @Transactional
    public void searchAcademicYear() throws Exception {
        // Initialize the database
        academicYearRepository.saveAndFlush(academicYear);
        when(mockAcademicYearSearchRepository.search(queryStringQuery("id:" + academicYear.getId())))
            .thenReturn(Collections.singletonList(academicYear));
        // Search the academicYear
        restAcademicYearMockMvc.perform(get("/api/_search/academic-years?query=id:" + academicYear.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(academicYear.getId().intValue())))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AcademicYear.class);
        AcademicYear academicYear1 = new AcademicYear();
        academicYear1.setId(1L);
        AcademicYear academicYear2 = new AcademicYear();
        academicYear2.setId(academicYear1.getId());
        assertThat(academicYear1).isEqualTo(academicYear2);
        academicYear2.setId(2L);
        assertThat(academicYear1).isNotEqualTo(academicYear2);
        academicYear1.setId(null);
        assertThat(academicYear1).isNotEqualTo(academicYear2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AcademicYearDTO.class);
        AcademicYearDTO academicYearDTO1 = new AcademicYearDTO();
        academicYearDTO1.setId(1L);
        AcademicYearDTO academicYearDTO2 = new AcademicYearDTO();
        assertThat(academicYearDTO1).isNotEqualTo(academicYearDTO2);
        academicYearDTO2.setId(academicYearDTO1.getId());
        assertThat(academicYearDTO1).isEqualTo(academicYearDTO2);
        academicYearDTO2.setId(2L);
        assertThat(academicYearDTO1).isNotEqualTo(academicYearDTO2);
        academicYearDTO1.setId(null);
        assertThat(academicYearDTO1).isNotEqualTo(academicYearDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(academicYearMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(academicYearMapper.fromId(null)).isNull();
    }
}
