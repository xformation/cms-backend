package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.Contract;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Contract entity.
 */
public interface ContractSearchRepository extends JPASearchRepository<Contract, Long> {
}
