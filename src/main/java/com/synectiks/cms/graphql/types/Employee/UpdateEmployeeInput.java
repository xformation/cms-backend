package com.synectiks.cms.graphql.types.Employee;

public class UpdateEmployeeInput extends AbstractEmployeeInput {
    private Long branchId;

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    @Override
    public String toString() {
        return "AddEmployeeInput{" +
            "branchId=" + branchId +
            '}';
    }
}
