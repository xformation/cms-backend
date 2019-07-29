package com.synectiks.cms.graphql.types.StudentExamReport;

import java.util.Date;
import java.util.Objects;

public class AbstractStudentExamReportInput {
    private Long id;
    private Integer marksObtained;
    private String comments;
    private Integer gOp;

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

    public Integer getgOp() {
        return gOp;
    }

    public void setgOp(Integer gOp) {
        this.gOp = gOp;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        AbstractStudentExamReportInput that = (AbstractStudentExamReportInput) object;
        return java.util.Objects.equals(id, that.id) &&
            java.util.Objects.equals(marksObtained, that.marksObtained) &&
            java.util.Objects.equals(comments, that.comments) &&
            java.util.Objects.equals(gOp, that.gOp);
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), id, marksObtained, comments, gOp);
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "AbstractStudentExamReportInput{" +
            "id=" + id +
            ", marksObtained=" + marksObtained +
            ", comments='" + comments + '\'' +
            ", gOp=" + gOp +
            '}';
    }
}
