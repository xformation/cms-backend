package com.synectiks.cms.graphql.types.Contract;

import com.synectiks.commons.entities.cms.Contract;

public class AbstractContractPayload {

    private final Contract contract;

    public AbstractContractPayload(Contract contract) {
        this.contract = contract;
    }

    public Contract getContract() {
        return contract;
    }
}
