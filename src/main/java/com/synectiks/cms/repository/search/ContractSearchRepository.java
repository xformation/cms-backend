package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Contract;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Contract entity.
 */
public interface ContractSearchRepository extends JPASearchRepository<Contract, Long> {
}
