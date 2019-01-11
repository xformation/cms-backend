package com.synectiks.cms.graphql.types.Branch;

public class UpdateBranchInput extends AbstractBranchInput{
	private Long collegeId;

	public Long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}
}
