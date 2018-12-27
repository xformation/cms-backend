package com.synectiks.cms.graphql.types.College;

import com.synectiks.cms.domain.College;

public class AddCollegePayload extends AbstractCollegePayload {

    public AddCollegePayload(College college){
        super(college);
    }
}
