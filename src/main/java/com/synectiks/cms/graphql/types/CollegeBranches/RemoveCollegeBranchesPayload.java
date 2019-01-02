package com.synectiks.cms.graphql.types.CollegeBranches;

import com.synectiks.cms.domain.CollegeBranches;

import java.util.List;

public class RemoveCollegeBranchesPayload {
    private final List<CollegeBranches> collegeBranches;

    public RemoveCollegeBranchesPayload(List<CollegeBranches> collegeBranches) {
        this.collegeBranches = collegeBranches;
    }

    public List<CollegeBranches> getCollegeBranches() {
        return collegeBranches;
    }
}
