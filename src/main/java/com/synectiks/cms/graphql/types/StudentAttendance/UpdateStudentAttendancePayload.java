package com.synectiks.cms.graphql.types.StudentAttendance;

import com.synectiks.cms.domain.StudentAttendance;

public class UpdateStudentAttendancePayload extends AbstractStudentAttendancePayload{
    public UpdateStudentAttendancePayload(StudentAttendance studentAttendance) {
        super(studentAttendance);
    }
}
