package com.synectiks.cms.graphql.types.Library;

import com.synectiks.commons.entities.cms.Batch;

import java.util.Objects;

public class AddLibraryInput extends AbstractLibraryInput {
    private Long batchId;
    private Long subjectId;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddLibraryInput)) return false;
        AddLibraryInput that = (AddLibraryInput) o;
        return Objects.equals(getBatchId(), that.getBatchId()) &&
            Objects.equals(getSubjectId(), that.getSubjectId());

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
            '}';
    }
}
