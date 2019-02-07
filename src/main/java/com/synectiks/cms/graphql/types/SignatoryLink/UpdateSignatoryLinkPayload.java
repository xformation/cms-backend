package com.synectiks.cms.graphql.types.SignatoryLink;

import com.synectiks.cms.domain.SignatoryLink;

public class UpdateSignatoryLinkPayload extends AbstractSignatoryLinkPayload {
    public UpdateSignatoryLinkPayload(SignatoryLink signatoryLink){
        super(signatoryLink);
    }
}
