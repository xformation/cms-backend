package com.synectiks.cms.graphql.types.AuthorizedSignatory;

public class UpdateAuthorizedSignatoryInput extends AbstractAuthorizedSignatoryInput {
    private Long branchId;
    private  Long collegeId;
    private Long legalEntityId;

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

    public Long getLegalEntityId() {
        return legalEntityId;
    }

    public void setLegalEntityId(Long legalEntityId) {
        this.legalEntityId = legalEntityId;
    }
}
