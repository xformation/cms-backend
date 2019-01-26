package com.synectiks.cms.AcademicSubject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AcademicSubjectInput {
    private String departmentId;
    private String batchId;

    @JsonProperty("departmentId")
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @JsonProperty("batchId")
    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }
}
