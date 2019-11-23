package com.synectiks.cms.graphql.types.Insurance;

import com.synectiks.commons.entities.cms.Insurance;

public class AddInsurancePayload extends AbstractInsurancePayload{
    public AddInsurancePayload(Insurance insurance) {
        super(insurance);
    }
}
