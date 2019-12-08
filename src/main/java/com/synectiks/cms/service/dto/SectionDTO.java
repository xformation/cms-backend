package com.synectiks.cms.service.dto;
import javax.validation.constraints.*;

import com.synectiks.cms.entities.enumeration.SectionEnum;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Section entity.
 */
public class SectionDTO implements Serializable {

    private Long id;

    @NotNull
    private SectionEnum section;


    private Long batchId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SectionEnum getSection() {
        return section;
    }

    public void setSection(SectionEnum section) {
        this.section = section;
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

        SectionDTO sectionDTO = (SectionDTO) o;
        if (sectionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sectionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SectionDTO{" +
            "id=" + getId() +
            ", section='" + getSection() + "'" +
            ", batch=" + getBatchId() +
            "}";
    }
}
