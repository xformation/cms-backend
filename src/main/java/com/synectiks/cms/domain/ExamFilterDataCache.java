package com.synectiks.cms.domain;

import java.util.List;

public class ExamFilterDataCache {

    private List<Department> departments;
    private List<Batch> batches;
    private List<Section> sections;
    private List<CmsSemesterVo> semesters;
    private List<Branch> branches;
    private List<Subject> subjects;
    private List<AcademicExamSetting> academicExamSettings;

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public List<CmsSemesterVo> getSemesters() {
        return semesters;
    }
    public void setSemesters(List<CmsSemesterVo> semesters) {
        this.semesters = semesters;
    }


    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<AcademicExamSetting> getAcademicExamSettings() {
        return academicExamSettings;
    }

    public void setAcademicExamSettings(List<AcademicExamSetting> academicExamSettings) {
        this.academicExamSettings = academicExamSettings;
    }
}
