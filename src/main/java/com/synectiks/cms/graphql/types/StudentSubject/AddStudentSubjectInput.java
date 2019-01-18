package com.synectiks.cms.graphql.types.StudentSubject;

import java.util.Objects;

public class AddStudentSubjectInput extends AbstractStudentSubjectInput{
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
        if (!(o instanceof AddStudentSubjectInput)) return false;
        AddStudentSubjectInput that = (AddStudentSubjectInput) o;
        return Objects.equals(getStudentId(), that.getStudentId()) &&
            Objects.equals(subjectId, that.subjectId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(getStudentId(), subjectId);
    }

    @Override
    public String toString() {
        return "AddStudentSubjectInput{" +
            "studentId=" + studentId +
            ", subjectId=" + subjectId +
            '}';
    }
}
