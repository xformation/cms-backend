package com.synectiks.cms.service.dto;

import java.io.Serializable;

public class LectureScheduleDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String weekDay;
	private String startTime;
    private String endTime;
    private String subjectId;
    private String teacherId;
    private String attendancemasterId;
	public String getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
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
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getAttendancemasterId() {
		return attendancemasterId;
	}
	public void setAttendancemasterId(String attendancemasterId) {
		this.attendancemasterId = attendancemasterId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attendancemasterId == null) ? 0 : attendancemasterId.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((subjectId == null) ? 0 : subjectId.hashCode());
		result = prime * result + ((teacherId == null) ? 0 : teacherId.hashCode());
		result = prime * result + ((weekDay == null) ? 0 : weekDay.hashCode());
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
		LectureScheduleDTO other = (LectureScheduleDTO) obj;
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
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (subjectId == null) {
			if (other.subjectId != null)
				return false;
		} else if (!subjectId.equals(other.subjectId))
			return false;
		if (teacherId == null) {
			if (other.teacherId != null)
				return false;
		} else if (!teacherId.equals(other.teacherId))
			return false;
		if (weekDay == null) {
			if (other.weekDay != null)
				return false;
		} else if (!weekDay.equals(other.weekDay))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "LectureScheduleDTO [weekDay=" + weekDay + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", subjectId=" + subjectId + ", teacherId=" + teacherId + ", attendancemasterId=" + attendancemasterId
				+ "]";
	}

    

	
}
