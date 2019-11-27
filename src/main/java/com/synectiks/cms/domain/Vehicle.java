package com.synectiks.cms.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

//import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.utils.IESEntity;

/**
 * A Vehicle.
 */
@Entity
@Table(name = "vehicle")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//@Document(indexName = "vehicle")
public class Vehicle implements Serializable, IESEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "vehicle_number", nullable = false)
    private String vehicleNumber;

    @NotNull
    @Column(name = "vehicle_type", nullable = false)
    private String vehicleType;

    @NotNull
    @Column(name = "capacity", nullable = false)
    private Long capacity;

    @NotNull
    @Column(name = "owner_ship", nullable = false)
    private String ownerShip;

    @Column(name = "date_of_registration")
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate dateOfRegistration;

    @Column(name = "year_of_manufacturing")
    private String yearOfManufacturing;

    @Column(name = "manufacturing_company")
    private String manufacturingCompany;

    @Column(name = "model")
    private String model;

    @Column(name = "chasis_no")
    private String chasisNo;

    @NotNull
    @Column(name = "rc_no", nullable = false)
    private String rcNo;

    @NotNull
    @Column(name = "contact_number", nullable = false)
    private String contactNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @OneToOne    @JoinColumn(unique = true)
    private Insurance insurance;

    @OneToOne    @JoinColumn(unique = true)
    private Employee employee;

    @ManyToOne
    @JsonIgnoreProperties("")
    private TransportRoute transportRoute;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Contract contract;

    @ManyToOne
    @JsonIgnoreProperties("")
    private College college;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Branch branch;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public Vehicle vehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
        return this;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public Vehicle vehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Long getCapacity() {
        return capacity;
    }

    public Vehicle capacity(Long capacity) {
        this.capacity = capacity;
        return this;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public String getOwnerShip() {
        return ownerShip;
    }

    public Vehicle ownerShip(String ownerShip) {
        this.ownerShip = ownerShip;
        return this;
    }

    public void setOwnerShip(String ownerShip) {
        this.ownerShip = ownerShip;
    }

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public Vehicle dateOfRegistration(LocalDate dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
        return this;
    }

    public void setDateOfRegistration(LocalDate dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getYearOfManufacturing() {
        return yearOfManufacturing;
    }

    public Vehicle yearOfManufacturing(String yearOfManufacturing) {
        this.yearOfManufacturing = yearOfManufacturing;
        return this;
    }

    public void setYearOfManufacturing(String yearOfManufacturing) {
        this.yearOfManufacturing = yearOfManufacturing;
    }

    public String getManufacturingCompany() {
        return manufacturingCompany;
    }

    public Vehicle manufacturingCompany(String manufacturingCompany) {
        this.manufacturingCompany = manufacturingCompany;
        return this;
    }

    public void setManufacturingCompany(String manufacturingCompany) {
        this.manufacturingCompany = manufacturingCompany;
    }

    public String getModel() {
        return model;
    }

    public Vehicle model(String model) {
        this.model = model;
        return this;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getChasisNo() {
        return chasisNo;
    }

    public Vehicle chasisNo(String chasisNo) {
        this.chasisNo = chasisNo;
        return this;
    }

    public void setChasisNo(String chasisNo) {
        this.chasisNo = chasisNo;
    }

    public String getRcNo() {
        return rcNo;
    }

    public Vehicle rcNo(String rcNo) {
        this.rcNo = rcNo;
        return this;
    }

    public void setRcNo(String rcNo) {
        this.rcNo = rcNo;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public Vehicle contactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
        return this;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Status getStatus() {
        return status;
    }

    public Vehicle status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public Vehicle insurance(Insurance insurance) {
        this.insurance = insurance;
        return this;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Vehicle employee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public TransportRoute getTransportRoute() {
        return transportRoute;
    }

    public Vehicle transportRoute(TransportRoute transportRoute) {
        this.transportRoute = transportRoute;
        return this;
    }

    public void setTransportRoute(TransportRoute transportRoute) {
        this.transportRoute = transportRoute;
    }

    public Contract getContract() {
        return contract;
    }

    public Vehicle contract(Contract contract) {
        this.contract = contract;
        return this;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public College getCollege() {
        return college;
    }

    public Vehicle college(College college) {
        this.college = college;
        return this;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public Branch getBranch() {
        return branch;
    }

    public Vehicle branch(Branch branch) {
        this.branch = branch;
        return this;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vehicle vehicle = (Vehicle) o;
        if (vehicle.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vehicle.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Vehicle{" +
            "id=" + getId() +
            ", vehicleNumber='" + getVehicleNumber() + "'" +
            ", vehicleType='" + getVehicleType() + "'" +
            ", capacity=" + getCapacity() +
            ", ownerShip='" + getOwnerShip() + "'" +
            ", dateOfRegistration='" + getDateOfRegistration() + "'" +
            ", yearOfManufacturing='" + getYearOfManufacturing() + "'" +
            ", manufacturingCompany='" + getManufacturingCompany() + "'" +
            ", model='" + getModel() + "'" +
            ", chasisNo='" + getChasisNo() + "'" +
            ", rcNo='" + getRcNo() + "'" +
            ", contactNumber='" + getContactNumber() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
