package com.synectiks.cms.graphql.types.Teacher;

import java.util.Objects;

public class AbstractTeacherInput {
    private Long id;
    private String teacherName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractTeacherInput that = (AbstractTeacherInput) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(teacherName, that.teacherName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teacherName);
    }

    @Override
    public String toString() {
        return "AbstractTeacherInput{" +
            "id=" + id +
            ", teacherName='" + teacherName + '\'' +
            '}';
    }
}
