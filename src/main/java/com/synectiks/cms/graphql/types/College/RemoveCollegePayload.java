package com.synectiks.cms.graphql.types.College;

import java.util.List;

import com.synectiks.cms.entities.College;

public class RemoveCollegePayload {
    private final List<College> colleges;

    public RemoveCollegePayload(List<College> colleges){
        this.colleges = colleges;
    }

    public List<College> getColleges() {
        return colleges;
    }
}
