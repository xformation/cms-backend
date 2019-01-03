package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.Departments;
import com.synectiks.cms.repository.DepartmentsRepository;
import com.synectiks.cms.repository.search.DepartmentsSearchRepository;
import com.synectiks.cms.service.DepartmentsService;
import com.synectiks.cms.service.dto.DepartmentsDTO;
import com.synectiks.cms.service.mapper.DepartmentsMapper;
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

/**
 * Test class for the DepartmentsResource REST controller.
 *
 * @see DepartmentsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class DepartmentsResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_DEPT_HEAD = "AAAAAAAAAA";
    private static final String UPDATED_DEPT_HEAD = "BBBBBBBBBB";

    @Autowired
    private DepartmentsRepository departmentsRepository;


    @Autowired
    private DepartmentsMapper departmentsMapper;
    

    @Autowired
    private DepartmentsService departmentsService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.DepartmentsSearchRepositoryMockConfiguration
     */
    @Autowired
    private DepartmentsSearchRepository mockDepartmentsSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDepartmentsMockMvc;

    private Departments departments;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DepartmentsResource departmentsResource = new DepartmentsResource(departmentsService);
        this.restDepartmentsMockMvc = MockMvcBuilders.standaloneSetup(departmentsResource)
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
    public static Departments createEntity(EntityManager em) {
        Departments departments = new Departments()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .deptHead(DEFAULT_DEPT_HEAD);
        return departments;
    }

    @Before
    public void initTest() {
        departments = createEntity(em);
    }

    @Test
    @Transactional
    public void createDepartments() throws Exception {
        int databaseSizeBeforeCreate = departmentsRepository.findAll().size();

        // Create the Departments
        DepartmentsDTO departmentsDTO = departmentsMapper.toDto(departments);
        restDepartmentsMockMvc.perform(post("/api/departments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departmentsDTO)))
            .andExpect(status().isCreated());

        // Validate the Departments in the database
        List<Departments> departmentsList = departmentsRepository.findAll();
        assertThat(departmentsList).hasSize(databaseSizeBeforeCreate + 1);
        Departments testDepartments = departmentsList.get(departmentsList.size() - 1);
        assertThat(testDepartments.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDepartments.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testDepartments.getDeptHead()).isEqualTo(DEFAULT_DEPT_HEAD);

        // Validate the Departments in Elasticsearch
        verify(mockDepartmentsSearchRepository, times(1)).save(testDepartments);
    }

    @Test
    @Transactional
    public void createDepartmentsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = departmentsRepository.findAll().size();

        // Create the Departments with an existing ID
        departments.setId(1L);
        DepartmentsDTO departmentsDTO = departmentsMapper.toDto(departments);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDepartmentsMockMvc.perform(post("/api/departments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departmentsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Departments in the database
        List<Departments> departmentsList = departmentsRepository.findAll();
        assertThat(departmentsList).hasSize(databaseSizeBeforeCreate);

        // Validate the Departments in Elasticsearch
        verify(mockDepartmentsSearchRepository, times(0)).save(departments);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = departmentsRepository.findAll().size();
        // set the field null
        departments.setName(null);

        // Create the Departments, which fails.
        DepartmentsDTO departmentsDTO = departmentsMapper.toDto(departments);

        restDepartmentsMockMvc.perform(post("/api/departments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departmentsDTO)))
            .andExpect(status().isBadRequest());

        List<Departments> departmentsList = departmentsRepository.findAll();
        assertThat(departmentsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = departmentsRepository.findAll().size();
        // set the field null
        departments.setDescription(null);

        // Create the Departments, which fails.
        DepartmentsDTO departmentsDTO = departmentsMapper.toDto(departments);

        restDepartmentsMockMvc.perform(post("/api/departments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departmentsDTO)))
            .andExpect(status().isBadRequest());

        List<Departments> departmentsList = departmentsRepository.findAll();
        assertThat(departmentsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDeptHeadIsRequired() throws Exception {
        int databaseSizeBeforeTest = departmentsRepository.findAll().size();
        // set the field null
        departments.setDeptHead(null);

        // Create the Departments, which fails.
        DepartmentsDTO departmentsDTO = departmentsMapper.toDto(departments);

        restDepartmentsMockMvc.perform(post("/api/departments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departmentsDTO)))
            .andExpect(status().isBadRequest());

        List<Departments> departmentsList = departmentsRepository.findAll();
        assertThat(departmentsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDepartments() throws Exception {
        // Initialize the database
        departmentsRepository.saveAndFlush(departments);

        // Get all the departmentsList
        restDepartmentsMockMvc.perform(get("/api/departments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(departments.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].deptHead").value(hasItem(DEFAULT_DEPT_HEAD.toString())));
    }
    

    @Test
    @Transactional
    public void getDepartments() throws Exception {
        // Initialize the database
        departmentsRepository.saveAndFlush(departments);

        // Get the departments
        restDepartmentsMockMvc.perform(get("/api/departments/{id}", departments.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(departments.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.deptHead").value(DEFAULT_DEPT_HEAD.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingDepartments() throws Exception {
        // Get the departments
        restDepartmentsMockMvc.perform(get("/api/departments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDepartments() throws Exception {
        // Initialize the database
        departmentsRepository.saveAndFlush(departments);

        int databaseSizeBeforeUpdate = departmentsRepository.findAll().size();

        // Update the departments
        Departments updatedDepartments = departmentsRepository.findById(departments.getId()).get();
        // Disconnect from session so that the updates on updatedDepartments are not directly saved in db
        em.detach(updatedDepartments);
        updatedDepartments
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .deptHead(UPDATED_DEPT_HEAD);
        DepartmentsDTO departmentsDTO = departmentsMapper.toDto(updatedDepartments);

        restDepartmentsMockMvc.perform(put("/api/departments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departmentsDTO)))
            .andExpect(status().isOk());

        // Validate the Departments in the database
        List<Departments> departmentsList = departmentsRepository.findAll();
        assertThat(departmentsList).hasSize(databaseSizeBeforeUpdate);
        Departments testDepartments = departmentsList.get(departmentsList.size() - 1);
        assertThat(testDepartments.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDepartments.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testDepartments.getDeptHead()).isEqualTo(UPDATED_DEPT_HEAD);

        // Validate the Departments in Elasticsearch
        verify(mockDepartmentsSearchRepository, times(1)).save(testDepartments);
    }

    @Test
    @Transactional
    public void updateNonExistingDepartments() throws Exception {
        int databaseSizeBeforeUpdate = departmentsRepository.findAll().size();

        // Create the Departments
        DepartmentsDTO departmentsDTO = departmentsMapper.toDto(departments);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDepartmentsMockMvc.perform(put("/api/departments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departmentsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Departments in the database
        List<Departments> departmentsList = departmentsRepository.findAll();
        assertThat(departmentsList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Departments in Elasticsearch
        verify(mockDepartmentsSearchRepository, times(0)).save(departments);
    }

    @Test
    @Transactional
    public void deleteDepartments() throws Exception {
        // Initialize the database
        departmentsRepository.saveAndFlush(departments);

        int databaseSizeBeforeDelete = departmentsRepository.findAll().size();

        // Get the departments
        restDepartmentsMockMvc.perform(delete("/api/departments/{id}", departments.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Departments> departmentsList = departmentsRepository.findAll();
        assertThat(departmentsList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Departments in Elasticsearch
        verify(mockDepartmentsSearchRepository, times(1)).deleteById(departments.getId());
    }

    @Test
    @Transactional
    public void searchDepartments() throws Exception {
        // Initialize the database
        departmentsRepository.saveAndFlush(departments);
        when(mockDepartmentsSearchRepository.search(queryStringQuery("id:" + departments.getId())))
            .thenReturn(Collections.singletonList(departments));
        // Search the departments
        restDepartmentsMockMvc.perform(get("/api/_search/departments?query=id:" + departments.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(departments.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].deptHead").value(hasItem(DEFAULT_DEPT_HEAD.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Departments.class);
        Departments departments1 = new Departments();
        departments1.setId(1L);
        Departments departments2 = new Departments();
        departments2.setId(departments1.getId());
        assertThat(departments1).isEqualTo(departments2);
        departments2.setId(2L);
        assertThat(departments1).isNotEqualTo(departments2);
        departments1.setId(null);
        assertThat(departments1).isNotEqualTo(departments2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DepartmentsDTO.class);
        DepartmentsDTO departmentsDTO1 = new DepartmentsDTO();
        departmentsDTO1.setId(1L);
        DepartmentsDTO departmentsDTO2 = new DepartmentsDTO();
        assertThat(departmentsDTO1).isNotEqualTo(departmentsDTO2);
        departmentsDTO2.setId(departmentsDTO1.getId());
        assertThat(departmentsDTO1).isEqualTo(departmentsDTO2);
        departmentsDTO2.setId(2L);
        assertThat(departmentsDTO1).isNotEqualTo(departmentsDTO2);
        departmentsDTO1.setId(null);
        assertThat(departmentsDTO1).isNotEqualTo(departmentsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(departmentsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(departmentsMapper.fromId(null)).isNull();
    }
}
