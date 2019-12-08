package com.synectiks.cms.graphql.types.Facility;

import com.synectiks.cms.entities.Facility;

public class UpdateFacilityPayload extends AbstractFacilityPayload {
    public UpdateFacilityPayload(Facility facility) {
        super(facility);
    }
}
