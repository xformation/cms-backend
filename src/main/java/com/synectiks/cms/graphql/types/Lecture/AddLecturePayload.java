package com.synectiks.cms.graphql.types.Lecture;

import com.synectiks.commons.entities.cms.Lecture;

public class AddLecturePayload extends AbstractLecturePayload {
    public AddLecturePayload(Lecture lecture) {
        super(lecture);
    }
}
