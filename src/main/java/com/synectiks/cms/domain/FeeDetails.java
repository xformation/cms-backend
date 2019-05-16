package com.synectiks.cms.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

import com.synectiks.cms.domain.enumeration.StudentTypeEnum;

import com.synectiks.cms.domain.enumeration.Gender;

/**
 * A FeeDetails.
 */
@Entity
@Table(name = "fee_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "feedetails")
public class FeeDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "fee_particulars_name", nullable = false)
    private String feeParticularsName;

    @NotNull
    @Column(name = "fee_particular_desc", nullable = false)
    private String feeParticularDesc;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "student_type", nullable = false)
    private StudentTypeEnum studentType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Long amount;

    @ManyToOne
    @JsonIgnoreProperties("")
    private FeeCategory feeCategory;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Batch batch;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Facility facility;

    @ManyToOne
    @JsonIgnoreProperties("")
    private TransportRoute transportRoute;

    @ManyToOne
    @JsonIgnoreProperties("")
    private College college;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Department department;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Branch branch;

    @ManyToOne
    @JsonIgnoreProperties("")
    private AcademicYear academicYear;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeeParticularsName() {
        return feeParticularsName;
    }

    public FeeDetails feeParticularsName(String feeParticularsName) {
        this.feeParticularsName = feeParticularsName;
        return this;
    }

    public void setFeeParticularsName(String feeParticularsName) {
        this.feeParticularsName = feeParticularsName;
    }

    public String getFeeParticularDesc() {
        return feeParticularDesc;
    }

    public FeeDetails feeParticularDesc(String feeParticularDesc) {
        this.feeParticularDesc = feeParticularDesc;
        return this;
    }

    public void setFeeParticularDesc(String feeParticularDesc) {
        this.feeParticularDesc = feeParticularDesc;
    }

    public StudentTypeEnum getStudentType() {
        return studentType;
    }

    public FeeDetails studentType(StudentTypeEnum studentType) {
        this.studentType = studentType;
        return this;
    }

    public void setStudentType(StudentTypeEnum studentType) {
        this.studentType = studentType;
    }

    public Gender getGender() {
        return gender;
    }

    public FeeDetails gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Long getAmount() {
        return amount;
    }

    public FeeDetails amount(Long amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public FeeCategory getFeeCategory() {
        return feeCategory;
    }

    public FeeDetails feeCategory(FeeCategory feeCategory) {
        this.feeCategory = feeCategory;
        return this;
    }

    public void setFeeCategory(FeeCategory feeCategory) {
        this.feeCategory = feeCategory;
    }

    public Batch getBatch() {
        return batch;
    }

    public FeeDetails batch(Batch batch) {
        this.batch = batch;
        return this;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public Facility getFacility() {
        return facility;
    }

    public FeeDetails facility(Facility facility) {
        this.facility = facility;
        return this;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public TransportRoute getTransportRoute() {
        return transportRoute;
    }

    public FeeDetails transportRoute(TransportRoute transportRoute) {
        this.transportRoute = transportRoute;
        return this;
    }

    public void setTransportRoute(TransportRoute transportRoute) {
        this.transportRoute = transportRoute;
    }

    public College getCollege() {
        return college;
    }

    public FeeDetails college(College college) {
        this.college = college;
        return this;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public Department getDepartment() {
        return department;
    }

    public FeeDetails department(Department department) {
        this.department = department;
        return this;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Branch getBranch() {
        return branch;
    }

    public FeeDetails branch(Branch branch) {
        this.branch = branch;
        return this;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public FeeDetails academicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
        return this;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
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
        FeeDetails feeDetails = (FeeDetails) o;
        if (feeDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), feeDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FeeDetails{" +
            "id=" + getId() +
            ", feeParticularsName='" + getFeeParticularsName() + "'" +
            ", feeParticularDesc='" + getFeeParticularDesc() + "'" +
            ", studentType='" + getStudentType() + "'" +
            ", gender='" + getGender() + "'" +
            ", amount=" + getAmount() +
            "}";
    }
}
