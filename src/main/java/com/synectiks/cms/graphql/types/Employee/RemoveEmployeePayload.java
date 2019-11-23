package com.synectiks.cms.graphql.types.Employee;

import com.synectiks.commons.entities.cms.Employee;

import java.util.List;

public class RemoveEmployeePayload {
    private final List<Employee> employees;

    public RemoveEmployeePayload(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
