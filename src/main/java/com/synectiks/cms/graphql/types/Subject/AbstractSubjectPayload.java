package com.synectiks.cms.graphql.types.Subject;

import com.synectiks.cms.entities.Subject;

public class AbstractSubjectPayload {
    private final Subject subject;

    public AbstractSubjectPayload(Subject subject) {
        this.subject = subject;
    }

    public Subject getSubject() {
        return subject;
    }
}
