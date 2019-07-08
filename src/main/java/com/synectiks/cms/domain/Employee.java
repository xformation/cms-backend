package com.synectiks.cms.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.synectiks.cms.domain.enumeration.Disability;

/**
 * A Employee.
 */
@Entity
@Table(name = "employee")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    @Column(name = "designation")
    private String designation;

    @NotNull
    @Column(name = "joining_date", nullable = false)
    private LocalDate joiningDate;

    @Column(name = "job_end_date")
    private LocalDate jobEndDate;

    @Column(name = "resignation_date")
    private LocalDate resignationDate;

    @Column(name = "resignation_acceptance_date")
    private LocalDate resignationAcceptanceDate;

    @Column(name = "aadhar_no")
    private String aadharNo;

    @Column(name = "pan_no")
    private String panNo;

    @Column(name = "passport_no")
    private String passportNo;

    @Column(name = "primary_contact_no")
    private String primaryContactNo;

    @Column(name = "secondary_contact_no")
    private String secondaryContactNo;

    @NotNull
    @Column(name = "employee_father_name", nullable = false)
    private String employeeFatherName;

    @NotNull
    @Column(name = "employee_mother_name", nullable = false)
    private String employeeMotherName;

    @NotNull
    @Column(name = "primary_address", nullable = false)
    private String primaryAddress;

    @NotNull
    @Column(name = "secondary_address", nullable = false)
    private String secondaryAddress;

    @Column(name = "employee_address")
    private String employeeAddress;

    @Column(name = "personal_mail_id")
    private String personalMailId;

    @Column(name = "official_mail_id")
    private String officialMailId;

    @Enumerated(EnumType.STRING)
    @Column(name = "disability")
    private Disability disability;

    @Column(name = "driving_licence_no")
    private String drivingLicenceNo;

    @Column(name = "driving_licence_validity")
    private LocalDate drivingLicenceValidity;

    @Column(name = "gender")
    private String gender;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public Employee employeeName(String employeeName) {
        this.employeeName = employeeName;
        return this;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDesignation() {
        return designation;
    }

    public Employee designation(String designation) {
        this.designation = designation;
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public Employee joiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
        return this;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public LocalDate getJobEndDate() {
        return jobEndDate;
    }

    public Employee jobEndDate(LocalDate jobEndDate) {
        this.jobEndDate = jobEndDate;
        return this;
    }

    public void setJobEndDate(LocalDate jobEndDate) {
        this.jobEndDate = jobEndDate;
    }

    public LocalDate getResignationDate() {
        return resignationDate;
    }

    public Employee resignationDate(LocalDate resignationDate) {
        this.resignationDate = resignationDate;
        return this;
    }

    public void setResignationDate(LocalDate resignationDate) {
        this.resignationDate = resignationDate;
    }

    public LocalDate getResignationAcceptanceDate() {
        return resignationAcceptanceDate;
    }

    public Employee resignationAcceptanceDate(LocalDate resignationAcceptanceDate) {
        this.resignationAcceptanceDate = resignationAcceptanceDate;
        return this;
    }

    public void setResignationAcceptanceDate(LocalDate resignationAcceptanceDate) {
        this.resignationAcceptanceDate = resignationAcceptanceDate;
    }

    public String getAadharNo() {
        return aadharNo;
    }

    public Employee aadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
        return this;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    public String getPanNo() {
        return panNo;
    }

    public Employee panNo(String panNo) {
        this.panNo = panNo;
        return this;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public Employee passportNo(String passportNo) {
        this.passportNo = passportNo;
        return this;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getPrimaryContactNo() {
        return primaryContactNo;
    }

    public Employee primaryContactNo(String primaryContactNo) {
        this.primaryContactNo = primaryContactNo;
        return this;
    }

    public void setPrimaryContactNo(String primaryContactNo) {
        this.primaryContactNo = primaryContactNo;
    }

    public String getSecondaryContactNo() {
        return secondaryContactNo;
    }

    public Employee secondaryContactNo(String secondaryContactNo) {
        this.secondaryContactNo = secondaryContactNo;
        return this;
    }

    public void setSecondaryContactNo(String secondaryContactNo) {
        this.secondaryContactNo = secondaryContactNo;
    }

    public String getEmployeeFatherName() {
        return employeeFatherName;
    }

    public Employee employeeFatherName(String employeeFatherName) {
        this.employeeFatherName = employeeFatherName;
        return this;
    }

    public void setEmployeeFatherName(String employeeFatherName) {
        this.employeeFatherName = employeeFatherName;
    }

    public String getEmployeeMotherName() {
        return employeeMotherName;
    }

    public Employee employeeMotherName(String employeeMotherName) {
        this.employeeMotherName = employeeMotherName;
        return this;
    }

    public void setEmployeeMotherName(String employeeMotherName) {
        this.employeeMotherName = employeeMotherName;
    }

    public String getPrimaryAddress() {
        return primaryAddress;
    }

    public Employee primaryAddress(String primaryAddress) {
        this.primaryAddress = primaryAddress;
        return this;
    }

    public void setPrimaryAddress(String primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public String getSecondaryAddress() {
        return secondaryAddress;
    }

    public Employee secondaryAddress(String secondaryAddress) {
        this.secondaryAddress = secondaryAddress;
        return this;
    }

    public void setSecondaryAddress(String secondaryAddress) {
        this.secondaryAddress = secondaryAddress;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public Employee employeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
        return this;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getPersonalMailId() {
        return personalMailId;
    }

    public Employee personalMailId(String personalMailId) {
        this.personalMailId = personalMailId;
        return this;
    }

    public void setPersonalMailId(String personalMailId) {
        this.personalMailId = personalMailId;
    }

    public String getOfficialMailId() {
        return officialMailId;
    }

    public Employee officialMailId(String officialMailId) {
        this.officialMailId = officialMailId;
        return this;
    }

    public void setOfficialMailId(String officialMailId) {
        this.officialMailId = officialMailId;
    }

    public Disability getDisability() {
        return disability;
    }

    public Employee disability(Disability disability) {
        this.disability = disability;
        return this;
    }

    public void setDisability(Disability disability) {
        this.disability = disability;
    }

    public String getDrivingLicenceNo() {
        return drivingLicenceNo;
    }

    public Employee drivingLicenceNo(String drivingLicenceNo) {
        this.drivingLicenceNo = drivingLicenceNo;
        return this;
    }

    public void setDrivingLicenceNo(String drivingLicenceNo) {
        this.drivingLicenceNo = drivingLicenceNo;
    }

    public LocalDate getDrivingLicenceValidity() {
        return drivingLicenceValidity;
    }

    public Employee drivingLicenceValidity(LocalDate drivingLicenceValidity) {
        this.drivingLicenceValidity = drivingLicenceValidity;
        return this;
    }

    public void setDrivingLicenceValidity(LocalDate drivingLicenceValidity) {
        this.drivingLicenceValidity = drivingLicenceValidity;
    }

    public String getGender() {
        return gender;
    }

    public Employee gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
        Employee employee = (Employee) o;
        if (employee.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), employee.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Employee{" +
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
            "}";
    }
}
