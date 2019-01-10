package com.synectiks.cms.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.synectiks.cms.domain.enumeration.LecStatusEnum;

/**
 * A DTO for the Lecture entity.
 */
public class LectureDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate lecDate;

    @NotNull
    private LocalDate lastUpdatedOn;

    @NotNull
    private String lastUpdatedBy;

    @NotNull
    private LecStatusEnum lecStatus;

    private String desc;

    private Long attendancemasterId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLecDate() {
        return lecDate;
    }

    public void setLecDate(LocalDate lecDate) {
        this.lecDate = lecDate;
    }

    public LocalDate getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(LocalDate lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public LecStatusEnum getLecStatus() {
        return lecStatus;
    }

    public void setLecStatus(LecStatusEnum lecStatus) {
        this.lecStatus = lecStatus;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getAttendancemasterId() {
        return attendancemasterId;
    }

    public void setAttendancemasterId(Long attendanceMasterId) {
        this.attendancemasterId = attendanceMasterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LectureDTO lectureDTO = (LectureDTO) o;
        if (lectureDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), lectureDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LectureDTO{" +
            "id=" + getId() +
            ", lecDate='" + getLecDate() + "'" +
            ", lastUpdatedOn='" + getLastUpdatedOn() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lecStatus='" + getLecStatus() + "'" +
            ", desc='" + getDesc() + "'" +
            ", attendancemaster=" + getAttendancemasterId() +
            "}";
    }
}
