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
import com.synectiks.cms.domain.StudentExamReport;
import com.synectiks.cms.repository.StudentExamReportRepository;
import com.synectiks.cms.repository.search.StudentExamReportSearchRepository;
import com.synectiks.cms.service.StudentExamReportService;
import com.synectiks.cms.service.dto.StudentExamReportDTO;
import com.synectiks.cms.service.mapper.StudentExamReportMapper;
import com.synectiks.cms.web.rest.errors.ExceptionTranslator;

/**
 * Integration tests for the {@Link StudentExamReportResource} REST controller.
 */
@SpringBootTest(classes = CmsApp.class)
public class StudentExamReportResourceIT {

    private static final Integer DEFAULT_MARKS_OBTAINED = 1;
    private static final Integer UPDATED_MARKS_OBTAINED = 2;

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final Integer DEFAULT_G_OP = 1;
    private static final Integer UPDATED_G_OP = 2;

    private static final Long DEFAULT_SECTION_ID = 1L;
    private static final Long UPDATED_SECTION_ID = 2L;

    private static final Long DEFAULT_SUBJECT_ID = 1L;
    private static final Long UPDATED_SUBJECT_ID = 2L;

    private static final Long DEFAULT_DEPARTMENT_ID = 1L;
    private static final Long UPDATED_DEPARTMENT_ID = 2L;

    private static final Long DEFAULT_BATCH_ID = 1L;
    private static final Long UPDATED_BATCH_ID = 2L;

    private static final Long DEFAULT_ACADEMICYEAR_ID = 1L;
    private static final Long UPDATED_ACADEMICYEAR_ID = 2L;

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

    @BeforeEach
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
            .gOp(DEFAULT_G_OP)
            .sectionId(DEFAULT_SECTION_ID)
            .subjectId(DEFAULT_SUBJECT_ID)
            .departmentId(DEFAULT_DEPARTMENT_ID)
            .batchId(DEFAULT_BATCH_ID)
            .academicyearId(DEFAULT_ACADEMICYEAR_ID);
        return studentExamReport;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StudentExamReport createUpdatedEntity(EntityManager em) {
        StudentExamReport studentExamReport = new StudentExamReport()
            .marksObtained(UPDATED_MARKS_OBTAINED)
            .comments(UPDATED_COMMENTS)
            .gOp(UPDATED_G_OP)
            .sectionId(UPDATED_SECTION_ID)
            .subjectId(UPDATED_SUBJECT_ID)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .batchId(UPDATED_BATCH_ID)
            .academicyearId(UPDATED_ACADEMICYEAR_ID);
        return studentExamReport;
    }

    @BeforeEach
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
        assertThat(testStudentExamReport.getgOp()).isEqualTo(DEFAULT_G_OP);
        assertThat(testStudentExamReport.getSectionId()).isEqualTo(DEFAULT_SECTION_ID);
        assertThat(testStudentExamReport.getSubjectId()).isEqualTo(DEFAULT_SUBJECT_ID);
        assertThat(testStudentExamReport.getDepartmentId()).isEqualTo(DEFAULT_DEPARTMENT_ID);
        assertThat(testStudentExamReport.getBatchId()).isEqualTo(DEFAULT_BATCH_ID);
        assertThat(testStudentExamReport.getAcademicyearId()).isEqualTo(DEFAULT_ACADEMICYEAR_ID);

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
            .andExpect(jsonPath("$.[*].gOp").value(hasItem(DEFAULT_G_OP)))
            .andExpect(jsonPath("$.[*].sectionId").value(hasItem(DEFAULT_SECTION_ID.intValue())))
            .andExpect(jsonPath("$.[*].subjectId").value(hasItem(DEFAULT_SUBJECT_ID.intValue())))
            .andExpect(jsonPath("$.[*].departmentId").value(hasItem(DEFAULT_DEPARTMENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].batchId").value(hasItem(DEFAULT_BATCH_ID.intValue())))
            .andExpect(jsonPath("$.[*].academicyearId").value(hasItem(DEFAULT_ACADEMICYEAR_ID.intValue())));
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
            .andExpect(jsonPath("$.gOp").value(DEFAULT_G_OP))
            .andExpect(jsonPath("$.sectionId").value(DEFAULT_SECTION_ID.intValue()))
            .andExpect(jsonPath("$.subjectId").value(DEFAULT_SUBJECT_ID.intValue()))
            .andExpect(jsonPath("$.departmentId").value(DEFAULT_DEPARTMENT_ID.intValue()))
            .andExpect(jsonPath("$.batchId").value(DEFAULT_BATCH_ID.intValue()))
            .andExpect(jsonPath("$.academicyearId").value(DEFAULT_ACADEMICYEAR_ID.intValue()));
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
            .gOp(UPDATED_G_OP)
            .sectionId(UPDATED_SECTION_ID)
            .subjectId(UPDATED_SUBJECT_ID)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .batchId(UPDATED_BATCH_ID)
            .academicyearId(UPDATED_ACADEMICYEAR_ID);
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
        assertThat(testStudentExamReport.getgOp()).isEqualTo(UPDATED_G_OP);
        assertThat(testStudentExamReport.getSectionId()).isEqualTo(UPDATED_SECTION_ID);
        assertThat(testStudentExamReport.getSubjectId()).isEqualTo(UPDATED_SUBJECT_ID);
        assertThat(testStudentExamReport.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testStudentExamReport.getBatchId()).isEqualTo(UPDATED_BATCH_ID);
        assertThat(testStudentExamReport.getAcademicyearId()).isEqualTo(UPDATED_ACADEMICYEAR_ID);

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
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
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
//        when(mockStudentExamReportSearchRepository.search(queryStringQuery("id:" + studentExamReport.getId())))
//            .thenReturn(Collections.singletonList(studentExamReport));
        // Search the studentExamReport
        restStudentExamReportMockMvc.perform(get("/api/_search/student-exam-reports?query=id:" + studentExamReport.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(studentExamReport.getId().intValue())))
            .andExpect(jsonPath("$.[*].marksObtained").value(hasItem(DEFAULT_MARKS_OBTAINED)))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS)))
            .andExpect(jsonPath("$.[*].gOp").value(hasItem(DEFAULT_G_OP)))
            .andExpect(jsonPath("$.[*].sectionId").value(hasItem(DEFAULT_SECTION_ID.intValue())))
            .andExpect(jsonPath("$.[*].subjectId").value(hasItem(DEFAULT_SUBJECT_ID.intValue())))
            .andExpect(jsonPath("$.[*].departmentId").value(hasItem(DEFAULT_DEPARTMENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].batchId").value(hasItem(DEFAULT_BATCH_ID.intValue())))
            .andExpect(jsonPath("$.[*].academicyearId").value(hasItem(DEFAULT_ACADEMICYEAR_ID.intValue())));
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
