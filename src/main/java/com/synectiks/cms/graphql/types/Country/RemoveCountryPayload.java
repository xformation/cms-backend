package com.synectiks.cms.graphql.types.Country;

import java.util.List;

import com.synectiks.cms.entities.Country;

public class RemoveCountryPayload {
    private final List<Country> countries;

    public RemoveCountryPayload(List<Country> countries){
        this.countries = countries;
    }

    public List<Country> getCountries(){
        return countries;
    }
}
