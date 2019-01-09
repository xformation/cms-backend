package com.synectiks.cms.graphql.types.Student;

import com.synectiks.cms.domain.Teacher;
import com.synectiks.cms.domain.enumeration.Elective;

import java.util.Objects;

public class AbstractStudentInput {
    private Long id;
    private String studentName;
    private Boolean attendance;
    private Elective electiveSub;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Boolean getAttendance() {
        return attendance;
    }

    public void setAttendance(Boolean attendance) {
        this.attendance = attendance;
    }

    public Elective getElectiveSub() {
        return electiveSub;
    }

    public void setElectiveSub(Elective electiveSub) {
        this.electiveSub = electiveSub;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractStudentInput that = (AbstractStudentInput) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(studentName, that.studentName) &&
            Objects.equals(attendance, that.attendance) &&
            electiveSub == that.electiveSub;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentName, attendance, electiveSub);
    }

    @Override
    public String toString() {
        return "AbstractStudentInput{" +
            "id=" + id +
            ", studentName='" + studentName + '\'' +
            ", attendance=" + attendance +
            ", electiveSub=" + electiveSub +
            '}';
    }
}
