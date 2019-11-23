package com.synectiks.cms.graphql.types.Branch;

import com.synectiks.commons.entities.cms.Branch;

public class UpdateBranchPayload extends AbstractBranchPayload {
    public UpdateBranchPayload(Branch branch) {
        super(branch);
    }
}
