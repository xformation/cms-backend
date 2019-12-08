package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.BankAccounts;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the BankAccounts entity.
 */
public interface BankAccountsSearchRepository extends JPASearchRepository<BankAccounts, Long> {
}
