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
    @Column(name = "address", nullable = false)
    private String address;

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

    public String getAddress() {
        return address;
    }

    public AuthorizedSignatory address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
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
            ", address='" + getAddress() + "'" +
            ", email='" + getEmail() + "'" +
            ", panCardNumber='" + getPanCardNumber() + "'" +
            "}";
    }
}
