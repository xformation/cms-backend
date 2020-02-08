package com.synectiks.cms.business.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.synectiks.cms.domain.CmsTransportVo;
import com.synectiks.cms.domain.CmsVehicleVo;
import com.synectiks.cms.domain.Vehicle;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.graphql.types.Vehicle.AddVehicleInput;
import com.synectiks.cms.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.service.util.DateFormatUtil;

@Component
public class VehicleService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    VehicleRepository vehicleRepository;

    public List<CmsVehicleVo> getCmsVehicleList(Status status) {
        Vehicle vehicle = new Vehicle();
        vehicle.setStatus(status);
        List<Vehicle> list = this.vehicleRepository.findAll(Example.of(vehicle));
        List<CmsVehicleVo> ls = changeVehicleToCmsVehicleList(list);
        Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }

    public List<Vehicle> getVehicleList(Status status) {
        Vehicle vehicle = new  Vehicle();
        vehicle.setStatus(status);
        List<Vehicle> list = this.vehicleRepository.findAll(Example.of(vehicle));
        Collections.sort(list, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return list;
    }

    public List<CmsVehicleVo> getVehicleList(){
        List<Vehicle> list = this.vehicleRepository.findAll();
        List<CmsVehicleVo> ls = changeVehicleToCmsVehicleList(list);
        Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }

    public CmsVehicleVo getCmsVehicle(Long id){
        Optional<Vehicle> v = this.vehicleRepository.findById(id);
        if(v.isPresent()) {
            CmsVehicleVo vo = CommonUtil.createCopyProperties(v.get(), CmsVehicleVo.class);
            convertDatesAndProvideDependencies(v.get(), vo);
            logger.debug("CmsVehicle for given id : "+id+". CmsVehicle object : "+vo);
            return vo;
        }
        logger.debug("Vehicle object not found for the given id. "+id+". Returning null object");
        return null;
    }
    public Vehicle getVehicle(Long id){
        Optional< Vehicle> v = this.vehicleRepository.findById(id);
        if(v.isPresent()) {
            return v.get();
        }
        logger.debug("Vehicle object not found for the given id. "+id+". Returning null");
        return null;
    }
    private List<CmsVehicleVo> changeVehicleToCmsVehicleList(List<Vehicle> list){
        List<CmsVehicleVo> ls = new ArrayList<>();
        for(Vehicle v: list) {
            CmsVehicleVo vo = CommonUtil.createCopyProperties(v, CmsVehicleVo.class);
            convertDatesAndProvideDependencies(v, vo);
            ls.add(vo);
        }
        return ls;
    }

    private void convertDatesAndProvideDependencies(Vehicle v, CmsVehicleVo vo) {
        if(v.getDateOfRegistration() != null) {
            vo.setStrDateOfRegistration(DateFormatUtil.changeLocalDateFormat(v.getDateOfRegistration(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            vo.setDateOfRegistration(null);
        }
    }

    public CmsVehicleVo addVehicle(AddVehicleInput cmsVehicleVo) {
        logger.info("Saving Vehicle");
        CmsVehicleVo vo = null;
        try {
            Vehicle vehicle = null;
            if (cmsVehicleVo.getId() == null) {
                logger.debug("Adding new vehicle");
                vehicle = CommonUtil.createCopyProperties(cmsVehicleVo, Vehicle.class);
            } else {
                logger.debug("Updating existing vehicle");
                vehicle = this.vehicleRepository.findById(cmsVehicleVo.getId()).get();

                if (cmsVehicleVo.getVehicleNumber() != null) {
                    vehicle.setVehicleNumber(cmsVehicleVo.getVehicleNumber());
                }

                if (cmsVehicleVo.getVehicleType() != null) {
                    vehicle.setVehicleType(cmsVehicleVo.getVehicleType());
                }

                if (cmsVehicleVo.getCapacity() != null) {
                    vehicle.setCapacity(cmsVehicleVo.getCapacity());
                }
                if (cmsVehicleVo.getOwnerShip() != null) {
                    vehicle.setOwnerShip(cmsVehicleVo.getOwnerShip());
                }

                if (cmsVehicleVo.getRcNo() != null) {
                    vehicle.setRcNo(cmsVehicleVo.getRcNo());
                }
                if (cmsVehicleVo.getModel() != null) {
                    vehicle.setModel(cmsVehicleVo.getModel());
                }

                if (cmsVehicleVo.getChasisNo() != null) {
                    vehicle.setChasisNo(cmsVehicleVo.getChasisNo());
                }

                if (cmsVehicleVo.getManufacturingCompany() != null) {
                    vehicle.setManufacturingCompany(cmsVehicleVo.getManufacturingCompany());
                }
                if (cmsVehicleVo.getYearOfManufacturing() != null) {
                    vehicle.setYearOfManufacturing(cmsVehicleVo.getYearOfManufacturing());
                }
                if (cmsVehicleVo.getContactNumber() != null) {
                    vehicle.setContactNumber(cmsVehicleVo.getContactNumber());
                }
                if (cmsVehicleVo.getStatus() != null) {
                    vehicle.setStatus(cmsVehicleVo.getStatus());
                }

            }
            vehicle.setDateOfRegistration(cmsVehicleVo.getStrDateOfRegistration() != null ? DateFormatUtil.convertStringToLocalDate(cmsVehicleVo.getStrDateOfRegistration(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
            vehicle = this.vehicleRepository.save(vehicle);
            vo = CommonUtil.createCopyProperties(vehicle, CmsVehicleVo.class);
            vo.setStrDateOfRegistration(vehicle.getDateOfRegistration() != null ? DateFormatUtil.changeLocalDateFormat(vehicle.getDateOfRegistration(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
            vo.setExitCode(0L);
            if (cmsVehicleVo.getId() == null) {
                vo.setExitDescription("Vehicle is added successfully");
                logger.debug("Vehicle is added successfully");
            } else {
                vo.setExitDescription("Vehicle is updated successfully");
                logger.debug("Vehicle is updated successfully");
            }

        } catch (Exception e) {
            vo = new CmsVehicleVo();
            vo.setExitCode(1L);
            vo.setExitDescription("Due to some exception, vehicle data not be saved");
            logger.error("Vehicle save failed. Exception : ", e);
        }
        logger.info("Vehicle saved successfully");
        List ls = getVehicleList();
        vo.setDataList(ls);
        return vo;
    }
}
