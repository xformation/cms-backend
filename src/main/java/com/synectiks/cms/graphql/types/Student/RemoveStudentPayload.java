package com.synectiks.cms.graphql.types.Student;


import java.util.List;

import com.synectiks.cms.entities.Student;

public class RemoveStudentPayload {
    private final List<Student> students;

    public RemoveStudentPayload(List<Student> students) {
    	this.students = students; 
    }

    public List<Student> getStudents() {
      return students;
    }
}
