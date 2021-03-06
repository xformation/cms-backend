package com.synectiks.cms.graphql.types.Term;

import com.synectiks.cms.domain.enumeration.Status;

import java.util.Date;
import java.util.Objects;

public class AbstractTermInput {
    private Long id;
    private String termsDesc;
    private Date startDate;
    private Date endDate;
    private Status termStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTermsDesc() {
        return termsDesc;
    }

    public void setTermsDesc(String termsDesc) {
        this.termsDesc = termsDesc;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractTermInput that = (AbstractTermInput) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(termsDesc, that.termsDesc) &&
            Objects.equals(startDate, that.startDate) &&
            Objects.equals(endDate, that.endDate) &&
            termStatus == that.termStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, termsDesc, startDate, endDate, termStatus);
    }

    @Override
    public String toString() {
        return "AbstractTermInput{" +
            "id=" + id +
            ", termsDesc='" + termsDesc + '\'' +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            ", termStatus=" + termStatus +
            '}';
    }

	public Status getTermStatus() {
		return termStatus;
	}

	public void setTermStatus(Status termStatus) {
		this.termStatus = termStatus;
	}
}
