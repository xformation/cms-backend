package com.synectiks.cms.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Teach entity.
 */
public class TeachDTO implements Serializable {

    private Long id;

    private String desc;

    private Long subjectId;

    private Long teacherId;

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

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
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

        TeachDTO teachDTO = (TeachDTO) o;
        if (teachDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), teachDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TeachDTO{" +
            "id=" + getId() +
            ", desc='" + getDesc() + "'" +
            ", subject=" + getSubjectId() +
            ", teacher=" + getTeacherId() +
            "}";
    }
}
