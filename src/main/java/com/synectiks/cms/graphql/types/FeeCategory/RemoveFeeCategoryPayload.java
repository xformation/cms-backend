package com.synectiks.cms.graphql.types.FeeCategory;

import com.synectiks.cms.domain.FeeCategory;

import java.util.List;

public class RemoveFeeCategoryPayload {
    private final List<FeeCategory> feeCategories;

    public RemoveFeeCategoryPayload(List<FeeCategory> feeCategories) {
        this.feeCategories = feeCategories;
    }

    public List<FeeCategory> getFeeCategories() {
        return feeCategories;
    }
}
