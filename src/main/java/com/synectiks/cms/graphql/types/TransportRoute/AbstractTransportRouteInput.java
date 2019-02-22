package com.synectiks.cms.graphql.types.TransportRoute;

import java.util.Objects;

public class AbstractTransportRouteInput {
    private Long id;
    private String routeName;
    private String routeDetails;
    private String routeMapUrl;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractTransportRouteInput)) return false;
        AbstractTransportRouteInput that = (AbstractTransportRouteInput) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getRouteName(), that.getRouteName()) &&
            Objects.equals(getRouteDetails(), that.getRouteDetails()) &&
            Objects.equals(getRouteMapUrl(), that.getRouteMapUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRouteName(), getRouteDetails(), getRouteMapUrl());
    }

    @Override
    public String toString() {
        return "AbstractTransportRouteInput{" +
            "id=" + id +
            ", routeName='" + routeName + '\'' +
            ", routeDetails='" + routeDetails + '\'' +
            ", routeMapUrl='" + routeMapUrl + '\'' +
            '}';
    }
}
