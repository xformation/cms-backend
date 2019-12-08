package com.synectiks.cms.graphql.types.AdmissionPersonalDetails;

import com.synectiks.cms.entities.AdmissionApplication;

public class AddAdmissionpPersonalDetailsPayload extends AbstractAdmissionPersonalDetailsPayload {
    public AddAdmissionpPersonalDetailsPayload(AdmissionApplication admissionApplication) {
        super(admissionApplication);
    }
}
