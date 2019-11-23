package com.synectiks.cms.graphql.types.BankAccounts;

import com.synectiks.commons.entities.cms.BankAccounts;

public class AbstractBankAccountsPayload {
    private final BankAccounts bankAccounts;

    public AbstractBankAccountsPayload(BankAccounts bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public BankAccounts getBankAccounts() {
        return bankAccounts;
    }
}
