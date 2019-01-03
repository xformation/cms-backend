package com.synectiks.cms.graphql.types.Departments;

import com.synectiks.cms.domain.Departments;

import java.util.List;

public class RemoveDepartmentsPayload {
    private final List<Departments> departments;

    public RemoveDepartmentsPayload(List<Departments> departments) {
        this.departments = departments;
    }

    public List<Departments> getDepartments() {
        return departments;
    }
}
