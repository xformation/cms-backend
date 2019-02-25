package com.synectiks.cms.graphql.types.DueDate;

public class UpdateDueDateInput extends AbstractDueDateInput{
    private Long collegeId;
    private  Long branchId;

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
}
