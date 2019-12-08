package com.synectiks.cms.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.synectiks.cms.utils.IESEntity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

//import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A StudentExamReport.
 */
@Entity
@Table(name = "student_exam_report")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//@Document(indexName = "studentexamreport")
public class StudentExamReport implements Serializable, IESEntity {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "marks_obtained", nullable = false)
    private Integer marksObtained;

    @NotNull
    @Column(name = "comments", nullable = false)
    private String comments;

    @Column(name = "g_op")
    private Integer gOp;

    @ManyToOne
    @JsonIgnoreProperties("studentExamReports")
    private AcademicExamSetting academicExamSetting;

    @ManyToOne
    @JsonIgnoreProperties("studentExamReports")
    private Student student;

    @ManyToOne
    @JsonIgnoreProperties("studentExamReports")
    private Section section;

    @ManyToOne
    @JsonIgnoreProperties("studentExamReports")
    private Subject subject;

    @ManyToOne
    @JsonIgnoreProperties("studentExamReports")
    private Department department;

    @ManyToOne
    @JsonIgnoreProperties("studentExamReports")
    private TypeOfGrading typeOfGrading;

    @ManyToOne
    @JsonIgnoreProperties("studentExamReports")
    private Batch batch;

    @ManyToOne
    @JsonIgnoreProperties("studentExamReports")
    private AcademicYear academicyear;

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

    public Section getSection() {
        return section;
    }

    public StudentExamReport section(Section section) {
        this.section = section;
        return this;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Subject getSubject() {
        return subject;
    }

    public StudentExamReport subject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Department getDepartment() {
        return department;
    }

    public StudentExamReport department(Department department) {
        this.department = department;
        return this;
    }

    public void setDepartment(Department department) {
        this.department = department;
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

    public Batch getBatch() {
        return batch;
    }

    public StudentExamReport batch(Batch batch) {
        this.batch = batch;
        return this;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public AcademicYear getAcademicyear() {
        return academicyear;
    }

    public StudentExamReport academicyear(AcademicYear academicYear) {
        this.academicyear = academicYear;
        return this;
    }

    public void setAcademicyear(AcademicYear academicYear) {
        this.academicyear = academicYear;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StudentExamReport studentExamReport = (StudentExamReport) o;
        if (studentExamReport.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), studentExamReport.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StudentExamReport{" +
            "id=" + getId() +
            ", marksObtained=" + getMarksObtained() +
            ", comments='" + getComments() + "'" +
            ", gOp=" + getgOp() +
            "}";
    }
}
