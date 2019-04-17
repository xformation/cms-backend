package com.synectiks.cms.business.service;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.domain.enumeration.Gender;
import com.synectiks.cms.domain.enumeration.StudentTypeEnum;
import com.synectiks.cms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public List<Student> searchStudent(Long departmentId, Long batchId, Long sectionId, Long branchId, Gender gender, StudentTypeEnum studentType, String studentName) {
        Student std = new Student();

        if(studentType != null) {
            std.setStudentType(studentType);
        }

        if(studentName != null){
            std.setStudentName(studentName);
        }

        if(gender != null) {
            std.setSex(gender);
        }
        if(departmentId != null) {
            Department department = new Department();
            department.setId(departmentId);
            std.setDepartment(department);
        }

        if(batchId != null) {
            Batch batch = new Batch();
            batch.setId(batchId);
            std.setBatch(batch);
        }

        if(sectionId != null) {
            Section section = new Section();
            section.setId(sectionId);
            std.setSection(section);
        }
        if(branchId != null) {
            Branch branch = new Branch();
            branch.setId(branchId);
            std.setBranch(branch);
        }
        Example<Student> example = Example.of(std);
        List<Student> list = this.studentRepository.findAll(example);
        return list;
    }
}
