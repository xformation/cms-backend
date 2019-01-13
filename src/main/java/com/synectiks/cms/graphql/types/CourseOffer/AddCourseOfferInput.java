package com.synectiks.cms.graphql.types.CourseOffer;

public class AddCourseOfferInput extends AbstractCourseOfferInput {

	private Long collegeId;
    private Long departmentId;
    private Long subjectId;
	public Long getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((collegeId == null) ? 0 : collegeId.hashCode());
		result = prime * result + ((departmentId == null) ? 0 : departmentId.hashCode());
		result = prime * result + ((subjectId == null) ? 0 : subjectId.hashCode());
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
		AddCourseOfferInput other = (AddCourseOfferInput) obj;
		if (collegeId == null) {
			if (other.collegeId != null)
				return false;
		} else if (!collegeId.equals(other.collegeId))
			return false;
		if (departmentId == null) {
			if (other.departmentId != null)
				return false;
		} else if (!departmentId.equals(other.departmentId))
			return false;
		if (subjectId == null) {
			if (other.subjectId != null)
				return false;
		} else if (!subjectId.equals(other.subjectId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AddCourseOfferInput [collegeId=" + collegeId + ", departmentId=" + departmentId + ", subjectId="
				+ subjectId + "]";
	}
    
}
