package com.synectiks.cms.graphql.types.DueDate;

import java.util.Date;
import java.util.Objects;

import com.synectiks.cms.entities.enumeration.Frequency;

public class AbstractDueDateInput {
    private Long id;
    private String paymentMethod;
    private Integer installments;
    private String dayDesc;
    private Integer paymentDay;
    private Frequency frequency;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDayDesc() {
        return dayDesc;
    }

    public void setDayDesc(String dayDesc) {
        this.dayDesc = dayDesc;
    }

    
    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

	public Integer getPaymentDay() {
		return paymentDay;
	}

	public void setPaymentDay(Integer paymentDay) {
		this.paymentDay = paymentDay;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dayDesc == null) ? 0 : dayDesc.hashCode());
		result = prime * result + ((frequency == null) ? 0 : frequency.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((installments == null) ? 0 : installments.hashCode());
		result = prime * result + ((paymentDay == null) ? 0 : paymentDay.hashCode());
		result = prime * result + ((paymentMethod == null) ? 0 : paymentMethod.hashCode());
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
		AbstractDueDateInput other = (AbstractDueDateInput) obj;
		if (dayDesc == null) {
			if (other.dayDesc != null)
				return false;
		} else if (!dayDesc.equals(other.dayDesc))
			return false;
		if (frequency != other.frequency)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (installments == null) {
			if (other.installments != null)
				return false;
		} else if (!installments.equals(other.installments))
			return false;
		if (paymentDay == null) {
			if (other.paymentDay != null)
				return false;
		} else if (!paymentDay.equals(other.paymentDay))
			return false;
		if (paymentMethod == null) {
			if (other.paymentMethod != null)
				return false;
		} else if (!paymentMethod.equals(other.paymentMethod))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractDueDateInput [id=" + id + ", paymentMethod=" + paymentMethod + ", installments=" + installments
				+ ", dayDesc=" + dayDesc + ", paymentDay=" + paymentDay + ", frequency=" + frequency + "]";
	}
}
