package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.IdCard;
import com.synectiks.cms.repository.IdCardRepository;
import com.synectiks.cms.repository.search.IdCardSearchRepository;
import com.synectiks.cms.service.IdCardService;
import com.synectiks.cms.service.dto.IdCardDTO;
import com.synectiks.cms.service.mapper.IdCardMapper;
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
 * Test class for the IdCardResource REST controller.
 *
 * @see IdCardResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class IdCardResourceIntTest {

    private static final String DEFAULT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_DESC = "BBBBBBBBBB";

    @Autowired
    private IdCardRepository idCardRepository;

    @Autowired
    private IdCardMapper idCardMapper;

    @Autowired
    private IdCardService idCardService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.IdCardSearchRepositoryMockConfiguration
     */
    @Autowired
    private IdCardSearchRepository mockIdCardSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restIdCardMockMvc;

    private IdCard idCard;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final IdCardResource idCardResource = new IdCardResource(idCardService);
        this.restIdCardMockMvc = MockMvcBuilders.standaloneSetup(idCardResource)
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
    public static IdCard createEntity(EntityManager em) {
        IdCard idCard = new IdCard()
            .desc(DEFAULT_DESC);
        return idCard;
    }

    @Before
    public void initTest() {
        idCard = createEntity(em);
    }

    @Test
    @Transactional
    public void createIdCard() throws Exception {
        int databaseSizeBeforeCreate = idCardRepository.findAll().size();

        // Create the IdCard
        IdCardDTO idCardDTO = idCardMapper.toDto(idCard);
        restIdCardMockMvc.perform(post("/api/id-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(idCardDTO)))
            .andExpect(status().isCreated());

        // Validate the IdCard in the database
        List<IdCard> idCardList = idCardRepository.findAll();
        assertThat(idCardList).hasSize(databaseSizeBeforeCreate + 1);
        IdCard testIdCard = idCardList.get(idCardList.size() - 1);
        assertThat(testIdCard.getDesc()).isEqualTo(DEFAULT_DESC);

        // Validate the IdCard in Elasticsearch
        verify(mockIdCardSearchRepository, times(1)).save(testIdCard);
    }

    @Test
    @Transactional
    public void createIdCardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = idCardRepository.findAll().size();

        // Create the IdCard with an existing ID
        idCard.setId(1L);
        IdCardDTO idCardDTO = idCardMapper.toDto(idCard);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIdCardMockMvc.perform(post("/api/id-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(idCardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the IdCard in the database
        List<IdCard> idCardList = idCardRepository.findAll();
        assertThat(idCardList).hasSize(databaseSizeBeforeCreate);

        // Validate the IdCard in Elasticsearch
        verify(mockIdCardSearchRepository, times(0)).save(idCard);
    }

    @Test
    @Transactional
    public void checkDescIsRequired() throws Exception {
        int databaseSizeBeforeTest = idCardRepository.findAll().size();
        // set the field null
        idCard.setDesc(null);

        // Create the IdCard, which fails.
        IdCardDTO idCardDTO = idCardMapper.toDto(idCard);

        restIdCardMockMvc.perform(post("/api/id-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(idCardDTO)))
            .andExpect(status().isBadRequest());

        List<IdCard> idCardList = idCardRepository.findAll();
        assertThat(idCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllIdCards() throws Exception {
        // Initialize the database
        idCardRepository.saveAndFlush(idCard);

        // Get all the idCardList
        restIdCardMockMvc.perform(get("/api/id-cards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(idCard.getId().intValue())))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC.toString())));
    }

    @Test
    @Transactional
    public void getIdCard() throws Exception {
        // Initialize the database
        idCardRepository.saveAndFlush(idCard);

        // Get the idCard
        restIdCardMockMvc.perform(get("/api/id-cards/{id}", idCard.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(idCard.getId().intValue()))
            .andExpect(jsonPath("$.desc").value(DEFAULT_DESC.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingIdCard() throws Exception {
        // Get the idCard
        restIdCardMockMvc.perform(get("/api/id-cards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIdCard() throws Exception {
        // Initialize the database
        idCardRepository.saveAndFlush(idCard);

        int databaseSizeBeforeUpdate = idCardRepository.findAll().size();

        // Update the idCard
        IdCard updatedIdCard = idCardRepository.findById(idCard.getId()).get();
        // Disconnect from session so that the updates on updatedIdCard are not directly saved in db
        em.detach(updatedIdCard);
        updatedIdCard
            .desc(UPDATED_DESC);
        IdCardDTO idCardDTO = idCardMapper.toDto(updatedIdCard);

        restIdCardMockMvc.perform(put("/api/id-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(idCardDTO)))
            .andExpect(status().isOk());

        // Validate the IdCard in the database
        List<IdCard> idCardList = idCardRepository.findAll();
        assertThat(idCardList).hasSize(databaseSizeBeforeUpdate);
        IdCard testIdCard = idCardList.get(idCardList.size() - 1);
        assertThat(testIdCard.getDesc()).isEqualTo(UPDATED_DESC);

        // Validate the IdCard in Elasticsearch
        verify(mockIdCardSearchRepository, times(1)).save(testIdCard);
    }

    @Test
    @Transactional
    public void updateNonExistingIdCard() throws Exception {
        int databaseSizeBeforeUpdate = idCardRepository.findAll().size();

        // Create the IdCard
        IdCardDTO idCardDTO = idCardMapper.toDto(idCard);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIdCardMockMvc.perform(put("/api/id-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(idCardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the IdCard in the database
        List<IdCard> idCardList = idCardRepository.findAll();
        assertThat(idCardList).hasSize(databaseSizeBeforeUpdate);

        // Validate the IdCard in Elasticsearch
        verify(mockIdCardSearchRepository, times(0)).save(idCard);
    }

    @Test
    @Transactional
    public void deleteIdCard() throws Exception {
        // Initialize the database
        idCardRepository.saveAndFlush(idCard);

        int databaseSizeBeforeDelete = idCardRepository.findAll().size();

        // Get the idCard
        restIdCardMockMvc.perform(delete("/api/id-cards/{id}", idCard.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<IdCard> idCardList = idCardRepository.findAll();
        assertThat(idCardList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the IdCard in Elasticsearch
        verify(mockIdCardSearchRepository, times(1)).deleteById(idCard.getId());
    }

    @Test
    @Transactional
    public void searchIdCard() throws Exception {
        // Initialize the database
        idCardRepository.saveAndFlush(idCard);
//        when(mockIdCardSearchRepository.search(queryStringQuery("id:" + idCard.getId())))
//            .thenReturn(Collections.singletonList(idCard));
        // Search the idCard
        restIdCardMockMvc.perform(get("/api/_search/id-cards?query=id:" + idCard.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(idCard.getId().intValue())))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(IdCard.class);
        IdCard idCard1 = new IdCard();
        idCard1.setId(1L);
        IdCard idCard2 = new IdCard();
        idCard2.setId(idCard1.getId());
        assertThat(idCard1).isEqualTo(idCard2);
        idCard2.setId(2L);
        assertThat(idCard1).isNotEqualTo(idCard2);
        idCard1.setId(null);
        assertThat(idCard1).isNotEqualTo(idCard2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(IdCardDTO.class);
        IdCardDTO idCardDTO1 = new IdCardDTO();
        idCardDTO1.setId(1L);
        IdCardDTO idCardDTO2 = new IdCardDTO();
        assertThat(idCardDTO1).isNotEqualTo(idCardDTO2);
        idCardDTO2.setId(idCardDTO1.getId());
        assertThat(idCardDTO1).isEqualTo(idCardDTO2);
        idCardDTO2.setId(2L);
        assertThat(idCardDTO1).isNotEqualTo(idCardDTO2);
        idCardDTO1.setId(null);
        assertThat(idCardDTO1).isNotEqualTo(idCardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(idCardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(idCardMapper.fromId(null)).isNull();
    }
}
