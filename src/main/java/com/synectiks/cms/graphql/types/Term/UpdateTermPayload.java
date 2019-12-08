package com.synectiks.cms.graphql.types.Term;

import com.synectiks.cms.entities.Term;

public class UpdateTermPayload extends AbstractTermPayload {
    public UpdateTermPayload(Term term){
        super(term);
    }
}
