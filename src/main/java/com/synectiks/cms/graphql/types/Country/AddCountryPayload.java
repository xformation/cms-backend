package com.synectiks.cms.graphql.types.Country;

import com.synectiks.cms.entities.Country;

public class AddCountryPayload extends AbstractCountryPayload {
    public AddCountryPayload(Country country){
        super(country);
    }
}
