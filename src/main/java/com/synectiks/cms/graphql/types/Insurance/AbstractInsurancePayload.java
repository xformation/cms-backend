package com.synectiks.cms.graphql.types.Insurance;

import com.synectiks.cms.domain.Insurance;

public class AbstractInsurancePayload {
    private final Insurance insurance;

    public AbstractInsurancePayload(Insurance insurance) {
        this.insurance = insurance;
    }

    public Insurance getInsurance() {
        return insurance;
    }
}
