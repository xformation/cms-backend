package com.synectiks.cms.service.dto;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.synectiks.cms.domain.enumeration.InvoicePaymentStatus;
import com.synectiks.cms.domain.enumeration.ModeOfPayment;

/**
 * A DTO for the Invoice entity.
 */
public class InvoiceDTO implements Serializable {

    private Long id;

    private String invoiceNumber;

    private Long amountPaid;

    private LocalDate paymentDate;

    private LocalDate nextPaymentDate;

    private Long outStandingAmount;

    private ModeOfPayment modeOfPayment;

    private Long chequeNumber;

    private Long demandDraftNumber;

    private String onlineTxnRefNumber;

    private InvoicePaymentStatus paymentStatus;

    private String comments;

    private String updatedBy;

    private LocalDate updatedOn;

    private Long collegeId;

    private Long branchId;

    private Long academicYearId;


    private Long feeCategoryId;

    private Long feeDetailsId;

    private Long dueDateId;

    private Long paymentRemainderId;

    private Long studentId;

    private String bank;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Long getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Long amountPaid) {
        this.amountPaid = amountPaid;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDate getNextPaymentDate() {
        return nextPaymentDate;
    }

    public void setNextPaymentDate(LocalDate nextPaymentDate) {
        this.nextPaymentDate = nextPaymentDate;
    }

    public Long getOutStandingAmount() {
        return outStandingAmount;
    }

    public void setOutStandingAmount(Long outStandingAmount) {
        this.outStandingAmount = outStandingAmount;
    }

    public ModeOfPayment getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(ModeOfPayment modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public Long getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(Long chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public Long getDemandDraftNumber() {
        return demandDraftNumber;
    }

    public void setDemandDraftNumber(Long demandDraftNumber) {
        this.demandDraftNumber = demandDraftNumber;
    }

    public String getOnlineTxnRefNumber() {
        return onlineTxnRefNumber;
    }

    public void setOnlineTxnRefNumber(String onlineTxnRefNumber) {
        this.onlineTxnRefNumber = onlineTxnRefNumber;
    }

    public InvoicePaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(InvoicePaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
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

    public Long getFeeCategoryId() {
        return feeCategoryId;
    }

    public void setFeeCategoryId(Long feeCategoryId) {
        this.feeCategoryId = feeCategoryId;
    }

    public Long getFeeDetailsId() {
        return feeDetailsId;
    }

    public void setFeeDetailsId(Long feeDetailsId) {
        this.feeDetailsId = feeDetailsId;
    }

    public Long getDueDateId() {
        return dueDateId;
    }

    public void setDueDateId(Long dueDateId) {
        this.dueDateId = dueDateId;
    }

    public Long getPaymentRemainderId() {
        return paymentRemainderId;
    }

    public void setPaymentRemainderId(Long paymentRemainderId) {
        this.paymentRemainderId = paymentRemainderId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InvoiceDTO invoiceDTO = (InvoiceDTO) o;
        if (invoiceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), invoiceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InvoiceDTO{" +
            "id=" + getId() +
            ", invoiceNumber='" + getInvoiceNumber() + "'" +
            ", amountPaid=" + getAmountPaid() +
            ", paymentDate='" + getPaymentDate() + "'" +
            ", nextPaymentDate='" + getNextPaymentDate() + "'" +
            ", outStandingAmount=" + getOutStandingAmount() +
            ", modeOfPayment='" + getModeOfPayment() + "'" +
            ", chequeNumber=" + getChequeNumber() +
            ", demandDraftNumber=" + getDemandDraftNumber() +
            ", onlineTxnRefNumber='" + getOnlineTxnRefNumber() + "'" +
            ", paymentStatus='" + getPaymentStatus() + "'" +
            ", comments='" + getComments() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            ", collegeId=" + getCollegeId() +
            ", branchId=" + getBranchId() +
            ", academicYearId=" + getAcademicYearId() +
            ", feeCategory=" + getFeeCategoryId() +
            ", feeDetails=" + getFeeDetailsId() +
            ", dueDate=" + getDueDateId() +
            ", paymentRemainder=" + getPaymentRemainderId() +
            ", student=" + getStudentId() +
            "}";
    }

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}
}
