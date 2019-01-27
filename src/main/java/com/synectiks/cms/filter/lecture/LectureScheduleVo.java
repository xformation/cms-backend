package com.synectiks.cms.filter.lecture;

import java.math.BigInteger;
import java.sql.Date;

public class LectureScheduleVo {
	private BigInteger id;
	private Date lecDate;
	private Date lastUpdatedOn;
	private String lastUpdatedBy;
	private String startTime;
	private String endTime;
	private BigInteger attendanceMasterId;
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public Date getLecDate() {
		return lecDate;
	}
	public void setLecDate(Date lecDate) {
		this.lecDate = lecDate;
	}
	public Date getLastUpdatedOn() {
		return lastUpdatedOn;
	}
	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public BigInteger getAttendanceMasterId() {
		return attendanceMasterId;
	}
	public void setAttendanceMasterId(BigInteger attendanceMasterId) {
		this.attendanceMasterId = attendanceMasterId;
	}
	
	@Override
	public String toString() {
		return "LectureScheduleVo [id=" + id + ", lecDate=" + lecDate + ", lastUpdatedOn=" + lastUpdatedOn
				+ ", lastUpdatedBy=" + lastUpdatedBy + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", attendanceMasterId=" + attendanceMasterId + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lecDate == null) ? 0 : lecDate.hashCode());
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
		LectureScheduleVo other = (LectureScheduleVo) obj;
		if (lecDate == null) {
			if (other.lecDate != null)
				return false;
		} else if (!lecDate.equals(other.lecDate))
			return false;
		return true;
	}
}
