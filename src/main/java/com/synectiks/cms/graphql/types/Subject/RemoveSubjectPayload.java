package com.synectiks.cms.graphql.types.Subject;

import com.synectiks.cms.domain.Subject;

import java.util.List;

public class RemoveSubjectPayload {
    private final List<Subject> subjects;

    public RemoveSubjectPayload(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }
}
