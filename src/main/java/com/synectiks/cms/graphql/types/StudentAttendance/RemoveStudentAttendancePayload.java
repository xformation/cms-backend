package com.synectiks.cms.graphql.types.StudentAttendance;

import com.synectiks.commons.entities.cms.StudentAttendance;

import java.util.List;

public class RemoveStudentAttendancePayload {
    private final List<StudentAttendance> studentAttendances;

    public RemoveStudentAttendancePayload(List<StudentAttendance> studentAttendances) {
        this.studentAttendances = studentAttendances;
    }

    public List<StudentAttendance> getStudentAttendances() {
        return studentAttendances;
    }
}
