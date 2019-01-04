package com.synectiks.cms.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AcademicSubject entity.
 */
public class AcademicSubjectDTO implements Serializable {

    private Long id;

    @NotNull
    private String subjectName;

    @NotNull
    private Boolean electiveSub;

    private Long academicDepartmentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Boolean isElectiveSub() {
        return electiveSub;
    }

    public void setElectiveSub(Boolean electiveSub) {
        this.electiveSub = electiveSub;
    }

    public Long getAcademicDepartmentId() {
        return academicDepartmentId;
    }

    public void setAcademicDepartmentId(Long academicDepartmentId) {
        this.academicDepartmentId = academicDepartmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AcademicSubjectDTO academicSubjectDTO = (AcademicSubjectDTO) o;
        if (academicSubjectDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), academicSubjectDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AcademicSubjectDTO{" +
            "id=" + getId() +
            ", subjectName='" + getSubjectName() + "'" +
            ", electiveSub='" + isElectiveSub() + "'" +
            ", academicDepartment=" + getAcademicDepartmentId() +
            "}";
    }
}
