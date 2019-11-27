package com.synectiks.cms.graphql.types.Country;

import com.synectiks.cms.domain.Country;

public class RemoveCountryInput {

    private Long countryId;

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }
}
