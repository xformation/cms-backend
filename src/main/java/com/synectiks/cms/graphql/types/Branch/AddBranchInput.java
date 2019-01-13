package com.synectiks.cms.graphql.types.Branch;

public class AddBranchInput extends AbstractBranchInput {
    private Long collegeId;

	public Long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}

    @Override
    public String toString() {
        return "AddBranchInput{" +
            "collegeId=" + collegeId +
            '}' + super.toString();
    }
}
