package com.synectiks.cms.graphql.types.AcademicExamSetting;

import java.util.Objects;

public class AddAcademicExamSettingInput extends AbstractAcademicExamSettingInput {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AddAcademicExamSettingInput that = (AddAcademicExamSettingInput) o;
        return Objects.equals(departmentId, that.departmentId) &&
            Objects.equals(academicyearId, that.academicyearId) &&
            Objects.equals(subjectId, that.subjectId) &&
            Objects.equals(sectionId, that.sectionId) &&
            Objects.equals(batchId, that.batchId) &&
            Objects.equals(branchId, that.branchId) &&
            Objects.equals(typeOfGradingId, that.typeOfGradingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), departmentId, academicyearId, subjectId, sectionId, batchId, branchId, typeOfGradingId);
    }

    @Override
    public String toString() {
        return "AddAcademicExamSettingInput{" +
            "departmentId=" + departmentId +
            ", academicyearId=" + academicyearId +
            ", subjectId=" + subjectId +
            ", sectionId=" + sectionId +
            ", batchId=" + batchId +
            ", branchId=" + branchId +
            ", typeOfGradingId=" + typeOfGradingId +
            '}';
    }
}
