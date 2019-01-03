package com.synectiks.cms.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AcademicDepartment entity.
 */
public class AcademicDepartmentDTO implements Serializable {

    private Long id;

    @NotNull
    private String departmentName;

    @NotNull
    private String university;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AcademicDepartmentDTO academicDepartmentDTO = (AcademicDepartmentDTO) o;
        if (academicDepartmentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), academicDepartmentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AcademicDepartmentDTO{" +
            "id=" + getId() +
            ", departmentName='" + getDepartmentName() + "'" +
            ", university='" + getUniversity() + "'" +
            "}";
    }
}
