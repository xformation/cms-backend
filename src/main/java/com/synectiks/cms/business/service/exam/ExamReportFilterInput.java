package com.synectiks.cms.business.service.exam;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExamReportFilterInput {

//    private String academicyearId;
//    private String typeOfGradingId;
//    private String studentId;
    private String academicExamSettingId;
    private String batchId;
    private String departmentId;
    private String subjectId;
    private String sectionId;


//
//    @JsonProperty("academicyearId")
//    public String getAcademicyearId() {
//        return academicyearId;
//    }
//
//    public void setAcademicyearId(String academicyearId) {
//        this.academicyearId = academicyearId;
//    }

    @JsonProperty("departmentId")
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @JsonProperty("subjectId")
    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    @JsonProperty("sectionId")
    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    //    @JsonProperty("studentId")
//    public String getStudentId() {
//        return studentId;
//    }
//
//    public void setStudentId(String studentId) {
//        this.studentId = studentId;
//    }
//
//    @JsonProperty("typeOfGradingId")
//    public String getTypeOfGradingId() {
//        return typeOfGradingId;
//    }
//
//    public void setTypeOfGradingId(String typeOfGradingId) {
//        this.typeOfGradingId = typeOfGradingId;
//    }

    @JsonProperty("academicExamSettingId")
    public String getAcademicExamSettingId() {
        return academicExamSettingId;
    }

    public void setAcademicExamSettingId(String academicExamSettingId) {
        this.academicExamSettingId = academicExamSettingId;
    }

    @JsonProperty("batchId")
    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }
}
