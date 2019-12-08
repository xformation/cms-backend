package com.synectiks.cms.graphql.types.State;

import java.util.List;

import com.synectiks.cms.entities.State;

public class RemoveStatePayload {
    private final List<State> states;

    public RemoveStatePayload(List<State> states){
        this.states = states;
    }

    public List<State> getStates(){
        return states;
    }
}
