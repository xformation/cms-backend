package com.synectiks.cms.graphql.types.Branch;

import java.util.List;

import com.synectiks.cms.domain.Branch;

public class RemoveBranchPayload {
    private final List<Branch> branch;

    public RemoveBranchPayload(List<Branch> branch) {
        this.branch = branch;
    }

    public List<Branch> getBranch() {
        return branch;
    }
}
