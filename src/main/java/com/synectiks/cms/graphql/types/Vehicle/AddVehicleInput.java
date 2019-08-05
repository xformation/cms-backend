package com.synectiks.cms.graphql.types.Vehicle;

public class AddVehicleInput extends AbstractVehicleInput{
    private Long employeeId;
    private Long transportRouteId;

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

    @Override
    public String toString() {
        return "AddVehicleInput{" +
            "employeeId=" + employeeId +
            ", transportRouteId=" + transportRouteId +
            '}';
    }
}
