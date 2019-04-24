package com.synectiks.cms.graphql.types.AcademicExamSetting;

import com.synectiks.cms.domain.enumeration.SemesterEnum;

import java.util.Date;
import java.util.Objects;

public class AbstractAcademicExamSettingInput {
    private Long id;
    private String examType;
    private SemesterEnum semester;
    private String subject;
    private Date date;
    private String day;
    private String duration;
    private String startTime;
    private String endTime;
    private Integer total;
    private Integer passing;
    private String actions;

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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAcademicExamSettingInput that = (AbstractAcademicExamSettingInput) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(examType, that.examType) &&
            semester == that.semester &&
            Objects.equals(subject, that.subject) &&
            Objects.equals(date, that.date) &&
            Objects.equals(day, that.day) &&
            Objects.equals(duration, that.duration) &&
            Objects.equals(startTime, that.startTime) &&
            Objects.equals(endTime, that.endTime) &&
            Objects.equals(total, that.total) &&
            Objects.equals(passing, that.passing) &&
            Objects.equals(actions, that.actions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, examType, semester, subject, date, day, duration, startTime, endTime, total, passing, actions);
    }

    @Override
    public String toString() {
        return "AbstractAcademicExamSettingInput{" +
            "id=" + id +
            ", examType='" + examType + '\'' +
            ", semester=" + semester +
            ", subject='" + subject + '\'' +
            ", date=" + date +
            ", day='" + day + '\'' +
            ", duration='" + duration + '\'' +
            ", startTime='" + startTime + '\'' +
            ", endTime='" + endTime + '\'' +
            ", total=" + total +
            ", passing=" + passing +
            ", actions='" + actions + '\'' +
            '}';
    }
}

