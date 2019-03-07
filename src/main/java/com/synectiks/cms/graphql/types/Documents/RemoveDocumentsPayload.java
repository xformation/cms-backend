package com.synectiks.cms.graphql.types.Documents;

import com.synectiks.cms.domain.Documents;

import java.util.List;

public class RemoveDocumentsPayload {

    private final List<Documents> documents ;


    public RemoveDocumentsPayload(List<Documents> documents) {
        this.documents = documents;
    }

    public List<Documents> getDocuments() {
        return documents;
    }
}
