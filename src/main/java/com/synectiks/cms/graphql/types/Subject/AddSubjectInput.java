package com.synectiks.cms.graphql.types.Subject;

public class AddSubjectInput extends AbstractSubjectInput {
    private Long departmentId;
    private Long batchId;

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "AddSubjectInput [batchIdId=" + batchId + ", departmentId=" + departmentId
            + "]";
    }

}
