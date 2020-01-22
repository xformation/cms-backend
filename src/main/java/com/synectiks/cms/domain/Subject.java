package com.synectiks.cms.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A Subject.
 */
@Entity
@Table(name = "subject")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "subject")
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "subject_code")
    private String subjectCode;

    @Column(name = "subject_type")
    private String subjectType;

    @Column(name = "subject_desc")
    private String subjectDesc;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JsonIgnoreProperties("subjects")
    private Department department;

    @ManyToOne
    @JsonIgnoreProperties("subjects")
    private Batch batch;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public Subject subjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
        return this;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public Subject subjectType(String subjectType) {
        this.subjectType = subjectType;
        return this;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public String getSubjectDesc() {
        return subjectDesc;
    }

    public Subject subjectDesc(String subjectDesc) {
        this.subjectDesc = subjectDesc;
        return this;
    }

    public void setSubjectDesc(String subjectDesc) {
        this.subjectDesc = subjectDesc;
    }

    public String getStatus() {
        return status;
    }

    public Subject status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Department getDepartment() {
        return department;
    }

    public Subject department(Department department) {
        this.department = department;
        return this;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Batch getBatch() {
        return batch;
    }

    public Subject batch(Batch batch) {
        this.batch = batch;
        return this;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Subject)) {
            return false;
        }
        return id != null && id.equals(((Subject) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Subject{" +
            "id=" + getId() +
            ", subjectCode='" + getSubjectCode() + "'" +
            ", subjectType='" + getSubjectType() + "'" +
            ", subjectDesc='" + getSubjectDesc() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
