package com.synectiks.cms.entities;

import java.util.List;

public class VehicleDataCache {
    private List<Vehicle> vehicles;
    private List<TransportRoute> transportRoutes;

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
}
