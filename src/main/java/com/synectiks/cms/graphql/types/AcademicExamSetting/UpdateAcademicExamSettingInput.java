package com.synectiks.cms.graphql.types.AcademicExamSetting;

public class UpdateAcademicExamSettingInput extends AbstractAcademicExamSettingInput{
    private Long departmentId;
    private Long academicyearId;
    private Long subjectId;
    private Long sectionId;
    private Long batchId;
    private Long branchId;
    private Long typeOfGradingId;

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

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getTypeOfGradingId() {
        return typeOfGradingId;
    }

    public void setTypeOfGradingId(Long typeOfGradingId) {
        this.typeOfGradingId = typeOfGradingId;
    }
}
