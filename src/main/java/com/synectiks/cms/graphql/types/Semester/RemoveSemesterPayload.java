package com.synectiks.cms.graphql.types.Semester;

import com.synectiks.cms.domain.Semester;

import java.util.List;

public class RemoveSemesterPayload {
    private final List<Semester> semesters;

    public RemoveSemesterPayload(List<Semester> semesters) {
        this.semesters = semesters;
    }

    public List<Semester> getSemesters() {
        return semesters;
    }
}
