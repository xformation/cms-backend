package com.synectiks.cms.graphql.types.Branch;

import com.synectiks.cms.entities.Branch;

public class AbstractBranchPayload {
    private final Branch branch;

    public AbstractBranchPayload(Branch branch) {
        this.branch = branch;
    }

    public Branch getBranch() {
        return branch;
    }
}
