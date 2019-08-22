package com.synectiks.cms.graphql.types.Library;

public class UpdateLibraryInput extends AbstractLibraryInput {
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
}
