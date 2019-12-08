package com.synectiks.cms.entities;

import java.io.Serializable;
import java.util.Objects;

public class CmsDepartmentVo implements Serializable {


    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String description;
    private String deptHead;
    private Long branchId;
    private Long academicyearId;
    private Branch branch;
    private AcademicYear academicyear;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeptHead() {
        return deptHead;
    }

    public void setDeptHead(String deptHead) {
        this.deptHead = deptHead;
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

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public AcademicYear getAcademicyear() {
        return academicyear;
    }

    public void setAcademicyear(AcademicYear academicyear) {
        this.academicyear = academicyear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CmsDepartmentVo departmentDTO = (CmsDepartmentVo) o;
        if (departmentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), departmentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CmsDepartmentVo{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", deptHead='" + deptHead + '\'' +
            ", branchId=" + branchId +
            ", academicyearId=" + academicyearId +
            ", branch=" + branch +
            ", academicyear=" + academicyear +
            '}';
    }
}
