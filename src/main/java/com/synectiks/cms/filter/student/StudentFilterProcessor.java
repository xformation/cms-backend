package com.synectiks.cms.filter.student;

import com.synectiks.cms.business.service.StudentService;
import com.synectiks.cms.domain.Student;
import com.synectiks.cms.domain.enumeration.Gender;
import com.synectiks.cms.domain.enumeration.StudentTypeEnum;
import com.synectiks.cms.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentFilterProcessor {


    @Autowired
    private StudentService studentService;

    public List<Student> searchStudent(Long departmentId, Long batchId, Long sectionId, Long branchId, Gender gender, StudentTypeEnum studentType, String studentName){
        return studentService.searchStudent(departmentId, batchId, sectionId, branchId, gender, studentType, studentName);
    }
}

