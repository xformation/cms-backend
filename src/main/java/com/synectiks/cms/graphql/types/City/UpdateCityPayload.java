package com.synectiks.cms.graphql.types.City;

import com.synectiks.cms.entities.City;

public class UpdateCityPayload extends  AbstractCityPayload {
    public UpdateCityPayload(City city){
        super(city);
    }
}
