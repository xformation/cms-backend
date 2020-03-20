package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.BankAccounts;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the BankAccounts entity.
 */
public interface BankAccountsSearchRepository extends JPASearchRepository<BankAccounts, Long> {
}
