package com.synectiks.cms.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.SubTypeEnum;

/**
 * A DTO for the Subject and Teach entity.
 */

public class AcademicSubjectDTO implements Serializable {

    private Long id;

    private String subjectCode;

    private SubTypeEnum subjectType;

    private String subjectDesc;

    private Status status;

    private Long departmentId;

    private Long batchId;

    private Long teacherId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public SubTypeEnum getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(SubTypeEnum subjectType) {
        this.subjectType = subjectType;
    }

    public String getSubjectDesc() {
        return subjectDesc;
    }

    public void setSubjectDesc(String subjectDesc) {
        this.subjectDesc = subjectDesc;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AcademicSubjectDTO subjectDTO = (AcademicSubjectDTO) o;
        if (subjectDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), subjectDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	@Override
	public String toString() {
		return "AcademicSubjectDTO [id=" + id + ", subjectCode=" + subjectCode + ", subjectType=" + subjectType
				+ ", subjectDesc=" + subjectDesc + ", status=" + status + ", departmentId=" + departmentId
				+ ", batchId=" + batchId + ", teacherId=" + teacherId + "]";
	}
}
