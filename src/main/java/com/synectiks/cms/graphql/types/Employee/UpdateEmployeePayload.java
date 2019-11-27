package com.synectiks.cms.graphql.types.Employee;

import com.synectiks.cms.domain.Employee;

public class UpdateEmployeePayload extends AbstractEmployeePayload {
    public UpdateEmployeePayload(Employee employee) {
        super(employee);
    }
}
