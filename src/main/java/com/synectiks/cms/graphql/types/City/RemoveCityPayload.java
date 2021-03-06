package com.synectiks.cms.graphql.types.City;

import com.synectiks.cms.domain.City;

import java.util.List;

public class RemoveCityPayload {
    private final List<City> cities;

    public RemoveCityPayload(List<City> cities){
        this.cities = cities;
    }

    public List<City> getCities(){
        return cities;
    }
}
