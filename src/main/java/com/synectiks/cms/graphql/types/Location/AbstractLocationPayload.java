package com.synectiks.cms.graphql.types.Location;

import com.synectiks.cms.domain.Location;

public class AbstractLocationPayload {
    private final Location location;

    public AbstractLocationPayload(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
