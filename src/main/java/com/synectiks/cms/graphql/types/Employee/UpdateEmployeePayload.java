package com.synectiks.cms.graphql.types.Employee;

import com.synectiks.cms.entities.Employee;

public class UpdateEmployeePayload extends AbstractEmployeePayload {
    public UpdateEmployeePayload(Employee employee) {
        super(employee);
    }
}
