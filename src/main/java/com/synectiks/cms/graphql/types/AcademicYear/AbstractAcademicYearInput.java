package com.synectiks.cms.graphql.types.AcademicYear;

import com.synectiks.cms.domain.enumeration.Status;

import java.util.Date;

public class AbstractAcademicYearInput {
    private Long id;
    private String year;
    private Date startDate;
    private Date endDate;
    private Status status;
    
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AbstractAcademicYearInput{" +
            "id=" + id +
            ", year='" + year + '\'' +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            ", status=" + status +
            '}';
    }
}
