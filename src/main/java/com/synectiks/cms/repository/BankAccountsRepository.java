package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.BankAccounts;
import com.synectiks.cms.utils.JPASearchRepository;


/**
 * Spring Data  repository for the BankAccounts entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BankAccountsRepository extends JPASearchRepository<BankAccounts, Long> {

}
