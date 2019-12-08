package com.synectiks.cms.graphql.types.StudentAttendance;

import java.util.List;

import com.synectiks.cms.entities.StudentAttendance;

public class RemoveStudentAttendancePayload {
    private final List<StudentAttendance> studentAttendances;

    public RemoveStudentAttendancePayload(List<StudentAttendance> studentAttendances) {
        this.studentAttendances = studentAttendances;
    }

    public List<StudentAttendance> getStudentAttendances() {
        return studentAttendances;
    }
}
