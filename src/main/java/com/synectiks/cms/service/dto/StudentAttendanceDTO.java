package com.synectiks.cms.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.synectiks.cms.domain.enumeration.AttendanceStatusEnum;

/**
 * A DTO for the StudentAttendance entity.
 */
public class StudentAttendanceDTO implements Serializable {

    private Long id;

    @NotNull
    private AttendanceStatusEnum attendanceStatus;

    @NotNull
    private String comments;

    @NotNull
    private LocalDate attendanceDate;

    private Long studentId;

    private Long lectureId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AttendanceStatusEnum getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(AttendanceStatusEnum attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getLectureId() {
        return lectureId;
    }

    public void setLectureId(Long lectureId) {
        this.lectureId = lectureId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StudentAttendanceDTO studentAttendanceDTO = (StudentAttendanceDTO) o;
        if (studentAttendanceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), studentAttendanceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StudentAttendanceDTO{" +
            "id=" + getId() +
            ", attendanceStatus='" + getAttendanceStatus() + "'" +
            ", comments='" + getComments() + "'" +
            ", attendanceDate='" + getAttendanceDate() + "'" +
            ", student=" + getStudentId() +
            ", lecture=" + getLectureId() +
            "}";
    }
}
