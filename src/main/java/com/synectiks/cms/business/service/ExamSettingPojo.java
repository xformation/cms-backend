package com.synectiks.cms.business.service;


import java.io.Serializable;
import java.util.Date;
import java.util.*;

public  class ExamSettingPojo implements Serializable{


    private String examType;
    private Long departmentId;
    private String sectionId;
    private String action;
    private String subject;
    private Date examDate;
    private Date startDate;
    private Date endDate;

    public ExamSettingPojo() { }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
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
        ExamSettingPojo that = (ExamSettingPojo) o;
        return examType.equals(that.examType) &&
            departmentId.equals(that.departmentId);
    }


    @Override
    public int hashCode() {
        return Objects.hash(examType, departmentId);
    }

    Comparator<ExamSettingPojo> comparator = Comparator.comparing( ExamSettingPojo::getStartDate);

    public ExamSettingPojo(String examType, Long departmentId, String sectionId, String action, String subject, Date examDate, Date startDate, Date endDate) {
        this.examType = examType;
        this.departmentId = departmentId;

        this.sectionId = sectionId;
        this.action = action;
        this.subject = subject;
        this.examDate = examDate;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    Comparator<ExamSettingPojo> cmp = Comparator.comparing(ExamSettingPojo::getExamDate);


    public ExamSettingPojo merge(ExamSettingPojo other) {
        assert (this.equals(other));


        return new ExamSettingPojo(
            this.examType,
            this.departmentId,
            this.sectionId=this.sectionId+","+other.sectionId ,
            this.action,
            this.subject + "," + other.subject,
            this.examDate,
            this.endDate= new Date(Long.MIN_VALUE),
            this.startDate=new Date(Long.MIN_VALUE)

        );
    }
}
