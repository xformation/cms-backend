package com.synectiks.cms.domain;

import java.util.List;

public class VehicleDataCache {
    private List<CmsTransportVo> transportRoute;
    private List<CmsContractVo> contract;
    private List<CmsVehicleVo> vehicle;
    private List<Employee> employee;
    private List<College> colleges;
    private List<Branch> branches;

    public List<CmsTransportVo> getTransportRoute() {
        return transportRoute;
    }

    public void setTransportRoute(List<CmsTransportVo> transportRoute) {
        this.transportRoute = transportRoute;
    }

    public List<CmsContractVo> getContract() {
        return contract;
    }

    public void setContract(List<CmsContractVo> contract) {
        this.contract = contract;
    }

    public List<CmsVehicleVo> getVehicle() {
        return vehicle;
    }

    public void setVehicle(List<CmsVehicleVo> vehicle) {
        this.vehicle = vehicle;
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }

    public List<College> getColleges() {
        return colleges;
    }

    public void setColleges(List<College> colleges) {
        this.colleges = colleges;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
}
