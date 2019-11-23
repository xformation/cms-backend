package com.synectiks.cms.graphql.types.FeeDetails;

import com.synectiks.commons.entities.cms.FeeDetails;

public class UpdateFeeDetailsPayload extends AbstractFeeDetailsPayload {
    public UpdateFeeDetailsPayload(FeeDetails feeDetails) {
        super(feeDetails);
    }
}
