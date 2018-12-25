package com.synectiks.cms.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

import com.synectiks.cms.domain.enumeration.NameOfBank;

/**
 * A BankAccounts.
 */
@Entity
@Table(name = "bank_accounts")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "bankaccounts")
public class BankAccounts implements Serializable {

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
    private Long accountNumber;

    @NotNull
    @Column(name = "type_of_account", nullable = false)
    private String typeOfAccount;

    @NotNull
    @Column(name = "ifs_code", nullable = false)
    private String ifsCode;

    @NotNull
    @Column(name = "branch", nullable = false)
    private String branch;

    @NotNull
    @Column(name = "corporate_id", nullable = false)
    private Integer corporateId;

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

    public Long getAccountNumber() {
        return accountNumber;
    }

    public BankAccounts accountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public void setAccountNumber(Long accountNumber) {
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

    public String getIfsCode() {
        return ifsCode;
    }

    public BankAccounts ifsCode(String ifsCode) {
        this.ifsCode = ifsCode;
        return this;
    }

    public void setIfsCode(String ifsCode) {
        this.ifsCode = ifsCode;
    }

    public String getBranch() {
        return branch;
    }

    public BankAccounts branch(String branch) {
        this.branch = branch;
        return this;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Integer getCorporateId() {
        return corporateId;
    }

    public BankAccounts corporateId(Integer corporateId) {
        this.corporateId = corporateId;
        return this;
    }

    public void setCorporateId(Integer corporateId) {
        this.corporateId = corporateId;
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
            ", ifsCode='" + getIfsCode() + "'" +
            ", branch='" + getBranch() + "'" +
            ", corporateId=" + getCorporateId() +
            "}";
    }
}
