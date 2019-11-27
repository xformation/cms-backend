package com.synectiks.cms.graphql.types.State;

import com.synectiks.cms.domain.State;

import java.util.List;

public class RemoveStatePayload {
    private final List<State> states;

    public RemoveStatePayload(List<State> states){
        this.states = states;
    }

    public List<State> getStates(){
        return states;
    }
}
