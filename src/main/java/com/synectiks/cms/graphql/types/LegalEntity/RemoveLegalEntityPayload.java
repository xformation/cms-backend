package com.synectiks.cms.graphql.types.LegalEntity;

import java.util.List;

import com.synectiks.cms.entities.LegalEntity;

public class RemoveLegalEntityPayload {
    private final List<LegalEntity> legalEntities;

    public RemoveLegalEntityPayload(List<LegalEntity> legalEntities) {
        this.legalEntities = legalEntities;
    }

    public List<LegalEntity> getLegalEntities() {
        return legalEntities;
    }
}
