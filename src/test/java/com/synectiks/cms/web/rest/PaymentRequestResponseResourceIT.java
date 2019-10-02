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

import java.time.LocalDate;
import java.time.ZoneId;
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
import com.synectiks.cms.domain.PaymentRequestResponse;
import com.synectiks.cms.repository.PaymentRequestResponseRepository;
import com.synectiks.cms.service.PaymentRequestResponseService;
import com.synectiks.cms.service.dto.PaymentRequestResponseDTO;
import com.synectiks.cms.service.mapper.PaymentRequestResponseMapper;
import com.synectiks.cms.web.rest.errors.ExceptionTranslator;

/**
 * Integration tests for the {@Link PaymentRequestResponseResource} REST controller.
 */
@SpringBootTest(classes = CmsApp.class)
public class PaymentRequestResponseResourceIT {

    private static final String DEFAULT_REQUEST_MERCHANT_ID = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_MERCHANT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_UNIQUE_TRANSACTION_NO = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_UNIQUE_TRANSACTION_NO = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_TXN_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_TXN_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_CURRENCY_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_CURRENCY_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_TYPE_FIELD_1 = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_TYPE_FIELD_1 = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_SECURITY_ID = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_SECURITY_ID = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_TYPE_FIELD_2 = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_TYPE_FIELD_2 = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_TXTADDITIONAL_1 = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_TXTADDITIONAL_1 = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_TXTADDITIONAL_2 = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_TXTADDITIONAL_2 = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_TXTADDITIONAL_3 = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_TXTADDITIONAL_3 = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_TXTADDITIONAL_4 = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_TXTADDITIONAL_4 = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_TXTADDITIONAL_5 = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_TXTADDITIONAL_5 = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_TXTADDITIONAL_6 = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_TXTADDITIONAL_6 = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_TXTADDITIONAL_7 = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_TXTADDITIONAL_7 = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_RU = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_RU = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_CHECK_SUM = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_CHECK_SUM = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_MSG = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_MSG = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_MERCHANT_ID = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_MERCHANT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_UNIQUE_TRANSACTION_NO = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_UNIQUE_TRANSACTION_NO = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_TXN_REFERENCE_NO = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_TXN_REFERENCE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_BANK_REFERENCE_NO = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_BANK_REFERENCE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_TXN_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_TXN_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_BANK_ID = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_BANK_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_BANK_MERCHANT_ID = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_BANK_MERCHANT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_TXN_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_TXN_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_CURRENCY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_CURRENCY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_ITEM_CODE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_ITEM_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_SECURITY_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_SECURITY_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_SECURITY_ID = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_SECURITY_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_SECURITY_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_SECURITY_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_TXN_DATE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_TXN_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_AUTH_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_AUTH_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_SETTLEMENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_SETTLEMENT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_ADDITIONAL_INFO_1 = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_ADDITIONAL_INFO_1 = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_ADDITIONAL_INFO_2 = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_ADDITIONAL_INFO_2 = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_ADDITIONAL_INFO_3 = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_ADDITIONAL_INFO_3 = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_ADDITIONAL_INFO_4 = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_ADDITIONAL_INFO_4 = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_ADDITIONAL_INFO_5 = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_ADDITIONAL_INFO_5 = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_ADDITIONAL_INFO_6 = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_ADDITIONAL_INFO_6 = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_ADDITIONAL_INFO_7 = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_ADDITIONAL_INFO_7 = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_ERROR_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_ERROR_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_ERROR_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_ERROR_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_CHECK_SUM = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_CHECK_SUM = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_MSG = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_MSG = "BBBBBBBBBB";

    private static final String DEFAULT_USER = "AAAAAAAAAA";
    private static final String UPDATED_USER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_REQUEST_TXN_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REQUEST_TXN_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_REQUEST_TXN_TIME = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_TXN_TIME = "BBBBBBBBBB";

    @Autowired
    private PaymentRequestResponseRepository paymentRequestResponseRepository;

    @Autowired
    private PaymentRequestResponseMapper paymentRequestResponseMapper;

    @Autowired
    private PaymentRequestResponseService paymentRequestResponseService;

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

    private MockMvc restPaymentRequestResponseMockMvc;

    private PaymentRequestResponse paymentRequestResponse;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PaymentRequestResponseResource paymentRequestResponseResource = new PaymentRequestResponseResource(paymentRequestResponseService);
        this.restPaymentRequestResponseMockMvc = MockMvcBuilders.standaloneSetup(paymentRequestResponseResource)
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
    public static PaymentRequestResponse createEntity(EntityManager em) {
        PaymentRequestResponse paymentRequestResponse = new PaymentRequestResponse()
            .requestMerchantID(DEFAULT_REQUEST_MERCHANT_ID)
            .requestUniqueTransactionNo(DEFAULT_REQUEST_UNIQUE_TRANSACTION_NO)
            .requestTxnAmount(DEFAULT_REQUEST_TXN_AMOUNT)
            .requestCurrencyType(DEFAULT_REQUEST_CURRENCY_TYPE)
            .requestTypeField1(DEFAULT_REQUEST_TYPE_FIELD_1)
            .requestSecurityID(DEFAULT_REQUEST_SECURITY_ID)
            .requestTypeField2(DEFAULT_REQUEST_TYPE_FIELD_2)
            .requestTxtadditional1(DEFAULT_REQUEST_TXTADDITIONAL_1)
            .requestTxtadditional2(DEFAULT_REQUEST_TXTADDITIONAL_2)
            .requestTxtadditional3(DEFAULT_REQUEST_TXTADDITIONAL_3)
            .requestTxtadditional4(DEFAULT_REQUEST_TXTADDITIONAL_4)
            .requestTxtadditional5(DEFAULT_REQUEST_TXTADDITIONAL_5)
            .requestTxtadditional6(DEFAULT_REQUEST_TXTADDITIONAL_6)
            .requestTxtadditional7(DEFAULT_REQUEST_TXTADDITIONAL_7)
            .requestRu(DEFAULT_REQUEST_RU)
            .requestCheckSum(DEFAULT_REQUEST_CHECK_SUM)
            .requestMsg(DEFAULT_REQUEST_MSG)
            .responseMerchantID(DEFAULT_RESPONSE_MERCHANT_ID)
            .responseUniqueTransactionNo(DEFAULT_RESPONSE_UNIQUE_TRANSACTION_NO)
            .responseTxnReferenceNo(DEFAULT_RESPONSE_TXN_REFERENCE_NO)
            .responseBankReferenceNo(DEFAULT_RESPONSE_BANK_REFERENCE_NO)
            .responseTxnAmount(DEFAULT_RESPONSE_TXN_AMOUNT)
            .responseBankID(DEFAULT_RESPONSE_BANK_ID)
            .responseBankMerchantID(DEFAULT_RESPONSE_BANK_MERCHANT_ID)
            .responseTxnType(DEFAULT_RESPONSE_TXN_TYPE)
            .responseCurrencyName(DEFAULT_RESPONSE_CURRENCY_NAME)
            .responseItemCode(DEFAULT_RESPONSE_ITEM_CODE)
            .responseSecurityType(DEFAULT_RESPONSE_SECURITY_TYPE)
            .responseSecurityID(DEFAULT_RESPONSE_SECURITY_ID)
            .responseSecurityPassword(DEFAULT_RESPONSE_SECURITY_PASSWORD)
            .responseTxnDate(DEFAULT_RESPONSE_TXN_DATE)
            .responseAuthStatus(DEFAULT_RESPONSE_AUTH_STATUS)
            .responseSettlementType(DEFAULT_RESPONSE_SETTLEMENT_TYPE)
            .responseAdditionalInfo1(DEFAULT_RESPONSE_ADDITIONAL_INFO_1)
            .responseAdditionalInfo2(DEFAULT_RESPONSE_ADDITIONAL_INFO_2)
            .responseAdditionalInfo3(DEFAULT_RESPONSE_ADDITIONAL_INFO_3)
            .responseAdditionalInfo4(DEFAULT_RESPONSE_ADDITIONAL_INFO_4)
            .responseAdditionalInfo5(DEFAULT_RESPONSE_ADDITIONAL_INFO_5)
            .responseAdditionalInfo6(DEFAULT_RESPONSE_ADDITIONAL_INFO_6)
            .responseAdditionalInfo7(DEFAULT_RESPONSE_ADDITIONAL_INFO_7)
            .responseErrorStatus(DEFAULT_RESPONSE_ERROR_STATUS)
            .responseErrorDescription(DEFAULT_RESPONSE_ERROR_DESCRIPTION)
            .responseCheckSum(DEFAULT_RESPONSE_CHECK_SUM)
            .responseMsg(DEFAULT_RESPONSE_MSG)
            .user(DEFAULT_USER)
            .requestTxnDate(DEFAULT_REQUEST_TXN_DATE)
            .requestTxnTime(DEFAULT_REQUEST_TXN_TIME);
        return paymentRequestResponse;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaymentRequestResponse createUpdatedEntity(EntityManager em) {
        PaymentRequestResponse paymentRequestResponse = new PaymentRequestResponse()
            .requestMerchantID(UPDATED_REQUEST_MERCHANT_ID)
            .requestUniqueTransactionNo(UPDATED_REQUEST_UNIQUE_TRANSACTION_NO)
            .requestTxnAmount(UPDATED_REQUEST_TXN_AMOUNT)
            .requestCurrencyType(UPDATED_REQUEST_CURRENCY_TYPE)
            .requestTypeField1(UPDATED_REQUEST_TYPE_FIELD_1)
            .requestSecurityID(UPDATED_REQUEST_SECURITY_ID)
            .requestTypeField2(UPDATED_REQUEST_TYPE_FIELD_2)
            .requestTxtadditional1(UPDATED_REQUEST_TXTADDITIONAL_1)
            .requestTxtadditional2(UPDATED_REQUEST_TXTADDITIONAL_2)
            .requestTxtadditional3(UPDATED_REQUEST_TXTADDITIONAL_3)
            .requestTxtadditional4(UPDATED_REQUEST_TXTADDITIONAL_4)
            .requestTxtadditional5(UPDATED_REQUEST_TXTADDITIONAL_5)
            .requestTxtadditional6(UPDATED_REQUEST_TXTADDITIONAL_6)
            .requestTxtadditional7(UPDATED_REQUEST_TXTADDITIONAL_7)
            .requestRu(UPDATED_REQUEST_RU)
            .requestCheckSum(UPDATED_REQUEST_CHECK_SUM)
            .requestMsg(UPDATED_REQUEST_MSG)
            .responseMerchantID(UPDATED_RESPONSE_MERCHANT_ID)
            .responseUniqueTransactionNo(UPDATED_RESPONSE_UNIQUE_TRANSACTION_NO)
            .responseTxnReferenceNo(UPDATED_RESPONSE_TXN_REFERENCE_NO)
            .responseBankReferenceNo(UPDATED_RESPONSE_BANK_REFERENCE_NO)
            .responseTxnAmount(UPDATED_RESPONSE_TXN_AMOUNT)
            .responseBankID(UPDATED_RESPONSE_BANK_ID)
            .responseBankMerchantID(UPDATED_RESPONSE_BANK_MERCHANT_ID)
            .responseTxnType(UPDATED_RESPONSE_TXN_TYPE)
            .responseCurrencyName(UPDATED_RESPONSE_CURRENCY_NAME)
            .responseItemCode(UPDATED_RESPONSE_ITEM_CODE)
            .responseSecurityType(UPDATED_RESPONSE_SECURITY_TYPE)
            .responseSecurityID(UPDATED_RESPONSE_SECURITY_ID)
            .responseSecurityPassword(UPDATED_RESPONSE_SECURITY_PASSWORD)
            .responseTxnDate(UPDATED_RESPONSE_TXN_DATE)
            .responseAuthStatus(UPDATED_RESPONSE_AUTH_STATUS)
            .responseSettlementType(UPDATED_RESPONSE_SETTLEMENT_TYPE)
            .responseAdditionalInfo1(UPDATED_RESPONSE_ADDITIONAL_INFO_1)
            .responseAdditionalInfo2(UPDATED_RESPONSE_ADDITIONAL_INFO_2)
            .responseAdditionalInfo3(UPDATED_RESPONSE_ADDITIONAL_INFO_3)
            .responseAdditionalInfo4(UPDATED_RESPONSE_ADDITIONAL_INFO_4)
            .responseAdditionalInfo5(UPDATED_RESPONSE_ADDITIONAL_INFO_5)
            .responseAdditionalInfo6(UPDATED_RESPONSE_ADDITIONAL_INFO_6)
            .responseAdditionalInfo7(UPDATED_RESPONSE_ADDITIONAL_INFO_7)
            .responseErrorStatus(UPDATED_RESPONSE_ERROR_STATUS)
            .responseErrorDescription(UPDATED_RESPONSE_ERROR_DESCRIPTION)
            .responseCheckSum(UPDATED_RESPONSE_CHECK_SUM)
            .responseMsg(UPDATED_RESPONSE_MSG)
            .user(UPDATED_USER)
            .requestTxnDate(UPDATED_REQUEST_TXN_DATE)
            .requestTxnTime(UPDATED_REQUEST_TXN_TIME);
        return paymentRequestResponse;
    }

    @BeforeEach
    public void initTest() {
        paymentRequestResponse = createEntity(em);
    }

    @Test
    @Transactional
    public void createPaymentRequestResponse() throws Exception {
        int databaseSizeBeforeCreate = paymentRequestResponseRepository.findAll().size();

        // Create the PaymentRequestResponse
        PaymentRequestResponseDTO paymentRequestResponseDTO = paymentRequestResponseMapper.toDto(paymentRequestResponse);
        restPaymentRequestResponseMockMvc.perform(post("/api/payment-request-responses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymentRequestResponseDTO)))
            .andExpect(status().isCreated());

        // Validate the PaymentRequestResponse in the database
        List<PaymentRequestResponse> paymentRequestResponseList = paymentRequestResponseRepository.findAll();
        assertThat(paymentRequestResponseList).hasSize(databaseSizeBeforeCreate + 1);
        PaymentRequestResponse testPaymentRequestResponse = paymentRequestResponseList.get(paymentRequestResponseList.size() - 1);
        assertThat(testPaymentRequestResponse.getRequestMerchantID()).isEqualTo(DEFAULT_REQUEST_MERCHANT_ID);
        assertThat(testPaymentRequestResponse.getRequestUniqueTransactionNo()).isEqualTo(DEFAULT_REQUEST_UNIQUE_TRANSACTION_NO);
        assertThat(testPaymentRequestResponse.getRequestTxnAmount()).isEqualTo(DEFAULT_REQUEST_TXN_AMOUNT);
        assertThat(testPaymentRequestResponse.getRequestCurrencyType()).isEqualTo(DEFAULT_REQUEST_CURRENCY_TYPE);
        assertThat(testPaymentRequestResponse.getRequestTypeField1()).isEqualTo(DEFAULT_REQUEST_TYPE_FIELD_1);
        assertThat(testPaymentRequestResponse.getRequestSecurityID()).isEqualTo(DEFAULT_REQUEST_SECURITY_ID);
        assertThat(testPaymentRequestResponse.getRequestTypeField2()).isEqualTo(DEFAULT_REQUEST_TYPE_FIELD_2);
        assertThat(testPaymentRequestResponse.getRequestTxtadditional1()).isEqualTo(DEFAULT_REQUEST_TXTADDITIONAL_1);
        assertThat(testPaymentRequestResponse.getRequestTxtadditional2()).isEqualTo(DEFAULT_REQUEST_TXTADDITIONAL_2);
        assertThat(testPaymentRequestResponse.getRequestTxtadditional3()).isEqualTo(DEFAULT_REQUEST_TXTADDITIONAL_3);
        assertThat(testPaymentRequestResponse.getRequestTxtadditional4()).isEqualTo(DEFAULT_REQUEST_TXTADDITIONAL_4);
        assertThat(testPaymentRequestResponse.getRequestTxtadditional5()).isEqualTo(DEFAULT_REQUEST_TXTADDITIONAL_5);
        assertThat(testPaymentRequestResponse.getRequestTxtadditional6()).isEqualTo(DEFAULT_REQUEST_TXTADDITIONAL_6);
        assertThat(testPaymentRequestResponse.getRequestTxtadditional7()).isEqualTo(DEFAULT_REQUEST_TXTADDITIONAL_7);
        assertThat(testPaymentRequestResponse.getRequestRu()).isEqualTo(DEFAULT_REQUEST_RU);
        assertThat(testPaymentRequestResponse.getRequestCheckSum()).isEqualTo(DEFAULT_REQUEST_CHECK_SUM);
        assertThat(testPaymentRequestResponse.getRequestMsg()).isEqualTo(DEFAULT_REQUEST_MSG);
        assertThat(testPaymentRequestResponse.getResponseMerchantID()).isEqualTo(DEFAULT_RESPONSE_MERCHANT_ID);
        assertThat(testPaymentRequestResponse.getResponseUniqueTransactionNo()).isEqualTo(DEFAULT_RESPONSE_UNIQUE_TRANSACTION_NO);
        assertThat(testPaymentRequestResponse.getResponseTxnReferenceNo()).isEqualTo(DEFAULT_RESPONSE_TXN_REFERENCE_NO);
        assertThat(testPaymentRequestResponse.getResponseBankReferenceNo()).isEqualTo(DEFAULT_RESPONSE_BANK_REFERENCE_NO);
        assertThat(testPaymentRequestResponse.getResponseTxnAmount()).isEqualTo(DEFAULT_RESPONSE_TXN_AMOUNT);
        assertThat(testPaymentRequestResponse.getResponseBankID()).isEqualTo(DEFAULT_RESPONSE_BANK_ID);
        assertThat(testPaymentRequestResponse.getResponseBankMerchantID()).isEqualTo(DEFAULT_RESPONSE_BANK_MERCHANT_ID);
        assertThat(testPaymentRequestResponse.getResponseTxnType()).isEqualTo(DEFAULT_RESPONSE_TXN_TYPE);
        assertThat(testPaymentRequestResponse.getResponseCurrencyName()).isEqualTo(DEFAULT_RESPONSE_CURRENCY_NAME);
        assertThat(testPaymentRequestResponse.getResponseItemCode()).isEqualTo(DEFAULT_RESPONSE_ITEM_CODE);
        assertThat(testPaymentRequestResponse.getResponseSecurityType()).isEqualTo(DEFAULT_RESPONSE_SECURITY_TYPE);
        assertThat(testPaymentRequestResponse.getResponseSecurityID()).isEqualTo(DEFAULT_RESPONSE_SECURITY_ID);
        assertThat(testPaymentRequestResponse.getResponseSecurityPassword()).isEqualTo(DEFAULT_RESPONSE_SECURITY_PASSWORD);
        assertThat(testPaymentRequestResponse.getResponseTxnDate()).isEqualTo(DEFAULT_RESPONSE_TXN_DATE);
        assertThat(testPaymentRequestResponse.getResponseAuthStatus()).isEqualTo(DEFAULT_RESPONSE_AUTH_STATUS);
        assertThat(testPaymentRequestResponse.getResponseSettlementType()).isEqualTo(DEFAULT_RESPONSE_SETTLEMENT_TYPE);
        assertThat(testPaymentRequestResponse.getResponseAdditionalInfo1()).isEqualTo(DEFAULT_RESPONSE_ADDITIONAL_INFO_1);
        assertThat(testPaymentRequestResponse.getResponseAdditionalInfo2()).isEqualTo(DEFAULT_RESPONSE_ADDITIONAL_INFO_2);
        assertThat(testPaymentRequestResponse.getResponseAdditionalInfo3()).isEqualTo(DEFAULT_RESPONSE_ADDITIONAL_INFO_3);
        assertThat(testPaymentRequestResponse.getResponseAdditionalInfo4()).isEqualTo(DEFAULT_RESPONSE_ADDITIONAL_INFO_4);
        assertThat(testPaymentRequestResponse.getResponseAdditionalInfo5()).isEqualTo(DEFAULT_RESPONSE_ADDITIONAL_INFO_5);
        assertThat(testPaymentRequestResponse.getResponseAdditionalInfo6()).isEqualTo(DEFAULT_RESPONSE_ADDITIONAL_INFO_6);
        assertThat(testPaymentRequestResponse.getResponseAdditionalInfo7()).isEqualTo(DEFAULT_RESPONSE_ADDITIONAL_INFO_7);
        assertThat(testPaymentRequestResponse.getResponseErrorStatus()).isEqualTo(DEFAULT_RESPONSE_ERROR_STATUS);
        assertThat(testPaymentRequestResponse.getResponseErrorDescription()).isEqualTo(DEFAULT_RESPONSE_ERROR_DESCRIPTION);
        assertThat(testPaymentRequestResponse.getResponseCheckSum()).isEqualTo(DEFAULT_RESPONSE_CHECK_SUM);
        assertThat(testPaymentRequestResponse.getResponseMsg()).isEqualTo(DEFAULT_RESPONSE_MSG);
        assertThat(testPaymentRequestResponse.getUser()).isEqualTo(DEFAULT_USER);
        assertThat(testPaymentRequestResponse.getRequestTxnDate()).isEqualTo(DEFAULT_REQUEST_TXN_DATE);
        assertThat(testPaymentRequestResponse.getRequestTxnTime()).isEqualTo(DEFAULT_REQUEST_TXN_TIME);

    }

    @Test
    @Transactional
    public void createPaymentRequestResponseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paymentRequestResponseRepository.findAll().size();

        // Create the PaymentRequestResponse with an existing ID
        paymentRequestResponse.setId(1L);
        PaymentRequestResponseDTO paymentRequestResponseDTO = paymentRequestResponseMapper.toDto(paymentRequestResponse);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPaymentRequestResponseMockMvc.perform(post("/api/payment-request-responses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymentRequestResponseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PaymentRequestResponse in the database
        List<PaymentRequestResponse> paymentRequestResponseList = paymentRequestResponseRepository.findAll();
        assertThat(paymentRequestResponseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPaymentRequestResponses() throws Exception {
        // Initialize the database
        paymentRequestResponseRepository.saveAndFlush(paymentRequestResponse);

        // Get all the paymentRequestResponseList
        restPaymentRequestResponseMockMvc.perform(get("/api/payment-request-responses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paymentRequestResponse.getId().intValue())))
            .andExpect(jsonPath("$.[*].requestMerchantID").value(hasItem(DEFAULT_REQUEST_MERCHANT_ID.toString())))
            .andExpect(jsonPath("$.[*].requestUniqueTransactionNo").value(hasItem(DEFAULT_REQUEST_UNIQUE_TRANSACTION_NO.toString())))
            .andExpect(jsonPath("$.[*].requestTxnAmount").value(hasItem(DEFAULT_REQUEST_TXN_AMOUNT.toString())))
            .andExpect(jsonPath("$.[*].requestCurrencyType").value(hasItem(DEFAULT_REQUEST_CURRENCY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].requestTypeField1").value(hasItem(DEFAULT_REQUEST_TYPE_FIELD_1.toString())))
            .andExpect(jsonPath("$.[*].requestSecurityID").value(hasItem(DEFAULT_REQUEST_SECURITY_ID.toString())))
            .andExpect(jsonPath("$.[*].requestTypeField2").value(hasItem(DEFAULT_REQUEST_TYPE_FIELD_2.toString())))
            .andExpect(jsonPath("$.[*].requestTxtadditional1").value(hasItem(DEFAULT_REQUEST_TXTADDITIONAL_1.toString())))
            .andExpect(jsonPath("$.[*].requestTxtadditional2").value(hasItem(DEFAULT_REQUEST_TXTADDITIONAL_2.toString())))
            .andExpect(jsonPath("$.[*].requestTxtadditional3").value(hasItem(DEFAULT_REQUEST_TXTADDITIONAL_3.toString())))
            .andExpect(jsonPath("$.[*].requestTxtadditional4").value(hasItem(DEFAULT_REQUEST_TXTADDITIONAL_4.toString())))
            .andExpect(jsonPath("$.[*].requestTxtadditional5").value(hasItem(DEFAULT_REQUEST_TXTADDITIONAL_5.toString())))
            .andExpect(jsonPath("$.[*].requestTxtadditional6").value(hasItem(DEFAULT_REQUEST_TXTADDITIONAL_6.toString())))
            .andExpect(jsonPath("$.[*].requestTxtadditional7").value(hasItem(DEFAULT_REQUEST_TXTADDITIONAL_7.toString())))
            .andExpect(jsonPath("$.[*].requestRu").value(hasItem(DEFAULT_REQUEST_RU.toString())))
            .andExpect(jsonPath("$.[*].requestCheckSum").value(hasItem(DEFAULT_REQUEST_CHECK_SUM.toString())))
            .andExpect(jsonPath("$.[*].requestMsg").value(hasItem(DEFAULT_REQUEST_MSG.toString())))
            .andExpect(jsonPath("$.[*].responseMerchantID").value(hasItem(DEFAULT_RESPONSE_MERCHANT_ID.toString())))
            .andExpect(jsonPath("$.[*].responseUniqueTransactionNo").value(hasItem(DEFAULT_RESPONSE_UNIQUE_TRANSACTION_NO.toString())))
            .andExpect(jsonPath("$.[*].responseTxnReferenceNo").value(hasItem(DEFAULT_RESPONSE_TXN_REFERENCE_NO.toString())))
            .andExpect(jsonPath("$.[*].responseBankReferenceNo").value(hasItem(DEFAULT_RESPONSE_BANK_REFERENCE_NO.toString())))
            .andExpect(jsonPath("$.[*].responseTxnAmount").value(hasItem(DEFAULT_RESPONSE_TXN_AMOUNT.toString())))
            .andExpect(jsonPath("$.[*].responseBankID").value(hasItem(DEFAULT_RESPONSE_BANK_ID.toString())))
            .andExpect(jsonPath("$.[*].responseBankMerchantID").value(hasItem(DEFAULT_RESPONSE_BANK_MERCHANT_ID.toString())))
            .andExpect(jsonPath("$.[*].responseTxnType").value(hasItem(DEFAULT_RESPONSE_TXN_TYPE.toString())))
            .andExpect(jsonPath("$.[*].responseCurrencyName").value(hasItem(DEFAULT_RESPONSE_CURRENCY_NAME.toString())))
            .andExpect(jsonPath("$.[*].responseItemCode").value(hasItem(DEFAULT_RESPONSE_ITEM_CODE.toString())))
            .andExpect(jsonPath("$.[*].responseSecurityType").value(hasItem(DEFAULT_RESPONSE_SECURITY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].responseSecurityID").value(hasItem(DEFAULT_RESPONSE_SECURITY_ID.toString())))
            .andExpect(jsonPath("$.[*].responseSecurityPassword").value(hasItem(DEFAULT_RESPONSE_SECURITY_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].responseTxnDate").value(hasItem(DEFAULT_RESPONSE_TXN_DATE.toString())))
            .andExpect(jsonPath("$.[*].responseAuthStatus").value(hasItem(DEFAULT_RESPONSE_AUTH_STATUS.toString())))
            .andExpect(jsonPath("$.[*].responseSettlementType").value(hasItem(DEFAULT_RESPONSE_SETTLEMENT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].responseAdditionalInfo1").value(hasItem(DEFAULT_RESPONSE_ADDITIONAL_INFO_1.toString())))
            .andExpect(jsonPath("$.[*].responseAdditionalInfo2").value(hasItem(DEFAULT_RESPONSE_ADDITIONAL_INFO_2.toString())))
            .andExpect(jsonPath("$.[*].responseAdditionalInfo3").value(hasItem(DEFAULT_RESPONSE_ADDITIONAL_INFO_3.toString())))
            .andExpect(jsonPath("$.[*].responseAdditionalInfo4").value(hasItem(DEFAULT_RESPONSE_ADDITIONAL_INFO_4.toString())))
            .andExpect(jsonPath("$.[*].responseAdditionalInfo5").value(hasItem(DEFAULT_RESPONSE_ADDITIONAL_INFO_5.toString())))
            .andExpect(jsonPath("$.[*].responseAdditionalInfo6").value(hasItem(DEFAULT_RESPONSE_ADDITIONAL_INFO_6.toString())))
            .andExpect(jsonPath("$.[*].responseAdditionalInfo7").value(hasItem(DEFAULT_RESPONSE_ADDITIONAL_INFO_7.toString())))
            .andExpect(jsonPath("$.[*].responseErrorStatus").value(hasItem(DEFAULT_RESPONSE_ERROR_STATUS.toString())))
            .andExpect(jsonPath("$.[*].responseErrorDescription").value(hasItem(DEFAULT_RESPONSE_ERROR_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].responseCheckSum").value(hasItem(DEFAULT_RESPONSE_CHECK_SUM.toString())))
            .andExpect(jsonPath("$.[*].responseMsg").value(hasItem(DEFAULT_RESPONSE_MSG.toString())))
            .andExpect(jsonPath("$.[*].user").value(hasItem(DEFAULT_USER.toString())))
            .andExpect(jsonPath("$.[*].requestTxnDate").value(hasItem(DEFAULT_REQUEST_TXN_DATE.toString())))
            .andExpect(jsonPath("$.[*].requestTxnTime").value(hasItem(DEFAULT_REQUEST_TXN_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getPaymentRequestResponse() throws Exception {
        // Initialize the database
        paymentRequestResponseRepository.saveAndFlush(paymentRequestResponse);

        // Get the paymentRequestResponse
        restPaymentRequestResponseMockMvc.perform(get("/api/payment-request-responses/{id}", paymentRequestResponse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(paymentRequestResponse.getId().intValue()))
            .andExpect(jsonPath("$.requestMerchantID").value(DEFAULT_REQUEST_MERCHANT_ID.toString()))
            .andExpect(jsonPath("$.requestUniqueTransactionNo").value(DEFAULT_REQUEST_UNIQUE_TRANSACTION_NO.toString()))
            .andExpect(jsonPath("$.requestTxnAmount").value(DEFAULT_REQUEST_TXN_AMOUNT.toString()))
            .andExpect(jsonPath("$.requestCurrencyType").value(DEFAULT_REQUEST_CURRENCY_TYPE.toString()))
            .andExpect(jsonPath("$.requestTypeField1").value(DEFAULT_REQUEST_TYPE_FIELD_1.toString()))
            .andExpect(jsonPath("$.requestSecurityID").value(DEFAULT_REQUEST_SECURITY_ID.toString()))
            .andExpect(jsonPath("$.requestTypeField2").value(DEFAULT_REQUEST_TYPE_FIELD_2.toString()))
            .andExpect(jsonPath("$.requestTxtadditional1").value(DEFAULT_REQUEST_TXTADDITIONAL_1.toString()))
            .andExpect(jsonPath("$.requestTxtadditional2").value(DEFAULT_REQUEST_TXTADDITIONAL_2.toString()))
            .andExpect(jsonPath("$.requestTxtadditional3").value(DEFAULT_REQUEST_TXTADDITIONAL_3.toString()))
            .andExpect(jsonPath("$.requestTxtadditional4").value(DEFAULT_REQUEST_TXTADDITIONAL_4.toString()))
            .andExpect(jsonPath("$.requestTxtadditional5").value(DEFAULT_REQUEST_TXTADDITIONAL_5.toString()))
            .andExpect(jsonPath("$.requestTxtadditional6").value(DEFAULT_REQUEST_TXTADDITIONAL_6.toString()))
            .andExpect(jsonPath("$.requestTxtadditional7").value(DEFAULT_REQUEST_TXTADDITIONAL_7.toString()))
            .andExpect(jsonPath("$.requestRu").value(DEFAULT_REQUEST_RU.toString()))
            .andExpect(jsonPath("$.requestCheckSum").value(DEFAULT_REQUEST_CHECK_SUM.toString()))
            .andExpect(jsonPath("$.requestMsg").value(DEFAULT_REQUEST_MSG.toString()))
            .andExpect(jsonPath("$.responseMerchantID").value(DEFAULT_RESPONSE_MERCHANT_ID.toString()))
            .andExpect(jsonPath("$.responseUniqueTransactionNo").value(DEFAULT_RESPONSE_UNIQUE_TRANSACTION_NO.toString()))
            .andExpect(jsonPath("$.responseTxnReferenceNo").value(DEFAULT_RESPONSE_TXN_REFERENCE_NO.toString()))
            .andExpect(jsonPath("$.responseBankReferenceNo").value(DEFAULT_RESPONSE_BANK_REFERENCE_NO.toString()))
            .andExpect(jsonPath("$.responseTxnAmount").value(DEFAULT_RESPONSE_TXN_AMOUNT.toString()))
            .andExpect(jsonPath("$.responseBankID").value(DEFAULT_RESPONSE_BANK_ID.toString()))
            .andExpect(jsonPath("$.responseBankMerchantID").value(DEFAULT_RESPONSE_BANK_MERCHANT_ID.toString()))
            .andExpect(jsonPath("$.responseTxnType").value(DEFAULT_RESPONSE_TXN_TYPE.toString()))
            .andExpect(jsonPath("$.responseCurrencyName").value(DEFAULT_RESPONSE_CURRENCY_NAME.toString()))
            .andExpect(jsonPath("$.responseItemCode").value(DEFAULT_RESPONSE_ITEM_CODE.toString()))
            .andExpect(jsonPath("$.responseSecurityType").value(DEFAULT_RESPONSE_SECURITY_TYPE.toString()))
            .andExpect(jsonPath("$.responseSecurityID").value(DEFAULT_RESPONSE_SECURITY_ID.toString()))
            .andExpect(jsonPath("$.responseSecurityPassword").value(DEFAULT_RESPONSE_SECURITY_PASSWORD.toString()))
            .andExpect(jsonPath("$.responseTxnDate").value(DEFAULT_RESPONSE_TXN_DATE.toString()))
            .andExpect(jsonPath("$.responseAuthStatus").value(DEFAULT_RESPONSE_AUTH_STATUS.toString()))
            .andExpect(jsonPath("$.responseSettlementType").value(DEFAULT_RESPONSE_SETTLEMENT_TYPE.toString()))
            .andExpect(jsonPath("$.responseAdditionalInfo1").value(DEFAULT_RESPONSE_ADDITIONAL_INFO_1.toString()))
            .andExpect(jsonPath("$.responseAdditionalInfo2").value(DEFAULT_RESPONSE_ADDITIONAL_INFO_2.toString()))
            .andExpect(jsonPath("$.responseAdditionalInfo3").value(DEFAULT_RESPONSE_ADDITIONAL_INFO_3.toString()))
            .andExpect(jsonPath("$.responseAdditionalInfo4").value(DEFAULT_RESPONSE_ADDITIONAL_INFO_4.toString()))
            .andExpect(jsonPath("$.responseAdditionalInfo5").value(DEFAULT_RESPONSE_ADDITIONAL_INFO_5.toString()))
            .andExpect(jsonPath("$.responseAdditionalInfo6").value(DEFAULT_RESPONSE_ADDITIONAL_INFO_6.toString()))
            .andExpect(jsonPath("$.responseAdditionalInfo7").value(DEFAULT_RESPONSE_ADDITIONAL_INFO_7.toString()))
            .andExpect(jsonPath("$.responseErrorStatus").value(DEFAULT_RESPONSE_ERROR_STATUS.toString()))
            .andExpect(jsonPath("$.responseErrorDescription").value(DEFAULT_RESPONSE_ERROR_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.responseCheckSum").value(DEFAULT_RESPONSE_CHECK_SUM.toString()))
            .andExpect(jsonPath("$.responseMsg").value(DEFAULT_RESPONSE_MSG.toString()))
            .andExpect(jsonPath("$.user").value(DEFAULT_USER.toString()))
            .andExpect(jsonPath("$.requestTxnDate").value(DEFAULT_REQUEST_TXN_DATE.toString()))
            .andExpect(jsonPath("$.requestTxnTime").value(DEFAULT_REQUEST_TXN_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPaymentRequestResponse() throws Exception {
        // Get the paymentRequestResponse
        restPaymentRequestResponseMockMvc.perform(get("/api/payment-request-responses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePaymentRequestResponse() throws Exception {
        // Initialize the database
        paymentRequestResponseRepository.saveAndFlush(paymentRequestResponse);

        int databaseSizeBeforeUpdate = paymentRequestResponseRepository.findAll().size();

        // Update the paymentRequestResponse
        PaymentRequestResponse updatedPaymentRequestResponse = paymentRequestResponseRepository.findById(paymentRequestResponse.getId()).get();
        // Disconnect from session so that the updates on updatedPaymentRequestResponse are not directly saved in db
        em.detach(updatedPaymentRequestResponse);
        updatedPaymentRequestResponse
            .requestMerchantID(UPDATED_REQUEST_MERCHANT_ID)
            .requestUniqueTransactionNo(UPDATED_REQUEST_UNIQUE_TRANSACTION_NO)
            .requestTxnAmount(UPDATED_REQUEST_TXN_AMOUNT)
            .requestCurrencyType(UPDATED_REQUEST_CURRENCY_TYPE)
            .requestTypeField1(UPDATED_REQUEST_TYPE_FIELD_1)
            .requestSecurityID(UPDATED_REQUEST_SECURITY_ID)
            .requestTypeField2(UPDATED_REQUEST_TYPE_FIELD_2)
            .requestTxtadditional1(UPDATED_REQUEST_TXTADDITIONAL_1)
            .requestTxtadditional2(UPDATED_REQUEST_TXTADDITIONAL_2)
            .requestTxtadditional3(UPDATED_REQUEST_TXTADDITIONAL_3)
            .requestTxtadditional4(UPDATED_REQUEST_TXTADDITIONAL_4)
            .requestTxtadditional5(UPDATED_REQUEST_TXTADDITIONAL_5)
            .requestTxtadditional6(UPDATED_REQUEST_TXTADDITIONAL_6)
            .requestTxtadditional7(UPDATED_REQUEST_TXTADDITIONAL_7)
            .requestRu(UPDATED_REQUEST_RU)
            .requestCheckSum(UPDATED_REQUEST_CHECK_SUM)
            .requestMsg(UPDATED_REQUEST_MSG)
            .responseMerchantID(UPDATED_RESPONSE_MERCHANT_ID)
            .responseUniqueTransactionNo(UPDATED_RESPONSE_UNIQUE_TRANSACTION_NO)
            .responseTxnReferenceNo(UPDATED_RESPONSE_TXN_REFERENCE_NO)
            .responseBankReferenceNo(UPDATED_RESPONSE_BANK_REFERENCE_NO)
            .responseTxnAmount(UPDATED_RESPONSE_TXN_AMOUNT)
            .responseBankID(UPDATED_RESPONSE_BANK_ID)
            .responseBankMerchantID(UPDATED_RESPONSE_BANK_MERCHANT_ID)
            .responseTxnType(UPDATED_RESPONSE_TXN_TYPE)
            .responseCurrencyName(UPDATED_RESPONSE_CURRENCY_NAME)
            .responseItemCode(UPDATED_RESPONSE_ITEM_CODE)
            .responseSecurityType(UPDATED_RESPONSE_SECURITY_TYPE)
            .responseSecurityID(UPDATED_RESPONSE_SECURITY_ID)
            .responseSecurityPassword(UPDATED_RESPONSE_SECURITY_PASSWORD)
            .responseTxnDate(UPDATED_RESPONSE_TXN_DATE)
            .responseAuthStatus(UPDATED_RESPONSE_AUTH_STATUS)
            .responseSettlementType(UPDATED_RESPONSE_SETTLEMENT_TYPE)
            .responseAdditionalInfo1(UPDATED_RESPONSE_ADDITIONAL_INFO_1)
            .responseAdditionalInfo2(UPDATED_RESPONSE_ADDITIONAL_INFO_2)
            .responseAdditionalInfo3(UPDATED_RESPONSE_ADDITIONAL_INFO_3)
            .responseAdditionalInfo4(UPDATED_RESPONSE_ADDITIONAL_INFO_4)
            .responseAdditionalInfo5(UPDATED_RESPONSE_ADDITIONAL_INFO_5)
            .responseAdditionalInfo6(UPDATED_RESPONSE_ADDITIONAL_INFO_6)
            .responseAdditionalInfo7(UPDATED_RESPONSE_ADDITIONAL_INFO_7)
            .responseErrorStatus(UPDATED_RESPONSE_ERROR_STATUS)
            .responseErrorDescription(UPDATED_RESPONSE_ERROR_DESCRIPTION)
            .responseCheckSum(UPDATED_RESPONSE_CHECK_SUM)
            .responseMsg(UPDATED_RESPONSE_MSG)
            .user(UPDATED_USER)
            .requestTxnDate(UPDATED_REQUEST_TXN_DATE)
            .requestTxnTime(UPDATED_REQUEST_TXN_TIME);
        PaymentRequestResponseDTO paymentRequestResponseDTO = paymentRequestResponseMapper.toDto(updatedPaymentRequestResponse);

        restPaymentRequestResponseMockMvc.perform(put("/api/payment-request-responses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymentRequestResponseDTO)))
            .andExpect(status().isOk());

        // Validate the PaymentRequestResponse in the database
        List<PaymentRequestResponse> paymentRequestResponseList = paymentRequestResponseRepository.findAll();
        assertThat(paymentRequestResponseList).hasSize(databaseSizeBeforeUpdate);
        PaymentRequestResponse testPaymentRequestResponse = paymentRequestResponseList.get(paymentRequestResponseList.size() - 1);
        assertThat(testPaymentRequestResponse.getRequestMerchantID()).isEqualTo(UPDATED_REQUEST_MERCHANT_ID);
        assertThat(testPaymentRequestResponse.getRequestUniqueTransactionNo()).isEqualTo(UPDATED_REQUEST_UNIQUE_TRANSACTION_NO);
        assertThat(testPaymentRequestResponse.getRequestTxnAmount()).isEqualTo(UPDATED_REQUEST_TXN_AMOUNT);
        assertThat(testPaymentRequestResponse.getRequestCurrencyType()).isEqualTo(UPDATED_REQUEST_CURRENCY_TYPE);
        assertThat(testPaymentRequestResponse.getRequestTypeField1()).isEqualTo(UPDATED_REQUEST_TYPE_FIELD_1);
        assertThat(testPaymentRequestResponse.getRequestSecurityID()).isEqualTo(UPDATED_REQUEST_SECURITY_ID);
        assertThat(testPaymentRequestResponse.getRequestTypeField2()).isEqualTo(UPDATED_REQUEST_TYPE_FIELD_2);
        assertThat(testPaymentRequestResponse.getRequestTxtadditional1()).isEqualTo(UPDATED_REQUEST_TXTADDITIONAL_1);
        assertThat(testPaymentRequestResponse.getRequestTxtadditional2()).isEqualTo(UPDATED_REQUEST_TXTADDITIONAL_2);
        assertThat(testPaymentRequestResponse.getRequestTxtadditional3()).isEqualTo(UPDATED_REQUEST_TXTADDITIONAL_3);
        assertThat(testPaymentRequestResponse.getRequestTxtadditional4()).isEqualTo(UPDATED_REQUEST_TXTADDITIONAL_4);
        assertThat(testPaymentRequestResponse.getRequestTxtadditional5()).isEqualTo(UPDATED_REQUEST_TXTADDITIONAL_5);
        assertThat(testPaymentRequestResponse.getRequestTxtadditional6()).isEqualTo(UPDATED_REQUEST_TXTADDITIONAL_6);
        assertThat(testPaymentRequestResponse.getRequestTxtadditional7()).isEqualTo(UPDATED_REQUEST_TXTADDITIONAL_7);
        assertThat(testPaymentRequestResponse.getRequestRu()).isEqualTo(UPDATED_REQUEST_RU);
        assertThat(testPaymentRequestResponse.getRequestCheckSum()).isEqualTo(UPDATED_REQUEST_CHECK_SUM);
        assertThat(testPaymentRequestResponse.getRequestMsg()).isEqualTo(UPDATED_REQUEST_MSG);
        assertThat(testPaymentRequestResponse.getResponseMerchantID()).isEqualTo(UPDATED_RESPONSE_MERCHANT_ID);
        assertThat(testPaymentRequestResponse.getResponseUniqueTransactionNo()).isEqualTo(UPDATED_RESPONSE_UNIQUE_TRANSACTION_NO);
        assertThat(testPaymentRequestResponse.getResponseTxnReferenceNo()).isEqualTo(UPDATED_RESPONSE_TXN_REFERENCE_NO);
        assertThat(testPaymentRequestResponse.getResponseBankReferenceNo()).isEqualTo(UPDATED_RESPONSE_BANK_REFERENCE_NO);
        assertThat(testPaymentRequestResponse.getResponseTxnAmount()).isEqualTo(UPDATED_RESPONSE_TXN_AMOUNT);
        assertThat(testPaymentRequestResponse.getResponseBankID()).isEqualTo(UPDATED_RESPONSE_BANK_ID);
        assertThat(testPaymentRequestResponse.getResponseBankMerchantID()).isEqualTo(UPDATED_RESPONSE_BANK_MERCHANT_ID);
        assertThat(testPaymentRequestResponse.getResponseTxnType()).isEqualTo(UPDATED_RESPONSE_TXN_TYPE);
        assertThat(testPaymentRequestResponse.getResponseCurrencyName()).isEqualTo(UPDATED_RESPONSE_CURRENCY_NAME);
        assertThat(testPaymentRequestResponse.getResponseItemCode()).isEqualTo(UPDATED_RESPONSE_ITEM_CODE);
        assertThat(testPaymentRequestResponse.getResponseSecurityType()).isEqualTo(UPDATED_RESPONSE_SECURITY_TYPE);
        assertThat(testPaymentRequestResponse.getResponseSecurityID()).isEqualTo(UPDATED_RESPONSE_SECURITY_ID);
        assertThat(testPaymentRequestResponse.getResponseSecurityPassword()).isEqualTo(UPDATED_RESPONSE_SECURITY_PASSWORD);
        assertThat(testPaymentRequestResponse.getResponseTxnDate()).isEqualTo(UPDATED_RESPONSE_TXN_DATE);
        assertThat(testPaymentRequestResponse.getResponseAuthStatus()).isEqualTo(UPDATED_RESPONSE_AUTH_STATUS);
        assertThat(testPaymentRequestResponse.getResponseSettlementType()).isEqualTo(UPDATED_RESPONSE_SETTLEMENT_TYPE);
        assertThat(testPaymentRequestResponse.getResponseAdditionalInfo1()).isEqualTo(UPDATED_RESPONSE_ADDITIONAL_INFO_1);
        assertThat(testPaymentRequestResponse.getResponseAdditionalInfo2()).isEqualTo(UPDATED_RESPONSE_ADDITIONAL_INFO_2);
        assertThat(testPaymentRequestResponse.getResponseAdditionalInfo3()).isEqualTo(UPDATED_RESPONSE_ADDITIONAL_INFO_3);
        assertThat(testPaymentRequestResponse.getResponseAdditionalInfo4()).isEqualTo(UPDATED_RESPONSE_ADDITIONAL_INFO_4);
        assertThat(testPaymentRequestResponse.getResponseAdditionalInfo5()).isEqualTo(UPDATED_RESPONSE_ADDITIONAL_INFO_5);
        assertThat(testPaymentRequestResponse.getResponseAdditionalInfo6()).isEqualTo(UPDATED_RESPONSE_ADDITIONAL_INFO_6);
        assertThat(testPaymentRequestResponse.getResponseAdditionalInfo7()).isEqualTo(UPDATED_RESPONSE_ADDITIONAL_INFO_7);
        assertThat(testPaymentRequestResponse.getResponseErrorStatus()).isEqualTo(UPDATED_RESPONSE_ERROR_STATUS);
        assertThat(testPaymentRequestResponse.getResponseErrorDescription()).isEqualTo(UPDATED_RESPONSE_ERROR_DESCRIPTION);
        assertThat(testPaymentRequestResponse.getResponseCheckSum()).isEqualTo(UPDATED_RESPONSE_CHECK_SUM);
        assertThat(testPaymentRequestResponse.getResponseMsg()).isEqualTo(UPDATED_RESPONSE_MSG);
        assertThat(testPaymentRequestResponse.getUser()).isEqualTo(UPDATED_USER);
        assertThat(testPaymentRequestResponse.getRequestTxnDate()).isEqualTo(UPDATED_REQUEST_TXN_DATE);
        assertThat(testPaymentRequestResponse.getRequestTxnTime()).isEqualTo(UPDATED_REQUEST_TXN_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingPaymentRequestResponse() throws Exception {
        int databaseSizeBeforeUpdate = paymentRequestResponseRepository.findAll().size();

        // Create the PaymentRequestResponse
        PaymentRequestResponseDTO paymentRequestResponseDTO = paymentRequestResponseMapper.toDto(paymentRequestResponse);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymentRequestResponseMockMvc.perform(put("/api/payment-request-responses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymentRequestResponseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PaymentRequestResponse in the database
        List<PaymentRequestResponse> paymentRequestResponseList = paymentRequestResponseRepository.findAll();
        assertThat(paymentRequestResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePaymentRequestResponse() throws Exception {
        // Initialize the database
        paymentRequestResponseRepository.saveAndFlush(paymentRequestResponse);

        int databaseSizeBeforeDelete = paymentRequestResponseRepository.findAll().size();

        // Delete the paymentRequestResponse
        restPaymentRequestResponseMockMvc.perform(delete("/api/payment-request-responses/{id}", paymentRequestResponse.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PaymentRequestResponse> paymentRequestResponseList = paymentRequestResponseRepository.findAll();
        assertThat(paymentRequestResponseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchPaymentRequestResponse() throws Exception {
        // Initialize the database
        paymentRequestResponseRepository.saveAndFlush(paymentRequestResponse);
        // Search the paymentRequestResponse
        restPaymentRequestResponseMockMvc.perform(get("/api/_search/payment-request-responses?query=id:" + paymentRequestResponse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paymentRequestResponse.getId().intValue())))
            .andExpect(jsonPath("$.[*].requestMerchantID").value(hasItem(DEFAULT_REQUEST_MERCHANT_ID)))
            .andExpect(jsonPath("$.[*].requestUniqueTransactionNo").value(hasItem(DEFAULT_REQUEST_UNIQUE_TRANSACTION_NO)))
            .andExpect(jsonPath("$.[*].requestTxnAmount").value(hasItem(DEFAULT_REQUEST_TXN_AMOUNT)))
            .andExpect(jsonPath("$.[*].requestCurrencyType").value(hasItem(DEFAULT_REQUEST_CURRENCY_TYPE)))
            .andExpect(jsonPath("$.[*].requestTypeField1").value(hasItem(DEFAULT_REQUEST_TYPE_FIELD_1)))
            .andExpect(jsonPath("$.[*].requestSecurityID").value(hasItem(DEFAULT_REQUEST_SECURITY_ID)))
            .andExpect(jsonPath("$.[*].requestTypeField2").value(hasItem(DEFAULT_REQUEST_TYPE_FIELD_2)))
            .andExpect(jsonPath("$.[*].requestTxtadditional1").value(hasItem(DEFAULT_REQUEST_TXTADDITIONAL_1)))
            .andExpect(jsonPath("$.[*].requestTxtadditional2").value(hasItem(DEFAULT_REQUEST_TXTADDITIONAL_2)))
            .andExpect(jsonPath("$.[*].requestTxtadditional3").value(hasItem(DEFAULT_REQUEST_TXTADDITIONAL_3)))
            .andExpect(jsonPath("$.[*].requestTxtadditional4").value(hasItem(DEFAULT_REQUEST_TXTADDITIONAL_4)))
            .andExpect(jsonPath("$.[*].requestTxtadditional5").value(hasItem(DEFAULT_REQUEST_TXTADDITIONAL_5)))
            .andExpect(jsonPath("$.[*].requestTxtadditional6").value(hasItem(DEFAULT_REQUEST_TXTADDITIONAL_6)))
            .andExpect(jsonPath("$.[*].requestTxtadditional7").value(hasItem(DEFAULT_REQUEST_TXTADDITIONAL_7)))
            .andExpect(jsonPath("$.[*].requestRu").value(hasItem(DEFAULT_REQUEST_RU)))
            .andExpect(jsonPath("$.[*].requestCheckSum").value(hasItem(DEFAULT_REQUEST_CHECK_SUM)))
            .andExpect(jsonPath("$.[*].requestMsg").value(hasItem(DEFAULT_REQUEST_MSG)))
            .andExpect(jsonPath("$.[*].responseMerchantID").value(hasItem(DEFAULT_RESPONSE_MERCHANT_ID)))
            .andExpect(jsonPath("$.[*].responseUniqueTransactionNo").value(hasItem(DEFAULT_RESPONSE_UNIQUE_TRANSACTION_NO)))
            .andExpect(jsonPath("$.[*].responseTxnReferenceNo").value(hasItem(DEFAULT_RESPONSE_TXN_REFERENCE_NO)))
            .andExpect(jsonPath("$.[*].responseBankReferenceNo").value(hasItem(DEFAULT_RESPONSE_BANK_REFERENCE_NO)))
            .andExpect(jsonPath("$.[*].responseTxnAmount").value(hasItem(DEFAULT_RESPONSE_TXN_AMOUNT)))
            .andExpect(jsonPath("$.[*].responseBankID").value(hasItem(DEFAULT_RESPONSE_BANK_ID)))
            .andExpect(jsonPath("$.[*].responseBankMerchantID").value(hasItem(DEFAULT_RESPONSE_BANK_MERCHANT_ID)))
            .andExpect(jsonPath("$.[*].responseTxnType").value(hasItem(DEFAULT_RESPONSE_TXN_TYPE)))
            .andExpect(jsonPath("$.[*].responseCurrencyName").value(hasItem(DEFAULT_RESPONSE_CURRENCY_NAME)))
            .andExpect(jsonPath("$.[*].responseItemCode").value(hasItem(DEFAULT_RESPONSE_ITEM_CODE)))
            .andExpect(jsonPath("$.[*].responseSecurityType").value(hasItem(DEFAULT_RESPONSE_SECURITY_TYPE)))
            .andExpect(jsonPath("$.[*].responseSecurityID").value(hasItem(DEFAULT_RESPONSE_SECURITY_ID)))
            .andExpect(jsonPath("$.[*].responseSecurityPassword").value(hasItem(DEFAULT_RESPONSE_SECURITY_PASSWORD)))
            .andExpect(jsonPath("$.[*].responseTxnDate").value(hasItem(DEFAULT_RESPONSE_TXN_DATE)))
            .andExpect(jsonPath("$.[*].responseAuthStatus").value(hasItem(DEFAULT_RESPONSE_AUTH_STATUS)))
            .andExpect(jsonPath("$.[*].responseSettlementType").value(hasItem(DEFAULT_RESPONSE_SETTLEMENT_TYPE)))
            .andExpect(jsonPath("$.[*].responseAdditionalInfo1").value(hasItem(DEFAULT_RESPONSE_ADDITIONAL_INFO_1)))
            .andExpect(jsonPath("$.[*].responseAdditionalInfo2").value(hasItem(DEFAULT_RESPONSE_ADDITIONAL_INFO_2)))
            .andExpect(jsonPath("$.[*].responseAdditionalInfo3").value(hasItem(DEFAULT_RESPONSE_ADDITIONAL_INFO_3)))
            .andExpect(jsonPath("$.[*].responseAdditionalInfo4").value(hasItem(DEFAULT_RESPONSE_ADDITIONAL_INFO_4)))
            .andExpect(jsonPath("$.[*].responseAdditionalInfo5").value(hasItem(DEFAULT_RESPONSE_ADDITIONAL_INFO_5)))
            .andExpect(jsonPath("$.[*].responseAdditionalInfo6").value(hasItem(DEFAULT_RESPONSE_ADDITIONAL_INFO_6)))
            .andExpect(jsonPath("$.[*].responseAdditionalInfo7").value(hasItem(DEFAULT_RESPONSE_ADDITIONAL_INFO_7)))
            .andExpect(jsonPath("$.[*].responseErrorStatus").value(hasItem(DEFAULT_RESPONSE_ERROR_STATUS)))
            .andExpect(jsonPath("$.[*].responseErrorDescription").value(hasItem(DEFAULT_RESPONSE_ERROR_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].responseCheckSum").value(hasItem(DEFAULT_RESPONSE_CHECK_SUM)))
            .andExpect(jsonPath("$.[*].responseMsg").value(hasItem(DEFAULT_RESPONSE_MSG)))
            .andExpect(jsonPath("$.[*].user").value(hasItem(DEFAULT_USER)))
            .andExpect(jsonPath("$.[*].requestTxnDate").value(hasItem(DEFAULT_REQUEST_TXN_DATE.toString())))
            .andExpect(jsonPath("$.[*].requestTxnTime").value(hasItem(DEFAULT_REQUEST_TXN_TIME)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymentRequestResponse.class);
        PaymentRequestResponse paymentRequestResponse1 = new PaymentRequestResponse();
        paymentRequestResponse1.setId(1L);
        PaymentRequestResponse paymentRequestResponse2 = new PaymentRequestResponse();
        paymentRequestResponse2.setId(paymentRequestResponse1.getId());
        assertThat(paymentRequestResponse1).isEqualTo(paymentRequestResponse2);
        paymentRequestResponse2.setId(2L);
        assertThat(paymentRequestResponse1).isNotEqualTo(paymentRequestResponse2);
        paymentRequestResponse1.setId(null);
        assertThat(paymentRequestResponse1).isNotEqualTo(paymentRequestResponse2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymentRequestResponseDTO.class);
        PaymentRequestResponseDTO paymentRequestResponseDTO1 = new PaymentRequestResponseDTO();
        paymentRequestResponseDTO1.setId(1L);
        PaymentRequestResponseDTO paymentRequestResponseDTO2 = new PaymentRequestResponseDTO();
        assertThat(paymentRequestResponseDTO1).isNotEqualTo(paymentRequestResponseDTO2);
        paymentRequestResponseDTO2.setId(paymentRequestResponseDTO1.getId());
        assertThat(paymentRequestResponseDTO1).isEqualTo(paymentRequestResponseDTO2);
        paymentRequestResponseDTO2.setId(2L);
        assertThat(paymentRequestResponseDTO1).isNotEqualTo(paymentRequestResponseDTO2);
        paymentRequestResponseDTO1.setId(null);
        assertThat(paymentRequestResponseDTO1).isNotEqualTo(paymentRequestResponseDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(paymentRequestResponseMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(paymentRequestResponseMapper.fromId(null)).isNull();
    }
}
