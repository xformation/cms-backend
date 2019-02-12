package com.synectiks.cms.graphql.types.AuthorizedSignatory;

public class AddAuthorizedSignatoryInput extends AbstractAuthorizedSignatoryInput {
    private Long branchId;
    private  Long collegeId;

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    @Override
    public String toString() {
        return "AddAuthorizedSignatoryInput{" +
            "branchId=" + branchId +
            ", collegeId=" + collegeId +
            '}';
    }
}
