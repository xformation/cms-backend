package com.synectiks.cms.graphql.types.PaymentRemainder;

public class AbstractPaymentRemainderInput {
	
    private Long id;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstPaymentRemainderDays == null) ? 0 : firstPaymentRemainderDays.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isAutoRemainder == null) ? 0 : isAutoRemainder.hashCode());
		result = prime * result + ((isFirstPaymentRemainder == null) ? 0 : isFirstPaymentRemainder.hashCode());
		result = prime * result + ((isOverDuePaymentRemainder == null) ? 0 : isOverDuePaymentRemainder.hashCode());
		result = prime * result + ((isRemainderRecipients == null) ? 0 : isRemainderRecipients.hashCode());
		result = prime * result + ((isSecondPaymentRemainder == null) ? 0 : isSecondPaymentRemainder.hashCode());
		result = prime * result + ((overDuePaymentRemainderAfterDueDateOrUntilPaid == null) ? 0
				: overDuePaymentRemainderAfterDueDateOrUntilPaid.hashCode());
		result = prime * result + ((overDuePaymentRemainderDays == null) ? 0 : overDuePaymentRemainderDays.hashCode());
		result = prime * result + ((remainderRecipients == null) ? 0 : remainderRecipients.hashCode());
		result = prime * result + ((secondPaymentRemainderDays == null) ? 0 : secondPaymentRemainderDays.hashCode());
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
		AbstractPaymentRemainderInput other = (AbstractPaymentRemainderInput) obj;
		if (firstPaymentRemainderDays == null) {
			if (other.firstPaymentRemainderDays != null)
				return false;
		} else if (!firstPaymentRemainderDays.equals(other.firstPaymentRemainderDays))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isAutoRemainder == null) {
			if (other.isAutoRemainder != null)
				return false;
		} else if (!isAutoRemainder.equals(other.isAutoRemainder))
			return false;
		if (isFirstPaymentRemainder == null) {
			if (other.isFirstPaymentRemainder != null)
				return false;
		} else if (!isFirstPaymentRemainder.equals(other.isFirstPaymentRemainder))
			return false;
		if (isOverDuePaymentRemainder == null) {
			if (other.isOverDuePaymentRemainder != null)
				return false;
		} else if (!isOverDuePaymentRemainder.equals(other.isOverDuePaymentRemainder))
			return false;
		if (isRemainderRecipients == null) {
			if (other.isRemainderRecipients != null)
				return false;
		} else if (!isRemainderRecipients.equals(other.isRemainderRecipients))
			return false;
		if (isSecondPaymentRemainder == null) {
			if (other.isSecondPaymentRemainder != null)
				return false;
		} else if (!isSecondPaymentRemainder.equals(other.isSecondPaymentRemainder))
			return false;
		if (overDuePaymentRemainderAfterDueDateOrUntilPaid == null) {
			if (other.overDuePaymentRemainderAfterDueDateOrUntilPaid != null)
				return false;
		} else if (!overDuePaymentRemainderAfterDueDateOrUntilPaid
				.equals(other.overDuePaymentRemainderAfterDueDateOrUntilPaid))
			return false;
		if (overDuePaymentRemainderDays == null) {
			if (other.overDuePaymentRemainderDays != null)
				return false;
		} else if (!overDuePaymentRemainderDays.equals(other.overDuePaymentRemainderDays))
			return false;
		if (remainderRecipients == null) {
			if (other.remainderRecipients != null)
				return false;
		} else if (!remainderRecipients.equals(other.remainderRecipients))
			return false;
		if (secondPaymentRemainderDays == null) {
			if (other.secondPaymentRemainderDays != null)
				return false;
		} else if (!secondPaymentRemainderDays.equals(other.secondPaymentRemainderDays))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AbstractPaymentRemainderInput [id=" + id + ", isAutoRemainder=" + isAutoRemainder
				+ ", isFirstPaymentRemainder=" + isFirstPaymentRemainder + ", firstPaymentRemainderDays="
				+ firstPaymentRemainderDays + ", isSecondPaymentRemainder=" + isSecondPaymentRemainder
				+ ", secondPaymentRemainderDays=" + secondPaymentRemainderDays + ", isOverDuePaymentRemainder="
				+ isOverDuePaymentRemainder + ", overDuePaymentRemainderAfterDueDateOrUntilPaid="
				+ overDuePaymentRemainderAfterDueDateOrUntilPaid + ", overDuePaymentRemainderDays="
				+ overDuePaymentRemainderDays + ", isRemainderRecipients=" + isRemainderRecipients
				+ ", remainderRecipients=" + remainderRecipients + "]";
	}

    
    
    
    
    
    
    

    
}
