package com.synectiks.cms.business.service;
import com.synectiks.cms.domain.AcademicExamSetting;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.*;

public  class ExamSettingPojo implements Serializable{


    private String examType;
    private Long departmentId;
    private String sectionId;
    private String action;
    private String subject;
    private Date startDate;
    private Date endDate;
    private Date examDate;

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


    public ExamSettingPojo(String examType, Long departmentId, String sectionId, String action, String subject, Date examDate, Date startDate, Date endDate) {
        this.examType = examType;
        this.departmentId = departmentId;
        this.examDate= examDate;
        this.sectionId = sectionId;
        this.action = action;
        this.subject = subject;
        this.startDate = startDate;
        this.endDate = endDate;
    }



    public ExamSettingPojo merge(ExamSettingPojo other) {
        assert (this.equals(other));

        String str =this.subject+","+other.subject;
        String[] strWords = str.split("\\,+");

        LinkedHashSet<String> lhSetWords
            = new LinkedHashSet<String>( Arrays.asList(strWords) );

        StringBuilder sbTemp = new StringBuilder();
        int index = 0;

        for(String s : lhSetWords){

            if(index > 0)
                sbTemp.append(",");

            sbTemp.append(s);
            index++;
        }

        str = sbTemp.toString();


        String str1 =this.sectionId+","+other.sectionId;
        String[] strWords1 = str1.split("\\,+");

        LinkedHashSet<String> lhSetWords1
            = new LinkedHashSet<String>( Arrays.asList(strWords1) );

        StringBuilder sbTemp1 = new StringBuilder();
        int index1 = 0;

        for(String s1 : lhSetWords1){

            if(index1 > 0)
                sbTemp1.append(",");

            sbTemp1.append(s1);
            index1++;
        }
        str1 = sbTemp1.toString();

        return new ExamSettingPojo(
            this.examType,
            this.departmentId,
            sectionId=str1,
            this.action,
            subject=str,
            this.examDate,
            this.endDate,
            this.startDate

        );
    }


}
