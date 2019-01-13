package com.synectiks.cms.graphql.types.Batch;

public class AddBatchInput extends AbstractBatchInput{
    private Long departmentId;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "AddBatchInput{" +
            "departmentId=" + departmentId +
            '}'+ super.toString();
    }
}
