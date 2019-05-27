package com.synectiks.cms.graphql.types.StudentExamReport;

import java.util.Objects;

public class AddStudentExamReportInput extends AbstractStudentExamReportInput {
    private Long academicyearId;
    private Long typeOfGradingId;
    private Long studentId;
    private Long academicExamSettingId;
    private Long batchId;

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
            Objects.equals(batchId, that.batchId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), academicyearId, typeOfGradingId, studentId, academicExamSettingId, batchId);
    }

    @Override
    public String toString() {
        return "AddStudentExamReportInput{" +
            "academicyearId=" + academicyearId +
            ", typeOfGradingId=" + typeOfGradingId +
            ", studentId=" + studentId +
            ", academicExamSettingId=" + academicExamSettingId +
            ", batchId=" + batchId +
            '}';
    }
}
