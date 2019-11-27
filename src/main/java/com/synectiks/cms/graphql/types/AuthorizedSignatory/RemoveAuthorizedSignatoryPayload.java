package com.synectiks.cms.graphql.types.AuthorizedSignatory;

import com.synectiks.cms.domain.AuthorizedSignatory;

import java.util.List;

public class RemoveAuthorizedSignatoryPayload {

    private final List<AuthorizedSignatory> authorizedSignatories;

    public RemoveAuthorizedSignatoryPayload(List<AuthorizedSignatory> authorizedSignatories) {
        this.authorizedSignatories = authorizedSignatories;

    }

    public List<AuthorizedSignatory> getAuthorizedSignatories() {
        return authorizedSignatories;
    }
}
