package com.synectiks.cms.graphql.types.LateFee;

public class AddLateFeeInput extends AbstractLateFeeInput {
    private Long collegeId;
    private Long branchId;

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

    @Override
    public String toString() {
        return "AddLateFeeInput{" +
            "collegeId=" + collegeId +
            ", branchId=" + branchId +
            '}';
    }
}
