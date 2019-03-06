package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.Teach;
import com.synectiks.cms.repository.TeachRepository;
import com.synectiks.cms.repository.search.TeachSearchRepository;
import com.synectiks.cms.service.TeachService;
import com.synectiks.cms.service.dto.TeachDTO;
import com.synectiks.cms.service.mapper.TeachMapper;
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

/**
 * Test class for the TeachResource REST controller.
 *
 * @see TeachResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class TeachResourceIntTest {

    private static final String DEFAULT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_DESC = "BBBBBBBBBB";

    @Autowired
    private TeachRepository teachRepository;

    @Autowired
    private TeachMapper teachMapper;

    @Autowired
    private TeachService teachService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.TeachSearchRepositoryMockConfiguration
     */
    @Autowired
    private TeachSearchRepository mockTeachSearchRepository;

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

    private MockMvc restTeachMockMvc;

    private Teach teach;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TeachResource teachResource = new TeachResource(teachService);
        this.restTeachMockMvc = MockMvcBuilders.standaloneSetup(teachResource)
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
    public static Teach createEntity(EntityManager em) {
        Teach teach = new Teach()
            .desc(DEFAULT_DESC);
        return teach;
    }

    @Before
    public void initTest() {
        teach = createEntity(em);
    }

    @Test
    @Transactional
    public void createTeach() throws Exception {
        int databaseSizeBeforeCreate = teachRepository.findAll().size();

        // Create the Teach
        TeachDTO teachDTO = teachMapper.toDto(teach);
        restTeachMockMvc.perform(post("/api/teaches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teachDTO)))
            .andExpect(status().isCreated());

        // Validate the Teach in the database
        List<Teach> teachList = teachRepository.findAll();
        assertThat(teachList).hasSize(databaseSizeBeforeCreate + 1);
        Teach testTeach = teachList.get(teachList.size() - 1);
        assertThat(testTeach.getDesc()).isEqualTo(DEFAULT_DESC);

        // Validate the Teach in Elasticsearch
        verify(mockTeachSearchRepository, times(1)).save(testTeach);
    }

    @Test
    @Transactional
    public void createTeachWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = teachRepository.findAll().size();

        // Create the Teach with an existing ID
        teach.setId(1L);
        TeachDTO teachDTO = teachMapper.toDto(teach);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTeachMockMvc.perform(post("/api/teaches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teachDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Teach in the database
        List<Teach> teachList = teachRepository.findAll();
        assertThat(teachList).hasSize(databaseSizeBeforeCreate);

        // Validate the Teach in Elasticsearch
        verify(mockTeachSearchRepository, times(0)).save(teach);
    }

    @Test
    @Transactional
    public void getAllTeaches() throws Exception {
        // Initialize the database
        teachRepository.saveAndFlush(teach);

        // Get all the teachList
        restTeachMockMvc.perform(get("/api/teaches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(teach.getId().intValue())))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC.toString())));
    }
    
    @Test
    @Transactional
    public void getTeach() throws Exception {
        // Initialize the database
        teachRepository.saveAndFlush(teach);

        // Get the teach
        restTeachMockMvc.perform(get("/api/teaches/{id}", teach.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(teach.getId().intValue()))
            .andExpect(jsonPath("$.desc").value(DEFAULT_DESC.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTeach() throws Exception {
        // Get the teach
        restTeachMockMvc.perform(get("/api/teaches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTeach() throws Exception {
        // Initialize the database
        teachRepository.saveAndFlush(teach);

        int databaseSizeBeforeUpdate = teachRepository.findAll().size();

        // Update the teach
        Teach updatedTeach = teachRepository.findById(teach.getId()).get();
        // Disconnect from session so that the updates on updatedTeach are not directly saved in db
        em.detach(updatedTeach);
        updatedTeach
            .desc(UPDATED_DESC);
        TeachDTO teachDTO = teachMapper.toDto(updatedTeach);

        restTeachMockMvc.perform(put("/api/teaches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teachDTO)))
            .andExpect(status().isOk());

        // Validate the Teach in the database
        List<Teach> teachList = teachRepository.findAll();
        assertThat(teachList).hasSize(databaseSizeBeforeUpdate);
        Teach testTeach = teachList.get(teachList.size() - 1);
        assertThat(testTeach.getDesc()).isEqualTo(UPDATED_DESC);

        // Validate the Teach in Elasticsearch
        verify(mockTeachSearchRepository, times(1)).save(testTeach);
    }

    @Test
    @Transactional
    public void updateNonExistingTeach() throws Exception {
        int databaseSizeBeforeUpdate = teachRepository.findAll().size();

        // Create the Teach
        TeachDTO teachDTO = teachMapper.toDto(teach);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTeachMockMvc.perform(put("/api/teaches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teachDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Teach in the database
        List<Teach> teachList = teachRepository.findAll();
        assertThat(teachList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Teach in Elasticsearch
        verify(mockTeachSearchRepository, times(0)).save(teach);
    }

    @Test
    @Transactional
    public void deleteTeach() throws Exception {
        // Initialize the database
        teachRepository.saveAndFlush(teach);

        int databaseSizeBeforeDelete = teachRepository.findAll().size();

        // Delete the teach
        restTeachMockMvc.perform(delete("/api/teaches/{id}", teach.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Teach> teachList = teachRepository.findAll();
        assertThat(teachList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Teach in Elasticsearch
        verify(mockTeachSearchRepository, times(1)).deleteById(teach.getId());
    }

    @Test
    @Transactional
    public void searchTeach() throws Exception {
        // Initialize the database
        teachRepository.saveAndFlush(teach);
        when(mockTeachSearchRepository.search(queryStringQuery("id:" + teach.getId())))
            .thenReturn(Collections.singletonList(teach));
        // Search the teach
        restTeachMockMvc.perform(get("/api/_search/teaches?query=id:" + teach.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(teach.getId().intValue())))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Teach.class);
        Teach teach1 = new Teach();
        teach1.setId(1L);
        Teach teach2 = new Teach();
        teach2.setId(teach1.getId());
        assertThat(teach1).isEqualTo(teach2);
        teach2.setId(2L);
        assertThat(teach1).isNotEqualTo(teach2);
        teach1.setId(null);
        assertThat(teach1).isNotEqualTo(teach2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TeachDTO.class);
        TeachDTO teachDTO1 = new TeachDTO();
        teachDTO1.setId(1L);
        TeachDTO teachDTO2 = new TeachDTO();
        assertThat(teachDTO1).isNotEqualTo(teachDTO2);
        teachDTO2.setId(teachDTO1.getId());
        assertThat(teachDTO1).isEqualTo(teachDTO2);
        teachDTO2.setId(2L);
        assertThat(teachDTO1).isNotEqualTo(teachDTO2);
        teachDTO1.setId(null);
        assertThat(teachDTO1).isNotEqualTo(teachDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(teachMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(teachMapper.fromId(null)).isNull();
    }
}
