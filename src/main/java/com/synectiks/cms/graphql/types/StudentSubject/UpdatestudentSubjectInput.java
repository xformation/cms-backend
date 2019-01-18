package com.synectiks.cms.graphql.types.StudentSubject;

import java.util.Objects;

public class UpdatestudentSubjectInput extends AbstractStudentSubjectInput{
    private Long studentId;
    private Long subjectId;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdatestudentSubjectInput)) return false;
        if (!super.equals(o)) return false;
        UpdatestudentSubjectInput that = (UpdatestudentSubjectInput) o;
        return Objects.equals(getStudentId(), that.getStudentId()) &&
            Objects.equals(getSubjectId(), that.getSubjectId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getStudentId(), getSubjectId());
    }

    @Override
    public String toString() {
        return "UpdatestudentSubjectInput{" +
            "studentId=" + studentId +
            ", subjectId=" + subjectId +
            '}';
    }
}
