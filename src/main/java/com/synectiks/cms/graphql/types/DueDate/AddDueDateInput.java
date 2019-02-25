package com.synectiks.cms.graphql.types.DueDate;

public class AddDueDateInput extends AbstractDueDateInput {
    private Long collegeId;
    private long branchId;

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public long getBranchId() {
        return branchId;
    }

    public void setBranchId(long branchId) {
        this.branchId = branchId;
    }

    @Override
    public String toString() {
        return "AddDueDateInput{" +
            "collegeId=" + collegeId +
            ", branchId=" + branchId +
            '}';
    }
}
