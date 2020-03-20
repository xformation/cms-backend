package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.FeeCategory;
import com.synectiks.cms.utils.JPASearchRepository;


/**
 * Spring Data  repository for the FeeCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FeeCategoryRepository extends JPASearchRepository<FeeCategory, Long> {

}
