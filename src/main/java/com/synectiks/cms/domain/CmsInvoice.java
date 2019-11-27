package com.synectiks.cms.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.synectiks.cms.domain.enumeration.InvoicePaymentStatus;
import com.synectiks.cms.domain.enumeration.ModeOfPayment;

public class CmsInvoice {

	private Long totalInvoice = 0L;
	private Long totalPaidInvoice = 0L;
	private Long totalUnPaidInvoice = 0L;
	private Long totalCanceledInvoice = 0L; 
	
	private Long id;
    private String invoiceNumber;
    private Long amountPaid;
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate paymentDate;
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate nextPaymentDate;
    private Long outStandingAmount;
    private ModeOfPayment modeOfPayment;
    private Long chequeNumber;
    private Long demandDraftNumber;
    private String onlineTxnRefNumber;
    private InvoicePaymentStatus paymentStatus;
    private String comments;
    private String updatedBy;
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate updatedOn;
    private FeeCategory feeCategory;
    private FeeDetails feeDetails;
    private DueDate dueDate;
    private PaymentRemainder paymentRemainder;
    private College college;
    private Branch branch;
    private Student student;
    private AcademicYear academicYear;
    private String strPaymentDate;
    private String strNextPaymentDate;
    private String strUpdatedOn;
	
	public Long getTotalInvoice() {
		return totalInvoice;
	}
	public void setTotalInvoice(Long totalInvoice) {
		this.totalInvoice = totalInvoice;
	}
	public Long getTotalPaidInvoice() {
		return totalPaidInvoice;
	}
	public void setTotalPaidInvoice(Long totalPaidInvoice) {
		this.totalPaidInvoice = totalPaidInvoice;
	}
	public Long getTotalUnPaidInvoice() {
		return totalUnPaidInvoice;
	}
	public void setTotalUnPaidInvoice(Long totalUnPaidInvoice) {
		this.totalUnPaidInvoice = totalUnPaidInvoice;
	}
	public Long getTotalCanceledInvoice() {
		return totalCanceledInvoice;
	}
	public void setTotalCanceledInvoice(Long totalCanceledInvoice) {
		this.totalCanceledInvoice = totalCanceledInvoice;
	}
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
	public FeeCategory getFeeCategory() {
		return feeCategory;
	}
	public void setFeeCategory(FeeCategory feeCategory) {
		this.feeCategory = feeCategory;
	}
	public FeeDetails getFeeDetails() {
		return feeDetails;
	}
	public void setFeeDetails(FeeDetails feeDetails) {
		this.feeDetails = feeDetails;
	}
	public DueDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(DueDate dueDate) {
		this.dueDate = dueDate;
	}
	public PaymentRemainder getPaymentRemainder() {
		return paymentRemainder;
	}
	public void setPaymentRemainder(PaymentRemainder paymentRemainder) {
		this.paymentRemainder = paymentRemainder;
	}
	public College getCollege() {
		return college;
	}
	public void setCollege(College college) {
		this.college = college;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
	public String getStrPaymentDate() {
		return strPaymentDate;
	}
	public void setStrPaymentDate(String strPaymentDate) {
		this.strPaymentDate = strPaymentDate;
	}
	public String getStrNextPaymentDate() {
		return strNextPaymentDate;
	}
	public void setStrNextPaymentDate(String strNextPaymentDate) {
		this.strNextPaymentDate = strNextPaymentDate;
	}
	public String getStrUpdatedOn() {
		return strUpdatedOn;
	}
	public void setStrUpdatedOn(String strUpdatedOn) {
		this.strUpdatedOn = strUpdatedOn;
	}
	
}
