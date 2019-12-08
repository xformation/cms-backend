package com.synectiks.cms.graphql.types.Insurance;

import java.util.List;

import com.synectiks.cms.entities.Insurance;

public class RemoveInsurancePayload {
    private final List<Insurance> insurances;

    public RemoveInsurancePayload(List<Insurance> insurances) {
        this.insurances = insurances;
    }

    public List<Insurance> getInsurances() {
        return insurances;
    }
}
