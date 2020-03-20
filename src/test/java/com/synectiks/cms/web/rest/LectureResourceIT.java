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

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import javax.persistence.EntityManager;

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

import com.synectiks.cms.CmsApp;
import com.synectiks.cms.domain.Lecture;
import com.synectiks.cms.repository.LectureRepository;
import com.synectiks.cms.repository.search.LectureSearchRepository;
import com.synectiks.cms.service.LectureService;
import com.synectiks.cms.service.dto.LectureDTO;
import com.synectiks.cms.service.mapper.LectureMapper;
import com.synectiks.cms.web.rest.errors.ExceptionTranslator;

/**
 * Integration tests for the {@Link LectureResource} REST controller.
 */
@SpringBootTest(classes = CmsApp.class)
public class LectureResourceIT {

    private static final LocalDate DEFAULT_LEC_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LEC_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_LAST_UPDATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LAST_UPDATED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_LAST_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_UPDATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_START_TIME = "AAAAAAAAAA";
    private static final String UPDATED_START_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_END_TIME = "AAAAAAAAAA";
    private static final String UPDATED_END_TIME = "BBBBBBBBBB";

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private LectureMapper lectureMapper;

    @Autowired
    private LectureService lectureService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.LectureSearchRepositoryMockConfiguration
     */
    @Autowired
    private LectureSearchRepository mockLectureSearchRepository;

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

    private MockMvc restLectureMockMvc;

    private Lecture lecture;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LectureResource lectureResource = new LectureResource(lectureService);
        this.restLectureMockMvc = MockMvcBuilders.standaloneSetup(lectureResource)
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
    public static Lecture createEntity(EntityManager em) {
        Lecture lecture = new Lecture()
            .lecDate(DEFAULT_LEC_DATE)
            .lastUpdatedOn(DEFAULT_LAST_UPDATED_ON)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .startTime(DEFAULT_START_TIME)
            .endTime(DEFAULT_END_TIME);
        return lecture;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Lecture createUpdatedEntity(EntityManager em) {
        Lecture lecture = new Lecture()
            .lecDate(UPDATED_LEC_DATE)
            .lastUpdatedOn(UPDATED_LAST_UPDATED_ON)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME);
        return lecture;
    }

    @BeforeEach
    public void initTest() {
        lecture = createEntity(em);
    }

    @Test
    @Transactional
    public void createLecture() throws Exception {
        int databaseSizeBeforeCreate = lectureRepository.findAll().size();

        // Create the Lecture
        LectureDTO lectureDTO = lectureMapper.toDto(lecture);
        restLectureMockMvc.perform(post("/api/lectures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lectureDTO)))
            .andExpect(status().isCreated());

        // Validate the Lecture in the database
        List<Lecture> lectureList = lectureRepository.findAll();
        assertThat(lectureList).hasSize(databaseSizeBeforeCreate + 1);
        Lecture testLecture = lectureList.get(lectureList.size() - 1);
        assertThat(testLecture.getLecDate()).isEqualTo(DEFAULT_LEC_DATE);
        assertThat(testLecture.getLastUpdatedOn()).isEqualTo(DEFAULT_LAST_UPDATED_ON);
        assertThat(testLecture.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testLecture.getStartTime()).isEqualTo(DEFAULT_START_TIME);
        assertThat(testLecture.getEndTime()).isEqualTo(DEFAULT_END_TIME);

        // Validate the Lecture in Elasticsearch
        verify(mockLectureSearchRepository, times(1)).save(testLecture);
    }

    @Test
    @Transactional
    public void createLectureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = lectureRepository.findAll().size();

        // Create the Lecture with an existing ID
        lecture.setId(1L);
        LectureDTO lectureDTO = lectureMapper.toDto(lecture);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLectureMockMvc.perform(post("/api/lectures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lectureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Lecture in the database
        List<Lecture> lectureList = lectureRepository.findAll();
        assertThat(lectureList).hasSize(databaseSizeBeforeCreate);

        // Validate the Lecture in Elasticsearch
        verify(mockLectureSearchRepository, times(0)).save(lecture);
    }


    @Test
    @Transactional
    public void checkLecDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = lectureRepository.findAll().size();
        // set the field null
        lecture.setLecDate(null);

        // Create the Lecture, which fails.
        LectureDTO lectureDTO = lectureMapper.toDto(lecture);

        restLectureMockMvc.perform(post("/api/lectures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lectureDTO)))
            .andExpect(status().isBadRequest());

        List<Lecture> lectureList = lectureRepository.findAll();
        assertThat(lectureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastUpdatedOnIsRequired() throws Exception {
        int databaseSizeBeforeTest = lectureRepository.findAll().size();
        // set the field null
        lecture.setLastUpdatedOn(null);

        // Create the Lecture, which fails.
        LectureDTO lectureDTO = lectureMapper.toDto(lecture);

        restLectureMockMvc.perform(post("/api/lectures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lectureDTO)))
            .andExpect(status().isBadRequest());

        List<Lecture> lectureList = lectureRepository.findAll();
        assertThat(lectureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = lectureRepository.findAll().size();
        // set the field null
        lecture.setLastUpdatedBy(null);

        // Create the Lecture, which fails.
        LectureDTO lectureDTO = lectureMapper.toDto(lecture);

        restLectureMockMvc.perform(post("/api/lectures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lectureDTO)))
            .andExpect(status().isBadRequest());

        List<Lecture> lectureList = lectureRepository.findAll();
        assertThat(lectureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = lectureRepository.findAll().size();
        // set the field null
        lecture.setStartTime(null);

        // Create the Lecture, which fails.
        LectureDTO lectureDTO = lectureMapper.toDto(lecture);

        restLectureMockMvc.perform(post("/api/lectures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lectureDTO)))
            .andExpect(status().isBadRequest());

        List<Lecture> lectureList = lectureRepository.findAll();
        assertThat(lectureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = lectureRepository.findAll().size();
        // set the field null
        lecture.setEndTime(null);

        // Create the Lecture, which fails.
        LectureDTO lectureDTO = lectureMapper.toDto(lecture);

        restLectureMockMvc.perform(post("/api/lectures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lectureDTO)))
            .andExpect(status().isBadRequest());

        List<Lecture> lectureList = lectureRepository.findAll();
        assertThat(lectureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLectures() throws Exception {
        // Initialize the database
        lectureRepository.saveAndFlush(lecture);

        // Get all the lectureList
        restLectureMockMvc.perform(get("/api/lectures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lecture.getId().intValue())))
            .andExpect(jsonPath("$.[*].lecDate").value(hasItem(DEFAULT_LEC_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedOn").value(hasItem(DEFAULT_LAST_UPDATED_ON.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())))
            .andExpect(jsonPath("$.[*].startTime").value(hasItem(DEFAULT_START_TIME.toString())))
            .andExpect(jsonPath("$.[*].endTime").value(hasItem(DEFAULT_END_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getLecture() throws Exception {
        // Initialize the database
        lectureRepository.saveAndFlush(lecture);

        // Get the lecture
        restLectureMockMvc.perform(get("/api/lectures/{id}", lecture.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(lecture.getId().intValue()))
            .andExpect(jsonPath("$.lecDate").value(DEFAULT_LEC_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedOn").value(DEFAULT_LAST_UPDATED_ON.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()))
            .andExpect(jsonPath("$.startTime").value(DEFAULT_START_TIME.toString()))
            .andExpect(jsonPath("$.endTime").value(DEFAULT_END_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLecture() throws Exception {
        // Get the lecture
        restLectureMockMvc.perform(get("/api/lectures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLecture() throws Exception {
        // Initialize the database
        lectureRepository.saveAndFlush(lecture);

        int databaseSizeBeforeUpdate = lectureRepository.findAll().size();

        // Update the lecture
        Lecture updatedLecture = lectureRepository.findById(lecture.getId()).get();
        // Disconnect from session so that the updates on updatedLecture are not directly saved in db
        em.detach(updatedLecture);
        updatedLecture
            .lecDate(UPDATED_LEC_DATE)
            .lastUpdatedOn(UPDATED_LAST_UPDATED_ON)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME);
        LectureDTO lectureDTO = lectureMapper.toDto(updatedLecture);

        restLectureMockMvc.perform(put("/api/lectures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lectureDTO)))
            .andExpect(status().isOk());

        // Validate the Lecture in the database
        List<Lecture> lectureList = lectureRepository.findAll();
        assertThat(lectureList).hasSize(databaseSizeBeforeUpdate);
        Lecture testLecture = lectureList.get(lectureList.size() - 1);
        assertThat(testLecture.getLecDate()).isEqualTo(UPDATED_LEC_DATE);
        assertThat(testLecture.getLastUpdatedOn()).isEqualTo(UPDATED_LAST_UPDATED_ON);
        assertThat(testLecture.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testLecture.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testLecture.getEndTime()).isEqualTo(UPDATED_END_TIME);

        // Validate the Lecture in Elasticsearch
        verify(mockLectureSearchRepository, times(1)).save(testLecture);
    }

    @Test
    @Transactional
    public void updateNonExistingLecture() throws Exception {
        int databaseSizeBeforeUpdate = lectureRepository.findAll().size();

        // Create the Lecture
        LectureDTO lectureDTO = lectureMapper.toDto(lecture);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLectureMockMvc.perform(put("/api/lectures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lectureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Lecture in the database
        List<Lecture> lectureList = lectureRepository.findAll();
        assertThat(lectureList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Lecture in Elasticsearch
        verify(mockLectureSearchRepository, times(0)).save(lecture);
    }

    @Test
    @Transactional
    public void deleteLecture() throws Exception {
        // Initialize the database
        lectureRepository.saveAndFlush(lecture);

        int databaseSizeBeforeDelete = lectureRepository.findAll().size();

        // Delete the lecture
        restLectureMockMvc.perform(delete("/api/lectures/{id}", lecture.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Lecture> lectureList = lectureRepository.findAll();
        assertThat(lectureList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Lecture in Elasticsearch
        verify(mockLectureSearchRepository, times(1)).deleteById(lecture.getId());
    }

    @Test
    @Transactional
    public void searchLecture() throws Exception {
        // Initialize the database
        lectureRepository.saveAndFlush(lecture);
//        when(mockLectureSearchRepository.search(queryStringQuery("id:" + lecture.getId())))
//            .thenReturn(Collections.singletonList(lecture));
        // Search the lecture
        restLectureMockMvc.perform(get("/api/_search/lectures?query=id:" + lecture.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lecture.getId().intValue())))
            .andExpect(jsonPath("$.[*].lecDate").value(hasItem(DEFAULT_LEC_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedOn").value(hasItem(DEFAULT_LAST_UPDATED_ON.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].startTime").value(hasItem(DEFAULT_START_TIME)))
            .andExpect(jsonPath("$.[*].endTime").value(hasItem(DEFAULT_END_TIME)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Lecture.class);
        Lecture lecture1 = new Lecture();
        lecture1.setId(1L);
        Lecture lecture2 = new Lecture();
        lecture2.setId(lecture1.getId());
        assertThat(lecture1).isEqualTo(lecture2);
        lecture2.setId(2L);
        assertThat(lecture1).isNotEqualTo(lecture2);
        lecture1.setId(null);
        assertThat(lecture1).isNotEqualTo(lecture2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LectureDTO.class);
        LectureDTO lectureDTO1 = new LectureDTO();
        lectureDTO1.setId(1L);
        LectureDTO lectureDTO2 = new LectureDTO();
        assertThat(lectureDTO1).isNotEqualTo(lectureDTO2);
        lectureDTO2.setId(lectureDTO1.getId());
        assertThat(lectureDTO1).isEqualTo(lectureDTO2);
        lectureDTO2.setId(2L);
        assertThat(lectureDTO1).isNotEqualTo(lectureDTO2);
        lectureDTO1.setId(null);
        assertThat(lectureDTO1).isNotEqualTo(lectureDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(lectureMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(lectureMapper.fromId(null)).isNull();
    }
}
