package com.synectiks.cms.graphql.types.AcademicYear;

import java.util.Date;

public class AbstractAcademicYearInput {
    private Long id;
    private Long year;
    private Date startDate;
    private Date endDate;
    private String desc;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
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

    @Override
    public String toString() {
        return "AbstractAcademicYearInput{" +
            "id=" + id +
            ", year=" + year +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            '}';
    }

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
