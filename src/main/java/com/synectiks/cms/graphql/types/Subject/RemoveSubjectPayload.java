package com.synectiks.cms.graphql.types.Subject;

import java.util.List;

import com.synectiks.cms.entities.Subject;

public class RemoveSubjectPayload {
    private final List<Subject> subjects;

    public RemoveSubjectPayload(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }
}
