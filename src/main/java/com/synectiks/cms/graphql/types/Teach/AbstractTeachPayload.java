package com.synectiks.cms.graphql.types.Teach;

import com.synectiks.commons.entities.cms.Teach;

public class AbstractTeachPayload {
    private final Teach teach;

    public AbstractTeachPayload(Teach teach) {
        this.teach = teach;
    }

	public Teach getTeach() {
		return teach;
	}

	

    
}
