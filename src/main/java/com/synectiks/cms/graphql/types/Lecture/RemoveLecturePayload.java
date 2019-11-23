package com.synectiks.cms.graphql.types.Lecture;

import java.util.List;

import com.synectiks.commons.entities.cms.Lecture;

public class RemoveLecturePayload {
    private final List<Lecture> lecture;

    public RemoveLecturePayload(List<Lecture> lecture) {
        this.lecture = lecture;
    }

	public List<Lecture> getLecture() {
		return lecture;
	}

    
}
