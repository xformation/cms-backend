package com.synectiks.cms.graphql.types.StudentExamReport;

import com.synectiks.cms.entities.StudentExamReport;

public class UpdateStudentExamReportPayload extends AddStudentExamReportPayload {
    public UpdateStudentExamReportPayload(StudentExamReport studentExamReport) {
        super(studentExamReport);
    }
}
