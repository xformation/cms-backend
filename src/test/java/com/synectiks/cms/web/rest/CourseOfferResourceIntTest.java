package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.CourseOffer;
import com.synectiks.cms.repository.CourseOfferRepository;
import com.synectiks.cms.repository.search.CourseOfferSearchRepository;
import com.synectiks.cms.service.CourseOfferService;
import com.synectiks.cms.service.dto.CourseOfferDTO;
import com.synectiks.cms.service.mapper.CourseOfferMapper;
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
 * Test class for the CourseOfferResource REST controller.
 *
 * @see CourseOfferResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class CourseOfferResourceIntTest {

    private static final String DEFAULT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_DESC = "BBBBBBBBBB";

    @Autowired
    private CourseOfferRepository courseOfferRepository;


    @Autowired
    private CourseOfferMapper courseOfferMapper;
    

    @Autowired
    private CourseOfferService courseOfferService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.CourseOfferSearchRepositoryMockConfiguration
     */
    @Autowired
    private CourseOfferSearchRepository mockCourseOfferSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCourseOfferMockMvc;

    private CourseOffer courseOffer;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CourseOfferResource courseOfferResource = new CourseOfferResource(courseOfferService);
        this.restCourseOfferMockMvc = MockMvcBuilders.standaloneSetup(courseOfferResource)
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
    public static CourseOffer createEntity(EntityManager em) {
        CourseOffer courseOffer = new CourseOffer()
            .desc(DEFAULT_DESC);
        return courseOffer;
    }

    @Before
    public void initTest() {
        courseOffer = createEntity(em);
    }

    @Test
    @Transactional
    public void createCourseOffer() throws Exception {
        int databaseSizeBeforeCreate = courseOfferRepository.findAll().size();

        // Create the CourseOffer
        CourseOfferDTO courseOfferDTO = courseOfferMapper.toDto(courseOffer);
        restCourseOfferMockMvc.perform(post("/api/course-offers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseOfferDTO)))
            .andExpect(status().isCreated());

        // Validate the CourseOffer in the database
        List<CourseOffer> courseOfferList = courseOfferRepository.findAll();
        assertThat(courseOfferList).hasSize(databaseSizeBeforeCreate + 1);
        CourseOffer testCourseOffer = courseOfferList.get(courseOfferList.size() - 1);
        assertThat(testCourseOffer.getDesc()).isEqualTo(DEFAULT_DESC);

        // Validate the CourseOffer in Elasticsearch
        verify(mockCourseOfferSearchRepository, times(1)).save(testCourseOffer);
    }

    @Test
    @Transactional
    public void createCourseOfferWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = courseOfferRepository.findAll().size();

        // Create the CourseOffer with an existing ID
        courseOffer.setId(1L);
        CourseOfferDTO courseOfferDTO = courseOfferMapper.toDto(courseOffer);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCourseOfferMockMvc.perform(post("/api/course-offers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseOfferDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CourseOffer in the database
        List<CourseOffer> courseOfferList = courseOfferRepository.findAll();
        assertThat(courseOfferList).hasSize(databaseSizeBeforeCreate);

        // Validate the CourseOffer in Elasticsearch
        verify(mockCourseOfferSearchRepository, times(0)).save(courseOffer);
    }

    @Test
    @Transactional
    public void getAllCourseOffers() throws Exception {
        // Initialize the database
        courseOfferRepository.saveAndFlush(courseOffer);

        // Get all the courseOfferList
        restCourseOfferMockMvc.perform(get("/api/course-offers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(courseOffer.getId().intValue())))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC.toString())));
    }
    

    @Test
    @Transactional
    public void getCourseOffer() throws Exception {
        // Initialize the database
        courseOfferRepository.saveAndFlush(courseOffer);

        // Get the courseOffer
        restCourseOfferMockMvc.perform(get("/api/course-offers/{id}", courseOffer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(courseOffer.getId().intValue()))
            .andExpect(jsonPath("$.desc").value(DEFAULT_DESC.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCourseOffer() throws Exception {
        // Get the courseOffer
        restCourseOfferMockMvc.perform(get("/api/course-offers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCourseOffer() throws Exception {
        // Initialize the database
        courseOfferRepository.saveAndFlush(courseOffer);

        int databaseSizeBeforeUpdate = courseOfferRepository.findAll().size();

        // Update the courseOffer
        CourseOffer updatedCourseOffer = courseOfferRepository.findById(courseOffer.getId()).get();
        // Disconnect from session so that the updates on updatedCourseOffer are not directly saved in db
        em.detach(updatedCourseOffer);
        updatedCourseOffer
            .desc(UPDATED_DESC);
        CourseOfferDTO courseOfferDTO = courseOfferMapper.toDto(updatedCourseOffer);

        restCourseOfferMockMvc.perform(put("/api/course-offers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseOfferDTO)))
            .andExpect(status().isOk());

        // Validate the CourseOffer in the database
        List<CourseOffer> courseOfferList = courseOfferRepository.findAll();
        assertThat(courseOfferList).hasSize(databaseSizeBeforeUpdate);
        CourseOffer testCourseOffer = courseOfferList.get(courseOfferList.size() - 1);
        assertThat(testCourseOffer.getDesc()).isEqualTo(UPDATED_DESC);

        // Validate the CourseOffer in Elasticsearch
        verify(mockCourseOfferSearchRepository, times(1)).save(testCourseOffer);
    }

    @Test
    @Transactional
    public void updateNonExistingCourseOffer() throws Exception {
        int databaseSizeBeforeUpdate = courseOfferRepository.findAll().size();

        // Create the CourseOffer
        CourseOfferDTO courseOfferDTO = courseOfferMapper.toDto(courseOffer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restCourseOfferMockMvc.perform(put("/api/course-offers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseOfferDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CourseOffer in the database
        List<CourseOffer> courseOfferList = courseOfferRepository.findAll();
        assertThat(courseOfferList).hasSize(databaseSizeBeforeUpdate);

        // Validate the CourseOffer in Elasticsearch
        verify(mockCourseOfferSearchRepository, times(0)).save(courseOffer);
    }

    @Test
    @Transactional
    public void deleteCourseOffer() throws Exception {
        // Initialize the database
        courseOfferRepository.saveAndFlush(courseOffer);

        int databaseSizeBeforeDelete = courseOfferRepository.findAll().size();

        // Get the courseOffer
        restCourseOfferMockMvc.perform(delete("/api/course-offers/{id}", courseOffer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CourseOffer> courseOfferList = courseOfferRepository.findAll();
        assertThat(courseOfferList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the CourseOffer in Elasticsearch
        verify(mockCourseOfferSearchRepository, times(1)).deleteById(courseOffer.getId());
    }

    @Test
    @Transactional
    public void searchCourseOffer() throws Exception {
        // Initialize the database
        courseOfferRepository.saveAndFlush(courseOffer);
        when(mockCourseOfferSearchRepository.search(queryStringQuery("id:" + courseOffer.getId())))
            .thenReturn(Collections.singletonList(courseOffer));
        // Search the courseOffer
        restCourseOfferMockMvc.perform(get("/api/_search/course-offers?query=id:" + courseOffer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(courseOffer.getId().intValue())))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CourseOffer.class);
        CourseOffer courseOffer1 = new CourseOffer();
        courseOffer1.setId(1L);
        CourseOffer courseOffer2 = new CourseOffer();
        courseOffer2.setId(courseOffer1.getId());
        assertThat(courseOffer1).isEqualTo(courseOffer2);
        courseOffer2.setId(2L);
        assertThat(courseOffer1).isNotEqualTo(courseOffer2);
        courseOffer1.setId(null);
        assertThat(courseOffer1).isNotEqualTo(courseOffer2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CourseOfferDTO.class);
        CourseOfferDTO courseOfferDTO1 = new CourseOfferDTO();
        courseOfferDTO1.setId(1L);
        CourseOfferDTO courseOfferDTO2 = new CourseOfferDTO();
        assertThat(courseOfferDTO1).isNotEqualTo(courseOfferDTO2);
        courseOfferDTO2.setId(courseOfferDTO1.getId());
        assertThat(courseOfferDTO1).isEqualTo(courseOfferDTO2);
        courseOfferDTO2.setId(2L);
        assertThat(courseOfferDTO1).isNotEqualTo(courseOfferDTO2);
        courseOfferDTO1.setId(null);
        assertThat(courseOfferDTO1).isNotEqualTo(courseOfferDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(courseOfferMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(courseOfferMapper.fromId(null)).isNull();
    }
}
