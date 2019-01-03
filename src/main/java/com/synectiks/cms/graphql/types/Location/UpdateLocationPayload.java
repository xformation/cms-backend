package com.synectiks.cms.graphql.types.Location;

import com.synectiks.cms.domain.Location;

public class UpdateLocationPayload extends AbstractLocationPayload{
    public UpdateLocationPayload(Location location) {
        super(location);
    }
}
