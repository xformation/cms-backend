package com.synectiks.cms.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A AuthorizedSignatory.
 */
@Entity
@Table(name = "authorized_signatory")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "authorizedsignatory")
public class AuthorizedSignatory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "signatory_name", nullable = false)
    private String signatoryName;

    @NotNull
    @Column(name = "signatory_father_name", nullable = false)
    private String signatoryFatherName;

    @NotNull
    @Column(name = "signatory_designation", nullable = false)
    private String signatoryDesignation;

    @NotNull
    @Column(name = "address_1", nullable = false)
    private String address1;

    @Column(name = "address_2")
    private String address2;

    @Column(name = "address_3")
    private String address3;

    @Column(name = "address_4")
    private String address4;

    @Column(name = "address_5")
    private String address5;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "pan_card_number", nullable = false)
    private String panCardNumber;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Branch branch;

    @ManyToOne
    @JsonIgnoreProperties("")
    private College college;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSignatoryName() {
        return signatoryName;
    }

    public AuthorizedSignatory signatoryName(String signatoryName) {
        this.signatoryName = signatoryName;
        return this;
    }

    public void setSignatoryName(String signatoryName) {
        this.signatoryName = signatoryName;
    }

    public String getSignatoryFatherName() {
        return signatoryFatherName;
    }

    public AuthorizedSignatory signatoryFatherName(String signatoryFatherName) {
        this.signatoryFatherName = signatoryFatherName;
        return this;
    }

    public void setSignatoryFatherName(String signatoryFatherName) {
        this.signatoryFatherName = signatoryFatherName;
    }

    public String getSignatoryDesignation() {
        return signatoryDesignation;
    }

    public AuthorizedSignatory signatoryDesignation(String signatoryDesignation) {
        this.signatoryDesignation = signatoryDesignation;
        return this;
    }

    public void setSignatoryDesignation(String signatoryDesignation) {
        this.signatoryDesignation = signatoryDesignation;
    }

    public String getAddress1() {
        return address1;
    }

    public AuthorizedSignatory address1(String address1) {
        this.address1 = address1;
        return this;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public AuthorizedSignatory address2(String address2) {
        this.address2 = address2;
        return this;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public AuthorizedSignatory address3(String address3) {
        this.address3 = address3;
        return this;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAddress4() {
        return address4;
    }

    public AuthorizedSignatory address4(String address4) {
        this.address4 = address4;
        return this;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    public String getAddress5() {
        return address5;
    }

    public AuthorizedSignatory address5(String address5) {
        this.address5 = address5;
        return this;
    }

    public void setAddress5(String address5) {
        this.address5 = address5;
    }

    public String getEmail() {
        return email;
    }

    public AuthorizedSignatory email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPanCardNumber() {
        return panCardNumber;
    }

    public AuthorizedSignatory panCardNumber(String panCardNumber) {
        this.panCardNumber = panCardNumber;
        return this;
    }

    public void setPanCardNumber(String panCardNumber) {
        this.panCardNumber = panCardNumber;
    }

    public Branch getBranch() {
        return branch;
    }

    public AuthorizedSignatory branch(Branch branch) {
        this.branch = branch;
        return this;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public College getCollege() {
        return college;
    }

    public AuthorizedSignatory college(College college) {
        this.college = college;
        return this;
    }

    public void setCollege(College college) {
        this.college = college;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuthorizedSignatory authorizedSignatory = (AuthorizedSignatory) o;
        if (authorizedSignatory.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), authorizedSignatory.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AuthorizedSignatory{" +
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
            "}";
    }
}
