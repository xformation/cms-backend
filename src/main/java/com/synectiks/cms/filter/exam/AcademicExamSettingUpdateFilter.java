package com.synectiks.cms.filter.exam;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AcademicExamSettingUpdateFilter {


    private String subjectIds;

    @JsonProperty("subjectIds")
    public String getSubjectIds() {return subjectIds;}
    public void setSubjectIds(String subjectIds) {this.subjectIds = subjectIds;}
}
