package com.synectiks.cms.graphql.types.CourseOffer;

import java.util.List;

import com.synectiks.cms.domain.CourseOffer;

public class RemoveCourseOfferPayload {
    private final List<CourseOffer> courseOffer;

    public RemoveCourseOfferPayload(List<CourseOffer> courseOffer) {
        this.courseOffer = courseOffer;
    }

	public List<CourseOffer> getCourseOffer() {
		return courseOffer;
	}
    
}
