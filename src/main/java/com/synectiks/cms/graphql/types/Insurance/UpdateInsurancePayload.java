package com.synectiks.cms.graphql.types.Insurance;

import com.synectiks.commons.entities.cms.Insurance;

public class UpdateInsurancePayload extends AbstractInsurancePayload{
    public UpdateInsurancePayload(Insurance insurance) {
        super(insurance);
    }
}
