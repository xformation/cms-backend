package com.synectiks.cms.graphql.types.Employee;

import java.util.List;

import com.synectiks.cms.entities.Employee;

public class RemoveEmployeePayload {
    private final List<Employee> employees;

    public RemoveEmployeePayload(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
