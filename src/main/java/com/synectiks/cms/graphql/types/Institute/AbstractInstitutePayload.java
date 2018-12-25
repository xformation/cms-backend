package com.synectiks.cms.graphql.types.Institute;


import com.synectiks.cms.model.Institute;

public class AbstractInstitutePayload {
    private final Institute institute;

    public AbstractInstitutePayload(Institute institute) {
        this.institute = institute;
    }


    public Institute getInstitute() {
        return institute;
    }
}
