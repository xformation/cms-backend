package com.synectiks.cms.filter.academicsubject;

public class AcademicSubjectVo {
	private int id;
	private String subjectCode;
	private String subjectType;
	private String subjectDesc;
	private String status;
	private int departmentId;
	private int batchId;
	private int teacherId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getSubjectType() {
		return subjectType;
	}
	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}
	public String getSubjectDesc() {
		return subjectDesc;
	}
	public void setSubjectDesc(String subjectDesc) {
		this.subjectDesc = subjectDesc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public int getBatchId() {
		return batchId;
	}
	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	@Override
	public String toString() {
		return "AcademicSubjectVo [id=" + id + ", subjectCode=" + subjectCode + ", subjectType=" + subjectType
				+ ", subjectDesc=" + subjectDesc + ", status=" + status + ", departmentId=" + departmentId
				+ ", batchId=" + batchId + ", teacherId=" + teacherId + "]";
	}

  
}
