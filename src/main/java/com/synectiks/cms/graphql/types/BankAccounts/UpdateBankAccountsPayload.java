package com.synectiks.cms.graphql.types.BankAccounts;

import com.synectiks.commons.entities.cms.BankAccounts;

public class UpdateBankAccountsPayload extends AbstractBankAccountsPayload {
    public UpdateBankAccountsPayload(BankAccounts bankAccounts) {
        super(bankAccounts);
    }
}
