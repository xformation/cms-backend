package com.synectiks.cms.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.synectiks.cms.domain.enumeration.TypeOfCollege;

/**
 * A DTO for the LegalEntity entity.
 */
public class LegalEntityDTO implements Serializable {

    private Long id;

    @NotNull
    private Long logo;

    @NotNull
    private String legalNameOfTheCollege;

    @NotNull
    private TypeOfCollege typeOfCollege;

    @NotNull
    private LocalDate dateOfIncorporation;

    @NotNull
    private String registeredOfficeAddress;

    @NotNull
    private String collegeIdentificationNumber;

    @NotNull
    private String pan;

    @NotNull
    private String tan;

    @NotNull
    private String tanCircleNumber;

    @NotNull
    private String citTdsLocation;

    @NotNull
    private String formSignatory;

    @NotNull
    private String pfNumber;

    @NotNull
    private LocalDate pfRegistrationDate;

    @NotNull
    private String pfSignatory;

    @NotNull
    private String pfSignatoryDesignation;

    @NotNull
    private String pfSignatoryFatherName;

    @NotNull
    private Long esiNumber;

    @NotNull
    private LocalDate esiRegistrationDate;

    @NotNull
    private String esiSignatory;

    @NotNull
    private String esiSignatoryDesignation;

    @NotNull
    private String esiSignatoryFatherName;

    @NotNull
    private Long ptNumber;

    @NotNull
    private LocalDate ptRegistrationDate;

    @NotNull
    private String ptSignatory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLogo() {
        return logo;
    }

    public void setLogo(Long logo) {
        this.logo = logo;
    }

    public String getLegalNameOfTheCollege() {
        return legalNameOfTheCollege;
    }

    public void setLegalNameOfTheCollege(String legalNameOfTheCollege) {
        this.legalNameOfTheCollege = legalNameOfTheCollege;
    }

    public TypeOfCollege getTypeOfCollege() {
        return typeOfCollege;
    }

    public void setTypeOfCollege(TypeOfCollege typeOfCollege) {
        this.typeOfCollege = typeOfCollege;
    }

    public LocalDate getDateOfIncorporation() {
        return dateOfIncorporation;
    }

    public void setDateOfIncorporation(LocalDate dateOfIncorporation) {
        this.dateOfIncorporation = dateOfIncorporation;
    }

    public String getRegisteredOfficeAddress() {
        return registeredOfficeAddress;
    }

    public void setRegisteredOfficeAddress(String registeredOfficeAddress) {
        this.registeredOfficeAddress = registeredOfficeAddress;
    }

    public String getCollegeIdentificationNumber() {
        return collegeIdentificationNumber;
    }

    public void setCollegeIdentificationNumber(String collegeIdentificationNumber) {
        this.collegeIdentificationNumber = collegeIdentificationNumber;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getTan() {
        return tan;
    }

    public void setTan(String tan) {
        this.tan = tan;
    }

    public String getTanCircleNumber() {
        return tanCircleNumber;
    }

    public void setTanCircleNumber(String tanCircleNumber) {
        this.tanCircleNumber = tanCircleNumber;
    }

    public String getCitTdsLocation() {
        return citTdsLocation;
    }

    public void setCitTdsLocation(String citTdsLocation) {
        this.citTdsLocation = citTdsLocation;
    }

    public String getFormSignatory() {
        return formSignatory;
    }

    public void setFormSignatory(String formSignatory) {
        this.formSignatory = formSignatory;
    }

    public String getPfNumber() {
        return pfNumber;
    }

    public void setPfNumber(String pfNumber) {
        this.pfNumber = pfNumber;
    }

    public LocalDate getPfRegistrationDate() {
        return pfRegistrationDate;
    }

    public void setPfRegistrationDate(LocalDate pfRegistrationDate) {
        this.pfRegistrationDate = pfRegistrationDate;
    }

    public String getPfSignatory() {
        return pfSignatory;
    }

    public void setPfSignatory(String pfSignatory) {
        this.pfSignatory = pfSignatory;
    }

    public String getPfSignatoryDesignation() {
        return pfSignatoryDesignation;
    }

    public void setPfSignatoryDesignation(String pfSignatoryDesignation) {
        this.pfSignatoryDesignation = pfSignatoryDesignation;
    }

    public String getPfSignatoryFatherName() {
        return pfSignatoryFatherName;
    }

    public void setPfSignatoryFatherName(String pfSignatoryFatherName) {
        this.pfSignatoryFatherName = pfSignatoryFatherName;
    }

    public Long getEsiNumber() {
        return esiNumber;
    }

    public void setEsiNumber(Long esiNumber) {
        this.esiNumber = esiNumber;
    }

    public LocalDate getEsiRegistrationDate() {
        return esiRegistrationDate;
    }

    public void setEsiRegistrationDate(LocalDate esiRegistrationDate) {
        this.esiRegistrationDate = esiRegistrationDate;
    }

    public String getEsiSignatory() {
        return esiSignatory;
    }

    public void setEsiSignatory(String esiSignatory) {
        this.esiSignatory = esiSignatory;
    }

    public String getEsiSignatoryDesignation() {
        return esiSignatoryDesignation;
    }

    public void setEsiSignatoryDesignation(String esiSignatoryDesignation) {
        this.esiSignatoryDesignation = esiSignatoryDesignation;
    }

    public String getEsiSignatoryFatherName() {
        return esiSignatoryFatherName;
    }

    public void setEsiSignatoryFatherName(String esiSignatoryFatherName) {
        this.esiSignatoryFatherName = esiSignatoryFatherName;
    }

    public Long getPtNumber() {
        return ptNumber;
    }

    public void setPtNumber(Long ptNumber) {
        this.ptNumber = ptNumber;
    }

    public LocalDate getPtRegistrationDate() {
        return ptRegistrationDate;
    }

    public void setPtRegistrationDate(LocalDate ptRegistrationDate) {
        this.ptRegistrationDate = ptRegistrationDate;
    }

    public String getPtSignatory() {
        return ptSignatory;
    }

    public void setPtSignatory(String ptSignatory) {
        this.ptSignatory = ptSignatory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LegalEntityDTO legalEntityDTO = (LegalEntityDTO) o;
        if (legalEntityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), legalEntityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LegalEntityDTO{" +
            "id=" + getId() +
            ", logo=" + getLogo() +
            ", legalNameOfTheCollege='" + getLegalNameOfTheCollege() + "'" +
            ", typeOfCollege='" + getTypeOfCollege() + "'" +
            ", dateOfIncorporation='" + getDateOfIncorporation() + "'" +
            ", registeredOfficeAddress='" + getRegisteredOfficeAddress() + "'" +
            ", collegeIdentificationNumber='" + getCollegeIdentificationNumber() + "'" +
            ", pan='" + getPan() + "'" +
            ", tan='" + getTan() + "'" +
            ", tanCircleNumber='" + getTanCircleNumber() + "'" +
            ", citTdsLocation='" + getCitTdsLocation() + "'" +
            ", formSignatory='" + getFormSignatory() + "'" +
            ", pfNumber='" + getPfNumber() + "'" +
            ", pfRegistrationDate='" + getPfRegistrationDate() + "'" +
            ", pfSignatory='" + getPfSignatory() + "'" +
            ", pfSignatoryDesignation='" + getPfSignatoryDesignation() + "'" +
            ", pfSignatoryFatherName='" + getPfSignatoryFatherName() + "'" +
            ", esiNumber=" + getEsiNumber() +
            ", esiRegistrationDate='" + getEsiRegistrationDate() + "'" +
            ", esiSignatory='" + getEsiSignatory() + "'" +
            ", esiSignatoryDesignation='" + getEsiSignatoryDesignation() + "'" +
            ", esiSignatoryFatherName='" + getEsiSignatoryFatherName() + "'" +
            ", ptNumber=" + getPtNumber() +
            ", ptRegistrationDate='" + getPtRegistrationDate() + "'" +
            ", ptSignatory='" + getPtSignatory() + "'" +
            "}";
    }
}
