package com.synectiks.cms.graphql.types.City;

import com.synectiks.commons.entities.cms.City;

public class UpdateCityPayload extends  AbstractCityPayload {
    public UpdateCityPayload(City city){
        super(city);
    }
}
