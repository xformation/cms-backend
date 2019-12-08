package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.Teach;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Teach entity.
 */
public interface TeachSearchRepository extends JPASearchRepository<Teach, Long> {
}
