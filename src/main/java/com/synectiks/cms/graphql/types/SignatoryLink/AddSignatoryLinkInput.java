package com.synectiks.cms.graphql.types.SignatoryLink;

import java.util.Objects;

public class AddSignatoryLinkInput {
    private Long authorizedSignatoryId;
    private Long legalEntityId;

    public Long getAuthorizedSignatoryId() {
        return authorizedSignatoryId;
    }

    public void setAuthorizedSignatoryId(Long authorizedSignatoryId) {
        this.authorizedSignatoryId = authorizedSignatoryId;
    }

    public Long getLegalEntityId() {
        return legalEntityId;
    }

    public void setLegalEntityId(Long legalEntityId) {
        this.legalEntityId = legalEntityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddSignatoryLinkInput that = (AddSignatoryLinkInput) o;
        return Objects.equals(authorizedSignatoryId, that.authorizedSignatoryId) &&
            Objects.equals(legalEntityId, that.legalEntityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorizedSignatoryId, legalEntityId);
    }

    @Override
    public String toString() {
        return "AddSignatoryLinkInput{" +
            "authorizedSignatoryId=" + authorizedSignatoryId +
            ", legalEntityId=" + legalEntityId +
            '}';
    }
}
