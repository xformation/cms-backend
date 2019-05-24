package com.synectiks.cms.graphql.types.AcademicExamSetting;

import java.util.Objects;

public class AddAcademicExamSettingInput extends AbstractAcademicExamSettingInput {
    private Long departmentId;
    private Long academicyearId;
    private Long sectionId;
    private Long batchId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AddAcademicExamSettingInput that = (AddAcademicExamSettingInput) o;
        return Objects.equals(departmentId, that.departmentId) &&
            Objects.equals(academicyearId, that.academicyearId) &&
            Objects.equals(sectionId, that.sectionId) &&
            Objects.equals(batchId, that.batchId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), departmentId, academicyearId, sectionId, batchId);
    }

    @Override
    public String toString() {
        return "AddAcademicExamSettingInput{" +
            "departmentId=" + departmentId +
            ", academicyearId=" + academicyearId +
            ", sectionId=" + sectionId +
            ", batchId=" + batchId +
            '}';
    }
}
