package com.synectiks.cms.graphql.types.AdmissionPersonalDetails;

import com.synectiks.cms.entities.AdmissionApplication;

public class UpdateAdmissionPersonalDetailsPayload extends AbstractAdmissionPersonalDetailsPayload {
    public UpdateAdmissionPersonalDetailsPayload(AdmissionApplication admissionApplication) {
        super(admissionApplication);
    }
}
