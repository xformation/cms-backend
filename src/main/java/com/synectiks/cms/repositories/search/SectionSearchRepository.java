package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.Section;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Section entity.
 */
public interface SectionSearchRepository extends JPASearchRepository<Section, Long> {
}
