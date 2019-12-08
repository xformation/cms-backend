package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.FeeCategory;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the FeeCategory entity.
 */
public interface FeeCategorySearchRepository extends JPASearchRepository<FeeCategory, Long> {
}
