package com.synectiks.cms.domain;

import java.io.Serializable;
import java.time.LocalDate;

import com.synectiks.cms.domain.enumeration.Status;


public class CmsFacility implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Status status;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate suspandStartDate;
    private LocalDate suspandEndDate;
    private AcademicYear academicYear;
    private Branch branch;

    private String strStartDate;
    private String strEndDate;
    private String strSuspandStartDate;
    private String strSuspandEndDate;
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
	public LocalDate getSuspandStartDate() {
		return suspandStartDate;
	}
	public void setSuspandStartDate(LocalDate suspandStartDate) {
		this.suspandStartDate = suspandStartDate;
	}
	public LocalDate getSuspandEndDate() {
		return suspandEndDate;
	}
	public void setSuspandEndDate(LocalDate suspandEndDate) {
		this.suspandEndDate = suspandEndDate;
	}
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
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
	public String getStrSuspandStartDate() {
		return strSuspandStartDate;
	}
	public void setStrSuspandStartDate(String strSuspandStartDate) {
		this.strSuspandStartDate = strSuspandStartDate;
	}
	public String getStrSuspandEndDate() {
		return strSuspandEndDate;
	}
	public void setStrSuspandEndDate(String strSuspandEndDate) {
		this.strSuspandEndDate = strSuspandEndDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((academicYear == null) ? 0 : academicYear.hashCode());
		result = prime * result + ((branch == null) ? 0 : branch.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((strEndDate == null) ? 0 : strEndDate.hashCode());
		result = prime * result + ((strStartDate == null) ? 0 : strStartDate.hashCode());
		result = prime * result + ((strSuspandEndDate == null) ? 0 : strSuspandEndDate.hashCode());
		result = prime * result + ((strSuspandStartDate == null) ? 0 : strSuspandStartDate.hashCode());
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
		CmsFacility other = (CmsFacility) obj;
		if (academicYear == null) {
			if (other.academicYear != null)
				return false;
		} else if (!academicYear.equals(other.academicYear))
			return false;
		if (branch == null) {
			if (other.branch != null)
				return false;
		} else if (!branch.equals(other.branch))
			return false;
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
		if (strEndDate == null) {
			if (other.strEndDate != null)
				return false;
		} else if (!strEndDate.equals(other.strEndDate))
			return false;
		if (strStartDate == null) {
			if (other.strStartDate != null)
				return false;
		} else if (!strStartDate.equals(other.strStartDate))
			return false;
		if (strSuspandEndDate == null) {
			if (other.strSuspandEndDate != null)
				return false;
		} else if (!strSuspandEndDate.equals(other.strSuspandEndDate))
			return false;
		if (strSuspandStartDate == null) {
			if (other.strSuspandStartDate != null)
				return false;
		} else if (!strSuspandStartDate.equals(other.strSuspandStartDate))
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
		return "CmsFacility [id=" + id + ", name=" + name + ", status=" + status + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", suspandStartDate=" + suspandStartDate + ", suspandEndDate="
				+ suspandEndDate + ", academicYear=" + academicYear + ", branch=" + branch + ", strStartDate="
				+ strStartDate + ", strEndDate=" + strEndDate + ", strSuspandStartDate=" + strSuspandStartDate
				+ ", strSuspandEndDate=" + strSuspandEndDate + "]";
	}
    
    
}
