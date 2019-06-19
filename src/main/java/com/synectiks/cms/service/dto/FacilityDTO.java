package com.synectiks.cms.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.synectiks.cms.domain.enumeration.Status;

/**
 * A DTO for the Facility entity.
 */
public class FacilityDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Status status;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate suspandStartDate;

    private LocalDate suspandEndDate;

    private Long academicYearId;

    private Long branchId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getSuspandStartDate() {
        return suspandStartDate;
    }

    public void setSuspandStartDate(LocalDate suspandStartDate) {
        this.suspandStartDate = suspandStartDate;
    }

    public LocalDate getSuspandEndDate() {
        return suspandEndDate;
    }

    public void setSuspandEndDate(LocalDate suspandEndDate) {
        this.suspandEndDate = suspandEndDate;
    }

    public Long getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FacilityDTO facilityDTO = (FacilityDTO) o;
        if (facilityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), facilityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FacilityDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", suspandStartDate='" + getSuspandStartDate() + "'" +
            ", suspandEndDate='" + getSuspandEndDate() + "'" +
            ", academicYear=" + getAcademicYearId() +
            ", branch=" + getBranchId() +
            "}";
    }
}
