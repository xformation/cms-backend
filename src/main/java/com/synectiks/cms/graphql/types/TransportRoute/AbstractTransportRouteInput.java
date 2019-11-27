package com.synectiks.cms.graphql.types.TransportRoute;

import com.synectiks.cms.domain.enumeration.RouteFrequency;

import java.util.Objects;

public class AbstractTransportRouteInput {
    private Long id;
    private String routeName;
    private String routeDetails;
    private String routeMapUrl;
    private Integer noOfStops;
    private RouteFrequency routeFrequency;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractTransportRouteInput)) return false;
        AbstractTransportRouteInput that = (AbstractTransportRouteInput) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getRouteName(), that.getRouteName()) &&
            Objects.equals(getRouteDetails(), that.getRouteDetails()) &&
            Objects.equals(getRouteMapUrl(), that.getRouteMapUrl()) &&
            Objects.equals(getNoOfStops(), that.getNoOfStops()) &&
            getRouteFrequency() == that.getRouteFrequency();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRouteName(), getRouteDetails(), getRouteMapUrl(), getNoOfStops(), getRouteFrequency());
    }

    @Override
    public String toString() {
        return "AbstractTransportRouteInput{" +
            "id=" + id +
            ", routeName='" + routeName + '\'' +
            ", routeDetails='" + routeDetails + '\'' +
            ", routeMapUrl='" + routeMapUrl + '\'' +
            ", noOfStops=" + noOfStops +
            ", routeFrequency=" + routeFrequency +
            '}';
    }
}
