package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.Insurance;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Insurance entity.
 */
public interface InsuranceSearchRepository extends JPASearchRepository<Insurance, Long> {
}
