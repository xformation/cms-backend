package com.synectiks.cms.graphql.types.Vehicle;

import com.synectiks.cms.domain.Vehicle;

public class AbstractVehiclePayload {
    private final Vehicle vehicle;

    public AbstractVehiclePayload(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
