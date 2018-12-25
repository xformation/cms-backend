package com.synectiks.cms.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.synectiks.cms.domain.enumeration.ClassPeriods;

/**
 * A DTO for the Periods entity.
 */
public class PeriodsDTO implements Serializable {

    private Long id;

    @NotNull
    private ClassPeriods periods;

    private Long sectionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClassPeriods getPeriods() {
        return periods;
    }

    public void setPeriods(ClassPeriods periods) {
        this.periods = periods;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PeriodsDTO periodsDTO = (PeriodsDTO) o;
        if (periodsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), periodsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PeriodsDTO{" +
            "id=" + getId() +
            ", periods='" + getPeriods() + "'" +
            ", section=" + getSectionId() +
            "}";
    }
}
