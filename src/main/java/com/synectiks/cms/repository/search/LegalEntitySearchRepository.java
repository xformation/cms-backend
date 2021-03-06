package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.LegalEntity;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the LegalEntity entity.
 */
public interface LegalEntitySearchRepository extends JPASearchRepository<LegalEntity, Long> {
}
