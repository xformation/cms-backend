package com.synectiks.cms.graphql.types.Lecture;

import com.synectiks.commons.entities.cms.Lecture;

public class AbstractLecturePayload {
    private final Lecture lecture;

    public AbstractLecturePayload(Lecture Lecture) {
        this.lecture = Lecture;
    }

	public Lecture getLecture() {
		return lecture;
	}

   
}
