package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.FeeCategory;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the FeeCategory entity.
 */
@Repository
public interface FeeCategoryRepository extends JPASearchRepository<FeeCategory, Long> {

}
