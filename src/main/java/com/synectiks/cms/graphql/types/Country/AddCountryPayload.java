package com.synectiks.cms.graphql.types.Country;

import com.synectiks.cms.domain.Country;

public class AddCountryPayload extends AbstractCountryPayload {
    public AddCountryPayload(Country country){
        super(country);
    }
}
