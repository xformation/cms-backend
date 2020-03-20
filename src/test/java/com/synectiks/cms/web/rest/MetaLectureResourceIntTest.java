package com.synectiks.cms.web.rest;

import static com.synectiks.cms.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.persistence.EntityManager;

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

import com.synectiks.cms.CmsApp;
import com.synectiks.cms.domain.MetaLecture;
import com.synectiks.cms.repository.MetaLectureRepository;
import com.synectiks.cms.repository.search.MetaLectureSearchRepository;
import com.synectiks.cms.service.MetaLectureService;
import com.synectiks.cms.service.dto.MetaLectureDTO;
import com.synectiks.cms.service.mapper.MetaLectureMapper;
import com.synectiks.cms.web.rest.errors.ExceptionTranslator;

/**
 * Test class for the MetaLectureResource REST controller.
 *
 * @see MetaLectureResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class MetaLectureResourceIntTest {

    private static final String DEFAULT_WEEK_DAY = "AAAAAAAAAA";
    private static final String UPDATED_WEEK_DAY = "BBBBBBBBBB";

    private static final String DEFAULT_START_TIME = "AAAAAAAAAA";
    private static final String UPDATED_START_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_END_TIME = "AAAAAAAAAA";
    private static final String UPDATED_END_TIME = "BBBBBBBBBB";

    @Autowired
    private MetaLectureRepository metaLectureRepository;

    @Autowired
    private MetaLectureMapper metaLectureMapper;

    @Autowired
    private MetaLectureService metaLectureService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.MetaLectureSearchRepositoryMockConfiguration
     */
    @Autowired
    private MetaLectureSearchRepository mockMetaLectureSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMetaLectureMockMvc;

    private MetaLecture metaLecture;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MetaLectureResource metaLectureResource = new MetaLectureResource(metaLectureService);
        this.restMetaLectureMockMvc = MockMvcBuilders.standaloneSetup(metaLectureResource)
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
    public static MetaLecture createEntity(EntityManager em) {
        MetaLecture metaLecture = new MetaLecture()
            .weekDay(DEFAULT_WEEK_DAY)
            .startTime(DEFAULT_START_TIME)
            .endTime(DEFAULT_END_TIME);
        return metaLecture;
    }

    @Before
    public void initTest() {
        metaLecture = createEntity(em);
    }

    @Test
    @Transactional
    public void createMetaLecture() throws Exception {
        int databaseSizeBeforeCreate = metaLectureRepository.findAll().size();

        // Create the MetaLecture
        MetaLectureDTO metaLectureDTO = metaLectureMapper.toDto(metaLecture);
        restMetaLectureMockMvc.perform(post("/api/meta-lectures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(metaLectureDTO)))
            .andExpect(status().isCreated());

        // Validate the MetaLecture in the database
        List<MetaLecture> metaLectureList = metaLectureRepository.findAll();
        assertThat(metaLectureList).hasSize(databaseSizeBeforeCreate + 1);
        MetaLecture testMetaLecture = metaLectureList.get(metaLectureList.size() - 1);
        assertThat(testMetaLecture.getWeekDay()).isEqualTo(DEFAULT_WEEK_DAY);
        assertThat(testMetaLecture.getStartTime()).isEqualTo(DEFAULT_START_TIME);
        assertThat(testMetaLecture.getEndTime()).isEqualTo(DEFAULT_END_TIME);

        // Validate the MetaLecture in Elasticsearch
        verify(mockMetaLectureSearchRepository, times(1)).save(testMetaLecture);
    }

    @Test
    @Transactional
    public void createMetaLectureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = metaLectureRepository.findAll().size();

        // Create the MetaLecture with an existing ID
        metaLecture.setId(1L);
        MetaLectureDTO metaLectureDTO = metaLectureMapper.toDto(metaLecture);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMetaLectureMockMvc.perform(post("/api/meta-lectures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(metaLectureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MetaLecture in the database
        List<MetaLecture> metaLectureList = metaLectureRepository.findAll();
        assertThat(metaLectureList).hasSize(databaseSizeBeforeCreate);

        // Validate the MetaLecture in Elasticsearch
        verify(mockMetaLectureSearchRepository, times(0)).save(metaLecture);
    }

    @Test
    @Transactional
    public void getAllMetaLectures() throws Exception {
        // Initialize the database
        metaLectureRepository.saveAndFlush(metaLecture);

        // Get all the metaLectureList
        restMetaLectureMockMvc.perform(get("/api/meta-lectures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(metaLecture.getId().intValue())))
            .andExpect(jsonPath("$.[*].weekDay").value(hasItem(DEFAULT_WEEK_DAY.toString())))
            .andExpect(jsonPath("$.[*].startTime").value(hasItem(DEFAULT_START_TIME.toString())))
            .andExpect(jsonPath("$.[*].endTime").value(hasItem(DEFAULT_END_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getMetaLecture() throws Exception {
        // Initialize the database
        metaLectureRepository.saveAndFlush(metaLecture);

        // Get the metaLecture
        restMetaLectureMockMvc.perform(get("/api/meta-lectures/{id}", metaLecture.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(metaLecture.getId().intValue()))
            .andExpect(jsonPath("$.weekDay").value(DEFAULT_WEEK_DAY.toString()))
            .andExpect(jsonPath("$.startTime").value(DEFAULT_START_TIME.toString()))
            .andExpect(jsonPath("$.endTime").value(DEFAULT_END_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMetaLecture() throws Exception {
        // Get the metaLecture
        restMetaLectureMockMvc.perform(get("/api/meta-lectures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMetaLecture() throws Exception {
        // Initialize the database
        metaLectureRepository.saveAndFlush(metaLecture);

        int databaseSizeBeforeUpdate = metaLectureRepository.findAll().size();

        // Update the metaLecture
        MetaLecture updatedMetaLecture = metaLectureRepository.findById(metaLecture.getId()).get();
        // Disconnect from session so that the updates on updatedMetaLecture are not directly saved in db
        em.detach(updatedMetaLecture);
        updatedMetaLecture
            .weekDay(UPDATED_WEEK_DAY)
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME);
        MetaLectureDTO metaLectureDTO = metaLectureMapper.toDto(updatedMetaLecture);

        restMetaLectureMockMvc.perform(put("/api/meta-lectures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(metaLectureDTO)))
            .andExpect(status().isOk());

        // Validate the MetaLecture in the database
        List<MetaLecture> metaLectureList = metaLectureRepository.findAll();
        assertThat(metaLectureList).hasSize(databaseSizeBeforeUpdate);
        MetaLecture testMetaLecture = metaLectureList.get(metaLectureList.size() - 1);
        assertThat(testMetaLecture.getWeekDay()).isEqualTo(UPDATED_WEEK_DAY);
        assertThat(testMetaLecture.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testMetaLecture.getEndTime()).isEqualTo(UPDATED_END_TIME);

        // Validate the MetaLecture in Elasticsearch
        verify(mockMetaLectureSearchRepository, times(1)).save(testMetaLecture);
    }

    @Test
    @Transactional
    public void updateNonExistingMetaLecture() throws Exception {
        int databaseSizeBeforeUpdate = metaLectureRepository.findAll().size();

        // Create the MetaLecture
        MetaLectureDTO metaLectureDTO = metaLectureMapper.toDto(metaLecture);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMetaLectureMockMvc.perform(put("/api/meta-lectures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(metaLectureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MetaLecture in the database
        List<MetaLecture> metaLectureList = metaLectureRepository.findAll();
        assertThat(metaLectureList).hasSize(databaseSizeBeforeUpdate);

        // Validate the MetaLecture in Elasticsearch
        verify(mockMetaLectureSearchRepository, times(0)).save(metaLecture);
    }

    @Test
    @Transactional
    public void deleteMetaLecture() throws Exception {
        // Initialize the database
        metaLectureRepository.saveAndFlush(metaLecture);

        int databaseSizeBeforeDelete = metaLectureRepository.findAll().size();

        // Get the metaLecture
        restMetaLectureMockMvc.perform(delete("/api/meta-lectures/{id}", metaLecture.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MetaLecture> metaLectureList = metaLectureRepository.findAll();
        assertThat(metaLectureList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the MetaLecture in Elasticsearch
        verify(mockMetaLectureSearchRepository, times(1)).deleteById(metaLecture.getId());
    }

    @Test
    @Transactional
    public void searchMetaLecture() throws Exception {
        // Initialize the database
        metaLectureRepository.saveAndFlush(metaLecture);
//        when(mockMetaLectureSearchRepository.search(queryStringQuery("id:" + metaLecture.getId())))
//            .thenReturn(Collections.singletonList(metaLecture));
        // Search the metaLecture
        restMetaLectureMockMvc.perform(get("/api/_search/meta-lectures?query=id:" + metaLecture.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(metaLecture.getId().intValue())))
            .andExpect(jsonPath("$.[*].weekDay").value(hasItem(DEFAULT_WEEK_DAY)))
            .andExpect(jsonPath("$.[*].startTime").value(hasItem(DEFAULT_START_TIME)))
            .andExpect(jsonPath("$.[*].endTime").value(hasItem(DEFAULT_END_TIME)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MetaLecture.class);
        MetaLecture metaLecture1 = new MetaLecture();
        metaLecture1.setId(1L);
        MetaLecture metaLecture2 = new MetaLecture();
        metaLecture2.setId(metaLecture1.getId());
        assertThat(metaLecture1).isEqualTo(metaLecture2);
        metaLecture2.setId(2L);
        assertThat(metaLecture1).isNotEqualTo(metaLecture2);
        metaLecture1.setId(null);
        assertThat(metaLecture1).isNotEqualTo(metaLecture2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MetaLectureDTO.class);
        MetaLectureDTO metaLectureDTO1 = new MetaLectureDTO();
        metaLectureDTO1.setId(1L);
        MetaLectureDTO metaLectureDTO2 = new MetaLectureDTO();
        assertThat(metaLectureDTO1).isNotEqualTo(metaLectureDTO2);
        metaLectureDTO2.setId(metaLectureDTO1.getId());
        assertThat(metaLectureDTO1).isEqualTo(metaLectureDTO2);
        metaLectureDTO2.setId(2L);
        assertThat(metaLectureDTO1).isNotEqualTo(metaLectureDTO2);
        metaLectureDTO1.setId(null);
        assertThat(metaLectureDTO1).isNotEqualTo(metaLectureDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(metaLectureMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(metaLectureMapper.fromId(null)).isNull();
    }
}
