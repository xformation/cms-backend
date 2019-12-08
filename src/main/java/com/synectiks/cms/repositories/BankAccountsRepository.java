package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.BankAccounts;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the BankAccounts entity.
 */
@Repository
public interface BankAccountsRepository extends JPASearchRepository<BankAccounts, Long> {

}
