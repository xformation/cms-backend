package com.synectiks.cms.graphql.types.StudentAttendance;

public class AddStudentAttendanceInput extends AbstractStudentAttendanceInput {

    private Long studentId;
    private Long lectureId;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

	public Long getLectureId() {
		return lectureId;
	}

	public void setLectureId(Long lectureId) {
		this.lectureId = lectureId;
	}

	@Override
	public String toString() {
		return "AddStudentAttendanceInput [studentId=" + studentId + ", lectureId=" + lectureId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((lectureId == null) ? 0 : lectureId.hashCode());
		result = prime * result + ((studentId == null) ? 0 : studentId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddStudentAttendanceInput other = (AddStudentAttendanceInput) obj;
		if (lectureId == null) {
			if (other.lectureId != null)
				return false;
		} else if (!lectureId.equals(other.lectureId))
			return false;
		if (studentId == null) {
			if (other.studentId != null)
				return false;
		} else if (!studentId.equals(other.studentId))
			return false;
		return true;
	}
}
