package com.synectiks.cms.graphql.types.Department;

import com.synectiks.commons.entities.cms.Department;

public class AbstractDepartmentPayload {
    private final Department department;

    public AbstractDepartmentPayload(Department department) {
        this.department = department;
    }

    public Department getDepartments() {
        return department;
    }
}
