package com.synectiks.cms.graphql.types.Location;

import com.synectiks.cms.domain.Location;

import java.util.List;

public class RemoveLocationPayload {
    private final List<Location> locations;

    public RemoveLocationPayload(List<Location> locations) {
        this.locations = locations;
    }

    public List<Location> getLocations() {
        return locations;
    }
}
