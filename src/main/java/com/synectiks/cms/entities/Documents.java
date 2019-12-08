package com.synectiks.cms.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.synectiks.cms.utils.IESEntity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

//import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Documents.
 */
@Entity
@Table(name = "documents")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//@Document(indexName = "documents")
public class Documents implements Serializable, IESEntity {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "document_name", nullable = false)
    private String documentName;

    @NotNull
    @Column(name = "document_file_path", nullable = false)
    private String documentFilePath;

    @ManyToOne
    @JsonIgnoreProperties("documents")
    private Student student;

    @ManyToOne
    @JsonIgnoreProperties("documents")
    private Vehicle vehicle;

    @ManyToOne
    @JsonIgnoreProperties("documents")
    private Employee employee;

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

    public Employee getEmployee() {
        return employee;
    }

    public Documents employee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Documents documents = (Documents) o;
        if (documents.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), documents.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Documents{" +
            "id=" + getId() +
            ", documentName='" + getDocumentName() + "'" +
            ", documentFilePath='" + getDocumentFilePath() + "'" +
            "}";
    }
}
