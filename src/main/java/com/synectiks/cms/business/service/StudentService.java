package com.synectiks.cms.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.synectiks.cms.entities.Batch;
import com.synectiks.cms.entities.Branch;
import com.synectiks.cms.entities.Department;
import com.synectiks.cms.entities.Section;
import com.synectiks.cms.entities.Student;
import com.synectiks.cms.entities.enumeration.Gender;
import com.synectiks.cms.entities.enumeration.StudentTypeEnum;
import com.synectiks.cms.filter.student.StudentListFilterInput;
import com.synectiks.cms.graphql.types.Student.StudentType;
import com.synectiks.cms.repositories.StudentRepository;
import com.synectiks.cms.service.util.CommonUtil;

@Component
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    private CommonService commonService;
    
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
    
    
    public List<Student> searchStudent(StudentListFilterInput filter) {
        Student student = new Student();
        if(!CommonUtil.isNullOrEmpty(filter.getBranchId())) {
        	Branch branch = this.commonService.getBranchById(Long.valueOf(filter.getBranchId()));
        	if(branch != null) {
        		student.setBranch(branch);
        	}
        }
        if(!CommonUtil.isNullOrEmpty(filter.getDepartmentId())) {
        	Department department = this.commonService.getDepartmentById(Long.valueOf(filter.getDepartmentId()));
        	if(department != null) {
        		student.setDepartment(department);
        	}
        }
        if(!CommonUtil.isNullOrEmpty(filter.getBatchId())) {
        	Batch batch = this.commonService.getBatchById(Long.valueOf(filter.getBatchId()));
        	if(batch != null) {
        		student.setBatch(batch);
        	}
        }
        if(!CommonUtil.isNullOrEmpty(filter.getSectionId())) {
        	Section section = this.commonService.getSectionById(Long.valueOf(filter.getSectionId()));
        	if(section != null) {
        		student.setSection(section);
        	}
        }
        if(!CommonUtil.isNullOrEmpty(filter.getGender())) {
        	if(filter.getGender().equalsIgnoreCase(Gender.MALE.toString())) {
        		student.setSex(Gender.MALE);
        	}else if(filter.getGender().equalsIgnoreCase(Gender.FEMALE.toString())) {
        		student.setSex(Gender.FEMALE);
        	}else if(filter.getGender().equalsIgnoreCase(Gender.OTHER.toString())) {
        		student.setSex(Gender.OTHER);
        	}
        }
        
        if(!CommonUtil.isNullOrEmpty(filter.getStudentType())) {
        	if(filter.getStudentType().equalsIgnoreCase(StudentTypeEnum.REGULAR.toString())) {
        		student.setStudentType(StudentTypeEnum.REGULAR);
        	}else if(filter.getStudentType().equalsIgnoreCase(StudentTypeEnum.STAFF_CONCESSION.toString())) {
        		student.setStudentType(StudentTypeEnum.STAFF_CONCESSION);
        	}else if(filter.getStudentType().equalsIgnoreCase(StudentTypeEnum.BENEFITS.toString())) {
        		student.setStudentType(StudentTypeEnum.BENEFITS);
        	}else if(filter.getStudentType().equalsIgnoreCase(StudentTypeEnum.SCHOLARSHIP.toString())) {
        		student.setStudentType(StudentTypeEnum.SCHOLARSHIP);
        	}else if(filter.getStudentType().equalsIgnoreCase(StudentTypeEnum.OTHER_BENEFITS.toString())) {
        		student.setStudentType(StudentTypeEnum.OTHER_BENEFITS);
        	}
        }
        
        Example<Student> example = Example.of(student);
        List<Student> list = this.studentRepository.findAll(example);
        return list;
    }
    
}
