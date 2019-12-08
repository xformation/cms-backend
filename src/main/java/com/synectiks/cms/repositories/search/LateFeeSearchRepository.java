package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.LateFee;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the LateFee entity.
 */
public interface LateFeeSearchRepository extends JPASearchRepository<LateFee, Long> {
}
