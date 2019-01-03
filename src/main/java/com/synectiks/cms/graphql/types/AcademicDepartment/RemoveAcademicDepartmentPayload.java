package com.synectiks.cms.graphql.types.AcademicDepartment;

import com.synectiks.cms.domain.AcademicDepartment;

import java.util.List;

public class RemoveAcademicDepartmentPayload {
    private final List<AcademicDepartment> academicDepartments;

    public RemoveAcademicDepartmentPayload(List<AcademicDepartment> academicDepartments) {
        this.academicDepartments = academicDepartments;
    }

    public List<AcademicDepartment> getAcademicDepartments() {
        return academicDepartments;
    }
}
