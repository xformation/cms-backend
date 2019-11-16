package com.synectiks.cms.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

//import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

import com.synectiks.cms.domain.enumeration.NameOfBank;
import com.synectiks.cms.utils.IESEntity;

/**
 * A BankAccounts.
 */
@Entity
@Table(name = "bank_accounts")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//@Document(indexName = "bankaccounts")
public class BankAccounts implements Serializable, IESEntity {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "name_of_bank", nullable = false)
    private NameOfBank nameOfBank;

    @NotNull
    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @NotNull
    @Column(name = "type_of_account", nullable = false)
    private String typeOfAccount;

    @NotNull
    @Column(name = "ifsc_code", nullable = false)
    private String ifscCode;

    @NotNull
    @Column(name = "branch_address", nullable = false)
    private String branchAddress;

    @NotNull
    @Column(name = "corporate_id", nullable = false)
    private String corporateId;

    @ManyToOne
    @JsonIgnoreProperties("bankAccounts")
    private Branch branch;

    @ManyToOne
    @JsonIgnoreProperties("bankAccounts")
    private College college;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NameOfBank getNameOfBank() {
        return nameOfBank;
    }

    public BankAccounts nameOfBank(NameOfBank nameOfBank) {
        this.nameOfBank = nameOfBank;
        return this;
    }

    public void setNameOfBank(NameOfBank nameOfBank) {
        this.nameOfBank = nameOfBank;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BankAccounts accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTypeOfAccount() {
        return typeOfAccount;
    }

    public BankAccounts typeOfAccount(String typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
        return this;
    }

    public void setTypeOfAccount(String typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public BankAccounts ifscCode(String ifscCode) {
        this.ifscCode = ifscCode;
        return this;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public BankAccounts branchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
        return this;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getCorporateId() {
        return corporateId;
    }

    public BankAccounts corporateId(String corporateId) {
        this.corporateId = corporateId;
        return this;
    }

    public void setCorporateId(String corporateId) {
        this.corporateId = corporateId;
    }

    public Branch getBranch() {
        return branch;
    }

    public BankAccounts branch(Branch branch) {
        this.branch = branch;
        return this;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public College getCollege() {
        return college;
    }

    public BankAccounts college(College college) {
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
        BankAccounts bankAccounts = (BankAccounts) o;
        if (bankAccounts.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bankAccounts.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BankAccounts{" +
            "id=" + getId() +
            ", nameOfBank='" + getNameOfBank() + "'" +
            ", accountNumber=" + getAccountNumber() +
            ", typeOfAccount='" + getTypeOfAccount() + "'" +
            ", ifscCode='" + getIfscCode() + "'" +
            ", branchAddress='" + getBranchAddress() + "'" +
            ", corporateId=" + getCorporateId() +
            "}";
    }
}
