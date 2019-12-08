package com.synectiks.cms.graphql.types.FeeDetails;

import com.synectiks.cms.entities.FeeDetails;

public class AbstractFeeDetailsPayload {
    private final FeeDetails feeDetails;

    public AbstractFeeDetailsPayload(FeeDetails feeDetails) {
        this.feeDetails = feeDetails;
    }

    public FeeDetails getFeeDetails() {
        return feeDetails;
    }
}
