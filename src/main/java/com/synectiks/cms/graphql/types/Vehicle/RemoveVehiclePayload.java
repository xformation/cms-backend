package com.synectiks.cms.graphql.types.Vehicle;

import java.util.List;

import com.synectiks.cms.entities.Vehicle;

public class RemoveVehiclePayload {
    private final List<Vehicle> vehicles;

    public RemoveVehiclePayload(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}
