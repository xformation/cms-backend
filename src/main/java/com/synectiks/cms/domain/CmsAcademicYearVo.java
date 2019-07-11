package com.synectiks.cms.domain;

import com.synectiks.cms.domain.enumeration.Status;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 * A Vo for the AcademicYear entity.
 */
public class CmsAcademicYearVo implements Serializable {

    private Long id;
    private String year;
    private LocalDate startDate;
    private LocalDate endDate;
    private Status status;

    private String strStartDate;
    private String strEndDate;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getStrStartDate() {
        return strStartDate;
    }

    public void setStrStartDate(String strStartDate) {
        this.strStartDate = strStartDate;
    }

    public String getStrEndDate() {
        return strEndDate;
    }

    public void setStrEndDate(String strEndDate) {
        this.strEndDate = strEndDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CmsAcademicYearVo that = (CmsAcademicYearVo) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(year, that.year) &&
            Objects.equals(startDate, that.startDate) &&
            Objects.equals(endDate, that.endDate) &&
            status == that.status &&
            Objects.equals(strStartDate, that.strStartDate) &&
            Objects.equals(strEndDate, that.strEndDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, year, startDate, endDate, status, strStartDate, strEndDate);
    }

    @Override
    public String toString() {
        return "CmsAcademicYearVo{" +
            "id=" + id +
            ", year='" + year + '\'' +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            ", status=" + status +
            ", strStartDate='" + strStartDate + '\'' +
            ", strEndDate='" + strEndDate + '\'' +
            '}';
    }
}
