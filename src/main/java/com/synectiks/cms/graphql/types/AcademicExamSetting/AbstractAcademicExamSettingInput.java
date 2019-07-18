package com.synectiks.cms.graphql.types.AcademicExamSetting;

import com.synectiks.cms.domain.enumeration.GradeType;
import com.synectiks.cms.domain.enumeration.SemesterEnum;

import java.util.Date;
import java.util.Objects;

public class AbstractAcademicExamSettingInput {
    private Long id;
    private String examName;
    private SemesterEnum semester;
    private Date examDate;
    private String startTime;
    private String endTime;
    private GradeType gradeType;
    private Integer total;
    private Integer passing;
    private String actions;
    private Long groupvalue;
    private Long countvalue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SemesterEnum getSemester() {
        return semester;
    }

    public void setSemester(SemesterEnum semester) {
        this.semester = semester;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public GradeType getGradeType() {
        return gradeType;
    }

    public void setGradeType(GradeType gradeType) {
        this.gradeType = gradeType;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPassing() {
        return passing;
    }

    public void setPassing(Integer passing) {
        this.passing = passing;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Long getGroupvalue() {
        return groupvalue;
    }

    public void setGroupvalue(Long groupvalue) {
        this.groupvalue = groupvalue;
    }

    public Long getCountvalue() {
        return countvalue;
    }

    public void setCountvalue(Long countvalue) {
        this.countvalue = countvalue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAcademicExamSettingInput that = (AbstractAcademicExamSettingInput) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(examName, that.examName) &&
            semester == that.semester &&
            Objects.equals(examDate, that.examDate) &&
            Objects.equals(startTime, that.startTime) &&
            Objects.equals(endTime, that.endTime) &&
            gradeType == that.gradeType &&
            Objects.equals(total, that.total) &&
            Objects.equals(passing, that.passing) &&
            Objects.equals(actions, that.actions) &&
            Objects.equals(groupvalue, that.groupvalue) &&
            Objects.equals(countvalue, that.countvalue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, examName, semester, examDate, startTime, endTime, gradeType, total, passing, actions, groupvalue, countvalue);
    }

    @Override
    public String toString() {
        return "AbstractAcademicExamSettingInput{" +
            "id=" + id +
            ", examName='" + examName + '\'' +
            ", semester=" + semester +
            ", examDate=" + examDate +
            ", startTime='" + startTime + '\'' +
            ", endTime='" + endTime + '\'' +
            ", gradeType=" + gradeType +
            ", total=" + total +
            ", passing=" + passing +
            ", actions='" + actions + '\'' +
            ", groupvalue=" + groupvalue +
            ", countvalue=" + countvalue +
            '}';
    }
}

