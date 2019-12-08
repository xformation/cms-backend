package com.synectiks.cms.graphql.types.College;

import com.synectiks.cms.entities.College;
import com.synectiks.cms.entities.Student;

public class AbstractCollegePayload {
    private final College college;

    public AbstractCollegePayload(College college){
        this.college = college;
    }

    public College getCollege() {
        return college;
    }
}
