package com.synectiks.cms.graphql.types.Country;

import com.synectiks.commons.entities.cms.Country;

public class RemoveCountryInput {

    private Long countryId;

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }
}
