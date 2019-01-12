package com.synectiks.cms.graphql.types.CourseOffer;

import com.synectiks.cms.domain.CourseOffer;

public class RemoveCourseOfferInput {
    private Long courseOfferId;

	public Long getCourseOfferId() {
		return courseOfferId;
	}

	public void setCourseOfferId(Long courseOfferId) {
		this.courseOfferId = courseOfferId;
	}

    
}
