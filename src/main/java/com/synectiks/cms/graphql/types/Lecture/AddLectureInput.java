package com.synectiks.cms.graphql.types.Lecture;

public class AddLectureInput extends AbstractLectureInput {
   
	private Long attendanceMasterId;

	public Long getAttendanceMasterId() {
		return attendanceMasterId;
	}

	public void setAttendanceMasterId(Long attendanceMasterId) {
		this.attendanceMasterId = attendanceMasterId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attendanceMasterId == null) ? 0 : attendanceMasterId.hashCode());
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
		AddLectureInput other = (AddLectureInput) obj;
		if (attendanceMasterId == null) {
			if (other.attendanceMasterId != null)
				return false;
		} else if (!attendanceMasterId.equals(other.attendanceMasterId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AddLectureInput [attendanceMasterId=" + attendanceMasterId + "]";
	}
}
