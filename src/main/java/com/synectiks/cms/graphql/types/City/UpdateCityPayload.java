package com.synectiks.cms.graphql.types.City;

import com.synectiks.cms.domain.City;

public class UpdateCityPayload extends  AbstractCityPayload {
    public UpdateCityPayload(City city){
        super(city);
    }
}
