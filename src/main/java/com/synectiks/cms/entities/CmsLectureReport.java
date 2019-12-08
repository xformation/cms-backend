package com.synectiks.cms.entities;

public class CmsLectureReport {
	public int totalLectures;
	public String departmentName;
	public String teacherName;
	public String subjectDesc;
	public String year;
	public String section;
	public int branchId;
	public int academicYearId;
	public int termId;
	public String branchName;
	public int getTotalLectures() {
		return totalLectures;
	}
	public void setTotalLectures(int totalLectures) {
		this.totalLectures = totalLectures;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getSubjectDesc() {
		return subjectDesc;
	}
	public void setSubjectDesc(String subjectDesc) {
		this.subjectDesc = subjectDesc;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public int getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(int academicYearId) {
		this.academicYearId = academicYearId;
	}
	public int getTermId() {
		return termId;
	}
	public void setTermId(int termId) {
		this.termId = termId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + academicYearId;
		result = prime * result + branchId;
		result = prime * result + ((branchName == null) ? 0 : branchName.hashCode());
		result = prime * result + ((departmentName == null) ? 0 : departmentName.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		result = prime * result + ((subjectDesc == null) ? 0 : subjectDesc.hashCode());
		result = prime * result + ((teacherName == null) ? 0 : teacherName.hashCode());
		result = prime * result + termId;
		result = prime * result + totalLectures;
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		CmsLectureReport other = (CmsLectureReport) obj;
		if (academicYearId != other.academicYearId)
			return false;
		if (branchId != other.branchId)
			return false;
		if (branchName == null) {
			if (other.branchName != null)
				return false;
		} else if (!branchName.equals(other.branchName))
			return false;
		if (departmentName == null) {
			if (other.departmentName != null)
				return false;
		} else if (!departmentName.equals(other.departmentName))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		if (subjectDesc == null) {
			if (other.subjectDesc != null)
				return false;
		} else if (!subjectDesc.equals(other.subjectDesc))
			return false;
		if (teacherName == null) {
			if (other.teacherName != null)
				return false;
		} else if (!teacherName.equals(other.teacherName))
			return false;
		if (termId != other.termId)
			return false;
		if (totalLectures != other.totalLectures)
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CmsLectureReport [totalLectures=" + totalLectures + ", departmentName=" + departmentName
				+ ", teacherName=" + teacherName + ", subjectDesc=" + subjectDesc + ", year=" + year + ", section="
				+ section + ", branchId=" + branchId + ", academicYearId=" + academicYearId + ", termId=" + termId
				+ ", branchName=" + branchName + "]";
	}
}
