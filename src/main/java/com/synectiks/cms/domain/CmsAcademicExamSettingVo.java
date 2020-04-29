package com.synectiks.cms.domain;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.synectiks.cms.domain.enumeration.GradeType;
import com.synectiks.cms.domain.enumeration.SemesterEnum;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

public class CmsAcademicExamSettingVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String examName;
    private SemesterEnum semester;
    private LocalDate examDate;
    private String strexamDate;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)

    private String day;
    private String duration;
    private String startTime;
    private GradeType gradeType;
    private String endTime;
    private Integer total;
    private Integer passing;
    private String actions;
    private Long groupValue;
    private Long countValue;
    private Long departmentId;
    private Long batchId;
    private Long sectionId;
    private Long branchId;
    private Long academicyearId;
    private Long subjectId;
    private Department department;
    private AcademicYear academicyear;
    private Section section;
    private Subject subject;
    private Batch batch;
    private Branch branch;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public SemesterEnum getSemester() {
        return semester;
    }

    public void setSemester(SemesterEnum semester) {
        this.semester = semester;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public String getStrexamDate() {
        return strexamDate;
    }

    public void setStrexamDate(String strexamDate) {
        this.strexamDate = strexamDate;
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

    public GradeType getGradeType() {
        return gradeType;
    }

    public void setGradeType(GradeType gradeType) {
        this.gradeType = gradeType;
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

    public Long getGroupValue() {
        return groupValue;
    }

    public void setGroupValue(Long groupValue) {
        this.groupValue = groupValue;
    }

    public Long getCountValue() {
        return countValue;
    }

    public void setCountValue(Long countValue) {
        this.countValue = countValue;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getAcademicyearId() {
        return academicyearId;
    }

    public void setAcademicyearId(Long academicyearId) {
        this.academicyearId = academicyearId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "CmsAcademicExamSettingVo{" +
            "id=" + id +
            ", examName='" + examName + '\'' +
            ", semester=" + semester +
            ", examDate=" + examDate +
            ", strexamDate='" + strexamDate + '\'' +
            ", day='" + day + '\'' +
            ", duration='" + duration + '\'' +
            ", startTime='" + startTime + '\'' +
            ", gradeType=" + gradeType +
            ", endTime='" + endTime + '\'' +
            ", total=" + total +
            ", passing=" + passing +
            ", actions='" + actions + '\'' +
            ", groupValue=" + groupValue +
            ", countValue=" + countValue +
            ", departmentId=" + departmentId +
            ", batchId=" + batchId +
            ", sectionId=" + sectionId +
            ", branchId=" + branchId +
            ", academicyearId=" + academicyearId +
            ", subjectId=" + subjectId +
            ", department=" + department +
            ", academicyear=" + academicyear +
            ", section=" + section +
            ", subject=" + subject +
            ", batch=" + batch +
            ", branch=" + branch +
            '}';
    }
}
