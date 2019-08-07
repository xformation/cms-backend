package com.synectiks.cms.filter.vehicle;

import com.synectiks.cms.business.service.VehicleService;
import com.synectiks.cms.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VehicleFilterProcessor {
    @Autowired
    private VehicleService vehicleService;

    public List<Vehicle> searchVehicle(Long transportRouteId, Integer vehicleNumber){
        return vehicleService.searchVehicle(transportRouteId,vehicleNumber);
    }

    public List<Vehicle> searchVehicle(VehicleListFilterInput filter){
        return vehicleService.searchVehicle(filter);
    }
}
