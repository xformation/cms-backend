package com.synectiks.cms.graphql.types.Facility;

import java.util.Objects;

public class AddFacilityInput extends AbstractFacilityInput {
    private Long academicyearId;
    private Long branchId;
    private Long studentId;

    public Long getAcademicyearId() {
        return academicyearId;
    }

    public void setAcademicyearId(Long academicyearId) {
        this.academicyearId = academicyearId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddFacilityInput)) return false;
        if (!super.equals(o)) return false;
        AddFacilityInput that = (AddFacilityInput) o;
        return Objects.equals(getAcademicyearId(), that.getAcademicyearId()) &&
            Objects.equals(getBranchId(), that.getBranchId()) &&
            Objects.equals(getStudentId(), that.getStudentId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAcademicyearId(), getBranchId(), getStudentId());
    }

    @Override
    public String toString() {
        return "AddFacilityInput{" +
            "academicyearId=" + academicyearId +
            ", branchId=" + branchId +
            ", studentId=" + studentId +
            '}';
    }
}
