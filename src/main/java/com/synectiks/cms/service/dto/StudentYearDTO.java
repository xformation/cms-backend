package com.synectiks.cms.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.synectiks.cms.domain.enumeration.SYear;

/**
 * A DTO for the StudentYear entity.
 */
public class StudentYearDTO implements Serializable {

    private Long id;

    @NotNull
    private SYear yearDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SYear getYearDesc() {
        return yearDesc;
    }

    public void setYearDesc(SYear yearDesc) {
        this.yearDesc = yearDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StudentYearDTO studentYearDTO = (StudentYearDTO) o;
        if (studentYearDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), studentYearDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StudentYearDTO{" +
            "id=" + getId() +
            ", yearDesc='" + getYearDesc() + "'" +
            "}";
    }
}
