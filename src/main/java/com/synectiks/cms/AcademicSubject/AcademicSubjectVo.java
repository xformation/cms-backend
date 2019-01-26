package com.synectiks.cms.AcademicSubject;

public class AcademicSubjectVo {
    private String subjectCode;
    private String subjectType;
    private String subjectDesc;
    private String status;

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public String getSubjectDesc() {
        return subjectDesc;
    }

    public void setSubjectDesc(String subjectDesc) {
        this.subjectDesc = subjectDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "AcademicSubjectVo{" +
            "subjectCode='" + subjectCode + '\'' +
            ", subjectType='" + subjectType + '\'' +
            ", subjectDesc='" + subjectDesc + '\'' +
            ", status='" + status + '\'' +
            '}';
    }
}
