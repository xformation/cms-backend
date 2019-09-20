package com.synectiks.cms.graphql.types.Vehicle;

public class UpdateVehicleInput extends AbstractVehicleInput {
    private Long employeeId;
    private Long transportRouteId;
    private Long insuranceId;
    private Long contractId;
    private Long branchId;
    private Long collegeId;
    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getTransportRouteId() {
        return transportRouteId;
    }

    public void setTransportRouteId(Long transportRouteId) {
        this.transportRouteId = transportRouteId;
    }

    public Long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    @Override
    public String toString() {
        return "AddVehicleInput{" +
            "employeeId=" + employeeId +
            ", transportRouteId=" + transportRouteId +
            ", insuranceId=" + insuranceId +
            ", contractId=" + contractId +
            ", branchId=" + branchId +
            ", collegeId=" + collegeId +
            '}';
    }
}
