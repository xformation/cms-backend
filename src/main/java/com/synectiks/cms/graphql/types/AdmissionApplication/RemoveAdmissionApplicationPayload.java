package com.synectiks.cms.graphql.types.AdmissionApplication;

import java.util.List;

import com.synectiks.cms.entities.AdmissionApplication;

public class RemoveAdmissionApplicationPayload {
    private final List<AdmissionApplication> admissionApplications;

    public RemoveAdmissionApplicationPayload(List<AdmissionApplication> admissionApplications){
        this.admissionApplications = admissionApplications;
    }

    public List<AdmissionApplication> getAdmissionApplications() {
        return admissionApplications;
    }
}
