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
    private String address1;

    @NotNull
    private String address2;

    @NotNull
    private String address3;

    @NotNull
    private String address4;

    @NotNull
    private String address5;

    @NotNull
    private String email;

    @NotNull
    private String panCardNumber;

    private Long branchId;

    private Long collegeId;

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

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
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
            ", address1='" + getAddress1() + "'" +
            ", address2='" + getAddress2() + "'" +
            ", address3='" + getAddress3() + "'" +
            ", address4='" + getAddress4() + "'" +
            ", address5='" + getAddress5() + "'" +
            ", email='" + getEmail() + "'" +
            ", panCardNumber='" + getPanCardNumber() + "'" +
            ", branch=" + getBranchId() +
            ", college=" + getCollegeId() +
            "}";
    }
}
