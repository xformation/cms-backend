package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.TypeOfGrading;
import com.synectiks.cms.repository.TypeOfGradingRepository;
import com.synectiks.cms.repository.search.TypeOfGradingSearchRepository;
import com.synectiks.cms.service.TypeOfGradingService;
import com.synectiks.cms.service.dto.TypeOfGradingDTO;
import com.synectiks.cms.service.mapper.TypeOfGradingMapper;
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

import com.synectiks.cms.domain.enumeration.GradesEnum;
/**
 * Test class for the TypeOfGradingResource REST controller.
 *
 * @see TypeOfGradingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class TypeOfGradingResourceIntTest {

    private static final Integer DEFAULT_MIN_MARKS = 1;
    private static final Integer UPDATED_MIN_MARKS = 2;

    private static final Integer DEFAULT_MAX_MARKS = 1;
    private static final Integer UPDATED_MAX_MARKS = 2;

    private static final GradesEnum DEFAULT_GRADES = GradesEnum.Aplus;
    private static final GradesEnum UPDATED_GRADES = GradesEnum.A;

    @Autowired
    private TypeOfGradingRepository typeOfGradingRepository;

    @Autowired
    private TypeOfGradingMapper typeOfGradingMapper;

    @Autowired
    private TypeOfGradingService typeOfGradingService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.TypeOfGradingSearchRepositoryMockConfiguration
     */
    @Autowired
    private TypeOfGradingSearchRepository mockTypeOfGradingSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTypeOfGradingMockMvc;

    private TypeOfGrading typeOfGrading;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TypeOfGradingResource typeOfGradingResource = new TypeOfGradingResource(typeOfGradingService);
        this.restTypeOfGradingMockMvc = MockMvcBuilders.standaloneSetup(typeOfGradingResource)
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
    public static TypeOfGrading createEntity(EntityManager em) {
        TypeOfGrading typeOfGrading = new TypeOfGrading()
            .minMarks(DEFAULT_MIN_MARKS)
            .maxMarks(DEFAULT_MAX_MARKS)
            .grades(DEFAULT_GRADES);
        return typeOfGrading;
    }

    @Before
    public void initTest() {
        typeOfGrading = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeOfGrading() throws Exception {
        int databaseSizeBeforeCreate = typeOfGradingRepository.findAll().size();

        // Create the TypeOfGrading
        TypeOfGradingDTO typeOfGradingDTO = typeOfGradingMapper.toDto(typeOfGrading);
        restTypeOfGradingMockMvc.perform(post("/api/type-of-gradings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeOfGradingDTO)))
            .andExpect(status().isCreated());

        // Validate the TypeOfGrading in the database
        List<TypeOfGrading> typeOfGradingList = typeOfGradingRepository.findAll();
        assertThat(typeOfGradingList).hasSize(databaseSizeBeforeCreate + 1);
        TypeOfGrading testTypeOfGrading = typeOfGradingList.get(typeOfGradingList.size() - 1);
        assertThat(testTypeOfGrading.getMinMarks()).isEqualTo(DEFAULT_MIN_MARKS);
        assertThat(testTypeOfGrading.getMaxMarks()).isEqualTo(DEFAULT_MAX_MARKS);
        assertThat(testTypeOfGrading.getGrades()).isEqualTo(DEFAULT_GRADES);

        // Validate the TypeOfGrading in Elasticsearch
        verify(mockTypeOfGradingSearchRepository, times(1)).save(testTypeOfGrading);
    }

    @Test
    @Transactional
    public void createTypeOfGradingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeOfGradingRepository.findAll().size();

        // Create the TypeOfGrading with an existing ID
        typeOfGrading.setId(1L);
        TypeOfGradingDTO typeOfGradingDTO = typeOfGradingMapper.toDto(typeOfGrading);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeOfGradingMockMvc.perform(post("/api/type-of-gradings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeOfGradingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeOfGrading in the database
        List<TypeOfGrading> typeOfGradingList = typeOfGradingRepository.findAll();
        assertThat(typeOfGradingList).hasSize(databaseSizeBeforeCreate);

        // Validate the TypeOfGrading in Elasticsearch
        verify(mockTypeOfGradingSearchRepository, times(0)).save(typeOfGrading);
    }

    @Test
    @Transactional
    public void checkMinMarksIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeOfGradingRepository.findAll().size();
        // set the field null
        typeOfGrading.setMinMarks(null);

        // Create the TypeOfGrading, which fails.
        TypeOfGradingDTO typeOfGradingDTO = typeOfGradingMapper.toDto(typeOfGrading);

        restTypeOfGradingMockMvc.perform(post("/api/type-of-gradings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeOfGradingDTO)))
            .andExpect(status().isBadRequest());

        List<TypeOfGrading> typeOfGradingList = typeOfGradingRepository.findAll();
        assertThat(typeOfGradingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxMarksIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeOfGradingRepository.findAll().size();
        // set the field null
        typeOfGrading.setMaxMarks(null);

        // Create the TypeOfGrading, which fails.
        TypeOfGradingDTO typeOfGradingDTO = typeOfGradingMapper.toDto(typeOfGrading);

        restTypeOfGradingMockMvc.perform(post("/api/type-of-gradings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeOfGradingDTO)))
            .andExpect(status().isBadRequest());

        List<TypeOfGrading> typeOfGradingList = typeOfGradingRepository.findAll();
        assertThat(typeOfGradingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGradesIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeOfGradingRepository.findAll().size();
        // set the field null
        typeOfGrading.setGrades(null);

        // Create the TypeOfGrading, which fails.
        TypeOfGradingDTO typeOfGradingDTO = typeOfGradingMapper.toDto(typeOfGrading);

        restTypeOfGradingMockMvc.perform(post("/api/type-of-gradings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeOfGradingDTO)))
            .andExpect(status().isBadRequest());

        List<TypeOfGrading> typeOfGradingList = typeOfGradingRepository.findAll();
        assertThat(typeOfGradingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTypeOfGradings() throws Exception {
        // Initialize the database
        typeOfGradingRepository.saveAndFlush(typeOfGrading);

        // Get all the typeOfGradingList
        restTypeOfGradingMockMvc.perform(get("/api/type-of-gradings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeOfGrading.getId().intValue())))
            .andExpect(jsonPath("$.[*].minMarks").value(hasItem(DEFAULT_MIN_MARKS)))
            .andExpect(jsonPath("$.[*].maxMarks").value(hasItem(DEFAULT_MAX_MARKS)))
            .andExpect(jsonPath("$.[*].grades").value(hasItem(DEFAULT_GRADES.toString())));
    }
    
    @Test
    @Transactional
    public void getTypeOfGrading() throws Exception {
        // Initialize the database
        typeOfGradingRepository.saveAndFlush(typeOfGrading);

        // Get the typeOfGrading
        restTypeOfGradingMockMvc.perform(get("/api/type-of-gradings/{id}", typeOfGrading.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(typeOfGrading.getId().intValue()))
            .andExpect(jsonPath("$.minMarks").value(DEFAULT_MIN_MARKS))
            .andExpect(jsonPath("$.maxMarks").value(DEFAULT_MAX_MARKS))
            .andExpect(jsonPath("$.grades").value(DEFAULT_GRADES.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTypeOfGrading() throws Exception {
        // Get the typeOfGrading
        restTypeOfGradingMockMvc.perform(get("/api/type-of-gradings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeOfGrading() throws Exception {
        // Initialize the database
        typeOfGradingRepository.saveAndFlush(typeOfGrading);

        int databaseSizeBeforeUpdate = typeOfGradingRepository.findAll().size();

        // Update the typeOfGrading
        TypeOfGrading updatedTypeOfGrading = typeOfGradingRepository.findById(typeOfGrading.getId()).get();
        // Disconnect from session so that the updates on updatedTypeOfGrading are not directly saved in db
        em.detach(updatedTypeOfGrading);
        updatedTypeOfGrading
            .minMarks(UPDATED_MIN_MARKS)
            .maxMarks(UPDATED_MAX_MARKS)
            .grades(UPDATED_GRADES);
        TypeOfGradingDTO typeOfGradingDTO = typeOfGradingMapper.toDto(updatedTypeOfGrading);

        restTypeOfGradingMockMvc.perform(put("/api/type-of-gradings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeOfGradingDTO)))
            .andExpect(status().isOk());

        // Validate the TypeOfGrading in the database
        List<TypeOfGrading> typeOfGradingList = typeOfGradingRepository.findAll();
        assertThat(typeOfGradingList).hasSize(databaseSizeBeforeUpdate);
        TypeOfGrading testTypeOfGrading = typeOfGradingList.get(typeOfGradingList.size() - 1);
        assertThat(testTypeOfGrading.getMinMarks()).isEqualTo(UPDATED_MIN_MARKS);
        assertThat(testTypeOfGrading.getMaxMarks()).isEqualTo(UPDATED_MAX_MARKS);
        assertThat(testTypeOfGrading.getGrades()).isEqualTo(UPDATED_GRADES);

        // Validate the TypeOfGrading in Elasticsearch
        verify(mockTypeOfGradingSearchRepository, times(1)).save(testTypeOfGrading);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeOfGrading() throws Exception {
        int databaseSizeBeforeUpdate = typeOfGradingRepository.findAll().size();

        // Create the TypeOfGrading
        TypeOfGradingDTO typeOfGradingDTO = typeOfGradingMapper.toDto(typeOfGrading);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeOfGradingMockMvc.perform(put("/api/type-of-gradings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeOfGradingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeOfGrading in the database
        List<TypeOfGrading> typeOfGradingList = typeOfGradingRepository.findAll();
        assertThat(typeOfGradingList).hasSize(databaseSizeBeforeUpdate);

        // Validate the TypeOfGrading in Elasticsearch
        verify(mockTypeOfGradingSearchRepository, times(0)).save(typeOfGrading);
    }

    @Test
    @Transactional
    public void deleteTypeOfGrading() throws Exception {
        // Initialize the database
        typeOfGradingRepository.saveAndFlush(typeOfGrading);

        int databaseSizeBeforeDelete = typeOfGradingRepository.findAll().size();

        // Get the typeOfGrading
        restTypeOfGradingMockMvc.perform(delete("/api/type-of-gradings/{id}", typeOfGrading.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TypeOfGrading> typeOfGradingList = typeOfGradingRepository.findAll();
        assertThat(typeOfGradingList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the TypeOfGrading in Elasticsearch
        verify(mockTypeOfGradingSearchRepository, times(1)).deleteById(typeOfGrading.getId());
    }

    @Test
    @Transactional
    public void searchTypeOfGrading() throws Exception {
        // Initialize the database
        typeOfGradingRepository.saveAndFlush(typeOfGrading);
        when(mockTypeOfGradingSearchRepository.search(queryStringQuery("id:" + typeOfGrading.getId())))
            .thenReturn(Collections.singletonList(typeOfGrading));
        // Search the typeOfGrading
        restTypeOfGradingMockMvc.perform(get("/api/_search/type-of-gradings?query=id:" + typeOfGrading.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeOfGrading.getId().intValue())))
            .andExpect(jsonPath("$.[*].minMarks").value(hasItem(DEFAULT_MIN_MARKS)))
            .andExpect(jsonPath("$.[*].maxMarks").value(hasItem(DEFAULT_MAX_MARKS)))
            .andExpect(jsonPath("$.[*].grades").value(hasItem(DEFAULT_GRADES.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeOfGrading.class);
        TypeOfGrading typeOfGrading1 = new TypeOfGrading();
        typeOfGrading1.setId(1L);
        TypeOfGrading typeOfGrading2 = new TypeOfGrading();
        typeOfGrading2.setId(typeOfGrading1.getId());
        assertThat(typeOfGrading1).isEqualTo(typeOfGrading2);
        typeOfGrading2.setId(2L);
        assertThat(typeOfGrading1).isNotEqualTo(typeOfGrading2);
        typeOfGrading1.setId(null);
        assertThat(typeOfGrading1).isNotEqualTo(typeOfGrading2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeOfGradingDTO.class);
        TypeOfGradingDTO typeOfGradingDTO1 = new TypeOfGradingDTO();
        typeOfGradingDTO1.setId(1L);
        TypeOfGradingDTO typeOfGradingDTO2 = new TypeOfGradingDTO();
        assertThat(typeOfGradingDTO1).isNotEqualTo(typeOfGradingDTO2);
        typeOfGradingDTO2.setId(typeOfGradingDTO1.getId());
        assertThat(typeOfGradingDTO1).isEqualTo(typeOfGradingDTO2);
        typeOfGradingDTO2.setId(2L);
        assertThat(typeOfGradingDTO1).isNotEqualTo(typeOfGradingDTO2);
        typeOfGradingDTO1.setId(null);
        assertThat(typeOfGradingDTO1).isNotEqualTo(typeOfGradingDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(typeOfGradingMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(typeOfGradingMapper.fromId(null)).isNull();
    }
}
