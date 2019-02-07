package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.SignatoryLink;
import com.synectiks.cms.repository.SignatoryLinkRepository;
import com.synectiks.cms.repository.search.SignatoryLinkSearchRepository;
import com.synectiks.cms.service.SignatoryLinkService;
import com.synectiks.cms.service.dto.SignatoryLinkDTO;
import com.synectiks.cms.service.mapper.SignatoryLinkMapper;
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
 * Test class for the SignatoryLinkResource REST controller.
 *
 * @see SignatoryLinkResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class SignatoryLinkResourceIntTest {

    private static final String DEFAULT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_DESC = "BBBBBBBBBB";

    @Autowired
    private SignatoryLinkRepository signatoryLinkRepository;

    @Autowired
    private SignatoryLinkMapper signatoryLinkMapper;

    @Autowired
    private SignatoryLinkService signatoryLinkService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.SignatoryLinkSearchRepositoryMockConfiguration
     */
    @Autowired
    private SignatoryLinkSearchRepository mockSignatoryLinkSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSignatoryLinkMockMvc;

    private SignatoryLink signatoryLink;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SignatoryLinkResource signatoryLinkResource = new SignatoryLinkResource(signatoryLinkService);
        this.restSignatoryLinkMockMvc = MockMvcBuilders.standaloneSetup(signatoryLinkResource)
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
    public static SignatoryLink createEntity(EntityManager em) {
        SignatoryLink signatoryLink = new SignatoryLink()
            .desc(DEFAULT_DESC);
        return signatoryLink;
    }

    @Before
    public void initTest() {
        signatoryLink = createEntity(em);
    }

    @Test
    @Transactional
    public void createSignatoryLink() throws Exception {
        int databaseSizeBeforeCreate = signatoryLinkRepository.findAll().size();

        // Create the SignatoryLink
        SignatoryLinkDTO signatoryLinkDTO = signatoryLinkMapper.toDto(signatoryLink);
        restSignatoryLinkMockMvc.perform(post("/api/signatory-links")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(signatoryLinkDTO)))
            .andExpect(status().isCreated());

        // Validate the SignatoryLink in the database
        List<SignatoryLink> signatoryLinkList = signatoryLinkRepository.findAll();
        assertThat(signatoryLinkList).hasSize(databaseSizeBeforeCreate + 1);
        SignatoryLink testSignatoryLink = signatoryLinkList.get(signatoryLinkList.size() - 1);
        assertThat(testSignatoryLink.getDesc()).isEqualTo(DEFAULT_DESC);

        // Validate the SignatoryLink in Elasticsearch
        verify(mockSignatoryLinkSearchRepository, times(1)).save(testSignatoryLink);
    }

    @Test
    @Transactional
    public void createSignatoryLinkWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = signatoryLinkRepository.findAll().size();

        // Create the SignatoryLink with an existing ID
        signatoryLink.setId(1L);
        SignatoryLinkDTO signatoryLinkDTO = signatoryLinkMapper.toDto(signatoryLink);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSignatoryLinkMockMvc.perform(post("/api/signatory-links")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(signatoryLinkDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SignatoryLink in the database
        List<SignatoryLink> signatoryLinkList = signatoryLinkRepository.findAll();
        assertThat(signatoryLinkList).hasSize(databaseSizeBeforeCreate);

        // Validate the SignatoryLink in Elasticsearch
        verify(mockSignatoryLinkSearchRepository, times(0)).save(signatoryLink);
    }

    @Test
    @Transactional
    public void getAllSignatoryLinks() throws Exception {
        // Initialize the database
        signatoryLinkRepository.saveAndFlush(signatoryLink);

        // Get all the signatoryLinkList
        restSignatoryLinkMockMvc.perform(get("/api/signatory-links?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(signatoryLink.getId().intValue())))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC.toString())));
    }
    
    @Test
    @Transactional
    public void getSignatoryLink() throws Exception {
        // Initialize the database
        signatoryLinkRepository.saveAndFlush(signatoryLink);

        // Get the signatoryLink
        restSignatoryLinkMockMvc.perform(get("/api/signatory-links/{id}", signatoryLink.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(signatoryLink.getId().intValue()))
            .andExpect(jsonPath("$.desc").value(DEFAULT_DESC.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSignatoryLink() throws Exception {
        // Get the signatoryLink
        restSignatoryLinkMockMvc.perform(get("/api/signatory-links/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSignatoryLink() throws Exception {
        // Initialize the database
        signatoryLinkRepository.saveAndFlush(signatoryLink);

        int databaseSizeBeforeUpdate = signatoryLinkRepository.findAll().size();

        // Update the signatoryLink
        SignatoryLink updatedSignatoryLink = signatoryLinkRepository.findById(signatoryLink.getId()).get();
        // Disconnect from session so that the updates on updatedSignatoryLink are not directly saved in db
        em.detach(updatedSignatoryLink);
        updatedSignatoryLink
            .desc(UPDATED_DESC);
        SignatoryLinkDTO signatoryLinkDTO = signatoryLinkMapper.toDto(updatedSignatoryLink);

        restSignatoryLinkMockMvc.perform(put("/api/signatory-links")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(signatoryLinkDTO)))
            .andExpect(status().isOk());

        // Validate the SignatoryLink in the database
        List<SignatoryLink> signatoryLinkList = signatoryLinkRepository.findAll();
        assertThat(signatoryLinkList).hasSize(databaseSizeBeforeUpdate);
        SignatoryLink testSignatoryLink = signatoryLinkList.get(signatoryLinkList.size() - 1);
        assertThat(testSignatoryLink.getDesc()).isEqualTo(UPDATED_DESC);

        // Validate the SignatoryLink in Elasticsearch
        verify(mockSignatoryLinkSearchRepository, times(1)).save(testSignatoryLink);
    }

    @Test
    @Transactional
    public void updateNonExistingSignatoryLink() throws Exception {
        int databaseSizeBeforeUpdate = signatoryLinkRepository.findAll().size();

        // Create the SignatoryLink
        SignatoryLinkDTO signatoryLinkDTO = signatoryLinkMapper.toDto(signatoryLink);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSignatoryLinkMockMvc.perform(put("/api/signatory-links")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(signatoryLinkDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SignatoryLink in the database
        List<SignatoryLink> signatoryLinkList = signatoryLinkRepository.findAll();
        assertThat(signatoryLinkList).hasSize(databaseSizeBeforeUpdate);

        // Validate the SignatoryLink in Elasticsearch
        verify(mockSignatoryLinkSearchRepository, times(0)).save(signatoryLink);
    }

    @Test
    @Transactional
    public void deleteSignatoryLink() throws Exception {
        // Initialize the database
        signatoryLinkRepository.saveAndFlush(signatoryLink);

        int databaseSizeBeforeDelete = signatoryLinkRepository.findAll().size();

        // Get the signatoryLink
        restSignatoryLinkMockMvc.perform(delete("/api/signatory-links/{id}", signatoryLink.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SignatoryLink> signatoryLinkList = signatoryLinkRepository.findAll();
        assertThat(signatoryLinkList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the SignatoryLink in Elasticsearch
        verify(mockSignatoryLinkSearchRepository, times(1)).deleteById(signatoryLink.getId());
    }

    @Test
    @Transactional
    public void searchSignatoryLink() throws Exception {
        // Initialize the database
        signatoryLinkRepository.saveAndFlush(signatoryLink);
        when(mockSignatoryLinkSearchRepository.search(queryStringQuery("id:" + signatoryLink.getId())))
            .thenReturn(Collections.singletonList(signatoryLink));
        // Search the signatoryLink
        restSignatoryLinkMockMvc.perform(get("/api/_search/signatory-links?query=id:" + signatoryLink.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(signatoryLink.getId().intValue())))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SignatoryLink.class);
        SignatoryLink signatoryLink1 = new SignatoryLink();
        signatoryLink1.setId(1L);
        SignatoryLink signatoryLink2 = new SignatoryLink();
        signatoryLink2.setId(signatoryLink1.getId());
        assertThat(signatoryLink1).isEqualTo(signatoryLink2);
        signatoryLink2.setId(2L);
        assertThat(signatoryLink1).isNotEqualTo(signatoryLink2);
        signatoryLink1.setId(null);
        assertThat(signatoryLink1).isNotEqualTo(signatoryLink2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SignatoryLinkDTO.class);
        SignatoryLinkDTO signatoryLinkDTO1 = new SignatoryLinkDTO();
        signatoryLinkDTO1.setId(1L);
        SignatoryLinkDTO signatoryLinkDTO2 = new SignatoryLinkDTO();
        assertThat(signatoryLinkDTO1).isNotEqualTo(signatoryLinkDTO2);
        signatoryLinkDTO2.setId(signatoryLinkDTO1.getId());
        assertThat(signatoryLinkDTO1).isEqualTo(signatoryLinkDTO2);
        signatoryLinkDTO2.setId(2L);
        assertThat(signatoryLinkDTO1).isNotEqualTo(signatoryLinkDTO2);
        signatoryLinkDTO1.setId(null);
        assertThat(signatoryLinkDTO1).isNotEqualTo(signatoryLinkDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(signatoryLinkMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(signatoryLinkMapper.fromId(null)).isNull();
    }
}
