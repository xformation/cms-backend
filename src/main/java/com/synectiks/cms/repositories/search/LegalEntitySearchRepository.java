package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.LegalEntity;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the LegalEntity entity.
 */
public interface LegalEntitySearchRepository extends JPASearchRepository<LegalEntity, Long> {
}
