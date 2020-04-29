package com.synectiks.cms.graphql.types.Library;
import com.synectiks.cms.domain.CmsLibraryVo;

public class AddLibraryPayload {
    private final CmsLibraryVo cmsLibraryVo;

    public AddLibraryPayload(CmsLibraryVo cmsLibraryVo){
        this.cmsLibraryVo = cmsLibraryVo;
    }

    public CmsLibraryVo getCmsLibraryVo() {
        return cmsLibraryVo;
    }

}
