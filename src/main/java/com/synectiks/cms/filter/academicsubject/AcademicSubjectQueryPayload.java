package com.synectiks.cms.filter.academicsubject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AcademicSubjectQueryPayload {
    private int departmentId;
    private int batchId;
    private int branchId;
    
    @JsonProperty("departmentId")
    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @JsonProperty("batchId")
    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    @JsonProperty("branchId")
	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
}
