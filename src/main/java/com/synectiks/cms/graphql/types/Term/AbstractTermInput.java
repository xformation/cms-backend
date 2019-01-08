package com.synectiks.cms.graphql.types.Term;

import com.synectiks.cms.domain.enumeration.Status;

import java.util.Date;
import java.util.Objects;

public class AbstractTermInput {
    private Long id;
    private Integer srNo;
    private String aTerms;
    private Date startDate;
    private Date endDate;
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

    public String getaTerms() {
        return aTerms;
    }

    public void setaTerms(String aTerms) {
        this.aTerms = aTerms;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractTermInput that = (AbstractTermInput) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(srNo, that.srNo) &&
            Objects.equals(aTerms, that.aTerms) &&
            Objects.equals(startDate, that.startDate) &&
            Objects.equals(endDate, that.endDate) &&
            status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, srNo, aTerms, startDate, endDate, status);
    }

    @Override
    public String toString() {
        return "AbstractTermInput{" +
            "id=" + id +
            ", srNo=" + srNo +
            ", aTerms='" + aTerms + '\'' +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            ", status=" + status +
            '}';
    }
}
