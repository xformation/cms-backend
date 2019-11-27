package com.synectiks.cms.graphql.types.AdmissionApplication;

import com.synectiks.cms.domain.AdmissionApplication;

import java.util.List;

public class RemoveAdmissionApplicationPayload {
    private final List<AdmissionApplication> admissionApplications;

    public RemoveAdmissionApplicationPayload(List<AdmissionApplication> admissionApplications){
        this.admissionApplications = admissionApplications;
    }

    public List<AdmissionApplication> getAdmissionApplications() {
        return admissionApplications;
    }
}
