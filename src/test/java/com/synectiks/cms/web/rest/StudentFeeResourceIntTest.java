package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.StudentFee;
import com.synectiks.cms.repository.StudentFeeRepository;
import com.synectiks.cms.repository.search.StudentFeeSearchRepository;
import com.synectiks.cms.service.StudentFeeService;
import com.synectiks.cms.service.dto.StudentFeeDTO;
import com.synectiks.cms.service.mapper.StudentFeeMapper;
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

/**
 * Test class for the StudentFeeResource REST controller.
 *
 * @see StudentFeeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class StudentFeeResourceIntTest {

    private static final String DEFAULT_TOTAL_FEE = "AAAAAAAAAA";
    private static final String UPDATED_TOTAL_FEE = "BBBBBBBBBB";

    private static final String DEFAULT_FEES_PAID = "AAAAAAAAAA";
    private static final String UPDATED_FEES_PAID = "BBBBBBBBBB";

    private static final String DEFAULT_FEES_DUE = "AAAAAAAAAA";
    private static final String UPDATED_FEES_DUE = "BBBBBBBBBB";

    private static final Date DEFAULT_DUE_DATE = new Date();
    private static final Date UPDATED_DUE_DATE = new Date();

    private static final String DEFAULT_TOTAL_REMAINING = "AAAAAAAAAA";
    private static final String UPDATED_TOTAL_REMAINING = "BBBBBBBBBB";

    @Autowired
    private StudentFeeRepository studentFeeRepository;

    @Autowired
    private StudentFeeMapper studentFeeMapper;

    @Autowired
    private StudentFeeService studentFeeService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.StudentFeeSearchRepositoryMockConfiguration
     */
    @Autowired
    private StudentFeeSearchRepository mockStudentFeeSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStudentFeeMockMvc;

    private StudentFee studentFee;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StudentFeeResource studentFeeResource = new StudentFeeResource(studentFeeService);
        this.restStudentFeeMockMvc = MockMvcBuilders.standaloneSetup(studentFeeResource)
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
    public static StudentFee createEntity(EntityManager em) {
        StudentFee studentFee = new StudentFee()
            .totalFee(DEFAULT_TOTAL_FEE)
            .feesPaid(DEFAULT_FEES_PAID)
            .feesDue(DEFAULT_FEES_DUE)
            .dueDate(DEFAULT_DUE_DATE)
            .totalRemaining(DEFAULT_TOTAL_REMAINING);
        return studentFee;
    }

    @Before
    public void initTest() {
        studentFee = createEntity(em);
    }

    @Test
    @Transactional
    public void createStudentFee() throws Exception {
        int databaseSizeBeforeCreate = studentFeeRepository.findAll().size();

        // Create the StudentFee
        StudentFeeDTO studentFeeDTO = studentFeeMapper.toDto(studentFee);
        restStudentFeeMockMvc.perform(post("/api/student-fees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentFeeDTO)))
            .andExpect(status().isCreated());

        // Validate the StudentFee in the database
        List<StudentFee> studentFeeList = studentFeeRepository.findAll();
        assertThat(studentFeeList).hasSize(databaseSizeBeforeCreate + 1);
        StudentFee testStudentFee = studentFeeList.get(studentFeeList.size() - 1);
        assertThat(testStudentFee.getTotalFee()).isEqualTo(DEFAULT_TOTAL_FEE);
        assertThat(testStudentFee.getFeesPaid()).isEqualTo(DEFAULT_FEES_PAID);
        assertThat(testStudentFee.getFeesDue()).isEqualTo(DEFAULT_FEES_DUE);
        assertThat(testStudentFee.getDueDate()).isEqualTo(DEFAULT_DUE_DATE);
        assertThat(testStudentFee.getTotalRemaining()).isEqualTo(DEFAULT_TOTAL_REMAINING);

        // Validate the StudentFee in Elasticsearch
        verify(mockStudentFeeSearchRepository, times(1)).save(testStudentFee);
    }

    @Test
    @Transactional
    public void createStudentFeeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = studentFeeRepository.findAll().size();

        // Create the StudentFee with an existing ID
        studentFee.setId(1L);
        StudentFeeDTO studentFeeDTO = studentFeeMapper.toDto(studentFee);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStudentFeeMockMvc.perform(post("/api/student-fees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentFeeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StudentFee in the database
        List<StudentFee> studentFeeList = studentFeeRepository.findAll();
        assertThat(studentFeeList).hasSize(databaseSizeBeforeCreate);

        // Validate the StudentFee in Elasticsearch
        verify(mockStudentFeeSearchRepository, times(0)).save(studentFee);
    }

    @Test
    @Transactional
    public void checkTotalFeeIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentFeeRepository.findAll().size();
        // set the field null
        studentFee.setTotalFee(null);

        // Create the StudentFee, which fails.
        StudentFeeDTO studentFeeDTO = studentFeeMapper.toDto(studentFee);

        restStudentFeeMockMvc.perform(post("/api/student-fees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentFeeDTO)))
            .andExpect(status().isBadRequest());

        List<StudentFee> studentFeeList = studentFeeRepository.findAll();
        assertThat(studentFeeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFeesPaidIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentFeeRepository.findAll().size();
        // set the field null
        studentFee.setFeesPaid(null);

        // Create the StudentFee, which fails.
        StudentFeeDTO studentFeeDTO = studentFeeMapper.toDto(studentFee);

        restStudentFeeMockMvc.perform(post("/api/student-fees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentFeeDTO)))
            .andExpect(status().isBadRequest());

        List<StudentFee> studentFeeList = studentFeeRepository.findAll();
        assertThat(studentFeeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFeesDueIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentFeeRepository.findAll().size();
        // set the field null
        studentFee.setFeesDue(null);

        // Create the StudentFee, which fails.
        StudentFeeDTO studentFeeDTO = studentFeeMapper.toDto(studentFee);

        restStudentFeeMockMvc.perform(post("/api/student-fees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentFeeDTO)))
            .andExpect(status().isBadRequest());

        List<StudentFee> studentFeeList = studentFeeRepository.findAll();
        assertThat(studentFeeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDueDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentFeeRepository.findAll().size();
        // set the field null
        studentFee.setDueDate(null);

        // Create the StudentFee, which fails.
        StudentFeeDTO studentFeeDTO = studentFeeMapper.toDto(studentFee);

        restStudentFeeMockMvc.perform(post("/api/student-fees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentFeeDTO)))
            .andExpect(status().isBadRequest());

        List<StudentFee> studentFeeList = studentFeeRepository.findAll();
        assertThat(studentFeeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTotalRemainingIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentFeeRepository.findAll().size();
        // set the field null
        studentFee.setTotalRemaining(null);

        // Create the StudentFee, which fails.
        StudentFeeDTO studentFeeDTO = studentFeeMapper.toDto(studentFee);

        restStudentFeeMockMvc.perform(post("/api/student-fees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentFeeDTO)))
            .andExpect(status().isBadRequest());

        List<StudentFee> studentFeeList = studentFeeRepository.findAll();
        assertThat(studentFeeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStudentFees() throws Exception {
        // Initialize the database
        studentFeeRepository.saveAndFlush(studentFee);

        // Get all the studentFeeList
        restStudentFeeMockMvc.perform(get("/api/student-fees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(studentFee.getId().intValue())))
            .andExpect(jsonPath("$.[*].totalFee").value(hasItem(DEFAULT_TOTAL_FEE.toString())))
            .andExpect(jsonPath("$.[*].feesPaid").value(hasItem(DEFAULT_FEES_PAID.toString())))
            .andExpect(jsonPath("$.[*].feesDue").value(hasItem(DEFAULT_FEES_DUE.toString())))
            .andExpect(jsonPath("$.[*].dueDate").value(hasItem(DEFAULT_DUE_DATE.toString())))
            .andExpect(jsonPath("$.[*].totalRemaining").value(hasItem(DEFAULT_TOTAL_REMAINING.toString())));
    }
    
    @Test
    @Transactional
    public void getStudentFee() throws Exception {
        // Initialize the database
        studentFeeRepository.saveAndFlush(studentFee);

        // Get the studentFee
        restStudentFeeMockMvc.perform(get("/api/student-fees/{id}", studentFee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(studentFee.getId().intValue()))
            .andExpect(jsonPath("$.totalFee").value(DEFAULT_TOTAL_FEE.toString()))
            .andExpect(jsonPath("$.feesPaid").value(DEFAULT_FEES_PAID.toString()))
            .andExpect(jsonPath("$.feesDue").value(DEFAULT_FEES_DUE.toString()))
            .andExpect(jsonPath("$.dueDate").value(DEFAULT_DUE_DATE.toString()))
            .andExpect(jsonPath("$.totalRemaining").value(DEFAULT_TOTAL_REMAINING.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingStudentFee() throws Exception {
        // Get the studentFee
        restStudentFeeMockMvc.perform(get("/api/student-fees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStudentFee() throws Exception {
        // Initialize the database
        studentFeeRepository.saveAndFlush(studentFee);

        int databaseSizeBeforeUpdate = studentFeeRepository.findAll().size();

        // Update the studentFee
        StudentFee updatedStudentFee = studentFeeRepository.findById(studentFee.getId()).get();
        // Disconnect from session so that the updates on updatedStudentFee are not directly saved in db
        em.detach(updatedStudentFee);
        updatedStudentFee
            .totalFee(UPDATED_TOTAL_FEE)
            .feesPaid(UPDATED_FEES_PAID)
            .feesDue(UPDATED_FEES_DUE)
            .dueDate(UPDATED_DUE_DATE)
            .totalRemaining(UPDATED_TOTAL_REMAINING);
        StudentFeeDTO studentFeeDTO = studentFeeMapper.toDto(updatedStudentFee);

        restStudentFeeMockMvc.perform(put("/api/student-fees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentFeeDTO)))
            .andExpect(status().isOk());

        // Validate the StudentFee in the database
        List<StudentFee> studentFeeList = studentFeeRepository.findAll();
        assertThat(studentFeeList).hasSize(databaseSizeBeforeUpdate);
        StudentFee testStudentFee = studentFeeList.get(studentFeeList.size() - 1);
        assertThat(testStudentFee.getTotalFee()).isEqualTo(UPDATED_TOTAL_FEE);
        assertThat(testStudentFee.getFeesPaid()).isEqualTo(UPDATED_FEES_PAID);
        assertThat(testStudentFee.getFeesDue()).isEqualTo(UPDATED_FEES_DUE);
        assertThat(testStudentFee.getDueDate()).isEqualTo(UPDATED_DUE_DATE);
        assertThat(testStudentFee.getTotalRemaining()).isEqualTo(UPDATED_TOTAL_REMAINING);

        // Validate the StudentFee in Elasticsearch
        verify(mockStudentFeeSearchRepository, times(1)).save(testStudentFee);
    }

    @Test
    @Transactional
    public void updateNonExistingStudentFee() throws Exception {
        int databaseSizeBeforeUpdate = studentFeeRepository.findAll().size();

        // Create the StudentFee
        StudentFeeDTO studentFeeDTO = studentFeeMapper.toDto(studentFee);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStudentFeeMockMvc.perform(put("/api/student-fees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentFeeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StudentFee in the database
        List<StudentFee> studentFeeList = studentFeeRepository.findAll();
        assertThat(studentFeeList).hasSize(databaseSizeBeforeUpdate);

        // Validate the StudentFee in Elasticsearch
        verify(mockStudentFeeSearchRepository, times(0)).save(studentFee);
    }

    @Test
    @Transactional
    public void deleteStudentFee() throws Exception {
        // Initialize the database
        studentFeeRepository.saveAndFlush(studentFee);

        int databaseSizeBeforeDelete = studentFeeRepository.findAll().size();

        // Get the studentFee
        restStudentFeeMockMvc.perform(delete("/api/student-fees/{id}", studentFee.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<StudentFee> studentFeeList = studentFeeRepository.findAll();
        assertThat(studentFeeList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the StudentFee in Elasticsearch
        verify(mockStudentFeeSearchRepository, times(1)).deleteById(studentFee.getId());
    }

    @Test
    @Transactional
    public void searchStudentFee() throws Exception {
        // Initialize the database
        studentFeeRepository.saveAndFlush(studentFee);
        when(mockStudentFeeSearchRepository.search(queryStringQuery("id:" + studentFee.getId())))
            .thenReturn(Collections.singletonList(studentFee));
        // Search the studentFee
        restStudentFeeMockMvc.perform(get("/api/_search/student-fees?query=id:" + studentFee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(studentFee.getId().intValue())))
            .andExpect(jsonPath("$.[*].totalFee").value(hasItem(DEFAULT_TOTAL_FEE)))
            .andExpect(jsonPath("$.[*].feesPaid").value(hasItem(DEFAULT_FEES_PAID)))
            .andExpect(jsonPath("$.[*].feesDue").value(hasItem(DEFAULT_FEES_DUE)))
            .andExpect(jsonPath("$.[*].dueDate").value(hasItem(DEFAULT_DUE_DATE.toString())))
            .andExpect(jsonPath("$.[*].totalRemaining").value(hasItem(DEFAULT_TOTAL_REMAINING)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StudentFee.class);
        StudentFee studentFee1 = new StudentFee();
        studentFee1.setId(1L);
        StudentFee studentFee2 = new StudentFee();
        studentFee2.setId(studentFee1.getId());
        assertThat(studentFee1).isEqualTo(studentFee2);
        studentFee2.setId(2L);
        assertThat(studentFee1).isNotEqualTo(studentFee2);
        studentFee1.setId(null);
        assertThat(studentFee1).isNotEqualTo(studentFee2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StudentFeeDTO.class);
        StudentFeeDTO studentFeeDTO1 = new StudentFeeDTO();
        studentFeeDTO1.setId(1L);
        StudentFeeDTO studentFeeDTO2 = new StudentFeeDTO();
        assertThat(studentFeeDTO1).isNotEqualTo(studentFeeDTO2);
        studentFeeDTO2.setId(studentFeeDTO1.getId());
        assertThat(studentFeeDTO1).isEqualTo(studentFeeDTO2);
        studentFeeDTO2.setId(2L);
        assertThat(studentFeeDTO1).isNotEqualTo(studentFeeDTO2);
        studentFeeDTO1.setId(null);
        assertThat(studentFeeDTO1).isNotEqualTo(studentFeeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(studentFeeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(studentFeeMapper.fromId(null)).isNull();
    }
}
