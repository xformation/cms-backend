package com.synectiks.cms.graphql.types.CourseOffer;

import com.synectiks.cms.domain.CourseOffer;

public class AbstractCourseOfferPayload {
    private final CourseOffer courseOffer;

    public AbstractCourseOfferPayload(CourseOffer courseOffer) {
        this.courseOffer = courseOffer;
    }

	public CourseOffer getCourseOffer() {
		return courseOffer;
	}

	

	

    
}
