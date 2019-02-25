package com.synectiks.cms.graphql.types.Invoice;

public class UpdateInvoiceInput  extends AbstractInvoiceInput{
    private Long feeCategoryId;
    private Long feeDetailsId;
    private Long dueDateId;
    private Long paymentRemainderId;
    private Long collegeId;
    private Long branchId;
    private Long studentId;
    private Long academicyearId;

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

    public Long getAcademicyearId() {
        return academicyearId;
    }

    public void setAcademicyearId(Long academicyearId) {
        this.academicyearId = academicyearId;
    }
}


