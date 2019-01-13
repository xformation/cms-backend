package com.synectiks.cms.graphql.types.Section;

public class UpdateSectionInput extends AbstractSectionInput {
	 private Long batchId;

	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}
}

