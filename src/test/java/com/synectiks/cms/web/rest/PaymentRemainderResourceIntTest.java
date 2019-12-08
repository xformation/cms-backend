package com.synectiks.cms.web.rest;

import static com.synectiks.cms.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
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
import org.springframework.validation.Validator;

import com.synectiks.cms.CmsApp;
import com.synectiks.cms.entities.PaymentRemainder;
import com.synectiks.cms.repositories.PaymentRemainderRepository;
import com.synectiks.cms.service.PaymentRemainderService;
import com.synectiks.cms.service.dto.PaymentRemainderDTO;
import com.synectiks.cms.service.mapper.PaymentRemainderMapper;
import com.synectiks.cms.web.rest.errors.ExceptionTranslator;

/**
 * Test class for the PaymentRemainderResource REST controller.
 *
 * @see PaymentRemainderResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class PaymentRemainderResourceIntTest {

    private static final String DEFAULT_IS_AUTO_REMAINDER = "AAAAAAAAAA";
    private static final String UPDATED_IS_AUTO_REMAINDER = "BBBBBBBBBB";

    private static final String DEFAULT_IS_FIRST_PAYMENT_REMAINDER = "AAAAAAAAAA";
    private static final String UPDATED_IS_FIRST_PAYMENT_REMAINDER = "BBBBBBBBBB";

    private static final Integer DEFAULT_FIRST_PAYMENT_REMAINDER_DAYS = 1;
    private static final Integer UPDATED_FIRST_PAYMENT_REMAINDER_DAYS = 2;

    private static final String DEFAULT_IS_SECOND_PAYMENT_REMAINDER = "AAAAAAAAAA";
    private static final String UPDATED_IS_SECOND_PAYMENT_REMAINDER = "BBBBBBBBBB";

    private static final Integer DEFAULT_SECOND_PAYMENT_REMAINDER_DAYS = 1;
    private static final Integer UPDATED_SECOND_PAYMENT_REMAINDER_DAYS = 2;

    private static final String DEFAULT_IS_OVER_DUE_PAYMENT_REMAINDER = "AAAAAAAAAA";
    private static final String UPDATED_IS_OVER_DUE_PAYMENT_REMAINDER = "BBBBBBBBBB";

    private static final String DEFAULT_OVER_DUE_PAYMENT_REMAINDER_AFTER_DUE_DATE_OR_UNTIL_PAID = "AAAAAAAAAA";
    private static final String UPDATED_OVER_DUE_PAYMENT_REMAINDER_AFTER_DUE_DATE_OR_UNTIL_PAID = "BBBBBBBBBB";

    private static final Integer DEFAULT_OVER_DUE_PAYMENT_REMAINDER_DAYS = 1;
    private static final Integer UPDATED_OVER_DUE_PAYMENT_REMAINDER_DAYS = 2;

    private static final String DEFAULT_IS_REMAINDER_RECIPIENTS = "AAAAAAAAAA";
    private static final String UPDATED_IS_REMAINDER_RECIPIENTS = "BBBBBBBBBB";

    private static final String DEFAULT_REMAINDER_RECIPIENTS = "AAAAAAAAAA";
    private static final String UPDATED_REMAINDER_RECIPIENTS = "BBBBBBBBBB";

    @Autowired
    private PaymentRemainderRepository paymentRemainderRepository;

    @Autowired
    private PaymentRemainderMapper paymentRemainderMapper;

    @Autowired
    private PaymentRemainderService paymentRemainderService;

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
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaymentRemainder createEntity(EntityManager em) {
        PaymentRemainder paymentRemainder = new PaymentRemainder()
            .isAutoRemainder(DEFAULT_IS_AUTO_REMAINDER)
            .isFirstPaymentRemainder(DEFAULT_IS_FIRST_PAYMENT_REMAINDER)
            .firstPaymentRemainderDays(DEFAULT_FIRST_PAYMENT_REMAINDER_DAYS)
            .isSecondPaymentRemainder(DEFAULT_IS_SECOND_PAYMENT_REMAINDER)
            .secondPaymentRemainderDays(DEFAULT_SECOND_PAYMENT_REMAINDER_DAYS)
            .isOverDuePaymentRemainder(DEFAULT_IS_OVER_DUE_PAYMENT_REMAINDER)
            .overDuePaymentRemainderAfterDueDateOrUntilPaid(DEFAULT_OVER_DUE_PAYMENT_REMAINDER_AFTER_DUE_DATE_OR_UNTIL_PAID)
            .overDuePaymentRemainderDays(DEFAULT_OVER_DUE_PAYMENT_REMAINDER_DAYS)
            .isRemainderRecipients(DEFAULT_IS_REMAINDER_RECIPIENTS)
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
        assertThat(testPaymentRemainder.getIsAutoRemainder()).isEqualTo(DEFAULT_IS_AUTO_REMAINDER);
        assertThat(testPaymentRemainder.getIsFirstPaymentRemainder()).isEqualTo(DEFAULT_IS_FIRST_PAYMENT_REMAINDER);
        assertThat(testPaymentRemainder.getFirstPaymentRemainderDays()).isEqualTo(DEFAULT_FIRST_PAYMENT_REMAINDER_DAYS);
        assertThat(testPaymentRemainder.getIsSecondPaymentRemainder()).isEqualTo(DEFAULT_IS_SECOND_PAYMENT_REMAINDER);
        assertThat(testPaymentRemainder.getSecondPaymentRemainderDays()).isEqualTo(DEFAULT_SECOND_PAYMENT_REMAINDER_DAYS);
        assertThat(testPaymentRemainder.getIsOverDuePaymentRemainder()).isEqualTo(DEFAULT_IS_OVER_DUE_PAYMENT_REMAINDER);
        assertThat(testPaymentRemainder.getOverDuePaymentRemainderAfterDueDateOrUntilPaid()).isEqualTo(DEFAULT_OVER_DUE_PAYMENT_REMAINDER_AFTER_DUE_DATE_OR_UNTIL_PAID);
        assertThat(testPaymentRemainder.getOverDuePaymentRemainderDays()).isEqualTo(DEFAULT_OVER_DUE_PAYMENT_REMAINDER_DAYS);
        assertThat(testPaymentRemainder.getIsRemainderRecipients()).isEqualTo(DEFAULT_IS_REMAINDER_RECIPIENTS);
        assertThat(testPaymentRemainder.getRemainderRecipients()).isEqualTo(DEFAULT_REMAINDER_RECIPIENTS);
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
    }

    @Test
    @Transactional
    public void checkIsAutoRemainderIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentRemainderRepository.findAll().size();
        // set the field null
        paymentRemainder.setIsAutoRemainder(null);

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
            .andExpect(jsonPath("$.[*].isAutoRemainder").value(hasItem(DEFAULT_IS_AUTO_REMAINDER.toString())))
            .andExpect(jsonPath("$.[*].isFirstPaymentRemainder").value(hasItem(DEFAULT_IS_FIRST_PAYMENT_REMAINDER.toString())))
            .andExpect(jsonPath("$.[*].firstPaymentRemainderDays").value(hasItem(DEFAULT_FIRST_PAYMENT_REMAINDER_DAYS)))
            .andExpect(jsonPath("$.[*].isSecondPaymentRemainder").value(hasItem(DEFAULT_IS_SECOND_PAYMENT_REMAINDER.toString())))
            .andExpect(jsonPath("$.[*].secondPaymentRemainderDays").value(hasItem(DEFAULT_SECOND_PAYMENT_REMAINDER_DAYS)))
            .andExpect(jsonPath("$.[*].isOverDuePaymentRemainder").value(hasItem(DEFAULT_IS_OVER_DUE_PAYMENT_REMAINDER.toString())))
            .andExpect(jsonPath("$.[*].overDuePaymentRemainderAfterDueDateOrUntilPaid").value(hasItem(DEFAULT_OVER_DUE_PAYMENT_REMAINDER_AFTER_DUE_DATE_OR_UNTIL_PAID.toString())))
            .andExpect(jsonPath("$.[*].overDuePaymentRemainderDays").value(hasItem(DEFAULT_OVER_DUE_PAYMENT_REMAINDER_DAYS)))
            .andExpect(jsonPath("$.[*].isRemainderRecipients").value(hasItem(DEFAULT_IS_REMAINDER_RECIPIENTS.toString())))
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
            .andExpect(jsonPath("$.isAutoRemainder").value(DEFAULT_IS_AUTO_REMAINDER.toString()))
            .andExpect(jsonPath("$.isFirstPaymentRemainder").value(DEFAULT_IS_FIRST_PAYMENT_REMAINDER.toString()))
            .andExpect(jsonPath("$.firstPaymentRemainderDays").value(DEFAULT_FIRST_PAYMENT_REMAINDER_DAYS))
            .andExpect(jsonPath("$.isSecondPaymentRemainder").value(DEFAULT_IS_SECOND_PAYMENT_REMAINDER.toString()))
            .andExpect(jsonPath("$.secondPaymentRemainderDays").value(DEFAULT_SECOND_PAYMENT_REMAINDER_DAYS))
            .andExpect(jsonPath("$.isOverDuePaymentRemainder").value(DEFAULT_IS_OVER_DUE_PAYMENT_REMAINDER.toString()))
            .andExpect(jsonPath("$.overDuePaymentRemainderAfterDueDateOrUntilPaid").value(DEFAULT_OVER_DUE_PAYMENT_REMAINDER_AFTER_DUE_DATE_OR_UNTIL_PAID.toString()))
            .andExpect(jsonPath("$.overDuePaymentRemainderDays").value(DEFAULT_OVER_DUE_PAYMENT_REMAINDER_DAYS))
            .andExpect(jsonPath("$.isRemainderRecipients").value(DEFAULT_IS_REMAINDER_RECIPIENTS.toString()))
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
            .isAutoRemainder(UPDATED_IS_AUTO_REMAINDER)
            .isFirstPaymentRemainder(UPDATED_IS_FIRST_PAYMENT_REMAINDER)
            .firstPaymentRemainderDays(UPDATED_FIRST_PAYMENT_REMAINDER_DAYS)
            .isSecondPaymentRemainder(UPDATED_IS_SECOND_PAYMENT_REMAINDER)
            .secondPaymentRemainderDays(UPDATED_SECOND_PAYMENT_REMAINDER_DAYS)
            .isOverDuePaymentRemainder(UPDATED_IS_OVER_DUE_PAYMENT_REMAINDER)
            .overDuePaymentRemainderAfterDueDateOrUntilPaid(UPDATED_OVER_DUE_PAYMENT_REMAINDER_AFTER_DUE_DATE_OR_UNTIL_PAID)
            .overDuePaymentRemainderDays(UPDATED_OVER_DUE_PAYMENT_REMAINDER_DAYS)
            .isRemainderRecipients(UPDATED_IS_REMAINDER_RECIPIENTS)
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
        assertThat(testPaymentRemainder.getIsAutoRemainder()).isEqualTo(UPDATED_IS_AUTO_REMAINDER);
        assertThat(testPaymentRemainder.getIsFirstPaymentRemainder()).isEqualTo(UPDATED_IS_FIRST_PAYMENT_REMAINDER);
        assertThat(testPaymentRemainder.getFirstPaymentRemainderDays()).isEqualTo(UPDATED_FIRST_PAYMENT_REMAINDER_DAYS);
        assertThat(testPaymentRemainder.getIsSecondPaymentRemainder()).isEqualTo(UPDATED_IS_SECOND_PAYMENT_REMAINDER);
        assertThat(testPaymentRemainder.getSecondPaymentRemainderDays()).isEqualTo(UPDATED_SECOND_PAYMENT_REMAINDER_DAYS);
        assertThat(testPaymentRemainder.getIsOverDuePaymentRemainder()).isEqualTo(UPDATED_IS_OVER_DUE_PAYMENT_REMAINDER);
        assertThat(testPaymentRemainder.getOverDuePaymentRemainderAfterDueDateOrUntilPaid()).isEqualTo(UPDATED_OVER_DUE_PAYMENT_REMAINDER_AFTER_DUE_DATE_OR_UNTIL_PAID);
        assertThat(testPaymentRemainder.getOverDuePaymentRemainderDays()).isEqualTo(UPDATED_OVER_DUE_PAYMENT_REMAINDER_DAYS);
        assertThat(testPaymentRemainder.getIsRemainderRecipients()).isEqualTo(UPDATED_IS_REMAINDER_RECIPIENTS);
        assertThat(testPaymentRemainder.getRemainderRecipients()).isEqualTo(UPDATED_REMAINDER_RECIPIENTS);
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
    }

    @Test
    @Transactional
    public void deletePaymentRemainder() throws Exception {
        // Initialize the database
        paymentRemainderRepository.saveAndFlush(paymentRemainder);

        int databaseSizeBeforeDelete = paymentRemainderRepository.findAll().size();

        // Delete the paymentRemainder
        restPaymentRemainderMockMvc.perform(delete("/api/payment-remainders/{id}", paymentRemainder.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PaymentRemainder> paymentRemainderList = paymentRemainderRepository.findAll();
        assertThat(paymentRemainderList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchPaymentRemainder() throws Exception {
        // Initialize the database
        paymentRemainderRepository.saveAndFlush(paymentRemainder);
        // Search the paymentRemainder
        restPaymentRemainderMockMvc.perform(get("/api/_search/payment-remainders?query=id:" + paymentRemainder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paymentRemainder.getId().intValue())))
            .andExpect(jsonPath("$.[*].isAutoRemainder").value(hasItem(DEFAULT_IS_AUTO_REMAINDER)))
            .andExpect(jsonPath("$.[*].isFirstPaymentRemainder").value(hasItem(DEFAULT_IS_FIRST_PAYMENT_REMAINDER)))
            .andExpect(jsonPath("$.[*].firstPaymentRemainderDays").value(hasItem(DEFAULT_FIRST_PAYMENT_REMAINDER_DAYS)))
            .andExpect(jsonPath("$.[*].isSecondPaymentRemainder").value(hasItem(DEFAULT_IS_SECOND_PAYMENT_REMAINDER)))
            .andExpect(jsonPath("$.[*].secondPaymentRemainderDays").value(hasItem(DEFAULT_SECOND_PAYMENT_REMAINDER_DAYS)))
            .andExpect(jsonPath("$.[*].isOverDuePaymentRemainder").value(hasItem(DEFAULT_IS_OVER_DUE_PAYMENT_REMAINDER)))
            .andExpect(jsonPath("$.[*].overDuePaymentRemainderAfterDueDateOrUntilPaid").value(hasItem(DEFAULT_OVER_DUE_PAYMENT_REMAINDER_AFTER_DUE_DATE_OR_UNTIL_PAID)))
            .andExpect(jsonPath("$.[*].overDuePaymentRemainderDays").value(hasItem(DEFAULT_OVER_DUE_PAYMENT_REMAINDER_DAYS)))
            .andExpect(jsonPath("$.[*].isRemainderRecipients").value(hasItem(DEFAULT_IS_REMAINDER_RECIPIENTS)))
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
