package com.synectiks.cms.graphql.types.CourseOffer;

import com.synectiks.cms.domain.CourseOffer;

public class UpdateCourseOfferPayload extends AbstractCourseOfferPayload {
    public UpdateCourseOfferPayload(CourseOffer courseOffer) {
        super(courseOffer);
    }
}
