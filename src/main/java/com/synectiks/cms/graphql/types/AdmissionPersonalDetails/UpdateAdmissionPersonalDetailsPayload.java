package com.synectiks.cms.graphql.types.AdmissionPersonalDetails;

import com.synectiks.commons.entities.cms.AdmissionApplication;

public class UpdateAdmissionPersonalDetailsPayload extends AbstractAdmissionPersonalDetailsPayload {
    public UpdateAdmissionPersonalDetailsPayload(AdmissionApplication admissionApplication) {
        super(admissionApplication);
    }
}
