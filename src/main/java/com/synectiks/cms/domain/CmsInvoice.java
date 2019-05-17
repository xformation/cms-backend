package com.synectiks.cms.domain;

public class CmsInvoice {

	private Long totalInvoice = 0L;
	private Long totalPaidInvoice = 0L;
	private Long totalUnPaidInvoice = 0L;
	private Long totalCanceledInvoice = 0L;
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
