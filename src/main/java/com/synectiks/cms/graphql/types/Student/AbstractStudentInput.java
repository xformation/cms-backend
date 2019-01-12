package com.synectiks.cms.graphql.types.Student;

import com.synectiks.cms.domain.Batch;
import com.synectiks.cms.domain.Branch;
import com.synectiks.cms.domain.Department;
import com.synectiks.cms.domain.Section;

public class AbstractStudentInput {
    private Long id;
    private String studentName;
    private Batch batch;
    private Section section;
    private Branch branch;
    private Department department;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Batch getBatch() {
		return batch;
	}
	public void setBatch(Batch batch) {
		this.batch = batch;
	}
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "AbstractStudentInput [id=" + id + ", studentName=" + studentName + ", batch=" + batch + ", section="
				+ section + ", branch=" + branch + ", department=" + department + "]";
	}
}
