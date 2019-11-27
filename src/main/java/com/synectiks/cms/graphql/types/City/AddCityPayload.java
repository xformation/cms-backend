package com.synectiks.cms.graphql.types.City;

import com.synectiks.cms.domain.City;

public class AddCityPayload extends AbstractCityPayload {
    public AddCityPayload(City city){
        super(city);
    }
}
