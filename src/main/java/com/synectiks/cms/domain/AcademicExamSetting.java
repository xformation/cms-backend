package com.synectiks.cms.domain;
import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.synectiks.cms.domain.enumeration.GradeType;
import com.synectiks.cms.domain.enumeration.SemesterEnum;

/**
 * A AcademicExamSetting.
 */
@Entity
@Table(name = "academic_exam_setting")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//@org.springframework.data.elasticsearch.annotations.Document(indexName = "academicexamsetting")
public class AcademicExamSetting implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "exam_name")
    private String examName;

    @Enumerated(EnumType.STRING)
    @Column(name = "semester")
    private SemesterEnum semester;

    @Column(name = "exam_date")
    private LocalDate examDate;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "grade_type")
    private GradeType gradeType;

    @Column(name = "total")
    private Integer total;

    @Column(name = "passing")
    private Integer passing;

    @Column(name = "actions")
    private String actions;

    @Column(name = "groupvalue")
    private Long groupvalue;

    @Column(name = "countvalue")
    private Long countvalue;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "academicyear_id")
    private Long academicyearId;

    @Column(name = "section_id")
    private Long sectionId;

    @Column(name = "batch_id")
    private Long batchId;

    @ManyToOne
    @JsonIgnoreProperties("academicExamSettings")
    private TypeOfGrading typeOfGrading;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExamName() {
        return examName;
    }

    public AcademicExamSetting examName(String examName) {
        this.examName = examName;
        return this;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public SemesterEnum getSemester() {
        return semester;
    }

    public AcademicExamSetting semester(SemesterEnum semester) {
        this.semester = semester;
        return this;
    }

    public void setSemester(SemesterEnum semester) {
        this.semester = semester;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public AcademicExamSetting examDate(LocalDate examDate) {
        this.examDate = examDate;
        return this;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public AcademicExamSetting startTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public AcademicExamSetting endTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public GradeType getGradeType() {
        return gradeType;
    }

    public AcademicExamSetting gradeType(GradeType gradeType) {
        this.gradeType = gradeType;
        return this;
    }

    public void setGradeType(GradeType gradeType) {
        this.gradeType = gradeType;
    }

    public Integer getTotal() {
        return total;
    }

    public AcademicExamSetting total(Integer total) {
        this.total = total;
        return this;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPassing() {
        return passing;
    }

    public AcademicExamSetting passing(Integer passing) {
        this.passing = passing;
        return this;
    }

    public void setPassing(Integer passing) {
        this.passing = passing;
    }

    public String getActions() {
        return actions;
    }

    public AcademicExamSetting actions(String actions) {
        this.actions = actions;
        return this;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public Long getGroupvalue() {
        return groupvalue;
    }

    public AcademicExamSetting groupvalue(Long groupvalue) {
        this.groupvalue = groupvalue;
        return this;
    }

    public void setGroupvalue(Long groupvalue) {
        this.groupvalue = groupvalue;
    }

    public Long getCountvalue() {
        return countvalue;
    }

    public AcademicExamSetting countvalue(Long countvalue) {
        this.countvalue = countvalue;
        return this;
    }

    public void setCountvalue(Long countvalue) {
        this.countvalue = countvalue;
    }

    public Long getBranchId() {
        return branchId;
    }

    public AcademicExamSetting branchId(Long branchId) {
        this.branchId = branchId;
        return this;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public AcademicExamSetting subjectId(Long subjectId) {
        this.subjectId = subjectId;
        return this;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public AcademicExamSetting departmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getAcademicyearId() {
        return academicyearId;
    }

    public AcademicExamSetting academicyearId(Long academicyearId) {
        this.academicyearId = academicyearId;
        return this;
    }

    public void setAcademicyearId(Long academicyearId) {
        this.academicyearId = academicyearId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public AcademicExamSetting sectionId(Long sectionId) {
        this.sectionId = sectionId;
        return this;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public AcademicExamSetting batchId(Long batchId) {
        this.batchId = batchId;
        return this;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public TypeOfGrading getTypeOfGrading() {
        return typeOfGrading;
    }

    public AcademicExamSetting typeOfGrading(TypeOfGrading typeOfGrading) {
        this.typeOfGrading = typeOfGrading;
        return this;
    }

    public void setTypeOfGrading(TypeOfGrading typeOfGrading) {
        this.typeOfGrading = typeOfGrading;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AcademicExamSetting)) {
            return false;
        }
        return id != null && id.equals(((AcademicExamSetting) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AcademicExamSetting{" +
            "id=" + getId() +
            ", examName='" + getExamName() + "'" +
            ", semester='" + getSemester() + "'" +
            ", examDate='" + getExamDate() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            ", gradeType='" + getGradeType() + "'" +
            ", total=" + getTotal() +
            ", passing=" + getPassing() +
            ", actions='" + getActions() + "'" +
            ", groupvalue=" + getGroupvalue() +
            ", countvalue=" + getCountvalue() +
            ", branchId=" + getBranchId() +
            ", subjectId=" + getSubjectId() +
            ", departmentId=" + getDepartmentId() +
            ", academicyearId=" + getAcademicyearId() +
            ", sectionId=" + getSectionId() +
            ", batchId=" + getBatchId() +
            "}";
    }
}
