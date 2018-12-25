package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.BankAccounts;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the BankAccounts entity.
 */
public interface BankAccountsSearchRepository extends ElasticsearchRepository<BankAccounts, Long> {
}
