package com.synectiks.cms.graphql.types.LegalEntity;

import com.synectiks.cms.entities.LegalEntity;

public class AbstractLegalEntityPayload {
    private final LegalEntity legalEntity;

    public AbstractLegalEntityPayload(LegalEntity legalEntity) {
        this.legalEntity = legalEntity;
    }

    public LegalEntity getLegalEntity() {
        return legalEntity;
    }
}
