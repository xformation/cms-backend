package com.synectiks.cms.repository.search;

import com.synectiks.commons.entities.cms.Teach;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Teach entity.
 */
public interface TeachSearchRepository extends JPASearchRepository<Teach, Long> {
}
