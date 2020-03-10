package com.synectiks.cms.repository;

import com.synectiks.cms.domain.BankAccounts;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BankAccounts entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BankAccountsRepository extends JPASearchRepository<BankAccounts, Long> {

}
