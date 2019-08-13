package com.synectiks.cms.dataimport;

public class CmsTableWithDomainAndRepositoryMapper {

	private String tableName;
	private Class domain;
	private Class repository;
	
	public CmsTableWithDomainAndRepositoryMapper(String tableName, Class domain, Class repository) {
		this.tableName = tableName;
		this.domain = domain;
		this.repository = repository;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((domain == null) ? 0 : domain.hashCode());
		result = prime * result + ((repository == null) ? 0 : repository.hashCode());
		result = prime * result + ((tableName == null) ? 0 : tableName.hashCode());
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
		CmsTableWithDomainAndRepositoryMapper other = (CmsTableWithDomainAndRepositoryMapper) obj;
		if (domain == null) {
			if (other.domain != null)
				return false;
		} else if (!domain.equals(other.domain))
			return false;
		if (repository == null) {
			if (other.repository != null)
				return false;
		} else if (!repository.equals(other.repository))
			return false;
		if (tableName == null) {
			if (other.tableName != null)
				return false;
		} else if (!tableName.equals(other.tableName))
			return false;
		return true;
	}


	public String getTableName() {
		return tableName;
	}


	public Class getDomain() {
		return domain;
	}


	public Class getRepository() {
		return repository;
	}
	
	
}
