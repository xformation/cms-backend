package com.synectiks.cms.service.dto;

import java.io.Serializable;
import java.util.Objects;
import com.synectiks.cms.domain.enumeration.Semester;

/**
 * A DTO for the AttendanceMaster entity.
 */
public class AttendanceMasterDTO implements Serializable {

    private Long id;

    private String desc;

    private Semester semester;

    private Long batchId;

    private Long sectionId;

    private Long teachId;

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

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Long getTeachId() {
        return teachId;
    }

    public void setTeachId(Long teachId) {
        this.teachId = teachId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AttendanceMasterDTO attendanceMasterDTO = (AttendanceMasterDTO) o;
        if (attendanceMasterDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), attendanceMasterDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AttendanceMasterDTO{" +
            "id=" + getId() +
            ", desc='" + getDesc() + "'" +
            ", semester='" + getSemester() + "'" +
            ", batch=" + getBatchId() +
            ", section=" + getSectionId() +
            ", teach=" + getTeachId() +
            "}";
    }
}
