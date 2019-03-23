package com.synectiks.cms.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synectiks.cms.domain.AcademicYear;
import com.synectiks.cms.domain.Branch;
import com.synectiks.cms.domain.College;
import com.synectiks.cms.domain.Invoice;
import com.synectiks.cms.domain.Student;
import com.synectiks.cms.domain.enumeration.InvoicePaymentStatus;
import com.synectiks.cms.repository.InvoiceRepository;

@Service
@Transactional
public class CmsInvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	public Long getTotalInvoice(Long collegeId, Long branchId, Long academicYearId) {
    	Long a = getTotalPaidInvoice(collegeId, branchId, academicYearId);
    	Long b = getTotalUnPaidInvoice(collegeId, branchId, academicYearId);
    	Long c = getTotalCanceledInvoice(collegeId, branchId, academicYearId);
    	return a+b+c;
    }
    
    public Long getTotalPaidInvoice(Long collegeId, Long branchId, Long academicYearId) {
    	Invoice inv = new Invoice();
    	
    	if(collegeId != null) {
    		College college = new College();
    		college.setId(collegeId);
    		inv.setCollege(college);
    	}
    	
    	if(branchId != null) {
    		Branch branch = new Branch();
    		branch.setId(branchId);
    		inv.setBranch(branch);
    	}
    	
    	AcademicYear ac = new AcademicYear();
    	ac.setId(academicYearId);
    	
    	inv.setPaymentStatus(InvoicePaymentStatus.PAID);
    	inv.setAcademicYear(ac);
    	Example<Invoice> example = Example.of(inv);
    	Long cnt = this.invoiceRepository.count(example);
    	return cnt;
    }
    
    public Long getTotalUnPaidInvoice(Long collegeId, Long branchId, Long academicYearId) {
    	Invoice inv = new Invoice();
    	
    	if(collegeId != null) {
    		College college = new College();
    		college.setId(collegeId);
    		inv.setCollege(college);
    	}
    	
    	if(branchId != null) {
    		Branch branch = new Branch();
    		branch.setId(branchId);
    		inv.setBranch(branch);
    	}
    	
    	AcademicYear ac = new AcademicYear();
    	ac.setId(academicYearId);
    	
    	inv.setPaymentStatus(InvoicePaymentStatus.UNPAID);
    	inv.setAcademicYear(ac);
    	Example<Invoice> example = Example.of(inv);
    	Long cnt = this.invoiceRepository.count(example);
    	return cnt;
    }
    
    public Long getTotalCanceledInvoice(Long collegeId, Long branchId, Long academicYearId) {
    	Invoice inv = new Invoice();
    	
    	if(collegeId != null) {
    		College college = new College();
    		college.setId(collegeId);
    		inv.setCollege(college);
    	}
    	
    	if(branchId != null) {
    		Branch branch = new Branch();
    		branch.setId(branchId);
    		inv.setBranch(branch);
    	}
    	AcademicYear ac = new AcademicYear();
    	ac.setId(academicYearId);
    	
    	inv.setPaymentStatus(InvoicePaymentStatus.CANCELED);
    	inv.setAcademicYear(ac);
    	Example<Invoice> example = Example.of(inv);
    	Long cnt = this.invoiceRepository.count(example);
    	return cnt;
    }
    
    public List<Invoice> searchInvoice(String invoiceNumber, Long studentId) {
    	Invoice inv = new Invoice();
    	
    	if(invoiceNumber != null) {
    		inv.setInvoiceNumber(invoiceNumber);
    	}
    	if(studentId != null) {
    		Student student = new Student();
    		student.setId(studentId);
    		inv.setStudent(student);
    	}
    	Example<Invoice> example = Example.of(inv);
    	List<Invoice> list = this.invoiceRepository.findAll(example);
    	return list;
    }
    
}
