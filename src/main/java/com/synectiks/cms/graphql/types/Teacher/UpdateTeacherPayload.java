package com.synectiks.cms.graphql.types.Teacher;

import com.synectiks.cms.domain.Teacher;

public class UpdateTeacherPayload extends AbstractTeacherPayload {
    public UpdateTeacherPayload(Teacher teacher) {
        super(teacher);
    }
}
