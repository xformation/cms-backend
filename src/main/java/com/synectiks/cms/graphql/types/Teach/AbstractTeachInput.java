package com.synectiks.cms.graphql.types.Teach;

import com.synectiks.cms.domain.Subject;
import com.synectiks.cms.domain.Teacher;

public class AbstractTeachInput {
	private Long id;
    private String desc;
    private Teacher teacher;
    private Subject subject;
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
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	@Override
	public String toString() {
		return "AbstractTeachInput [id=" + id + ", desc=" + desc + ", teacher=" + teacher + ", subject=" + subject
				+ "]";
	}



    
}
