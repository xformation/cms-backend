package com.synectiks.cms.graphql.types.AcademicExamSetting;

import com.synectiks.cms.domain.enumeration.GradeType;
import com.synectiks.cms.domain.enumeration.GradesEnum;
import com.synectiks.cms.domain.enumeration.SemesterEnum;

import java.util.Date;
import java.util.Objects;

public class AbstractAcademicExamSettingInput {
    private Long id;
    private String examType;
    private SemesterEnum semester;
    private Date examDate;
    private String day;
    private String duration;
    private String startTime;
    private String endTime;
    private GradeType gradeType;
    private Integer total;
    private Integer passing;
    private String actions;
    private Date startDate;
    private Date endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAcademicExamSettingInput that = (AbstractAcademicExamSettingInput) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(examType, that.examType) &&
            semester == that.semester &&
            Objects.equals(examDate, that.examDate) &&
            Objects.equals(day, that.day) &&
            Objects.equals(duration, that.duration) &&
            Objects.equals(startTime, that.startTime) &&
            Objects.equals(endTime, that.endTime) &&
            gradeType == that.gradeType &&
            Objects.equals(total, that.total) &&
            Objects.equals(passing, that.passing) &&
            Objects.equals(actions, that.actions) &&
            Objects.equals(startDate, that.startDate) &&
            Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, examType, semester, examDate, day, duration, startTime, endTime, gradeType, total, passing, actions, startDate, endDate);
    }

    @Override
    public String toString() {
        return "AbstractAcademicExamSettingInput{" +
            "id=" + id +
            ", examType='" + examType + '\'' +
            ", semester=" + semester +
            ", examDate=" + examDate +
            ", day='" + day + '\'' +
            ", duration='" + duration + '\'' +
            ", startTime='" + startTime + '\'' +
            ", endTime='" + endTime + '\'' +
            ", gradeType=" + gradeType +
            ", total=" + total +
            ", passing=" + passing +
            ", actions='" + actions + '\'' +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            '}';
    }
}

