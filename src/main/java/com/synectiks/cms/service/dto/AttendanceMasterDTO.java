package com.synectiks.cms.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AttendanceMaster entity.
 */
public class AttendanceMasterDTO implements Serializable {

    private Long id;

    private String desc;

    private Long teachId;

    private Long sectionId;

    private Long batchId;

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

    public Long getTeachId() {
        return teachId;
    }

    public void setTeachId(Long teachId) {
        this.teachId = teachId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
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
            ", teach=" + getTeachId() +
            ", section=" + getSectionId() +
            ", batch=" + getBatchId() +
            "}";
    }
}
