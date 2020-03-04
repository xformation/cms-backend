package com.synectiks.cms.graphql.types.Insurance;

import com.synectiks.cms.domain.CmsInsuranceVo;
import com.synectiks.cms.domain.CmsVehicleVo;
import com.synectiks.cms.domain.Insurance;

public class AddInsurancePayload{
    private final CmsInsuranceVo cmsInsuranceVo;

    public AddInsurancePayload(CmsInsuranceVo cmsInsuranceVo){
        this.cmsInsuranceVo = cmsInsuranceVo;
    }

    public CmsInsuranceVo getInsuranceVo() {
        return cmsInsuranceVo;
    }

}
