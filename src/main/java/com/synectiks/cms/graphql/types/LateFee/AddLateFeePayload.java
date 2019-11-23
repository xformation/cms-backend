package com.synectiks.cms.graphql.types.LateFee;

import com.synectiks.commons.entities.cms.LateFee;

public class AddLateFeePayload extends AbstractLateFeePayload{
    public AddLateFeePayload(LateFee lateFee) {
        super(lateFee);
    }
}
