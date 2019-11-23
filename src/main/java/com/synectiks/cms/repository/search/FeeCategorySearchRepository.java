package com.synectiks.cms.repository.search;

import com.synectiks.commons.entities.cms.FeeCategory;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the FeeCategory entity.
 */
public interface FeeCategorySearchRepository extends JPASearchRepository<FeeCategory, Long> {
}
