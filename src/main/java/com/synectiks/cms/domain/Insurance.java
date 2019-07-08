package com.synectiks.cms.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.synectiks.cms.domain.enumeration.TypeOfInsurance;

/**
 * A Insurance.
 */
@Entity
@Table(name = "insurance")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "insurance")
public class Insurance implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "insurance_company", nullable = false)
    private String insuranceCompany;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_insurance", nullable = false)
    private TypeOfInsurance typeOfInsurance;

    @NotNull
    @Column(name = "date_of_insurance", nullable = false)
    private LocalDate dateOfInsurance;

    @NotNull
    @Column(name = "valid_till", nullable = false)
    private LocalDate validTill;

    @OneToOne
    @JoinColumn(unique = true)
    private Vehicle vehicle;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public Insurance insuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
        return this;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public TypeOfInsurance getTypeOfInsurance() {
        return typeOfInsurance;
    }

    public Insurance typeOfInsurance(TypeOfInsurance typeOfInsurance) {
        this.typeOfInsurance = typeOfInsurance;
        return this;
    }

    public void setTypeOfInsurance(TypeOfInsurance typeOfInsurance) {
        this.typeOfInsurance = typeOfInsurance;
    }

    public LocalDate getDateOfInsurance() {
        return dateOfInsurance;
    }

    public Insurance dateOfInsurance(LocalDate dateOfInsurance) {
        this.dateOfInsurance = dateOfInsurance;
        return this;
    }

    public void setDateOfInsurance(LocalDate dateOfInsurance) {
        this.dateOfInsurance = dateOfInsurance;
    }

    public LocalDate getValidTill() {
        return validTill;
    }

    public Insurance validTill(LocalDate validTill) {
        this.validTill = validTill;
        return this;
    }

    public void setValidTill(LocalDate validTill) {
        this.validTill = validTill;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Insurance vehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
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
        Insurance insurance = (Insurance) o;
        if (insurance.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), insurance.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Insurance{" +
            "id=" + getId() +
            ", insuranceCompany='" + getInsuranceCompany() + "'" +
            ", typeOfInsurance='" + getTypeOfInsurance() + "'" +
            ", dateOfInsurance='" + getDateOfInsurance() + "'" +
            ", validTill='" + getValidTill() + "'" +
            "}";
    }
}
