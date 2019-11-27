package com.synectiks.cms.graphql.types.City;

import com.synectiks.cms.domain.City;

public class AbstractCityPayload {
    private final City city;

    public AbstractCityPayload(City city){
        this.city = city;
    }

    public City getCity(){
        return city;
    }
}
