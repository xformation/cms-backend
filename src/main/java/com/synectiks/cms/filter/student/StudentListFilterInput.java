package com.synectiks.cms.filter.student;

import com.fasterxml.jackson.annotation.JsonProperty;


public class StudentListFilterInput {

	private String branchId;
	private String departmentId;
	private String batchId;
	private String sectionId;
    private String gender;
    private String studentType;
    
    @JsonProperty("branchId")
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	
	@JsonProperty("departmentId")
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	
	@JsonProperty("batchId")
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	
	@JsonProperty("sectionId")
	public String getSectionId() {
		return sectionId;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	
	@JsonProperty("gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@JsonProperty("studentType")
	public String getStudentType() {
		return studentType;
	}
	public void setStudentType(String studentType) {
		this.studentType = studentType;
	}
	

        
}
