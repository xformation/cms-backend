package com.synectiks.cms.graphql.types.FeeCategory;

import com.synectiks.commons.entities.cms.FeeCategory;

public class AddFeeCategoryPayload extends AbstractFeeCategoryPayload{
    public AddFeeCategoryPayload(FeeCategory feeCategory) {
        super(feeCategory);
    }
}
