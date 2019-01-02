package com.synectiks.cms.graphql.types.LegalEntity;

import com.synectiks.cms.domain.LegalEntity;

import java.util.List;

public class RemoveLegalEntityPayload {
    private final List<LegalEntity> legalEntities;

    public RemoveLegalEntityPayload(List<LegalEntity> legalEntities) {
        this.legalEntities = legalEntities;
    }

    public List<LegalEntity> getLegalEntities() {
        return legalEntities;
    }
}
