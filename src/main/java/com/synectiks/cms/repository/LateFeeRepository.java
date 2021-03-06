package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.LateFee;
import com.synectiks.cms.utils.JPASearchRepository;


/**
 * Spring Data  repository for the LateFee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LateFeeRepository extends JPASearchRepository<LateFee, Long> {

}
