package com.synectiks.cms.entities;

import com.synectiks.cms.entities.enumeration.Frequency;

public class CmsFeeSettingsVo {
    private Long lateFeeId;
    private String isAutoLateFee;
    private Integer lateFeeDays;
    private String chargeType;
    private Long fixedCharges;
    private String percentCharges;
    private String lateFeeFrequency;
    private Integer lateFeeRepeatDays;
	
    
    private Long prId;
    private String isAutoRemainder;
    private String isFirstPaymentRemainder;
    private Integer firstPaymentRemainderDays;
    private String isSecondPaymentRemainder;
    private Integer secondPaymentRemainderDays;
    private String isOverDuePaymentRemainder;
    private String overDuePaymentRemainderAfterDueDateOrUntilPaid;
    private Integer overDuePaymentRemainderDays;
    private String isRemainderRecipients;
    private String remainderRecipients;
    
    private Long dueDateId;
    private String paymentMethod;
    private Integer installments;
    private Integer paymentDay;
    private Frequency frequency;
    
	public String getIsAutoLateFee() {
		return isAutoLateFee;
	}
	public void setIsAutoLateFee(String isAutoLateFee) {
		this.isAutoLateFee = isAutoLateFee;
	}
	public Integer getLateFeeDays() {
		return lateFeeDays;
	}
	public void setLateFeeDays(Integer lateFeeDays) {
		this.lateFeeDays = lateFeeDays;
	}
	public String getChargeType() {
		return chargeType;
	}
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	public Long getFixedCharges() {
		return fixedCharges;
	}
	public void setFixedCharges(Long fixedCharges) {
		this.fixedCharges = fixedCharges;
	}
	public String getPercentCharges() {
		return percentCharges;
	}
	public void setPercentCharges(String percentCharges) {
		this.percentCharges = percentCharges;
	}
	public String getLateFeeFrequency() {
		return lateFeeFrequency;
	}
	public void setLateFeeFrequency(String lateFeeFrequency) {
		this.lateFeeFrequency = lateFeeFrequency;
	}
	public Integer getLateFeeRepeatDays() {
		return lateFeeRepeatDays;
	}
	public void setLateFeeRepeatDays(Integer lateFeeRepeatDays) {
		this.lateFeeRepeatDays = lateFeeRepeatDays;
	}

	
	public Long getLateFeeId() {
		return lateFeeId;
	}
	public void setLateFeeId(Long lateFeeId) {
		this.lateFeeId = lateFeeId;
	}
	public Long getPrId() {
		return prId;
	}
	public void setPrId(Long prId) {
		this.prId = prId;
	}
	public String getIsAutoRemainder() {
		return isAutoRemainder;
	}
	public void setIsAutoRemainder(String isAutoRemainder) {
		this.isAutoRemainder = isAutoRemainder;
	}
	public String getIsFirstPaymentRemainder() {
		return isFirstPaymentRemainder;
	}
	public void setIsFirstPaymentRemainder(String isFirstPaymentRemainder) {
		this.isFirstPaymentRemainder = isFirstPaymentRemainder;
	}
	public Integer getFirstPaymentRemainderDays() {
		return firstPaymentRemainderDays;
	}
	public void setFirstPaymentRemainderDays(Integer firstPaymentRemainderDays) {
		this.firstPaymentRemainderDays = firstPaymentRemainderDays;
	}
	public String getIsSecondPaymentRemainder() {
		return isSecondPaymentRemainder;
	}
	public void setIsSecondPaymentRemainder(String isSecondPaymentRemainder) {
		this.isSecondPaymentRemainder = isSecondPaymentRemainder;
	}
	public Integer getSecondPaymentRemainderDays() {
		return secondPaymentRemainderDays;
	}
	public void setSecondPaymentRemainderDays(Integer secondPaymentRemainderDays) {
		this.secondPaymentRemainderDays = secondPaymentRemainderDays;
	}
	public String getIsOverDuePaymentRemainder() {
		return isOverDuePaymentRemainder;
	}
	public void setIsOverDuePaymentRemainder(String isOverDuePaymentRemainder) {
		this.isOverDuePaymentRemainder = isOverDuePaymentRemainder;
	}
	public String getOverDuePaymentRemainderAfterDueDateOrUntilPaid() {
		return overDuePaymentRemainderAfterDueDateOrUntilPaid;
	}
	public void setOverDuePaymentRemainderAfterDueDateOrUntilPaid(String overDuePaymentRemainderAfterDueDateOrUntilPaid) {
		this.overDuePaymentRemainderAfterDueDateOrUntilPaid = overDuePaymentRemainderAfterDueDateOrUntilPaid;
	}
	public Integer getOverDuePaymentRemainderDays() {
		return overDuePaymentRemainderDays;
	}
	public void setOverDuePaymentRemainderDays(Integer overDuePaymentRemainderDays) {
		this.overDuePaymentRemainderDays = overDuePaymentRemainderDays;
	}
	public String getIsRemainderRecipients() {
		return isRemainderRecipients;
	}
	public void setIsRemainderRecipients(String isRemainderRecipients) {
		this.isRemainderRecipients = isRemainderRecipients;
	}
	public String getRemainderRecipients() {
		return remainderRecipients;
	}
	public void setRemainderRecipients(String remainderRecipients) {
		this.remainderRecipients = remainderRecipients;
	}
	public Long getDueDateId() {
		return dueDateId;
	}
	public void setDueDateId(Long dueDateId) {
		this.dueDateId = dueDateId;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public Integer getInstallments() {
		return installments;
	}
	public void setInstallments(Integer installments) {
		this.installments = installments;
	}
	public Integer getPaymentDay() {
		return paymentDay;
	}
	public void setPaymentDay(Integer paymentDay) {
		this.paymentDay = paymentDay;
	}
	public Frequency getFrequency() {
		return frequency;
	}
	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}
    
    
    

    
}
