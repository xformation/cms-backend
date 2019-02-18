package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.AdminAttendance;
import com.synectiks.cms.repository.AdminAttendanceRepository;
import com.synectiks.cms.repository.search.AdminAttendanceSearchRepository;
import com.synectiks.cms.service.AdminAttendanceService;
import com.synectiks.cms.service.dto.AdminAttendanceDTO;
import com.synectiks.cms.service.mapper.AdminAttendanceMapper;
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

import com.synectiks.cms.domain.enumeration.AttendanceStatusEnum;
/**
 * Test class for the AdminAttendanceResource REST controller.
 *
 * @see AdminAttendanceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class AdminAttendanceResourceIntTest {

    private static final AttendanceStatusEnum DEFAULT_ATTENDANCE_STATUS = AttendanceStatusEnum.PRESENT;
    private static final AttendanceStatusEnum UPDATED_ATTENDANCE_STATUS = AttendanceStatusEnum.ABSENT;

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

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

    private MockMvc restAdminAttendanceMockMvc;

    private AdminAttendance adminAttendance;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdminAttendanceResource adminAttendanceResource = new AdminAttendanceResource(adminAttendanceService);
        this.restAdminAttendanceMockMvc = MockMvcBuilders.standaloneSetup(adminAttendanceResource)
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
    public static AdminAttendance createEntity(EntityManager em) {
        AdminAttendance adminAttendance = new AdminAttendance()
            .attendanceStatus(DEFAULT_ATTENDANCE_STATUS)
            .comments(DEFAULT_COMMENTS);
        return adminAttendance;
    }

    @Before
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
        assertThat(testAdminAttendance.getAttendanceStatus()).isEqualTo(DEFAULT_ATTENDANCE_STATUS);
        assertThat(testAdminAttendance.getComments()).isEqualTo(DEFAULT_COMMENTS);

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
    public void checkAttendanceStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = adminAttendanceRepository.findAll().size();
        // set the field null
        adminAttendance.setAttendanceStatus(null);

        // Create the AdminAttendance, which fails.
        AdminAttendanceDTO adminAttendanceDTO = adminAttendanceMapper.toDto(adminAttendance);

        restAdminAttendanceMockMvc.perform(post("/api/admin-attendances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adminAttendanceDTO)))
            .andExpect(status().isBadRequest());

        List<AdminAttendance> adminAttendanceList = adminAttendanceRepository.findAll();
        assertThat(adminAttendanceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCommentsIsRequired() throws Exception {
        int databaseSizeBeforeTest = adminAttendanceRepository.findAll().size();
        // set the field null
        adminAttendance.setComments(null);

        // Create the AdminAttendance, which fails.
        AdminAttendanceDTO adminAttendanceDTO = adminAttendanceMapper.toDto(adminAttendance);

        restAdminAttendanceMockMvc.perform(post("/api/admin-attendances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adminAttendanceDTO)))
            .andExpect(status().isBadRequest());

        List<AdminAttendance> adminAttendanceList = adminAttendanceRepository.findAll();
        assertThat(adminAttendanceList).hasSize(databaseSizeBeforeTest);
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
            .andExpect(jsonPath("$.[*].attendanceStatus").value(hasItem(DEFAULT_ATTENDANCE_STATUS.toString())))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS.toString())));
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
            .andExpect(jsonPath("$.attendanceStatus").value(DEFAULT_ATTENDANCE_STATUS.toString()))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS.toString()));
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
            .attendanceStatus(UPDATED_ATTENDANCE_STATUS)
            .comments(UPDATED_COMMENTS);
        AdminAttendanceDTO adminAttendanceDTO = adminAttendanceMapper.toDto(updatedAdminAttendance);

        restAdminAttendanceMockMvc.perform(put("/api/admin-attendances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adminAttendanceDTO)))
            .andExpect(status().isOk());

        // Validate the AdminAttendance in the database
        List<AdminAttendance> adminAttendanceList = adminAttendanceRepository.findAll();
        assertThat(adminAttendanceList).hasSize(databaseSizeBeforeUpdate);
        AdminAttendance testAdminAttendance = adminAttendanceList.get(adminAttendanceList.size() - 1);
        assertThat(testAdminAttendance.getAttendanceStatus()).isEqualTo(UPDATED_ATTENDANCE_STATUS);
        assertThat(testAdminAttendance.getComments()).isEqualTo(UPDATED_COMMENTS);

        // Validate the AdminAttendance in Elasticsearch
        verify(mockAdminAttendanceSearchRepository, times(1)).save(testAdminAttendance);
    }

    @Test
    @Transactional
    public void updateNonExistingAdminAttendance() throws Exception {
        int databaseSizeBeforeUpdate = adminAttendanceRepository.findAll().size();

        // Create the AdminAttendance
        AdminAttendanceDTO adminAttendanceDTO = adminAttendanceMapper.toDto(adminAttendance);

        // If the entity doesn't have an ID, it will be created instead of just being updated
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

        // Get the adminAttendance
        restAdminAttendanceMockMvc.perform(delete("/api/admin-attendances/{id}", adminAttendance.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
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
            .andExpect(jsonPath("$.[*].attendanceStatus").value(hasItem(DEFAULT_ATTENDANCE_STATUS.toString())))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS.toString())));
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
