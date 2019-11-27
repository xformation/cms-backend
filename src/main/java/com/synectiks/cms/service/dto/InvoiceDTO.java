package com.synectiks.cms.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.synectiks.cms.domain.enumeration.ModeOfPayment;
import com.synectiks.cms.domain.enumeration.InvoicePaymentStatus;

/**
 * A DTO for the Invoice entity.
 */
public class InvoiceDTO implements Serializable {

    private Long id;

    @NotNull
    private String invoiceNumber;

    @NotNull
    private Long amountPaid;

    @NotNull
    private LocalDate paymentDate;

    @NotNull
    private LocalDate nextPaymentDate;

    @NotNull
    private Long outStandingAmount;

    @NotNull
    private ModeOfPayment modeOfPayment;

    private Long chequeNumber;

    private Long demandDraftNumber;

    private String onlineTxnRefNumber;

    @NotNull
    private InvoicePaymentStatus paymentStatus;

    @NotNull
    private String comments;

    @NotNull
    private String updatedBy;

    @NotNull
    private LocalDate updatedOn;


    private Long feeCategoryId;

    private Long feeDetailsId;

    private Long dueDateId;

    private Long paymentRemainderId;

    private Long collegeId;

    private Long branchId;

    private Long studentId;

    private Long academicYearId;

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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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
            ", feeCategory=" + getFeeCategoryId() +
            ", feeDetails=" + getFeeDetailsId() +
            ", dueDate=" + getDueDateId() +
            ", paymentRemainder=" + getPaymentRemainderId() +
            ", college=" + getCollegeId() +
            ", branch=" + getBranchId() +
            ", student=" + getStudentId() +
            ", academicYear=" + getAcademicYearId() +
            "}";
    }
}
