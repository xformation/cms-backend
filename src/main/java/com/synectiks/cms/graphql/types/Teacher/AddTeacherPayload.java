package com.synectiks.cms.graphql.types.Teacher;

import com.synectiks.cms.domain.Teacher;

public class AddTeacherPayload extends AbstractTeacherPayload{

    public AddTeacherPayload(Teacher teacher) {
        super(teacher);
    }
}
