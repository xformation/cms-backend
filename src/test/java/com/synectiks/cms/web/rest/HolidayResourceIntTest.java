package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.Holiday;
import com.synectiks.cms.repository.HolidayRepository;
import com.synectiks.cms.repository.search.HolidaySearchRepository;
import com.synectiks.cms.service.HolidayService;
import com.synectiks.cms.service.dto.HolidayDTO;
import com.synectiks.cms.service.mapper.HolidayMapper;
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
import java.util.List;


import static com.synectiks.cms.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.synectiks.cms.domain.enumeration.Status;
/**
 * Test class for the HolidayResource REST controller.
 *
 * @see HolidayResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class HolidayResourceIntTest {

    private static final String DEFAULT_HOLIDAY_DESC = "AAAAAAAAAA";
    private static final String UPDATED_HOLIDAY_DESC = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_HOLIDAY_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_HOLIDAY_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Status DEFAULT_HOLIDAY_STATUS = Status.ACTIVE;
    private static final Status UPDATED_HOLIDAY_STATUS = Status.DEACTIVE;

    @Autowired
    private HolidayRepository holidayRepository;


    @Autowired
    private HolidayMapper holidayMapper;
    

    @Autowired
    private HolidayService holidayService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.HolidaySearchRepositoryMockConfiguration
     */
    @Autowired
    private HolidaySearchRepository mockHolidaySearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restHolidayMockMvc;

    private Holiday holiday;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HolidayResource holidayResource = new HolidayResource(holidayService);
        this.restHolidayMockMvc = MockMvcBuilders.standaloneSetup(holidayResource)
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
    public static Holiday createEntity(EntityManager em) {
        Holiday holiday = new Holiday()
            .holidayDesc(DEFAULT_HOLIDAY_DESC)
            .holidayDate(DEFAULT_HOLIDAY_DATE)
            .holidayStatus(DEFAULT_HOLIDAY_STATUS);
        return holiday;
    }

    @Before
    public void initTest() {
        holiday = createEntity(em);
    }

    @Test
    @Transactional
    public void createHoliday() throws Exception {
        int databaseSizeBeforeCreate = holidayRepository.findAll().size();

        // Create the Holiday
        HolidayDTO holidayDTO = holidayMapper.toDto(holiday);
        restHolidayMockMvc.perform(post("/api/holidays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(holidayDTO)))
            .andExpect(status().isCreated());

        // Validate the Holiday in the database
        List<Holiday> holidayList = holidayRepository.findAll();
        assertThat(holidayList).hasSize(databaseSizeBeforeCreate + 1);
        Holiday testHoliday = holidayList.get(holidayList.size() - 1);
        assertThat(testHoliday.getHolidayDesc()).isEqualTo(DEFAULT_HOLIDAY_DESC);
        assertThat(testHoliday.getHolidayDate()).isEqualTo(DEFAULT_HOLIDAY_DATE);
        assertThat(testHoliday.getHolidayStatus()).isEqualTo(DEFAULT_HOLIDAY_STATUS);

        // Validate the Holiday in Elasticsearch
        verify(mockHolidaySearchRepository, times(1)).save(testHoliday);
    }

    @Test
    @Transactional
    public void createHolidayWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = holidayRepository.findAll().size();

        // Create the Holiday with an existing ID
        holiday.setId(1L);
        HolidayDTO holidayDTO = holidayMapper.toDto(holiday);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHolidayMockMvc.perform(post("/api/holidays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(holidayDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Holiday in the database
        List<Holiday> holidayList = holidayRepository.findAll();
        assertThat(holidayList).hasSize(databaseSizeBeforeCreate);

        // Validate the Holiday in Elasticsearch
        verify(mockHolidaySearchRepository, times(0)).save(holiday);
    }

    @Test
    @Transactional
    public void checkHolidayDescIsRequired() throws Exception {
        int databaseSizeBeforeTest = holidayRepository.findAll().size();
        // set the field null
        holiday.setHolidayDesc(null);

        // Create the Holiday, which fails.
        HolidayDTO holidayDTO = holidayMapper.toDto(holiday);

        restHolidayMockMvc.perform(post("/api/holidays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(holidayDTO)))
            .andExpect(status().isBadRequest());

        List<Holiday> holidayList = holidayRepository.findAll();
        assertThat(holidayList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHolidayDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = holidayRepository.findAll().size();
        // set the field null
        holiday.setHolidayDate(null);

        // Create the Holiday, which fails.
        HolidayDTO holidayDTO = holidayMapper.toDto(holiday);

        restHolidayMockMvc.perform(post("/api/holidays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(holidayDTO)))
            .andExpect(status().isBadRequest());

        List<Holiday> holidayList = holidayRepository.findAll();
        assertThat(holidayList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHolidayStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = holidayRepository.findAll().size();
        // set the field null
        holiday.setHolidayStatus(null);

        // Create the Holiday, which fails.
        HolidayDTO holidayDTO = holidayMapper.toDto(holiday);

        restHolidayMockMvc.perform(post("/api/holidays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(holidayDTO)))
            .andExpect(status().isBadRequest());

        List<Holiday> holidayList = holidayRepository.findAll();
        assertThat(holidayList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllHolidays() throws Exception {
        // Initialize the database
        holidayRepository.saveAndFlush(holiday);

        // Get all the holidayList
        restHolidayMockMvc.perform(get("/api/holidays?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(holiday.getId().intValue())))
            .andExpect(jsonPath("$.[*].holidayDesc").value(hasItem(DEFAULT_HOLIDAY_DESC.toString())))
            .andExpect(jsonPath("$.[*].holidayDate").value(hasItem(DEFAULT_HOLIDAY_DATE.toString())))
            .andExpect(jsonPath("$.[*].holidayStatus").value(hasItem(DEFAULT_HOLIDAY_STATUS.toString())));
    }
    

    @Test
    @Transactional
    public void getHoliday() throws Exception {
        // Initialize the database
        holidayRepository.saveAndFlush(holiday);

        // Get the holiday
        restHolidayMockMvc.perform(get("/api/holidays/{id}", holiday.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(holiday.getId().intValue()))
            .andExpect(jsonPath("$.holidayDesc").value(DEFAULT_HOLIDAY_DESC.toString()))
            .andExpect(jsonPath("$.holidayDate").value(DEFAULT_HOLIDAY_DATE.toString()))
            .andExpect(jsonPath("$.holidayStatus").value(DEFAULT_HOLIDAY_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingHoliday() throws Exception {
        // Get the holiday
        restHolidayMockMvc.perform(get("/api/holidays/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHoliday() throws Exception {
        // Initialize the database
        holidayRepository.saveAndFlush(holiday);

        int databaseSizeBeforeUpdate = holidayRepository.findAll().size();

        // Update the holiday
        Holiday updatedHoliday = holidayRepository.findById(holiday.getId()).get();
        // Disconnect from session so that the updates on updatedHoliday are not directly saved in db
        em.detach(updatedHoliday);
        updatedHoliday
            .holidayDesc(UPDATED_HOLIDAY_DESC)
            .holidayDate(UPDATED_HOLIDAY_DATE)
            .holidayStatus(UPDATED_HOLIDAY_STATUS);
        HolidayDTO holidayDTO = holidayMapper.toDto(updatedHoliday);

        restHolidayMockMvc.perform(put("/api/holidays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(holidayDTO)))
            .andExpect(status().isOk());

        // Validate the Holiday in the database
        List<Holiday> holidayList = holidayRepository.findAll();
        assertThat(holidayList).hasSize(databaseSizeBeforeUpdate);
        Holiday testHoliday = holidayList.get(holidayList.size() - 1);
        assertThat(testHoliday.getHolidayDesc()).isEqualTo(UPDATED_HOLIDAY_DESC);
        assertThat(testHoliday.getHolidayDate()).isEqualTo(UPDATED_HOLIDAY_DATE);
        assertThat(testHoliday.getHolidayStatus()).isEqualTo(UPDATED_HOLIDAY_STATUS);

        // Validate the Holiday in Elasticsearch
        verify(mockHolidaySearchRepository, times(1)).save(testHoliday);
    }

    @Test
    @Transactional
    public void updateNonExistingHoliday() throws Exception {
        int databaseSizeBeforeUpdate = holidayRepository.findAll().size();

        // Create the Holiday
        HolidayDTO holidayDTO = holidayMapper.toDto(holiday);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restHolidayMockMvc.perform(put("/api/holidays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(holidayDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Holiday in the database
        List<Holiday> holidayList = holidayRepository.findAll();
        assertThat(holidayList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Holiday in Elasticsearch
        verify(mockHolidaySearchRepository, times(0)).save(holiday);
    }

    @Test
    @Transactional
    public void deleteHoliday() throws Exception {
        // Initialize the database
        holidayRepository.saveAndFlush(holiday);

        int databaseSizeBeforeDelete = holidayRepository.findAll().size();

        // Get the holiday
        restHolidayMockMvc.perform(delete("/api/holidays/{id}", holiday.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Holiday> holidayList = holidayRepository.findAll();
        assertThat(holidayList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Holiday in Elasticsearch
        verify(mockHolidaySearchRepository, times(1)).deleteById(holiday.getId());
    }

    @Test
    @Transactional
    public void searchHoliday() throws Exception {
        // Initialize the database
        holidayRepository.saveAndFlush(holiday);
        when(mockHolidaySearchRepository.search(queryStringQuery("id:" + holiday.getId())))
            .thenReturn(Collections.singletonList(holiday));
        // Search the holiday
        restHolidayMockMvc.perform(get("/api/_search/holidays?query=id:" + holiday.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(holiday.getId().intValue())))
            .andExpect(jsonPath("$.[*].holidayDesc").value(hasItem(DEFAULT_HOLIDAY_DESC.toString())))
            .andExpect(jsonPath("$.[*].holidayDate").value(hasItem(DEFAULT_HOLIDAY_DATE.toString())))
            .andExpect(jsonPath("$.[*].holidayStatus").value(hasItem(DEFAULT_HOLIDAY_STATUS.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Holiday.class);
        Holiday holiday1 = new Holiday();
        holiday1.setId(1L);
        Holiday holiday2 = new Holiday();
        holiday2.setId(holiday1.getId());
        assertThat(holiday1).isEqualTo(holiday2);
        holiday2.setId(2L);
        assertThat(holiday1).isNotEqualTo(holiday2);
        holiday1.setId(null);
        assertThat(holiday1).isNotEqualTo(holiday2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HolidayDTO.class);
        HolidayDTO holidayDTO1 = new HolidayDTO();
        holidayDTO1.setId(1L);
        HolidayDTO holidayDTO2 = new HolidayDTO();
        assertThat(holidayDTO1).isNotEqualTo(holidayDTO2);
        holidayDTO2.setId(holidayDTO1.getId());
        assertThat(holidayDTO1).isEqualTo(holidayDTO2);
        holidayDTO2.setId(2L);
        assertThat(holidayDTO1).isNotEqualTo(holidayDTO2);
        holidayDTO1.setId(null);
        assertThat(holidayDTO1).isNotEqualTo(holidayDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(holidayMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(holidayMapper.fromId(null)).isNull();
    }
}
