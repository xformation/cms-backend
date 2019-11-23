package com.synectiks.cms.graphql.types.Insurance;

import com.synectiks.commons.entities.cms.Insurance;

import java.util.List;

public class RemoveInsurancePayload {
    private final List<Insurance> insurances;

    public RemoveInsurancePayload(List<Insurance> insurances) {
        this.insurances = insurances;
    }

    public List<Insurance> getInsurances() {
        return insurances;
    }
}
