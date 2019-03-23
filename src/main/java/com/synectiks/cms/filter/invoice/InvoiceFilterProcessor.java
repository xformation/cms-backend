package com.synectiks.cms.filter.invoice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.synectiks.cms.business.service.CmsInvoiceService;
import com.synectiks.cms.domain.Invoice;

@Component
public class InvoiceFilterProcessor {

	private final Logger logger = LoggerFactory.getLogger(InvoiceFilterProcessor.class);
	
	private Long totalInvoice = 0L;
	private Long totalPaidInvoice = 0L;
	private Long totalUnPaidInvoice = 0L;
	private Long totalCanceledInvoice = 0L;
	
	@Autowired
	private CmsInvoiceService cmsInvoiceService;
	
	public List<Invoice> searchInvoice(String invoiceNumber, Long studentId){
		return cmsInvoiceService.searchInvoice(invoiceNumber, studentId);
	}
	
	public Long getTotalInvoice(Long collegeId, Long branchId, Long academicYearId) {
		return cmsInvoiceService.getTotalInvoice(collegeId, branchId, academicYearId);
	}
	
	public Long getTotalPaidInvoice(Long collegeId, Long branchId, Long academicYearId) {
		return cmsInvoiceService.getTotalPaidInvoice(collegeId, branchId, academicYearId);
	}
	
	public Long getTotalUnPaidInvoice(Long collegeId, Long branchId, Long academicYearId) {
		return cmsInvoiceService.getTotalUnPaidInvoice(collegeId, branchId, academicYearId);
	}
	
	public Long getTotalCanceledInvoice(Long collegeId, Long branchId, Long academicYearId) {
		return cmsInvoiceService.getTotalCanceledInvoice(collegeId, branchId, academicYearId);
	}
	
	public InvoiceFilterProcessor getInvoiceData(Long collegeId, Long branchId, Long academicYearId) {
		InvoiceFilterProcessor ifp = new InvoiceFilterProcessor();
		ifp.setTotalInvoice(this.getTotalInvoice(collegeId, branchId, academicYearId));
		ifp.setTotalPaidInvoice(this.getTotalPaidInvoice(collegeId, branchId, academicYearId));
		ifp.setTotalUnPaidInvoice(this.getTotalUnPaidInvoice(collegeId, branchId, academicYearId));
		ifp.setTotalCanceledInvoice(this.getTotalCanceledInvoice(collegeId, branchId, academicYearId));
		return ifp;
	}

	public Long getTotalInvoice() {
		return totalInvoice;
	}

	public void setTotalInvoice(Long totalInvoice) {
		this.totalInvoice = totalInvoice;
	}

	public Long getTotalPaidInvoice() {
		return totalPaidInvoice;
	}

	public void setTotalPaidInvoice(Long totalPaidInvoice) {
		this.totalPaidInvoice = totalPaidInvoice;
	}

	public Long getTotalUnPaidInvoice() {
		return totalUnPaidInvoice;
	}

	public void setTotalUnPaidInvoice(Long totalUnPaidInvoice) {
		this.totalUnPaidInvoice = totalUnPaidInvoice;
	}

	public Long getTotalCanceledInvoice() {
		return totalCanceledInvoice;
	}

	public void setTotalCanceledInvoice(Long totalCanceledInvoice) {
		this.totalCanceledInvoice = totalCanceledInvoice;
	}
	
}
