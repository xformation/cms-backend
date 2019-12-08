package com.synectiks.cms.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;

import com.synectiks.cms.entities.enumeration.TypeOfCollege;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the LegalEntity entity.
 */
public class LegalEntityDTO implements Serializable {

    private Long id;

    private String logoPath;

    private String logoFileName;

    private String logoFile;

    @NotNull
    private String legalNameOfTheCollege;

    @NotNull
    private TypeOfCollege typeOfCollege;

    @NotNull
    private LocalDate dateOfIncorporation;

    @NotNull
    private String registeredOfficeAddress1;

    private String registeredOfficeAddress2;

    private String registeredOfficeAddress3;

    private String registeredOfficeAddress4;

    private String registeredOfficeAddress5;

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
    private Long formSignatory;

    @NotNull
    private String pfNumber;

    @NotNull
    private LocalDate pfRegistrationDate;

    @NotNull
    private Long pfSignatory;

    @NotNull
    private String esiNumber;

    @NotNull
    private LocalDate esiRegistrationDate;

    @NotNull
    private Long esiSignatory;

    @NotNull
    private String ptNumber;

    @NotNull
    private LocalDate ptRegistrationDate;

    @NotNull
    private Long ptSignatory;


    private Long branchId;

    private Long collegeId;

    private Long stateId;

    private Long cityId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getLogoFileName() {
        return logoFileName;
    }

    public void setLogoFileName(String logoFileName) {
        this.logoFileName = logoFileName;
    }

    public String getLogoFile() {
        return logoFile;
    }

    public void setLogoFile(String logoFile) {
        this.logoFile = logoFile;
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

    public String getRegisteredOfficeAddress1() {
        return registeredOfficeAddress1;
    }

    public void setRegisteredOfficeAddress1(String registeredOfficeAddress1) {
        this.registeredOfficeAddress1 = registeredOfficeAddress1;
    }

    public String getRegisteredOfficeAddress2() {
        return registeredOfficeAddress2;
    }

    public void setRegisteredOfficeAddress2(String registeredOfficeAddress2) {
        this.registeredOfficeAddress2 = registeredOfficeAddress2;
    }

    public String getRegisteredOfficeAddress3() {
        return registeredOfficeAddress3;
    }

    public void setRegisteredOfficeAddress3(String registeredOfficeAddress3) {
        this.registeredOfficeAddress3 = registeredOfficeAddress3;
    }

    public String getRegisteredOfficeAddress4() {
        return registeredOfficeAddress4;
    }

    public void setRegisteredOfficeAddress4(String registeredOfficeAddress4) {
        this.registeredOfficeAddress4 = registeredOfficeAddress4;
    }

    public String getRegisteredOfficeAddress5() {
        return registeredOfficeAddress5;
    }

    public void setRegisteredOfficeAddress5(String registeredOfficeAddress5) {
        this.registeredOfficeAddress5 = registeredOfficeAddress5;
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

    public Long getFormSignatory() {
        return formSignatory;
    }

    public void setFormSignatory(Long formSignatory) {
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

    public Long getPfSignatory() {
        return pfSignatory;
    }

    public void setPfSignatory(Long pfSignatory) {
        this.pfSignatory = pfSignatory;
    }

    public String getEsiNumber() {
        return esiNumber;
    }

    public void setEsiNumber(String esiNumber) {
        this.esiNumber = esiNumber;
    }

    public LocalDate getEsiRegistrationDate() {
        return esiRegistrationDate;
    }

    public void setEsiRegistrationDate(LocalDate esiRegistrationDate) {
        this.esiRegistrationDate = esiRegistrationDate;
    }

    public Long getEsiSignatory() {
        return esiSignatory;
    }

    public void setEsiSignatory(Long esiSignatory) {
        this.esiSignatory = esiSignatory;
    }

    public String getPtNumber() {
        return ptNumber;
    }

    public void setPtNumber(String ptNumber) {
        this.ptNumber = ptNumber;
    }

    public LocalDate getPtRegistrationDate() {
        return ptRegistrationDate;
    }

    public void setPtRegistrationDate(LocalDate ptRegistrationDate) {
        this.ptRegistrationDate = ptRegistrationDate;
    }

    public Long getPtSignatory() {
        return ptSignatory;
    }

    public void setPtSignatory(Long ptSignatory) {
        this.ptSignatory = ptSignatory;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
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
            ", logoPath='" + getLogoPath() + "'" +
            ", logoFileName='" + getLogoFileName() + "'" +
            ", logoFile='" + getLogoFile() + "'" +
            ", legalNameOfTheCollege='" + getLegalNameOfTheCollege() + "'" +
            ", typeOfCollege='" + getTypeOfCollege() + "'" +
            ", dateOfIncorporation='" + getDateOfIncorporation() + "'" +
            ", registeredOfficeAddress1='" + getRegisteredOfficeAddress1() + "'" +
            ", registeredOfficeAddress2='" + getRegisteredOfficeAddress2() + "'" +
            ", registeredOfficeAddress3='" + getRegisteredOfficeAddress3() + "'" +
            ", registeredOfficeAddress4='" + getRegisteredOfficeAddress4() + "'" +
            ", registeredOfficeAddress5='" + getRegisteredOfficeAddress5() + "'" +
            ", collegeIdentificationNumber='" + getCollegeIdentificationNumber() + "'" +
            ", pan='" + getPan() + "'" +
            ", tan='" + getTan() + "'" +
            ", tanCircleNumber='" + getTanCircleNumber() + "'" +
            ", citTdsLocation='" + getCitTdsLocation() + "'" +
            ", formSignatory=" + getFormSignatory() +
            ", pfNumber='" + getPfNumber() + "'" +
            ", pfRegistrationDate='" + getPfRegistrationDate() + "'" +
            ", pfSignatory=" + getPfSignatory() +
            ", esiNumber='" + getEsiNumber() + "'" +
            ", esiRegistrationDate='" + getEsiRegistrationDate() + "'" +
            ", esiSignatory=" + getEsiSignatory() +
            ", ptNumber='" + getPtNumber() + "'" +
            ", ptRegistrationDate='" + getPtRegistrationDate() + "'" +
            ", ptSignatory=" + getPtSignatory() +
            ", branch=" + getBranchId() +
            ", college=" + getCollegeId() +
            ", state=" + getStateId() +
            ", city=" + getCityId() +
            "}";
    }
}
