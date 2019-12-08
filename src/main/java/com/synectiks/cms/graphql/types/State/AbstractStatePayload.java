package com.synectiks.cms.graphql.types.State;

import com.synectiks.cms.entities.State;

public class AbstractStatePayload {
    private final State state;

    public AbstractStatePayload(State state){
        this.state = state;
    }

    public State getState(){
        return state;
    }
}
