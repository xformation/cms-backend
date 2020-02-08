package com.synectiks.cms.business.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.synectiks.cms.domain.CmsTransportVo;
import com.synectiks.cms.domain.CmsVehicleVo;
import com.synectiks.cms.domain.TransportRoute;
import com.synectiks.cms.domain.Vehicle;
import com.synectiks.cms.domain.enumeration.RouteFrequency;
import com.synectiks.cms.graphql.types.TransportRoute.AddTransportRouteInput;
import com.synectiks.cms.graphql.types.Vehicle.AddVehicleInput;
import com.synectiks.cms.repository.TransportRouteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.service.util.DateFormatUtil;

@Component
public class TransportService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TransportRouteRepository transportRouteRepository;

    public List<TransportRoute> getTransportRouteList(RouteFrequency routeFrequency) {
        TransportRoute transportRoute = new TransportRoute();
        transportRoute.setRouteFrequency(routeFrequency);
        List<TransportRoute> list = this.transportRouteRepository.findAll(Example.of(transportRoute), Sort.by(Direction.DESC, "id"));
        logger.debug("TransportRoute list for the given routeFrequency : "+routeFrequency+". List : "+list);
        return list;
    }

    public List<CmsTransportVo> getTransportRouteList(){
        List<TransportRoute> list = this.transportRouteRepository.findAll();
        List<CmsTransportVo> ls = changeTransportRouteToCmsTransportRouteList(list);
        logger.debug("Transport Route list : "+list);
        return ls;
    }

    public TransportRoute getTransportRoute(Long id){
        Optional<TransportRoute> tr = this.transportRouteRepository.findById(id);
        if(tr.isPresent()) {
            logger.debug("Transport Route object found for given id : "+id+". Transport Route object : "+tr.get());
            return tr.get();
        }
        logger.debug("AcademicYear object not found for the given id. "+id+". Returning null");
        return null;
    }

    public List<CmsTransportVo> getCmsTransportRouteList(RouteFrequency routeFrequency) {
        TransportRoute transportRoute = new TransportRoute();
        transportRoute.setRouteFrequency(routeFrequency);
        List<TransportRoute> list = this.transportRouteRepository.findAll(Example.of(transportRoute));
        List<CmsTransportVo> ls = changeTransportRouteToCmsTransportRouteList(list);
        Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        logger.debug("CmsTransport Route list for the given routeFrequency : "+routeFrequency+". List : "+list);
        return ls;
    }

    public List<CmsTransportVo> getCmsTransportRouteList(){
        List<TransportRoute> list = this.transportRouteRepository.findAll();
        List<CmsTransportVo> ls = changeTransportRouteToCmsTransportRouteList(list);
        Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        logger.debug("CmsTransport Route list : "+list);
        return ls;
    }

    public CmsTransportVo getCmsTransportRoute(Long id){
        Optional<TransportRoute> tr = this.transportRouteRepository.findById(id);
        if(tr.isPresent()) {
            CmsTransportVo vo = CommonUtil.createCopyProperties(tr.get(), CmsTransportVo.class);
            logger.debug("CmsTransport Route for given id : "+id+". CmsTransport Route object : "+vo);
            return vo;
        }
        logger.debug("TransportRoute object not found for the given id. "+id+". Returning null object");
        return null;
    }

    private List<CmsTransportVo> changeTransportRouteToCmsTransportRouteList(List<TransportRoute> list) {
        List<CmsTransportVo> ls = new ArrayList<>();
        for (TransportRoute o : list) {
            CmsTransportVo vo = CommonUtil.createCopyProperties(o, CmsTransportVo.class);
            ls.add(vo);
        }
        return ls;
    }

    public CmsTransportVo addTransportRoute(AddTransportRouteInput cmsTransportVo) {
        logger.info("Saving TransportRoute");
        CmsTransportVo vo = null;
        try {
            TransportRoute transportRoute = null;
            if (cmsTransportVo.getId() == null) {
                logger.debug("Adding new transportRoute");
                transportRoute = CommonUtil.createCopyProperties(cmsTransportVo, TransportRoute.class);
            }
            else {
                logger.debug("Updating existing transportRoute");
                transportRoute = this.transportRouteRepository.findById(cmsTransportVo.getId()).get();

                if (cmsTransportVo.getRouteName() != null) {
                    transportRoute.setRouteName(cmsTransportVo.getRouteName());
                }

                if (cmsTransportVo.getRouteDetails() != null) {
                    transportRoute.setRouteDetails(cmsTransportVo.getRouteDetails());
                }

                if (cmsTransportVo.getRouteMapUrl() != null) {
                    transportRoute.setRouteMapUrl(cmsTransportVo.getRouteMapUrl());
                }
                if (cmsTransportVo.getNoOfStops() != null) {
                    transportRoute.setNoOfStops(cmsTransportVo.getNoOfStops());
                }

                if (cmsTransportVo.getRouteFrequency() != null) {
                    transportRoute.setRouteFrequency(cmsTransportVo.getRouteFrequency());
                }
            }
            transportRoute = this.transportRouteRepository.save(transportRoute);
            vo = CommonUtil.createCopyProperties(transportRoute, CmsTransportVo.class);
            vo.setExitCode(0L);
            if (cmsTransportVo.getId() == null) {
                vo.setExitDescription("TransportRoute is added successfully");
                logger.debug("TransportRoute is added successfully");
            } else {
                vo.setExitDescription("TransportRoute is updated successfully");
                logger.debug("TransportRoute is updated successfully");
            }

        } catch (Exception e) {
            vo = new CmsTransportVo();
            vo.setExitCode(1L);
            vo.setExitDescription("Due to some exception, transportRoute data not be saved");
            logger.error("TransportRoute save failed. Exception : ", e);
        }
        logger.info("TransportRoute saved successfully");
        List ls = getTransportRouteList();
        vo.setDataList(ls);
        return vo;
    }
}




