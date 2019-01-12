package com.synectiks.cms.graphql.types.CourseOffer;

import com.synectiks.cms.domain.CourseOffer;

public class AddCourseOfferPayload extends AbstractCourseOfferPayload {
    public AddCourseOfferPayload(CourseOffer courseOffer) {
        super(courseOffer);
    }
}
