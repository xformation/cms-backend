package com.synectiks.cms.graphql.types.College;

public class UpdateCollegeInput extends AbstractCollegeInput {

    private  Long collegeId;

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }
}
