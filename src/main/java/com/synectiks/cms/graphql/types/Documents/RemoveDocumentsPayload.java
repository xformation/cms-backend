package com.synectiks.cms.graphql.types.Documents;

import java.util.List;

import com.synectiks.cms.entities.Documents;

public class RemoveDocumentsPayload {

    private final List<Documents> documents ;


    public RemoveDocumentsPayload(List<Documents> documents) {
        this.documents = documents;
    }

    public List<Documents> getDocuments() {
        return documents;
    }
}
