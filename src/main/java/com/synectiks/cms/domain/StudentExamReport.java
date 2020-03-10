package com.synectiks.cms.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.synectiks.cms.utils.IESEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A StudentExamReport.
 */
@Entity
@Table(name = "student_exam_report")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StudentExamReport implements Serializable, IESEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "marks_obtained")
    private Integer marksObtained;

    @Column(name = "comments")
    private String comments;

    @Column(name = "g_op")
    private Integer gOp;

    @Column(name = "section_id")
    private Long sectionId;

    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "batch_id")
    private Long batchId;

    @Column(name = "academicyear_id")
    private Long academicyearId;

    @ManyToOne
    @JsonIgnoreProperties("studentExamReports")
    private AcademicExamSetting academicExamSetting;

    @ManyToOne
    @JsonIgnoreProperties("studentExamReports")
    private Student student;

    @ManyToOne
    @JsonIgnoreProperties("studentExamReports")
    private TypeOfGrading typeOfGrading;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMarksObtained() {
        return marksObtained;
    }

    public StudentExamReport marksObtained(Integer marksObtained) {
        this.marksObtained = marksObtained;
        return this;
    }

    public void setMarksObtained(Integer marksObtained) {
        this.marksObtained = marksObtained;
    }

    public String getComments() {
        return comments;
    }

    public StudentExamReport comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getgOp() {
        return gOp;
    }

    public StudentExamReport gOp(Integer gOp) {
        this.gOp = gOp;
        return this;
    }

    public void setgOp(Integer gOp) {
        this.gOp = gOp;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public StudentExamReport sectionId(Long sectionId) {
        this.sectionId = sectionId;
        return this;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public StudentExamReport subjectId(Long subjectId) {
        this.subjectId = subjectId;
        return this;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public StudentExamReport departmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public StudentExamReport batchId(Long batchId) {
        this.batchId = batchId;
        return this;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getAcademicyearId() {
        return academicyearId;
    }

    public StudentExamReport academicyearId(Long academicyearId) {
        this.academicyearId = academicyearId;
        return this;
    }

    public void setAcademicyearId(Long academicyearId) {
        this.academicyearId = academicyearId;
    }

    public AcademicExamSetting getAcademicExamSetting() {
        return academicExamSetting;
    }

    public StudentExamReport academicExamSetting(AcademicExamSetting academicExamSetting) {
        this.academicExamSetting = academicExamSetting;
        return this;
    }

    public void setAcademicExamSetting(AcademicExamSetting academicExamSetting) {
        this.academicExamSetting = academicExamSetting;
    }

    public Student getStudent() {
        return student;
    }

    public StudentExamReport student(Student student) {
        this.student = student;
        return this;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public TypeOfGrading getTypeOfGrading() {
        return typeOfGrading;
    }

    public StudentExamReport typeOfGrading(TypeOfGrading typeOfGrading) {
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
        if (!(o instanceof StudentExamReport)) {
            return false;
        }
        return id != null && id.equals(((StudentExamReport) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "StudentExamReport{" +
            "id=" + getId() +
            ", marksObtained=" + getMarksObtained() +
            ", comments='" + getComments() + "'" +
            ", gOp=" + getgOp() +
            ", sectionId=" + getSectionId() +
            ", subjectId=" + getSubjectId() +
            ", departmentId=" + getDepartmentId() +
            ", batchId=" + getBatchId() +
            ", academicyearId=" + getAcademicyearId() +
            "}";
    }
}
