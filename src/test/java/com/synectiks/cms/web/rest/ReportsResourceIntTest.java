package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.Reports;
import com.synectiks.cms.repository.ReportsRepository;
import com.synectiks.cms.repository.search.ReportsSearchRepository;
import com.synectiks.cms.service.ReportsService;
import com.synectiks.cms.service.dto.ReportsDTO;
import com.synectiks.cms.service.mapper.ReportsMapper;
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
 * Test class for the ReportsResource REST controller.
 *
 * @see ReportsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class ReportsResourceIntTest {

    private static final String DEFAULT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_DESC = "BBBBBBBBBB";

    @Autowired
    private ReportsRepository reportsRepository;

    @Autowired
    private ReportsMapper reportsMapper;

    @Autowired
    private ReportsService reportsService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.ReportsSearchRepositoryMockConfiguration
     */
    @Autowired
    private ReportsSearchRepository mockReportsSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restReportsMockMvc;

    private Reports reports;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ReportsResource reportsResource = new ReportsResource(reportsService);
        this.restReportsMockMvc = MockMvcBuilders.standaloneSetup(reportsResource)
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
    public static Reports createEntity(EntityManager em) {
        Reports reports = new Reports()
            .desc(DEFAULT_DESC);
        return reports;
    }

    @Before
    public void initTest() {
        reports = createEntity(em);
    }

    @Test
    @Transactional
    public void createReports() throws Exception {
        int databaseSizeBeforeCreate = reportsRepository.findAll().size();

        // Create the Reports
        ReportsDTO reportsDTO = reportsMapper.toDto(reports);
        restReportsMockMvc.perform(post("/api/reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reportsDTO)))
            .andExpect(status().isCreated());

        // Validate the Reports in the database
        List<Reports> reportsList = reportsRepository.findAll();
        assertThat(reportsList).hasSize(databaseSizeBeforeCreate + 1);
        Reports testReports = reportsList.get(reportsList.size() - 1);
        assertThat(testReports.getDesc()).isEqualTo(DEFAULT_DESC);

        // Validate the Reports in Elasticsearch
        verify(mockReportsSearchRepository, times(1)).save(testReports);
    }

    @Test
    @Transactional
    public void createReportsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = reportsRepository.findAll().size();

        // Create the Reports with an existing ID
        reports.setId(1L);
        ReportsDTO reportsDTO = reportsMapper.toDto(reports);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReportsMockMvc.perform(post("/api/reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reportsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Reports in the database
        List<Reports> reportsList = reportsRepository.findAll();
        assertThat(reportsList).hasSize(databaseSizeBeforeCreate);

        // Validate the Reports in Elasticsearch
        verify(mockReportsSearchRepository, times(0)).save(reports);
    }

    @Test
    @Transactional
    public void checkDescIsRequired() throws Exception {
        int databaseSizeBeforeTest = reportsRepository.findAll().size();
        // set the field null
        reports.setDesc(null);

        // Create the Reports, which fails.
        ReportsDTO reportsDTO = reportsMapper.toDto(reports);

        restReportsMockMvc.perform(post("/api/reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reportsDTO)))
            .andExpect(status().isBadRequest());

        List<Reports> reportsList = reportsRepository.findAll();
        assertThat(reportsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllReports() throws Exception {
        // Initialize the database
        reportsRepository.saveAndFlush(reports);

        // Get all the reportsList
        restReportsMockMvc.perform(get("/api/reports?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reports.getId().intValue())))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC.toString())));
    }

    @Test
    @Transactional
    public void getReports() throws Exception {
        // Initialize the database
        reportsRepository.saveAndFlush(reports);

        // Get the reports
        restReportsMockMvc.perform(get("/api/reports/{id}", reports.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(reports.getId().intValue()))
            .andExpect(jsonPath("$.desc").value(DEFAULT_DESC.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingReports() throws Exception {
        // Get the reports
        restReportsMockMvc.perform(get("/api/reports/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReports() throws Exception {
        // Initialize the database
        reportsRepository.saveAndFlush(reports);

        int databaseSizeBeforeUpdate = reportsRepository.findAll().size();

        // Update the reports
        Reports updatedReports = reportsRepository.findById(reports.getId()).get();
        // Disconnect from session so that the updates on updatedReports are not directly saved in db
        em.detach(updatedReports);
        updatedReports
            .desc(UPDATED_DESC);
        ReportsDTO reportsDTO = reportsMapper.toDto(updatedReports);

        restReportsMockMvc.perform(put("/api/reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reportsDTO)))
            .andExpect(status().isOk());

        // Validate the Reports in the database
        List<Reports> reportsList = reportsRepository.findAll();
        assertThat(reportsList).hasSize(databaseSizeBeforeUpdate);
        Reports testReports = reportsList.get(reportsList.size() - 1);
        assertThat(testReports.getDesc()).isEqualTo(UPDATED_DESC);

        // Validate the Reports in Elasticsearch
        verify(mockReportsSearchRepository, times(1)).save(testReports);
    }

    @Test
    @Transactional
    public void updateNonExistingReports() throws Exception {
        int databaseSizeBeforeUpdate = reportsRepository.findAll().size();

        // Create the Reports
        ReportsDTO reportsDTO = reportsMapper.toDto(reports);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReportsMockMvc.perform(put("/api/reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reportsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Reports in the database
        List<Reports> reportsList = reportsRepository.findAll();
        assertThat(reportsList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Reports in Elasticsearch
        verify(mockReportsSearchRepository, times(0)).save(reports);
    }

    @Test
    @Transactional
    public void deleteReports() throws Exception {
        // Initialize the database
        reportsRepository.saveAndFlush(reports);

        int databaseSizeBeforeDelete = reportsRepository.findAll().size();

        // Get the reports
        restReportsMockMvc.perform(delete("/api/reports/{id}", reports.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Reports> reportsList = reportsRepository.findAll();
        assertThat(reportsList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Reports in Elasticsearch
        verify(mockReportsSearchRepository, times(1)).deleteById(reports.getId());
    }

    @Test
    @Transactional
    public void searchReports() throws Exception {
        // Initialize the database
        reportsRepository.saveAndFlush(reports);
//        when(mockReportsSearchRepository.search(queryStringQuery("id:" + reports.getId())))
//            .thenReturn(Collections.singletonList(reports));
        // Search the reports
        restReportsMockMvc.perform(get("/api/_search/reports?query=id:" + reports.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reports.getId().intValue())))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Reports.class);
        Reports reports1 = new Reports();
        reports1.setId(1L);
        Reports reports2 = new Reports();
        reports2.setId(reports1.getId());
        assertThat(reports1).isEqualTo(reports2);
        reports2.setId(2L);
        assertThat(reports1).isNotEqualTo(reports2);
        reports1.setId(null);
        assertThat(reports1).isNotEqualTo(reports2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReportsDTO.class);
        ReportsDTO reportsDTO1 = new ReportsDTO();
        reportsDTO1.setId(1L);
        ReportsDTO reportsDTO2 = new ReportsDTO();
        assertThat(reportsDTO1).isNotEqualTo(reportsDTO2);
        reportsDTO2.setId(reportsDTO1.getId());
        assertThat(reportsDTO1).isEqualTo(reportsDTO2);
        reportsDTO2.setId(2L);
        assertThat(reportsDTO1).isNotEqualTo(reportsDTO2);
        reportsDTO1.setId(null);
        assertThat(reportsDTO1).isNotEqualTo(reportsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(reportsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(reportsMapper.fromId(null)).isNull();
    }
}
