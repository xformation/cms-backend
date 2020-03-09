package com.synectiks.cms.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Employee entity.
 */
public class EmployeeDTO implements Serializable {

    private Long id;

    private String employeeName;

    private String designation;

    private LocalDate joiningDate;

    private LocalDate jobEndDate;

    private LocalDate resignationDate;

    private LocalDate resignationAcceptanceDate;

    private String aadharNo;

    private String panNo;

    private String passportNo;

    private String primaryContactNo;

    private String secondaryContactNo;

    private String employeeFatherName;

    private String employeeMotherName;

    private String primaryAddress;

    private String secondaryAddress;

    private String employeeAddress;

    private String personalMailId;

    private String officialMailId;

    private String disability;

    private String drivingLicenceNo;

    private LocalDate drivingLicenceValidity;

    private String gender;

    private String typeOfEmployment;

    private Long managerId;

    private String status;

    private String maritalStatus;

    private String staffType;


    private Long branchId;

    private Long transportRouteId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public LocalDate getJobEndDate() {
        return jobEndDate;
    }

    public void setJobEndDate(LocalDate jobEndDate) {
        this.jobEndDate = jobEndDate;
    }

    public LocalDate getResignationDate() {
        return resignationDate;
    }

    public void setResignationDate(LocalDate resignationDate) {
        this.resignationDate = resignationDate;
    }

    public LocalDate getResignationAcceptanceDate() {
        return resignationAcceptanceDate;
    }

    public void setResignationAcceptanceDate(LocalDate resignationAcceptanceDate) {
        this.resignationAcceptanceDate = resignationAcceptanceDate;
    }

    public String getAadharNo() {
        return aadharNo;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getPrimaryContactNo() {
        return primaryContactNo;
    }

    public void setPrimaryContactNo(String primaryContactNo) {
        this.primaryContactNo = primaryContactNo;
    }

    public String getSecondaryContactNo() {
        return secondaryContactNo;
    }

    public void setSecondaryContactNo(String secondaryContactNo) {
        this.secondaryContactNo = secondaryContactNo;
    }

    public String getEmployeeFatherName() {
        return employeeFatherName;
    }

    public void setEmployeeFatherName(String employeeFatherName) {
        this.employeeFatherName = employeeFatherName;
    }

    public String getEmployeeMotherName() {
        return employeeMotherName;
    }

    public void setEmployeeMotherName(String employeeMotherName) {
        this.employeeMotherName = employeeMotherName;
    }

    public String getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(String primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public String getSecondaryAddress() {
        return secondaryAddress;
    }

    public void setSecondaryAddress(String secondaryAddress) {
        this.secondaryAddress = secondaryAddress;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getPersonalMailId() {
        return personalMailId;
    }

    public void setPersonalMailId(String personalMailId) {
        this.personalMailId = personalMailId;
    }

    public String getOfficialMailId() {
        return officialMailId;
    }

    public void setOfficialMailId(String officialMailId) {
        this.officialMailId = officialMailId;
    }

    public String getDisability() {
        return disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

    public String getDrivingLicenceNo() {
        return drivingLicenceNo;
    }

    public void setDrivingLicenceNo(String drivingLicenceNo) {
        this.drivingLicenceNo = drivingLicenceNo;
    }

    public LocalDate getDrivingLicenceValidity() {
        return drivingLicenceValidity;
    }

    public void setDrivingLicenceValidity(LocalDate drivingLicenceValidity) {
        this.drivingLicenceValidity = drivingLicenceValidity;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTypeOfEmployment() {
        return typeOfEmployment;
    }

    public void setTypeOfEmployment(String typeOfEmployment) {
        this.typeOfEmployment = typeOfEmployment;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getStaffType() {
        return staffType;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getTransportRouteId() {
        return transportRouteId;
    }

    public void setTransportRouteId(Long transportRouteId) {
        this.transportRouteId = transportRouteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmployeeDTO employeeDTO = (EmployeeDTO) o;
        if (employeeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), employeeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
            "id=" + getId() +
            ", employeeName='" + getEmployeeName() + "'" +
            ", designation='" + getDesignation() + "'" +
            ", joiningDate='" + getJoiningDate() + "'" +
            ", jobEndDate='" + getJobEndDate() + "'" +
            ", resignationDate='" + getResignationDate() + "'" +
            ", resignationAcceptanceDate='" + getResignationAcceptanceDate() + "'" +
            ", aadharNo='" + getAadharNo() + "'" +
            ", panNo='" + getPanNo() + "'" +
            ", passportNo='" + getPassportNo() + "'" +
            ", primaryContactNo='" + getPrimaryContactNo() + "'" +
            ", secondaryContactNo='" + getSecondaryContactNo() + "'" +
            ", employeeFatherName='" + getEmployeeFatherName() + "'" +
            ", employeeMotherName='" + getEmployeeMotherName() + "'" +
            ", primaryAddress='" + getPrimaryAddress() + "'" +
            ", secondaryAddress='" + getSecondaryAddress() + "'" +
            ", employeeAddress='" + getEmployeeAddress() + "'" +
            ", personalMailId='" + getPersonalMailId() + "'" +
            ", officialMailId='" + getOfficialMailId() + "'" +
            ", disability='" + getDisability() + "'" +
            ", drivingLicenceNo='" + getDrivingLicenceNo() + "'" +
            ", drivingLicenceValidity='" + getDrivingLicenceValidity() + "'" +
            ", gender='" + getGender() + "'" +
            ", typeOfEmployment='" + getTypeOfEmployment() + "'" +
            ", managerId=" + getManagerId() +
            ", status='" + getStatus() + "'" +
            ", maritalStatus='" + getMaritalStatus() + "'" +
            ", staffType='" + getStaffType() + "'" +
            ", branch=" + getBranchId() +
            ", transportRoute=" + getTransportRouteId() +
            "}";
    }
}
