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

import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.Status;
/**
 * Test class for the FacilityResource REST controller.
 *
 * @see FacilityResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class FacilityResourceIntTest {

    private static final Status DEFAULT_TRANSPORT = Status.ACTIVE;
    private static final Status UPDATED_TRANSPORT = Status.DEACTIVE;

    private static final Status DEFAULT_MESS = Status.ACTIVE;
    private static final Status UPDATED_MESS = Status.DEACTIVE;

    private static final Status DEFAULT_GYM = Status.ACTIVE;
    private static final Status UPDATED_GYM = Status.DEACTIVE;

    private static final Status DEFAULT_CULTURAL_CLASS = Status.ACTIVE;
    private static final Status UPDATED_CULTURAL_CLASS = Status.DEACTIVE;

    private static final Status DEFAULT_LIBRARY = Status.ACTIVE;
    private static final Status UPDATED_LIBRARY = Status.DEACTIVE;

    private static final Status DEFAULT_SPORTS = Status.ACTIVE;
    private static final Status UPDATED_SPORTS = Status.DEACTIVE;

    private static final Status DEFAULT_SWIMMING = Status.ACTIVE;
    private static final Status UPDATED_SWIMMING = Status.DEACTIVE;

    private static final Status DEFAULT_EXTRA_CLASS = Status.ACTIVE;
    private static final Status UPDATED_EXTRA_CLASS = Status.DEACTIVE;

    private static final Status DEFAULT_HANDICRAFTS = Status.ACTIVE;
    private static final Status UPDATED_HANDICRAFTS = Status.DEACTIVE;

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
            .transport(DEFAULT_TRANSPORT)
            .mess(DEFAULT_MESS)
            .gym(DEFAULT_GYM)
            .culturalClass(DEFAULT_CULTURAL_CLASS)
            .library(DEFAULT_LIBRARY)
            .sports(DEFAULT_SPORTS)
            .swimming(DEFAULT_SWIMMING)
            .extraClass(DEFAULT_EXTRA_CLASS)
            .handicrafts(DEFAULT_HANDICRAFTS);
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
        assertThat(testFacility.getTransport()).isEqualTo(DEFAULT_TRANSPORT);
        assertThat(testFacility.getMess()).isEqualTo(DEFAULT_MESS);
        assertThat(testFacility.getGym()).isEqualTo(DEFAULT_GYM);
        assertThat(testFacility.getCulturalClass()).isEqualTo(DEFAULT_CULTURAL_CLASS);
        assertThat(testFacility.getLibrary()).isEqualTo(DEFAULT_LIBRARY);
        assertThat(testFacility.getSports()).isEqualTo(DEFAULT_SPORTS);
        assertThat(testFacility.getSwimming()).isEqualTo(DEFAULT_SWIMMING);
        assertThat(testFacility.getExtraClass()).isEqualTo(DEFAULT_EXTRA_CLASS);
        assertThat(testFacility.getHandicrafts()).isEqualTo(DEFAULT_HANDICRAFTS);

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
            .andExpect(jsonPath("$.[*].transport").value(hasItem(DEFAULT_TRANSPORT.toString())))
            .andExpect(jsonPath("$.[*].mess").value(hasItem(DEFAULT_MESS.toString())))
            .andExpect(jsonPath("$.[*].gym").value(hasItem(DEFAULT_GYM.toString())))
            .andExpect(jsonPath("$.[*].culturalClass").value(hasItem(DEFAULT_CULTURAL_CLASS.toString())))
            .andExpect(jsonPath("$.[*].library").value(hasItem(DEFAULT_LIBRARY.toString())))
            .andExpect(jsonPath("$.[*].sports").value(hasItem(DEFAULT_SPORTS.toString())))
            .andExpect(jsonPath("$.[*].swimming").value(hasItem(DEFAULT_SWIMMING.toString())))
            .andExpect(jsonPath("$.[*].extraClass").value(hasItem(DEFAULT_EXTRA_CLASS.toString())))
            .andExpect(jsonPath("$.[*].handicrafts").value(hasItem(DEFAULT_HANDICRAFTS.toString())));
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
            .andExpect(jsonPath("$.transport").value(DEFAULT_TRANSPORT.toString()))
            .andExpect(jsonPath("$.mess").value(DEFAULT_MESS.toString()))
            .andExpect(jsonPath("$.gym").value(DEFAULT_GYM.toString()))
            .andExpect(jsonPath("$.culturalClass").value(DEFAULT_CULTURAL_CLASS.toString()))
            .andExpect(jsonPath("$.library").value(DEFAULT_LIBRARY.toString()))
            .andExpect(jsonPath("$.sports").value(DEFAULT_SPORTS.toString()))
            .andExpect(jsonPath("$.swimming").value(DEFAULT_SWIMMING.toString()))
            .andExpect(jsonPath("$.extraClass").value(DEFAULT_EXTRA_CLASS.toString()))
            .andExpect(jsonPath("$.handicrafts").value(DEFAULT_HANDICRAFTS.toString()));
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
            .transport(UPDATED_TRANSPORT)
            .mess(UPDATED_MESS)
            .gym(UPDATED_GYM)
            .culturalClass(UPDATED_CULTURAL_CLASS)
            .library(UPDATED_LIBRARY)
            .sports(UPDATED_SPORTS)
            .swimming(UPDATED_SWIMMING)
            .extraClass(UPDATED_EXTRA_CLASS)
            .handicrafts(UPDATED_HANDICRAFTS);
        FacilityDTO facilityDTO = facilityMapper.toDto(updatedFacility);

        restFacilityMockMvc.perform(put("/api/facilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(facilityDTO)))
            .andExpect(status().isOk());

        // Validate the Facility in the database
        List<Facility> facilityList = facilityRepository.findAll();
        assertThat(facilityList).hasSize(databaseSizeBeforeUpdate);
        Facility testFacility = facilityList.get(facilityList.size() - 1);
        assertThat(testFacility.getTransport()).isEqualTo(UPDATED_TRANSPORT);
        assertThat(testFacility.getMess()).isEqualTo(UPDATED_MESS);
        assertThat(testFacility.getGym()).isEqualTo(UPDATED_GYM);
        assertThat(testFacility.getCulturalClass()).isEqualTo(UPDATED_CULTURAL_CLASS);
        assertThat(testFacility.getLibrary()).isEqualTo(UPDATED_LIBRARY);
        assertThat(testFacility.getSports()).isEqualTo(UPDATED_SPORTS);
        assertThat(testFacility.getSwimming()).isEqualTo(UPDATED_SWIMMING);
        assertThat(testFacility.getExtraClass()).isEqualTo(UPDATED_EXTRA_CLASS);
        assertThat(testFacility.getHandicrafts()).isEqualTo(UPDATED_HANDICRAFTS);

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
            .andExpect(jsonPath("$.[*].transport").value(hasItem(DEFAULT_TRANSPORT.toString())))
            .andExpect(jsonPath("$.[*].mess").value(hasItem(DEFAULT_MESS.toString())))
            .andExpect(jsonPath("$.[*].gym").value(hasItem(DEFAULT_GYM.toString())))
            .andExpect(jsonPath("$.[*].culturalClass").value(hasItem(DEFAULT_CULTURAL_CLASS.toString())))
            .andExpect(jsonPath("$.[*].library").value(hasItem(DEFAULT_LIBRARY.toString())))
            .andExpect(jsonPath("$.[*].sports").value(hasItem(DEFAULT_SPORTS.toString())))
            .andExpect(jsonPath("$.[*].swimming").value(hasItem(DEFAULT_SWIMMING.toString())))
            .andExpect(jsonPath("$.[*].extraClass").value(hasItem(DEFAULT_EXTRA_CLASS.toString())))
            .andExpect(jsonPath("$.[*].handicrafts").value(hasItem(DEFAULT_HANDICRAFTS.toString())));
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
