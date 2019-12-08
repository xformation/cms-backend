package com.synectiks.cms.graphql.types.LateFee;

import com.synectiks.cms.entities.LateFee;

public class AbstractLateFeePayload {
    private final LateFee lateFee;

    public AbstractLateFeePayload(LateFee lateFee) {
        this.lateFee = lateFee;
    }

    public LateFee getLateFee() {
        return lateFee;
    }
}
