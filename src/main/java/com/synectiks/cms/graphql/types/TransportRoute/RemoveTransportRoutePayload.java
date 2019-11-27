package com.synectiks.cms.graphql.types.TransportRoute;

import com.synectiks.cms.domain.TransportRoute;

import java.util.List;

public class RemoveTransportRoutePayload {
    private final List<TransportRoute> transportRoutes;

    public RemoveTransportRoutePayload(List<TransportRoute> transportRoutes) {
        this.transportRoutes = transportRoutes;
    }

    public List<TransportRoute> getTransportRoutes() {
        return transportRoutes;
    }
}
