package com.synectiks.cms.graphql.types.Invoice;

import java.util.Objects;

public class AddInvoiceInput extends AbstractInvoiceInput{
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddInvoiceInput)) return false;
        AddInvoiceInput that = (AddInvoiceInput) o;
        return Objects.equals(getFeeCategoryId(), that.getFeeCategoryId()) &&
            Objects.equals(getFeeDetailsId(), that.getFeeDetailsId()) &&
            Objects.equals(getDueDateId(), that.getDueDateId()) &&
            Objects.equals(getPaymentRemainderId(), that.getPaymentRemainderId()) &&
            Objects.equals(getCollegeId(), that.getCollegeId()) &&
            Objects.equals(getBranchId(), that.getBranchId()) &&
            Objects.equals(getStudentId(), that.getStudentId()) &&
            Objects.equals(getAcademicyearId(), that.getAcademicyearId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFeeCategoryId(), getFeeDetailsId(), getDueDateId(), getPaymentRemainderId(), getCollegeId(), getBranchId(), getStudentId(), getAcademicyearId());
    }

    @Override
    public String toString() {
        return "AddInvoiceInput{" +
            "feeCategoryId=" + feeCategoryId +
            ", feeDetailsId=" + feeDetailsId +
            ", dueDateId=" + dueDateId +
            ", paymentRemainderId=" + paymentRemainderId +
            ", collegeId=" + collegeId +
            ", branchId=" + branchId +
            ", studentId=" + studentId +
            ", academicyearId=" + academicyearId +
            '}';
    }
}
