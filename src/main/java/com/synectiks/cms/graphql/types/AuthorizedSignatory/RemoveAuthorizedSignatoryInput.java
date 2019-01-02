package com.synectiks.cms.graphql.types.AuthorizedSignatory;

public class RemoveAuthorizedSignatoryInput {
    private Long authorizedSignatoryId;

    public Long getAuthorizedSignatoryId() {
        return authorizedSignatoryId;
    }

    public void setAuthorizedSignatoryId(Long authorizedSignatoryId) {
        this.authorizedSignatoryId = authorizedSignatoryId;
    }
}
