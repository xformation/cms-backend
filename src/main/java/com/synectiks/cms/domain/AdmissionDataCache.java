package com.synectiks.cms.domain;

import com.synectiks.cms.graphql.types.course.Course;

import java.util.List;

public class AdmissionDataCache {
    private List<Department> departments;
    private List<Batch> batches;
    private List<Branch> branches;
    private List<State> states;
    private List<City> cities;
    private List<CmsCourseEnumVo> courses;


    public List<Department> getDepartments() {
        return departments;
    }

    public List<Batch> getBatches() {
        return batches;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public List<State> getStates() {
        return states;
    }

    public List<City> getCities() {
        return cities;
    }

    public List<CmsCourseEnumVo> getCourses() {
        return courses;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public void setCourses(List<CmsCourseEnumVo> courses) {
        this.courses = courses;
    }
}
