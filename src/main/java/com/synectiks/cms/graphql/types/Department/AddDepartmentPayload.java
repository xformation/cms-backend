package com.synectiks.cms.graphql.types.Department;

import com.synectiks.cms.domain.Department;

public class AddDepartmentPayload extends AbstractDepartmentPayload{
    public AddDepartmentPayload(Department department) {
        super(department);
    }
}
