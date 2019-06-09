package com.synectiks.cms.domain;

public class CmsFeeSettingsVo {
    private Long lateFeeId;
    private String isAutoLateFee;
    private Integer lateFeeDays;
    private String chargeType;
    private Long fixedCharges;
    private String percentCharges;
    private String lateFeeFrequency;
    private Integer lateFeeRepeatDays;
	
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chargeType == null) ? 0 : chargeType.hashCode());
		result = prime * result + ((fixedCharges == null) ? 0 : fixedCharges.hashCode());
		
		result = prime * result + ((isAutoLateFee == null) ? 0 : isAutoLateFee.hashCode());
		result = prime * result + ((lateFeeDays == null) ? 0 : lateFeeDays.hashCode());
		result = prime * result + ((lateFeeFrequency == null) ? 0 : lateFeeFrequency.hashCode());
		result = prime * result + ((lateFeeRepeatDays == null) ? 0 : lateFeeRepeatDays.hashCode());
		result = prime * result + ((percentCharges == null) ? 0 : percentCharges.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CmsFeeSettingsVo other = (CmsFeeSettingsVo) obj;
		if (chargeType == null) {
			if (other.chargeType != null)
				return false;
		} else if (!chargeType.equals(other.chargeType))
			return false;
		if (fixedCharges == null) {
			if (other.fixedCharges != null)
				return false;
		} else if (!fixedCharges.equals(other.fixedCharges))
			return false;
		
		if (isAutoLateFee == null) {
			if (other.isAutoLateFee != null)
				return false;
		} else if (!isAutoLateFee.equals(other.isAutoLateFee))
			return false;
		if (lateFeeDays == null) {
			if (other.lateFeeDays != null)
				return false;
		} else if (!lateFeeDays.equals(other.lateFeeDays))
			return false;
		if (lateFeeFrequency == null) {
			if (other.lateFeeFrequency != null)
				return false;
		} else if (!lateFeeFrequency.equals(other.lateFeeFrequency))
			return false;
		if (lateFeeRepeatDays == null) {
			if (other.lateFeeRepeatDays != null)
				return false;
		} else if (!lateFeeRepeatDays.equals(other.lateFeeRepeatDays))
			return false;
		if (percentCharges == null) {
			if (other.percentCharges != null)
				return false;
		} else if (!percentCharges.equals(other.percentCharges))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AbstractLateFeeInput [lateFeeId=" + lateFeeId + ", isAutoLateFee=" + isAutoLateFee + ", lateFeeDays=" + lateFeeDays
				+ ", chargeType=" + chargeType + ", fixedCharges=" + fixedCharges + ", percentCharges=" + percentCharges
				+ ", lateFeeFrequency=" + lateFeeFrequency + ", lateFeeRepeatDays=" + lateFeeRepeatDays + "]";
	}
	public Long getLateFeeId() {
		return lateFeeId;
	}
	public void setLateFeeId(Long lateFeeId) {
		this.lateFeeId = lateFeeId;
	}
    
    
    

    
}
