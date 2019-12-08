package com.synectiks.cms.graphql.types.Facility;

import java.util.Date;

import com.synectiks.cms.entities.enumeration.Status;

public class AbstractFacilityInput {
    private Long id;
    private String name;
    private Status status;
    private Date startDate;
    private Date endDate;
    private Date suspandStartDate;
    private Date suspandEndDate;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getSuspandStartDate() {
		return suspandStartDate;
	}

	public void setSuspandStartDate(Date suspandStartDate) {
		this.suspandStartDate = suspandStartDate;
	}

	public Date getSuspandEndDate() {
		return suspandEndDate;
	}

	public void setSuspandEndDate(Date suspandEndDate) {
		this.suspandEndDate = suspandEndDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((suspandEndDate == null) ? 0 : suspandEndDate.hashCode());
		result = prime * result + ((suspandStartDate == null) ? 0 : suspandStartDate.hashCode());
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
		AbstractFacilityInput other = (AbstractFacilityInput) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (status != other.status)
			return false;
		if (suspandEndDate == null) {
			if (other.suspandEndDate != null)
				return false;
		} else if (!suspandEndDate.equals(other.suspandEndDate))
			return false;
		if (suspandStartDate == null) {
			if (other.suspandStartDate != null)
				return false;
		} else if (!suspandStartDate.equals(other.suspandStartDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractFacilityInput [id=" + id + ", name=" + name + ", status=" + status + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", suspandStartDate=" + suspandStartDate + ", suspandEndDate="
				+ suspandEndDate + "]";
	}

    
}
