package com.synectiks.cms.graphql.types.State;

import com.synectiks.cms.domain.State;

public class AddStatePayload extends AbstractStatePayload {
    public AddStatePayload(State state){
        super(state);
    }
}
