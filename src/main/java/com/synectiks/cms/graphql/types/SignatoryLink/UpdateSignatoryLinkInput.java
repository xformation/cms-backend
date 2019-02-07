package com.synectiks.cms.graphql.types.SignatoryLink;

public class UpdateSignatoryLinkInput extends AbstractSignatoryLinkInput{
    private Long authorizedSignatoryId;
    private Long legaEntityId;

    public Long getAuthorizedSignatoryId() {
        return authorizedSignatoryId;
    }

    public void setAuthorizedSignatoryId(Long authorizedSignatoryId) {
        this.authorizedSignatoryId = authorizedSignatoryId;
    }

    public Long getLegaEntityId() {
        return legaEntityId;
    }

    public void setLegaEntityId(Long legaEntityId) {
        this.legaEntityId = legaEntityId;
    }
}
