package com.synectiks.cms.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.synectiks.cms.domain.Documents} entity.
 */
public class DocumentsDTO implements Serializable {

    private Long id;

    private String documentName;

    @Size(max = 3)
    private String isFlatFileStorage;

    private String documentFilePath;

    @Size(max = 3)
    private String isMsOneDriveStorage;

    @Size(max = 5000)
    private String oneDrivePath;

    @Size(max = 3)
    private String isOakStorage;

    @Size(max = 500)
    private String oakPath;

    @Size(max = 3)
    private String isAwsStorage;

    @Size(max = 1000)
    private String awsPath;


    private Long studentId;

    private Long vehicleId;

    private Long employeeId;

    private Long contractId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getIsFlatFileStorage() {
        return isFlatFileStorage;
    }

    public void setIsFlatFileStorage(String isFlatFileStorage) {
        this.isFlatFileStorage = isFlatFileStorage;
    }

    public String getDocumentFilePath() {
        return documentFilePath;
    }

    public void setDocumentFilePath(String documentFilePath) {
        this.documentFilePath = documentFilePath;
    }

    public String getIsMsOneDriveStorage() {
        return isMsOneDriveStorage;
    }

    public void setIsMsOneDriveStorage(String isMsOneDriveStorage) {
        this.isMsOneDriveStorage = isMsOneDriveStorage;
    }

    public String getOneDrivePath() {
        return oneDrivePath;
    }

    public void setOneDrivePath(String oneDrivePath) {
        this.oneDrivePath = oneDrivePath;
    }

    public String getIsOakStorage() {
        return isOakStorage;
    }

    public void setIsOakStorage(String isOakStorage) {
        this.isOakStorage = isOakStorage;
    }

    public String getOakPath() {
        return oakPath;
    }

    public void setOakPath(String oakPath) {
        this.oakPath = oakPath;
    }

    public String getIsAwsStorage() {
        return isAwsStorage;
    }

    public void setIsAwsStorage(String isAwsStorage) {
        this.isAwsStorage = isAwsStorage;
    }

    public String getAwsPath() {
        return awsPath;
    }

    public void setAwsPath(String awsPath) {
        this.awsPath = awsPath;
    }

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DocumentsDTO documentsDTO = (DocumentsDTO) o;
        if (documentsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), documentsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DocumentsDTO{" +
            "id=" + getId() +
            ", documentName='" + getDocumentName() + "'" +
            ", isFlatFileStorage='" + getIsFlatFileStorage() + "'" +
            ", documentFilePath='" + getDocumentFilePath() + "'" +
            ", isMsOneDriveStorage='" + getIsMsOneDriveStorage() + "'" +
            ", oneDrivePath='" + getOneDrivePath() + "'" +
            ", isOakStorage='" + getIsOakStorage() + "'" +
            ", oakPath='" + getOakPath() + "'" +
            ", isAwsStorage='" + getIsAwsStorage() + "'" +
            ", awsPath='" + getAwsPath() + "'" +
            ", student=" + getStudentId() +
            ", vehicle=" + getVehicleId() +
            ", employee=" + getEmployeeId() +
            ", contract=" + getContractId() +
            "}";
    }
}
