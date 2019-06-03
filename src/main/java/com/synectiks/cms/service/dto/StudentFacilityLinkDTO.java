package com.synectiks.cms.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the StudentFacilityLink entity.
 */
public class StudentFacilityLinkDTO implements Serializable {

    private Long id;

    private String linkDesc;

    private Long studentId;

    private Long facilityId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinkDesc() {
        return linkDesc;
    }

    public void setLinkDesc(String linkDesc) {
        this.linkDesc = linkDesc;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StudentFacilityLinkDTO studentFacilityLinkDTO = (StudentFacilityLinkDTO) o;
        if (studentFacilityLinkDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), studentFacilityLinkDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StudentFacilityLinkDTO{" +
            "id=" + getId() +
            ", linkDesc='" + getLinkDesc() + "'" +
            ", student=" + getStudentId() +
            ", facility=" + getFacilityId() +
            "}";
    }
}
