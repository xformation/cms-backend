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
    private String strDateOfInsurance;
    private String strValidTill;

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

    public String getStrDateOfInsurance() {
        return strDateOfInsurance;
    }

    public void setStrDateOfInsurance(String strDateOfInsurance) {
        this.strDateOfInsurance = strDateOfInsurance;
    }

    public String getStrValidTill() {
        return strValidTill;
    }

    public void setStrValidTill(String strValidTill) {
        this.strValidTill = strValidTill;
    }

}
