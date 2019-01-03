package com.synectiks.cms.graphql.types.AcademicDepartment;

import com.synectiks.cms.domain.AcademicDepartment;

public class AbstractAcademicDepartmentPayload {

    private final AcademicDepartment academicDepartment;

    public AbstractAcademicDepartmentPayload(AcademicDepartment academicDepartment) {
        this.academicDepartment = academicDepartment;
    }

    public AcademicDepartment getAcademicDepartment() {
        return academicDepartment;
    }
}
