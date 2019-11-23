package com.synectiks.cms.graphql.types.AdmissionApplication;

import com.synectiks.commons.entities.cms.AdmissionApplication;

public class AbstractAdmissionApplicationPayload {
    private final AdmissionApplication admissionApplication;

    public AbstractAdmissionApplicationPayload(AdmissionApplication admissionApplication){
        this.admissionApplication = admissionApplication;
    }

    public AdmissionApplication getAdmissionApplication() {
        return admissionApplication;
    }
}
