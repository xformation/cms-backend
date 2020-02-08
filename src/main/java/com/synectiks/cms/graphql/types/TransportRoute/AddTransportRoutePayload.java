package com.synectiks.cms.graphql.types.TransportRoute;

import com.synectiks.cms.domain.CmsTransportVo;

public class AddTransportRoutePayload {
    private final CmsTransportVo cmsTransportVo;

    public AddTransportRoutePayload(CmsTransportVo cmsTransportVo){
        this.cmsTransportVo = cmsTransportVo;
    }

    public CmsTransportVo getTransportRouteVo() {
        return cmsTransportVo;
    }

}
