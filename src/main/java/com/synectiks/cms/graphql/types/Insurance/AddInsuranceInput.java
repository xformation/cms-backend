package com.synectiks.cms.graphql.types.Insurance;

public class AddInsuranceInput extends AbstractInsuranceInput {
    private Long vehicleId;

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Override
    public String toString() {
        return "AddInsuranceInput{" +
            "vehicleId=" + vehicleId +
            '}';
    }
}
