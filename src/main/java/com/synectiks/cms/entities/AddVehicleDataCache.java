package com.synectiks.cms.entities;

import java.util.List;

public class AddVehicleDataCache {
    private List<College> colleges;
    private List<Branch> branches;
    private List<CmsTypeOfInsuranceVo> typeOfInsurances;
    private List<CmsTypeOfOwnershipVo> typeOfOwnerships;

    public List<College> getColleges() {
        return colleges;
    }
    public void setColleges(List<College> colleges) {
        this.colleges = colleges;
    }
    public List<Branch> getBranches() {
        return branches;
    }
    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public List<CmsTypeOfInsuranceVo> getTypeOfInsurances() {
        return typeOfInsurances;
    }

    public void setTypeOfInsurances(List<CmsTypeOfInsuranceVo> typeOfInsurances) {
        this.typeOfInsurances = typeOfInsurances;
    }

    public List<CmsTypeOfOwnershipVo> getTypeOfOwnerships() {
        return typeOfOwnerships;
    }

    public void setTypeOfOwnerships(List<CmsTypeOfOwnershipVo> typeOfOwnerships) {
        this.typeOfOwnerships = typeOfOwnerships;
    }
}
