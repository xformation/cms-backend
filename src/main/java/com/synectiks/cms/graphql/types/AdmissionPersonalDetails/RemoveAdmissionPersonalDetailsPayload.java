package com.synectiks.cms.graphql.types.AdmissionPersonalDetails;

import java.util.List;

import com.synectiks.cms.entities.AdmissionApplication;

public class RemoveAdmissionPersonalDetailsPayload {
    private final List<AdmissionApplication> admissionpersonaldetails;

    public RemoveAdmissionPersonalDetailsPayload(List<AdmissionApplication> admissionpersonaldetails) {
        this.admissionpersonaldetails = admissionpersonaldetails;
    }

    public List<AdmissionApplication> getAdmissionpersonaldetails() {
        return admissionpersonaldetails;
    }
}
