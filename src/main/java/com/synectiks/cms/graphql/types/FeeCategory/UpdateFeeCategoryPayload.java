package com.synectiks.cms.graphql.types.FeeCategory;

import com.synectiks.cms.entities.FeeCategory;

public class UpdateFeeCategoryPayload extends AbstractFeeCategoryPayload{
    public UpdateFeeCategoryPayload(FeeCategory feeCategory) {
        super(feeCategory);
    }
}
