package com.synectiks.cms.graphql.types.StudentExamReport;

import com.synectiks.commons.entities.cms.StudentExamReport;

import java.util.List;

public class RemoveStudentExamReportPayload {
    private final List<StudentExamReport> studentExamReports;

    public RemoveStudentExamReportPayload(List<StudentExamReport> studentExamReports)
    {
        this.studentExamReports = studentExamReports;
    }
    public List<StudentExamReport> getStudentExamReports(){
        return studentExamReports;
    }
}
