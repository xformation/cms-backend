package com.synectiks.cms.graphql.types.AuthorizedSignatory;

import java.util.List;

import com.synectiks.cms.entities.AuthorizedSignatory;

public class RemoveAuthorizedSignatoryPayload {

    private final List<AuthorizedSignatory> authorizedSignatories;

    public RemoveAuthorizedSignatoryPayload(List<AuthorizedSignatory> authorizedSignatories) {
        this.authorizedSignatories = authorizedSignatories;

    }

    public List<AuthorizedSignatory> getAuthorizedSignatories() {
        return authorizedSignatories;
    }
}
