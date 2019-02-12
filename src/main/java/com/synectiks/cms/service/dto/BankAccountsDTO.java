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
    private String ifscCode;

    @NotNull
    private String branchAddress;

    @NotNull
    private Integer corporateId;

    private Long branchId;

    private Long collegeId;

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

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public Integer getCorporateId() {
        return corporateId;
    }

    public void setCorporateId(Integer corporateId) {
        this.corporateId = corporateId;
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
            ", ifscCode='" + getIfscCode() + "'" +
            ", branchAddress='" + getBranchAddress() + "'" +
            ", corporateId=" + getCorporateId() +
            ", branch=" + getBranchId() +
            ", college=" + getCollegeId() +
            "}";
    }
}
