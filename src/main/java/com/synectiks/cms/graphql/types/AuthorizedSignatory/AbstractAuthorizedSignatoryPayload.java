package com.synectiks.cms.graphql.types.AuthorizedSignatory;

import com.synectiks.cms.domain.AuthorizedSignatory;

public class AbstractAuthorizedSignatoryPayload {
    private final AuthorizedSignatory authorizedSignatory;

    public AbstractAuthorizedSignatoryPayload(AuthorizedSignatory authorizedSignatory) {
        this.authorizedSignatory = authorizedSignatory;
    }

    public AuthorizedSignatory getAuthorizedSignatory() {
        return authorizedSignatory;
    }
}
