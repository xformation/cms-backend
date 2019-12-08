package com.synectiks.cms.graphql.types.Documents;

import java.util.List;

import com.synectiks.cms.entities.Documents;

public class RemoveDocumentsInput {

    private Long documentsId;

    public Long getDocumentsId() {
        return documentsId;
    }

    public void setDocumentsId(Long documentsId) {
        this.documentsId = documentsId;
    }
}
