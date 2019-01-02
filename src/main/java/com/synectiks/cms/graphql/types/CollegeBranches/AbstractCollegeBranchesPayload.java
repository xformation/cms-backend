package com.synectiks.cms.graphql.types.CollegeBranches;

import com.synectiks.cms.domain.CollegeBranches;

public class AbstractCollegeBranchesPayload {
    private final CollegeBranches collegeBranches;

    public AbstractCollegeBranchesPayload(CollegeBranches collegeBranches) {
        this.collegeBranches = collegeBranches;
    }

    public CollegeBranches getCollegeBranches() {
        return collegeBranches;
    }
}
