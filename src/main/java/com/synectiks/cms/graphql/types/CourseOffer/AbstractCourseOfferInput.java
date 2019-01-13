package com.synectiks.cms.graphql.types.CourseOffer;

import com.synectiks.cms.domain.College;
import com.synectiks.cms.domain.Department;
import com.synectiks.cms.domain.Subject;

public class AbstractCourseOfferInput {
	private Long id;
    private String desc;
//    private College college;
//    private Department department;
//    private Subject subject;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/*public College getCollege() {
		return college;
	}
	public void setCollege(College college) {
		this.college = college;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}*/
	@Override
	public String toString() {
		return "AbstractCourseOfferInput [id=" + id + ", desc=" + desc + "]";
	}
	
    
	



    
}
