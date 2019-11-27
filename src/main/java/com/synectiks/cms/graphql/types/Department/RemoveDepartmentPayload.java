package com.synectiks.cms.graphql.types.Department;

import java.util.List;

import com.synectiks.cms.domain.Department;

public class RemoveDepartmentPayload {
    private final List<Department> department;

    public RemoveDepartmentPayload(List<Department> department) {
        this.department = department;
    }

    public List<Department> getDepartment() {
        return department;
    }
}
