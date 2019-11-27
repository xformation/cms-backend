package com.synectiks.cms.graphql.types.Contract;

import com.synectiks.cms.domain.Contract;

import java.util.List;

public class RemoveContractPayload {
    private final List<Contract> contracts;

    public RemoveContractPayload(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public List<Contract> getContracts() {
        return contracts;
    }
}
