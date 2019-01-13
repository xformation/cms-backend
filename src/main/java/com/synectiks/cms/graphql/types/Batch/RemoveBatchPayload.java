package com.synectiks.cms.graphql.types.Batch;

import com.synectiks.cms.domain.Batch;

import java.util.List;

public class RemoveBatchPayload {
    private final List<Batch> batches;

    public RemoveBatchPayload(List<Batch> batches) {
        this.batches = batches;
    }

    public List<Batch> getBatches() {
        return batches;
    }
}
