package com.synectiks.cms.graphql.types.FeeDetails;

import com.synectiks.commons.entities.cms.FeeDetails;

import java.util.List;

public class RemoveFeeDetailsPayload {
    private final List<FeeDetails> feeDetails;

    public RemoveFeeDetailsPayload(List<FeeDetails> feeDetails) {
        this.feeDetails = feeDetails;
    }

    public List<FeeDetails> getFeeDetails() {
        return feeDetails;
    }
}
