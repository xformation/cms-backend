package com.synectiks.cms.graphql.types.Holiday;

import com.synectiks.cms.domain.enumeration.Status;

import java.util.Date;
import java.util.Objects;

public class AbstractHolidayInput {
    private Long id;
    private Integer srNo;
    private String sHoliday;
    private Date aDate;
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSrNo() {
        return srNo;
    }

    public void setSrNo(Integer srNo) {
        this.srNo = srNo;
    }

    public String getsHoliday() {
        return sHoliday;
    }

    public void setsHoliday(String sHoliday) {
        this.sHoliday = sHoliday;
    }

    public Date getaDate() {
        return aDate;
    }

    public void setaDate(Date aDate) {
        this.aDate = aDate;
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
            Objects.equals(srNo, that.srNo) &&
            Objects.equals(sHoliday, that.sHoliday) &&
            Objects.equals(aDate, that.aDate) &&
            status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, srNo, sHoliday, aDate, status);
    }

    @Override
    public String toString() {
        return "AbstractHolidayInput{" +
            "id=" + id +
            ", srNo=" + srNo +
            ", sHoliday='" + sHoliday + '\'' +
            ", aDate=" + aDate +
            ", status=" + status +
            '}';
    }
}
