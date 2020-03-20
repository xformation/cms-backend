package com.synectiks.cms.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A StudentFacilityLink.
 */
@Entity
@Table(name = "student_facility_link")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//@Document(indexName = "studentfacilitylink")
public class StudentFacilityLink implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "link_desc")
    private String linkDesc;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Student student;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Facility facility;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinkDesc() {
        return linkDesc;
    }

    public StudentFacilityLink linkDesc(String linkDesc) {
        this.linkDesc = linkDesc;
        return this;
    }

    public void setLinkDesc(String linkDesc) {
        this.linkDesc = linkDesc;
    }

    public Student getStudent() {
        return student;
    }

    public StudentFacilityLink student(Student student) {
        this.student = student;
        return this;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Facility getFacility() {
        return facility;
    }

    public StudentFacilityLink facility(Facility facility) {
        this.facility = facility;
        return this;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
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
        StudentFacilityLink studentFacilityLink = (StudentFacilityLink) o;
        if (studentFacilityLink.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), studentFacilityLink.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StudentFacilityLink{" +
            "id=" + getId() +
            ", linkDesc='" + getLinkDesc() + "'" +
            "}";
    }
}
