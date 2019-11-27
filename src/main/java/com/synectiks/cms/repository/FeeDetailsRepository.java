package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.FeeDetails;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the FeeDetails entity.
 */
@Repository
public interface FeeDetailsRepository extends JPASearchRepository<FeeDetails, Long> {

}
