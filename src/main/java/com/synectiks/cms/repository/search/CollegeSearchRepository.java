package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.College;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the College entity.
 */
public interface CollegeSearchRepository extends JPASearchRepository<College, Long> {
}
