package com.synectiks.cms.graphql.types.College;

import com.synectiks.commons.entities.cms.College;

public class UpdateCollegePayload extends AbstractCollegePayload {
    public UpdateCollegePayload(College college){
        super(college);
    }
}
