package com.synectiks.cms.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Facility entity.
 */
public class FacilityDTO implements Serializable {

    private Long id;

    @NotNull
    private String facilityName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FacilityDTO facilityDTO = (FacilityDTO) o;
        if (facilityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), facilityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FacilityDTO{" +
            "id=" + getId() +
            ", facilityName='" + getFacilityName() + "'" +
            "}";
    }
}
