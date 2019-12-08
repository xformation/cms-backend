package com.synectiks.cms.graphql.types.BankAccounts;

import java.util.List;

import com.synectiks.cms.entities.BankAccounts;

public class RemoveBankAccountsPayload {
    private final List<BankAccounts> bankAccounts;

    public RemoveBankAccountsPayload(List<BankAccounts> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public List<BankAccounts> getBankAccounts() {
        return bankAccounts;
    }
}
