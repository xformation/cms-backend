package com.synectiks.cms.graphql.types.StudentSubject;

import java.util.Date;
import java.util.Objects;

public class AbstractStudentSubjectInput{
    private Long id;
    private String comments;
    private Date lastupdatedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getLastupdatedDate() {
        return lastupdatedDate;
    }

    public void setLastupdatedDate(Date lastupdatedDate) {
        this.lastupdatedDate = lastupdatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractStudentSubjectInput)) return false;
        AbstractStudentSubjectInput that = (AbstractStudentSubjectInput) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getComments(), that.getComments()) &&
            Objects.equals(getLastupdatedDate(), that.getLastupdatedDate());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getComments(), getLastupdatedDate());
    }

    @Override
    public String toString() {
        return "AbstractStudentSubjectInput{" +
            "id=" + id +
            ", comments='" + comments + '\'' +
            ", lastupdatedDate=" + lastupdatedDate +
            '}';
    }
}
