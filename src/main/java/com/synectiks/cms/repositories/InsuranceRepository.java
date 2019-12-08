package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.Insurance;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Insurance entity.
 */
@Repository
public interface InsuranceRepository extends JPASearchRepository<Insurance, Long> {

}
