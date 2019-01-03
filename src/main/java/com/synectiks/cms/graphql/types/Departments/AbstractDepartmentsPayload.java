package com.synectiks.cms.graphql.types.Departments;

import com.synectiks.cms.domain.Departments;

public class AbstractDepartmentsPayload {
    private final Departments departments;

    public AbstractDepartmentsPayload(Departments departments) {
        this.departments = departments;
    }

    public Departments getDepartments() {
        return departments;
    }
}
