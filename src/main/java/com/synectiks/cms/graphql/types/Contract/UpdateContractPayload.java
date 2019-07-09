package com.synectiks.cms.graphql.types.Contract;

import com.synectiks.cms.domain.Contract;

public class UpdateContractPayload extends  AbstractContractPayload {
    public UpdateContractPayload(Contract contract) {
        super(contract);
    }
}
