package com.synectiks.cms.graphql.types.Teacher;

import com.synectiks.cms.domain.Teacher;

public class AbstractTeacherPayload {
   private final Teacher teacher;


    public AbstractTeacherPayload(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }
}
