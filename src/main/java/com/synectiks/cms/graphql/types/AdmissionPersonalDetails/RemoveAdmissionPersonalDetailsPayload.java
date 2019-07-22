package com.synectiks.cms.graphql.types.AdmissionPersonalDetails;

import com.synectiks.cms.domain.AdmissionApplication;

import java.util.List;

public class RemoveAdmissionPersonalDetailsPayload {
    private final List<AdmissionApplication> admissionpersonaldetails;

    public RemoveAdmissionPersonalDetailsPayload(List<AdmissionApplication> admissionpersonaldetails) {
        this.admissionpersonaldetails = admissionpersonaldetails;
    }

    public List<AdmissionApplication> getAdmissionpersonaldetails() {
        return admissionpersonaldetails;
    }
}
