package com.synectiks.cms.graphql.types.AuthorizedSignatory;

import com.synectiks.commons.entities.cms.AuthorizedSignatory;

public class AddAuthorizedSignatoryPayload extends AbstractAuthorizedSignatoryPayload {

    public AddAuthorizedSignatoryPayload(AuthorizedSignatory authorizedSignatory) {
        super(authorizedSignatory);
    }
}
