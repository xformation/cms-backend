package com.synectiks.cms.repository;

import com.synectiks.cms.domain.BankAccounts;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BankAccounts entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BankAccountsRepository extends JpaRepository<BankAccounts, Long> {

}
