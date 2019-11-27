package com.synectiks.cms.graphql.types.FeeDetails;

import com.synectiks.cms.domain.FeeDetails;

public class UpdateFeeDetailsPayload extends AbstractFeeDetailsPayload {
    public UpdateFeeDetailsPayload(FeeDetails feeDetails) {
        super(feeDetails);
    }
}
