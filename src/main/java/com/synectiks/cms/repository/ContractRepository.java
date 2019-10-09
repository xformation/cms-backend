package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.Contract;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Contract entity.
 */
@Repository
public interface ContractRepository extends JPASearchRepository<Contract, Long> {

}
