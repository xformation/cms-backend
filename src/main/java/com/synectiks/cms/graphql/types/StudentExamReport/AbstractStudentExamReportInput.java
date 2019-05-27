package com.synectiks.cms.graphql.types.StudentExamReport;

import java.util.Date;
import java.util.Objects;

public class AbstractStudentExamReportInput {
    private Long id;
    private Integer marksObtained;
    private String comments;
    private Date createdOn;
    private String createdBy;
    private Date updatedOn;
    private String updatedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(Integer marksObtained) {
        this.marksObtained = marksObtained;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractStudentExamReportInput that = (AbstractStudentExamReportInput) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(marksObtained, that.marksObtained) &&
            Objects.equals(comments, that.comments) &&
            Objects.equals(createdOn, that.createdOn) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(updatedOn, that.updatedOn) &&
            Objects.equals(updatedBy, that.updatedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, marksObtained, comments, createdOn, createdBy, updatedOn, updatedBy);
    }

    @Override
    public String toString() {
        return "AbstractStudentExamReportInput{" +
            "id=" + id +
            ", marksObtained=" + marksObtained +
            ", comments='" + comments + '\'' +
            ", createdOn=" + createdOn +
            ", createdBy='" + createdBy + '\'' +
            ", updatedOn=" + updatedOn +
            ", updatedBy='" + updatedBy + '\'' +
            '}';
    }
}
