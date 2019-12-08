package com.synectiks.cms.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

/**
 * A Vo for the Lecture entity.
 */

public class CmsLectureVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6029380770424642966L;
	private Long id;
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate lecDate;
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate lastUpdatedOn;
	private String lastUpdatedBy;
	private String startTime;
	private String endTime;
	private Long attendancemasterId;
	private AttendanceMaster attendancemaster;
	private String strLecDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getLecDate() {
		return lecDate;
	}

	public void setLecDate(LocalDate lecDate) {
		this.lecDate = lecDate;
	}

	public LocalDate getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(LocalDate lastUpdatedOn) {
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

	public Long getAttendancemasterId() {
		return attendancemasterId;
	}

	public void setAttendancemasterId(Long attendanceMasterId) {
		this.attendancemasterId = attendanceMasterId;
	}

	public AttendanceMaster getAttendancemaster() {
		return attendancemaster;
	}

	public void setAttendancemaster(AttendanceMaster attendancemaster) {
		this.attendancemaster = attendancemaster;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((attendancemaster == null) ? 0 : attendancemaster.hashCode());
		result = prime * result
				+ ((attendancemasterId == null) ? 0 : attendancemasterId.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastUpdatedBy == null) ? 0 : lastUpdatedBy.hashCode());
		result = prime * result
				+ ((lastUpdatedOn == null) ? 0 : lastUpdatedOn.hashCode());
		result = prime * result + ((lecDate == null) ? 0 : lecDate.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
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
		CmsLectureVo other = (CmsLectureVo) obj;
		if (attendancemaster == null) {
			if (other.attendancemaster != null)
				return false;
		} else if (!attendancemaster.equals(other.attendancemaster))
			return false;
		if (attendancemasterId == null) {
			if (other.attendancemasterId != null)
				return false;
		} else if (!attendancemasterId.equals(other.attendancemasterId))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastUpdatedBy == null) {
			if (other.lastUpdatedBy != null)
				return false;
		} else if (!lastUpdatedBy.equals(other.lastUpdatedBy))
			return false;
		if (lastUpdatedOn == null) {
			if (other.lastUpdatedOn != null)
				return false;
		} else if (!lastUpdatedOn.equals(other.lastUpdatedOn))
			return false;
		if (lecDate == null) {
			if (other.lecDate != null)
				return false;
		} else if (!lecDate.equals(other.lecDate))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CmsLectureVo [id=" + id + ", lecDate=" + lecDate + ", lastUpdatedOn="
				+ lastUpdatedOn + ", lastUpdatedBy=" + lastUpdatedBy + ", startTime="
				+ startTime + ", endTime=" + endTime + ", attendancemasterId="
				+ attendancemasterId + ", attendancemaster=" + attendancemaster + "]";
	}

	public String getStrLecDate() {
		return strLecDate;
	}

	public void setStrLecDate(String strLecDate) {
		this.strLecDate = strLecDate;
	}

}
