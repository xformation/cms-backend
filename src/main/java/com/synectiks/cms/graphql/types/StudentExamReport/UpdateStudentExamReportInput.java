package com.synectiks.cms.graphql.types.StudentExamReport;

public class UpdateStudentExamReportInput extends AddStudentExamReportInput {
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
}
