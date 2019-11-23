package com.synectiks.cms.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.synectiks.commons.entities.cms.enumeration.TypeOfInsurance;

/**
 * A DTO for the Insurance entity.
 */
public class InsuranceDTO implements Serializable {

    private Long id;

    @NotNull
    private String insuranceCompany;

    @NotNull
    private TypeOfInsurance typeOfInsurance;

    @NotNull
    private LocalDate dateOfInsurance;

    @NotNull
    private LocalDate validTill;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public TypeOfInsurance getTypeOfInsurance() {
        return typeOfInsurance;
    }

    public void setTypeOfInsurance(TypeOfInsurance typeOfInsurance) {
        this.typeOfInsurance = typeOfInsurance;
    }

    public LocalDate getDateOfInsurance() {
        return dateOfInsurance;
    }

    public void setDateOfInsurance(LocalDate dateOfInsurance) {
        this.dateOfInsurance = dateOfInsurance;
    }

    public LocalDate getValidTill() {
        return validTill;
    }

    public void setValidTill(LocalDate validTill) {
        this.validTill = validTill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InsuranceDTO insuranceDTO = (InsuranceDTO) o;
        if (insuranceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), insuranceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InsuranceDTO{" +
            "id=" + getId() +
            ", insuranceCompany='" + getInsuranceCompany() + "'" +
            ", typeOfInsurance='" + getTypeOfInsurance() + "'" +
            ", dateOfInsurance='" + getDateOfInsurance() + "'" +
            ", validTill='" + getValidTill() + "'" +
            "}";
    }
}
