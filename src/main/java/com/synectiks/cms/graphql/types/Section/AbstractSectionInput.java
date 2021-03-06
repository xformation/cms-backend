package com.synectiks.cms.graphql.types.Section;

import com.synectiks.cms.domain.enumeration.SectionEnum;

public class AbstractSectionInput {
    private Long id;
    private SectionEnum section;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public SectionEnum getSection() {
		return section;
	}
	public void setSection(SectionEnum section) {
		this.section = section;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
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
		AbstractSectionInput other = (AbstractSectionInput) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (section != other.section)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AbstractSectionInput [id=" + id + ", section=" + section + "]";
	}

	
	
}
