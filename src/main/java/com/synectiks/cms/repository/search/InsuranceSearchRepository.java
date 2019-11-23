package com.synectiks.cms.repository.search;

import com.synectiks.commons.entities.cms.Insurance;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Insurance entity.
 */
public interface InsuranceSearchRepository extends JPASearchRepository<Insurance, Long> {
}
