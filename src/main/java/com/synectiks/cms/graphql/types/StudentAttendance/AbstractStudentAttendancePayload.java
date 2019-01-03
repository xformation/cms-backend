package com.synectiks.cms.graphql.types.StudentAttendance;

import com.synectiks.cms.domain.StudentAttendance;

public class AbstractStudentAttendancePayload {
    private final StudentAttendance studentAttendance;

    public AbstractStudentAttendancePayload(StudentAttendance studentAttendance) {
        this.studentAttendance = studentAttendance;
    }

    public StudentAttendance getStudentAttendance() {
        return studentAttendance;
    }
}
