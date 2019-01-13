package com.synectiks.cms.graphql.types.Section;

public class AddSectionInput extends AbstractSectionInput {

    private Long batchId;

	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	@Override
	public String toString() {
		return "AddSectionInput [batchId=" + batchId + "]";
	}

    
}
