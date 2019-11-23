package com.synectiks.cms.graphql.types.Vehicle;

import com.synectiks.commons.entities.cms.enumeration.Status;

import java.util.Date;
import java.util.Objects;

public class AbstractVehicleInput {
    private Long id;
    private String vehicleNumber;
    private String vehicleType;
    private Long capacity;
    private String ownerShip;
    private Date dateOfRegistration;
    private String yearOfManufacturing;
    private String manufacturingCompany;
    private String model;
    private String chasisNo;
    private String rcNo;
    private String contactNumber;
    private Status status;

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

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractVehicleInput)) return false;
        AbstractVehicleInput that = (AbstractVehicleInput) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getVehicleNumber(), that.getVehicleNumber()) &&
            Objects.equals(getVehicleType(), that.getVehicleType()) &&
            Objects.equals(getCapacity(), that.getCapacity()) &&
            Objects.equals(getOwnerShip(), that.getOwnerShip()) &&
            Objects.equals(getDateOfRegistration(), that.getDateOfRegistration()) &&
            Objects.equals(getYearOfManufacturing(), that.getYearOfManufacturing()) &&
            Objects.equals(getManufacturingCompany(), that.getManufacturingCompany()) &&
            Objects.equals(getModel(), that.getModel()) &&
            Objects.equals(getChasisNo(), that.getChasisNo()) &&
            Objects.equals(getRcNo(), that.getRcNo()) &&
            Objects.equals(getContactNumber(), that.getContactNumber()) &&
            getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getVehicleNumber(), getVehicleType(), getCapacity(), getOwnerShip(), getDateOfRegistration(), getYearOfManufacturing(), getManufacturingCompany(), getModel(), getChasisNo(), getRcNo(), getContactNumber(), getStatus());
    }

    @Override
    public String toString() {
        return "AbstractVehicleInput{" +
            "id=" + id +
            ", vehicleNumber=" + vehicleNumber +
            ", vehicleType='" + vehicleType + '\'' +
            ", capacity=" + capacity +
            ", ownerShip='" + ownerShip + '\'' +
            ", dateOfRegistration=" + dateOfRegistration +
            ", yearOfManufacturing='" + yearOfManufacturing + '\'' +
            ", manufacturingCompany='" + manufacturingCompany + '\'' +
            ", model='" + model + '\'' +
            ", chasisNo='" + chasisNo + '\'' +
            ", rcNo='" + rcNo + '\'' +
            ", contactNumber='" + contactNumber + '\'' +
            ", status=" + status +
            '}';
    }
}
