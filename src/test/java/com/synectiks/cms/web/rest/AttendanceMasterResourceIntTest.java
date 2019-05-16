package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.AttendanceMaster;
import com.synectiks.cms.repository.AttendanceMasterRepository;
import com.synectiks.cms.repository.search.AttendanceMasterSearchRepository;
import com.synectiks.cms.service.AttendanceMasterService;
import com.synectiks.cms.service.dto.AttendanceMasterDTO;
import com.synectiks.cms.service.mapper.AttendanceMasterMapper;
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
 * Test class for the AttendanceMasterResource REST controller.
 *
 * @see AttendanceMasterResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class AttendanceMasterResourceIntTest {

    private static final String DEFAULT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_DESC = "BBBBBBBBBB";

    @Autowired
    private AttendanceMasterRepository attendanceMasterRepository;

    @Autowired
    private AttendanceMasterMapper attendanceMasterMapper;

    @Autowired
    private AttendanceMasterService attendanceMasterService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.AttendanceMasterSearchRepositoryMockConfiguration
     */
    @Autowired
    private AttendanceMasterSearchRepository mockAttendanceMasterSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAttendanceMasterMockMvc;

    private AttendanceMaster attendanceMaster;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AttendanceMasterResource attendanceMasterResource = new AttendanceMasterResource(attendanceMasterService);
        this.restAttendanceMasterMockMvc = MockMvcBuilders.standaloneSetup(attendanceMasterResource)
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
    public static AttendanceMaster createEntity(EntityManager em) {
        AttendanceMaster attendanceMaster = new AttendanceMaster()
            .desc(DEFAULT_DESC);
        return attendanceMaster;
    }

    @Before
    public void initTest() {
        attendanceMaster = createEntity(em);
    }

    @Test
    @Transactional
    public void createAttendanceMaster() throws Exception {
        int databaseSizeBeforeCreate = attendanceMasterRepository.findAll().size();

        // Create the AttendanceMaster
        AttendanceMasterDTO attendanceMasterDTO = attendanceMasterMapper.toDto(attendanceMaster);
        restAttendanceMasterMockMvc.perform(post("/api/attendance-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attendanceMasterDTO)))
            .andExpect(status().isCreated());

        // Validate the AttendanceMaster in the database
        List<AttendanceMaster> attendanceMasterList = attendanceMasterRepository.findAll();
        assertThat(attendanceMasterList).hasSize(databaseSizeBeforeCreate + 1);
        AttendanceMaster testAttendanceMaster = attendanceMasterList.get(attendanceMasterList.size() - 1);
        assertThat(testAttendanceMaster.getDesc()).isEqualTo(DEFAULT_DESC);

        // Validate the AttendanceMaster in Elasticsearch
        verify(mockAttendanceMasterSearchRepository, times(1)).save(testAttendanceMaster);
    }

    @Test
    @Transactional
    public void createAttendanceMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = attendanceMasterRepository.findAll().size();

        // Create the AttendanceMaster with an existing ID
        attendanceMaster.setId(1L);
        AttendanceMasterDTO attendanceMasterDTO = attendanceMasterMapper.toDto(attendanceMaster);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAttendanceMasterMockMvc.perform(post("/api/attendance-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attendanceMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AttendanceMaster in the database
        List<AttendanceMaster> attendanceMasterList = attendanceMasterRepository.findAll();
        assertThat(attendanceMasterList).hasSize(databaseSizeBeforeCreate);

        // Validate the AttendanceMaster in Elasticsearch
        verify(mockAttendanceMasterSearchRepository, times(0)).save(attendanceMaster);
    }

    @Test
    @Transactional
    public void getAllAttendanceMasters() throws Exception {
        // Initialize the database
        attendanceMasterRepository.saveAndFlush(attendanceMaster);

        // Get all the attendanceMasterList
        restAttendanceMasterMockMvc.perform(get("/api/attendance-masters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(attendanceMaster.getId().intValue())))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC.toString())));
    }
    
    @Test
    @Transactional
    public void getAttendanceMaster() throws Exception {
        // Initialize the database
        attendanceMasterRepository.saveAndFlush(attendanceMaster);

        // Get the attendanceMaster
        restAttendanceMasterMockMvc.perform(get("/api/attendance-masters/{id}", attendanceMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(attendanceMaster.getId().intValue()))
            .andExpect(jsonPath("$.desc").value(DEFAULT_DESC.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAttendanceMaster() throws Exception {
        // Get the attendanceMaster
        restAttendanceMasterMockMvc.perform(get("/api/attendance-masters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAttendanceMaster() throws Exception {
        // Initialize the database
        attendanceMasterRepository.saveAndFlush(attendanceMaster);

        int databaseSizeBeforeUpdate = attendanceMasterRepository.findAll().size();

        // Update the attendanceMaster
        AttendanceMaster updatedAttendanceMaster = attendanceMasterRepository.findById(attendanceMaster.getId()).get();
        // Disconnect from session so that the updates on updatedAttendanceMaster are not directly saved in db
        em.detach(updatedAttendanceMaster);
        updatedAttendanceMaster
            .desc(UPDATED_DESC);
        AttendanceMasterDTO attendanceMasterDTO = attendanceMasterMapper.toDto(updatedAttendanceMaster);

        restAttendanceMasterMockMvc.perform(put("/api/attendance-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attendanceMasterDTO)))
            .andExpect(status().isOk());

        // Validate the AttendanceMaster in the database
        List<AttendanceMaster> attendanceMasterList = attendanceMasterRepository.findAll();
        assertThat(attendanceMasterList).hasSize(databaseSizeBeforeUpdate);
        AttendanceMaster testAttendanceMaster = attendanceMasterList.get(attendanceMasterList.size() - 1);
        assertThat(testAttendanceMaster.getDesc()).isEqualTo(UPDATED_DESC);

        // Validate the AttendanceMaster in Elasticsearch
        verify(mockAttendanceMasterSearchRepository, times(1)).save(testAttendanceMaster);
    }

    @Test
    @Transactional
    public void updateNonExistingAttendanceMaster() throws Exception {
        int databaseSizeBeforeUpdate = attendanceMasterRepository.findAll().size();

        // Create the AttendanceMaster
        AttendanceMasterDTO attendanceMasterDTO = attendanceMasterMapper.toDto(attendanceMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAttendanceMasterMockMvc.perform(put("/api/attendance-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attendanceMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AttendanceMaster in the database
        List<AttendanceMaster> attendanceMasterList = attendanceMasterRepository.findAll();
        assertThat(attendanceMasterList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AttendanceMaster in Elasticsearch
        verify(mockAttendanceMasterSearchRepository, times(0)).save(attendanceMaster);
    }

    @Test
    @Transactional
    public void deleteAttendanceMaster() throws Exception {
        // Initialize the database
        attendanceMasterRepository.saveAndFlush(attendanceMaster);

        int databaseSizeBeforeDelete = attendanceMasterRepository.findAll().size();

        // Get the attendanceMaster
        restAttendanceMasterMockMvc.perform(delete("/api/attendance-masters/{id}", attendanceMaster.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AttendanceMaster> attendanceMasterList = attendanceMasterRepository.findAll();
        assertThat(attendanceMasterList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AttendanceMaster in Elasticsearch
        verify(mockAttendanceMasterSearchRepository, times(1)).deleteById(attendanceMaster.getId());
    }

    @Test
    @Transactional
    public void searchAttendanceMaster() throws Exception {
        // Initialize the database
        attendanceMasterRepository.saveAndFlush(attendanceMaster);
        when(mockAttendanceMasterSearchRepository.search(queryStringQuery("id:" + attendanceMaster.getId())))
            .thenReturn(Collections.singletonList(attendanceMaster));
        // Search the attendanceMaster
        restAttendanceMasterMockMvc.perform(get("/api/_search/attendance-masters?query=id:" + attendanceMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(attendanceMaster.getId().intValue())))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AttendanceMaster.class);
        AttendanceMaster attendanceMaster1 = new AttendanceMaster();
        attendanceMaster1.setId(1L);
        AttendanceMaster attendanceMaster2 = new AttendanceMaster();
        attendanceMaster2.setId(attendanceMaster1.getId());
        assertThat(attendanceMaster1).isEqualTo(attendanceMaster2);
        attendanceMaster2.setId(2L);
        assertThat(attendanceMaster1).isNotEqualTo(attendanceMaster2);
        attendanceMaster1.setId(null);
        assertThat(attendanceMaster1).isNotEqualTo(attendanceMaster2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AttendanceMasterDTO.class);
        AttendanceMasterDTO attendanceMasterDTO1 = new AttendanceMasterDTO();
        attendanceMasterDTO1.setId(1L);
        AttendanceMasterDTO attendanceMasterDTO2 = new AttendanceMasterDTO();
        assertThat(attendanceMasterDTO1).isNotEqualTo(attendanceMasterDTO2);
        attendanceMasterDTO2.setId(attendanceMasterDTO1.getId());
        assertThat(attendanceMasterDTO1).isEqualTo(attendanceMasterDTO2);
        attendanceMasterDTO2.setId(2L);
        assertThat(attendanceMasterDTO1).isNotEqualTo(attendanceMasterDTO2);
        attendanceMasterDTO1.setId(null);
        assertThat(attendanceMasterDTO1).isNotEqualTo(attendanceMasterDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(attendanceMasterMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(attendanceMasterMapper.fromId(null)).isNull();
    }
}
