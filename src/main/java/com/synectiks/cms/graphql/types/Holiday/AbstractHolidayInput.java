package com.synectiks.cms.graphql.types.Holiday;

import com.synectiks.cms.domain.enumeration.Status;

import java.util.Date;
import java.util.Objects;

public class AbstractHolidayInput {
    private Long id;
    private String holidayDesc;
    private Date holidayDate;
    private Status status;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractHolidayInput that = (AbstractHolidayInput) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(holidayDesc, that.holidayDesc) &&
            Objects.equals(holidayDate, that.holidayDate) &&
            status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, holidayDesc, holidayDate, status);
    }

    @Override
    public String toString() {
        return "AbstractHolidayInput{" +
            "id=" + id +
            ", holidayDesc='" + holidayDesc + '\'' +
            ", holidayDate=" + holidayDate +
            ", status=" + status +
            '}';
    }
}
