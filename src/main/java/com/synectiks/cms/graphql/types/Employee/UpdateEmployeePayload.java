package com.synectiks.cms.graphql.types.Employee;

import com.synectiks.commons.entities.cms.Employee;

public class UpdateEmployeePayload extends AbstractEmployeePayload {
    public UpdateEmployeePayload(Employee employee) {
        super(employee);
    }
}
