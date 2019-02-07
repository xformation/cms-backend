package com.synectiks.cms.graphql.types.SignatoryLink;

import com.synectiks.cms.domain.SignatoryLink;

import java.util.List;

public class RemoveSignatoryLinkPayload {
    private final List<SignatoryLink> signatoryLinks;

    public RemoveSignatoryLinkPayload(List<SignatoryLink> signatoryLinks){
        this.signatoryLinks=signatoryLinks;
    }

    public List<SignatoryLink> getSignatoryLinks() {
        return signatoryLinks;
    }
}
