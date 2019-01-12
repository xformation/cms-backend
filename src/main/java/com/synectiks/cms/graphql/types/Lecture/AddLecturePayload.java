package com.synectiks.cms.graphql.types.Lecture;

import com.synectiks.cms.domain.Lecture;

public class AddLecturePayload extends AbstractLecturePayload {
    public AddLecturePayload(Lecture lecture) {
        super(lecture);
    }
}
