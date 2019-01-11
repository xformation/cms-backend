package com.synectiks.cms.graphql.types.Branch;

import com.synectiks.cms.domain.Branch;

public class UpdateBranchPayload extends AbstractBranchPayload {
    public UpdateBranchPayload(Branch branch) {
        super(branch);
    }
}
