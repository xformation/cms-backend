package com.synectiks.cms.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;

import com.synectiks.cms.entities.enumeration.LectureAdminEnum;
import com.synectiks.cms.entities.enumeration.SectionEnum;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AdminOverview entity.
 */
public class AdminOverviewDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate chooseDate;

    @NotNull
    private SectionEnum section;

    @NotNull
    private LectureAdminEnum lectureOne;

    @NotNull
    private LectureAdminEnum lectureTwo;

    @NotNull
    private LectureAdminEnum lectureThree;

    @NotNull
    private LectureAdminEnum lectureFour;

    @NotNull
    private LectureAdminEnum lectureFive;

    @NotNull
    private LectureAdminEnum lectureSix;

    @NotNull
    private LectureAdminEnum lectureSeven;

    @NotNull
    private LectureAdminEnum lectureEight;

    private Long departmentId;

    private Long batchId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getChooseDate() {
        return chooseDate;
    }

    public void setChooseDate(LocalDate chooseDate) {
        this.chooseDate = chooseDate;
    }

    public SectionEnum getSection() {
        return section;
    }

    public void setSection(SectionEnum section) {
        this.section = section;
    }

    public LectureAdminEnum getLectureOne() {
        return lectureOne;
    }

    public void setLectureOne(LectureAdminEnum lectureOne) {
        this.lectureOne = lectureOne;
    }

    public LectureAdminEnum getLectureTwo() {
        return lectureTwo;
    }

    public void setLectureTwo(LectureAdminEnum lectureTwo) {
        this.lectureTwo = lectureTwo;
    }

    public LectureAdminEnum getLectureThree() {
        return lectureThree;
    }

    public void setLectureThree(LectureAdminEnum lectureThree) {
        this.lectureThree = lectureThree;
    }

    public LectureAdminEnum getLectureFour() {
        return lectureFour;
    }

    public void setLectureFour(LectureAdminEnum lectureFour) {
        this.lectureFour = lectureFour;
    }

    public LectureAdminEnum getLectureFive() {
        return lectureFive;
    }

    public void setLectureFive(LectureAdminEnum lectureFive) {
        this.lectureFive = lectureFive;
    }

    public LectureAdminEnum getLectureSix() {
        return lectureSix;
    }

    public void setLectureSix(LectureAdminEnum lectureSix) {
        this.lectureSix = lectureSix;
    }

    public LectureAdminEnum getLectureSeven() {
        return lectureSeven;
    }

    public void setLectureSeven(LectureAdminEnum lectureSeven) {
        this.lectureSeven = lectureSeven;
    }

    public LectureAdminEnum getLectureEight() {
        return lectureEight;
    }

    public void setLectureEight(LectureAdminEnum lectureEight) {
        this.lectureEight = lectureEight;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdminOverviewDTO adminOverviewDTO = (AdminOverviewDTO) o;
        if (adminOverviewDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adminOverviewDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdminOverviewDTO{" +
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
            ", department=" + getDepartmentId() +
            ", batch=" + getBatchId() +
            "}";
    }
}
