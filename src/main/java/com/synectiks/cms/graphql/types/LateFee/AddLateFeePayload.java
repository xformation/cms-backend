package com.synectiks.cms.graphql.types.LateFee;

import com.synectiks.cms.entities.LateFee;

public class AddLateFeePayload extends AbstractLateFeePayload{
    public AddLateFeePayload(LateFee lateFee) {
        super(lateFee);
    }
}
