package com.synectiks.cms.graphql.types.Holiday;

import com.synectiks.commons.entities.cms.enumeration.Status;

import java.util.Date;
import java.util.Objects;

public class AbstractHolidayInput {
    private Long id;
    private String holidayDesc;
    private Date holidayDate;
    private Status holidayStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHolidayDesc() {
        return holidayDesc;
    }

    public void setHolidayDesc(String holidayDesc) {
        this.holidayDesc = holidayDesc;
    }

    public Date getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(Date holidayDate) {
        this.holidayDate = holidayDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractHolidayInput that = (AbstractHolidayInput) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(holidayDesc, that.holidayDesc) &&
            Objects.equals(holidayDate, that.holidayDate) &&
            holidayStatus == that.holidayStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, holidayDesc, holidayDate, holidayStatus);
    }

    @Override
    public String toString() {
        return "AbstractHolidayInput{" +
            "id=" + id +
            ", holidayDesc='" + holidayDesc + '\'' +
            ", holidayDate=" + holidayDate +
            ", holidayStatus=" + holidayStatus +
            '}';
    }

	public Status getHolidayStatus() {
		return holidayStatus;
	}

	public void setHolidayStatus(Status holidayStatus) {
		this.holidayStatus = holidayStatus;
	}
}
