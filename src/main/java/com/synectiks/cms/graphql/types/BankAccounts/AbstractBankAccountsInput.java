package com.synectiks.cms.graphql.types.BankAccounts;

import com.synectiks.cms.domain.enumeration.NameOfBank;

import java.util.Objects;

public class AbstractBankAccountsInput {
    private Long id;
    private NameOfBank nameOfBank;
    private Long accountNumber;
    private String typeOfAccount;
    private String ifsCode;
    private String branch;
    private Integer corporateId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NameOfBank getNameOfBank() {
        return nameOfBank;
    }

    public void setNameOfBank(NameOfBank nameOfBank) {
        this.nameOfBank = nameOfBank;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(String typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public String getIfsCode() {
        return ifsCode;
    }

    public void setIfsCode(String ifsCode) {
        this.ifsCode = ifsCode;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Integer getCorporateId() {
        return corporateId;
    }

    public void setCorporateId(Integer corporateId) {
        this.corporateId = corporateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractBankAccountsInput)) return false;
        AbstractBankAccountsInput that = (AbstractBankAccountsInput) o;
        return Objects.equals(getId(), that.getId()) &&
            getNameOfBank() == that.getNameOfBank() &&
            Objects.equals(getAccountNumber(), that.getAccountNumber()) &&
            Objects.equals(getTypeOfAccount(), that.getTypeOfAccount()) &&
            Objects.equals(getIfsCode(), that.getIfsCode()) &&
            Objects.equals(getBranch(), that.getBranch()) &&
            Objects.equals(getCorporateId(), that.getCorporateId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNameOfBank(), getAccountNumber(), getTypeOfAccount(), getIfsCode(), getBranch(), getCorporateId());
    }

    @Override
    public String toString() {
        return "AbstractBankAccountsInput{" +
            "id=" + id +
            ", nameOfBank=" + nameOfBank +
            ", accountNumber=" + accountNumber +
            ", typeOfAccount='" + typeOfAccount + '\'' +
            ", ifsCode='" + ifsCode + '\'' +
            ", branch='" + branch + '\'' +
            ", corporateId=" + corporateId +
            '}';
    }
}
