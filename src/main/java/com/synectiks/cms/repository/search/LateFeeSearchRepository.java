package com.synectiks.cms.repository.search;

import com.synectiks.commons.entities.cms.LateFee;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the LateFee entity.
 */
public interface LateFeeSearchRepository extends JPASearchRepository<LateFee, Long> {
}
