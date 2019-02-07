package com.synectiks.cms.graphql.types.SignatoryLink;

import com.synectiks.cms.domain.SignatoryLink;

public class AddSignatoryLinkPayload extends AbstractSignatoryLinkPayload {

    public AddSignatoryLinkPayload(SignatoryLink signatoryLink){
        super(signatoryLink);
    }
}
