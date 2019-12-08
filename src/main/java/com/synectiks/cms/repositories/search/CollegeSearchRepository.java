package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.College;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the College entity.
 */
public interface CollegeSearchRepository extends JPASearchRepository<College, Long> {
}
