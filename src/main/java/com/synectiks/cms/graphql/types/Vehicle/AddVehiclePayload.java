package com.synectiks.cms.graphql.types.Vehicle;

import com.synectiks.cms.domain.CmsVehicleVo;

public class AddVehiclePayload {
    private final CmsVehicleVo cmsVehicleVo;

    public AddVehiclePayload(CmsVehicleVo cmsVehicleVo){
        this.cmsVehicleVo = cmsVehicleVo;
    }

    public CmsVehicleVo getVehicleVo() {
        return cmsVehicleVo;
    }

}
