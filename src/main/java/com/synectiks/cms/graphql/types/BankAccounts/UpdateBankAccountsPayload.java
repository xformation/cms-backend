package com.synectiks.cms.graphql.types.BankAccounts;

import com.synectiks.cms.domain.BankAccounts;

public class UpdateBankAccountsPayload extends AbstractBankAccountsPayload {
    public UpdateBankAccountsPayload(BankAccounts bankAccounts) {
        super(bankAccounts);
    }
}
