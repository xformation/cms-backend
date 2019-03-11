package com.synectiks.cms.graphql.types.AdmissionApplication;

import com.synectiks.cms.domain.AdmissionApplication;

public class UpdateAdmissionApplicationPayload extends AbstractAdmissionApplicationPayload {
    public UpdateAdmissionApplicationPayload(AdmissionApplication admissionApplication)
    {
        super(admissionApplication);
    }
}
