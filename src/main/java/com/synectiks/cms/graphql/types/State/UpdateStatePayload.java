package com.synectiks.cms.graphql.types.State;

import com.synectiks.cms.entities.State;

public class UpdateStatePayload extends AbstractStatePayload {
    public UpdateStatePayload(State state){
        super(state);
    }
}
