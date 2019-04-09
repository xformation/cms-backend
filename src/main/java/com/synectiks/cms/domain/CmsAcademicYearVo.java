package com.synectiks.cms.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * A Vo for the AcademicYear entity.
 */
public class CmsAcademicYearVo implements Serializable {

    private Long id;
    private String year;
    private String startDate;
    private String endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CmsAcademicYearVo academicYearDTO = (CmsAcademicYearVo) o;
        if (academicYearDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), academicYearDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AcademicYearDTO{" +
            "id=" + getId() +
            ", year='" + getYear() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            "}";
    }
}
