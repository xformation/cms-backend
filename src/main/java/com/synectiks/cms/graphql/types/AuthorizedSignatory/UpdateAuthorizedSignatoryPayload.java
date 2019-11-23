package com.synectiks.cms.graphql.types.AuthorizedSignatory;

import com.synectiks.commons.entities.cms.AuthorizedSignatory;

public class UpdateAuthorizedSignatoryPayload extends AbstractAuthorizedSignatoryPayload {
    public UpdateAuthorizedSignatoryPayload(AuthorizedSignatory authorizedSignatory) {
        super(authorizedSignatory);
    }
}
