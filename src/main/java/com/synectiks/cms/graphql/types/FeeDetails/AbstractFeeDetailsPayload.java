package com.synectiks.cms.graphql.types.FeeDetails;

import com.synectiks.commons.entities.cms.FeeDetails;

public class AbstractFeeDetailsPayload {
    private final FeeDetails feeDetails;

    public AbstractFeeDetailsPayload(FeeDetails feeDetails) {
        this.feeDetails = feeDetails;
    }

    public FeeDetails getFeeDetails() {
        return feeDetails;
    }
}
