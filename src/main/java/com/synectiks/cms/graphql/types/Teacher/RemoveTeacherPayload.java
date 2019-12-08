package com.synectiks.cms.graphql.types.Teacher;

import java.util.List;

import com.synectiks.cms.entities.Teacher;

public class RemoveTeacherPayload {
    private final List<Teacher> teachers;

    public RemoveTeacherPayload(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }
}
