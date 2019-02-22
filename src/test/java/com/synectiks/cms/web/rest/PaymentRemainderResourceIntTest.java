package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.PaymentRemainder;
import com.synectiks.cms.repository.PaymentRemainderRepository;
import com.synectiks.cms.repository.search.PaymentRemainderSearchRepository;
import com.synectiks.cms.service.PaymentRemainderService;
import com.synectiks.cms.service.dto.PaymentRemainderDTO;
import com.synectiks.cms.service.mapper.PaymentRemainderMapper;
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

import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.Status;
/**
 * Test class for the PaymentRemainderResource REST controller.
 *
 * @see PaymentRemainderResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class PaymentRemainderResourceIntTest {

    private static final Status DEFAULT_FEE_REMAINDER = Status.ACTIVE;
    private static final Status UPDATED_FEE_REMAINDER = Status.DEACTIVE;

    private static final Integer DEFAULT_NOTICE_DAY = 1;
    private static final Integer UPDATED_NOTICE_DAY = 2;

    private static final Status DEFAULT_OVER_DUE_REMAINDER = Status.ACTIVE;
    private static final Status UPDATED_OVER_DUE_REMAINDER = Status.DEACTIVE;

    private static final String DEFAULT_REMAINDER_RECIPIENTS = "AAAAAAAAAA";
    private static final String UPDATED_REMAINDER_RECIPIENTS = "BBBBBBBBBB";

    @Autowired
    private PaymentRemainderRepository paymentRemainderRepository;

    @Autowired
    private PaymentRemainderMapper paymentRemainderMapper;

    @Autowired
    private PaymentRemainderService paymentRemainderService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.PaymentRemainderSearchRepositoryMockConfiguration
     */
    @Autowired
    private PaymentRemainderSearchRepository mockPaymentRemainderSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPaymentRemainderMockMvc;

    private PaymentRemainder paymentRemainder;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PaymentRemainderResource paymentRemainderResource = new PaymentRemainderResource(paymentRemainderService);
        this.restPaymentRemainderMockMvc = MockMvcBuilders.standaloneSetup(paymentRemainderResource)
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
    public static PaymentRemainder createEntity(EntityManager em) {
        PaymentRemainder paymentRemainder = new PaymentRemainder()
            .feeRemainder(DEFAULT_FEE_REMAINDER)
            .noticeDay(DEFAULT_NOTICE_DAY)
            .overDueRemainder(DEFAULT_OVER_DUE_REMAINDER)
            .remainderRecipients(DEFAULT_REMAINDER_RECIPIENTS);
        return paymentRemainder;
    }

    @Before
    public void initTest() {
        paymentRemainder = createEntity(em);
    }

    @Test
    @Transactional
    public void createPaymentRemainder() throws Exception {
        int databaseSizeBeforeCreate = paymentRemainderRepository.findAll().size();

        // Create the PaymentRemainder
        PaymentRemainderDTO paymentRemainderDTO = paymentRemainderMapper.toDto(paymentRemainder);
        restPaymentRemainderMockMvc.perform(post("/api/payment-remainders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymentRemainderDTO)))
            .andExpect(status().isCreated());

        // Validate the PaymentRemainder in the database
        List<PaymentRemainder> paymentRemainderList = paymentRemainderRepository.findAll();
        assertThat(paymentRemainderList).hasSize(databaseSizeBeforeCreate + 1);
        PaymentRemainder testPaymentRemainder = paymentRemainderList.get(paymentRemainderList.size() - 1);
        assertThat(testPaymentRemainder.getFeeRemainder()).isEqualTo(DEFAULT_FEE_REMAINDER);
        assertThat(testPaymentRemainder.getNoticeDay()).isEqualTo(DEFAULT_NOTICE_DAY);
        assertThat(testPaymentRemainder.getOverDueRemainder()).isEqualTo(DEFAULT_OVER_DUE_REMAINDER);
        assertThat(testPaymentRemainder.getRemainderRecipients()).isEqualTo(DEFAULT_REMAINDER_RECIPIENTS);

        // Validate the PaymentRemainder in Elasticsearch
        verify(mockPaymentRemainderSearchRepository, times(1)).save(testPaymentRemainder);
    }

    @Test
    @Transactional
    public void createPaymentRemainderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paymentRemainderRepository.findAll().size();

        // Create the PaymentRemainder with an existing ID
        paymentRemainder.setId(1L);
        PaymentRemainderDTO paymentRemainderDTO = paymentRemainderMapper.toDto(paymentRemainder);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPaymentRemainderMockMvc.perform(post("/api/payment-remainders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymentRemainderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PaymentRemainder in the database
        List<PaymentRemainder> paymentRemainderList = paymentRemainderRepository.findAll();
        assertThat(paymentRemainderList).hasSize(databaseSizeBeforeCreate);

        // Validate the PaymentRemainder in Elasticsearch
        verify(mockPaymentRemainderSearchRepository, times(0)).save(paymentRemainder);
    }

    @Test
    @Transactional
    public void checkFeeRemainderIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentRemainderRepository.findAll().size();
        // set the field null
        paymentRemainder.setFeeRemainder(null);

        // Create the PaymentRemainder, which fails.
        PaymentRemainderDTO paymentRemainderDTO = paymentRemainderMapper.toDto(paymentRemainder);

        restPaymentRemainderMockMvc.perform(post("/api/payment-remainders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymentRemainderDTO)))
            .andExpect(status().isBadRequest());

        List<PaymentRemainder> paymentRemainderList = paymentRemainderRepository.findAll();
        assertThat(paymentRemainderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNoticeDayIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentRemainderRepository.findAll().size();
        // set the field null
        paymentRemainder.setNoticeDay(null);

        // Create the PaymentRemainder, which fails.
        PaymentRemainderDTO paymentRemainderDTO = paymentRemainderMapper.toDto(paymentRemainder);

        restPaymentRemainderMockMvc.perform(post("/api/payment-remainders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymentRemainderDTO)))
            .andExpect(status().isBadRequest());

        List<PaymentRemainder> paymentRemainderList = paymentRemainderRepository.findAll();
        assertThat(paymentRemainderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOverDueRemainderIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentRemainderRepository.findAll().size();
        // set the field null
        paymentRemainder.setOverDueRemainder(null);

        // Create the PaymentRemainder, which fails.
        PaymentRemainderDTO paymentRemainderDTO = paymentRemainderMapper.toDto(paymentRemainder);

        restPaymentRemainderMockMvc.perform(post("/api/payment-remainders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymentRemainderDTO)))
            .andExpect(status().isBadRequest());

        List<PaymentRemainder> paymentRemainderList = paymentRemainderRepository.findAll();
        assertThat(paymentRemainderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRemainderRecipientsIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentRemainderRepository.findAll().size();
        // set the field null
        paymentRemainder.setRemainderRecipients(null);

        // Create the PaymentRemainder, which fails.
        PaymentRemainderDTO paymentRemainderDTO = paymentRemainderMapper.toDto(paymentRemainder);

        restPaymentRemainderMockMvc.perform(post("/api/payment-remainders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymentRemainderDTO)))
            .andExpect(status().isBadRequest());

        List<PaymentRemainder> paymentRemainderList = paymentRemainderRepository.findAll();
        assertThat(paymentRemainderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPaymentRemainders() throws Exception {
        // Initialize the database
        paymentRemainderRepository.saveAndFlush(paymentRemainder);

        // Get all the paymentRemainderList
        restPaymentRemainderMockMvc.perform(get("/api/payment-remainders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paymentRemainder.getId().intValue())))
            .andExpect(jsonPath("$.[*].feeRemainder").value(hasItem(DEFAULT_FEE_REMAINDER.toString())))
            .andExpect(jsonPath("$.[*].noticeDay").value(hasItem(DEFAULT_NOTICE_DAY)))
            .andExpect(jsonPath("$.[*].overDueRemainder").value(hasItem(DEFAULT_OVER_DUE_REMAINDER.toString())))
            .andExpect(jsonPath("$.[*].remainderRecipients").value(hasItem(DEFAULT_REMAINDER_RECIPIENTS.toString())));
    }
    
    @Test
    @Transactional
    public void getPaymentRemainder() throws Exception {
        // Initialize the database
        paymentRemainderRepository.saveAndFlush(paymentRemainder);

        // Get the paymentRemainder
        restPaymentRemainderMockMvc.perform(get("/api/payment-remainders/{id}", paymentRemainder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(paymentRemainder.getId().intValue()))
            .andExpect(jsonPath("$.feeRemainder").value(DEFAULT_FEE_REMAINDER.toString()))
            .andExpect(jsonPath("$.noticeDay").value(DEFAULT_NOTICE_DAY))
            .andExpect(jsonPath("$.overDueRemainder").value(DEFAULT_OVER_DUE_REMAINDER.toString()))
            .andExpect(jsonPath("$.remainderRecipients").value(DEFAULT_REMAINDER_RECIPIENTS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPaymentRemainder() throws Exception {
        // Get the paymentRemainder
        restPaymentRemainderMockMvc.perform(get("/api/payment-remainders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePaymentRemainder() throws Exception {
        // Initialize the database
        paymentRemainderRepository.saveAndFlush(paymentRemainder);

        int databaseSizeBeforeUpdate = paymentRemainderRepository.findAll().size();

        // Update the paymentRemainder
        PaymentRemainder updatedPaymentRemainder = paymentRemainderRepository.findById(paymentRemainder.getId()).get();
        // Disconnect from session so that the updates on updatedPaymentRemainder are not directly saved in db
        em.detach(updatedPaymentRemainder);
        updatedPaymentRemainder
            .feeRemainder(UPDATED_FEE_REMAINDER)
            .noticeDay(UPDATED_NOTICE_DAY)
            .overDueRemainder(UPDATED_OVER_DUE_REMAINDER)
            .remainderRecipients(UPDATED_REMAINDER_RECIPIENTS);
        PaymentRemainderDTO paymentRemainderDTO = paymentRemainderMapper.toDto(updatedPaymentRemainder);

        restPaymentRemainderMockMvc.perform(put("/api/payment-remainders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymentRemainderDTO)))
            .andExpect(status().isOk());

        // Validate the PaymentRemainder in the database
        List<PaymentRemainder> paymentRemainderList = paymentRemainderRepository.findAll();
        assertThat(paymentRemainderList).hasSize(databaseSizeBeforeUpdate);
        PaymentRemainder testPaymentRemainder = paymentRemainderList.get(paymentRemainderList.size() - 1);
        assertThat(testPaymentRemainder.getFeeRemainder()).isEqualTo(UPDATED_FEE_REMAINDER);
        assertThat(testPaymentRemainder.getNoticeDay()).isEqualTo(UPDATED_NOTICE_DAY);
        assertThat(testPaymentRemainder.getOverDueRemainder()).isEqualTo(UPDATED_OVER_DUE_REMAINDER);
        assertThat(testPaymentRemainder.getRemainderRecipients()).isEqualTo(UPDATED_REMAINDER_RECIPIENTS);

        // Validate the PaymentRemainder in Elasticsearch
        verify(mockPaymentRemainderSearchRepository, times(1)).save(testPaymentRemainder);
    }

    @Test
    @Transactional
    public void updateNonExistingPaymentRemainder() throws Exception {
        int databaseSizeBeforeUpdate = paymentRemainderRepository.findAll().size();

        // Create the PaymentRemainder
        PaymentRemainderDTO paymentRemainderDTO = paymentRemainderMapper.toDto(paymentRemainder);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymentRemainderMockMvc.perform(put("/api/payment-remainders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymentRemainderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PaymentRemainder in the database
        List<PaymentRemainder> paymentRemainderList = paymentRemainderRepository.findAll();
        assertThat(paymentRemainderList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PaymentRemainder in Elasticsearch
        verify(mockPaymentRemainderSearchRepository, times(0)).save(paymentRemainder);
    }

    @Test
    @Transactional
    public void deletePaymentRemainder() throws Exception {
        // Initialize the database
        paymentRemainderRepository.saveAndFlush(paymentRemainder);

        int databaseSizeBeforeDelete = paymentRemainderRepository.findAll().size();

        // Get the paymentRemainder
        restPaymentRemainderMockMvc.perform(delete("/api/payment-remainders/{id}", paymentRemainder.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PaymentRemainder> paymentRemainderList = paymentRemainderRepository.findAll();
        assertThat(paymentRemainderList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PaymentRemainder in Elasticsearch
        verify(mockPaymentRemainderSearchRepository, times(1)).deleteById(paymentRemainder.getId());
    }

    @Test
    @Transactional
    public void searchPaymentRemainder() throws Exception {
        // Initialize the database
        paymentRemainderRepository.saveAndFlush(paymentRemainder);
        when(mockPaymentRemainderSearchRepository.search(queryStringQuery("id:" + paymentRemainder.getId())))
            .thenReturn(Collections.singletonList(paymentRemainder));
        // Search the paymentRemainder
        restPaymentRemainderMockMvc.perform(get("/api/_search/payment-remainders?query=id:" + paymentRemainder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paymentRemainder.getId().intValue())))
            .andExpect(jsonPath("$.[*].feeRemainder").value(hasItem(DEFAULT_FEE_REMAINDER.toString())))
            .andExpect(jsonPath("$.[*].noticeDay").value(hasItem(DEFAULT_NOTICE_DAY)))
            .andExpect(jsonPath("$.[*].overDueRemainder").value(hasItem(DEFAULT_OVER_DUE_REMAINDER.toString())))
            .andExpect(jsonPath("$.[*].remainderRecipients").value(hasItem(DEFAULT_REMAINDER_RECIPIENTS)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymentRemainder.class);
        PaymentRemainder paymentRemainder1 = new PaymentRemainder();
        paymentRemainder1.setId(1L);
        PaymentRemainder paymentRemainder2 = new PaymentRemainder();
        paymentRemainder2.setId(paymentRemainder1.getId());
        assertThat(paymentRemainder1).isEqualTo(paymentRemainder2);
        paymentRemainder2.setId(2L);
        assertThat(paymentRemainder1).isNotEqualTo(paymentRemainder2);
        paymentRemainder1.setId(null);
        assertThat(paymentRemainder1).isNotEqualTo(paymentRemainder2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymentRemainderDTO.class);
        PaymentRemainderDTO paymentRemainderDTO1 = new PaymentRemainderDTO();
        paymentRemainderDTO1.setId(1L);
        PaymentRemainderDTO paymentRemainderDTO2 = new PaymentRemainderDTO();
        assertThat(paymentRemainderDTO1).isNotEqualTo(paymentRemainderDTO2);
        paymentRemainderDTO2.setId(paymentRemainderDTO1.getId());
        assertThat(paymentRemainderDTO1).isEqualTo(paymentRemainderDTO2);
        paymentRemainderDTO2.setId(2L);
        assertThat(paymentRemainderDTO1).isNotEqualTo(paymentRemainderDTO2);
        paymentRemainderDTO1.setId(null);
        assertThat(paymentRemainderDTO1).isNotEqualTo(paymentRemainderDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(paymentRemainderMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(paymentRemainderMapper.fromId(null)).isNull();
    }
}
