package com.synectiks.cms.graphql.types.Country;

import com.synectiks.commons.entities.cms.Country;

public class AddCountryPayload extends AbstractCountryPayload {
    public AddCountryPayload(Country country){
        super(country);
    }
}
