package com.synectiks.cms.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

import com.synectiks.cms.domain.enumeration.Status;

/**
 * A Facility.
 */
@Entity
@Table(name = "facility")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "facility")
public class Facility implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "transport")
    private Status transport;

    @Enumerated(EnumType.STRING)
    @Column(name = "mess")
    private Status mess;

    @Enumerated(EnumType.STRING)
    @Column(name = "gym")
    private Status gym;

    @Enumerated(EnumType.STRING)
    @Column(name = "cultural_class")
    private Status culturalClass;

    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_library")
    private Status library;

    @Enumerated(EnumType.STRING)
    @Column(name = "sports")
    private Status sports;

    @Enumerated(EnumType.STRING)
    @Column(name = "swimming")
    private Status swimming;

    @Enumerated(EnumType.STRING)
    @Column(name = "extra_class")
    private Status extraClass;

    @Enumerated(EnumType.STRING)
    @Column(name = "handicrafts")
    private Status handicrafts;

    @ManyToOne
    @JsonIgnoreProperties("")
    private AcademicYear academicYear;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Branch branch;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Student student;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getTransport() {
        return transport;
    }

    public Facility transport(Status transport) {
        this.transport = transport;
        return this;
    }

    public void setTransport(Status transport) {
        this.transport = transport;
    }

    public Status getMess() {
        return mess;
    }

    public Facility mess(Status mess) {
        this.mess = mess;
        return this;
    }

    public void setMess(Status mess) {
        this.mess = mess;
    }

    public Status getGym() {
        return gym;
    }

    public Facility gym(Status gym) {
        this.gym = gym;
        return this;
    }

    public void setGym(Status gym) {
        this.gym = gym;
    }

    public Status getCulturalClass() {
        return culturalClass;
    }

    public Facility culturalClass(Status culturalClass) {
        this.culturalClass = culturalClass;
        return this;
    }

    public void setCulturalClass(Status culturalClass) {
        this.culturalClass = culturalClass;
    }

    public Status getLibrary() {
        return library;
    }

    public Facility library(Status library) {
        this.library = library;
        return this;
    }

    public void setLibrary(Status library) {
        this.library = library;
    }

    public Status getSports() {
        return sports;
    }

    public Facility sports(Status sports) {
        this.sports = sports;
        return this;
    }

    public void setSports(Status sports) {
        this.sports = sports;
    }

    public Status getSwimming() {
        return swimming;
    }

    public Facility swimming(Status swimming) {
        this.swimming = swimming;
        return this;
    }

    public void setSwimming(Status swimming) {
        this.swimming = swimming;
    }

    public Status getExtraClass() {
        return extraClass;
    }

    public Facility extraClass(Status extraClass) {
        this.extraClass = extraClass;
        return this;
    }

    public void setExtraClass(Status extraClass) {
        this.extraClass = extraClass;
    }

    public Status getHandicrafts() {
        return handicrafts;
    }

    public Facility handicrafts(Status handicrafts) {
        this.handicrafts = handicrafts;
        return this;
    }

    public void setHandicrafts(Status handicrafts) {
        this.handicrafts = handicrafts;
    }

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public Facility academicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
        return this;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public Branch getBranch() {
        return branch;
    }

    public Facility branch(Branch branch) {
        this.branch = branch;
        return this;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Student getStudent() {
        return student;
    }

    public Facility student(Student student) {
        this.student = student;
        return this;
    }

    public void setStudent(Student student) {
        this.student = student;
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
        Facility facility = (Facility) o;
        if (facility.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), facility.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Facility{" +
            "id=" + getId() +
            ", transport='" + getTransport() + "'" +
            ", mess='" + getMess() + "'" +
            ", gym='" + getGym() + "'" +
            ", culturalClass='" + getCulturalClass() + "'" +
            ", library='" + getLibrary() + "'" +
            ", sports='" + getSports() + "'" +
            ", swimming='" + getSwimming() + "'" +
            ", extraClass='" + getExtraClass() + "'" +
            ", handicrafts='" + getHandicrafts() + "'" +
            "}";
    }
}
