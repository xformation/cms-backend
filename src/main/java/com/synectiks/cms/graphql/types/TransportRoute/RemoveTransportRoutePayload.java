package com.synectiks.cms.graphql.types.TransportRoute;

import com.synectiks.commons.entities.cms.TransportRoute;

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
