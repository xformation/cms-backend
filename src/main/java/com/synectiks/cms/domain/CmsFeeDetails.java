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


public class CmsFeeDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String feeParticularsName;
    private String feeParticularDesc;
    private StudentTypeEnum studentType;
    private Gender gender;
    private Float amount;
    private Status status;
    private String createdBy;
    private LocalDate createdOn;
    private String updatedBy;
    private LocalDate updatedOn;
    private LocalDate startDate;
    private LocalDate endDate;
    private FeeCategory feeCategory;
    private Long batchId;
    private Facility facility;
    private TransportRoute transportRoute;
    private Long departmentId;

    private String strCreatedOn;
    private String strUpdatedOn;
    private String strStartDate;
    private String strEndDate;
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

    public CmsFeeDetails feeParticularsName(String feeParticularsName) {
        this.feeParticularsName = feeParticularsName;
        return this;
    }

    public void setFeeParticularsName(String feeParticularsName) {
        this.feeParticularsName = feeParticularsName;
    }

    public String getFeeParticularDesc() {
        return feeParticularDesc;
    }

    public CmsFeeDetails feeParticularDesc(String feeParticularDesc) {
        this.feeParticularDesc = feeParticularDesc;
        return this;
    }

    public void setFeeParticularDesc(String feeParticularDesc) {
        this.feeParticularDesc = feeParticularDesc;
    }

    public StudentTypeEnum getStudentType() {
        return studentType;
    }

    public CmsFeeDetails studentType(StudentTypeEnum studentType) {
        this.studentType = studentType;
        return this;
    }

    public void setStudentType(StudentTypeEnum studentType) {
        this.studentType = studentType;
    }

    public Gender getGender() {
        return gender;
    }

    public CmsFeeDetails gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Float getAmount() {
        return amount;
    }

    public CmsFeeDetails amount(Float amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public CmsFeeDetails status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public CmsFeeDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public CmsFeeDetails createdOn(LocalDate createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public CmsFeeDetails updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public CmsFeeDetails updatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public CmsFeeDetails startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public CmsFeeDetails endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public FeeCategory getFeeCategory() {
        return feeCategory;
    }

    public CmsFeeDetails feeCategory(FeeCategory feeCategory) {
        this.feeCategory = feeCategory;
        return this;
    }

    public void setFeeCategory(FeeCategory feeCategory) {
        this.feeCategory = feeCategory;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Facility getFacility() {
        return facility;
    }

    public CmsFeeDetails facility(Facility facility) {
        this.facility = facility;
        return this;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public TransportRoute getTransportRoute() {
        return transportRoute;
    }

    public CmsFeeDetails transportRoute(TransportRoute transportRoute) {
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
        CmsFeeDetails feeDetails = (CmsFeeDetails) o;
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
            "}";
    }

	public String getStrCreatedOn() {
		return strCreatedOn;
	}

	public void setStrCreatedOn(String strCreatedOn) {
		this.strCreatedOn = strCreatedOn;
	}

	public String getStrUpdatedOn() {
		return strUpdatedOn;
	}

	public void setStrUpdatedOn(String strUpdatedOn) {
		this.strUpdatedOn = strUpdatedOn;
	}

	public String getStrStartDate() {
		return strStartDate;
	}

	public void setStrStartDate(String strStartDate) {
		this.strStartDate = strStartDate;
	}

	public String getStrEndDate() {
		return strEndDate;
	}

	public void setStrEndDate(String strEndDate) {
		this.strEndDate = strEndDate;
	}
}
