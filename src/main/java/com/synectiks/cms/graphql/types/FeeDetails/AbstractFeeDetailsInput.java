package com.synectiks.cms.graphql.types.FeeDetails;

import java.time.LocalDate;

import com.synectiks.cms.domain.enumeration.Gender;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.StudentTypeEnum;

public class AbstractFeeDetailsInput {
	private Long id;
    private String feeParticularsName;
    private String feeParticularDesc;
    private StudentTypeEnum studentType;
    private Gender gender;
    private Long amount;
    private Status status;
    private String createdBy;
    private LocalDate createdOn;
    private String updatedBy;
    private LocalDate updatedOn;
    private LocalDate startDate;
    private LocalDate endDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFeeParticularsName() {
		return feeParticularsName;
	}
	public void setFeeParticularsName(String feeParticularsName) {
		this.feeParticularsName = feeParticularsName;
	}
	public String getFeeParticularDesc() {
		return feeParticularDesc;
	}
	public void setFeeParticularDesc(String feeParticularDesc) {
		this.feeParticularDesc = feeParticularDesc;
	}
	public StudentTypeEnum getStudentType() {
		return studentType;
	}
	public void setStudentType(StudentTypeEnum studentType) {
		this.studentType = studentType;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDate getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public LocalDate getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(LocalDate updatedOn) {
		this.updatedOn = updatedOn;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((feeParticularDesc == null) ? 0 : feeParticularDesc.hashCode());
		result = prime * result + ((feeParticularsName == null) ? 0 : feeParticularsName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((studentType == null) ? 0 : studentType.hashCode());
		result = prime * result + ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result + ((updatedOn == null) ? 0 : updatedOn.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractFeeDetailsInput other = (AbstractFeeDetailsInput) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (feeParticularDesc == null) {
			if (other.feeParticularDesc != null)
				return false;
		} else if (!feeParticularDesc.equals(other.feeParticularDesc))
			return false;
		if (feeParticularsName == null) {
			if (other.feeParticularsName != null)
				return false;
		} else if (!feeParticularsName.equals(other.feeParticularsName))
			return false;
		if (gender != other.gender)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (status != other.status)
			return false;
		if (studentType != other.studentType)
			return false;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		if (updatedOn == null) {
			if (other.updatedOn != null)
				return false;
		} else if (!updatedOn.equals(other.updatedOn))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AbstractFeeDetailsInput [id=" + id + ", feeParticularsName=" + feeParticularsName
				+ ", feeParticularDesc=" + feeParticularDesc + ", studentType=" + studentType + ", gender=" + gender
				+ ", amount=" + amount + ", status=" + status + ", createdBy=" + createdBy + ", createdOn=" + createdOn
				+ ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
	}

    
}
