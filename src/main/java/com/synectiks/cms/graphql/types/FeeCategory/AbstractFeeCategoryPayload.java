package com.synectiks.cms.graphql.types.FeeCategory;

import com.synectiks.cms.entities.City;
import com.synectiks.cms.entities.FeeCategory;

public class AbstractFeeCategoryPayload {
    private final FeeCategory feeCategory;

    public AbstractFeeCategoryPayload(FeeCategory feeCategory) {
        this.feeCategory = feeCategory;
    }

    public FeeCategory getFeeCategory() {
        return feeCategory;
    }
}
