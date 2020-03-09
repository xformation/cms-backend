package com.synectiks.cms.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.synectiks.cms.domain.enumeration.TypeOfOwnerShip;

/**
 * A Contract.
 */
@Entity
@Table(name = "contract")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "contract")
public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "vendor_name")
    private String vendorName;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_owner_ship")
    private TypeOfOwnerShip typeOfOwnerShip;

    @Column(name = "duration_of_contract")
    private String durationOfContract;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVendorName() {
        return vendorName;
    }

    public Contract vendorName(String vendorName) {
        this.vendorName = vendorName;
        return this;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public TypeOfOwnerShip getTypeOfOwnerShip() {
        return typeOfOwnerShip;
    }

    public Contract typeOfOwnerShip(TypeOfOwnerShip typeOfOwnerShip) {
        this.typeOfOwnerShip = typeOfOwnerShip;
        return this;
    }

    public void setTypeOfOwnerShip(TypeOfOwnerShip typeOfOwnerShip) {
        this.typeOfOwnerShip = typeOfOwnerShip;
    }

    public String getDurationOfContract() {
        return durationOfContract;
    }

    public Contract durationOfContract(String durationOfContract) {
        this.durationOfContract = durationOfContract;
        return this;
    }

    public void setDurationOfContract(String durationOfContract) {
        this.durationOfContract = durationOfContract;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Contract startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Contract endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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
        Contract contract = (Contract) o;
        if (contract.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contract.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Contract{" +
            "id=" + getId() +
            ", vendorName='" + getVendorName() + "'" +
            ", typeOfOwnerShip='" + getTypeOfOwnerShip() + "'" +
            ", durationOfContract='" + getDurationOfContract() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            "}";
    }
}
