package com.synectiks.cms.graphql.types.AuthorizedSignatory;

import com.synectiks.cms.domain.AuthorizedSignatory;

public class UpdateAuthorizedSignatoryPayload extends AbstractAuthorizedSignatoryPayload {
    public UpdateAuthorizedSignatoryPayload(AuthorizedSignatory authorizedSignatory) {
        super(authorizedSignatory);
    }
}
