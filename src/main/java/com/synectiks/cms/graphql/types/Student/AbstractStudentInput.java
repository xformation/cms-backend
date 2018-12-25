package com.synectiks.cms.graphql.types.Student;

import com.synectiks.cms.domain.Teacher;
import com.synectiks.cms.domain.enumeration.Elective;

import java.util.Objects;

public class AbstractStudentInput {
    private Long id;
    private String sName;
    private Boolean attendance;
    private Elective electiveSub;
    private Teacher teacher;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractStudentInput)) return false;
        AbstractStudentInput that = (AbstractStudentInput) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getsName(), that.getsName()) &&
            Objects.equals(getAttendance(), that.getAttendance()) &&
            getElectiveSub() == that.getElectiveSub() &&
            Objects.equals(getTeacher(), that.getTeacher());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getsName(), getAttendance(), getElectiveSub(), getTeacher());
    }

    @Override
    public String toString() {
        return "AbstractStudentInput{" +
            "id=" + id +
            ", sName='" + sName + '\'' +
            ", attendance=" + attendance +
            ", electiveSub=" + electiveSub +
            ", teacher=" + teacher +
            '}';
    }
}
