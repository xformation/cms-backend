package com.synectiks.cms.graphql.types.Book;

import com.synectiks.cms.domain.CmsBookVo;

public class AddBookPayload {
    private final CmsBookVo cmsBookVo;

    public AddBookPayload(CmsBookVo cmsBookVo){
        this.cmsBookVo = cmsBookVo;
    }

    public CmsBookVo getCmsBookVo() {
        return cmsBookVo;
    }

}
