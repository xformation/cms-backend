package com.synectiks.cms.graphql.types.Batch;

import com.synectiks.commons.entities.cms.Department;
import com.synectiks.commons.entities.cms.enumeration.BatchEnum;

public class AbstractBatchInput {
    private Long id;
    private BatchEnum batch;
//    private Department department;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BatchEnum getBatch() {
		return batch;
	}
	public void setBatch(BatchEnum batch) {
		this.batch = batch;
	}
	/*public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}*/
	@Override
	public String toString() {
		return "AbstractBatchInput [id=" + id + ", batch=" + batch + "]";
	}
	

    

    
}
