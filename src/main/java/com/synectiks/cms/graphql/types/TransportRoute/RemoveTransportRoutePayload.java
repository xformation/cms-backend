package com.synectiks.cms.graphql.types.TransportRoute;

import java.util.List;

import com.synectiks.cms.entities.TransportRoute;

public class RemoveTransportRoutePayload {
    private final List<TransportRoute> transportRoutes;

    public RemoveTransportRoutePayload(List<TransportRoute> transportRoutes) {
        this.transportRoutes = transportRoutes;
    }

    public List<TransportRoute> getTransportRoutes() {
        return transportRoutes;
    }
}
