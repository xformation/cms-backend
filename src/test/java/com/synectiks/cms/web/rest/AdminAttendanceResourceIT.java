package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;
import com.synectiks.cms.domain.AdminAttendance;
import com.synectiks.cms.repository.AdminAttendanceRepository;
import com.synectiks.cms.repository.search.AdminAttendanceSearchRepository;
import com.synectiks.cms.service.AdminAttendanceService;
import com.synectiks.cms.service.dto.AdminAttendanceDTO;
import com.synectiks.cms.service.mapper.AdminAttendanceMapper;
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
 * Integration tests for the {@Link AdminAttendanceResource} REST controller.
 */
@SpringBootTest(classes = CmsApp.class)
public class AdminAttendanceResourceIT {

    private static final LocalDate DEFAULT_UPDATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    @Autowired
    private AdminAttendanceRepository adminAttendanceRepository;

    @Autowired
    private AdminAttendanceMapper adminAttendanceMapper;

    @Autowired
    private AdminAttendanceService adminAttendanceService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.AdminAttendanceSearchRepositoryMockConfiguration
     */
    @Autowired
    private AdminAttendanceSearchRepository mockAdminAttendanceSearchRepository;

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

    private MockMvc restAdminAttendanceMockMvc;

    private AdminAttendance adminAttendance;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdminAttendanceResource adminAttendanceResource = new AdminAttendanceResource(adminAttendanceService);
        this.restAdminAttendanceMockMvc = MockMvcBuilders.standaloneSetup(adminAttendanceResource)
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
    public static AdminAttendance createEntity(EntityManager em) {
        AdminAttendance adminAttendance = new AdminAttendance()
            .updatedOn(DEFAULT_UPDATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY);
        return adminAttendance;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdminAttendance createUpdatedEntity(EntityManager em) {
        AdminAttendance adminAttendance = new AdminAttendance()
            .updatedOn(UPDATED_UPDATED_ON)
            .updatedBy(UPDATED_UPDATED_BY);
        return adminAttendance;
    }

    @BeforeEach
    public void initTest() {
        adminAttendance = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdminAttendance() throws Exception {
        int databaseSizeBeforeCreate = adminAttendanceRepository.findAll().size();

        // Create the AdminAttendance
        AdminAttendanceDTO adminAttendanceDTO = adminAttendanceMapper.toDto(adminAttendance);
        restAdminAttendanceMockMvc.perform(post("/api/admin-attendances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adminAttendanceDTO)))
            .andExpect(status().isCreated());

        // Validate the AdminAttendance in the database
        List<AdminAttendance> adminAttendanceList = adminAttendanceRepository.findAll();
        assertThat(adminAttendanceList).hasSize(databaseSizeBeforeCreate + 1);
        AdminAttendance testAdminAttendance = adminAttendanceList.get(adminAttendanceList.size() - 1);
        assertThat(testAdminAttendance.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
        assertThat(testAdminAttendance.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);

        // Validate the AdminAttendance in Elasticsearch
        verify(mockAdminAttendanceSearchRepository, times(1)).save(testAdminAttendance);
    }

    @Test
    @Transactional
    public void createAdminAttendanceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adminAttendanceRepository.findAll().size();

        // Create the AdminAttendance with an existing ID
        adminAttendance.setId(1L);
        AdminAttendanceDTO adminAttendanceDTO = adminAttendanceMapper.toDto(adminAttendance);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdminAttendanceMockMvc.perform(post("/api/admin-attendances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adminAttendanceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdminAttendance in the database
        List<AdminAttendance> adminAttendanceList = adminAttendanceRepository.findAll();
        assertThat(adminAttendanceList).hasSize(databaseSizeBeforeCreate);

        // Validate the AdminAttendance in Elasticsearch
        verify(mockAdminAttendanceSearchRepository, times(0)).save(adminAttendance);
    }


    @Test
    @Transactional
    public void getAllAdminAttendances() throws Exception {
        // Initialize the database
        adminAttendanceRepository.saveAndFlush(adminAttendance);

        // Get all the adminAttendanceList
        restAdminAttendanceMockMvc.perform(get("/api/admin-attendances?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adminAttendance.getId().intValue())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())));
    }
    
    @Test
    @Transactional
    public void getAdminAttendance() throws Exception {
        // Initialize the database
        adminAttendanceRepository.saveAndFlush(adminAttendance);

        // Get the adminAttendance
        restAdminAttendanceMockMvc.perform(get("/api/admin-attendances/{id}", adminAttendance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(adminAttendance.getId().intValue()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAdminAttendance() throws Exception {
        // Get the adminAttendance
        restAdminAttendanceMockMvc.perform(get("/api/admin-attendances/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdminAttendance() throws Exception {
        // Initialize the database
        adminAttendanceRepository.saveAndFlush(adminAttendance);

        int databaseSizeBeforeUpdate = adminAttendanceRepository.findAll().size();

        // Update the adminAttendance
        AdminAttendance updatedAdminAttendance = adminAttendanceRepository.findById(adminAttendance.getId()).get();
        // Disconnect from session so that the updates on updatedAdminAttendance are not directly saved in db
        em.detach(updatedAdminAttendance);
        updatedAdminAttendance
            .updatedOn(UPDATED_UPDATED_ON)
            .updatedBy(UPDATED_UPDATED_BY);
        AdminAttendanceDTO adminAttendanceDTO = adminAttendanceMapper.toDto(updatedAdminAttendance);

        restAdminAttendanceMockMvc.perform(put("/api/admin-attendances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adminAttendanceDTO)))
            .andExpect(status().isOk());

        // Validate the AdminAttendance in the database
        List<AdminAttendance> adminAttendanceList = adminAttendanceRepository.findAll();
        assertThat(adminAttendanceList).hasSize(databaseSizeBeforeUpdate);
        AdminAttendance testAdminAttendance = adminAttendanceList.get(adminAttendanceList.size() - 1);
        assertThat(testAdminAttendance.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
        assertThat(testAdminAttendance.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);

        // Validate the AdminAttendance in Elasticsearch
        verify(mockAdminAttendanceSearchRepository, times(1)).save(testAdminAttendance);
    }

    @Test
    @Transactional
    public void updateNonExistingAdminAttendance() throws Exception {
        int databaseSizeBeforeUpdate = adminAttendanceRepository.findAll().size();

        // Create the AdminAttendance
        AdminAttendanceDTO adminAttendanceDTO = adminAttendanceMapper.toDto(adminAttendance);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdminAttendanceMockMvc.perform(put("/api/admin-attendances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adminAttendanceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdminAttendance in the database
        List<AdminAttendance> adminAttendanceList = adminAttendanceRepository.findAll();
        assertThat(adminAttendanceList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AdminAttendance in Elasticsearch
        verify(mockAdminAttendanceSearchRepository, times(0)).save(adminAttendance);
    }

    @Test
    @Transactional
    public void deleteAdminAttendance() throws Exception {
        // Initialize the database
        adminAttendanceRepository.saveAndFlush(adminAttendance);

        int databaseSizeBeforeDelete = adminAttendanceRepository.findAll().size();

        // Delete the adminAttendance
        restAdminAttendanceMockMvc.perform(delete("/api/admin-attendances/{id}", adminAttendance.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdminAttendance> adminAttendanceList = adminAttendanceRepository.findAll();
        assertThat(adminAttendanceList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AdminAttendance in Elasticsearch
        verify(mockAdminAttendanceSearchRepository, times(1)).deleteById(adminAttendance.getId());
    }

    @Test
    @Transactional
    public void searchAdminAttendance() throws Exception {
        // Initialize the database
        adminAttendanceRepository.saveAndFlush(adminAttendance);
        when(mockAdminAttendanceSearchRepository.search(queryStringQuery("id:" + adminAttendance.getId())))
            .thenReturn(Collections.singletonList(adminAttendance));
        // Search the adminAttendance
        restAdminAttendanceMockMvc.perform(get("/api/_search/admin-attendances?query=id:" + adminAttendance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adminAttendance.getId().intValue())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdminAttendance.class);
        AdminAttendance adminAttendance1 = new AdminAttendance();
        adminAttendance1.setId(1L);
        AdminAttendance adminAttendance2 = new AdminAttendance();
        adminAttendance2.setId(adminAttendance1.getId());
        assertThat(adminAttendance1).isEqualTo(adminAttendance2);
        adminAttendance2.setId(2L);
        assertThat(adminAttendance1).isNotEqualTo(adminAttendance2);
        adminAttendance1.setId(null);
        assertThat(adminAttendance1).isNotEqualTo(adminAttendance2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdminAttendanceDTO.class);
        AdminAttendanceDTO adminAttendanceDTO1 = new AdminAttendanceDTO();
        adminAttendanceDTO1.setId(1L);
        AdminAttendanceDTO adminAttendanceDTO2 = new AdminAttendanceDTO();
        assertThat(adminAttendanceDTO1).isNotEqualTo(adminAttendanceDTO2);
        adminAttendanceDTO2.setId(adminAttendanceDTO1.getId());
        assertThat(adminAttendanceDTO1).isEqualTo(adminAttendanceDTO2);
        adminAttendanceDTO2.setId(2L);
        assertThat(adminAttendanceDTO1).isNotEqualTo(adminAttendanceDTO2);
        adminAttendanceDTO1.setId(null);
        assertThat(adminAttendanceDTO1).isNotEqualTo(adminAttendanceDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(adminAttendanceMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(adminAttendanceMapper.fromId(null)).isNull();
    }
}
