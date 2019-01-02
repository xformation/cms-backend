package com.synectiks.cms.graphql.types.LegalEntity;

public class AddLegalEntityInput extends AbstractLegalEntityInput {

    private Long authorizedSignatoryId;

    public Long getAuthorizedSignatoryId() {
        return authorizedSignatoryId;
    }

    public void setAuthorizedSignatoryId(Long authorizedSignatoryId) {
        this.authorizedSignatoryId = authorizedSignatoryId;
    }

    @Override
    public String toString() {
        return "AddLegalEntityInput{" +
            "authorizedSignatoryId=" + authorizedSignatoryId +
            '}'+ super.toString();
    }
}
