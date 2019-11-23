package com.synectiks.cms.graphql.types.City;

import com.synectiks.commons.entities.cms.City;

public class AddCityPayload extends AbstractCityPayload {
    public AddCityPayload(City city){
        super(city);
    }
}
