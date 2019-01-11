package com.synectiks.cms.graphql.types.LegalEntity;

import com.synectiks.cms.domain.enumeration.TypeOfCollege;

import java.util.Date;

public class AbstractLegalEntityInput {
    private Long id;
    private Long logo;
    private String legalNameOfTheCollege;
    private TypeOfCollege typeOfCollege;
    private Date dateOfIncorporation;
    private String registeredOfficeAddress;
    private String collegeIdentificationNumber;
    private String pan;
    private String tan;
    private String tanCircleNumber;
    private String citTdsLocation;
    private String formSignatory;
    private String pfNumber;
    private Date registrationDate;
    private Long esiNumber;
    private Date ptRegistrationDate;
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

    public Date getDateOfIncorporation() {
        return dateOfIncorporation;
    }

    public void setDateOfIncorporation(Date dateOfIncorporation) {
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Long getEsiNumber() {
        return esiNumber;
    }

    public void setEsiNumber(Long esiNumber) {
        this.esiNumber = esiNumber;
    }

    public Date getPtRegistrationDate() {
        return ptRegistrationDate;
    }

    public void setPtRegistrationDate(Date ptRegistrationDate) {
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
