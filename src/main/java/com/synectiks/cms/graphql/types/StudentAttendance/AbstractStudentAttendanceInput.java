package com.synectiks.cms.graphql.types.StudentAttendance;

import com.synectiks.cms.domain.enumeration.Status;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class AbstractStudentAttendanceInput {
    private Long id;
    private Date attendanceDate;
    private String sName;
    private Status status;
    private String comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractStudentAttendanceInput)) return false;
        AbstractStudentAttendanceInput that = (AbstractStudentAttendanceInput) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getAttendanceDate(), that.getAttendanceDate()) &&
            Objects.equals(getsName(), that.getsName()) &&
            getStatus() == that.getStatus() &&
            Objects.equals(getComments(), that.getComments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAttendanceDate(), getsName(), getStatus(), getComments());
    }

    @Override
    public String toString() {
        return "AbstractStudentAttendanceInput{" +
            "id=" + id +
            ", attendanceDate=" + attendanceDate +
            ", sName='" + sName + '\'' +
            ", status=" + status +
            ", comments='" + comments + '\'' +
            '}';
    }
}
