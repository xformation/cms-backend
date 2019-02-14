package com.synectiks.cms.graphql.types.State;

public class UpdateStateInput extends AbstractStateInput {
    private Long countryId;

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }
}
