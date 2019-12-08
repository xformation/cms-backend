package com.synectiks.cms.graphql.types.Book;

import java.util.Date;
import java.util.Objects;

import com.synectiks.cms.entities.enumeration.StatusEnum;

public class AbstractBookInput {
    private Long id;
    private Date issueDate;
    private Date dueDate;
    private Integer noOfCopiesAvailable;
    private StatusEnum status;
    private Date receivedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getNoOfCopiesAvailable() {
        return noOfCopiesAvailable;
    }

    public void setNoOfCopiesAvailable(Integer noOfCopiesAvailable) {
        this.noOfCopiesAvailable = noOfCopiesAvailable;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractBookInput)) return false;
        AbstractBookInput that = (AbstractBookInput) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(issueDate, that.issueDate) &&
            Objects.equals(dueDate, that.dueDate) &&
            Objects.equals(noOfCopiesAvailable, that.noOfCopiesAvailable) &&
            status == that.status &&
            Objects.equals(receivedDate, that.receivedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, issueDate, dueDate, noOfCopiesAvailable, status, receivedDate);
    }

    @Override
    public String toString() {
        return "AbstractBookInput{" +
            "id=" + id +
            ", issueDate=" + issueDate +
            ", dueDate=" + dueDate +
            ", noOfCopiesAvailable=" + noOfCopiesAvailable +
            ", status=" + status +
            ", receivedDate=" + receivedDate +
            '}';
    }
}
