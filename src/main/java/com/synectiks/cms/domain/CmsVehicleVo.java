package com.synectiks.cms.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.synectiks.cms.domain.enumeration.Status;

public class CmsVehicleVo extends CmsCommonVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String vehicleNumber;
    private String vehicleType;
    private Long capacity;
    private String ownerShip;
    private String yearOfManufacturing;
    private String manufacturingCompany;
    private String model;
    private String chasisNo;
    private String rcNo;
    private String contactNumber;
    private Status status;
    private Long transportRouteId;
    private Long collegeId;
    private Long branchId;
    private Long contractId;
    private Long insuranceId;
    private Long employeeId;
    private String strDateOfRegistration;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfRegistration;
    private List<CmsVehicleVo> dataList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public String getOwnerShip() {
        return ownerShip;
    }

    public void setOwnerShip(String ownerShip) {
        this.ownerShip = ownerShip;
    }

    public String getYearOfManufacturing() {
        return yearOfManufacturing;
    }

    public void setYearOfManufacturing(String yearOfManufacturing) {
        this.yearOfManufacturing = yearOfManufacturing;
    }

    public String getManufacturingCompany() {
        return manufacturingCompany;
    }

    public void setManufacturingCompany(String manufacturingCompany) {
        this.manufacturingCompany = manufacturingCompany;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getChasisNo() {
        return chasisNo;
    }

    public void setChasisNo(String chasisNo) {
        this.chasisNo = chasisNo;
    }

    public String getRcNo() {
        return rcNo;
    }

    public void setRcNo(String rcNo) {
        this.rcNo = rcNo;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }


    public Long getTransportRouteId() {
        return transportRouteId;
    }

    public void setTransportRouteId(Long transportRouteId) {
        this.transportRouteId = transportRouteId;
    }

    public String getStrDateOfRegistration() {
        return strDateOfRegistration;
    }

    public void setStrDateOfRegistration(String strDateOfRegistration) {
        this.strDateOfRegistration = strDateOfRegistration;
    }

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDate dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public List<CmsVehicleVo> getDataList() {
        return dataList;
    }

    public void setDataList(List<CmsVehicleVo> dataList) {
        this.dataList = dataList;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "CmsEmployeeVo [" +
            "id=" + id + "," +
            " vehicleNumber=" + vehicleNumber + "," +
            " vehicleType=" + vehicleType + ", " +
            "capacity=" + capacity + ", " +
            "ownerShip=" + ownerShip + ", " +
            "yearOfManufacturing=" + yearOfManufacturing + ", " +
            "manufacturingCompany=" + manufacturingCompany + ", " +
            "model=" + model + ", " +
            "chasisNo=" + chasisNo + ", " +
            "rcNo=" + rcNo + ", " +
            "contactNumber=" + contactNumber + ", " +
            "dateOfRegistration=" + dateOfRegistration + ", " +
            "status=" + status + ", " +
            "strDateOfRegistration=" + strDateOfRegistration + "," +
            "transportRouteId=" + transportRouteId + "," +
            "collegeId=" + collegeId + "," +
            "branchId=" + branchId + "," +
            "contractId=" + contractId + "," +
            "insuranceId=" + insuranceId + "," +
            "employeeId=" + employeeId + "," +
            " dataList=" + dataList + "," +
            " getExitCode()=" + getExitCode() + ", " +
            "getExitDescription()=" + getExitDescription() + ", " +
            "getClass()=" + getClass() + ", " +
            "hashCode()=" + hashCode() + ", " +
            "toString()=" + super.toString() + "]";
    }
}
