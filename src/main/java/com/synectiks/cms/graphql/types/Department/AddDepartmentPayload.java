package com.synectiks.cms.graphql.types.Department;

import com.synectiks.commons.entities.cms.Department;

public class AddDepartmentPayload extends AbstractDepartmentPayload{
    public AddDepartmentPayload(Department department) {
        super(department);
    }
}
