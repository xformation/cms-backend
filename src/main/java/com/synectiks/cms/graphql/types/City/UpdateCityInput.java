package com.synectiks.cms.graphql.types.City;

public class UpdateCityInput extends AbstractCityInput {
    private Long stateId;

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }
}
