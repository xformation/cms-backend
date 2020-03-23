package com.synectiks.cms.filter.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.synectiks.cms.business.service.StudentService;
import com.synectiks.cms.domain.CmsInvoice;
import com.synectiks.cms.domain.CmsStudentVo;
import com.synectiks.cms.domain.FeeDetails;
import com.synectiks.cms.domain.Student;
import com.synectiks.cms.domain.StudentFacilityLink;
import com.synectiks.cms.domain.enumeration.Gender;
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
    
    public Float getTotalFees(List<FeeDetails> feeDetailsList, List<StudentFacilityLink> facilityList) {
    	return studentService.getTotalFees(feeDetailsList, facilityList);
    }
    
    public Long getTotalFeesPaid(CmsStudentVo vo) {
    	return studentService.getTotalFeePaid(vo);
    }
    public Long getTotalFeesOverDue(CmsStudentVo vo) {
    	return studentService.getTotalFeeOverDue(vo);
    }
    
    public List<StudentFacilityLink> getFacilityList(CmsStudentVo vo){
        return studentService.getFacilityList(vo);
    }
    
    public List<CmsInvoice> getPaymentHistory(CmsStudentVo vo){
    	return studentService.getPaymentHistory(vo);
    }
    
    public String getNextPaymentDate(CmsStudentVo vo) {
    	List<CmsInvoice> list = studentService.getPaymentHistory(vo);
    	if(list.size() > 0) {
    		return list.get(0).getStrNextPaymentDate();
    	}
    	return null;
    }
}

