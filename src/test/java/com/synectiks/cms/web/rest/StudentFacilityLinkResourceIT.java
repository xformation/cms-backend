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
import com.synectiks.cms.domain.StudentFacilityLink;
import com.synectiks.cms.repository.StudentFacilityLinkRepository;
import com.synectiks.cms.repository.search.StudentFacilityLinkSearchRepository;
import com.synectiks.cms.service.StudentFacilityLinkService;
import com.synectiks.cms.service.dto.StudentFacilityLinkDTO;
import com.synectiks.cms.service.mapper.StudentFacilityLinkMapper;
import com.synectiks.cms.web.rest.errors.ExceptionTranslator;

/**
 * Integration tests for the {@Link StudentFacilityLinkResource} REST controller.
 */
@SpringBootTest(classes = CmsApp.class)
public class StudentFacilityLinkResourceIT {

    private static final String DEFAULT_LINK_DESC = "AAAAAAAAAA";
    private static final String UPDATED_LINK_DESC = "BBBBBBBBBB";

    @Autowired
    private StudentFacilityLinkRepository studentFacilityLinkRepository;

    @Autowired
    private StudentFacilityLinkMapper studentFacilityLinkMapper;

    @Autowired
    private StudentFacilityLinkService studentFacilityLinkService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.StudentFacilityLinkSearchRepositoryMockConfiguration
     */
    @Autowired
    private StudentFacilityLinkSearchRepository mockStudentFacilityLinkSearchRepository;

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

    private MockMvc restStudentFacilityLinkMockMvc;

    private StudentFacilityLink studentFacilityLink;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StudentFacilityLinkResource studentFacilityLinkResource = new StudentFacilityLinkResource(studentFacilityLinkService);
        this.restStudentFacilityLinkMockMvc = MockMvcBuilders.standaloneSetup(studentFacilityLinkResource)
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
    public static StudentFacilityLink createEntity(EntityManager em) {
        StudentFacilityLink studentFacilityLink = new StudentFacilityLink()
            .linkDesc(DEFAULT_LINK_DESC);
        return studentFacilityLink;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StudentFacilityLink createUpdatedEntity(EntityManager em) {
        StudentFacilityLink studentFacilityLink = new StudentFacilityLink()
            .linkDesc(UPDATED_LINK_DESC);
        return studentFacilityLink;
    }

    @BeforeEach
    public void initTest() {
        studentFacilityLink = createEntity(em);
    }

    @Test
    @Transactional
    public void createStudentFacilityLink() throws Exception {
        int databaseSizeBeforeCreate = studentFacilityLinkRepository.findAll().size();

        // Create the StudentFacilityLink
        StudentFacilityLinkDTO studentFacilityLinkDTO = studentFacilityLinkMapper.toDto(studentFacilityLink);
        restStudentFacilityLinkMockMvc.perform(post("/api/student-facility-links")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentFacilityLinkDTO)))
            .andExpect(status().isCreated());

        // Validate the StudentFacilityLink in the database
        List<StudentFacilityLink> studentFacilityLinkList = studentFacilityLinkRepository.findAll();
        assertThat(studentFacilityLinkList).hasSize(databaseSizeBeforeCreate + 1);
        StudentFacilityLink testStudentFacilityLink = studentFacilityLinkList.get(studentFacilityLinkList.size() - 1);
        assertThat(testStudentFacilityLink.getLinkDesc()).isEqualTo(DEFAULT_LINK_DESC);

        // Validate the StudentFacilityLink in Elasticsearch
        verify(mockStudentFacilityLinkSearchRepository, times(1)).save(testStudentFacilityLink);
    }

    @Test
    @Transactional
    public void createStudentFacilityLinkWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = studentFacilityLinkRepository.findAll().size();

        // Create the StudentFacilityLink with an existing ID
        studentFacilityLink.setId(1L);
        StudentFacilityLinkDTO studentFacilityLinkDTO = studentFacilityLinkMapper.toDto(studentFacilityLink);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStudentFacilityLinkMockMvc.perform(post("/api/student-facility-links")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentFacilityLinkDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StudentFacilityLink in the database
        List<StudentFacilityLink> studentFacilityLinkList = studentFacilityLinkRepository.findAll();
        assertThat(studentFacilityLinkList).hasSize(databaseSizeBeforeCreate);

        // Validate the StudentFacilityLink in Elasticsearch
        verify(mockStudentFacilityLinkSearchRepository, times(0)).save(studentFacilityLink);
    }


    @Test
    @Transactional
    public void getAllStudentFacilityLinks() throws Exception {
        // Initialize the database
        studentFacilityLinkRepository.saveAndFlush(studentFacilityLink);

        // Get all the studentFacilityLinkList
        restStudentFacilityLinkMockMvc.perform(get("/api/student-facility-links?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(studentFacilityLink.getId().intValue())))
            .andExpect(jsonPath("$.[*].linkDesc").value(hasItem(DEFAULT_LINK_DESC.toString())));
    }
    
    @Test
    @Transactional
    public void getStudentFacilityLink() throws Exception {
        // Initialize the database
        studentFacilityLinkRepository.saveAndFlush(studentFacilityLink);

        // Get the studentFacilityLink
        restStudentFacilityLinkMockMvc.perform(get("/api/student-facility-links/{id}", studentFacilityLink.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(studentFacilityLink.getId().intValue()))
            .andExpect(jsonPath("$.linkDesc").value(DEFAULT_LINK_DESC.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingStudentFacilityLink() throws Exception {
        // Get the studentFacilityLink
        restStudentFacilityLinkMockMvc.perform(get("/api/student-facility-links/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStudentFacilityLink() throws Exception {
        // Initialize the database
        studentFacilityLinkRepository.saveAndFlush(studentFacilityLink);

        int databaseSizeBeforeUpdate = studentFacilityLinkRepository.findAll().size();

        // Update the studentFacilityLink
        StudentFacilityLink updatedStudentFacilityLink = studentFacilityLinkRepository.findById(studentFacilityLink.getId()).get();
        // Disconnect from session so that the updates on updatedStudentFacilityLink are not directly saved in db
        em.detach(updatedStudentFacilityLink);
        updatedStudentFacilityLink
            .linkDesc(UPDATED_LINK_DESC);
        StudentFacilityLinkDTO studentFacilityLinkDTO = studentFacilityLinkMapper.toDto(updatedStudentFacilityLink);

        restStudentFacilityLinkMockMvc.perform(put("/api/student-facility-links")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentFacilityLinkDTO)))
            .andExpect(status().isOk());

        // Validate the StudentFacilityLink in the database
        List<StudentFacilityLink> studentFacilityLinkList = studentFacilityLinkRepository.findAll();
        assertThat(studentFacilityLinkList).hasSize(databaseSizeBeforeUpdate);
        StudentFacilityLink testStudentFacilityLink = studentFacilityLinkList.get(studentFacilityLinkList.size() - 1);
        assertThat(testStudentFacilityLink.getLinkDesc()).isEqualTo(UPDATED_LINK_DESC);

        // Validate the StudentFacilityLink in Elasticsearch
        verify(mockStudentFacilityLinkSearchRepository, times(1)).save(testStudentFacilityLink);
    }

    @Test
    @Transactional
    public void updateNonExistingStudentFacilityLink() throws Exception {
        int databaseSizeBeforeUpdate = studentFacilityLinkRepository.findAll().size();

        // Create the StudentFacilityLink
        StudentFacilityLinkDTO studentFacilityLinkDTO = studentFacilityLinkMapper.toDto(studentFacilityLink);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStudentFacilityLinkMockMvc.perform(put("/api/student-facility-links")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studentFacilityLinkDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StudentFacilityLink in the database
        List<StudentFacilityLink> studentFacilityLinkList = studentFacilityLinkRepository.findAll();
        assertThat(studentFacilityLinkList).hasSize(databaseSizeBeforeUpdate);

        // Validate the StudentFacilityLink in Elasticsearch
        verify(mockStudentFacilityLinkSearchRepository, times(0)).save(studentFacilityLink);
    }

    @Test
    @Transactional
    public void deleteStudentFacilityLink() throws Exception {
        // Initialize the database
        studentFacilityLinkRepository.saveAndFlush(studentFacilityLink);

        int databaseSizeBeforeDelete = studentFacilityLinkRepository.findAll().size();

        // Delete the studentFacilityLink
        restStudentFacilityLinkMockMvc.perform(delete("/api/student-facility-links/{id}", studentFacilityLink.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<StudentFacilityLink> studentFacilityLinkList = studentFacilityLinkRepository.findAll();
        assertThat(studentFacilityLinkList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the StudentFacilityLink in Elasticsearch
        verify(mockStudentFacilityLinkSearchRepository, times(1)).deleteById(studentFacilityLink.getId());
    }

    @Test
    @Transactional
    public void searchStudentFacilityLink() throws Exception {
        // Initialize the database
        studentFacilityLinkRepository.saveAndFlush(studentFacilityLink);
//        when(mockStudentFacilityLinkSearchRepository.search(queryStringQuery("id:" + studentFacilityLink.getId())))
//            .thenReturn(Collections.singletonList(studentFacilityLink));
        // Search the studentFacilityLink
        restStudentFacilityLinkMockMvc.perform(get("/api/_search/student-facility-links?query=id:" + studentFacilityLink.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(studentFacilityLink.getId().intValue())))
            .andExpect(jsonPath("$.[*].linkDesc").value(hasItem(DEFAULT_LINK_DESC)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StudentFacilityLink.class);
        StudentFacilityLink studentFacilityLink1 = new StudentFacilityLink();
        studentFacilityLink1.setId(1L);
        StudentFacilityLink studentFacilityLink2 = new StudentFacilityLink();
        studentFacilityLink2.setId(studentFacilityLink1.getId());
        assertThat(studentFacilityLink1).isEqualTo(studentFacilityLink2);
        studentFacilityLink2.setId(2L);
        assertThat(studentFacilityLink1).isNotEqualTo(studentFacilityLink2);
        studentFacilityLink1.setId(null);
        assertThat(studentFacilityLink1).isNotEqualTo(studentFacilityLink2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StudentFacilityLinkDTO.class);
        StudentFacilityLinkDTO studentFacilityLinkDTO1 = new StudentFacilityLinkDTO();
        studentFacilityLinkDTO1.setId(1L);
        StudentFacilityLinkDTO studentFacilityLinkDTO2 = new StudentFacilityLinkDTO();
        assertThat(studentFacilityLinkDTO1).isNotEqualTo(studentFacilityLinkDTO2);
        studentFacilityLinkDTO2.setId(studentFacilityLinkDTO1.getId());
        assertThat(studentFacilityLinkDTO1).isEqualTo(studentFacilityLinkDTO2);
        studentFacilityLinkDTO2.setId(2L);
        assertThat(studentFacilityLinkDTO1).isNotEqualTo(studentFacilityLinkDTO2);
        studentFacilityLinkDTO1.setId(null);
        assertThat(studentFacilityLinkDTO1).isNotEqualTo(studentFacilityLinkDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(studentFacilityLinkMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(studentFacilityLinkMapper.fromId(null)).isNull();
    }
}
