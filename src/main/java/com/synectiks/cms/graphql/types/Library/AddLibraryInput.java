package com.synectiks.cms.graphql.types.Library;

import com.synectiks.cms.domain.Batch;

import java.util.Objects;

public class AddLibraryInput extends AbstractLibraryInput {
    private Long batchId;
    private Long subjectId;
    private Long departmentId;

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddLibraryInput)) return false;
        AddLibraryInput that = (AddLibraryInput) o;
        return Objects.equals(getBatchId(), that.getBatchId()) &&
            Objects.equals(getSubjectId(), that.getSubjectId()) &&
            Objects.equals(getDepartmentId(), that.getDepartmentId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(batchId, subjectId);
    }


    @Override
    public String toString() {
        return "AddLibraryInput{" +
            "batchId=" + batchId +
            ", subjectId=" + subjectId +
            ", departmentId=" + departmentId +
            '}';
    }
}
