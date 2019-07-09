package com.synectiks.cms.graphql.types.Insurance;

import com.synectiks.cms.domain.enumeration.TypeOfInsurance;

import java.util.Date;
import java.util.Objects;

public class AbstractInsuranceInput {
    private Long id;
    private String insuranceCompany;
    private TypeOfInsurance typeOfInsurance;
    private Date dateOfInsurance;
    private Date validTill;

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

    public Date getDateOfInsurance() {
        return dateOfInsurance;
    }

    public void setDateOfInsurance(Date dateOfInsurance) {
        this.dateOfInsurance = dateOfInsurance;
    }

    public Date getValidTill() {
        return validTill;
    }

    public void setValidTill(Date validTill) {
        this.validTill = validTill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractInsuranceInput)) return false;
        AbstractInsuranceInput that = (AbstractInsuranceInput) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getInsuranceCompany(), that.getInsuranceCompany()) &&
            getTypeOfInsurance() == that.getTypeOfInsurance() &&
            Objects.equals(getDateOfInsurance(), that.getDateOfInsurance()) &&
            Objects.equals(getValidTill(), that.getValidTill());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getInsuranceCompany(), getTypeOfInsurance(), getDateOfInsurance(), getValidTill());
    }

    @Override
    public String toString() {
        return "AbstractInsuranceInput{" +
            "id=" + id +
            ", insuranceCompany='" + insuranceCompany + '\'' +
            ", typeOfInsurance=" + typeOfInsurance +
            ", dateOfInsurance=" + dateOfInsurance +
            ", validTill=" + validTill +
            '}';
    }
}
