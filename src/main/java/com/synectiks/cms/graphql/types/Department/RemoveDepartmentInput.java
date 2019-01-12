package com.synectiks.cms.graphql.types.Department;

public class RemoveDepartmentInput {
    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    private Long departmentId;
}
