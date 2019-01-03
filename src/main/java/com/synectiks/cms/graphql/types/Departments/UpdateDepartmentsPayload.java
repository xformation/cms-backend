package com.synectiks.cms.graphql.types.Departments;

import com.synectiks.cms.domain.Departments;

public class UpdateDepartmentsPayload extends AbstractDepartmentsPayload{
    public UpdateDepartmentsPayload(Departments departments) {
        super(departments);
    }
}
