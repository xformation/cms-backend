package com.synectiks.cms.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AuthorizedSignatory entity.
 */
public class AuthorizedSignatoryDTO implements Serializable {

    private Long id;

    @NotNull
    private String signatoryName;

    @NotNull
    private String signatoryFatherName;

    @NotNull
    private String signatoryDesignation;

    @NotNull
    private String address;

    @NotNull
    private String email;

    @NotNull
    private String panCardNumber;

    private Long legalEntityId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSignatoryName() {
        return signatoryName;
    }

    public void setSignatoryName(String signatoryName) {
        this.signatoryName = signatoryName;
    }

    public String getSignatoryFatherName() {
        return signatoryFatherName;
    }

    public void setSignatoryFatherName(String signatoryFatherName) {
        this.signatoryFatherName = signatoryFatherName;
    }

    public String getSignatoryDesignation() {
        return signatoryDesignation;
    }

    public void setSignatoryDesignation(String signatoryDesignation) {
        this.signatoryDesignation = signatoryDesignation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPanCardNumber() {
        return panCardNumber;
    }

    public void setPanCardNumber(String panCardNumber) {
        this.panCardNumber = panCardNumber;
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

        AuthorizedSignatoryDTO authorizedSignatoryDTO = (AuthorizedSignatoryDTO) o;
        if (authorizedSignatoryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), authorizedSignatoryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AuthorizedSignatoryDTO{" +
            "id=" + getId() +
            ", signatoryName='" + getSignatoryName() + "'" +
            ", signatoryFatherName='" + getSignatoryFatherName() + "'" +
            ", signatoryDesignation='" + getSignatoryDesignation() + "'" +
            ", address='" + getAddress() + "'" +
            ", email='" + getEmail() + "'" +
            ", panCardNumber='" + getPanCardNumber() + "'" +
            ", legalEntity=" + getLegalEntityId() +
            "}";
    }
}
