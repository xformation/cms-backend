package com.synectiks.cms.graphql.types.StudentYear;

import com.synectiks.cms.domain.StudentYear;

public class AbstractStudentYearPayload {
    private final StudentYear studentYear;

    public AbstractStudentYearPayload(StudentYear studentYear) {
        this.studentYear = studentYear;
    }

    public StudentYear getStudentYear() {
        return studentYear;
    }
}
