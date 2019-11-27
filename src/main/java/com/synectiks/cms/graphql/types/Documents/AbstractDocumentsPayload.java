package com.synectiks.cms.graphql.types.Documents;

import com.synectiks.cms.domain.Documents;

public class AbstractDocumentsPayload {

    private final Documents documents;

    public AbstractDocumentsPayload(Documents documents) {
        this.documents = documents;
    }

    public Documents getDocuments() {
        return documents;
    }
}
