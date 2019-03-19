package com.synectiks.cms.filter.student;

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
    private StudentServiceImpl studentServiceImpl;

    public List<Student> searchStudent(Long departmentId, Long batchId, Long sectionId, Long branchId, Gender gender, StudentTypeEnum studentType, String studentName){
        return studentServiceImpl.searchStudent(departmentId, batchId, sectionId, branchId, gender, studentType, studentName);
    }
}

