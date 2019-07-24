package com.synectiks.cms.graphql.types.StudentExamReport;

import java.util.Objects;

public class AddStudentExamReportInput extends AbstractStudentExamReportInput {
    private Long academicyearId;
    private Long typeOfGradingId;
    private Long studentId;
    private Long academicExamSettingId;
    private Long batchId;
    private Long departmentId;
    private Long sectionId;
    private Long subjectId;


    public Long getAcademicyearId() {
        return academicyearId;
    }

    public void setAcademicyearId(Long academicyearId) {
        this.academicyearId = academicyearId;
    }

    public Long getTypeOfGradingId() {
        return typeOfGradingId;
    }

    public void setTypeOfGradingId(Long typeOfGradingId) {
        this.typeOfGradingId = typeOfGradingId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getAcademicExamSettingId() {
        return academicExamSettingId;
    }

    public void setAcademicExamSettingId(Long academicExamSettingId) {
        this.academicExamSettingId = academicExamSettingId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AddStudentExamReportInput that = (AddStudentExamReportInput) o;
        return Objects.equals(academicyearId, that.academicyearId) &&
            Objects.equals(typeOfGradingId, that.typeOfGradingId) &&
            Objects.equals(studentId, that.studentId) &&
            Objects.equals(academicExamSettingId, that.academicExamSettingId) &&
            Objects.equals(batchId, that.batchId) &&
            Objects.equals(departmentId, that.departmentId) &&
            Objects.equals(sectionId, that.sectionId) &&
            Objects.equals(subjectId, that.subjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), academicyearId, typeOfGradingId, studentId, academicExamSettingId, batchId, departmentId, sectionId, subjectId);
    }

    @Override
    public String toString() {
        return "AddStudentExamReportInput{" +
            "academicyearId=" + academicyearId +
            ", typeOfGradingId=" + typeOfGradingId +
            ", studentId=" + studentId +
            ", academicExamSettingId=" + academicExamSettingId +
            ", batchId=" + batchId +
            ", departmentId=" + departmentId +
            ", sectionId=" + sectionId +
            ", subjectId=" + subjectId +
            '}';
    }
}
