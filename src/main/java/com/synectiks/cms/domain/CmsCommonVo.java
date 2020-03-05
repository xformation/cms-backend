package com.synectiks.cms.domain;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.synectiks.cms.domain.enumeration.RouteFrequency;
import com.synectiks.cms.domain.enumeration.Status;

public class CmsCommonVo implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String createdBy;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate createdOn;

    private String updatedBy;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate updatedOn;

    private Status status;
    private RouteFrequency routeFrequency;
    private String strCreatedOn;
    private String strUpdatedOn;
    private Long exitCode;
    private String exitDescription;



    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public LocalDate getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }
    public String getUpdatedBy() {
        return updatedBy;
    }
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
    public LocalDate getUpdatedOn() {
        return updatedOn;
    }
    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public RouteFrequency getRouteFrequency() { return routeFrequency; }
    public void setRouteFrequency(RouteFrequency routeFrequency) { this.routeFrequency = routeFrequency; }
    public String getStrCreatedOn() {
        return strCreatedOn;
    }
    public void setStrCreatedOn(String strCreatedOn) {
        this.strCreatedOn = strCreatedOn;
    }
    public String getStrUpdatedOn() {
        return strUpdatedOn;
    }
    public void setStrUpdatedOn(String strUpdatedOn) {
        this.strUpdatedOn = strUpdatedOn;
    }
    public Long getExitCode() {
        return exitCode;
    }
    public void setExitCode(Long exitCode) {
        this.exitCode = exitCode;
    }
    public String getExitDescription() {
        return exitDescription;
    }
    public void setExitDescription(String exitDescription) {
        this.exitDescription = exitDescription;
    }

}
