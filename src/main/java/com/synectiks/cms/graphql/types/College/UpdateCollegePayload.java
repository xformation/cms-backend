package com.synectiks.cms.graphql.types.College;

import com.synectiks.cms.entities.College;

public class UpdateCollegePayload extends AbstractCollegePayload {
    public UpdateCollegePayload(College college){
        super(college);
    }
}
