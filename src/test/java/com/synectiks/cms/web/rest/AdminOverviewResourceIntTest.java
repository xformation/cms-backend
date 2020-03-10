package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.AdminOverview;
import com.synectiks.cms.repository.AdminOverviewRepository;
import com.synectiks.cms.repository.search.AdminOverviewSearchRepository;
import com.synectiks.cms.service.AdminOverviewService;
import com.synectiks.cms.service.dto.AdminOverviewDTO;
import com.synectiks.cms.service.mapper.AdminOverviewMapper;
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

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;


import static com.synectiks.cms.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.synectiks.cms.domain.enumeration.SectionEnum;
import com.synectiks.cms.domain.enumeration.LectureAdminEnum;
import com.synectiks.cms.domain.enumeration.LectureAdminEnum;
import com.synectiks.cms.domain.enumeration.LectureAdminEnum;
import com.synectiks.cms.domain.enumeration.LectureAdminEnum;
import com.synectiks.cms.domain.enumeration.LectureAdminEnum;
import com.synectiks.cms.domain.enumeration.LectureAdminEnum;
import com.synectiks.cms.domain.enumeration.LectureAdminEnum;
import com.synectiks.cms.domain.enumeration.LectureAdminEnum;
/**
 * Test class for the AdminOverviewResource REST controller.
 *
 * @see AdminOverviewResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class AdminOverviewResourceIntTest {

    private static final LocalDate DEFAULT_CHOOSE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CHOOSE_DATE =  LocalDate.now(ZoneId.systemDefault());

    private static final SectionEnum DEFAULT_SECTION = SectionEnum.A;
    private static final SectionEnum UPDATED_SECTION = SectionEnum.B;

    private static final LectureAdminEnum DEFAULT_LECTURE_ONE = LectureAdminEnum.MARKED;
    private static final LectureAdminEnum UPDATED_LECTURE_ONE = LectureAdminEnum.UNMARKED;

    private static final LectureAdminEnum DEFAULT_LECTURE_TWO = LectureAdminEnum.MARKED;
    private static final LectureAdminEnum UPDATED_LECTURE_TWO = LectureAdminEnum.UNMARKED;

    private static final LectureAdminEnum DEFAULT_LECTURE_THREE = LectureAdminEnum.MARKED;
    private static final LectureAdminEnum UPDATED_LECTURE_THREE = LectureAdminEnum.UNMARKED;

    private static final LectureAdminEnum DEFAULT_LECTURE_FOUR = LectureAdminEnum.MARKED;
    private static final LectureAdminEnum UPDATED_LECTURE_FOUR = LectureAdminEnum.UNMARKED;

    private static final LectureAdminEnum DEFAULT_LECTURE_FIVE = LectureAdminEnum.MARKED;
    private static final LectureAdminEnum UPDATED_LECTURE_FIVE = LectureAdminEnum.UNMARKED;

    private static final LectureAdminEnum DEFAULT_LECTURE_SIX = LectureAdminEnum.MARKED;
    private static final LectureAdminEnum UPDATED_LECTURE_SIX = LectureAdminEnum.UNMARKED;

    private static final LectureAdminEnum DEFAULT_LECTURE_SEVEN = LectureAdminEnum.MARKED;
    private static final LectureAdminEnum UPDATED_LECTURE_SEVEN = LectureAdminEnum.UNMARKED;

    private static final LectureAdminEnum DEFAULT_LECTURE_EIGHT = LectureAdminEnum.MARKED;
    private static final LectureAdminEnum UPDATED_LECTURE_EIGHT = LectureAdminEnum.UNMARKED;

    @Autowired
    private AdminOverviewRepository adminOverviewRepository;


    @Autowired
    private AdminOverviewMapper adminOverviewMapper;


    @Autowired
    private AdminOverviewService adminOverviewService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.AdminOverviewSearchRepositoryMockConfiguration
     */
    @Autowired
    private AdminOverviewSearchRepository mockAdminOverviewSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAdminOverviewMockMvc;

    private AdminOverview adminOverview;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdminOverviewResource adminOverviewResource = new AdminOverviewResource(adminOverviewService);
        this.restAdminOverviewMockMvc = MockMvcBuilders.standaloneSetup(adminOverviewResource)
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
    public static AdminOverview createEntity(EntityManager em) {
        AdminOverview adminOverview = new AdminOverview()
            .chooseDate(DEFAULT_CHOOSE_DATE)
            .section(DEFAULT_SECTION)
            .lectureOne(DEFAULT_LECTURE_ONE)
            .lectureTwo(DEFAULT_LECTURE_TWO)
            .lectureThree(DEFAULT_LECTURE_THREE)
            .lectureFour(DEFAULT_LECTURE_FOUR)
            .lectureFive(DEFAULT_LECTURE_FIVE)
            .lectureSix(DEFAULT_LECTURE_SIX)
            .lectureSeven(DEFAULT_LECTURE_SEVEN)
            .lectureEight(DEFAULT_LECTURE_EIGHT);
        return adminOverview;
    }

    @Before
    public void initTest() {
        adminOverview = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdminOverview() throws Exception {
        int databaseSizeBeforeCreate = adminOverviewRepository.findAll().size();

        // Create the AdminOverview
        AdminOverviewDTO adminOverviewDTO = adminOverviewMapper.toDto(adminOverview);
        restAdminOverviewMockMvc.perform(post("/api/admin-overviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adminOverviewDTO)))
            .andExpect(status().isCreated());

        // Validate the AdminOverview in the database
        List<AdminOverview> adminOverviewList = adminOverviewRepository.findAll();
        assertThat(adminOverviewList).hasSize(databaseSizeBeforeCreate + 1);
        AdminOverview testAdminOverview = adminOverviewList.get(adminOverviewList.size() - 1);
        assertThat(testAdminOverview.getChooseDate()).isEqualTo(DEFAULT_CHOOSE_DATE);
        assertThat(testAdminOverview.getSection()).isEqualTo(DEFAULT_SECTION);
        assertThat(testAdminOverview.getLectureOne()).isEqualTo(DEFAULT_LECTURE_ONE);
        assertThat(testAdminOverview.getLectureTwo()).isEqualTo(DEFAULT_LECTURE_TWO);
        assertThat(testAdminOverview.getLectureThree()).isEqualTo(DEFAULT_LECTURE_THREE);
        assertThat(testAdminOverview.getLectureFour()).isEqualTo(DEFAULT_LECTURE_FOUR);
        assertThat(testAdminOverview.getLectureFive()).isEqualTo(DEFAULT_LECTURE_FIVE);
        assertThat(testAdminOverview.getLectureSix()).isEqualTo(DEFAULT_LECTURE_SIX);
        assertThat(testAdminOverview.getLectureSeven()).isEqualTo(DEFAULT_LECTURE_SEVEN);
        assertThat(testAdminOverview.getLectureEight()).isEqualTo(DEFAULT_LECTURE_EIGHT);

        // Validate the AdminOverview in Elasticsearch
        verify(mockAdminOverviewSearchRepository, times(1)).save(testAdminOverview);
    }

    @Test
    @Transactional
    public void createAdminOverviewWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adminOverviewRepository.findAll().size();

        // Create the AdminOverview with an existing ID
        adminOverview.setId(1L);
        AdminOverviewDTO adminOverviewDTO = adminOverviewMapper.toDto(adminOverview);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdminOverviewMockMvc.perform(post("/api/admin-overviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adminOverviewDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdminOverview in the database
        List<AdminOverview> adminOverviewList = adminOverviewRepository.findAll();
        assertThat(adminOverviewList).hasSize(databaseSizeBeforeCreate);

        // Validate the AdminOverview in Elasticsearch
        verify(mockAdminOverviewSearchRepository, times(0)).save(adminOverview);
    }

    @Test
    @Transactional
    public void checkChooseDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = adminOverviewRepository.findAll().size();
        // set the field null
        adminOverview.setChooseDate(null);

        // Create the AdminOverview, which fails.
        AdminOverviewDTO adminOverviewDTO = adminOverviewMapper.toDto(adminOverview);

        restAdminOverviewMockMvc.perform(post("/api/admin-overviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adminOverviewDTO)))
            .andExpect(status().isBadRequest());

        List<AdminOverview> adminOverviewList = adminOverviewRepository.findAll();
        assertThat(adminOverviewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSectionIsRequired() throws Exception {
        int databaseSizeBeforeTest = adminOverviewRepository.findAll().size();
        // set the field null
        adminOverview.setSection(null);

        // Create the AdminOverview, which fails.
        AdminOverviewDTO adminOverviewDTO = adminOverviewMapper.toDto(adminOverview);

        restAdminOverviewMockMvc.perform(post("/api/admin-overviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adminOverviewDTO)))
            .andExpect(status().isBadRequest());

        List<AdminOverview> adminOverviewList = adminOverviewRepository.findAll();
        assertThat(adminOverviewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLectureOneIsRequired() throws Exception {
        int databaseSizeBeforeTest = adminOverviewRepository.findAll().size();
        // set the field null
        adminOverview.setLectureOne(null);

        // Create the AdminOverview, which fails.
        AdminOverviewDTO adminOverviewDTO = adminOverviewMapper.toDto(adminOverview);

        restAdminOverviewMockMvc.perform(post("/api/admin-overviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adminOverviewDTO)))
            .andExpect(status().isBadRequest());

        List<AdminOverview> adminOverviewList = adminOverviewRepository.findAll();
        assertThat(adminOverviewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLectureTwoIsRequired() throws Exception {
        int databaseSizeBeforeTest = adminOverviewRepository.findAll().size();
        // set the field null
        adminOverview.setLectureTwo(null);

        // Create the AdminOverview, which fails.
        AdminOverviewDTO adminOverviewDTO = adminOverviewMapper.toDto(adminOverview);

        restAdminOverviewMockMvc.perform(post("/api/admin-overviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adminOverviewDTO)))
            .andExpect(status().isBadRequest());

        List<AdminOverview> adminOverviewList = adminOverviewRepository.findAll();
        assertThat(adminOverviewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLectureThreeIsRequired() throws Exception {
        int databaseSizeBeforeTest = adminOverviewRepository.findAll().size();
        // set the field null
        adminOverview.setLectureThree(null);

        // Create the AdminOverview, which fails.
        AdminOverviewDTO adminOverviewDTO = adminOverviewMapper.toDto(adminOverview);

        restAdminOverviewMockMvc.perform(post("/api/admin-overviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adminOverviewDTO)))
            .andExpect(status().isBadRequest());

        List<AdminOverview> adminOverviewList = adminOverviewRepository.findAll();
        assertThat(adminOverviewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLectureFourIsRequired() throws Exception {
        int databaseSizeBeforeTest = adminOverviewRepository.findAll().size();
        // set the field null
        adminOverview.setLectureFour(null);

        // Create the AdminOverview, which fails.
        AdminOverviewDTO adminOverviewDTO = adminOverviewMapper.toDto(adminOverview);

        restAdminOverviewMockMvc.perform(post("/api/admin-overviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adminOverviewDTO)))
            .andExpect(status().isBadRequest());

        List<AdminOverview> adminOverviewList = adminOverviewRepository.findAll();
        assertThat(adminOverviewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLectureFiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = adminOverviewRepository.findAll().size();
        // set the field null
        adminOverview.setLectureFive(null);

        // Create the AdminOverview, which fails.
        AdminOverviewDTO adminOverviewDTO = adminOverviewMapper.toDto(adminOverview);

        restAdminOverviewMockMvc.perform(post("/api/admin-overviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adminOverviewDTO)))
            .andExpect(status().isBadRequest());

        List<AdminOverview> adminOverviewList = adminOverviewRepository.findAll();
        assertThat(adminOverviewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLectureSixIsRequired() throws Exception {
        int databaseSizeBeforeTest = adminOverviewRepository.findAll().size();
        // set the field null
        adminOverview.setLectureSix(null);

        // Create the AdminOverview, which fails.
        AdminOverviewDTO adminOverviewDTO = adminOverviewMapper.toDto(adminOverview);

        restAdminOverviewMockMvc.perform(post("/api/admin-overviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adminOverviewDTO)))
            .andExpect(status().isBadRequest());

        List<AdminOverview> adminOverviewList = adminOverviewRepository.findAll();
        assertThat(adminOverviewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLectureSevenIsRequired() throws Exception {
        int databaseSizeBeforeTest = adminOverviewRepository.findAll().size();
        // set the field null
        adminOverview.setLectureSeven(null);

        // Create the AdminOverview, which fails.
        AdminOverviewDTO adminOverviewDTO = adminOverviewMapper.toDto(adminOverview);

        restAdminOverviewMockMvc.perform(post("/api/admin-overviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adminOverviewDTO)))
            .andExpect(status().isBadRequest());

        List<AdminOverview> adminOverviewList = adminOverviewRepository.findAll();
        assertThat(adminOverviewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLectureEightIsRequired() throws Exception {
        int databaseSizeBeforeTest = adminOverviewRepository.findAll().size();
        // set the field null
        adminOverview.setLectureEight(null);

        // Create the AdminOverview, which fails.
        AdminOverviewDTO adminOverviewDTO = adminOverviewMapper.toDto(adminOverview);

        restAdminOverviewMockMvc.perform(post("/api/admin-overviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adminOverviewDTO)))
            .andExpect(status().isBadRequest());

        List<AdminOverview> adminOverviewList = adminOverviewRepository.findAll();
        assertThat(adminOverviewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAdminOverviews() throws Exception {
        // Initialize the database
        adminOverviewRepository.saveAndFlush(adminOverview);

        // Get all the adminOverviewList
        restAdminOverviewMockMvc.perform(get("/api/admin-overviews?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adminOverview.getId().intValue())))
            .andExpect(jsonPath("$.[*].chooseDate").value(hasItem(DEFAULT_CHOOSE_DATE.toString())))
            .andExpect(jsonPath("$.[*].section").value(hasItem(DEFAULT_SECTION.toString())))
            .andExpect(jsonPath("$.[*].lectureOne").value(hasItem(DEFAULT_LECTURE_ONE.toString())))
            .andExpect(jsonPath("$.[*].lectureTwo").value(hasItem(DEFAULT_LECTURE_TWO.toString())))
            .andExpect(jsonPath("$.[*].lectureThree").value(hasItem(DEFAULT_LECTURE_THREE.toString())))
            .andExpect(jsonPath("$.[*].lectureFour").value(hasItem(DEFAULT_LECTURE_FOUR.toString())))
            .andExpect(jsonPath("$.[*].lectureFive").value(hasItem(DEFAULT_LECTURE_FIVE.toString())))
            .andExpect(jsonPath("$.[*].lectureSix").value(hasItem(DEFAULT_LECTURE_SIX.toString())))
            .andExpect(jsonPath("$.[*].lectureSeven").value(hasItem(DEFAULT_LECTURE_SEVEN.toString())))
            .andExpect(jsonPath("$.[*].lectureEight").value(hasItem(DEFAULT_LECTURE_EIGHT.toString())));
    }


    @Test
    @Transactional
    public void getAdminOverview() throws Exception {
        // Initialize the database
        adminOverviewRepository.saveAndFlush(adminOverview);

        // Get the adminOverview
        restAdminOverviewMockMvc.perform(get("/api/admin-overviews/{id}", adminOverview.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(adminOverview.getId().intValue()))
            .andExpect(jsonPath("$.chooseDate").value(DEFAULT_CHOOSE_DATE.toString()))
            .andExpect(jsonPath("$.section").value(DEFAULT_SECTION.toString()))
            .andExpect(jsonPath("$.lectureOne").value(DEFAULT_LECTURE_ONE.toString()))
            .andExpect(jsonPath("$.lectureTwo").value(DEFAULT_LECTURE_TWO.toString()))
            .andExpect(jsonPath("$.lectureThree").value(DEFAULT_LECTURE_THREE.toString()))
            .andExpect(jsonPath("$.lectureFour").value(DEFAULT_LECTURE_FOUR.toString()))
            .andExpect(jsonPath("$.lectureFive").value(DEFAULT_LECTURE_FIVE.toString()))
            .andExpect(jsonPath("$.lectureSix").value(DEFAULT_LECTURE_SIX.toString()))
            .andExpect(jsonPath("$.lectureSeven").value(DEFAULT_LECTURE_SEVEN.toString()))
            .andExpect(jsonPath("$.lectureEight").value(DEFAULT_LECTURE_EIGHT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingAdminOverview() throws Exception {
        // Get the adminOverview
        restAdminOverviewMockMvc.perform(get("/api/admin-overviews/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdminOverview() throws Exception {
        // Initialize the database
        adminOverviewRepository.saveAndFlush(adminOverview);

        int databaseSizeBeforeUpdate = adminOverviewRepository.findAll().size();

        // Update the adminOverview
        AdminOverview updatedAdminOverview = adminOverviewRepository.findById(adminOverview.getId()).get();
        // Disconnect from session so that the updates on updatedAdminOverview are not directly saved in db
        em.detach(updatedAdminOverview);
        updatedAdminOverview
            .chooseDate(UPDATED_CHOOSE_DATE)
            .section(UPDATED_SECTION)
            .lectureOne(UPDATED_LECTURE_ONE)
            .lectureTwo(UPDATED_LECTURE_TWO)
            .lectureThree(UPDATED_LECTURE_THREE)
            .lectureFour(UPDATED_LECTURE_FOUR)
            .lectureFive(UPDATED_LECTURE_FIVE)
            .lectureSix(UPDATED_LECTURE_SIX)
            .lectureSeven(UPDATED_LECTURE_SEVEN)
            .lectureEight(UPDATED_LECTURE_EIGHT);
        AdminOverviewDTO adminOverviewDTO = adminOverviewMapper.toDto(updatedAdminOverview);

        restAdminOverviewMockMvc.perform(put("/api/admin-overviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adminOverviewDTO)))
            .andExpect(status().isOk());

        // Validate the AdminOverview in the database
        List<AdminOverview> adminOverviewList = adminOverviewRepository.findAll();
        assertThat(adminOverviewList).hasSize(databaseSizeBeforeUpdate);
        AdminOverview testAdminOverview = adminOverviewList.get(adminOverviewList.size() - 1);
        assertThat(testAdminOverview.getChooseDate()).isEqualTo(UPDATED_CHOOSE_DATE);
        assertThat(testAdminOverview.getSection()).isEqualTo(UPDATED_SECTION);
        assertThat(testAdminOverview.getLectureOne()).isEqualTo(UPDATED_LECTURE_ONE);
        assertThat(testAdminOverview.getLectureTwo()).isEqualTo(UPDATED_LECTURE_TWO);
        assertThat(testAdminOverview.getLectureThree()).isEqualTo(UPDATED_LECTURE_THREE);
        assertThat(testAdminOverview.getLectureFour()).isEqualTo(UPDATED_LECTURE_FOUR);
        assertThat(testAdminOverview.getLectureFive()).isEqualTo(UPDATED_LECTURE_FIVE);
        assertThat(testAdminOverview.getLectureSix()).isEqualTo(UPDATED_LECTURE_SIX);
        assertThat(testAdminOverview.getLectureSeven()).isEqualTo(UPDATED_LECTURE_SEVEN);
        assertThat(testAdminOverview.getLectureEight()).isEqualTo(UPDATED_LECTURE_EIGHT);

        // Validate the AdminOverview in Elasticsearch
        verify(mockAdminOverviewSearchRepository, times(1)).save(testAdminOverview);
    }

    @Test
    @Transactional
    public void updateNonExistingAdminOverview() throws Exception {
        int databaseSizeBeforeUpdate = adminOverviewRepository.findAll().size();

        // Create the AdminOverview
        AdminOverviewDTO adminOverviewDTO = adminOverviewMapper.toDto(adminOverview);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAdminOverviewMockMvc.perform(put("/api/admin-overviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adminOverviewDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdminOverview in the database
        List<AdminOverview> adminOverviewList = adminOverviewRepository.findAll();
        assertThat(adminOverviewList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AdminOverview in Elasticsearch
        verify(mockAdminOverviewSearchRepository, times(0)).save(adminOverview);
    }

    @Test
    @Transactional
    public void deleteAdminOverview() throws Exception {
        // Initialize the database
        adminOverviewRepository.saveAndFlush(adminOverview);

        int databaseSizeBeforeDelete = adminOverviewRepository.findAll().size();

        // Get the adminOverview
        restAdminOverviewMockMvc.perform(delete("/api/admin-overviews/{id}", adminOverview.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AdminOverview> adminOverviewList = adminOverviewRepository.findAll();
        assertThat(adminOverviewList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AdminOverview in Elasticsearch
        verify(mockAdminOverviewSearchRepository, times(1)).deleteById(adminOverview.getId());
    }

    @Test
    @Transactional
    public void searchAdminOverview() throws Exception {
        // Initialize the database
        adminOverviewRepository.saveAndFlush(adminOverview);
//        when(mockAdminOverviewSearchRepository.search(queryStringQuery("id:" + adminOverview.getId())))
//            .thenReturn(Collections.singletonList(adminOverview));
        // Search the adminOverview
        restAdminOverviewMockMvc.perform(get("/api/_search/admin-overviews?query=id:" + adminOverview.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adminOverview.getId().intValue())))
            .andExpect(jsonPath("$.[*].chooseDate").value(hasItem(DEFAULT_CHOOSE_DATE.toString())))
            .andExpect(jsonPath("$.[*].section").value(hasItem(DEFAULT_SECTION.toString())))
            .andExpect(jsonPath("$.[*].lectureOne").value(hasItem(DEFAULT_LECTURE_ONE.toString())))
            .andExpect(jsonPath("$.[*].lectureTwo").value(hasItem(DEFAULT_LECTURE_TWO.toString())))
            .andExpect(jsonPath("$.[*].lectureThree").value(hasItem(DEFAULT_LECTURE_THREE.toString())))
            .andExpect(jsonPath("$.[*].lectureFour").value(hasItem(DEFAULT_LECTURE_FOUR.toString())))
            .andExpect(jsonPath("$.[*].lectureFive").value(hasItem(DEFAULT_LECTURE_FIVE.toString())))
            .andExpect(jsonPath("$.[*].lectureSix").value(hasItem(DEFAULT_LECTURE_SIX.toString())))
            .andExpect(jsonPath("$.[*].lectureSeven").value(hasItem(DEFAULT_LECTURE_SEVEN.toString())))
            .andExpect(jsonPath("$.[*].lectureEight").value(hasItem(DEFAULT_LECTURE_EIGHT.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdminOverview.class);
        AdminOverview adminOverview1 = new AdminOverview();
        adminOverview1.setId(1L);
        AdminOverview adminOverview2 = new AdminOverview();
        adminOverview2.setId(adminOverview1.getId());
        assertThat(adminOverview1).isEqualTo(adminOverview2);
        adminOverview2.setId(2L);
        assertThat(adminOverview1).isNotEqualTo(adminOverview2);
        adminOverview1.setId(null);
        assertThat(adminOverview1).isNotEqualTo(adminOverview2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdminOverviewDTO.class);
        AdminOverviewDTO adminOverviewDTO1 = new AdminOverviewDTO();
        adminOverviewDTO1.setId(1L);
        AdminOverviewDTO adminOverviewDTO2 = new AdminOverviewDTO();
        assertThat(adminOverviewDTO1).isNotEqualTo(adminOverviewDTO2);
        adminOverviewDTO2.setId(adminOverviewDTO1.getId());
        assertThat(adminOverviewDTO1).isEqualTo(adminOverviewDTO2);
        adminOverviewDTO2.setId(2L);
        assertThat(adminOverviewDTO1).isNotEqualTo(adminOverviewDTO2);
        adminOverviewDTO1.setId(null);
        assertThat(adminOverviewDTO1).isNotEqualTo(adminOverviewDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(adminOverviewMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(adminOverviewMapper.fromId(null)).isNull();
    }
}
