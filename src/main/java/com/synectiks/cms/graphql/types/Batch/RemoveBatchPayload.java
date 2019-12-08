package com.synectiks.cms.graphql.types.Batch;

import java.util.List;

import com.synectiks.cms.entities.Batch;

public class RemoveBatchPayload {
    private final List<Batch> batch;

    public RemoveBatchPayload(List<Batch> batch) {
        this.batch = batch;
    }

	public List<Batch> getBatch() {
		return batch;
	}

    
}
