package com.synectiks.cms.repository.search;

import com.synectiks.commons.entities.cms.Batch;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Batch entity.
 */
public interface BatchSearchRepository extends JPASearchRepository<Batch, Long> {
}
