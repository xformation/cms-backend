package com.synectiks.cms.graphql.types.FeeCategory;

import com.synectiks.commons.entities.cms.City;
import com.synectiks.commons.entities.cms.FeeCategory;

public class AbstractFeeCategoryPayload {
    private final FeeCategory feeCategory;

    public AbstractFeeCategoryPayload(FeeCategory feeCategory) {
        this.feeCategory = feeCategory;
    }

    public FeeCategory getFeeCategory() {
        return feeCategory;
    }
}
