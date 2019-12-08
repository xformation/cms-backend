package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.Batch;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Batch entity.
 */
public interface BatchSearchRepository extends JPASearchRepository<Batch, Long> {
}
