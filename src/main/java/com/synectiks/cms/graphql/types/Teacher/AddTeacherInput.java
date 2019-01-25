package com.synectiks.cms.graphql.types.Teacher;

import java.util.Objects;

public class AddTeacherInput extends AbstractTeacherInput {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AddTeacherInput that = (AddTeacherInput) o;
        return Objects.equals(branchId, that.branchId) &&
            Objects.equals(departmentId, that.departmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), branchId, departmentId);
    }

    @Override
    public String toString() {
        return "AddTeacherInput{" +
            "branchId=" + branchId +
            ", departmentId=" + departmentId +
            '}';
    }
}
