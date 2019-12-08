package com.synectiks.cms.graphql.types.City;

import java.util.List;

import com.synectiks.cms.entities.City;

public class RemoveCityPayload {
    private final List<City> cities;

    public RemoveCityPayload(List<City> cities){
        this.cities = cities;
    }

    public List<City> getCities(){
        return cities;
    }
}
