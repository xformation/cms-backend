package com.synectiks.cms.entities;

import java.util.List;

public class StudentAttendanceCache {

	private List<Department> departments;
	private List<Batch> batches;
	private List<Subject> subjects;
	private List<Section> sections;
	private List<CmsLectureVo> lectures;
	private List<CmsSemesterVo> semesters;
	private List<Teach> teaches;
	private List<AttendanceMaster> attendanceMasters;
	private List<CmsTermVo> terms;
	
	public List<Department> getDepartments() {
		return departments;
	}
	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}
	public List<Batch> getBatches() {
		return batches;
	}
	public void setBatches(List<Batch> batches) {
		this.batches = batches;
	}
	public List<Subject> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	public List<Section> getSections() {
		return sections;
	}
	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
	public List<CmsLectureVo> getLectures() {
		return lectures;
	}
	public void setLectures(List<CmsLectureVo> lectures) {
		this.lectures = lectures;
	}
	public List<CmsSemesterVo> getSemesters() {
		return semesters;
	}
	public void setSemesters(List<CmsSemesterVo> semesters) {
		this.semesters = semesters;
	}
	public List<Teach> getTeaches() {
		return teaches;
	}
	public void setTeaches(List<Teach> teaches) {
		this.teaches = teaches;
	}
	public List<AttendanceMaster> getAttendanceMasters() {
		return attendanceMasters;
	}
	public void setAttendanceMasters(List<AttendanceMaster> attendanceMasters) {
		this.attendanceMasters = attendanceMasters;
	}
	public List<CmsTermVo> getTerms() {
		return terms;
	}
	public void setTerms(List<CmsTermVo> terms) {
		this.terms = terms;
	}
	
	
	
	
	
}
