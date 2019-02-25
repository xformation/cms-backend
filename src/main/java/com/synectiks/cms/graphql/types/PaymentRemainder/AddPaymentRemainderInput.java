package com.synectiks.cms.graphql.types.PaymentRemainder;

public class AddPaymentRemainderInput extends AbstractPaymentRemainderInput {
    private Long collegeId;
    private Long branchId;
    private Long dueDateId;

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

    public Long getDueDateId() {
        return dueDateId;
    }

    public void setDueDateId(Long dueDateId) {
        this.dueDateId = dueDateId;
    }

    @Override
    public String toString() {
        return "AddPaymentRemainderInput{" +
            "collegeId=" + collegeId +
            ", branchId=" + branchId +
            ", dueDateId=" + dueDateId +
            '}';
    }
}

