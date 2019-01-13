package com.synectiks.cms.graphql.types.Batch;

import com.synectiks.cms.domain.Batch;

public class UpdateBatchPayload extends AbstractBatchPayload{
    public UpdateBatchPayload(Batch batch) {
        super(batch);
    }
}
