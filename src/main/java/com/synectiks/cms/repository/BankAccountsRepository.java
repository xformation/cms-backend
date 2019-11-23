package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.BankAccounts;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the BankAccounts entity.
 */
@Repository
public interface BankAccountsRepository extends JPASearchRepository<BankAccounts, Long> {

}
