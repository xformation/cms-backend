package com.synectiks.cms.entities;

import java.util.List;

public class EmployeeDataCache {
    private List<Vehicle> vehicles;
    private List<Employee> employees;

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
