package com.synectiks.cms.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CourseOffer entity.
 */
public class CourseOfferDTO implements Serializable {

    private Long id;

    private String desc;

    private Long collegeId;

    private Long departmentId;

    private Long subjectId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CourseOfferDTO courseOfferDTO = (CourseOfferDTO) o;
        if (courseOfferDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), courseOfferDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CourseOfferDTO{" +
            "id=" + getId() +
            ", desc='" + getDesc() + "'" +
            ", college=" + getCollegeId() +
            ", department=" + getDepartmentId() +
            ", subject=" + getSubjectId() +
            "}";
    }
}
