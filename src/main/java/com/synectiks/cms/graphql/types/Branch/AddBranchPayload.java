package com.synectiks.cms.graphql.types.Branch;

import com.synectiks.commons.entities.cms.Branch;

public class AddBranchPayload extends AbstractBranchPayload {
    public AddBranchPayload(Branch branch) {
        super(branch);
    }
}
