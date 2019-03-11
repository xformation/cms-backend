package com.synectiks.cms.graphql.types.AdmissionEnquiry;

import com.synectiks.cms.domain.AdmissionEnquiry;

public class AbstractAdmissionEnquiryPayload {
    private final AdmissionEnquiry admissionEnquiry;

    public AbstractAdmissionEnquiryPayload(AdmissionEnquiry admissionEnquiry) {
        this.admissionEnquiry = admissionEnquiry;
    }

    public AdmissionEnquiry getAdmissionEnquiry() {
        return admissionEnquiry;
    }
}
