package com.synectiks.cms.graphql.types.StudentExamReport;

import com.synectiks.cms.domain.StudentExamReport;

public class AbstractStudentExamReportPayload {
    private final StudentExamReport studentExamReport;

    public AbstractStudentExamReportPayload(StudentExamReport studentExamReport) {
        this.studentExamReport = studentExamReport;
    }

    public StudentExamReport getStudentExamReport() {
        return studentExamReport;
    }
}
