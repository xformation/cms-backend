package com.synectiks.cms.graphql.types.Location;

import com.synectiks.cms.domain.Location;

public class AddLocationPayload extends AbstractLocationPayload{
    public AddLocationPayload(Location location) {
        super(location);
    }
}
