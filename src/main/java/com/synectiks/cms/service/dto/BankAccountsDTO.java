package com.synectiks.cms.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.synectiks.cms.domain.enumeration.NameOfBank;

/**
 * A DTO for the BankAccounts entity.
 */
public class BankAccountsDTO implements Serializable {

    private Long id;

    @NotNull
    private NameOfBank nameOfBank;

    @NotNull
    private Long accountNumber;

    @NotNull
    private String typeOfAccount;

    @NotNull
    private String ifsCode;

    @NotNull
    private String branch;

    @NotNull
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BankAccountsDTO bankAccountsDTO = (BankAccountsDTO) o;
        if (bankAccountsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bankAccountsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BankAccountsDTO{" +
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
