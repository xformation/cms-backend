package com.synectiks.cms.graphql.types.Country;

import com.synectiks.cms.domain.Country;

public class UpdateCountryPayload extends AbstractCountryPayload {
    public UpdateCountryPayload(Country country){
        super(country);

    }
}
