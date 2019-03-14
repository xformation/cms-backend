package com.synectiks.cms.graphql.types.Invoice;

import com.synectiks.cms.domain.enumeration.InvoicePaymentStatus;
import com.synectiks.cms.domain.enumeration.ModeOfPayment;
import com.synectiks.cms.domain.enumeration.Status;

import java.util.Date;
import java.util.Objects;

public class AbstractInvoiceInput {
    private Long id;
    private String invoiceNumber;
    private Long amountPaid;
    private Date paymentDate;
    private Date nextPaymentDate;
    private Long outStandingAmount;
    private ModeOfPayment modeOfPayment;
    private Long chequeNumber;
    private Long demandDraftNumber;
    private String onlineTxnRefNumber;
    private InvoicePaymentStatus paymentStatus;
    private String comments;
    private String updatedBy;
    private Date updatedOn;

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

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getNextPaymentDate() {
        return nextPaymentDate;
    }

    public void setNextPaymentDate(Date nextPaymentDate) {
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

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractInvoiceInput)) return false;
        AbstractInvoiceInput that = (AbstractInvoiceInput) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getInvoiceNumber(), that.getInvoiceNumber()) &&
            Objects.equals(getAmountPaid(), that.getAmountPaid()) &&
            Objects.equals(getPaymentDate(), that.getPaymentDate()) &&
            Objects.equals(getNextPaymentDate(), that.getNextPaymentDate()) &&
            Objects.equals(getOutStandingAmount(), that.getOutStandingAmount()) &&
            getModeOfPayment() == that.getModeOfPayment() &&
            Objects.equals(getChequeNumber(), that.getChequeNumber()) &&
            Objects.equals(getDemandDraftNumber(), that.getDemandDraftNumber()) &&
            Objects.equals(getOnlineTxnRefNumber(), that.getOnlineTxnRefNumber()) &&
            getPaymentStatus() == that.getPaymentStatus() &&
            Objects.equals(getComments(), that.getComments()) &&
            Objects.equals(getUpdatedBy(), that.getUpdatedBy()) &&
            Objects.equals(getUpdatedOn(), that.getUpdatedOn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getInvoiceNumber(), getAmountPaid(), getPaymentDate(), getNextPaymentDate(), getOutStandingAmount(), getModeOfPayment(), getChequeNumber(), getDemandDraftNumber(), getOnlineTxnRefNumber(), getPaymentStatus(), getComments(), getUpdatedBy(), getUpdatedOn());
    }

    @Override
    public String toString() {
        return "AbstractInvoiceInput{" +
            "id=" + id +
            ", invoiceNumber='" + invoiceNumber + '\'' +
            ", amountPaid=" + amountPaid +
            ", paymentDate=" + paymentDate +
            ", nextPaymentDate=" + nextPaymentDate +
            ", outStandingAmount=" + outStandingAmount +
            ", modeOfPayment=" + modeOfPayment +
            ", chequeNumber=" + chequeNumber +
            ", demandDraftNumber=" + demandDraftNumber +
            ", onlineTxnRefNumber='" + onlineTxnRefNumber + '\'' +
            ", paymentStatus=" + paymentStatus +
            ", comments='" + comments + '\'' +
            ", updatedBy='" + updatedBy + '\'' +
            ", updatedOn=" + updatedOn +
            '}';
    }
}
