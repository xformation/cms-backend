package com.synectiks.cms.graphql.types.Institute;

import com.synectiks.cms.model.Institute;

import java.util.List;

public class RemoveInstitutePayload {
    private final List<Institute> institutes;

    public RemoveInstitutePayload(List<Institute> institutes) {
        this.institutes = institutes;
    }

    public List<Institute> getInstitutes() {
        return institutes;
    }
}
