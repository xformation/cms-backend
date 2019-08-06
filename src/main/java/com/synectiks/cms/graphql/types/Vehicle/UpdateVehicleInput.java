package com.synectiks.cms.graphql.types.Vehicle;

public class UpdateVehicleInput extends AbstractVehicleInput {
    private Long employeeId;
    private Long transportRouteId;
    private Long insuranceId;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getTransportRouteId() {
        return transportRouteId;
    }

    public void setTransportRouteId(Long transportRouteId) {
        this.transportRouteId = transportRouteId;
    }

    public Long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

    @Override
    public String toString() {
        return "UpdateVehicleInput{" +
            "employeeId=" + employeeId +
            ", transportRouteId=" + transportRouteId +
            ", insuranceId=" + insuranceId +
            '}';
    }
}
