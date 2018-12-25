package com.synectiks.cms.graphql.types.Institute;


public class UpdateInstituteInput extends AbstractInstituteInput {
    private int instituteId;

    public int getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(int instituteId) {
        this.instituteId = instituteId;
    }
}
