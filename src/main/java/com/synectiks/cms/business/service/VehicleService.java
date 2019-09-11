package com.synectiks.cms.business.service;

import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.CmsVehicleVo;
import com.synectiks.cms.domain.TransportRoute;
import com.synectiks.cms.domain.TransportRoute_;
import com.synectiks.cms.domain.Vehicle;
import com.synectiks.cms.filter.vehicle.VehicleListFilterInput;
import com.synectiks.cms.repository.VehicleRepository;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.service.util.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    private CommonService commonService;

    public List<CmsVehicleVo> searchVehicle(Long transportRouteId, String vehicleNumber) throws Exception{
        Vehicle vehicle = new Vehicle();

        if(transportRouteId != null) {
            TransportRoute transportRoute = new TransportRoute();
            transportRoute.setId(transportRouteId);
            vehicle.setTransportRoute(transportRoute);
        }

        if( vehicleNumber != null) {
            vehicle.setVehicleNumber(vehicleNumber);
        }

        Example<Vehicle> example = Example.of(vehicle);
        List<Vehicle> list = this.vehicleRepository.findAll(example);
        List<CmsVehicleVo> ls = new ArrayList<>();
        for(Vehicle temp: list) {
//            String stDt = DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, temp.getPaymentDate());
            CmsVehicleVo cve = CommonUtil.createCopyProperties(temp, CmsVehicleVo.class);
            cve.setStrDateOfRegistration(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(temp.getDateOfRegistration()))));
            ls.add(cve);
        }
        return ls;
    }


    public List<CmsVehicleVo> searchVehicle(VehicleListFilterInput filter) throws Exception{
        Vehicle vehicle = new Vehicle();
        if(!CommonUtil.isNullOrEmpty(filter.getTransportRouteId())) {
            TransportRoute transportRoute = this.commonService.getTransportRouteById(Long.valueOf(filter.getTransportRouteId()));
            if(transportRoute != null) {
                vehicle.setTransportRoute(transportRoute);
            }
        }
        if(!CommonUtil.isNullOrEmpty(filter.getVehicleId())) {
            if(vehicle != null) {
                vehicle.setId(Long.valueOf(filter.getVehicleId()));
            }
        }


        Example<Vehicle> example = Example.of(vehicle);
        List<Vehicle> list = this.vehicleRepository.findAll(example);
        List<CmsVehicleVo> ls = new ArrayList<>();
        for(Vehicle temp: list) {
//            String stDt = DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, temp.getPaymentDate());
            CmsVehicleVo cve = CommonUtil.createCopyProperties(temp, CmsVehicleVo.class);
            cve.setStrDateOfRegistration(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(temp.getDateOfRegistration()))));
            ls.add(cve);
        }
        return ls;
    }
}
