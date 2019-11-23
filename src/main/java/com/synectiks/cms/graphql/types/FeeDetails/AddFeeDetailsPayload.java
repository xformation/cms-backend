package com.synectiks.cms.graphql.types.FeeDetails;

import com.synectiks.commons.entities.cms.FeeDetails;

public class AddFeeDetailsPayload extends AbstractFeeDetailsPayload{
    public AddFeeDetailsPayload(FeeDetails feeDetails) {
        super(feeDetails);
    }
}
