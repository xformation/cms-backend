package com.synectiks.cms.filter.studentattendance;

import com.fasterxml.jackson.annotation.JsonProperty;


public class StudentAttendanceUpdateFilter {
	
    private String studentIds;
    private long lectureId;
	
    @JsonProperty("studentIds")
	public String getStudentIds() {
		return studentIds;
	}
	public void setStudentIds(String studentIds) {
		this.studentIds = studentIds;
	}

	@JsonProperty("lectureId")
	public long getLectureId() {
		return lectureId;
	}
	public void setLectureId(long lectureId) {
		this.lectureId = lectureId;
	}
         
}
