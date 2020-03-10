package com.synectiks.cms.domain;
import com.synectiks.cms.utils.IESEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A TypeOfGrading.
 */
@Entity
@Table(name = "type_of_grading")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TypeOfGrading implements Serializable, IESEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "min_marks")
    private Integer minMarks;

    @Column(name = "max_marks")
    private Integer maxMarks;

    @Column(name = "grades")
    private String grades;

    @Column(name = "groupvalue")
    private Long groupvalue;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMinMarks() {
        return minMarks;
    }

    public TypeOfGrading minMarks(Integer minMarks) {
        this.minMarks = minMarks;
        return this;
    }

    public void setMinMarks(Integer minMarks) {
        this.minMarks = minMarks;
    }

    public Integer getMaxMarks() {
        return maxMarks;
    }

    public TypeOfGrading maxMarks(Integer maxMarks) {
        this.maxMarks = maxMarks;
        return this;
    }

    public void setMaxMarks(Integer maxMarks) {
        this.maxMarks = maxMarks;
    }

    public String getGrades() {
        return grades;
    }

    public TypeOfGrading grades(String grades) {
        this.grades = grades;
        return this;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public Long getGroupvalue() {
        return groupvalue;
    }

    public TypeOfGrading groupvalue(Long groupvalue) {
        this.groupvalue = groupvalue;
        return this;
    }

    public void setGroupvalue(Long groupvalue) {
        this.groupvalue = groupvalue;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeOfGrading)) {
            return false;
        }
        return id != null && id.equals(((TypeOfGrading) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TypeOfGrading{" +
            "id=" + getId() +
            ", minMarks=" + getMinMarks() +
            ", maxMarks=" + getMaxMarks() +
            ", grades='" + getGrades() + "'" +
            ", groupvalue=" + getGroupvalue() +
            "}";
    }
}
