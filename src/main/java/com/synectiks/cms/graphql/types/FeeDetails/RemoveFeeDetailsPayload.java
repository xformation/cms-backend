package com.synectiks.cms.graphql.types.FeeDetails;

import java.util.List;

import com.synectiks.cms.entities.FeeDetails;

public class RemoveFeeDetailsPayload {
    private final List<FeeDetails> feeDetails;

    public RemoveFeeDetailsPayload(List<FeeDetails> feeDetails) {
        this.feeDetails = feeDetails;
    }

    public List<FeeDetails> getFeeDetails() {
        return feeDetails;
    }
}
