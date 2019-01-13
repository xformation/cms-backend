package com.synectiks.cms.graphql.types.AuthorizedSignatory;

public class UpdateAuthorizedSignatoryInput extends AbstractAuthorizedSignatoryInput {
	private Long legalEntityId;

    public Long getLegalEntityId() {
        return legalEntityId;
    }

    public void setLegalEntityId(Long legalEntityId) {
        this.legalEntityId = legalEntityId;
    }
}
