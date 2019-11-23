package com.synectiks.cms.graphql.types.Country;

import com.synectiks.commons.entities.cms.Country;

public class UpdateCountryPayload extends AbstractCountryPayload {
    public UpdateCountryPayload(Country country){
        super(country);

    }
}
