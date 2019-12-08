package com.synectiks.cms.entities;

import java.util.List;

public class FeeSetupDataCache {

//	private List<Branch> branches;
	private List<Department> departments;
	private List<Batch> batches;
//	private List<Section> sections;
	private List<CmsStudentTypeVo> studentTypes;
	private List<CmsGenderVo> genders;
	
	private List<CmsFeeDetails> feeDetails;
	private List<CmsFeeCategory> feeCategory;
	private List<CmsFacility> facility;
	private List<TransportRoute> transportRoute;
	
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
	
//	public List<Section> getSections() {
//		return sections;
//	}
//	public void setSections(List<Section> sections) {
//		this.sections = sections;
//	}
	
//	public List<Branch> getBranches() {
//		return branches;
//	}
//	public void setBranches(List<Branch> branches) {
//		this.branches = branches;
//	}
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
	public List<CmsFeeDetails> getFeeDetails() {
		return feeDetails;
	}
	public void setFeeDetails(List<CmsFeeDetails> feeDetails) {
		this.feeDetails = feeDetails;
	}
	public List<CmsFeeCategory> getFeeCategory() {
		return feeCategory;
	}
	public void setFeeCategory(List<CmsFeeCategory> feeCategory) {
		this.feeCategory = feeCategory;
	}
	public List<CmsFacility> getFacility() {
		return facility;
	}
	public void setFacility(List<CmsFacility> facility) {
		this.facility = facility;
	}
	public List<TransportRoute> getTransportRoute() {
		return transportRoute;
	}
	public void setTransportRoute(List<TransportRoute> transportRoute) {
		this.transportRoute = transportRoute;
	}
	
	
	
}
