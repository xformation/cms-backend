package com.synectiks.cms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

import com.synectiks.cms.domain.enumeration.ClassPeriods;

/**
 * A Periods.
 */
@Entity
@Table(name = "periods")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "periods")
public class Periods implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "periods", nullable = false)
    private ClassPeriods periods;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Section section;

    @OneToOne(mappedBy = "periods")
    @JsonIgnore
    private Teacher teacher;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClassPeriods getPeriods() {
        return periods;
    }

    public Periods periods(ClassPeriods periods) {
        this.periods = periods;
        return this;
    }

    public void setPeriods(ClassPeriods periods) {
        this.periods = periods;
    }

    public Section getSection() {
        return section;
    }

    public Periods section(Section section) {
        this.section = section;
        return this;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Periods teacher(Teacher teacher) {
        this.teacher = teacher;
        return this;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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
        Periods periods = (Periods) o;
        if (periods.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), periods.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Periods{" +
            "id=" + getId() +
            ", periods='" + getPeriods() + "'" +
            "}";
    }
}
