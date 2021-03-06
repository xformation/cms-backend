package com.synectiks.cms.graphql.types.Department;

import com.synectiks.cms.domain.Department;

public class AbstractDepartmentPayload {
    private final Department department;

    public AbstractDepartmentPayload(Department department) {
        this.department = department;
    }

    public Department getDepartments() {
        return department;
    }
}
