package com.synectiks.cms.domain;

import com.synectiks.cms.domain.enumeration.CourseEnum;

import java.util.List;

public class AdmissionDataCache {
    private List<Branch> branches;
    private List<Department> departments;
    private List<Batch> batches;
    private List<State> states;
    private List<City> cities;
    private List<CmsCourseEnumVo> courseEnums;

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

    public List<Branch> getBranches() {
        return branches;
    }
    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public List<State> getStates() {
        return states;
    }
    public void setStates(List<State> states) {
        this.states = states;
    }

    public List<City> getCities() {
        return cities;
    }
    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public List<CmsCourseEnumVo> getCourseEnums() {
        return courseEnums;
    }
    public void setCourseEnums(List<CmsCourseEnumVo> courseEnums) {
        this.courseEnums = courseEnums;
    }

}
