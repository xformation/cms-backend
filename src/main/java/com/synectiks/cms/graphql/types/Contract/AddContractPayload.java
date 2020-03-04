package com.synectiks.cms.graphql.types.Contract;

import com.synectiks.cms.domain.CmsContractVo;


public class AddContractPayload {
    private final CmsContractVo cmsContractVo;

    public AddContractPayload(CmsContractVo cmsContractVo) {
        this.cmsContractVo = cmsContractVo;
    }

    public CmsContractVo getContractVo() {
        return cmsContractVo;
    }
}
