package com.synectiks.cms.graphql.types.FeeCategory;

import com.synectiks.cms.domain.FeeCategory;

public class AddFeeCategoryPayload extends AbstractFeeCategoryPayload{
    public AddFeeCategoryPayload(FeeCategory feeCategory) {
        super(feeCategory);
    }
}
