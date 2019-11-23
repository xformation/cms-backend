package com.synectiks.cms.graphql.types.Country;

import com.synectiks.commons.entities.cms.Country;

import java.util.List;

public class RemoveCountryPayload {
    private final List<Country> countries;

    public RemoveCountryPayload(List<Country> countries){
        this.countries = countries;
    }

    public List<Country> getCountries(){
        return countries;
    }
}
