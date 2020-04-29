package com.synectiks.cms.domain;

import java.util.List;

public class LibraryFilterDataCache {

    private List<Department> departments;
    private List<Batch> batches;
    private List<CmsLibraryVo> libraries;
    private List<CmsBookVo> books;
    private List<Student> students;

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

    public List<CmsLibraryVo> getLibraries() {
        return libraries;
    }

    public void setLibraries(List<CmsLibraryVo> libraries) {
        this.libraries = libraries;
    }

    public List<CmsBookVo> getBooks() {
        return books;
    }

    public void setBooks(List<CmsBookVo> books) {
        this.books = books;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
