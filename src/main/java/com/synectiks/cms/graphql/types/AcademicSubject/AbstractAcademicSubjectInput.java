package com.synectiks.cms.graphql.types.AcademicSubject;

import java.util.Objects;

public class AbstractAcademicSubjectInput {
    private Long id;
    private String subjectName;
    private Boolean electiveSub;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Boolean getElectiveSub() {
        return electiveSub;
    }

    public void setElectiveSub(Boolean electiveSub) {
        this.electiveSub = electiveSub;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractAcademicSubjectInput)) return false;
        AbstractAcademicSubjectInput that = (AbstractAcademicSubjectInput) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getSubjectName(), that.getSubjectName()) &&
            Objects.equals(getElectiveSub(), that.getElectiveSub());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSubjectName(), getElectiveSub());
    }

    @Override
    public String toString() {
        return "AbstractAcademicSubjectInput{" +
            "id=" + id +
            ", subjectName='" + subjectName + '\'' +
            ", electiveSub=" + electiveSub +
            '}';
    }
}
