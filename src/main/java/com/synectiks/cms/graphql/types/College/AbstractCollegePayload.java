package com.synectiks.cms.graphql.types.College;

import com.synectiks.commons.entities.cms.College;
import com.synectiks.commons.entities.cms.Student;

public class AbstractCollegePayload {
    private final College college;

    public AbstractCollegePayload(College college){
        this.college = college;
    }

    public College getCollege() {
        return college;
    }
}
