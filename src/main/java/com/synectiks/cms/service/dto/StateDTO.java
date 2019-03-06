package com.synectiks.cms.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the State entity.
 */
public class StateDTO implements Serializable {

    private Long id;

    @NotNull
    private String stateName;

    @NotNull
    private String divisionType;

    @NotNull
    private String stateCode;


    private Long countryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getDivisionType() {
        return divisionType;
    }

    public void setDivisionType(String divisionType) {
        this.divisionType = divisionType;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StateDTO stateDTO = (StateDTO) o;
        if (stateDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), stateDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StateDTO{" +
            "id=" + getId() +
            ", stateName='" + getStateName() + "'" +
            ", divisionType='" + getDivisionType() + "'" +
            ", stateCode='" + getStateCode() + "'" +
            ", country=" + getCountryId() +
            "}";
    }
}
