package com.synectiks.cms.graphql.types.Department;

import com.synectiks.commons.entities.cms.Department;

public class UpdateDepartmentPayload extends AbstractDepartmentPayload{
    public UpdateDepartmentPayload(Department department) {
        super(department);
    }
}
