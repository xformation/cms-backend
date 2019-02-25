package com.synectiks.cms.graphql.types.LateFee;

import com.synectiks.cms.domain.LateFee;

public class UpdateLateFeePayload extends AbstractLateFeePayload {
    public UpdateLateFeePayload(LateFee lateFee) {
        super(lateFee);
    }
}
