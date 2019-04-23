package com.synectiks.cms.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import com.synectiks.cms.domain.enumeration.ModeOfPayment;

import com.synectiks.cms.domain.enumeration.InvoicePaymentStatus;

/**
 * A Invoice.
 */
@Entity
@Table(name = "invoice")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "invoice")
public class Invoice implements Serializable {

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
    private Date paymentDate;

    @NotNull
    @Column(name = "next_payment_date", nullable = false)
    private Date nextPaymentDate;

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
    private Date updatedOn;

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
    private College college;

    @ManyToOne
    @JsonIgnoreProperties("invoices")
    private Branch branch;

    @ManyToOne
    @JsonIgnoreProperties("invoices")
    private Student student;

    @ManyToOne
    @JsonIgnoreProperties("invoices")
    private AcademicYear academicYear;

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

    public Date getPaymentDate() {
        return paymentDate;
    }

    public Invoice paymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
        return this;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getNextPaymentDate() {
        return nextPaymentDate;
    }

    public Invoice nextPaymentDate(Date nextPaymentDate) {
        this.nextPaymentDate = nextPaymentDate;
        return this;
    }

    public void setNextPaymentDate(Date nextPaymentDate) {
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

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public Invoice updatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
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

    public College getCollege() {
        return college;
    }

    public Invoice college(College college) {
        this.college = college;
        return this;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public Branch getBranch() {
        return branch;
    }

    public Invoice branch(Branch branch) {
        this.branch = branch;
        return this;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
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

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public Invoice academicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
        return this;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
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
            "}";
    }
}
