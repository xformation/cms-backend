package com.synectiks.cms.graphql.types.Teacher;

public class UpdateTeacherInput extends AbstractTeacherInput {
    private Long branchId;
    private Long departmentId;

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
