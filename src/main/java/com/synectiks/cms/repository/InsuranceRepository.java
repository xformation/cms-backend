package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.Insurance;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Insurance entity.
 */
@Repository
public interface InsuranceRepository extends JPASearchRepository<Insurance, Long> {

}
