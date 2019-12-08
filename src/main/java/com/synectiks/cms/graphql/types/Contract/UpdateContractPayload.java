package com.synectiks.cms.graphql.types.Contract;

import com.synectiks.cms.entities.Contract;

public class UpdateContractPayload extends  AbstractContractPayload {
    public UpdateContractPayload(Contract contract) {
        super(contract);
    }
}
