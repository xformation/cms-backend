package com.synectiks.cms.filter.studentattendance;

import com.fasterxml.jackson.annotation.JsonProperty;


public class StudentAttendanceUpdateFilter {
	
    private String studentIds;
    private String lectureId;
	
    @JsonProperty("studentIds")
	public String getStudentIds() {
		return studentIds;
	}
	public void setStudentIds(String studentIds) {
		this.studentIds = studentIds;
	}

	@JsonProperty("lectureId")
	public String getLectureId() {
		return lectureId;
	}
	public void setLectureId(String lectureId) {
		this.lectureId = lectureId;
	}
         
}
