package com.synectiks.cms.graphql.types.Subject;

import com.synectiks.cms.domain.enumeration.CommonSubEnum;
import com.synectiks.cms.domain.enumeration.ElectiveEnum;

public class AbstractSubjectInput {
    private Long id;
    private CommonSubEnum commonSub;
    private ElectiveEnum electiveSub;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public CommonSubEnum getCommonSub() {
		return commonSub;
	}

	public void setCommonSub(CommonSubEnum commonSub) {
		this.commonSub = commonSub;
	}

	public ElectiveEnum getElectiveSub() {
		return electiveSub;
	}

	public void setElectiveSub(ElectiveEnum electiveSub) {
		this.electiveSub = electiveSub;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commonSub == null) ? 0 : commonSub.hashCode());
		result = prime * result + ((electiveSub == null) ? 0 : electiveSub.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		AbstractSubjectInput other = (AbstractSubjectInput) obj;
		if (commonSub != other.commonSub)
			return false;
		if (electiveSub != other.electiveSub)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractSubjectInput [id=" + id + ", commonSub=" + commonSub + ", electiveSub=" + electiveSub + "]";
	}

    
    
}
