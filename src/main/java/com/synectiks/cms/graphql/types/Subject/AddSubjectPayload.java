package com.synectiks.cms.graphql.types.Subject;

import com.synectiks.cms.domain.Subject;

public class AddSubjectPayload extends AbstractSubjectPayload{
    public AddSubjectPayload(Subject subject) {
        super(subject);
    }
}
