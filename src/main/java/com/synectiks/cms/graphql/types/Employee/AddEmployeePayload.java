package com.synectiks.cms.graphql.types.Employee;

import com.synectiks.cms.entities.Employee;

public class AddEmployeePayload extends AbstractEmployeePayload {
    public AddEmployeePayload(Employee employee) {
        super(employee);
    }
}
