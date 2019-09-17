package com.synectiks.cms.domain;

import com.synectiks.cms.domain.enumeration.Disability;
import com.synectiks.cms.domain.enumeration.MaritalStatus;
import com.synectiks.cms.domain.enumeration.Status;

import java.time.LocalDate;

public class CmsEmployeeVo {
    private static final long serialVersionUID = 1L;
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
    private Disability disability;
    private String drivingLicenceNo;
    private LocalDate drivingLicenceValidity;
    private String gender;
    private String typeOfEmployment;
    private Long managerId;
    private Status status;
    private MaritalStatus maritalStatus;
    private String strjoiningDate;
    private String strjobEndDate;
    private String strresignationDate;
    private String strresignationAcceptanceDate;
    private String strdrivingLicenceValidity;
    private Branch branch;
    private TransportRoute transportRoute;
    private Vehicle vehicle;
    private Long branchId;
    private Long transportRouteId;
    private Long vehicleId;

    public String getStrdrivingLicenceValidity() {
        return strdrivingLicenceValidity;
    }

    public void setStrdrivingLicenceValidity(String strdrivingLicenceValidity) {
        this.strdrivingLicenceValidity = strdrivingLicenceValidity;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public String getStrjoiningDate() {
        return strjoiningDate;
    }

    public void setStrjoiningDate(String strjoiningDate) {
        this.strjoiningDate = strjoiningDate;
    }

    public String getStrjobEndDate() {
        return strjobEndDate;
    }

    public void setStrjobEndDate(String strjobEndDate) {
        this.strjobEndDate = strjobEndDate;
    }

    public String getStrresignationDate() {
        return strresignationDate;
    }

    public void setStrresignationDate(String strresignationDate) {
        this.strresignationDate = strresignationDate;
    }

    public String getStrresignationAcceptanceDate() {
        return strresignationAcceptanceDate;
    }

    public void setStrresignationAcceptanceDate(String strresignationAcceptanceDate) {
        this.strresignationAcceptanceDate = strresignationAcceptanceDate;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public TransportRoute getTransportRoute() {
        return transportRoute;
    }

    public void setTransportRoute(TransportRoute transportRoute) {
        this.transportRoute = transportRoute;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
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

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }
}
