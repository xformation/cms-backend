package com.synectiks.cms.graphql.types.State;

import com.synectiks.cms.domain.State;

public class UpdateStatePayload extends AbstractStatePayload {
    public UpdateStatePayload(State state){
        super(state);
    }
}
