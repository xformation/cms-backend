package com.synectiks.cms.graphql.types.Batch;

import com.synectiks.cms.domain.Batch;

public class AddBatchPayload extends AbstractBatchPayload {
    public AddBatchPayload(Batch batch) {
        super(batch);
    }
}
