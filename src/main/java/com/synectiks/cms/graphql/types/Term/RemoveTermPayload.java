package com.synectiks.cms.graphql.types.Term;

import java.util.List;

import com.synectiks.cms.entities.Term;

public class RemoveTermPayload {

    private final List<Term> terms;

    public RemoveTermPayload(List<Term> terms){
        this.terms = terms;
    }

    public List<Term> getTerms() {
        return terms;
    }
}
