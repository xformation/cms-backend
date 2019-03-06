package com.synectiks.cms.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the TransportRoute entity.
 */
public class TransportRouteDTO implements Serializable {

    private Long id;

    @NotNull
    private String routeName;

    @NotNull
    private String routeDetails;

    @NotNull
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TransportRouteDTO transportRouteDTO = (TransportRouteDTO) o;
        if (transportRouteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), transportRouteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TransportRouteDTO{" +
            "id=" + getId() +
            ", routeName='" + getRouteName() + "'" +
            ", routeDetails='" + getRouteDetails() + "'" +
            ", routeMapUrl='" + getRouteMapUrl() + "'" +
            "}";
    }
}
