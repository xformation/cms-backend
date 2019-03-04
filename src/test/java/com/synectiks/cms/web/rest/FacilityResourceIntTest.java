package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.Facility;
import com.synectiks.cms.repository.FacilityRepository;
import com.synectiks.cms.repository.search.FacilitySearchRepository;
import com.synectiks.cms.service.FacilityService;
import com.synectiks.cms.service.dto.FacilityDTO;
import com.synectiks.cms.service.mapper.FacilityMapper;
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
 * Test class for the FacilityResource REST controller.
 *
 * @see FacilityResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class FacilityResourceIntTest {

    private static final String DEFAULT_FACILITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FACILITY_NAME = "BBBBBBBBBB";

    @Autowired
    private FacilityRepository facilityRepository;

    @Autowired
    private FacilityMapper facilityMapper;

    @Autowired
    private FacilityService facilityService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.FacilitySearchRepositoryMockConfiguration
     */
    @Autowired
    private FacilitySearchRepository mockFacilitySearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFacilityMockMvc;

    private Facility facility;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FacilityResource facilityResource = new FacilityResource(facilityService);
        this.restFacilityMockMvc = MockMvcBuilders.standaloneSetup(facilityResource)
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
    public static Facility createEntity(EntityManager em) {
        Facility facility = new Facility()
            .facilityName(DEFAULT_FACILITY_NAME);
        return facility;
    }

    @Before
    public void initTest() {
        facility = createEntity(em);
    }

    @Test
    @Transactional
    public void createFacility() throws Exception {
        int databaseSizeBeforeCreate = facilityRepository.findAll().size();

        // Create the Facility
        FacilityDTO facilityDTO = facilityMapper.toDto(facility);
        restFacilityMockMvc.perform(post("/api/facilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(facilityDTO)))
            .andExpect(status().isCreated());

        // Validate the Facility in the database
        List<Facility> facilityList = facilityRepository.findAll();
        assertThat(facilityList).hasSize(databaseSizeBeforeCreate + 1);
        Facility testFacility = facilityList.get(facilityList.size() - 1);
        assertThat(testFacility.getFacilityName()).isEqualTo(DEFAULT_FACILITY_NAME);

        // Validate the Facility in Elasticsearch
        verify(mockFacilitySearchRepository, times(1)).save(testFacility);
    }

    @Test
    @Transactional
    public void createFacilityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = facilityRepository.findAll().size();

        // Create the Facility with an existing ID
        facility.setId(1L);
        FacilityDTO facilityDTO = facilityMapper.toDto(facility);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFacilityMockMvc.perform(post("/api/facilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(facilityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Facility in the database
        List<Facility> facilityList = facilityRepository.findAll();
        assertThat(facilityList).hasSize(databaseSizeBeforeCreate);

        // Validate the Facility in Elasticsearch
        verify(mockFacilitySearchRepository, times(0)).save(facility);
    }

    @Test
    @Transactional
    public void getAllFacilities() throws Exception {
        // Initialize the database
        facilityRepository.saveAndFlush(facility);

        // Get all the facilityList
        restFacilityMockMvc.perform(get("/api/facilities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(facility.getId().intValue())))
            .andExpect(jsonPath("$.[*].facilityName").value(hasItem(DEFAULT_FACILITY_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getFacility() throws Exception {
        // Initialize the database
        facilityRepository.saveAndFlush(facility);

        // Get the facility
        restFacilityMockMvc.perform(get("/api/facilities/{id}", facility.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(facility.getId().intValue()))
            .andExpect(jsonPath("$.facilityName").value(DEFAULT_FACILITY_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFacility() throws Exception {
        // Get the facility
        restFacilityMockMvc.perform(get("/api/facilities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFacility() throws Exception {
        // Initialize the database
        facilityRepository.saveAndFlush(facility);

        int databaseSizeBeforeUpdate = facilityRepository.findAll().size();

        // Update the facility
        Facility updatedFacility = facilityRepository.findById(facility.getId()).get();
        // Disconnect from session so that the updates on updatedFacility are not directly saved in db
        em.detach(updatedFacility);
        updatedFacility
            .facilityName(UPDATED_FACILITY_NAME);
        FacilityDTO facilityDTO = facilityMapper.toDto(updatedFacility);

        restFacilityMockMvc.perform(put("/api/facilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(facilityDTO)))
            .andExpect(status().isOk());

        // Validate the Facility in the database
        List<Facility> facilityList = facilityRepository.findAll();
        assertThat(facilityList).hasSize(databaseSizeBeforeUpdate);
        Facility testFacility = facilityList.get(facilityList.size() - 1);
        assertThat(testFacility.getFacilityName()).isEqualTo(UPDATED_FACILITY_NAME);

        // Validate the Facility in Elasticsearch
        verify(mockFacilitySearchRepository, times(1)).save(testFacility);
    }

    @Test
    @Transactional
    public void updateNonExistingFacility() throws Exception {
        int databaseSizeBeforeUpdate = facilityRepository.findAll().size();

        // Create the Facility
        FacilityDTO facilityDTO = facilityMapper.toDto(facility);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFacilityMockMvc.perform(put("/api/facilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(facilityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Facility in the database
        List<Facility> facilityList = facilityRepository.findAll();
        assertThat(facilityList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Facility in Elasticsearch
        verify(mockFacilitySearchRepository, times(0)).save(facility);
    }

    @Test
    @Transactional
    public void deleteFacility() throws Exception {
        // Initialize the database
        facilityRepository.saveAndFlush(facility);

        int databaseSizeBeforeDelete = facilityRepository.findAll().size();

        // Get the facility
        restFacilityMockMvc.perform(delete("/api/facilities/{id}", facility.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Facility> facilityList = facilityRepository.findAll();
        assertThat(facilityList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Facility in Elasticsearch
        verify(mockFacilitySearchRepository, times(1)).deleteById(facility.getId());
    }

    @Test
    @Transactional
    public void searchFacility() throws Exception {
        // Initialize the database
        facilityRepository.saveAndFlush(facility);
        when(mockFacilitySearchRepository.search(queryStringQuery("id:" + facility.getId())))
            .thenReturn(Collections.singletonList(facility));
        // Search the facility
        restFacilityMockMvc.perform(get("/api/_search/facilities?query=id:" + facility.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(facility.getId().intValue())))
            .andExpect(jsonPath("$.[*].facilityName").value(hasItem(DEFAULT_FACILITY_NAME)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Facility.class);
        Facility facility1 = new Facility();
        facility1.setId(1L);
        Facility facility2 = new Facility();
        facility2.setId(facility1.getId());
        assertThat(facility1).isEqualTo(facility2);
        facility2.setId(2L);
        assertThat(facility1).isNotEqualTo(facility2);
        facility1.setId(null);
        assertThat(facility1).isNotEqualTo(facility2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FacilityDTO.class);
        FacilityDTO facilityDTO1 = new FacilityDTO();
        facilityDTO1.setId(1L);
        FacilityDTO facilityDTO2 = new FacilityDTO();
        assertThat(facilityDTO1).isNotEqualTo(facilityDTO2);
        facilityDTO2.setId(facilityDTO1.getId());
        assertThat(facilityDTO1).isEqualTo(facilityDTO2);
        facilityDTO2.setId(2L);
        assertThat(facilityDTO1).isNotEqualTo(facilityDTO2);
        facilityDTO1.setId(null);
        assertThat(facilityDTO1).isNotEqualTo(facilityDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(facilityMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(facilityMapper.fromId(null)).isNull();
    }
}
