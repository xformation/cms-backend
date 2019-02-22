package com.synectiks.cms.graphql.types.Facility;

import com.synectiks.cms.domain.Facility;

import java.util.List;

public class RemoveFacilityPayload {
    private final List<Facility> facilities;
    public RemoveFacilityPayload(List<Facility> facilities) {
        this.facilities = facilities;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }
}
