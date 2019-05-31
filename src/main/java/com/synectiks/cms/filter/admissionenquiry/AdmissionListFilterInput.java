package com.synectiks.cms.filter.admissionenquiry;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdmissionListFilterInput {
    private String status;

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
