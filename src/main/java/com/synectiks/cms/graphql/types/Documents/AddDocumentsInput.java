package com.synectiks.cms.graphql.types.Documents;

public class AddDocumentsInput extends AbstractDocumentsInput {

    private Long studentId;
    private Long vehicleId;
    private Long employeeId;
    private Long contractId;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    @Override
    public String toString() {
        return "AddDocumentsInput{" +
            "studentId=" + studentId +
            ", vehicleId=" + vehicleId +
            ", employeeId=" + employeeId +
            ", contractId=" + contractId +
            '}';
    }
}
