package com.synectiks.cms.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.synectiks.cms.domain.enumeration.StudentTypeEnum;

import com.synectiks.cms.domain.enumeration.Gender;

import com.synectiks.cms.domain.enumeration.Status;

/**
 * A FeeDetails.
 */
@Entity
@Table(name = "fee_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "feedetails")
public class FeeDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "fee_particulars_name", nullable = false)
    private String feeParticularsName;

    @NotNull
    @Column(name = "fee_particular_desc", nullable = false)
    private String feeParticularDesc;

    @Enumerated(EnumType.STRING)
    @Column(name = "student_type")
    private StudentTypeEnum studentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "amount")
    private Float amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private LocalDate createdOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    private LocalDate updatedOn;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "batch_id")
    private Long batchId;

    @Column(name = "department_id")
    private Long departmentId;

    @ManyToOne
    @JsonIgnoreProperties("feeDetails")
    private FeeCategory feeCategory;

    @ManyToOne
    @JsonIgnoreProperties("feeDetails")
    private Facility facility;

    @ManyToOne
    @JsonIgnoreProperties("feeDetails")
    private TransportRoute transportRoute;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeeParticularsName() {
        return feeParticularsName;
    }

    public FeeDetails feeParticularsName(String feeParticularsName) {
        this.feeParticularsName = feeParticularsName;
        return this;
    }

    public void setFeeParticularsName(String feeParticularsName) {
        this.feeParticularsName = feeParticularsName;
    }

    public String getFeeParticularDesc() {
        return feeParticularDesc;
    }

    public FeeDetails feeParticularDesc(String feeParticularDesc) {
        this.feeParticularDesc = feeParticularDesc;
        return this;
    }

    public void setFeeParticularDesc(String feeParticularDesc) {
        this.feeParticularDesc = feeParticularDesc;
    }

    public StudentTypeEnum getStudentType() {
        return studentType;
    }

    public FeeDetails studentType(StudentTypeEnum studentType) {
        this.studentType = studentType;
        return this;
    }

    public void setStudentType(StudentTypeEnum studentType) {
        this.studentType = studentType;
    }

    public Gender getGender() {
        return gender;
    }

    public FeeDetails gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Float getAmount() {
        return amount;
    }

    public FeeDetails amount(Float amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public FeeDetails status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public FeeDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public FeeDetails createdOn(LocalDate createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public FeeDetails updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public FeeDetails updatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public FeeDetails startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public FeeDetails endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getBatchId() {
        return batchId;
    }

    public FeeDetails batchId(Long batchId) {
        this.batchId = batchId;
        return this;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public FeeDetails departmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public FeeCategory getFeeCategory() {
        return feeCategory;
    }

    public FeeDetails feeCategory(FeeCategory feeCategory) {
        this.feeCategory = feeCategory;
        return this;
    }

    public void setFeeCategory(FeeCategory feeCategory) {
        this.feeCategory = feeCategory;
    }

    public Facility getFacility() {
        return facility;
    }

    public FeeDetails facility(Facility facility) {
        this.facility = facility;
        return this;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public TransportRoute getTransportRoute() {
        return transportRoute;
    }

    public FeeDetails transportRoute(TransportRoute transportRoute) {
        this.transportRoute = transportRoute;
        return this;
    }

    public void setTransportRoute(TransportRoute transportRoute) {
        this.transportRoute = transportRoute;
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
        FeeDetails feeDetails = (FeeDetails) o;
        if (feeDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), feeDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FeeDetails{" +
            "id=" + getId() +
            ", feeParticularsName='" + getFeeParticularsName() + "'" +
            ", feeParticularDesc='" + getFeeParticularDesc() + "'" +
            ", studentType='" + getStudentType() + "'" +
            ", gender='" + getGender() + "'" +
            ", amount=" + getAmount() +
            ", status='" + getStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", batchId=" + getBatchId() +
            ", departmentId=" + getDepartmentId() +
            "}";
    }
}
