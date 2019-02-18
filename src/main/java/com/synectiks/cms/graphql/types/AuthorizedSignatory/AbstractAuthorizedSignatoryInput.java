package com.synectiks.cms.graphql.types.AuthorizedSignatory;


import java.util.Objects;

public class AbstractAuthorizedSignatoryInput {

    private Long id;
    private String signatoryName;
    private String signatoryFatherName;
    private String signatoryDesignation;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String address5;
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

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    public String getAddress5() {
        return address5;
    }

    public void setAddress5(String address5) {
        this.address5 = address5;
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
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAuthorizedSignatoryInput that = (AbstractAuthorizedSignatoryInput) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(signatoryName, that.signatoryName) &&
            Objects.equals(signatoryFatherName, that.signatoryFatherName) &&
            Objects.equals(signatoryDesignation, that.signatoryDesignation) &&
            Objects.equals(address1, that.address1) &&
            Objects.equals(address2, that.address2) &&
            Objects.equals(address3, that.address3) &&
            Objects.equals(address4, that.address4) &&
            Objects.equals(address5, that.address5) &&
            Objects.equals(email, that.email) &&
            Objects.equals(panCardNumber, that.panCardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, signatoryName, signatoryFatherName, signatoryDesignation, address1, address2, address3, address4, address5, email, panCardNumber);
    }

    @Override
    public String toString() {
        return "AbstractAuthorizedSignatoryInput{" +
            "id=" + id +
            ", signatoryName='" + signatoryName + '\'' +
            ", signatoryFatherName='" + signatoryFatherName + '\'' +
            ", signatoryDesignation='" + signatoryDesignation + '\'' +
            ", address1='" + address1 + '\'' +
            ", address2='" + address2 + '\'' +
            ", address3='" + address3 + '\'' +
            ", address4='" + address4 + '\'' +
            ", address5='" + address5 + '\'' +
            ", email='" + email + '\'' +
            ", panCardNumber='" + panCardNumber + '\'' +
            '}';
    }
}
