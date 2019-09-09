package com.synectiks.cms.graphql.types.Employee;

public class UpdateEmployeeInput extends AbstractEmployeeInput {
    private Long branchId;
    private Long transportRouteId;

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getTransportRouteId() {
        return transportRouteId;
    }

    public void setTransportRouteId(Long transportRouteId) {
        this.transportRouteId = transportRouteId;
    }

    @Override
    public String toString() {
        return "AddEmployeeInput{" +
            "branchId=" + branchId +
            ", transportRouteId=" + transportRouteId +
            '}';
    }
}
