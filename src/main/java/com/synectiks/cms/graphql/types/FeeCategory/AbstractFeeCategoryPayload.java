package com.synectiks.cms.graphql.types.FeeCategory;

import com.synectiks.cms.domain.City;
import com.synectiks.cms.domain.FeeCategory;

public class AbstractFeeCategoryPayload {
    private final FeeCategory feeCategory;

    public AbstractFeeCategoryPayload(FeeCategory feeCategory) {
        this.feeCategory = feeCategory;
    }

    public FeeCategory getFeeCategory() {
        return feeCategory;
    }
}
