package com.synectiks.cms.graphql.types.SignatoryLink;

import com.synectiks.cms.domain.SignatoryLink;

public class AbstractSignatoryLinkPayload {
    private final SignatoryLink signatoryLink;

    public AbstractSignatoryLinkPayload(SignatoryLink signatoryLink) {
        this.signatoryLink = signatoryLink;
    }

    public SignatoryLink getSignatoryLink() {
        return signatoryLink;
    }
}
