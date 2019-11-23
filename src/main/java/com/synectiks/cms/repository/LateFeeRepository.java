package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.LateFee;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the LateFee entity.
 */
@Repository
public interface LateFeeRepository extends JPASearchRepository<LateFee, Long> {

}
