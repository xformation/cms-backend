package com.synectiks.cms.graphql.types.Lecture;

import com.synectiks.commons.entities.cms.Lecture;

public class UpdateLecturePayload extends AbstractLecturePayload {
    public UpdateLecturePayload(Lecture lecture) {
        super(lecture);
    }
}
