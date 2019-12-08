package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.Contract;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Contract entity.
 */
@Repository
public interface ContractRepository extends JPASearchRepository<Contract, Long> {

}
