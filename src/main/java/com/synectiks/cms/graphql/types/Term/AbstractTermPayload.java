package com.synectiks.cms.graphql.types.Term;

import com.synectiks.cms.domain.Term;

public class AbstractTermPayload {
    private final Term term;

    public AbstractTermPayload(Term term){
        this.term = term;
    }

    public Term getTerm() {
        return term;
    }
}
