package com.synectiks.cms.graphql.types.FeeCategory;

import java.util.List;

import com.synectiks.cms.entities.FeeCategory;

public class RemoveFeeCategoryPayload {
    private final List<FeeCategory> feeCategories;

    public RemoveFeeCategoryPayload(List<FeeCategory> feeCategories) {
        this.feeCategories = feeCategories;
    }

    public List<FeeCategory> getFeeCategories() {
        return feeCategories;
    }
}
