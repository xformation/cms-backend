package com.synectiks.cms.service.dto;
import javax.validation.constraints.*;

import com.synectiks.cms.entities.enumeration.Status;
import com.synectiks.cms.entities.enumeration.SubTypeEnum;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Subject entity.
 */
public class SubjectDTO implements Serializable {

    private Long id;

    @NotNull
    private String subjectCode;

    @NotNull
    private SubTypeEnum subjectType;

    @NotNull
    private String subjectDesc;

    @NotNull
    private Status status;


    private Long departmentId;

    private Long batchId;

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

        SubjectDTO subjectDTO = (SubjectDTO) o;
        if (subjectDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), subjectDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SubjectDTO{" +
            "id=" + getId() +
            ", subjectCode='" + getSubjectCode() + "'" +
            ", subjectType='" + getSubjectType() + "'" +
            ", subjectDesc='" + getSubjectDesc() + "'" +
            ", status='" + getStatus() + "'" +
            ", department=" + getDepartmentId() +
            ", batch=" + getBatchId() +
            "}";
    }
}
