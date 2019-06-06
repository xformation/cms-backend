package com.synectiks.cms.graphql.types.LateFee;

public class AbstractLateFeeInput {
    private Long id;
    private String assignLateFee;
    private Integer lateFeeDays;
    private String fixed;
    private String percentage;
    private Long fixedCharges;
    private String percentCharges;
    private String lateFeeOneTime;
    private Integer lateFeeRepeatDays;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssignLateFee() {
        return assignLateFee;
    }

    public void setAssignLateFee(String assignLateFee) {
        this.assignLateFee = assignLateFee;
    }

    public Integer getLateFeeDays() {
        return lateFeeDays;
    }

    public void setLateFeeDays(Integer lateFeeDays) {
        this.lateFeeDays = lateFeeDays;
    }

    public String getFixed() {
        return fixed;
    }

    public void setFixed(String fixed) {
        this.fixed = fixed;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
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

	public String getLateFeeOneTime() {
		return lateFeeOneTime;
	}

	public void setLateFeeOneTime(String lateFeeOneTime) {
		this.lateFeeOneTime = lateFeeOneTime;
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
		result = prime * result + ((assignLateFee == null) ? 0 : assignLateFee.hashCode());
		result = prime * result + ((fixed == null) ? 0 : fixed.hashCode());
		result = prime * result + ((fixedCharges == null) ? 0 : fixedCharges.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lateFeeDays == null) ? 0 : lateFeeDays.hashCode());
		result = prime * result + ((lateFeeOneTime == null) ? 0 : lateFeeOneTime.hashCode());
		result = prime * result + ((lateFeeRepeatDays == null) ? 0 : lateFeeRepeatDays.hashCode());
		result = prime * result + ((percentCharges == null) ? 0 : percentCharges.hashCode());
		result = prime * result + ((percentage == null) ? 0 : percentage.hashCode());
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
		AbstractLateFeeInput other = (AbstractLateFeeInput) obj;
		if (assignLateFee != other.assignLateFee)
			return false;
		if (fixed != other.fixed)
			return false;
		if (fixedCharges == null) {
			if (other.fixedCharges != null)
				return false;
		} else if (!fixedCharges.equals(other.fixedCharges))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lateFeeDays == null) {
			if (other.lateFeeDays != null)
				return false;
		} else if (!lateFeeDays.equals(other.lateFeeDays))
			return false;
		if (lateFeeOneTime != other.lateFeeOneTime)
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
		if (percentage != other.percentage)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractLateFeeInput [id=" + id + ", assignLateFee=" + assignLateFee + ", lateFeeDays=" + lateFeeDays
				+ ", fixed=" + fixed + ", percentage=" + percentage + ", fixedCharges=" + fixedCharges
				+ ", percentCharges=" + percentCharges + ", lateFeeOneTime=" + lateFeeOneTime + ", lateFeeRepeatDays="
				+ lateFeeRepeatDays + "]";
	}

    
}
