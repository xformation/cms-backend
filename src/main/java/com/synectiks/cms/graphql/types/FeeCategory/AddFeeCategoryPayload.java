package com.synectiks.cms.graphql.types.FeeCategory;

import com.synectiks.cms.entities.FeeCategory;

public class AddFeeCategoryPayload extends AbstractFeeCategoryPayload{
    public AddFeeCategoryPayload(FeeCategory feeCategory) {
        super(feeCategory);
    }
}
