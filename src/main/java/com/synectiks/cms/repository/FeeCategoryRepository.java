package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.FeeCategory;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the FeeCategory entity.
 */
@Repository
public interface FeeCategoryRepository extends JPASearchRepository<FeeCategory, Long> {

}
