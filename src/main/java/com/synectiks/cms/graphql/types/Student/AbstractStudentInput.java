package com.synectiks.cms.graphql.types.Student;

public class AbstractStudentInput {
    private Long id;
    private String studentName;


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
	
}
