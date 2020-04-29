package com.synectiks.cms.filter.exam;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.synectiks.cms.domain.enumeration.SemesterEnum;


public class ExamListFilterInput {

    private String branchId;
    private String departmentId;
    private String batchId;
    private String sectionId;
    private String subjectId;

    @JsonProperty("branchId")
    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

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

    @JsonProperty("sectionId")
    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    @JsonProperty("subjectId")
    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

}
