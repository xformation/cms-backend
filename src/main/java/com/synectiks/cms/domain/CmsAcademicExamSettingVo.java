package com.synectiks.cms.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.synectiks.cms.domain.enumeration.SemesterEnum;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class CmsAcademicExamSettingVo implements Serializable {
	private static final long serialVersionUID = -2547366689482152312L;

	private Long id;
    private String examType;
    private SemesterEnum semester;
    private String subject;
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate examDate;
    private String day;
    private String duration;
    private String startTime;
    private String endTime;
    private Integer total;
    private Integer passing;
    private String actions;
    private Department department;
    private AcademicYear academicyear;
    private Section section;

    private String strexamDate;

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

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public AcademicYear getAcademicyear() {
        return academicyear;
    }

    public void setAcademicyear(AcademicYear academicyear) {
        this.academicyear = academicyear;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public String getStrexamDate() {
        return strexamDate;
    }

    public void setStrexamDate(String strexamDate) {
        this.strexamDate = strexamDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CmsAcademicExamSettingVo that = (CmsAcademicExamSettingVo) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(examType, that.examType) &&
            semester == that.semester &&
            Objects.equals(subject, that.subject) &&
            Objects.equals(examDate, that.examDate) &&
            Objects.equals(day, that.day) &&
            Objects.equals(duration, that.duration) &&
            Objects.equals(startTime, that.startTime) &&
            Objects.equals(endTime, that.endTime) &&
            Objects.equals(total, that.total) &&
            Objects.equals(passing, that.passing) &&
            Objects.equals(actions, that.actions) &&
            Objects.equals(department, that.department) &&
            Objects.equals(academicyear, that.academicyear) &&
            Objects.equals(section, that.section) &&
            Objects.equals(strexamDate, that.strexamDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, examType, semester, subject, examDate, day, duration, startTime, endTime, total, passing, actions, department, academicyear, section, strexamDate);
    }

    @Override
    public String toString() {
        return "CmsAcademicExamSettingVo{" +
            "id=" + id +
            ", examType='" + examType + '\'' +
            ", semester=" + semester +
            ", subject='" + subject + '\'' +
            ", examDate=" + examDate +
            ", day='" + day + '\'' +
            ", duration='" + duration + '\'' +
            ", startTime='" + startTime + '\'' +
            ", endTime='" + endTime + '\'' +
            ", total=" + total +
            ", passing=" + passing +
            ", actions='" + actions + '\'' +
            ", department=" + department +
            ", academicyear=" + academicyear +
            ", section=" + section +
            ", strexamDate='" + strexamDate + '\'' +
            '}';
    }
}
