package com.synectiks.cms.graphql.types.College;

import com.synectiks.cms.entities.College;

public class AddCollegePayload extends AbstractCollegePayload {

    public AddCollegePayload(College college){
        super(college);
    }
}
