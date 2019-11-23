package com.synectiks.cms.graphql.types.Subject;

import com.synectiks.commons.entities.cms.Subject;

public class AbstractSubjectPayload {
    private final Subject subject;

    public AbstractSubjectPayload(Subject subject) {
        this.subject = subject;
    }

    public Subject getSubject() {
        return subject;
    }
}
