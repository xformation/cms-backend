package com.synectiks.cms.graphql.types.Teacher;

import com.synectiks.cms.domain.Teacher;

import java.util.List;

public class RemoveTeacherPayload {
    private final List<Teacher> teachers;

    public RemoveTeacherPayload(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }
}
