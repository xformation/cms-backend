package com.synectiks.cms.graphql.types.College;

import com.synectiks.cms.domain.College;

import java.util.List;

public class RemoveCollegePayload {
    private final List<College> colleges;

    public RemoveCollegePayload(List<College> colleges){
        this.colleges = colleges;
    }

    public List<College> getColleges() {
        return colleges;
    }
}
