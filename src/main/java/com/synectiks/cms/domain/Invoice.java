package com.synectiks.cms.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.synectiks.cms.utils.IESEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.synectiks.cms.domain.enumeration.ModeOfPayment;

import com.synectiks.cms.domain.enumeration.InvoicePaymentStatus;

/**
 * A Invoice.
 */
@Entity
@Table(name = "invoice")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Invoice implements Serializable, IESEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "invoice_number", nullable = false)
    private String invoiceNumber;

    @NotNull
    @Column(name = "amount_paid", nullable = false)
    private Long amountPaid;

    @NotNull
    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    @NotNull
    @Column(name = "next_payment_date", nullable = false)
    private LocalDate nextPaymentDate;

    @NotNull
    @Column(name = "out_standing_amount", nullable = false)
    private Long outStandingAmount;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "mode_of_payment", nullable = false)
    private ModeOfPayment modeOfPayment;

    @Column(name = "cheque_number")
    private Long chequeNumber;

    @Column(name = "demand_draft_number")
    private Long demandDraftNumber;

    @Column(name = "online_txn_ref_number")
    private String onlineTxnRefNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private InvoicePaymentStatus paymentStatus;

    @NotNull
    @Column(name = "comments", nullable = false)
    private String comments;

    @NotNull
    @Column(name = "updated_by", nullable = false)
    private String updatedBy;

    @NotNull
    @Column(name = "updated_on", nullable = false)
    private LocalDate updatedOn;

    @Column(name = "college_id")
    private Long collegeId;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "academic_year_id")
    private Long academicYearId;

    @ManyToOne
    @JsonIgnoreProperties("invoices")
    private FeeCategory feeCategory;

    @ManyToOne
    @JsonIgnoreProperties("invoices")
    private FeeDetails feeDetails;

    @ManyToOne
    @JsonIgnoreProperties("invoices")
    private DueDate dueDate;

    @ManyToOne
    @JsonIgnoreProperties("invoices")
    private PaymentRemainder paymentRemainder;

    @ManyToOne
    @JsonIgnoreProperties("invoices")
    private Student student;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public Invoice invoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        return this;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Long getAmountPaid() {
        return amountPaid;
    }

    public Invoice amountPaid(Long amountPaid) {
        this.amountPaid = amountPaid;
        return this;
    }

    public void setAmountPaid(Long amountPaid) {
        this.amountPaid = amountPaid;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public Invoice paymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
        return this;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDate getNextPaymentDate() {
        return nextPaymentDate;
    }

    public Invoice nextPaymentDate(LocalDate nextPaymentDate) {
        this.nextPaymentDate = nextPaymentDate;
        return this;
    }

    public void setNextPaymentDate(LocalDate nextPaymentDate) {
        this.nextPaymentDate = nextPaymentDate;
    }

    public Long getOutStandingAmount() {
        return outStandingAmount;
    }

    public Invoice outStandingAmount(Long outStandingAmount) {
        this.outStandingAmount = outStandingAmount;
        return this;
    }

    public void setOutStandingAmount(Long outStandingAmount) {
        this.outStandingAmount = outStandingAmount;
    }

    public ModeOfPayment getModeOfPayment() {
        return modeOfPayment;
    }

    public Invoice modeOfPayment(ModeOfPayment modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
        return this;
    }

    public void setModeOfPayment(ModeOfPayment modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public Long getChequeNumber() {
        return chequeNumber;
    }

    public Invoice chequeNumber(Long chequeNumber) {
        this.chequeNumber = chequeNumber;
        return this;
    }

    public void setChequeNumber(Long chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public Long getDemandDraftNumber() {
        return demandDraftNumber;
    }

    public Invoice demandDraftNumber(Long demandDraftNumber) {
        this.demandDraftNumber = demandDraftNumber;
        return this;
    }

    public void setDemandDraftNumber(Long demandDraftNumber) {
        this.demandDraftNumber = demandDraftNumber;
    }

    public String getOnlineTxnRefNumber() {
        return onlineTxnRefNumber;
    }

    public Invoice onlineTxnRefNumber(String onlineTxnRefNumber) {
        this.onlineTxnRefNumber = onlineTxnRefNumber;
        return this;
    }

    public void setOnlineTxnRefNumber(String onlineTxnRefNumber) {
        this.onlineTxnRefNumber = onlineTxnRefNumber;
    }

    public InvoicePaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public Invoice paymentStatus(InvoicePaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
        return this;
    }

    public void setPaymentStatus(InvoicePaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getComments() {
        return comments;
    }

    public Invoice comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Invoice updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public Invoice updatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public Invoice collegeId(Long collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public Invoice branchId(Long branchId) {
        this.branchId = branchId;
        return this;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getAcademicYearId() {
        return academicYearId;
    }

    public Invoice academicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
        return this;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }

    public FeeCategory getFeeCategory() {
        return feeCategory;
    }

    public Invoice feeCategory(FeeCategory feeCategory) {
        this.feeCategory = feeCategory;
        return this;
    }

    public void setFeeCategory(FeeCategory feeCategory) {
        this.feeCategory = feeCategory;
    }

    public FeeDetails getFeeDetails() {
        return feeDetails;
    }

    public Invoice feeDetails(FeeDetails feeDetails) {
        this.feeDetails = feeDetails;
        return this;
    }

    public void setFeeDetails(FeeDetails feeDetails) {
        this.feeDetails = feeDetails;
    }

    public DueDate getDueDate() {
        return dueDate;
    }

    public Invoice dueDate(DueDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public void setDueDate(DueDate dueDate) {
        this.dueDate = dueDate;
    }

    public PaymentRemainder getPaymentRemainder() {
        return paymentRemainder;
    }

    public Invoice paymentRemainder(PaymentRemainder paymentRemainder) {
        this.paymentRemainder = paymentRemainder;
        return this;
    }

    public void setPaymentRemainder(PaymentRemainder paymentRemainder) {
        this.paymentRemainder = paymentRemainder;
    }

    public Student getStudent() {
        return student;
    }

    public Invoice student(Student student) {
        this.student = student;
        return this;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Invoice invoice = (Invoice) o;
        if (invoice.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), invoice.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Invoice{" +
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
            "}";
    }
}
