package com.synectiks.cms.graphql.types.Department;

public class UpdateDepartmentInput extends AbstractDepartmentInput {
	private Long studentId;
    private Long academicyearId;
    private Long branchId;
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Long getAcademicyearId() {
		return academicyearId;
	}
	public void setAcademicyearId(Long academicyearId) {
		this.academicyearId = academicyearId;
	}
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((academicyearId == null) ? 0 : academicyearId.hashCode());
		result = prime * result + ((branchId == null) ? 0 : branchId.hashCode());
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
		if (branchId == null) {
			if (other.branchId != null)
				return false;
		} else if (!branchId.equals(other.branchId))
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
		return "UpdateDepartmentInput [studentId=" + studentId + ", academicyearId=" + academicyearId + ", branchId="
				+ branchId + "]";
	}
	
	
}
