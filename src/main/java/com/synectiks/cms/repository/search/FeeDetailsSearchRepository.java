package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.FeeDetails;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the FeeDetails entity.
 */
public interface FeeDetailsSearchRepository extends JPASearchRepository<FeeDetails, Long> {
}
