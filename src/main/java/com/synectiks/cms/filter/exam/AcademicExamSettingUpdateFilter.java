package com.synectiks.cms.filter.exam;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AcademicExamSettingUpdateFilter {


    private String departmentId;
    private String academicyearId;
    private String sectionId;
    private String batchId;


    @JsonProperty("departmentId")
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @JsonProperty("academicyearId")
    public String getAcademicyearId() {
        return academicyearId;
    }

    public void setAcademicyearId(String academicyearId) {
        this.academicyearId = academicyearId;
    }

    @JsonProperty("sectionId")
    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }
    @JsonProperty("batchId")
    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }
}
