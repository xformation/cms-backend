package com.synectiks.cms.graphql.types.Branch;

import com.synectiks.cms.domain.Branch;

public class AddBranchPayload extends AbstractBranchPayload {
    public AddBranchPayload(Branch branch) {
        super(branch);
    }
}
