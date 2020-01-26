package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;
import com.synectiks.cms.domain.TransportRoute;
import com.synectiks.cms.repository.TransportRouteRepository;
import com.synectiks.cms.repository.search.TransportRouteSearchRepository;
import com.synectiks.cms.service.TransportRouteService;
import com.synectiks.cms.service.dto.TransportRouteDTO;
import com.synectiks.cms.service.mapper.TransportRouteMapper;
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
import java.util.Collections;
import java.util.List;

import static com.synectiks.cms.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.synectiks.cms.domain.enumeration.RouteFrequency;
/**
 * Integration tests for the {@Link TransportRouteResource} REST controller.
 */
@SpringBootTest(classes = CmsApp.class)
public class TransportRouteResourceIT {

    private static final String DEFAULT_ROUTE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ROUTE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ROUTE_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_ROUTE_DETAILS = "BBBBBBBBBB";

    private static final String DEFAULT_ROUTE_MAP_URL = "AAAAAAAAAA";
    private static final String UPDATED_ROUTE_MAP_URL = "BBBBBBBBBB";

    private static final Integer DEFAULT_NO_OF_STOPS = 1;
    private static final Integer UPDATED_NO_OF_STOPS = 2;

    private static final RouteFrequency DEFAULT_ROUTE_FREQUENCY = RouteFrequency.MORNINGPICKUP;
    private static final RouteFrequency UPDATED_ROUTE_FREQUENCY = RouteFrequency.AFTERNOONDROPANDPICKUP;

    @Autowired
    private TransportRouteRepository transportRouteRepository;

    @Autowired
    private TransportRouteMapper transportRouteMapper;

    @Autowired
    private TransportRouteService transportRouteService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.TransportRouteSearchRepositoryMockConfiguration
     */
    @Autowired
    private TransportRouteSearchRepository mockTransportRouteSearchRepository;

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

    private MockMvc restTransportRouteMockMvc;

    private TransportRoute transportRoute;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TransportRouteResource transportRouteResource = new TransportRouteResource(transportRouteService);
        this.restTransportRouteMockMvc = MockMvcBuilders.standaloneSetup(transportRouteResource)
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
    public static TransportRoute createEntity(EntityManager em) {
        TransportRoute transportRoute = new TransportRoute()
            .routeName(DEFAULT_ROUTE_NAME)
            .routeDetails(DEFAULT_ROUTE_DETAILS)
            .routeMapUrl(DEFAULT_ROUTE_MAP_URL)
            .noOfStops(DEFAULT_NO_OF_STOPS)
            .routeFrequency(DEFAULT_ROUTE_FREQUENCY);
        return transportRoute;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TransportRoute createUpdatedEntity(EntityManager em) {
        TransportRoute transportRoute = new TransportRoute()
            .routeName(UPDATED_ROUTE_NAME)
            .routeDetails(UPDATED_ROUTE_DETAILS)
            .routeMapUrl(UPDATED_ROUTE_MAP_URL)
            .noOfStops(UPDATED_NO_OF_STOPS)
            .routeFrequency(UPDATED_ROUTE_FREQUENCY);
        return transportRoute;
    }

    @BeforeEach
    public void initTest() {
        transportRoute = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransportRoute() throws Exception {
        int databaseSizeBeforeCreate = transportRouteRepository.findAll().size();

        // Create the TransportRoute
        TransportRouteDTO transportRouteDTO = transportRouteMapper.toDto(transportRoute);
        restTransportRouteMockMvc.perform(post("/api/transport-routes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transportRouteDTO)))
            .andExpect(status().isCreated());

        // Validate the TransportRoute in the database
        List<TransportRoute> transportRouteList = transportRouteRepository.findAll();
        assertThat(transportRouteList).hasSize(databaseSizeBeforeCreate + 1);
        TransportRoute testTransportRoute = transportRouteList.get(transportRouteList.size() - 1);
        assertThat(testTransportRoute.getRouteName()).isEqualTo(DEFAULT_ROUTE_NAME);
        assertThat(testTransportRoute.getRouteDetails()).isEqualTo(DEFAULT_ROUTE_DETAILS);
        assertThat(testTransportRoute.getRouteMapUrl()).isEqualTo(DEFAULT_ROUTE_MAP_URL);
        assertThat(testTransportRoute.getNoOfStops()).isEqualTo(DEFAULT_NO_OF_STOPS);
        assertThat(testTransportRoute.getRouteFrequency()).isEqualTo(DEFAULT_ROUTE_FREQUENCY);

        // Validate the TransportRoute in Elasticsearch
        verify(mockTransportRouteSearchRepository, times(1)).save(testTransportRoute);
    }

    @Test
    @Transactional
    public void createTransportRouteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transportRouteRepository.findAll().size();

        // Create the TransportRoute with an existing ID
        transportRoute.setId(1L);
        TransportRouteDTO transportRouteDTO = transportRouteMapper.toDto(transportRoute);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransportRouteMockMvc.perform(post("/api/transport-routes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transportRouteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TransportRoute in the database
        List<TransportRoute> transportRouteList = transportRouteRepository.findAll();
        assertThat(transportRouteList).hasSize(databaseSizeBeforeCreate);

        // Validate the TransportRoute in Elasticsearch
        verify(mockTransportRouteSearchRepository, times(0)).save(transportRoute);
    }


    @Test
    @Transactional
    public void checkRouteNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = transportRouteRepository.findAll().size();
        // set the field null
        transportRoute.setRouteName(null);

        // Create the TransportRoute, which fails.
        TransportRouteDTO transportRouteDTO = transportRouteMapper.toDto(transportRoute);

        restTransportRouteMockMvc.perform(post("/api/transport-routes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transportRouteDTO)))
            .andExpect(status().isBadRequest());

        List<TransportRoute> transportRouteList = transportRouteRepository.findAll();
        assertThat(transportRouteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRouteDetailsIsRequired() throws Exception {
        int databaseSizeBeforeTest = transportRouteRepository.findAll().size();
        // set the field null
        transportRoute.setRouteDetails(null);

        // Create the TransportRoute, which fails.
        TransportRouteDTO transportRouteDTO = transportRouteMapper.toDto(transportRoute);

        restTransportRouteMockMvc.perform(post("/api/transport-routes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transportRouteDTO)))
            .andExpect(status().isBadRequest());

        List<TransportRoute> transportRouteList = transportRouteRepository.findAll();
        assertThat(transportRouteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNoOfStopsIsRequired() throws Exception {
        int databaseSizeBeforeTest = transportRouteRepository.findAll().size();
        // set the field null
        transportRoute.setNoOfStops(null);

        // Create the TransportRoute, which fails.
        TransportRouteDTO transportRouteDTO = transportRouteMapper.toDto(transportRoute);

        restTransportRouteMockMvc.perform(post("/api/transport-routes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transportRouteDTO)))
            .andExpect(status().isBadRequest());

        List<TransportRoute> transportRouteList = transportRouteRepository.findAll();
        assertThat(transportRouteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRouteFrequencyIsRequired() throws Exception {
        int databaseSizeBeforeTest = transportRouteRepository.findAll().size();
        // set the field null
        transportRoute.setRouteFrequency(null);

        // Create the TransportRoute, which fails.
        TransportRouteDTO transportRouteDTO = transportRouteMapper.toDto(transportRoute);

        restTransportRouteMockMvc.perform(post("/api/transport-routes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transportRouteDTO)))
            .andExpect(status().isBadRequest());

        List<TransportRoute> transportRouteList = transportRouteRepository.findAll();
        assertThat(transportRouteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTransportRoutes() throws Exception {
        // Initialize the database
        transportRouteRepository.saveAndFlush(transportRoute);

        // Get all the transportRouteList
        restTransportRouteMockMvc.perform(get("/api/transport-routes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transportRoute.getId().intValue())))
            .andExpect(jsonPath("$.[*].routeName").value(hasItem(DEFAULT_ROUTE_NAME.toString())))
            .andExpect(jsonPath("$.[*].routeDetails").value(hasItem(DEFAULT_ROUTE_DETAILS.toString())))
            .andExpect(jsonPath("$.[*].routeMapUrl").value(hasItem(DEFAULT_ROUTE_MAP_URL.toString())))
            .andExpect(jsonPath("$.[*].noOfStops").value(hasItem(DEFAULT_NO_OF_STOPS)))
            .andExpect(jsonPath("$.[*].routeFrequency").value(hasItem(DEFAULT_ROUTE_FREQUENCY.toString())));
    }
    
    @Test
    @Transactional
    public void getTransportRoute() throws Exception {
        // Initialize the database
        transportRouteRepository.saveAndFlush(transportRoute);

        // Get the transportRoute
        restTransportRouteMockMvc.perform(get("/api/transport-routes/{id}", transportRoute.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(transportRoute.getId().intValue()))
            .andExpect(jsonPath("$.routeName").value(DEFAULT_ROUTE_NAME.toString()))
            .andExpect(jsonPath("$.routeDetails").value(DEFAULT_ROUTE_DETAILS.toString()))
            .andExpect(jsonPath("$.routeMapUrl").value(DEFAULT_ROUTE_MAP_URL.toString()))
            .andExpect(jsonPath("$.noOfStops").value(DEFAULT_NO_OF_STOPS))
            .andExpect(jsonPath("$.routeFrequency").value(DEFAULT_ROUTE_FREQUENCY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTransportRoute() throws Exception {
        // Get the transportRoute
        restTransportRouteMockMvc.perform(get("/api/transport-routes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransportRoute() throws Exception {
        // Initialize the database
        transportRouteRepository.saveAndFlush(transportRoute);

        int databaseSizeBeforeUpdate = transportRouteRepository.findAll().size();

        // Update the transportRoute
        TransportRoute updatedTransportRoute = transportRouteRepository.findById(transportRoute.getId()).get();
        // Disconnect from session so that the updates on updatedTransportRoute are not directly saved in db
        em.detach(updatedTransportRoute);
        updatedTransportRoute
            .routeName(UPDATED_ROUTE_NAME)
            .routeDetails(UPDATED_ROUTE_DETAILS)
            .routeMapUrl(UPDATED_ROUTE_MAP_URL)
            .noOfStops(UPDATED_NO_OF_STOPS)
            .routeFrequency(UPDATED_ROUTE_FREQUENCY);
        TransportRouteDTO transportRouteDTO = transportRouteMapper.toDto(updatedTransportRoute);

        restTransportRouteMockMvc.perform(put("/api/transport-routes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transportRouteDTO)))
            .andExpect(status().isOk());

        // Validate the TransportRoute in the database
        List<TransportRoute> transportRouteList = transportRouteRepository.findAll();
        assertThat(transportRouteList).hasSize(databaseSizeBeforeUpdate);
        TransportRoute testTransportRoute = transportRouteList.get(transportRouteList.size() - 1);
        assertThat(testTransportRoute.getRouteName()).isEqualTo(UPDATED_ROUTE_NAME);
        assertThat(testTransportRoute.getRouteDetails()).isEqualTo(UPDATED_ROUTE_DETAILS);
        assertThat(testTransportRoute.getRouteMapUrl()).isEqualTo(UPDATED_ROUTE_MAP_URL);
        assertThat(testTransportRoute.getNoOfStops()).isEqualTo(UPDATED_NO_OF_STOPS);
        assertThat(testTransportRoute.getRouteFrequency()).isEqualTo(UPDATED_ROUTE_FREQUENCY);

        // Validate the TransportRoute in Elasticsearch
        verify(mockTransportRouteSearchRepository, times(1)).save(testTransportRoute);
    }

    @Test
    @Transactional
    public void updateNonExistingTransportRoute() throws Exception {
        int databaseSizeBeforeUpdate = transportRouteRepository.findAll().size();

        // Create the TransportRoute
        TransportRouteDTO transportRouteDTO = transportRouteMapper.toDto(transportRoute);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransportRouteMockMvc.perform(put("/api/transport-routes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transportRouteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TransportRoute in the database
        List<TransportRoute> transportRouteList = transportRouteRepository.findAll();
        assertThat(transportRouteList).hasSize(databaseSizeBeforeUpdate);

        // Validate the TransportRoute in Elasticsearch
        verify(mockTransportRouteSearchRepository, times(0)).save(transportRoute);
    }

    @Test
    @Transactional
    public void deleteTransportRoute() throws Exception {
        // Initialize the database
        transportRouteRepository.saveAndFlush(transportRoute);

        int databaseSizeBeforeDelete = transportRouteRepository.findAll().size();

        // Delete the transportRoute
        restTransportRouteMockMvc.perform(delete("/api/transport-routes/{id}", transportRoute.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TransportRoute> transportRouteList = transportRouteRepository.findAll();
        assertThat(transportRouteList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the TransportRoute in Elasticsearch
        verify(mockTransportRouteSearchRepository, times(1)).deleteById(transportRoute.getId());
    }

    @Test
    @Transactional
    public void searchTransportRoute() throws Exception {
        // Initialize the database
        transportRouteRepository.saveAndFlush(transportRoute);
        when(mockTransportRouteSearchRepository.search(queryStringQuery("id:" + transportRoute.getId())))
            .thenReturn(Collections.singletonList(transportRoute));
        // Search the transportRoute
        restTransportRouteMockMvc.perform(get("/api/_search/transport-routes?query=id:" + transportRoute.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transportRoute.getId().intValue())))
            .andExpect(jsonPath("$.[*].routeName").value(hasItem(DEFAULT_ROUTE_NAME)))
            .andExpect(jsonPath("$.[*].routeDetails").value(hasItem(DEFAULT_ROUTE_DETAILS)))
            .andExpect(jsonPath("$.[*].routeMapUrl").value(hasItem(DEFAULT_ROUTE_MAP_URL)))
            .andExpect(jsonPath("$.[*].noOfStops").value(hasItem(DEFAULT_NO_OF_STOPS)))
            .andExpect(jsonPath("$.[*].routeFrequency").value(hasItem(DEFAULT_ROUTE_FREQUENCY.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransportRoute.class);
        TransportRoute transportRoute1 = new TransportRoute();
        transportRoute1.setId(1L);
        TransportRoute transportRoute2 = new TransportRoute();
        transportRoute2.setId(transportRoute1.getId());
        assertThat(transportRoute1).isEqualTo(transportRoute2);
        transportRoute2.setId(2L);
        assertThat(transportRoute1).isNotEqualTo(transportRoute2);
        transportRoute1.setId(null);
        assertThat(transportRoute1).isNotEqualTo(transportRoute2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransportRouteDTO.class);
        TransportRouteDTO transportRouteDTO1 = new TransportRouteDTO();
        transportRouteDTO1.setId(1L);
        TransportRouteDTO transportRouteDTO2 = new TransportRouteDTO();
        assertThat(transportRouteDTO1).isNotEqualTo(transportRouteDTO2);
        transportRouteDTO2.setId(transportRouteDTO1.getId());
        assertThat(transportRouteDTO1).isEqualTo(transportRouteDTO2);
        transportRouteDTO2.setId(2L);
        assertThat(transportRouteDTO1).isNotEqualTo(transportRouteDTO2);
        transportRouteDTO1.setId(null);
        assertThat(transportRouteDTO1).isNotEqualTo(transportRouteDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(transportRouteMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(transportRouteMapper.fromId(null)).isNull();
    }
}
