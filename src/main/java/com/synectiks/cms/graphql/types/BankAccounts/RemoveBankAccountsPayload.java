package com.synectiks.cms.graphql.types.BankAccounts;

import com.synectiks.cms.domain.BankAccounts;

import java.util.List;

public class RemoveBankAccountsPayload {
    private final List<BankAccounts> bankAccounts;

    public RemoveBankAccountsPayload(List<BankAccounts> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public List<BankAccounts> getBankAccounts() {
        return bankAccounts;
    }
}
