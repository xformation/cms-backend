package com.synectiks.cms.graphql.types.Departments;

import java.util.Objects;

public class AbstractDepartmentsInput {
    private Long id;
    private String name;
    private String description;
    private String deptHead;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeptHead() {
        return deptHead;
    }

    public void setDeptHead(String deptHead) {
        this.deptHead = deptHead;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractDepartmentsInput)) return false;
        AbstractDepartmentsInput that = (AbstractDepartmentsInput) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getName(), that.getName()) &&
            Objects.equals(getDescription(), that.getDescription()) &&
            Objects.equals(getDeptHead(), that.getDeptHead());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getDeptHead());
    }

    @Override
    public String toString() {
        return "AbstractDepartmentsInput{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", deptHead='" + deptHead + '\'' +
            '}';
    }
}
