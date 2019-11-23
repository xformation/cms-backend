package com.synectiks.cms.graphql.types.StudentExamReport;

import com.synectiks.commons.entities.cms.StudentExamReport;

public class AbstractStudentExamReportPayload {
    private final StudentExamReport studentExamReport;

    public AbstractStudentExamReportPayload(StudentExamReport studentExamReport) {
        this.studentExamReport = studentExamReport;
    }

    public StudentExamReport getStudentExamReport() {
        return studentExamReport;
    }
}
