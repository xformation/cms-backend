package com.synectiks.cms.graphql.types.BankAccounts;

import com.synectiks.cms.domain.College;
import com.synectiks.cms.domain.enumeration.NameOfBank;

import java.util.Objects;

public class AbstractBankAccountsInput {
    private Long id;
    private NameOfBank nameOfBank;
    private String accountNumber;
    private String typeOfAccount;
    private String ifscCode;
    private String branchAddress;
    private String corporateId;
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
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

    public void setIfscCode(String ifsCode) {
        this.ifscCode = ifsCode;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getCorporateId() {
        return corporateId;
    }

    public void setCorporateId(String corporateId) {
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
            Objects.equals(getIfscCode(), that.getIfscCode()) &&
            Objects.equals(getBranchAddress(), that.getBranchAddress()) &&
            Objects.equals(getCorporateId(), that.getCorporateId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNameOfBank(), getAccountNumber(), getTypeOfAccount(), getIfscCode(), getBranchAddress(), getCorporateId());
    }

    @Override
    public String toString() {
        return "AbstractBankAccountsInput{" +
            "id=" + id +
            ", nameOfBank=" + nameOfBank +
            ", accountNumber=" + accountNumber +
            ", typeOfAccount='" + typeOfAccount + '\'' +
            ", ifsCode='" + ifscCode + '\'' +
            ", branch='" + branchAddress + '\'' +
            ", corporateId=" + corporateId +
            '}';
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
}
