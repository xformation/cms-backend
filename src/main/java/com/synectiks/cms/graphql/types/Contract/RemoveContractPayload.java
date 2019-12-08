package com.synectiks.cms.graphql.types.Contract;

import java.util.List;

import com.synectiks.cms.entities.Contract;

public class RemoveContractPayload {
    private final List<Contract> contracts;

    public RemoveContractPayload(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public List<Contract> getContracts() {
        return contracts;
    }
}
