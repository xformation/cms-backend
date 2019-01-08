package com.synectiks.cms.graphql.types.Term;

import com.synectiks.cms.domain.Term;

import java.util.List;

public class RemoveTermPayload {

    private final List<Term> terms;

    public RemoveTermPayload(List<Term> terms){
        this.terms = terms;
    }

    public List<Term> getTerms() {
        return terms;
    }
}
