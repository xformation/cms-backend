package com.synectiks.cms.filter.library;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LibraryFilterInput {

    private String batchId;
    private String subjectId;

    @JsonProperty("batchId")
    public String getBatchId() {return batchId;}
    public void setBatchId(String batchId) { this.batchId = batchId;}

    @JsonProperty("subjectId")
    public String getSubjectId() {return subjectId;}
    public void setSubjectId(String subjectId) { this.subjectId = subjectId;}
}
