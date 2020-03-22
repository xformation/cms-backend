package com.synectiks.cms.filter.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.synectiks.cms.business.service.StudentService;
import com.synectiks.cms.domain.CmsStudentVo;
import com.synectiks.cms.domain.FeeDetails;
import com.synectiks.cms.domain.Student;
import com.synectiks.cms.domain.enumeration.Gender;
import com.synectiks.cms.domain.enumeration.InvoicePaymentStatus;
import com.synectiks.cms.domain.enumeration.StudentTypeEnum;

@Component
public class StudentFilterProcessor {


    @Autowired
    private StudentService studentService;

    public List<Student> searchStudent(Long departmentId, Long batchId, Long sectionId, Long branchId, Gender gender, StudentTypeEnum studentType, String studentName){
        return studentService.searchStudent(departmentId, batchId, sectionId, branchId, gender, studentType, studentName);
    }
    
    public List<Student> searchStudent(StudentListFilterInput filter){
        return studentService.searchStudent(filter);
    }
    
    public List<FeeDetails> getFeeDetailsList(CmsStudentVo vo){
        return studentService.getFeeDetailsList(vo);
    }
    
    public Float getTotalFees(List<FeeDetails> feeDetailsList) {
    	return studentService.getTotalFees(feeDetailsList);
    }
    
    public Long getTotalFeesPaid(CmsStudentVo vo) {
    	return studentService.getTotalFeePaid(vo);
    }
    public Long getTotalFeesOverDue(CmsStudentVo vo) {
    	return studentService.getTotalFeeOverDue(vo);
    }
}

