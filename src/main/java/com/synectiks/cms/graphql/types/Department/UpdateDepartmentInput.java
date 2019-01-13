package com.synectiks.cms.graphql.types.Department;

public class UpdateDepartmentInput extends AbstractDepartmentInput {
	private Long studentId;
    private Long collegeId;
    private Long academicyearId;
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Long getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}
	public Long getAcademicyearId() {
		return academicyearId;
	}
	public void setAcademicyearId(Long academicyearId) {
		this.academicyearId = academicyearId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((academicyearId == null) ? 0 : academicyearId.hashCode());
		result = prime * result + ((collegeId == null) ? 0 : collegeId.hashCode());
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
		UpdateDepartmentInput other = (UpdateDepartmentInput) obj;
		if (academicyearId == null) {
			if (other.academicyearId != null)
				return false;
		} else if (!academicyearId.equals(other.academicyearId))
			return false;
		if (collegeId == null) {
			if (other.collegeId != null)
				return false;
		} else if (!collegeId.equals(other.collegeId))
			return false;
		if (studentId == null) {
			if (other.studentId != null)
				return false;
		} else if (!studentId.equals(other.studentId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UpdateDepartmentInput [studentId=" + studentId + ", collegeId=" + collegeId + ", academicyearId="
				+ academicyearId + "]";
	}
}
