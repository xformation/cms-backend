package com.synectiks.cms.graphql.types.AdmissionEnquiry;

public class UpdateAdmissionEnquiryInput extends AbstractAdmissionEnquiryInput {
    private Long branchId;
    private Long admissionApplicationId;

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getAdmissionApplicationId() {
        return admissionApplicationId;
    }

    public void setAdmissionApplicationId(Long admissionApplicationId) {
        this.admissionApplicationId = admissionApplicationId;
    }
}
