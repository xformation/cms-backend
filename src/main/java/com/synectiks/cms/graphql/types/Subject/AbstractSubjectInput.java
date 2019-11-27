package com.synectiks.cms.graphql.types.Subject;

import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.SubTypeEnum;

import java.util.Objects;

public class AbstractSubjectInput {
    private Long id;
    private String subjectCode;
    private SubTypeEnum subjectType;
    private String subjectDesc;
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public SubTypeEnum getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(SubTypeEnum subjectType) {
        this.subjectType = subjectType;
    }

    public String getSubjectDesc() {
        return subjectDesc;
    }

    public void setSubjectDesc(String subjectDesc) {
        this.subjectDesc = subjectDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractSubjectInput that = (AbstractSubjectInput) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(subjectCode, that.subjectCode) &&
            subjectType == that.subjectType &&
            Objects.equals(subjectDesc, that.subjectDesc) &&
            status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subjectCode, subjectType, subjectDesc, status);
    }

    @Override
    public String toString() {
        return "AbstractSubjectInput{" +
            "id=" + id +
            ", subjectCode='" + subjectCode + '\'' +
            ", subjectType=" + subjectType +
            ", subjectDesc='" + subjectDesc + '\'' +
            ", status=" + status +
            '}';
    }
}
