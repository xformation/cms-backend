package com.synectiks.cms.graphql.types.LateFee;

import com.synectiks.commons.entities.cms.LateFee;

public class UpdateLateFeePayload extends AbstractLateFeePayload {
    public UpdateLateFeePayload(LateFee lateFee) {
        super(lateFee);
    }
}
