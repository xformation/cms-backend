package com.synectiks.cms.graphql.types.Vehicle;

import com.synectiks.cms.domain.Vehicle;

import java.util.List;

public class RemoveVehiclePayload {
    private final List<Vehicle> vehicles;

    public RemoveVehiclePayload(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}
