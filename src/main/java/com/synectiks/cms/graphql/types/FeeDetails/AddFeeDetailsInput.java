package com.synectiks.cms.graphql.types.FeeDetails;

public class AddFeeDetailsInput extends AbstractFeeDetailsInput{
    private Long departmentId;
//    private Long academicyearId;
    private Long batchId;
//    private Long collegeId;
//    private Long branchId;
    private Long feeCategoryId;
    private Long facilityId;
    private Long transportRouteId;
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long getBatchId() {
		return batchId;
	}
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
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

    
}
