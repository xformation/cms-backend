package com.synectiks.cms.graphql.types.Department;

import com.synectiks.cms.domain.Department;

public class UpdateDepartmentPayload extends AbstractDepartmentPayload{
    public UpdateDepartmentPayload(Department department) {
        super(department);
    }
}
