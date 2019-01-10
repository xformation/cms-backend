package com.synectiks.cms.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.synectiks.cms.domain.enumeration.CommonSubEnum;
import com.synectiks.cms.domain.enumeration.ElectiveEnum;

/**
 * A DTO for the Subject entity.
 */
public class SubjectDTO implements Serializable {

    private Long id;

    @NotNull
    private CommonSubEnum commonSub;

    @NotNull
    private ElectiveEnum electiveSub;

    private Long departmentId;

    private Long studentId;

    private Long teacherId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CommonSubEnum getCommonSub() {
        return commonSub;
    }

    public void setCommonSub(CommonSubEnum commonSub) {
        this.commonSub = commonSub;
    }

    public ElectiveEnum getElectiveSub() {
        return electiveSub;
    }

    public void setElectiveSub(ElectiveEnum electiveSub) {
        this.electiveSub = electiveSub;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
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
            ", commonSub='" + getCommonSub() + "'" +
            ", electiveSub='" + getElectiveSub() + "'" +
            ", department=" + getDepartmentId() +
            ", student=" + getStudentId() +
            ", teacher=" + getTeacherId() +
            "}";
    }
}
