package com.synectiks.cms.domain;

import java.util.List;

public class VehicleDataCache {
    private List<Vehicle> vehicles;
    private List<TransportRoute> transportRoutes;
    private List<CmsRouteFrequency> routeFrequencies;


    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<TransportRoute> getTransportRoutes() {
        return transportRoutes;
    }

    public void setTransportRoutes(List<TransportRoute> transportRoutes) {
        this.transportRoutes = transportRoutes;
    }
    public List<CmsRouteFrequency> getRouteFrequencies() {
        return routeFrequencies;
    }

    public void setRouteFrequencies(List<CmsRouteFrequency> routeFrequencies) {
        this.routeFrequencies = routeFrequencies;
    }
}
