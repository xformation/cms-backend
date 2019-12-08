package com.synectiks.cms.graphql.types.Insurance;

import com.synectiks.cms.entities.Insurance;

public class AddInsurancePayload extends AbstractInsurancePayload{
    public AddInsurancePayload(Insurance insurance) {
        super(insurance);
    }
}
