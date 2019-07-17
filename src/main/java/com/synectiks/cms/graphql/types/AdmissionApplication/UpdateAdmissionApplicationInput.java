package com.synectiks.cms.graphql.types.AdmissionApplication;

public class UpdateAdmissionApplicationInput extends AbstractAdmissionApplicationInput {
    private Long admissionEnquiryId;
    private Long academicHistoryId;
    private Long documentsId;
    private Long branchId;
    private Long batchId;
    private Long stateId;
    private Long cityId;
    private Long countryId;
    private Long departmentId;
    private Long academicyearId;

    public Long getAdmissionEnquiryId() {
        return admissionEnquiryId;
    }

    public void setAdmissionEnquiryId(Long admissionEnquiryId) {
        this.admissionEnquiryId = admissionEnquiryId;
    }

    public Long getAcademicHistoryId() {
        return academicHistoryId;
    }

    public void setAcademicHistoryId(Long academicHistoryId) {
        this.academicHistoryId = academicHistoryId;
    }

    public Long getDocumentsId() {
        return documentsId;
    }

    public void setDocumentsId(Long documentsId) {
        this.documentsId = documentsId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getAcademicyearId() {
        return academicyearId;
    }

    public void setAcademicyearId(Long academicyearId) {
        this.academicyearId = academicyearId;
    }
}
