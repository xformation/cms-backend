package com.synectiks.cms.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.synectiks.cms.domain.enumeration.SectionEnum;

import com.synectiks.cms.domain.enumeration.LectureAdminEnum;

/**
 * A AdminOverview.
 */
@Entity
@Table(name = "admin_overview")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "adminoverview")
public class AdminOverview implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "choose_date", nullable = false)
    private Date chooseDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "section", nullable = false)
    private SectionEnum section;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "lecture_one", nullable = false)
    private LectureAdminEnum lectureOne;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "lecture_two", nullable = false)
    private LectureAdminEnum lectureTwo;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "lecture_three", nullable = false)
    private LectureAdminEnum lectureThree;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "lecture_four", nullable = false)
    private LectureAdminEnum lectureFour;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "lecture_five", nullable = false)
    private LectureAdminEnum lectureFive;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "lecture_six", nullable = false)
    private LectureAdminEnum lectureSix;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "lecture_seven", nullable = false)
    private LectureAdminEnum lectureSeven;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "lecture_eight", nullable = false)
    private LectureAdminEnum lectureEight;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Department department;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Batch batch;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getChooseDate() {
        return chooseDate;
    }

    public AdminOverview chooseDate(Date chooseDate) {
        this.chooseDate = chooseDate;
        return this;
    }

    public void setChooseDate(Date chooseDate) {
        this.chooseDate = chooseDate;
    }

    public SectionEnum getSection() {
        return section;
    }

    public AdminOverview section(SectionEnum section) {
        this.section = section;
        return this;
    }

    public void setSection(SectionEnum section) {
        this.section = section;
    }

    public LectureAdminEnum getLectureOne() {
        return lectureOne;
    }

    public AdminOverview lectureOne(LectureAdminEnum lectureOne) {
        this.lectureOne = lectureOne;
        return this;
    }

    public void setLectureOne(LectureAdminEnum lectureOne) {
        this.lectureOne = lectureOne;
    }

    public LectureAdminEnum getLectureTwo() {
        return lectureTwo;
    }

    public AdminOverview lectureTwo(LectureAdminEnum lectureTwo) {
        this.lectureTwo = lectureTwo;
        return this;
    }

    public void setLectureTwo(LectureAdminEnum lectureTwo) {
        this.lectureTwo = lectureTwo;
    }

    public LectureAdminEnum getLectureThree() {
        return lectureThree;
    }

    public AdminOverview lectureThree(LectureAdminEnum lectureThree) {
        this.lectureThree = lectureThree;
        return this;
    }

    public void setLectureThree(LectureAdminEnum lectureThree) {
        this.lectureThree = lectureThree;
    }

    public LectureAdminEnum getLectureFour() {
        return lectureFour;
    }

    public AdminOverview lectureFour(LectureAdminEnum lectureFour) {
        this.lectureFour = lectureFour;
        return this;
    }

    public void setLectureFour(LectureAdminEnum lectureFour) {
        this.lectureFour = lectureFour;
    }

    public LectureAdminEnum getLectureFive() {
        return lectureFive;
    }

    public AdminOverview lectureFive(LectureAdminEnum lectureFive) {
        this.lectureFive = lectureFive;
        return this;
    }

    public void setLectureFive(LectureAdminEnum lectureFive) {
        this.lectureFive = lectureFive;
    }

    public LectureAdminEnum getLectureSix() {
        return lectureSix;
    }

    public AdminOverview lectureSix(LectureAdminEnum lectureSix) {
        this.lectureSix = lectureSix;
        return this;
    }

    public void setLectureSix(LectureAdminEnum lectureSix) {
        this.lectureSix = lectureSix;
    }

    public LectureAdminEnum getLectureSeven() {
        return lectureSeven;
    }

    public AdminOverview lectureSeven(LectureAdminEnum lectureSeven) {
        this.lectureSeven = lectureSeven;
        return this;
    }

    public void setLectureSeven(LectureAdminEnum lectureSeven) {
        this.lectureSeven = lectureSeven;
    }

    public LectureAdminEnum getLectureEight() {
        return lectureEight;
    }

    public AdminOverview lectureEight(LectureAdminEnum lectureEight) {
        this.lectureEight = lectureEight;
        return this;
    }

    public void setLectureEight(LectureAdminEnum lectureEight) {
        this.lectureEight = lectureEight;
    }

    public Department getDepartment() {
        return department;
    }

    public AdminOverview department(Department department) {
        this.department = department;
        return this;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Batch getBatch() {
        return batch;
    }

    public AdminOverview batch(Batch batch) {
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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AdminOverview adminOverview = (AdminOverview) o;
        if (adminOverview.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adminOverview.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdminOverview{" +
            "id=" + getId() +
            ", chooseDate='" + getChooseDate() + "'" +
            ", section='" + getSection() + "'" +
            ", lectureOne='" + getLectureOne() + "'" +
            ", lectureTwo='" + getLectureTwo() + "'" +
            ", lectureThree='" + getLectureThree() + "'" +
            ", lectureFour='" + getLectureFour() + "'" +
            ", lectureFive='" + getLectureFive() + "'" +
            ", lectureSix='" + getLectureSix() + "'" +
            ", lectureSeven='" + getLectureSeven() + "'" +
            ", lectureEight='" + getLectureEight() + "'" +
            "}";
    }
}
