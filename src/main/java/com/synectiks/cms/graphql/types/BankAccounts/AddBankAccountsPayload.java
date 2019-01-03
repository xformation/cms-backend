package com.synectiks.cms.graphql.types.BankAccounts;

import com.synectiks.cms.domain.BankAccounts;

public class AddBankAccountsPayload extends AbstractBankAccountsPayload{
    public AddBankAccountsPayload(BankAccounts bankAccounts) {
        super(bankAccounts);
    }
}
