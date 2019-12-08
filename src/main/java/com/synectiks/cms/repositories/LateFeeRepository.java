package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.LateFee;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the LateFee entity.
 */
@Repository
public interface LateFeeRepository extends JPASearchRepository<LateFee, Long> {

}
