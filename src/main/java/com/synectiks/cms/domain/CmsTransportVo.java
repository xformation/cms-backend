package com.synectiks.cms.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.synectiks.cms.domain.enumeration.RouteFrequency;

/**
 * A Vo for the AcademicYear entity.
 */
public class CmsTransportVo extends CmsCommonVo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 533914446871927557L;
    private Long id;
    private String routeName;
    private String routeDetails;
    private String routeMapUrl;
    private Integer noOfStops;
    private RouteFrequency routeFrequency;
    private List<CmsTransportVo> dataList = new ArrayList<CmsTransportVo>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getRouteDetails() {
        return routeDetails;
    }

    public void setRouteDetails(String routeDetails) {
        this.routeDetails = routeDetails;
    }

    public String getRouteMapUrl() {
        return routeMapUrl;
    }

    public void setRouteMapUrl(String routeMapUrl) {
        this.routeMapUrl = routeMapUrl;
    }

    public Integer getNoOfStops() {
        return noOfStops;
    }

    public void setNoOfStops(Integer noOfStops) {
        this.noOfStops = noOfStops;
    }

    public RouteFrequency getRouteFrequency() {
        return routeFrequency;
    }

    public void setRouteFrequency(RouteFrequency routeFrequency) {
        this.routeFrequency = routeFrequency;
    }

    public List<CmsTransportVo> getDataList() {
        return dataList;
    }

    public void setDataList(List<CmsTransportVo> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "CmsTransportVo [id=" + id + "," + "" +
            "routeName=" + routeName + ", " +
            "routeDetails=" + routeDetails + ", " +
            "routeMapUrl=" + routeMapUrl + ", " +
            "noOfStops=" + noOfStops + ", " +
            "routeFrequency=" + routeFrequency + ", " +
            "dataList=" + dataList + ", " +
            "getExitCode()=" + getExitCode() + ", " +
            "getExitDescription()=" + getExitDescription() + ", " +
            "getClass()=" + getClass() + ", " +
            "hashCode()=" + hashCode() + ", " +
            "toString()=" + super.toString() + "]";
    }
}
