package com.synectiks.cms.graphql.types.TransportRoute;

import com.synectiks.cms.domain.TransportRoute;

public class AbstractTransportRoutePayload {
    private final TransportRoute transportRoute;

    public AbstractTransportRoutePayload(TransportRoute transportRoute) {
        this.transportRoute = transportRoute;
    }

    public TransportRoute getTransportRoute() {
        return transportRoute;
    }
}
