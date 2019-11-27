package com.synectiks.cms.graphql.types.Vehicle;

import com.synectiks.cms.domain.Vehicle;

public class UpdateVehiclePayload extends AbstractVehiclePayload {
    public UpdateVehiclePayload(Vehicle vehicle) {
        super(vehicle);
    }
}
