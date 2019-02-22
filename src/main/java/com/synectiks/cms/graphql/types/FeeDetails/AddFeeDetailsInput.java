package com.synectiks.cms.graphql.types.FeeDetails;

import java.util.Objects;

public class AddFeeDetailsInput extends AbstractFeeDetailsInput{
    private Long departmentId;
    private Long academicyearId;
    private Long batchId;
    private Long collegeId;
    private Long branchId;
    private Long feeCategoryId;
    private Long facilityId;
    private Long transportRouteId;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getAcademicyearId() {
        return academicyearId;
    }

    public void setAcademicyearId(Long academicyearId) {
        this.academicyearId = academicyearId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getFeeCategoryId() {
        return feeCategoryId;
    }

    public void setFeeCategoryId(Long feeCategoryId) {
        this.feeCategoryId = feeCategoryId;
    }

    public Long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }

    public Long getTransportRouteId() {
        return transportRouteId;
    }

    public void setTransportRouteId(Long transportRouteId) {
        this.transportRouteId = transportRouteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddFeeDetailsInput)) return false;
        if (!super.equals(o)) return false;
        AddFeeDetailsInput that = (AddFeeDetailsInput) o;
        return Objects.equals(getDepartmentId(), that.getDepartmentId()) &&
            Objects.equals(getAcademicyearId(), that.getAcademicyearId()) &&
            Objects.equals(getBatchId(), that.getBatchId()) &&
            Objects.equals(getCollegeId(), that.getCollegeId()) &&
            Objects.equals(getBranchId(), that.getBranchId()) &&
            Objects.equals(getFeeCategoryId(), that.getFeeCategoryId()) &&
            Objects.equals(getFacilityId(), that.getFacilityId()) &&
            Objects.equals(getTransportRouteId(), that.getTransportRouteId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDepartmentId(), getAcademicyearId(), getBatchId(), getCollegeId(), getBranchId(), getFeeCategoryId(), getFacilityId(), getTransportRouteId());
    }

    @Override
    public String toString() {
        return "AddFeeDetailsInput{" +
            "departmentId=" + departmentId +
            ", academicyearId=" + academicyearId +
            ", batchId=" + batchId +
            ", collegeId=" + collegeId +
            ", branchId=" + branchId +
            ", feeCategoryId=" + feeCategoryId +
            ", facilityId=" + facilityId +
            ", transportRouteId=" + transportRouteId +
            '}';
    }
}
