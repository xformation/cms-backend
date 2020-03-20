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
import com.synectiks.cms.domain.Modules;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.repository.ModulesRepository;
import com.synectiks.cms.repository.search.ModulesSearchRepository;
import com.synectiks.cms.service.ModulesService;
import com.synectiks.cms.service.dto.ModulesDTO;
import com.synectiks.cms.service.mapper.ModulesMapper;
import com.synectiks.cms.web.rest.errors.ExceptionTranslator;
/**
 * Test class for the ModulesResource REST controller.
 *
 * @see ModulesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class ModulesResourceIntTest {

    private static final String DEFAULT_MODULE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MODULE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_MODULE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SUB_MODULE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.DEACTIVE;

    @Autowired
    private ModulesRepository modulesRepository;

    @Autowired
    private ModulesMapper modulesMapper;

    @Autowired
    private ModulesService modulesService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.ModulesSearchRepositoryMockConfiguration
     */
    @Autowired
    private ModulesSearchRepository mockModulesSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restModulesMockMvc;

    private Modules modules;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ModulesResource modulesResource = new ModulesResource(modulesService);
        this.restModulesMockMvc = MockMvcBuilders.standaloneSetup(modulesResource)
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
    public static Modules createEntity(EntityManager em) {
        Modules modules = new Modules()
            .moduleName(DEFAULT_MODULE_NAME)
            .subModuleName(DEFAULT_SUB_MODULE_NAME)
            .url(DEFAULT_URL)
            .status(DEFAULT_STATUS);
        return modules;
    }

    @Before
    public void initTest() {
        modules = createEntity(em);
    }

    @Test
    @Transactional
    public void createModules() throws Exception {
        int databaseSizeBeforeCreate = modulesRepository.findAll().size();

        // Create the Modules
        ModulesDTO modulesDTO = modulesMapper.toDto(modules);
        restModulesMockMvc.perform(post("/api/modules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(modulesDTO)))
            .andExpect(status().isCreated());

        // Validate the Modules in the database
        List<Modules> modulesList = modulesRepository.findAll();
        assertThat(modulesList).hasSize(databaseSizeBeforeCreate + 1);
        Modules testModules = modulesList.get(modulesList.size() - 1);
        assertThat(testModules.getModuleName()).isEqualTo(DEFAULT_MODULE_NAME);
        assertThat(testModules.getSubModuleName()).isEqualTo(DEFAULT_SUB_MODULE_NAME);
        assertThat(testModules.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testModules.getStatus()).isEqualTo(DEFAULT_STATUS);

        // Validate the Modules in Elasticsearch
        verify(mockModulesSearchRepository, times(1)).save(testModules);
    }

    @Test
    @Transactional
    public void createModulesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = modulesRepository.findAll().size();

        // Create the Modules with an existing ID
        modules.setId(1L);
        ModulesDTO modulesDTO = modulesMapper.toDto(modules);

        // An entity with an existing ID cannot be created, so this API call must fail
        restModulesMockMvc.perform(post("/api/modules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(modulesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Modules in the database
        List<Modules> modulesList = modulesRepository.findAll();
        assertThat(modulesList).hasSize(databaseSizeBeforeCreate);

        // Validate the Modules in Elasticsearch
        verify(mockModulesSearchRepository, times(0)).save(modules);
    }

    @Test
    @Transactional
    public void getAllModules() throws Exception {
        // Initialize the database
        modulesRepository.saveAndFlush(modules);

        // Get all the modulesList
        restModulesMockMvc.perform(get("/api/modules?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(modules.getId().intValue())))
            .andExpect(jsonPath("$.[*].moduleName").value(hasItem(DEFAULT_MODULE_NAME.toString())))
            .andExpect(jsonPath("$.[*].subModuleName").value(hasItem(DEFAULT_SUB_MODULE_NAME.toString())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getModules() throws Exception {
        // Initialize the database
        modulesRepository.saveAndFlush(modules);

        // Get the modules
        restModulesMockMvc.perform(get("/api/modules/{id}", modules.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(modules.getId().intValue()))
            .andExpect(jsonPath("$.moduleName").value(DEFAULT_MODULE_NAME.toString()))
            .andExpect(jsonPath("$.subModuleName").value(DEFAULT_SUB_MODULE_NAME.toString()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingModules() throws Exception {
        // Get the modules
        restModulesMockMvc.perform(get("/api/modules/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateModules() throws Exception {
        // Initialize the database
        modulesRepository.saveAndFlush(modules);

        int databaseSizeBeforeUpdate = modulesRepository.findAll().size();

        // Update the modules
        Modules updatedModules = modulesRepository.findById(modules.getId()).get();
        // Disconnect from session so that the updates on updatedModules are not directly saved in db
        em.detach(updatedModules);
        updatedModules
            .moduleName(UPDATED_MODULE_NAME)
            .subModuleName(UPDATED_SUB_MODULE_NAME)
            .url(UPDATED_URL)
            .status(UPDATED_STATUS);
        ModulesDTO modulesDTO = modulesMapper.toDto(updatedModules);

        restModulesMockMvc.perform(put("/api/modules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(modulesDTO)))
            .andExpect(status().isOk());

        // Validate the Modules in the database
        List<Modules> modulesList = modulesRepository.findAll();
        assertThat(modulesList).hasSize(databaseSizeBeforeUpdate);
        Modules testModules = modulesList.get(modulesList.size() - 1);
        assertThat(testModules.getModuleName()).isEqualTo(UPDATED_MODULE_NAME);
        assertThat(testModules.getSubModuleName()).isEqualTo(UPDATED_SUB_MODULE_NAME);
        assertThat(testModules.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testModules.getStatus()).isEqualTo(UPDATED_STATUS);

        // Validate the Modules in Elasticsearch
        verify(mockModulesSearchRepository, times(1)).save(testModules);
    }

    @Test
    @Transactional
    public void updateNonExistingModules() throws Exception {
        int databaseSizeBeforeUpdate = modulesRepository.findAll().size();

        // Create the Modules
        ModulesDTO modulesDTO = modulesMapper.toDto(modules);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restModulesMockMvc.perform(put("/api/modules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(modulesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Modules in the database
        List<Modules> modulesList = modulesRepository.findAll();
        assertThat(modulesList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Modules in Elasticsearch
        verify(mockModulesSearchRepository, times(0)).save(modules);
    }

    @Test
    @Transactional
    public void deleteModules() throws Exception {
        // Initialize the database
        modulesRepository.saveAndFlush(modules);

        int databaseSizeBeforeDelete = modulesRepository.findAll().size();

        // Get the modules
        restModulesMockMvc.perform(delete("/api/modules/{id}", modules.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Modules> modulesList = modulesRepository.findAll();
        assertThat(modulesList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Modules in Elasticsearch
        verify(mockModulesSearchRepository, times(1)).deleteById(modules.getId());
    }

    @Test
    @Transactional
    public void searchModules() throws Exception {
        // Initialize the database
        modulesRepository.saveAndFlush(modules);
//        when(mockModulesSearchRepository.search(queryStringQuery("id:" + modules.getId())))
//            .thenReturn(Collections.singletonList(modules));
        // Search the modules
        restModulesMockMvc.perform(get("/api/_search/modules?query=id:" + modules.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(modules.getId().intValue())))
            .andExpect(jsonPath("$.[*].moduleName").value(hasItem(DEFAULT_MODULE_NAME)))
            .andExpect(jsonPath("$.[*].subModuleName").value(hasItem(DEFAULT_SUB_MODULE_NAME)))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Modules.class);
        Modules modules1 = new Modules();
        modules1.setId(1L);
        Modules modules2 = new Modules();
        modules2.setId(modules1.getId());
        assertThat(modules1).isEqualTo(modules2);
        modules2.setId(2L);
        assertThat(modules1).isNotEqualTo(modules2);
        modules1.setId(null);
        assertThat(modules1).isNotEqualTo(modules2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ModulesDTO.class);
        ModulesDTO modulesDTO1 = new ModulesDTO();
        modulesDTO1.setId(1L);
        ModulesDTO modulesDTO2 = new ModulesDTO();
        assertThat(modulesDTO1).isNotEqualTo(modulesDTO2);
        modulesDTO2.setId(modulesDTO1.getId());
        assertThat(modulesDTO1).isEqualTo(modulesDTO2);
        modulesDTO2.setId(2L);
        assertThat(modulesDTO1).isNotEqualTo(modulesDTO2);
        modulesDTO1.setId(null);
        assertThat(modulesDTO1).isNotEqualTo(modulesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(modulesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(modulesMapper.fromId(null)).isNull();
    }
}
