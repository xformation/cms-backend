package com.synectiks.cms.graphql.types.Student;


import com.synectiks.cms.domain.Student;

import java.util.List;

public class RemoveStudentPayload {
    private final List<Student> students;

    public RemoveStudentPayload(List<Student> students) {
    	this.students = students; 
    }

    public List<Student> getStudents() {
      return students;
    }
}
