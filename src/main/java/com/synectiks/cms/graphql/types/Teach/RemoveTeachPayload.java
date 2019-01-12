package com.synectiks.cms.graphql.types.Teach;

import java.util.List;

import com.synectiks.cms.domain.Teach;

public class RemoveTeachPayload {
    private final List<Teach> teach;

    public RemoveTeachPayload(List<Teach> teach) {
        this.teach = teach;
    }

	public List<Teach> getTeach() {
		return teach;
	}

	

    
}
