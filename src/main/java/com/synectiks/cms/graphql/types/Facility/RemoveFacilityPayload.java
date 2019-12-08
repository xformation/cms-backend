package com.synectiks.cms.graphql.types.Facility;

import java.util.List;

import com.synectiks.cms.entities.Facility;

public class RemoveFacilityPayload {
    private final List<Facility> facilities;
    public RemoveFacilityPayload(List<Facility> facilities) {
        this.facilities = facilities;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }
}
