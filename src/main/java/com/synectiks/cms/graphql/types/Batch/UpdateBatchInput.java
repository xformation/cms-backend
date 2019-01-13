package com.synectiks.cms.graphql.types.Batch;

public class UpdateBatchInput extends AbstractBatchInput{
    private Long departmentId;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
