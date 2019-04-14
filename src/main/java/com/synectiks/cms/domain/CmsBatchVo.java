package com.synectiks.cms.domain;

import java.io.Serializable;

import com.synectiks.cms.domain.enumeration.BatchEnum;

/**
 * A Vo for the Batch entity.
 */
public class CmsBatchVo implements Serializable {

    private Long id;
    private BatchEnum batch;
    private Long departmentId;
    private Department department;
    
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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batch == null) ? 0 : batch.hashCode());
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((departmentId == null) ? 0 : departmentId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		CmsBatchVo other = (CmsBatchVo) obj;
		if (batch != other.batch)
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (departmentId == null) {
			if (other.departmentId != null)
				return false;
		} else if (!departmentId.equals(other.departmentId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CmsBatchVo [id=" + id + ", batch=" + batch + ", departmentId=" + departmentId + ", department="
				+ department + "]";
	}
}
