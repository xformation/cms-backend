package com.synectiks.cms.filter.exam;

import com.synectiks.cms.domain.AcademicExamSetting;
import com.synectiks.cms.domain.Student;

public class DailyExamVo {
	
	private String subjectId;
	private String subjectDesc;
    private String examDate;
    private String day;
    private String startTime;
    private String endTime;
    private String total;
    private String passing;
    private AcademicExamSetting academicExamSetting;


    public String getSubjectId() {return subjectId;}

    public void setSubjectId(String subjectId) {this.subjectId = subjectId;}

    public String getSubjectDesc() {return subjectDesc;}

    public void setSubjectDesc(String subjectDesc) {this.subjectDesc = subjectDesc;}

    public String getExamDate() {return examDate;}

    public void setExamDate(String examDate) {this.examDate = examDate;}

    public String getDay() {return day;}

    public void setDay(String day) {this.day = day;}

    public String getStartTime() {return startTime;}

    public void setStartTime(String startTime) {this.startTime = startTime;}

    public String getEndTime() {return endTime;}

    public void setEndTime(String endTime) { this.endTime = endTime; }

    public String getTotal() {return total; }

    public void setTotal(String total) {this.total = total;}

    public String getPassing() { return passing;}

    public void setPassing(String passing) {this.passing = passing;}

    public AcademicExamSetting getAcademicExamSetting() {return academicExamSetting;}

    public void setAcademicExamSetting(AcademicExamSetting academicExamSetting) {this.academicExamSetting = academicExamSetting; }


    @Override
    public String toString() {
        return "DailyExamVo{" +"subjectId='" + subjectId + '\'' +", subjectDesc='" + subjectDesc + '\'' +
            ", examDate='" + examDate + '\'' +", day='" + day + '\'' +", startTime='" + startTime + '\'' +
            ", endTime='" + endTime + '\'' +", total='" + total + '\'' +", passing='" + passing + '\'' +
            ", academicExamSetting=" + academicExamSetting +'}';
    }
}
