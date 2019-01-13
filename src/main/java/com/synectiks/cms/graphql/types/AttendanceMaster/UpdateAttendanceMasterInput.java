package com.synectiks.cms.graphql.types.AttendanceMaster;

public class UpdateAttendanceMasterInput extends AbstractAttendanceMasterInput{
	private Long teachId;
    private Long sectionId;
    private Long academicYearId;
	public Long getTeachId() {
		return teachId;
	}
	public void setTeachId(Long teachId) {
		this.teachId = teachId;
	}
	public Long getSectionId() {
		return sectionId;
	}
	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}
	public Long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(Long academicYearId) {
		this.academicYearId = academicYearId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((academicYearId == null) ? 0 : academicYearId.hashCode());
		result = prime * result + ((sectionId == null) ? 0 : sectionId.hashCode());
		result = prime * result + ((teachId == null) ? 0 : teachId.hashCode());
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
		UpdateAttendanceMasterInput other = (UpdateAttendanceMasterInput) obj;
		if (academicYearId == null) {
			if (other.academicYearId != null)
				return false;
		} else if (!academicYearId.equals(other.academicYearId))
			return false;
		if (sectionId == null) {
			if (other.sectionId != null)
				return false;
		} else if (!sectionId.equals(other.sectionId))
			return false;
		if (teachId == null) {
			if (other.teachId != null)
				return false;
		} else if (!teachId.equals(other.teachId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UpdateAttendanceMasterInput [teachId=" + teachId + ", sectionId=" + sectionId + ", academicYearId="
				+ academicYearId + "]";
	}
}
