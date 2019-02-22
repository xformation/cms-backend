package com.synectiks.cms.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A FeeCategory.
 */
@Entity
@Table(name = "fee_category")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "feecategory")
public class FeeCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public FeeCategory categoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public FeeCategory description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
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
        FeeCategory feeCategory = (FeeCategory) o;
        if (feeCategory.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), feeCategory.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FeeCategory{" +
            "id=" + getId() +
            ", categoryName='" + getCategoryName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
