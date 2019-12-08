package com.synectiks.cms.graphql.types.Lecture;

import com.synectiks.cms.entities.Lecture;

public class AddLecturePayload extends AbstractLecturePayload {
    public AddLecturePayload(Lecture lecture) {
        super(lecture);
    }
}
