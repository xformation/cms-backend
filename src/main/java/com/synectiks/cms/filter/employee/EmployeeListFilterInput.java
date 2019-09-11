package com.synectiks.cms.filter.employee;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeListFilterInput {
    private String vehicleId;
    private String employeeId;

    @JsonProperty("vehicleId")
    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }
    @JsonProperty("employeeId")
    public String getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

}
