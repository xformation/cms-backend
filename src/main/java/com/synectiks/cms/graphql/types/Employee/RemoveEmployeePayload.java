package com.synectiks.cms.graphql.types.Employee;

import com.synectiks.cms.domain.Employee;

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
