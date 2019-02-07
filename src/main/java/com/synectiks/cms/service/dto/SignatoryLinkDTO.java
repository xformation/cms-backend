package com.synectiks.cms.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the SignatoryLink entity.
 */
public class SignatoryLinkDTO implements Serializable {

    private Long id;

    private String desc;

    private Long authorizedSignatoryId;

    private Long legalEntityId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SignatoryLinkDTO signatoryLinkDTO = (SignatoryLinkDTO) o;
        if (signatoryLinkDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), signatoryLinkDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SignatoryLinkDTO{" +
            "id=" + getId() +
            ", desc='" + getDesc() + "'" +
            ", authorizedSignatory=" + getAuthorizedSignatoryId() +
            ", legalEntity=" + getLegalEntityId() +
            "}";
    }
}
