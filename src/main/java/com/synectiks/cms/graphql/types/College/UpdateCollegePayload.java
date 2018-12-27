package com.synectiks.cms.graphql.types.College;

import com.synectiks.cms.domain.College;

public class UpdateCollegePayload extends AbstractCollegePayload {
    public UpdateCollegePayload(College college){
        super(college);
    }
}
