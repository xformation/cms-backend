package com.synectiks.cms.graphql.types.AcademicDepartment;

import java.util.Objects;

public class AbstractAcademicDepartmentInput {

    private Long id;
    private String departmentName;
    private String university;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractAcademicDepartmentInput)) return false;
        AbstractAcademicDepartmentInput that = (AbstractAcademicDepartmentInput) o;
        return Objects.equals(getDepartmentName(), that.getDepartmentName()) &&
            Objects.equals(getUniversity(), that.getUniversity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDepartmentName(), getUniversity());
    }

    @Override
    public String toString() {
        return "AbstractAcademicDepartmentInput{" +
            "id=" + id +
            ", departmentName='" + departmentName + '\'' +
            ", university=" + university +
            '}';
    }
}
