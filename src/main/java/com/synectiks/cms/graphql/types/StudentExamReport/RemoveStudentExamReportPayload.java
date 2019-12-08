package com.synectiks.cms.graphql.types.StudentExamReport;

import java.util.List;

import com.synectiks.cms.entities.StudentExamReport;

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
