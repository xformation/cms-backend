package com.synectiks.cms.graphql.types.Documents;

import com.synectiks.cms.domain.Documents;

import java.util.List;

public class RemoveDocumentsInput {

    private Long documentsId;

    public Long getDocumentsId() {
        return documentsId;
    }

    public void setDocumentsId(Long documentsId) {
        this.documentsId = documentsId;
    }
}