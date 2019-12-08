package com.synectiks.cms.graphql.types.Department;

import com.synectiks.cms.entities.Department;

public class AddDepartmentPayload extends AbstractDepartmentPayload{
    public AddDepartmentPayload(Department department) {
        super(department);
    }
}
