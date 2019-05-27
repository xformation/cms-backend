package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.StudentExamReport;
import com.synectiks.cms.repository.StudentExamReportRepository;
import com.synectiks.cms.repository.search.StudentExamReportSearchRepository;
import com.synectiks.cms.service.StudentExamReportService;
import com.synectiks.cms.service.dto.StudentExamReportDTO;
import com.synectiks.cms.service.mapper.StudentExamReportMapper;
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

/**
 * Test class for the StudentExamReportResource REST controller.
 *
 * @see StudentExamReportResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class StudentExamReportResourceIntTest {

    private static final Integer DEFAULT_MARKS_OBTAINED = 1;
    private static final Integer UPDATED_MARKS_OBTAINED = 2;

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final Date DEFAULT_CREATED_ON = new Date();
    private static final Date UPDATED_CREATED_ON = new Date();

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Date DEFAULT_UPDATED_ON = new Date();
    private static final Date UPDATED_UPDATED_ON = new Date();

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    @Autowired
    private StudentExamReportRepository studentExamReportRepository;

    @Autowired
    private StudentExamReportMapper studentExamReportMapper;

    @Autowired
    private StudentExamReportService studentExamReportService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.StudentExamReportSearchRepositoryMockConfiguration
     */
    @Autowired
    private StudentExamReportSearchRepository mockStudentExamReportSearchRepository;

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

    private MockMvc restStudentExamReportMockMvc;

    private StudentExamReport studentExamReport;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StudentExamReportResource studentExamReportResource = new StudentExamReportResource(studentExamReportService);
        this.restStudentExamReportMockMvc = MockMvcBuilders.standaloneSetup(studentExamReportResource)
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
    public static StudentExamReport createEntity(EntityManager em) {
        StudentExamReport studentExamReport = new StudentExamReport()
            .marksObtained(DEFAULT_MARKS_OBTAINED)
            .comments(DEFAULT_COMMENTS)
            .createdOn(DEFAULT_CREATED_ON)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY);
        return studentExamReport;
    }

    @Before
    public void initTest() {
        studentExamReport = createEntity(em);
    }

    @Test
    @Transactional
    public void createStudentExamReport() throws Exception {
        int databaseSizeBeforeCreate = studentExamReportRepository.findAll().size();

        // Create the StudentExamReport
        StudentExamReportDTO studentExamReportDTO = studentExamReportMapper.toDto(studentExamReport);
        restStudentExamReportMockMvc.perform(post("/api/student-exam-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentExamReportDTO)))
            .andExpect(status().isCreated());

        // Validate the StudentExamReport in the database
        List<StudentExamReport> studentExamReportList = studentExamReportRepository.findAll();
        assertThat(studentExamReportList).hasSize(databaseSizeBeforeCreate + 1);
        StudentExamReport testStudentExamReport = studentExamReportList.get(studentExamReportList.size() - 1);
        assertThat(testStudentExamReport.getMarksObtained()).isEqualTo(DEFAULT_MARKS_OBTAINED);
        assertThat(testStudentExamReport.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testStudentExamReport.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testStudentExamReport.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testStudentExamReport.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
        assertThat(testStudentExamReport.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);

        // Validate the StudentExamReport in Elasticsearch
        verify(mockStudentExamReportSearchRepository, times(1)).save(testStudentExamReport);
    }

    @Test
    @Transactional
    public void createStudentExamReportWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = studentExamReportRepository.findAll().size();

        // Create the StudentExamReport with an existing ID
        studentExamReport.setId(1L);
        StudentExamReportDTO studentExamReportDTO = studentExamReportMapper.toDto(studentExamReport);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStudentExamReportMockMvc.perform(post("/api/student-exam-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentExamReportDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StudentExamReport in the database
        List<StudentExamReport> studentExamReportList = studentExamReportRepository.findAll();
        assertThat(studentExamReportList).hasSize(databaseSizeBeforeCreate);

        // Validate the StudentExamReport in Elasticsearch
        verify(mockStudentExamReportSearchRepository, times(0)).save(studentExamReport);
    }

    @Test
    @Transactional
    public void checkMarksObtainedIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentExamReportRepository.findAll().size();
        // set the field null
        studentExamReport.setMarksObtained(null);

        // Create the StudentExamReport, which fails.
        StudentExamReportDTO studentExamReportDTO = studentExamReportMapper.toDto(studentExamReport);

        restStudentExamReportMockMvc.perform(post("/api/student-exam-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentExamReportDTO)))
            .andExpect(status().isBadRequest());

        List<StudentExamReport> studentExamReportList = studentExamReportRepository.findAll();
        assertThat(studentExamReportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCommentsIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentExamReportRepository.findAll().size();
        // set the field null
        studentExamReport.setComments(null);

        // Create the StudentExamReport, which fails.
        StudentExamReportDTO studentExamReportDTO = studentExamReportMapper.toDto(studentExamReport);

        restStudentExamReportMockMvc.perform(post("/api/student-exam-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentExamReportDTO)))
            .andExpect(status().isBadRequest());

        List<StudentExamReport> studentExamReportList = studentExamReportRepository.findAll();
        assertThat(studentExamReportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedOnIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentExamReportRepository.findAll().size();
        // set the field null
        studentExamReport.setCreatedOn(null);

        // Create the StudentExamReport, which fails.
        StudentExamReportDTO studentExamReportDTO = studentExamReportMapper.toDto(studentExamReport);

        restStudentExamReportMockMvc.perform(post("/api/student-exam-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentExamReportDTO)))
            .andExpect(status().isBadRequest());

        List<StudentExamReport> studentExamReportList = studentExamReportRepository.findAll();
        assertThat(studentExamReportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentExamReportRepository.findAll().size();
        // set the field null
        studentExamReport.setCreatedBy(null);

        // Create the StudentExamReport, which fails.
        StudentExamReportDTO studentExamReportDTO = studentExamReportMapper.toDto(studentExamReport);

        restStudentExamReportMockMvc.perform(post("/api/student-exam-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentExamReportDTO)))
            .andExpect(status().isBadRequest());

        List<StudentExamReport> studentExamReportList = studentExamReportRepository.findAll();
        assertThat(studentExamReportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUpdatedOnIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentExamReportRepository.findAll().size();
        // set the field null
        studentExamReport.setUpdatedOn(null);

        // Create the StudentExamReport, which fails.
        StudentExamReportDTO studentExamReportDTO = studentExamReportMapper.toDto(studentExamReport);

        restStudentExamReportMockMvc.perform(post("/api/student-exam-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentExamReportDTO)))
            .andExpect(status().isBadRequest());

        List<StudentExamReport> studentExamReportList = studentExamReportRepository.findAll();
        assertThat(studentExamReportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentExamReportRepository.findAll().size();
        // set the field null
        studentExamReport.setUpdatedBy(null);

        // Create the StudentExamReport, which fails.
        StudentExamReportDTO studentExamReportDTO = studentExamReportMapper.toDto(studentExamReport);

        restStudentExamReportMockMvc.perform(post("/api/student-exam-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentExamReportDTO)))
            .andExpect(status().isBadRequest());

        List<StudentExamReport> studentExamReportList = studentExamReportRepository.findAll();
        assertThat(studentExamReportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStudentExamReports() throws Exception {
        // Initialize the database
        studentExamReportRepository.saveAndFlush(studentExamReport);

        // Get all the studentExamReportList
        restStudentExamReportMockMvc.perform(get("/api/student-exam-reports?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(studentExamReport.getId().intValue())))
            .andExpect(jsonPath("$.[*].marksObtained").value(hasItem(DEFAULT_MARKS_OBTAINED)))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())));
    }
    
    @Test
    @Transactional
    public void getStudentExamReport() throws Exception {
        // Initialize the database
        studentExamReportRepository.saveAndFlush(studentExamReport);

        // Get the studentExamReport
        restStudentExamReportMockMvc.perform(get("/api/student-exam-reports/{id}", studentExamReport.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(studentExamReport.getId().intValue()))
            .andExpect(jsonPath("$.marksObtained").value(DEFAULT_MARKS_OBTAINED))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingStudentExamReport() throws Exception {
        // Get the studentExamReport
        restStudentExamReportMockMvc.perform(get("/api/student-exam-reports/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStudentExamReport() throws Exception {
        // Initialize the database
        studentExamReportRepository.saveAndFlush(studentExamReport);

        int databaseSizeBeforeUpdate = studentExamReportRepository.findAll().size();

        // Update the studentExamReport
        StudentExamReport updatedStudentExamReport = studentExamReportRepository.findById(studentExamReport.getId()).get();
        // Disconnect from session so that the updates on updatedStudentExamReport are not directly saved in db
        em.detach(updatedStudentExamReport);
        updatedStudentExamReport
            .marksObtained(UPDATED_MARKS_OBTAINED)
            .comments(UPDATED_COMMENTS)
            .createdOn(UPDATED_CREATED_ON)
            .createdBy(UPDATED_CREATED_BY)
            .updatedOn(UPDATED_UPDATED_ON)
            .updatedBy(UPDATED_UPDATED_BY);
        StudentExamReportDTO studentExamReportDTO = studentExamReportMapper.toDto(updatedStudentExamReport);

        restStudentExamReportMockMvc.perform(put("/api/student-exam-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentExamReportDTO)))
            .andExpect(status().isOk());

        // Validate the StudentExamReport in the database
        List<StudentExamReport> studentExamReportList = studentExamReportRepository.findAll();
        assertThat(studentExamReportList).hasSize(databaseSizeBeforeUpdate);
        StudentExamReport testStudentExamReport = studentExamReportList.get(studentExamReportList.size() - 1);
        assertThat(testStudentExamReport.getMarksObtained()).isEqualTo(UPDATED_MARKS_OBTAINED);
        assertThat(testStudentExamReport.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testStudentExamReport.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testStudentExamReport.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testStudentExamReport.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
        assertThat(testStudentExamReport.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);

        // Validate the StudentExamReport in Elasticsearch
        verify(mockStudentExamReportSearchRepository, times(1)).save(testStudentExamReport);
    }

    @Test
    @Transactional
    public void updateNonExistingStudentExamReport() throws Exception {
        int databaseSizeBeforeUpdate = studentExamReportRepository.findAll().size();

        // Create the StudentExamReport
        StudentExamReportDTO studentExamReportDTO = studentExamReportMapper.toDto(studentExamReport);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStudentExamReportMockMvc.perform(put("/api/student-exam-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentExamReportDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StudentExamReport in the database
        List<StudentExamReport> studentExamReportList = studentExamReportRepository.findAll();
        assertThat(studentExamReportList).hasSize(databaseSizeBeforeUpdate);

        // Validate the StudentExamReport in Elasticsearch
        verify(mockStudentExamReportSearchRepository, times(0)).save(studentExamReport);
    }

    @Test
    @Transactional
    public void deleteStudentExamReport() throws Exception {
        // Initialize the database
        studentExamReportRepository.saveAndFlush(studentExamReport);

        int databaseSizeBeforeDelete = studentExamReportRepository.findAll().size();

        // Delete the studentExamReport
        restStudentExamReportMockMvc.perform(delete("/api/student-exam-reports/{id}", studentExamReport.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<StudentExamReport> studentExamReportList = studentExamReportRepository.findAll();
        assertThat(studentExamReportList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the StudentExamReport in Elasticsearch
        verify(mockStudentExamReportSearchRepository, times(1)).deleteById(studentExamReport.getId());
    }

    @Test
    @Transactional
    public void searchStudentExamReport() throws Exception {
        // Initialize the database
        studentExamReportRepository.saveAndFlush(studentExamReport);
        when(mockStudentExamReportSearchRepository.search(queryStringQuery("id:" + studentExamReport.getId())))
            .thenReturn(Collections.singletonList(studentExamReport));
        // Search the studentExamReport
        restStudentExamReportMockMvc.perform(get("/api/_search/student-exam-reports?query=id:" + studentExamReport.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(studentExamReport.getId().intValue())))
            .andExpect(jsonPath("$.[*].marksObtained").value(hasItem(DEFAULT_MARKS_OBTAINED)))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS)))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StudentExamReport.class);
        StudentExamReport studentExamReport1 = new StudentExamReport();
        studentExamReport1.setId(1L);
        StudentExamReport studentExamReport2 = new StudentExamReport();
        studentExamReport2.setId(studentExamReport1.getId());
        assertThat(studentExamReport1).isEqualTo(studentExamReport2);
        studentExamReport2.setId(2L);
        assertThat(studentExamReport1).isNotEqualTo(studentExamReport2);
        studentExamReport1.setId(null);
        assertThat(studentExamReport1).isNotEqualTo(studentExamReport2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StudentExamReportDTO.class);
        StudentExamReportDTO studentExamReportDTO1 = new StudentExamReportDTO();
        studentExamReportDTO1.setId(1L);
        StudentExamReportDTO studentExamReportDTO2 = new StudentExamReportDTO();
        assertThat(studentExamReportDTO1).isNotEqualTo(studentExamReportDTO2);
        studentExamReportDTO2.setId(studentExamReportDTO1.getId());
        assertThat(studentExamReportDTO1).isEqualTo(studentExamReportDTO2);
        studentExamReportDTO2.setId(2L);
        assertThat(studentExamReportDTO1).isNotEqualTo(studentExamReportDTO2);
        studentExamReportDTO1.setId(null);
        assertThat(studentExamReportDTO1).isNotEqualTo(studentExamReportDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(studentExamReportMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(studentExamReportMapper.fromId(null)).isNull();
    }
}
