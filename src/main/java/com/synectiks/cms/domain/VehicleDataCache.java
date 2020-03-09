package com.synectiks.cms.domain;

import java.util.List;

public class VehicleDataCache {
    private List<CmsTransportVo> transportRoute;
    private List<CmsInsuranceVo> insurance;
    private List<CmsVehicleVo> vehicle;

    public List<CmsTransportVo> getTransportRoute() {
        return transportRoute;
    }

    public void setTransportRoute(List<CmsTransportVo> transportRoute) {
        this.transportRoute = transportRoute;
    }

    public List<CmsInsuranceVo> getInsurance() {
        return insurance;
    }

    public void setInsurance(List<CmsInsuranceVo> insurance) {
        this.insurance = insurance;
    }

    public List<CmsVehicleVo> getVehicle() {
        return vehicle;
    }

    public void setVehicle(List<CmsVehicleVo> vehicle) {
        this.vehicle = vehicle;
    }
}
