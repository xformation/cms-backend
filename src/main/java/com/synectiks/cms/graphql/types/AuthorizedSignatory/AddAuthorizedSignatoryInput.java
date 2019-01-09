package com.synectiks.cms.graphql.types.AuthorizedSignatory;

public class AddAuthorizedSignatoryInput extends AbstractAuthorizedSignatoryInput {
    private Long legalEntityId;

    public Long getLegalEntityId() {
        return legalEntityId;
    }

    public void setLegalEntityId(Long legalEntityId) {
        this.legalEntityId = legalEntityId;
    }

    @Override
    public String toString() {
        return "AddAuthorizedSignatoryInput{" +
            "legalEntityId=" + legalEntityId +
            '}'+ super.toString();
    }
}
