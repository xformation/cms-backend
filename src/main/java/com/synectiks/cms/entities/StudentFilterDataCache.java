package com.synectiks.cms.entities;

import java.util.List;

public class StudentFilterDataCache {

	private List<Branch> branches;
	private List<Department> departments;
	private List<Batch> batches;
	private List<Section> sections;
	private List<CmsStudentTypeVo> studentTypes;
	private List<CmsGenderVo> genders;
	
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
	
	public List<Section> getSections() {
		return sections;
	}
	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
	
	public List<Branch> getBranches() {
		return branches;
	}
	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}
	public List<CmsStudentTypeVo> getStudentTypes() {
		return studentTypes;
	}
	public void setStudentTypes(List<CmsStudentTypeVo> studentTypes) {
		this.studentTypes = studentTypes;
	}
	public List<CmsGenderVo> getGenders() {
		return genders;
	}
	public void setGenders(List<CmsGenderVo> genders) {
		this.genders = genders;
	}
	
	
	
}
