package com.synectiks.cms.graphql.types.AdminAttendance;

import java.util.Date;
import java.util.Objects;

public class AbstractAdminAttendanceInput {
    private Long id;
    private Date updatedOn;
    private String updatedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAdminAttendanceInput that = (AbstractAdminAttendanceInput) o;
        return id.equals(that.id) &&
            updatedOn.equals(that.updatedOn) &&
            updatedBy.equals(that.updatedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, updatedOn, updatedBy);
    }

    @Override
    public String toString() {
        return "AbstractAdminAttendanceInput{" +
            "id=" + id +
            ", updatedOn=" + updatedOn +
            ", updatedBy='" + updatedBy + '\'' +
            '}';
    }
}
