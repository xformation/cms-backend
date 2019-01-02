package com.synectiks.cms.graphql.types.LegalEntity;

import com.synectiks.cms.domain.AuthorizedSignatory;
import com.synectiks.cms.domain.enumeration.TypeOfCollege;

import java.time.LocalDate;
import java.util.Objects;

public class AbstractLegalEntityInput {
    private Long id;
    private Long logo;
    private String legalNameOfTheCollege;
    private TypeOfCollege typeOfCollege;
    private LocalDate dateOfIncorporation;
    private String registeredOfficeAddress;
    private String collegeIdentificationNumber;
    private String pan;
    private String tan;
    private String tanCircleNumber;
    private String citTdsLocation;
    private String formSignatory;
    private String pfNumber;
    private LocalDate registrationDate;
    private Long esiNumber;
    private LocalDate ptRegistrationDate;
    private String ptSignatory;
    private Long ptNumber;

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

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Long getEsiNumber() {
        return esiNumber;
    }

    public void setEsiNumber(Long esiNumber) {
        this.esiNumber = esiNumber;
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

    public Long getPtNumber() {
        return ptNumber;
    }

    public void setPtNumber(Long ptNumber) {
        this.ptNumber = ptNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractLegalEntityInput that = (AbstractLegalEntityInput) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(logo, that.logo) &&
            Objects.equals(legalNameOfTheCollege, that.legalNameOfTheCollege) &&
            typeOfCollege == that.typeOfCollege &&
            Objects.equals(dateOfIncorporation, that.dateOfIncorporation) &&
            Objects.equals(registeredOfficeAddress, that.registeredOfficeAddress) &&
            Objects.equals(collegeIdentificationNumber, that.collegeIdentificationNumber) &&
            Objects.equals(pan, that.pan) &&
            Objects.equals(tan, that.tan) &&
            Objects.equals(tanCircleNumber, that.tanCircleNumber) &&
            Objects.equals(citTdsLocation, that.citTdsLocation) &&
            Objects.equals(formSignatory, that.formSignatory) &&
            Objects.equals(pfNumber, that.pfNumber) &&
            Objects.equals(registrationDate, that.registrationDate) &&
            Objects.equals(esiNumber, that.esiNumber) &&
            Objects.equals(ptRegistrationDate, that.ptRegistrationDate) &&
            Objects.equals(ptSignatory, that.ptSignatory) &&
            Objects.equals(ptNumber, that.ptNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, logo, legalNameOfTheCollege, typeOfCollege, dateOfIncorporation, registeredOfficeAddress, collegeIdentificationNumber, pan, tan, tanCircleNumber, citTdsLocation, formSignatory, pfNumber, registrationDate, esiNumber, ptRegistrationDate, ptSignatory, ptNumber);
    }

    @Override
    public String toString() {
        return "AbstractLegalEntityInput{" +
            "id=" + id +
            ", logo=" + logo +
            ", legalNameOfTheCollege='" + legalNameOfTheCollege + '\'' +
            ", typeOfCollege=" + typeOfCollege +
            ", dateOfIncorporation=" + dateOfIncorporation +
            ", registeredOfficeAddress='" + registeredOfficeAddress + '\'' +
            ", collegeIdentificationNumber='" + collegeIdentificationNumber + '\'' +
            ", pan='" + pan + '\'' +
            ", tan='" + tan + '\'' +
            ", tanCircleNumber='" + tanCircleNumber + '\'' +
            ", citTdsLocation='" + citTdsLocation + '\'' +
            ", formSignatory='" + formSignatory + '\'' +
            ", pfNumber='" + pfNumber + '\'' +
            ", registrationDate=" + registrationDate +
            ", esiNumber=" + esiNumber +
            ", ptRegistrationDate=" + ptRegistrationDate +
            ", ptSignatory='" + ptSignatory + '\'' +
            ", ptNumber=" + ptNumber +
            '}';
    }
}
