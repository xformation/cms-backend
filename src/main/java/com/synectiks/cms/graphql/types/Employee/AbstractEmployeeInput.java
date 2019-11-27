package com.synectiks.cms.graphql.types.Employee;

import com.synectiks.cms.domain.enumeration.Disability;
import com.synectiks.cms.domain.enumeration.Gender;
import com.synectiks.cms.domain.enumeration.MaritalStatus;
import com.synectiks.cms.domain.enumeration.Status;

import java.util.Date;
import java.util.Objects;

public class AbstractEmployeeInput {
    private Long id;
    private String employeeName;
    private String designation;
    private Date joiningDate;
    private Date jobEndDate;
    private Date resignationDate;
    private Date resignationAcceptanceDate;
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
    private Disability disability;
    private String drivingLicenceNo;
    private Date drivingLicenceValidity;
    private Gender gender;
    private String typeOfEmployment;
    private Long managerId;
    private Status status;
    private MaritalStatus maritalStatus;

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

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Date getJobEndDate() {
        return jobEndDate;
    }

    public void setJobEndDate(Date jobEndDate) {
        this.jobEndDate = jobEndDate;
    }

    public Date getResignationDate() {
        return resignationDate;
    }

    public void setResignationDate(Date resignationDate) {
        this.resignationDate = resignationDate;
    }

    public Date getResignationAcceptanceDate() {
        return resignationAcceptanceDate;
    }

    public void setResignationAcceptanceDate(Date resignationAcceptanceDate) {
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

    public Disability getDisability() {
        return disability;
    }

    public void setDisability(Disability disability) {
        this.disability = disability;
    }

    public String getDrivingLicenceNo() {
        return drivingLicenceNo;
    }

    public void setDrivingLicenceNo(String drivingLicenceNo) {
        this.drivingLicenceNo = drivingLicenceNo;
    }

    public Date getDrivingLicenceValidity() {
        return drivingLicenceValidity;
    }

    public void setDrivingLicenceValidity(Date drivingLicenceValidity) {
        this.drivingLicenceValidity = drivingLicenceValidity;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEmployeeInput)) return false;
        AbstractEmployeeInput that = (AbstractEmployeeInput) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getEmployeeName(), that.getEmployeeName()) &&
            Objects.equals(getDesignation(), that.getDesignation()) &&
            Objects.equals(getJoiningDate(), that.getJoiningDate()) &&
            Objects.equals(getJobEndDate(), that.getJobEndDate()) &&
            Objects.equals(getResignationDate(), that.getResignationDate()) &&
            Objects.equals(getResignationAcceptanceDate(), that.getResignationAcceptanceDate()) &&
            Objects.equals(getAadharNo(), that.getAadharNo()) &&
            Objects.equals(getPanNo(), that.getPanNo()) &&
            Objects.equals(getPassportNo(), that.getPassportNo()) &&
            Objects.equals(getPrimaryContactNo(), that.getPrimaryContactNo()) &&
            Objects.equals(getSecondaryContactNo(), that.getSecondaryContactNo()) &&
            Objects.equals(getEmployeeFatherName(), that.getEmployeeFatherName()) &&
            Objects.equals(getEmployeeMotherName(), that.getEmployeeMotherName()) &&
            Objects.equals(getPrimaryAddress(), that.getPrimaryAddress()) &&
            Objects.equals(getSecondaryAddress(), that.getSecondaryAddress()) &&
            Objects.equals(getEmployeeAddress(), that.getEmployeeAddress()) &&
            Objects.equals(getPersonalMailId(), that.getPersonalMailId()) &&
            Objects.equals(getOfficialMailId(), that.getOfficialMailId()) &&
            getDisability() == that.getDisability() &&
            Objects.equals(getDrivingLicenceNo(), that.getDrivingLicenceNo()) &&
            Objects.equals(getDrivingLicenceValidity(), that.getDrivingLicenceValidity()) &&
            Objects.equals(getGender(), that.getGender()) &&
            Objects.equals(getTypeOfEmployment(), that.getTypeOfEmployment()) &&
            Objects.equals(getManagerId(), that.getManagerId()) &&
            getStatus() == that.getStatus() &&
            getMaritalStatus() == that.getMaritalStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmployeeName(), getDesignation(), getJoiningDate(), getJobEndDate(), getResignationDate(), getResignationAcceptanceDate(), getAadharNo(), getPanNo(), getPassportNo(), getPrimaryContactNo(), getSecondaryContactNo(), getEmployeeFatherName(), getEmployeeMotherName(), getPrimaryAddress(), getSecondaryAddress(), getEmployeeAddress(), getPersonalMailId(), getOfficialMailId(), getDisability(), getDrivingLicenceNo(), getDrivingLicenceValidity(), getGender(), getTypeOfEmployment(), getManagerId(), getStatus(), getMaritalStatus());
    }

    @Override
    public String toString() {
        return "AbstractEmployeeInput{" +
            "id=" + id +
            ", employeeName='" + employeeName + '\'' +
            ", designation='" + designation + '\'' +
            ", joiningDate=" + joiningDate +
            ", jobEndDate=" + jobEndDate +
            ", resignationDate=" + resignationDate +
            ", resignationAcceptanceDate=" + resignationAcceptanceDate +
            ", aadharNo='" + aadharNo + '\'' +
            ", panNo='" + panNo + '\'' +
            ", passportNo='" + passportNo + '\'' +
            ", primaryContactNo='" + primaryContactNo + '\'' +
            ", secondaryContactNo='" + secondaryContactNo + '\'' +
            ", employeeFatherName='" + employeeFatherName + '\'' +
            ", employeeMotherName='" + employeeMotherName + '\'' +
            ", primaryAddress='" + primaryAddress + '\'' +
            ", secondaryAddress='" + secondaryAddress + '\'' +
            ", employeeAddress='" + employeeAddress + '\'' +
            ", personalMailId='" + personalMailId + '\'' +
            ", officialMailId='" + officialMailId + '\'' +
            ", disability=" + disability +
            ", drivingLicenceNo='" + drivingLicenceNo + '\'' +
            ", drivingLicenceValidity=" + drivingLicenceValidity +
            ", gender='" + gender + '\'' +
            ", typeOfEmployment='" + typeOfEmployment + '\'' +
            ", managerId=" + managerId +
            ", status=" + status +
            ", maritalStatus=" + maritalStatus +
            '}';
    }
}
