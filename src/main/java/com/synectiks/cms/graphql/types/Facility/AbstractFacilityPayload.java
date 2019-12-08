package com.synectiks.cms.graphql.types.Facility;

import com.synectiks.cms.entities.Facility;

public class AbstractFacilityPayload {
    private final Facility facility;

    public AbstractFacilityPayload(Facility facility) {
        this.facility = facility;
    }

    public Facility getFacility() {
        return facility;
    }
}
