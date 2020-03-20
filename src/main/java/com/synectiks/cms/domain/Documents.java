package com.synectiks.cms.domain;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A Documents.
 */
@Entity
@Table(name = "documents")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//@org.springframework.data.elasticsearch.annotations.Document(indexName = "documents")
public class Documents implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "document_name")
    private String documentName;

    @Size(max = 3)
    @Column(name = "is_flat_file_storage", length = 3)
    private String isFlatFileStorage;

    @Column(name = "document_file_path")
    private String documentFilePath;

    @Size(max = 3)
    @Column(name = "is_ms_one_drive_storage", length = 3)
    private String isMsOneDriveStorage;

    @Size(max = 5000)
    @Column(name = "one_drive_path", length = 5000)
    private String oneDrivePath;

    @Size(max = 3)
    @Column(name = "is_oak_storage", length = 3)
    private String isOakStorage;

    @Size(max = 500)
    @Column(name = "oak_path", length = 500)
    private String oakPath;

    @Size(max = 3)
    @Column(name = "is_aws_storage", length = 3)
    private String isAwsStorage;

    @Size(max = 1000)
    @Column(name = "aws_path", length = 1000)
    private String awsPath;

    @ManyToOne
    @JsonIgnoreProperties("documents")
    private Student student;

    @ManyToOne
    @JsonIgnoreProperties("documents")
    private Vehicle vehicle;

    @Column(name = "employee_id")
    private Long employeeId;

    @ManyToOne
    @JsonIgnoreProperties("documents")
    private Contract contract;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public Documents documentName(String documentName) {
        this.documentName = documentName;
        return this;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getIsFlatFileStorage() {
        return isFlatFileStorage;
    }

    public Documents isFlatFileStorage(String isFlatFileStorage) {
        this.isFlatFileStorage = isFlatFileStorage;
        return this;
    }

    public void setIsFlatFileStorage(String isFlatFileStorage) {
        this.isFlatFileStorage = isFlatFileStorage;
    }

    public String getDocumentFilePath() {
        return documentFilePath;
    }

    public Documents documentFilePath(String documentFilePath) {
        this.documentFilePath = documentFilePath;
        return this;
    }

    public void setDocumentFilePath(String documentFilePath) {
        this.documentFilePath = documentFilePath;
    }

    public String getIsMsOneDriveStorage() {
        return isMsOneDriveStorage;
    }

    public Documents isMsOneDriveStorage(String isMsOneDriveStorage) {
        this.isMsOneDriveStorage = isMsOneDriveStorage;
        return this;
    }

    public void setIsMsOneDriveStorage(String isMsOneDriveStorage) {
        this.isMsOneDriveStorage = isMsOneDriveStorage;
    }

    public String getOneDrivePath() {
        return oneDrivePath;
    }

    public Documents oneDrivePath(String oneDrivePath) {
        this.oneDrivePath = oneDrivePath;
        return this;
    }

    public void setOneDrivePath(String oneDrivePath) {
        this.oneDrivePath = oneDrivePath;
    }

    public String getIsOakStorage() {
        return isOakStorage;
    }

    public Documents isOakStorage(String isOakStorage) {
        this.isOakStorage = isOakStorage;
        return this;
    }

    public void setIsOakStorage(String isOakStorage) {
        this.isOakStorage = isOakStorage;
    }

    public String getOakPath() {
        return oakPath;
    }

    public Documents oakPath(String oakPath) {
        this.oakPath = oakPath;
        return this;
    }

    public void setOakPath(String oakPath) {
        this.oakPath = oakPath;
    }

    public String getIsAwsStorage() {
        return isAwsStorage;
    }

    public Documents isAwsStorage(String isAwsStorage) {
        this.isAwsStorage = isAwsStorage;
        return this;
    }

    public void setIsAwsStorage(String isAwsStorage) {
        this.isAwsStorage = isAwsStorage;
    }

    public String getAwsPath() {
        return awsPath;
    }

    public Documents awsPath(String awsPath) {
        this.awsPath = awsPath;
        return this;
    }

    public void setAwsPath(String awsPath) {
        this.awsPath = awsPath;
    }

    public Student getStudent() {
        return student;
    }

    public Documents student(Student student) {
        this.student = student;
        return this;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Documents vehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

        public Contract getContract() {
        return contract;
    }

    public Documents contract(Contract contract) {
        this.contract = contract;
        return this;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Documents)) {
            return false;
        }
        return id != null && id.equals(((Documents) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Documents{" +
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
            "}";
    }

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
}
