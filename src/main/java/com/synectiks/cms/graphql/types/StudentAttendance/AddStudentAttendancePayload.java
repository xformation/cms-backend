package com.synectiks.cms.graphql.types.StudentAttendance;

import com.synectiks.commons.entities.cms.StudentAttendance;

public class AddStudentAttendancePayload extends AbstractStudentAttendancePayload{
    public AddStudentAttendancePayload(StudentAttendance studentAttendance) {
        super(studentAttendance);
    }
}
