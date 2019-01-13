package com.synectiks.cms.graphql.types.Lecture;

public class UpdateLectureInput extends AbstractLectureInput{
	private Long attendanceMasterId;

	public Long getAttendanceMasterId() {
		return attendanceMasterId;
	}

	public void setAttendanceMasterId(Long attendanceMasterId) {
		this.attendanceMasterId = attendanceMasterId;
	}
}
