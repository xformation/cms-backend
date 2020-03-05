package com.synectiks.cms.domain;

import java.util.List;

public class VehicleDataCache {
    private List<CmsTransportVo> transportRoute;

    public List<CmsTransportVo> getTransportRoute() {
        return transportRoute;
    }

    public void setTransportRoute(List<CmsTransportVo> transportRoute) {
        this.transportRoute = transportRoute;
    }
}
