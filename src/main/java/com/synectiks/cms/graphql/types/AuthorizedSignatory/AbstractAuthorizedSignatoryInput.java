package com.synectiks.cms.graphql.types.AuthorizedSignatory;


import java.util.Objects;

public class AbstractAuthorizedSignatoryInput {

    private Long id;
    private String signatoryName;
    private String signatoryFatherName;
    private String signatoryDesignation;
    private String address;
    private String email;
    private String panCardNumber;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractAuthorizedSignatoryInput)) return false;
        AbstractAuthorizedSignatoryInput that = (AbstractAuthorizedSignatoryInput) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getSignatoryName(), that.getSignatoryName()) &&
            Objects.equals(getSignatoryFatherName(), that.getSignatoryFatherName()) &&
            Objects.equals(getSignatoryDesignation(), that.getSignatoryDesignation()) &&
            Objects.equals(getAddress(), that.getAddress()) &&
            Objects.equals(getEmail(), that.getEmail()) &&
            Objects.equals(getPanCardNumber(), that.getPanCardNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSignatoryName(), getSignatoryFatherName(), getSignatoryDesignation(), getAddress(), getEmail(), getPanCardNumber());
    }

    @Override
    public String toString() {
        return "AbstractAuthorizedSignatoryInput{" +
            "id=" + id +
            ", signatoryName='" + signatoryName + '\'' +
            ", signatoryFatherName='" + signatoryFatherName + '\'' +
            ", signatoryDesignation='" + signatoryDesignation + '\'' +
            ", address='" + address + '\'' +
            ", email='" + email + '\'' +
            ", panCardNumber='" + panCardNumber + '\'' +
            '}';
    }
}
