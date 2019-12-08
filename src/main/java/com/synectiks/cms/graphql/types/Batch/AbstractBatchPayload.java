package com.synectiks.cms.graphql.types.Batch;

import com.synectiks.cms.entities.Batch;

public class AbstractBatchPayload {
    private final Batch batch;

    public AbstractBatchPayload(Batch batch) {
        this.batch = batch;
    }

	public Batch getBatch() {
		return batch;
	}

    
}
