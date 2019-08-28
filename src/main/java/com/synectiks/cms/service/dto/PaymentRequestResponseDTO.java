package com.synectiks.cms.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.synectiks.cms.domain.PaymentRequestResponse} entity.
 */
public class PaymentRequestResponseDTO implements Serializable {

    private Long id;

    private String requestMerchantID;

    private String requestUniqueTransactionNo;

    private String requestTxnAmount;

    private String requestCurrencyType;

    private String requestTypeField1;

    private String requestSecurityID;

    private String requestTypeField2;

    private String requestTxtadditional1;

    private String requestTxtadditional2;

    private String requestTxtadditional3;

    private String requestTxtadditional4;

    private String requestTxtadditional5;

    private String requestTxtadditional6;

    private String requestTxtadditional7;

    private String requestRu;

    private String requestCheckSum;

    private String requestMsg;

    private String responseMerchantID;

    private String responseUniqueTransactionNo;

    private String responseTxnReferenceNo;

    private String responseBankReferenceNo;

    private String responseTxnAmount;

    private String responseBankID;

    private String responseBankMerchantID;

    private String responseTxnType;

    private String responseCurrencyName;

    private String responseItemCode;

    private String responseSecurityType;

    private String responseSecurityID;

    private String responseSecurityPassword;

    private String responseTxnDate;

    private String responseAuthStatus;

    private String responseSettlementType;

    private String responseAdditionalInfo1;

    private String responseAdditionalInfo2;

    private String responseAdditionalInfo3;

    private String responseAdditionalInfo4;

    private String responseAdditionalInfo5;

    private String responseAdditionalInfo6;

    private String responseAdditionalInfo7;

    private String responseErrorStatus;

    private String responseErrorDescription;

    private String responseCheckSum;

    private String responseMsg;

    private String user;

    private LocalDate requestTxnDate;

    private String requestTxnTime;


    private Long branchId;

    private Long academicYearId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestMerchantID() {
        return requestMerchantID;
    }

    public void setRequestMerchantID(String requestMerchantID) {
        this.requestMerchantID = requestMerchantID;
    }

    public String getRequestUniqueTransactionNo() {
        return requestUniqueTransactionNo;
    }

    public void setRequestUniqueTransactionNo(String requestUniqueTransactionNo) {
        this.requestUniqueTransactionNo = requestUniqueTransactionNo;
    }

    public String getRequestTxnAmount() {
        return requestTxnAmount;
    }

    public void setRequestTxnAmount(String requestTxnAmount) {
        this.requestTxnAmount = requestTxnAmount;
    }

    public String getRequestCurrencyType() {
        return requestCurrencyType;
    }

    public void setRequestCurrencyType(String requestCurrencyType) {
        this.requestCurrencyType = requestCurrencyType;
    }

    public String getRequestTypeField1() {
        return requestTypeField1;
    }

    public void setRequestTypeField1(String requestTypeField1) {
        this.requestTypeField1 = requestTypeField1;
    }

    public String getRequestSecurityID() {
        return requestSecurityID;
    }

    public void setRequestSecurityID(String requestSecurityID) {
        this.requestSecurityID = requestSecurityID;
    }

    public String getRequestTypeField2() {
        return requestTypeField2;
    }

    public void setRequestTypeField2(String requestTypeField2) {
        this.requestTypeField2 = requestTypeField2;
    }

    public String getRequestTxtadditional1() {
        return requestTxtadditional1;
    }

    public void setRequestTxtadditional1(String requestTxtadditional1) {
        this.requestTxtadditional1 = requestTxtadditional1;
    }

    public String getRequestTxtadditional2() {
        return requestTxtadditional2;
    }

    public void setRequestTxtadditional2(String requestTxtadditional2) {
        this.requestTxtadditional2 = requestTxtadditional2;
    }

    public String getRequestTxtadditional3() {
        return requestTxtadditional3;
    }

    public void setRequestTxtadditional3(String requestTxtadditional3) {
        this.requestTxtadditional3 = requestTxtadditional3;
    }

    public String getRequestTxtadditional4() {
        return requestTxtadditional4;
    }

    public void setRequestTxtadditional4(String requestTxtadditional4) {
        this.requestTxtadditional4 = requestTxtadditional4;
    }

    public String getRequestTxtadditional5() {
        return requestTxtadditional5;
    }

    public void setRequestTxtadditional5(String requestTxtadditional5) {
        this.requestTxtadditional5 = requestTxtadditional5;
    }

    public String getRequestTxtadditional6() {
        return requestTxtadditional6;
    }

    public void setRequestTxtadditional6(String requestTxtadditional6) {
        this.requestTxtadditional6 = requestTxtadditional6;
    }

    public String getRequestTxtadditional7() {
        return requestTxtadditional7;
    }

    public void setRequestTxtadditional7(String requestTxtadditional7) {
        this.requestTxtadditional7 = requestTxtadditional7;
    }

    public String getRequestRu() {
        return requestRu;
    }

    public void setRequestRu(String requestRu) {
        this.requestRu = requestRu;
    }

    public String getRequestCheckSum() {
        return requestCheckSum;
    }

    public void setRequestCheckSum(String requestCheckSum) {
        this.requestCheckSum = requestCheckSum;
    }

    public String getRequestMsg() {
        return requestMsg;
    }

    public void setRequestMsg(String requestMsg) {
        this.requestMsg = requestMsg;
    }

    public String getResponseMerchantID() {
        return responseMerchantID;
    }

    public void setResponseMerchantID(String responseMerchantID) {
        this.responseMerchantID = responseMerchantID;
    }

    public String getResponseUniqueTransactionNo() {
        return responseUniqueTransactionNo;
    }

    public void setResponseUniqueTransactionNo(String responseUniqueTransactionNo) {
        this.responseUniqueTransactionNo = responseUniqueTransactionNo;
    }

    public String getResponseTxnReferenceNo() {
        return responseTxnReferenceNo;
    }

    public void setResponseTxnReferenceNo(String responseTxnReferenceNo) {
        this.responseTxnReferenceNo = responseTxnReferenceNo;
    }

    public String getResponseBankReferenceNo() {
        return responseBankReferenceNo;
    }

    public void setResponseBankReferenceNo(String responseBankReferenceNo) {
        this.responseBankReferenceNo = responseBankReferenceNo;
    }

    public String getResponseTxnAmount() {
        return responseTxnAmount;
    }

    public void setResponseTxnAmount(String responseTxnAmount) {
        this.responseTxnAmount = responseTxnAmount;
    }

    public String getResponseBankID() {
        return responseBankID;
    }

    public void setResponseBankID(String responseBankID) {
        this.responseBankID = responseBankID;
    }

    public String getResponseBankMerchantID() {
        return responseBankMerchantID;
    }

    public void setResponseBankMerchantID(String responseBankMerchantID) {
        this.responseBankMerchantID = responseBankMerchantID;
    }

    public String getResponseTxnType() {
        return responseTxnType;
    }

    public void setResponseTxnType(String responseTxnType) {
        this.responseTxnType = responseTxnType;
    }

    public String getResponseCurrencyName() {
        return responseCurrencyName;
    }

    public void setResponseCurrencyName(String responseCurrencyName) {
        this.responseCurrencyName = responseCurrencyName;
    }

    public String getResponseItemCode() {
        return responseItemCode;
    }

    public void setResponseItemCode(String responseItemCode) {
        this.responseItemCode = responseItemCode;
    }

    public String getResponseSecurityType() {
        return responseSecurityType;
    }

    public void setResponseSecurityType(String responseSecurityType) {
        this.responseSecurityType = responseSecurityType;
    }

    public String getResponseSecurityID() {
        return responseSecurityID;
    }

    public void setResponseSecurityID(String responseSecurityID) {
        this.responseSecurityID = responseSecurityID;
    }

    public String getResponseSecurityPassword() {
        return responseSecurityPassword;
    }

    public void setResponseSecurityPassword(String responseSecurityPassword) {
        this.responseSecurityPassword = responseSecurityPassword;
    }

    public String getResponseTxnDate() {
        return responseTxnDate;
    }

    public void setResponseTxnDate(String responseTxnDate) {
        this.responseTxnDate = responseTxnDate;
    }

    public String getResponseAuthStatus() {
        return responseAuthStatus;
    }

    public void setResponseAuthStatus(String responseAuthStatus) {
        this.responseAuthStatus = responseAuthStatus;
    }

    public String getResponseSettlementType() {
        return responseSettlementType;
    }

    public void setResponseSettlementType(String responseSettlementType) {
        this.responseSettlementType = responseSettlementType;
    }

    public String getResponseAdditionalInfo1() {
        return responseAdditionalInfo1;
    }

    public void setResponseAdditionalInfo1(String responseAdditionalInfo1) {
        this.responseAdditionalInfo1 = responseAdditionalInfo1;
    }

    public String getResponseAdditionalInfo2() {
        return responseAdditionalInfo2;
    }

    public void setResponseAdditionalInfo2(String responseAdditionalInfo2) {
        this.responseAdditionalInfo2 = responseAdditionalInfo2;
    }

    public String getResponseAdditionalInfo3() {
        return responseAdditionalInfo3;
    }

    public void setResponseAdditionalInfo3(String responseAdditionalInfo3) {
        this.responseAdditionalInfo3 = responseAdditionalInfo3;
    }

    public String getResponseAdditionalInfo4() {
        return responseAdditionalInfo4;
    }

    public void setResponseAdditionalInfo4(String responseAdditionalInfo4) {
        this.responseAdditionalInfo4 = responseAdditionalInfo4;
    }

    public String getResponseAdditionalInfo5() {
        return responseAdditionalInfo5;
    }

    public void setResponseAdditionalInfo5(String responseAdditionalInfo5) {
        this.responseAdditionalInfo5 = responseAdditionalInfo5;
    }

    public String getResponseAdditionalInfo6() {
        return responseAdditionalInfo6;
    }

    public void setResponseAdditionalInfo6(String responseAdditionalInfo6) {
        this.responseAdditionalInfo6 = responseAdditionalInfo6;
    }

    public String getResponseAdditionalInfo7() {
        return responseAdditionalInfo7;
    }

    public void setResponseAdditionalInfo7(String responseAdditionalInfo7) {
        this.responseAdditionalInfo7 = responseAdditionalInfo7;
    }

    public String getResponseErrorStatus() {
        return responseErrorStatus;
    }

    public void setResponseErrorStatus(String responseErrorStatus) {
        this.responseErrorStatus = responseErrorStatus;
    }

    public String getResponseErrorDescription() {
        return responseErrorDescription;
    }

    public void setResponseErrorDescription(String responseErrorDescription) {
        this.responseErrorDescription = responseErrorDescription;
    }

    public String getResponseCheckSum() {
        return responseCheckSum;
    }

    public void setResponseCheckSum(String responseCheckSum) {
        this.responseCheckSum = responseCheckSum;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public LocalDate getRequestTxnDate() {
        return requestTxnDate;
    }

    public void setRequestTxnDate(LocalDate requestTxnDate) {
        this.requestTxnDate = requestTxnDate;
    }

    public String getRequestTxnTime() {
        return requestTxnTime;
    }

    public void setRequestTxnTime(String requestTxnTime) {
        this.requestTxnTime = requestTxnTime;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PaymentRequestResponseDTO paymentRequestResponseDTO = (PaymentRequestResponseDTO) o;
        if (paymentRequestResponseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), paymentRequestResponseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PaymentRequestResponseDTO{" +
            "id=" + getId() +
            ", requestMerchantID='" + getRequestMerchantID() + "'" +
            ", requestUniqueTransactionNo='" + getRequestUniqueTransactionNo() + "'" +
            ", requestTxnAmount='" + getRequestTxnAmount() + "'" +
            ", requestCurrencyType='" + getRequestCurrencyType() + "'" +
            ", requestTypeField1='" + getRequestTypeField1() + "'" +
            ", requestSecurityID='" + getRequestSecurityID() + "'" +
            ", requestTypeField2='" + getRequestTypeField2() + "'" +
            ", requestTxtadditional1='" + getRequestTxtadditional1() + "'" +
            ", requestTxtadditional2='" + getRequestTxtadditional2() + "'" +
            ", requestTxtadditional3='" + getRequestTxtadditional3() + "'" +
            ", requestTxtadditional4='" + getRequestTxtadditional4() + "'" +
            ", requestTxtadditional5='" + getRequestTxtadditional5() + "'" +
            ", requestTxtadditional6='" + getRequestTxtadditional6() + "'" +
            ", requestTxtadditional7='" + getRequestTxtadditional7() + "'" +
            ", requestRu='" + getRequestRu() + "'" +
            ", requestCheckSum='" + getRequestCheckSum() + "'" +
            ", requestMsg='" + getRequestMsg() + "'" +
            ", responseMerchantID='" + getResponseMerchantID() + "'" +
            ", responseUniqueTransactionNo='" + getResponseUniqueTransactionNo() + "'" +
            ", responseTxnReferenceNo='" + getResponseTxnReferenceNo() + "'" +
            ", responseBankReferenceNo='" + getResponseBankReferenceNo() + "'" +
            ", responseTxnAmount='" + getResponseTxnAmount() + "'" +
            ", responseBankID='" + getResponseBankID() + "'" +
            ", responseBankMerchantID='" + getResponseBankMerchantID() + "'" +
            ", responseTxnType='" + getResponseTxnType() + "'" +
            ", responseCurrencyName='" + getResponseCurrencyName() + "'" +
            ", responseItemCode='" + getResponseItemCode() + "'" +
            ", responseSecurityType='" + getResponseSecurityType() + "'" +
            ", responseSecurityID='" + getResponseSecurityID() + "'" +
            ", responseSecurityPassword='" + getResponseSecurityPassword() + "'" +
            ", responseTxnDate='" + getResponseTxnDate() + "'" +
            ", responseAuthStatus='" + getResponseAuthStatus() + "'" +
            ", responseSettlementType='" + getResponseSettlementType() + "'" +
            ", responseAdditionalInfo1='" + getResponseAdditionalInfo1() + "'" +
            ", responseAdditionalInfo2='" + getResponseAdditionalInfo2() + "'" +
            ", responseAdditionalInfo3='" + getResponseAdditionalInfo3() + "'" +
            ", responseAdditionalInfo4='" + getResponseAdditionalInfo4() + "'" +
            ", responseAdditionalInfo5='" + getResponseAdditionalInfo5() + "'" +
            ", responseAdditionalInfo6='" + getResponseAdditionalInfo6() + "'" +
            ", responseAdditionalInfo7='" + getResponseAdditionalInfo7() + "'" +
            ", responseErrorStatus='" + getResponseErrorStatus() + "'" +
            ", responseErrorDescription='" + getResponseErrorDescription() + "'" +
            ", responseCheckSum='" + getResponseCheckSum() + "'" +
            ", responseMsg='" + getResponseMsg() + "'" +
            ", user='" + getUser() + "'" +
            ", requestTxnDate='" + getRequestTxnDate() + "'" +
            ", requestTxnTime='" + getRequestTxnTime() + "'" +
            ", branch=" + getBranchId() +
            ", academicYear=" + getAcademicYearId() +
            "}";
    }
}
