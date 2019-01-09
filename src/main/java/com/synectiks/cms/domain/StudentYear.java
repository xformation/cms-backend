package com.synectiks.cms.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

import com.synectiks.cms.domain.enumeration.SYear;

/**
 * A StudentYear.
 */
@Entity
@Table(name = "student_year")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "studentyear")
public class StudentYear implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "year_desc", nullable = false)
    private SYear yearDesc;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SYear getYearDesc() {
        return yearDesc;
    }

    public StudentYear yearDesc(SYear yearDesc) {
        this.yearDesc = yearDesc;
        return this;
    }

    public void setYearDesc(SYear yearDesc) {
        this.yearDesc = yearDesc;
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
        StudentYear studentYear = (StudentYear) o;
        if (studentYear.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), studentYear.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StudentYear{" +
            "id=" + getId() +
            ", yearDesc='" + getYearDesc() + "'" +
            "}";
    }
}
