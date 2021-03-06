package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Teach;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Teach entity.
 */
public interface TeachSearchRepository extends JPASearchRepository<Teach, Long> {
}
