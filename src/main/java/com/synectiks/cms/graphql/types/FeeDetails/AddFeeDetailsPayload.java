package com.synectiks.cms.graphql.types.FeeDetails;

import com.synectiks.cms.domain.FeeDetails;

public class AddFeeDetailsPayload extends AbstractFeeDetailsPayload{
    public AddFeeDetailsPayload(FeeDetails feeDetails) {
        super(feeDetails);
    }
}
